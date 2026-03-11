package com.example.new_project_challenge_15.service;

import com.example.new_project_challenge_15.entity.DTO.Nodes;
import com.example.new_project_challenge_15.entity.DTO.doubleReturn;
import com.example.new_project_challenge_15.entity.DTO.relationModel;
import com.example.new_project_challenge_15.models.Role;
import com.example.new_project_challenge_15.modelsPhoto.photoDb;
import com.example.new_project_challenge_15.repository.RoleRepository;
import com.example.new_project_challenge_15.neo4j2.PersonZags;
import com.example.new_project_challenge_15.neo4j2.NewZags;
import com.example.new_project_challenge_15.neo4j2.ZagsPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZAGSDBService {

    @Autowired
    ZagsPersonRepository personRepo;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    newPhotoService newPhotoService;
    private Nodes tryAddPhoto(Nodes node, String IIN) {
        try {
            photoDb photoDb = newPhotoService.getPhotoByIIN(IIN);
            node.setPhotoDbf(photoDb);
            return node;
        } catch (Exception e) {
            System.out.println(e);
        }
        return node;
    }

    public List<String> filterRelations(Long id, List<String> relations) {
        Role currole = roleRepository.findRoleById(id);
        List<String> newRel = new ArrayList<>();
        for (String relation: relations) {
            if (currole.getRelations() != null) {
                if (currole.getRelations().contains(relation)) {
                    newRel.add(relation);
                }
            } else {
                newRel.add(relation);
            }
        }
        return newRel;
    }

    public doubleReturn shortOpen(Long id, Long ID, List<String> relations, int limit) {
        return ConstructDoubleReturn(id, personRepo.shortOpen(ID, relations, limit));
    }

    public doubleReturn getShortestPaths(Long id, String FIRST, String SECOND, List<String> list) {
        return ConstructDoubleReturn(id, personRepo.getPathsWithIIN(FIRST, SECOND, list));
    }
    public doubleReturn getShortestPathWithFIO(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        return ConstructDoubleReturn(id, personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,RELS));
    }
    public doubleReturn getShortestPathWithFIOAccurate(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,RELS));
    }
    public doubleReturn getShortestPathWithFIOAccurate12(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,RELS));
    }
    public doubleReturn getShortestPathWithFIOAccurate21(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,RELS));
    }
    public doubleReturn getShortestPathWithFIObezs(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,RELS));
    }
    public doubleReturn getShortestPathWithFIOsbez(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        return ConstructDoubleReturn(id, personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,RELS));
    }
    public doubleReturn getShortestPathWithFIObezbez(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,RELS));
    }






    public doubleReturn getPersonTree(Long id, String person,int depth, int limit, List<String> relations) {
        if(depth==1) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepthOne(person, limit, relations));
        }
        if(depth==2) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepthTwo(person, limit, relations));
        }
        if(depth==3) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepthThree(person, limit, relations));
        }
        if(depth==4) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth4(person, limit, relations));
        }
        if(depth==5) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth5(person, limit, relations));
        }
        if(depth==6) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth6(person, limit, relations));
        }
        if(depth==7) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth7(person, limit, relations));
        }
        if(depth==8) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth8(person, limit, relations));
        }
        if(depth==9) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth9(person, limit, relations));
        }
        if(depth==10) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth10(person, limit, relations));
        }
        if(depth==11) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth11(person, limit, relations));
        }
        if(depth==12) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth12(person, limit, relations));
        }
        if(depth==13) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth13(person, limit, relations));
        }
        return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth13(person, limit, relations));
    }
    public doubleReturn getPersonByFIO_(Long id, String F,String I,String O,int depth, int limit, List<String> relations) {
        if(depth==1) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth1(F,I,O, limit, relations));
        }
        if(depth==2) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth2(F,I,O, limit, relations));
        }
        if(depth==3) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth3(F,I,O, limit, relations));
        }
        if(depth==4) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth4(F,I,O, limit, relations));
        }
        if(depth==5) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth5(F,I,O, limit, relations));
        }
        if(depth==6) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth6(F,I,O, limit, relations));
        }
        if(depth==7) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth7(F,I,O, limit, relations));
        }
        if(depth==8) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth8(F,I,O, limit, relations));
        }
        if(depth==9) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth9(F,I,O, limit, relations));
        }
        if(depth==10) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth10(F,I,O, limit, relations));
        }
        if(depth==11) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth11(F,I,O, limit, relations));
        }
        if(depth==12) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth12(F,I,O, limit, relations));
        }
        if(depth==13) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13(F,I,O, limit, relations));
        }
        return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13(F,I,O, limit, relations));
    }
    public doubleReturn getPersonByFIO_Accurate(Long id, String F,String I,String O,int depth, int limit, List<String> relations) {
        if(depth==1) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth1Accurate(F,I,O, limit, relations));
        }
        if(depth==2) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth2Accurate(F,I,O, limit, relations));
        }
        if(depth==3) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth3Accurate(F,I,O, limit, relations));
        }
        if(depth==4) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth4Accurate(F,I,O, limit, relations));
        }
        if(depth==5) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth5Accurate(F,I,O, limit, relations));
        }
        if(depth==6) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth6Accurate(F,I,O, limit, relations));
        }
        if(depth==7) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth7Accurate(F,I,O, limit, relations));
        }
        if(depth==8) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth8Accurate(F,I,O, limit, relations));
        }
        if(depth==9) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth9Accurate(F,I,O, limit, relations));
        }
        if(depth==10) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth10Accurate(F,I,O, limit, relations));
        }
        if(depth==11) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth11Accurate(F,I,O, limit, relations));
        }
        if(depth==12) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth12Accurate(F,I,O, limit, relations));
        }
        if(depth==13) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13Accurate(F,I,O, limit, relations));
        }
        return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13Accurate(F,I,O, limit, relations));
    }
    public doubleReturn getPersonByFIO_withoutO(Long id, String F,String I,String O,int depth, int limit, List<String> relations) {
        if(depth==1) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth1withoutO(F,I,O, limit, relations));
        }
        if(depth==2) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth2withoutO(F,I,O, limit, relations));
        }
        if(depth==3) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth3withoutO(F,I,O, limit, relations));
        }
        if(depth==4) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth4withoutO(F,I,O, limit, relations));
        }
        if(depth==5) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth5withoutO(F,I,O, limit, relations));
        }
        if(depth==6) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth6withoutO(F,I,O, limit, relations));
        }
        if(depth==7) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth7withoutO(F,I,O, limit, relations));
        }
        if(depth==8) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth8withoutO(F,I,O, limit, relations));
        }
        if(depth==9) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth9withoutO(F,I,O, limit, relations));
        }
        if(depth==10) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth10withoutO(F,I,O, limit, relations));
        }
        if(depth==11) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth11withoutO(F,I,O, limit, relations));
        }
        if(depth==12) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth12withoutO(F,I,O, limit, relations));
        }
        if(depth==13) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13withoutO(F,I,O, limit, relations));
        }
        return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13withoutO(F,I,O, limit, relations));
    }
    private Map<String, Object> getPropertyMap(Object obj) {
        Map<String, Object> properties = new HashMap<>();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field: fields) {
            try {
                Object value = field.get(obj);
                properties.put(field.getName(), value);
            } catch (IllegalAccessException e){
//                e.printStackTrace();
            }
        }

        return properties;
    }
    private Map<String, Object> getPropertyMap(Long id, Object obj) {
        Map<String, Object> properties = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        Role currRole = roleRepository.findRoleById(id);
        if (currRole.getPerson_properties() == null) {
            for (Field field: fields) {
                try {
                    Object value = field.get(obj);
                    properties.put(field.getName(), value);
                } catch (IllegalAccessException e){
//                    e.printStackTrace();
                }
            }
        } else {
            for (Field field: fields) {
                try {
                    if (currRole.getPerson_properties().contains(field.getName()) || currRole.getCompany_properties().contains(field.getName())) {
                        Object value = field.get(obj);
                        properties.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e){
//                    e.printStackTrace();
                }
            }
        }

        return properties;
    }

    private doubleReturn ConstructDoubleReturn(Long User, List<PersonZags> personsList) {
        List<Nodes> nodes = new ArrayList<>();
        List<relationModel> edges = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        for (PersonZags object : personsList) {
            if (!ids.contains(object.getId())) {
                ids.add(object.getId());
                Nodes currNode = new Nodes();
                Map<String, Object> properties = getPropertyMap(User, object);
                currNode = tryAddPhoto(currNode, object.getIIN());
//                photoDb photoDb = newPhotoService.getPhotoByIIN(object.getIIN());
//                currNode.setPhotoDbf(photoDb);
                currNode.setId(object.getId());
                currNode.setProperties(properties);
                nodes.add(currNode);
            }
            List<NewZags> ZAGS = object.getZagsList();
            for (NewZags relation : ZAGS) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(relation.getPerson().getId(), object.getId(), properties);
                currRel.setType("ZAGS");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }


        }
        doubleReturn doubleReturn = new doubleReturn(nodes, edges);
        for (Long id : ids) {
            System.out.println(id);
        }
        return doubleReturn;
    }
}
