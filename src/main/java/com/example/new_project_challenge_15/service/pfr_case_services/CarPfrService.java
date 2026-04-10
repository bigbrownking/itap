package com.example.new_project_challenge_15.service.pfr_case_services;

import com.example.new_project_challenge_15.entity.DTO.JsonCarData;
import com.example.new_project_challenge_15.entity.DTO.Nodes;
import com.example.new_project_challenge_15.entity.DTO.doubleReturn;
import com.example.new_project_challenge_15.entity.DTO.relationModel;
import com.example.new_project_challenge_15.models.Role;
import com.example.new_project_challenge_15.repository.PersonRepo;
import com.example.new_project_challenge_15.repository.RoleRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarPfrService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PersonRepo personRepo;

    private Map<String, JsonCarData> carDataMap = new HashMap<>();

    @PostConstruct
    public void loadJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("car_pfr.json");
            JsonNode root = mapper.readTree(is);
            JsonNode objectNode = root.get("object");

            objectNode.fieldNames().forEachRemaining(plateNumber -> {
                JsonNode carNode = objectNode.get(plateNumber);
                JsonCarData data = new JsonCarData();
                data.setNodes(mapper.convertValue(carNode.get("nodes"),
                        new TypeReference<List<Map<String, Object>>>() {
                        }));
                data.setEdges(mapper.convertValue(carNode.get("edges"),
                        new TypeReference<List<Map<String, Object>>>() {
                        }));
                carDataMap.put(plateNumber.toUpperCase(), data);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public doubleReturn getCarTree(Long id, String plateNumber) {
        JsonCarData data = carDataMap.get(plateNumber.toUpperCase());
        if (data == null) {
            return new doubleReturn(new ArrayList<>(), new ArrayList<>());
        }
        return constructDoubleReturn(id, data);
    }

    public doubleReturn shortOpen(Long id, String text) {
        JsonCarData data = carDataMap.get(text.trim());
        if (data == null) {
            return new doubleReturn(new ArrayList<>(), new ArrayList<>());
        }
        return constructDoubleReturn(id, data);
    }

    private doubleReturn constructDoubleReturn(Long userId, JsonCarData data) {
        List<Nodes> nodes = new ArrayList<>();
        List<relationModel> edges = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        for (Map<String, Object> rawNode : data.getNodes()) {
            Long nodeId = toLong(rawNode.get("id"));
            if (nodeId == null || ids.contains(nodeId)) continue;

            ids.add(nodeId);
            Nodes currNode = new Nodes();
            currNode.setId(nodeId);

            Map<String, Object> rawProperties = (Map<String, Object>) rawNode.get("properties");
            Map<String, Object> filteredProperties = filterPropertiesByRole(userId, rawProperties);
            currNode.setProperties(filteredProperties);
            nodes.add(currNode);
        }

        for (Map<String, Object> rawEdge : data.getEdges()) {
            Long from = toLong(rawEdge.get("from"));
            Long to = toLong(rawEdge.get("to"));
            String type = (String) rawEdge.get("type");
            Map<String, Object> properties = (Map<String, Object>) rawEdge.get("properties");

            relationModel currRel = new relationModel(from, to, properties != null ? properties : new HashMap<>());
            currRel.setType(type != null ? type : "OWNER");
            edges.add(currRel);
        }

        doubleReturn result = new doubleReturn(nodes, edges);
        return CountAll(result);
    }

    public doubleReturn getCarTreeByIin(Long id, String iin) {
        JsonCarData data = carDataMap.get(iin.trim());
        if (data == null) {
            return new doubleReturn(new ArrayList<>(), new ArrayList<>());
        }
        return constructDoubleReturn(id, data);
    }

    private Long toLong(Object val) {
        if (val == null) return null;
        if (val instanceof Integer) return ((Integer) val).longValue();
        if (val instanceof Long) return (Long) val;
        try {
            return Long.parseLong(val.toString());
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, Object> filterPropertiesByRole(Long userId, Map<String, Object> rawProperties) {
        Map<String, Object> properties = new HashMap<>();
        if (rawProperties == null) return properties;

        Role currRole = roleRepository.findRoleById(userId);
        if (currRole == null || currRole.getPerson_properties() == null) {
            return new HashMap<>(rawProperties);
        }

        for (Map.Entry<String, Object> entry : rawProperties.entrySet()) {
            String key = entry.getKey();
            if (currRole.getPerson_properties().contains(key)
                    || currRole.getCompany_properties().contains(key)) {
                properties.put(key, entry.getValue());
            }
        }
        return properties;
    }

    private doubleReturn CountAll(doubleReturn list) {
        List<Nodes> newlist = new ArrayList<>();
        for (Nodes node : list.getNodes()) {
            try {
                Nodes updated = setRelaCount(node);
                newlist.add(updated);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        list.setNodes(newlist);
        return list;
    }

    private Nodes setRelaCount(Nodes node) {
        Long rel = personRepo.countRels(node.getId());
        node.setRelCount(rel);
        return node;
    }
}
