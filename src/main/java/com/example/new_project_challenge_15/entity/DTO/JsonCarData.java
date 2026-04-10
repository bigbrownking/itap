package com.example.new_project_challenge_15.entity.DTO;

import java.util.List;
import java.util.Map;

public class JsonCarData {
    private List<Map<String, Object>> nodes;
    private List<Map<String, Object>> edges;

    public List<Map<String, Object>> getNodes() {
        return nodes;
    }

    public void setNodes(List<Map<String, Object>> nodes) {
        this.nodes = nodes;
    }

    public List<Map<String, Object>> getEdges() {
        return edges;
    }

    public void setEdges(List<Map<String, Object>> edges) {
        this.edges = edges;
    }
}
