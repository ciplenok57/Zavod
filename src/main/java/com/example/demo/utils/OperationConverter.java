package com.example.demo.utils;

import com.example.demo.entity.*;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class OperationConverter {


    /**
     * Генерация из операции в реальную операцию
     * @param operation
     * @return
     */
    public static RealOperation operationToRealOperation(Operation operation) {
        RealOperation realOperation = new RealOperation(operation.getInternalId(), operation.getName());
        realOperation.setInternalId(operation.getId());
        realOperation.setPosition(operation.getPosition());
        return realOperation;
    }

    /**
     * Получения графа по оюъекту конструкции
     * @param construction
     * @return
     */
    public static Graph<RealOperation, DefaultEdge> constructionToGraphConverter(Construction construction) {
        Graph<RealOperation, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

        for (RealOperation realOperation : construction.getOperations()) {
            graph.addVertex(realOperation);
        }

        for (Edge<RealOperation> edge : construction.getEdgesList()) {
            RealOperation source = graph.vertexSet().stream().filter(el -> el.getId().equals(edge.getSource().getId()))
                    .findFirst().orElseThrow(RuntimeException::new);
            RealOperation target = graph.vertexSet().stream().filter(el -> el.getId().equals(edge.getTarget().getId()))
                    .findFirst().orElseThrow(RuntimeException::new);
            graph.addEdge(source, target);
        }

        return graph;
    }

    public static Construction productTemplateToConstructionConverter(ProductTemplate productTemplate){
        Set<RealOperation> operations = new HashSet<>();
        Set<Edge<RealOperation>> edgesSet = new HashSet<>();
        Set<RealOperation> startOperations = new HashSet<>();

        for (Operation operation : productTemplate.getOperations()) {
            operations.add(OperationConverter.operationToRealOperation(operation));
        }

        for (Edge<Operation> operationEdge : productTemplate.getEdgesList()) {
            Edge<RealOperation> realOperationEdge = new Edge<>();

            realOperationEdge.setSource(OperationConverter.operationToRealOperation(operationEdge.getSource()));
            realOperationEdge.setTarget(OperationConverter.operationToRealOperation(operationEdge.getTarget()));

            edgesSet.add(realOperationEdge);
        }

        Construction construction = new Construction();
        construction.setId(UUID.randomUUID());
        construction.setOperations(operations);
        construction.setStartOperations(startOperations);
        construction.setEdgesList(edgesSet);

        return construction;
    }

    /**
     * Сохранение картинки графа
     * @param g
     */
    public static void printGraph(Graph<RealOperation, DefaultEdge> g) {

        JGraphXAdapter<RealOperation, DefaultEdge> graphAdapter =
                new JGraphXAdapter<>(g);
        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);

        layout.execute(graphAdapter.getDefaultParent());

        BufferedImage image =
                mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
        File imgFile = new File("src/main/resources/graph.png");
        try {
            ImageIO.write(image, "PNG", imgFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
