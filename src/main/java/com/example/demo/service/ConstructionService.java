package com.example.demo.service;

import com.example.demo.dto.construction.CreateConstructionDto;
import com.example.demo.entity.*;
import com.example.demo.exceprion.OperationStartException;
import com.example.demo.exceprion.OperationStopException;
import com.example.demo.repository.ConstructionRepository;
import com.example.demo.repository.ProductTemplateRepository;
import com.example.demo.utils.OperationConverter;
import lombok.AllArgsConstructor;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConstructionService {

    private final ConstructionRepository constructionRepository;
    private final ProductTemplateRepository productTemplateRepository;

    public Construction createConstruction(CreateConstructionDto createConstructionDto) {

        ProductTemplate productTemplate = productTemplateRepository.findById(createConstructionDto
                .getTemplateId()).orElseThrow(RuntimeException::new);

        return constructionRepository.save(OperationConverter.productTemplateToConstructionConverter(productTemplate));
    }

    public Construction findOneConstruction(UUID constructionId) {
        return constructionRepository.findById(constructionId).orElseThrow(RuntimeException::new);
    }

    public Construction startOperation(UUID constructionId, UUID operationId) {
        Construction construction = constructionRepository.findById(constructionId).orElseThrow(RuntimeException::new);
        Graph<RealOperation, DefaultEdge> graph = OperationConverter.constructionToGraphConverter(construction);

        RealOperation operation = graph
                .vertexSet().stream().filter(uri -> uri.getId().equals(operationId)).findAny()
                .orElseThrow(() -> new RuntimeException("Такой операции не существует"));

        Set<DefaultEdge> incomingEdges = graph.incomingEdgesOf(operation);

        boolean allPrevOperationIsComplete = true;

        // все предыдущие операции выполнены
        for (DefaultEdge edges : incomingEdges) {
            RealOperation edgeSource = graph.getEdgeSource(edges);
            if (!edgeSource.getStatus().equals(RealOperationStatus.FINISHED)) {
                allPrevOperationIsComplete = false;
                break;
            }
        }

        // меняем состояние операции
        if (allPrevOperationIsComplete) {
            RealOperation realOperation = construction.getOperations()
                    .stream().filter(el -> el.getId().equals(operationId))
                    .findAny().orElseThrow(() -> new RuntimeException("Операция не найдена"));
            realOperation.setStatus(RealOperationStatus.IN_PROCESS);
            construction.addNewOperation(realOperation);

            return constructionRepository.save(construction);
        } else {
            throw new OperationStartException("Невозможно начать операцию. Не выполнены предыдущие");
        }

    }

    public Construction stopOperation(UUID constructionId, UUID operationId) {
        Construction construction = constructionRepository.findById(constructionId).orElseThrow(RuntimeException::new);
        Graph<RealOperation, DefaultEdge> graph = OperationConverter.constructionToGraphConverter(construction);

        RealOperation operation = graph
                .vertexSet().stream().filter(uri -> uri.getId().equals(operationId)).findAny()
                .orElseThrow(() -> new RuntimeException("Такой операции не существует"));

        switch (operation.getStatus()) {
            case NEW -> throw new OperationStopException("Невозможно завершить операцию. Она еще не начата");
            case IN_PROCESS -> operation.setStatus(RealOperationStatus.FINISHED);
            case FINISHED -> throw new OperationStopException("Невозможно завершить операцию. Она уже завершена");
        }

        return constructionRepository.save(construction);
    }


}
