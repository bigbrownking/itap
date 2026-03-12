package com.example.new_project_challenge_15.service;


import com.example.new_project_challenge_15.entity.*;
import com.example.new_project_challenge_15.entity.DTO.Nodes;
import com.example.new_project_challenge_15.entity.DTO.doubleReturn;
import com.example.new_project_challenge_15.entity.DTO.relationModel;
import com.example.new_project_challenge_15.entity.RELS_22.*;
import com.example.new_project_challenge_15.models.Role;
import com.example.new_project_challenge_15.modelsPhoto.photoDb;
import com.example.new_project_challenge_15.repository.CompanyRepo;
import com.example.new_project_challenge_15.repository.PersonRepo;
import com.example.new_project_challenge_15.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class FiPersonsService {
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    PersonRepo personRepo;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    com.example.new_project_challenge_15.service.newPhotoService newPhotoService;
    @Autowired
    WeightedRelationSearch weightedRelationSearch;

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
        System.out.println("ID" + id);
        Role currole = roleRepository.findRoleById(id);
        List<String> newRel = new ArrayList<>();
        if (currole.getRelations() != null) {
            for (String relation: relations) {
                if (currole.getRelations().contains(relation)) {
                    newRel.add(relation);
                }
            }
        } else {
            return relations;
        }
        for (String relation: newRel) {
            System.out.println(relation);
        }
        return newRel;
    }

    public doubleReturn shortOpen(Long id, Long ID, List<String> relations, int limit) {
        return ConstructDoubleReturn(id, personRepo.shortOpen(ID, relations, limit), companyRepo.shortOpen(ID, relations, limit));
    }

    public doubleReturn getShortestPaths(Long id, String FIRST, String SECOND, List<String> list) {
        List<String> rels = new ArrayList<>(weightedRelationSearch.FirstLevelSearch(list));
        if (!personRepo.getPathsWithIIN(FIRST, SECOND, rels).isEmpty() || !companyRepo.getPathsWithIIN(FIRST, SECOND, rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getPathsWithIIN(FIRST, SECOND, rels), companyRepo.getPathsWithIIN(FIRST, SECOND, rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(list);
            if (!personRepo.getPathsWithIIN(FIRST, SECOND, rels).isEmpty() || !companyRepo.getPathsWithIIN(FIRST, SECOND, rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getPathsWithIIN(FIRST, SECOND, rels), companyRepo.getPathsWithIIN(FIRST, SECOND, rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(list);
                if (!personRepo.getPathsWithIIN(FIRST, SECOND, rels).isEmpty() || !companyRepo.getPathsWithIIN(FIRST, SECOND, rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getPathsWithIIN(FIRST, SECOND, rels), companyRepo.getPathsWithIIN(FIRST, SECOND, rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(list);
                    if (!personRepo.getPathsWithIIN(FIRST, SECOND, rels).isEmpty() || !companyRepo.getPathsWithIIN(FIRST, SECOND, rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getPathsWithIIN(FIRST, SECOND, rels), companyRepo.getPathsWithIIN(FIRST, SECOND, rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(list);
                        if (!personRepo.getPathsWithIIN(FIRST, SECOND, rels).isEmpty() || !companyRepo.getPathsWithIIN(FIRST, SECOND, rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithIIN(FIRST, SECOND, rels), companyRepo.getPathsWithIIN(FIRST, SECOND, rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithIIN(FIRST, SECOND, list), companyRepo.getPathsWithIIN(FIRST, SECOND, list));
                        }
                    }
                }
            }
        }
    }
    public doubleReturn getShortestPathWithFIO(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        List<String> rels = new ArrayList<>(weightedRelationSearch.FirstLevelSearch(RELS));
        if (!personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(RELS);
            if (!personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(RELS);
                if (!personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(RELS);
                    if (!personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(RELS);
                        if (!personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,RELS), companyRepo.getPathsWithFIO(F1,I1,O1,F2,I2,O2,RELS));
                        }
                    }
                }
            }
        }
    }
    public doubleReturn getShortestPathWithFIOAccurate(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        List<String> rels = new ArrayList<>(weightedRelationSearch.FirstLevelSearch(RELS));
        if (!personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(RELS);
            if (!personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(RELS);
                if (!personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(RELS);
                    if (!personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(RELS);
                        if (!personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,RELS), companyRepo.getPathsWithFIOAccurate2(F1,I1,O1,F2,I2,O2,RELS));
                        }
                    }
                }
            }
        }
    }
    public doubleReturn getShortestPathWithFIOAccurate12(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        List<String> rels = new ArrayList<>(weightedRelationSearch.FirstLevelSearch(RELS));
        if (!personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(RELS);
            if (!personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(RELS);
                if (!personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(RELS);
                    if (!personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(RELS);
                        if (!personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,RELS), companyRepo.getPathsWithFIOAccurate12(F1,I1,O1,F2,I2,O2,RELS));
                        }
                    }
                }
            }
        }
    }
    public doubleReturn getShortestPathWithFIOAccurate21(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        List<String> rels = new ArrayList<>(weightedRelationSearch.FirstLevelSearch(RELS));
        if (!personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(RELS);
            if (!personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(RELS);
                if (!personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(RELS);
                    if (!personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(RELS);
                        if (!personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,RELS), companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,RELS));
                        }
                    }
                }
            }
        }
    }
    public doubleReturn getShortestPathWithFIObezs(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        List<String> rels = new ArrayList<>(weightedRelationSearch.FirstLevelSearch(RELS));
        if (!personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(RELS);
            if (!personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(RELS);
                if (!personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(RELS);
                    if (!personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(RELS);
                        if (!personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOAccurate21(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,RELS), companyRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,RELS), companyRepo.getPathsWithFIObezs(F1,I1,O1,F2,I2,O2,RELS));
                        }
                    }
                }
            }
        }
    }
    public doubleReturn getShortestPathWithFIOsbez(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        List<String> rels = new ArrayList<>(weightedRelationSearch.FirstLevelSearch(RELS));
        if (!personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(RELS);
            if (!personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(RELS);
                if (!personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(RELS);
                    if (!personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(RELS);
                        if (!personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,RELS), companyRepo.getPathsWithFIOsbez(F1,I1,O1,F2,I2,O2,RELS));
                        }
                    }
                }
            }
        }
    }
    public doubleReturn getShortestPathWithFIObezbez(Long id, String F1,String I1,String O1, String F2,String I2,String O2, List<String> RELS) {
        List<String> rels = new ArrayList<>(weightedRelationSearch.FirstLevelSearch(RELS));
        if (!personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(RELS);
            if (!personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(RELS);
                if (!personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(RELS);
                    if (!personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(RELS);
                        if (!personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels).isEmpty() || !companyRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels), companyRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,RELS), companyRepo.getPathsWithFIObezbez(F1,I1,O1,F2,I2,O2,RELS));
                        }
                    }
                }
            }
        }
    }




    public doubleReturn getUlFlPath(Long id, String ul, String person, List<String> relations) {
        List<String> rels = new ArrayList<>(weightedRelationSearch.FirstLevelSearch(relations));
        if (!personRepo.getUlFlPath(person, ul, rels).isEmpty() || !companyRepo.getUlFlPath(person, ul, rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getUlFlPath(person, ul, rels), companyRepo.getUlFlPath(person, ul, rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(relations);
            if (!personRepo.getUlFlPath(person, ul, rels).isEmpty() || !companyRepo.getUlFlPath(person, ul, rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getUlFlPath(person, ul, rels), companyRepo.getUlFlPath(person, ul, rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(relations);
                if (!personRepo.getUlFlPath(person, ul, rels).isEmpty() || !companyRepo.getUlFlPath(person, ul, rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getUlFlPath(person, ul, rels), companyRepo.getUlFlPath(person, ul, rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(relations);
                    if (!personRepo.getUlFlPath(person, ul, rels).isEmpty() || !companyRepo.getUlFlPath(person, ul, rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getUlFlPath(person, ul, rels), companyRepo.getUlFlPath(person, ul, rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(relations);
                        if (!personRepo.getUlFlPath(person, ul, rels).isEmpty() || !companyRepo.getUlFlPath(person, ul, rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getUlFlPath(person, ul, rels), companyRepo.getUlFlPath(person, ul, rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getUlFlPath(person, ul, relations), companyRepo.getUlFlPath(person, ul, relations));
                        }
                    }
                }
            }
        }
    }
    public doubleReturn getUlFlPathByFIO(Long id, String F,String I,String O,String ul, List<String> relations) {
        List<String> rels = weightedRelationSearch.FirstLevelSearch(relations);
        if (!personRepo.getUlFlPathByFIO(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIO(F,I,O, ul, rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIO(F,I,O, ul, rels), companyRepo.getUlFlPathByFIO(F,I,O, ul, rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(relations);
            if (!personRepo.getUlFlPathByFIO(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIO(F,I,O, ul, rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIO(F,I,O, ul, rels), companyRepo.getUlFlPathByFIO(F,I,O, ul, rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(relations);
                if (!personRepo.getUlFlPathByFIO(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIO(F,I,O, ul, rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIO(F,I,O, ul, rels), companyRepo.getUlFlPathByFIO(F,I,O, ul, rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(relations);
                    if (!personRepo.getUlFlPathByFIO(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIO(F,I,O, ul, rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIO(F,I,O, ul, rels), companyRepo.getUlFlPathByFIO(F,I,O, ul, rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(relations);
                        if (!personRepo.getUlFlPathByFIO(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIO(F,I,O, ul, rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIO(F,I,O, ul, rels), companyRepo.getUlFlPathByFIO(F,I,O, ul, rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIO(F,I,O, ul, relations), companyRepo.getUlFlPathByFIO(F,I,O, ul, relations));
                        }
                    }
                }
            }
        }
    }
    public doubleReturn getUlFlPathByFIOAccurate(Long id, String F,String I,String O,String ul, List<String> relations) {
        List<String> rels = weightedRelationSearch.FirstLevelSearch(relations);
        if (!personRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels), companyRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(relations);
            if (!personRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels), companyRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(relations);
                if (!personRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels), companyRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(relations);
                    if (!personRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels), companyRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(relations);
                        if (!personRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels), companyRepo.getUlFlPathByFIOAccurate(F,I,O, ul, rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOAccurate(F,I,O, ul, relations), companyRepo.getUlFlPathByFIOAccurate(F,I,O, ul, relations));
                        }
                    }
                }
            }
        }
    }
    public doubleReturn getUlFlPathByFIOwithoutO(Long id, String F,String I,String O,String ul, List<String> relations) {
        List<String> rels = weightedRelationSearch.FirstLevelSearch(relations);
        if (!personRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels).isEmpty()) {
            return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels), companyRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels));
        } else {
            rels = weightedRelationSearch.SecondLevelSearch(relations);
            if (!personRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels).isEmpty()) {
                return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels), companyRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels));
            } else {
                rels = weightedRelationSearch.ThirdLevelSearch(relations);
                if (!personRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels).isEmpty()) {
                    return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels), companyRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels));
                } else {
                    rels = weightedRelationSearch.FourthLevelSearch(relations);
                    if (!personRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels).isEmpty()) {
                        return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels), companyRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels));
                    } else {
                        rels = weightedRelationSearch.FifthLevelSearch(relations);
                        if (!personRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels).isEmpty() || !companyRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels).isEmpty()) {
                            return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels), companyRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, rels));
                        } else {
                            return ConstructDoubleReturn(id, personRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, relations), companyRepo.getUlFlPathByFIOwithoutO(F,I,O, ul, relations));
                        }
                    }
                }
            }
        }
    }

    public doubleReturn getUlUlPath(Long id, String ul1, String ul2, List<String> relations) {
        List<String> rels = weightedRelationSearch.FirstLevelSearch(relations);
        return ConstructDoubleReturn(id, personRepo.getUlUlPath(ul1, ul2, relations), companyRepo.getUlUlPath(ul1, ul2, relations));
       // if (!personRepo.getUlUlPath(ul1, ul2, rels).isEmpty() || !companyRepo.getUlUlPath(ul1, ul2, rels).isEmpty()) {
       //     return ConstructDoubleReturn(id, personRepo.getUlUlPath(ul1, ul2, rels), companyRepo.getUlUlPath(ul1, ul2, rels));
       // } else {
       //    rels = weightedRelationSearch.SecondLevelSearch(relations);
       //     if (!personRepo.getUlUlPath(ul1, ul2, rels).isEmpty() || !companyRepo.getUlUlPath(ul1, ul2, rels).isEmpty()) {
       //         return ConstructDoubleReturn(id, personRepo.getUlUlPath(ul1, ul2, rels), companyRepo.getUlUlPath(ul1, ul2, rels));
       //     } else {
       //         rels = weightedRelationSearch.ThirdLevelSearch(relations);
       //         if (!personRepo.getUlUlPath(ul1, ul2, rels).isEmpty() || !companyRepo.getUlUlPath(ul1, ul2, rels).isEmpty()) {
       //             return ConstructDoubleReturn(id, personRepo.getUlUlPath(ul1, ul2, rels), companyRepo.getUlUlPath(ul1, ul2, rels));
       //         } else {
       //             rels = weightedRelationSearch.FourthLevelSearch(relations);
       //             if (!personRepo.getUlUlPath(ul1, ul2, rels).isEmpty() || !companyRepo.getUlUlPath(ul1, ul2, rels).isEmpty()) {
       //                return ConstructDoubleReturn(id, personRepo.getUlUlPath(ul1, ul2, rels), companyRepo.getUlUlPath(ul1, ul2, rels));
       //             } else {
       //                 rels = weightedRelationSearch.FifthLevelSearch(relations);
       //                 if (!personRepo.getUlUlPath(ul1, ul2, rels).isEmpty() || !companyRepo.getUlUlPath(ul1, ul2, rels).isEmpty()) {
       //                     return ConstructDoubleReturn(id, personRepo.getUlUlPath(ul1, ul2, rels), companyRepo.getUlUlPath(ul1, ul2, rels));
       //       }else {
       //                     return ConstructDoubleReturn(id, personRepo.getUlUlPath(ul1, ul2, relations), companyRepo.getUlUlPath(ul1, ul2, relations));
       //                 }
       //             }
       //         }
       //     }
       // }
    }

    public doubleReturn getUlTree(Long id, String ul, List<String> relations, int depth, int limit) {
        if (depth==1) {
            List<Company> listOfCompanies = companyRepo.getUlTree1(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree1(ul, limit, relations), listOfCompanies);
        } else if (depth==2) {
            List<Company> listOfCompanies = companyRepo.getUlTree2(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree2(ul, limit, relations), listOfCompanies);
        } else if (depth==3) {
            List<Company> listOfCompanies = companyRepo.getUlTree3(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree3(ul, limit, relations), listOfCompanies);
        } else if (depth==4) {
            List<Company> listOfCompanies = companyRepo.getUlTree4(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree4(ul, limit, relations), listOfCompanies);
        } else if (depth==5) {
            List<Company> listOfCompanies = companyRepo.getUlTree5(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree5(ul, limit, relations), listOfCompanies);
        } else if (depth==6) {
            List<Company> listOfCompanies = companyRepo.getUlTree6(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree6(ul, limit, relations), listOfCompanies);
        } else if (depth==7) {
            List<Company> listOfCompanies = companyRepo.getUlTree7(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree7(ul, limit, relations), listOfCompanies);
        } else if (depth==8) {
            List<Company> listOfCompanies = companyRepo.getUlTree8(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree8(ul, limit, relations), listOfCompanies);
        } else if (depth==9) {
            List<Company> listOfCompanies = companyRepo.getUlTree9(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree9(ul, limit, relations), listOfCompanies);
        } else if (depth==10) {
            List<Company> listOfCompanies = companyRepo.getUlTree10(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree10(ul, limit, relations), listOfCompanies);
        } else if (depth==11) {
            List<Company> listOfCompanies = companyRepo.getUlTree11(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree11(ul, limit, relations), listOfCompanies);
        } else if (depth==12) {
            List<Company> listOfCompanies = companyRepo.getUlTree12(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree12(ul, limit, relations), listOfCompanies);
        } else if (depth==13) {
            List<Company> listOfCompanies = companyRepo.getUlTree13(ul, limit, relations);
            List<Persons> listOfPersons = new ArrayList<>();
            if (listOfCompanies.size() == 0) {
                return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
            }
            return ConstructDoubleReturn(id, personRepo.getUlTree13(ul, limit, relations), listOfCompanies);
        }
        List<Company> listOfCompanies = companyRepo.getUlTree13(ul, limit, relations);
        List<Persons> listOfPersons = new ArrayList<>();
        if (listOfCompanies.size() == 0) {
            return ConstructDoubleReturn(id, listOfPersons, companyRepo.getOneCompany(ul));
        }
        return ConstructDoubleReturn(id, personRepo.getUlTree13(ul, limit, relations), listOfCompanies);
    }

    public doubleReturn getPersonTree(Long id, String person,int depth, int limit, List<String> relations) {
        if(depth==1) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepthOne(person, limit, relations), companyRepo.getPersonTreeDepthOne(person, limit, relations));
        }
        if(depth==2) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepthTwo(person, limit, relations), companyRepo.getPersonTreeDepthTwo(person, limit, relations));
        }
        if(depth==3) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepthThree(person, limit, relations), companyRepo.getPersonTreeDepthThree(person, limit, relations));
        }
        if(depth==4) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth4(person, limit, relations), companyRepo.getPersonTreeDepth4(person, limit, relations));
        }
        if(depth==5) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth5(person, limit, relations), companyRepo.getPersonTreeDepth5(person, limit, relations));
        }
        if(depth==6) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth6(person, limit, relations), companyRepo.getPersonTreeDepth6(person, limit, relations));
        }
        if(depth==7) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth7(person, limit, relations), companyRepo.getPersonTreeDepth7(person, limit, relations));
        }
        if(depth==8) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth8(person, limit, relations), companyRepo.getPersonTreeDepth8(person, limit, relations));
        }
        if(depth==9) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth9(person, limit, relations), companyRepo.getPersonTreeDepth9(person, limit, relations));
        }
        if(depth==10) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth10(person, limit, relations), companyRepo.getPersonTreeDepth10(person, limit, relations));
        }
        if(depth==11) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth11(person, limit, relations), companyRepo.getPersonTreeDepth11(person, limit, relations));
        }
        if(depth==12) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth12(person, limit, relations), companyRepo.getPersonTreeDepth12(person, limit, relations));
        }
        if(depth==13) {
            return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth13(person, limit, relations), companyRepo.getPersonTreeDepth13(person, limit, relations));
        }
        return ConstructDoubleReturn(id, personRepo.getPersonTreeDepth13(person, limit, relations), companyRepo.getPersonTreeDepth13(person, limit, relations));
    }
    public doubleReturn getPersonByFIO_(Long id, String F,String I,String O,int depth, int limit, List<String> relations) {
        if(depth==1) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth1(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth1(F,I,O, limit, relations));
        }
        if(depth==2) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth2(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth2(F,I,O, limit, relations));
        }
        if(depth==3) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth3(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth3(F,I,O, limit, relations));
        }
        if(depth==4) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth4(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth4(F,I,O, limit, relations));
        }
        if(depth==5) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth5(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth5(F,I,O, limit, relations));
        }
        if(depth==6) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth6(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth6(F,I,O, limit, relations));
        }
        if(depth==7) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth7(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth7(F,I,O, limit, relations));
        }
        if(depth==8) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth8(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth8(F,I,O, limit, relations));
        }
        if(depth==9) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth9(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth9(F,I,O, limit, relations));
        }
        if(depth==10) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth10(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth10(F,I,O, limit, relations));
        }
        if(depth==11) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth11(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth11(F,I,O, limit, relations));
        }
        if(depth==12) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth12(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth12(F,I,O, limit, relations));
        }
        if(depth==13) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth13(F,I,O, limit, relations));
        }
        return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth13(F,I,O, limit, relations));
    }
    public doubleReturn getPersonByFIO_Accurate(Long id, String F,String I,String O,int depth, int limit, List<String> relations) {
        if(depth==1) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth1Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth1Accurate(F,I,O, limit, relations));
        }
        if(depth==2) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth2Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth2Accurate(F,I,O, limit, relations));
        }
        if(depth==3) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth3Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth3Accurate(F,I,O, limit, relations));
        }
        if(depth==4) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth4Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth4Accurate(F,I,O, limit, relations));
        }
        if(depth==5) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth5Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth5Accurate(F,I,O, limit, relations));
        }
        if(depth==6) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth6Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth6Accurate(F,I,O, limit, relations));
        }
        if(depth==7) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth7Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth7Accurate(F,I,O, limit, relations));
        }
        if(depth==8) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth8Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth8Accurate(F,I,O, limit, relations));
        }
        if(depth==9) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth9Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth9Accurate(F,I,O, limit, relations));
        }
        if(depth==10) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth10Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth10Accurate(F,I,O, limit, relations));
        }
        if(depth==11) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth11Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth11Accurate(F,I,O, limit, relations));
        }
        if(depth==12) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth12Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth12Accurate(F,I,O, limit, relations));
        }
        if(depth==13) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth13Accurate(F,I,O, limit, relations));
        }
        return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13Accurate(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth13Accurate(F,I,O, limit, relations));
    }
    public doubleReturn getPersonByFIO_withoutO(Long id, String F,String I,String O,int depth, int limit, List<String> relations) {
        if(depth==1) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth1withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth1withoutO(F,I,O, limit, relations));
        }
        if(depth==2) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth2withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth2withoutO(F,I,O, limit, relations));
        }
        if(depth==3) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth3withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth3withoutO(F,I,O, limit, relations));
        }
        if(depth==4) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth4withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth4withoutO(F,I,O, limit, relations));
        }
        if(depth==5) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth5withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth5withoutO(F,I,O, limit, relations));
        }
        if(depth==6) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth6withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth6withoutO(F,I,O, limit, relations));
        }
        if(depth==7) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth7withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth7withoutO(F,I,O, limit, relations));
        }
        if(depth==8) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth8withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth8withoutO(F,I,O, limit, relations));
        }
        if(depth==9) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth9withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth9withoutO(F,I,O, limit, relations));
        }
        if(depth==10) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth10withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth10withoutO(F,I,O, limit, relations));
        }
        if(depth==11) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth11withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth11withoutO(F,I,O, limit, relations));
        }
        if(depth==12) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth12withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth12withoutO(F,I,O, limit, relations));
        }
        if(depth==13) {
            return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth13withoutO(F,I,O, limit, relations));
        }
        return ConstructDoubleReturn(id, personRepo.getPersonByFIO_Depth13withoutO(F,I,O, limit, relations), companyRepo.getPersonByFIO_Depth13withoutO(F,I,O, limit, relations));
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
    private Nodes setRelaCount(Nodes node) {
        Long rel = personRepo.countRels(node.getId());
        node.setRelCount(rel);
        return node;
    }
    private doubleReturn CountAll(doubleReturn list) {
        List<Nodes> newlist = new ArrayList<>();
        for (Nodes bode: list.getNodes()) {
            try {
                Nodes newe = setRelaCount(bode);
                newlist.add(newe);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        list.setNodes(newlist);
        return list;
    }
    private doubleReturn ConstructDoubleReturn(Long User, List<Persons> personsList, List<Company> companies) {
        List<Nodes> nodes = new ArrayList<>();
        List<relationModel> edges = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        for (Company object: companies) {
            if (!ids.contains(object.getId())) {
                ids.add(object.getId());
                Nodes currNode = new Nodes();
                Map<String, Object> properties = getPropertyMap(User, object);
                currNode.setId(object.getId());
                currNode.setProperties(properties);
                nodes.add(currNode);
            }
            List<OPG> OPG = object.getOpgs();
            for (OPG relation: OPG) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("OPG");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<esf_1000kk_10kkk> esf_1000kk_10kkk = object.getEsf_1000kk_10kkk();
            for (esf_1000kk_10kkk relation: esf_1000kk_10kkk) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("esf_1000kk_10kkk");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<esf_100kk_1000kk> esf_100kk_1000kk = object.getEsf_100kk_1000kk();
            for (esf_100kk_1000kk relation: esf_100kk_1000kk) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("esf_100kk_1000kk");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<esf_10kk_100kk> esf_10kk_100kk = object.getEsf_10kk_100kk();
            for (esf_10kk_100kk relation: esf_10kk_100kk) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("esf_10kk_100kk");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<esf_10kkk_bolshe> esf_10kkk_bolshe = object.getEsf_10kkk_bolshe();
            for (esf_10kkk_bolshe relation: esf_10kkk_bolshe) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("esf_10kkk_bolshe");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }

            List<GOSZAKUP> GOSZAKUP = object.getGoszakups();
            for (GOSZAKUP relation: GOSZAKUP) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("GOSZAKUP");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<FOUNDER_HIST> FOUNDER_HIST = object.getFounderHists();
            for (FOUNDER_HIST relation: FOUNDER_HIST) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(relation.getCompany().getId(),object.getId(), properties);
                currRel.setType("FOUNDER_HIST");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<FOUNDER_CUR> FOUNDER_CUR = object.getFounderCurs();
            for (FOUNDER_CUR relation: FOUNDER_CUR) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(relation.getCompany().getId(), object.getId(), properties);
                currRel.setType("FOUNDER_CUR");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<DIRECTOR_CUR> DIRECTOR_CUR = object.getDirectorCurs();
            for (DIRECTOR_CUR relation: DIRECTOR_CUR) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("DIRECTOR_CUR");
                System.out.println("FIRECTOR");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPersons().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<DIRECTOR_HIST> DIRECTOR_HIST = object.getDirectorHists();
            for (DIRECTOR_HIST relation: DIRECTOR_HIST) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("DIRECTOR_HIST");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPersons().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<WORKER_HIST> WORKER_HIST = object.getWorkerHists();
            for (WORKER_HIST relation: WORKER_HIST) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("WORKER_HIST");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPerson().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<WORKER_CUR> WORKER_CURs = object.getWorkerCurs();
            for (WORKER_CUR relation: WORKER_CURs) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("WORKER_CUR");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPerson().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<dolzhniki_po_sudispol> dolzhnik_po_sudispol = object.getDolzhnik_po_sudispol();
            for (dolzhniki_po_sudispol relation: dolzhnik_po_sudispol) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("dolzhnik_po_sudispol");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPerson().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<WORKER_CUR> WORKER_CUR = object.getWorkerCurs();
            for (WORKER_CUR relation: WORKER_CUR) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("WORKER_CUR");
                System.out.println("WORKER CUR UL founded");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPerson().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }

            List<SUDIM> SUDIM = object.getSudims();
            for (SUDIM relation: SUDIM) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("SUDIM");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPerson().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<UCHILSYA> UCHILSYA = object.getUchilsyas();
            for (UCHILSYA relation: UCHILSYA) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("UCHILSYA");
                System.out.println("UCHILSUAS EXISTS");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPerson().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<REG_ADDRESS_CUR> REG_ADDRESS_CURs = object.getREG_ADDRESS_CUR();
            for (REG_ADDRESS_CUR relation: REG_ADDRESS_CURs) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getAddress().getId(), properties);
                currRel.setType("REG_ADDRESS_CUR");
                edges.add(currRel);
                if (!ids.contains(relation.getAddress().getId())) {
                    ids.add(relation.getAddress().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(relation.getAddress());
                    currNode.setId(relation.getAddress().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<REG_ADDRESS_HIST> REG_ADDESS_UL = object.getREG_ADDRESS_HIST();
            for (REG_ADDRESS_HIST relation: REG_ADDESS_UL) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getAddress().getId(), properties);
                currRel.setType("REG_ADDRESS_HIST");
                edges.add(currRel);
                if (!ids.contains(relation.getAddress().getId())) {
                    ids.add(relation.getAddress().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(relation.getAddress());
                    currNode.setId(relation.getAddress().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<PDL> PDL = object.getPdls();
            for (PDL relation: PDL) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("PDL");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<DETDOM_HIST> detdomHistList = object.getDetdomHistList();
            for (DETDOM_HIST relation: detdomHistList) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("DETDOM_HIST");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<BUHGALTER> buhgalters = object.getBuhgalters();
            for (BUHGALTER relation: buhgalters) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("BUHGALTER");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<DFO_AFF_UL> DFO_AFF_UL = object.getDfoAffUls();
            for (DFO_AFF_UL relation: DFO_AFF_UL) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("DFO_AFF_UL");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<PREDPOL_BENEFICIAR> predpol_beneficiar = object.getPredpol_beneficiar();
            for (PREDPOL_BENEFICIAR relation: predpol_beneficiar) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("predpol_beneficiar");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<INS_POLICY> INS_POLICY = object.getINS_POLICY();
            for (INS_POLICY relation: INS_POLICY) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(relation.getPerson().getId(), object.getId(), properties);
                currRel.setType("INS_POLICY");
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
            List<IP_KX> IP_KX = object.getIpKxes();
            for (IP_KX relation: IP_KX) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("IP_KX");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPerson().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<DFO_AFF_FIZ> dfoAffFizs = object.getDFO_AFF_FIZ();
            for (DFO_AFF_FIZ relation: dfoAffFizs) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("DFO_AFF_FIZ");
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
        for (Persons object: personsList) {
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
            List<BERKUT> BERKUT = object.getBERKUT();
            for (BERKUT relation: BERKUT) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("BERKUT");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(object.getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<FOUNDER_CUR> FOUNDER_CUR = object.getFounderCurs();
            for (FOUNDER_CUR relation: FOUNDER_CUR) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(relation.getCompany().getId(), object.getId(), properties);
                currRel.setType("FOUNDER_CUR");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<INS_POLICY> INS_POLICY = object.getINS_POLICY();
            for (INS_POLICY relation: INS_POLICY) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(relation.getPerson().getId(), object.getId(), properties);
                currRel.setType("INS_POLICY");
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
            List<BLIZKIE_RODS> BLIZKIE_RODS = object.getBlizkieRods();
            for (BLIZKIE_RODS relation: BLIZKIE_RODS) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("BLIZKIE_RODS");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(object.getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<SIBLING> SIBLING = object.getSiblings();
            for (SIBLING relation: SIBLING) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPersons().getId(), properties);
                currRel.setType("SIBLING");
                edges.add(currRel);
                if (!ids.contains(relation.getPersons().getId())) {
                    ids.add(relation.getPersons().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPersons());
                    currNode = tryAddPhoto(currNode, relation.getPersons().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(object.getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPersons().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<FOUNDER_HIST> FOUNDER_HIST = object.getFounderHists();
            for (FOUNDER_HIST relation: FOUNDER_HIST) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(relation.getCompany().getId(),object.getId(), properties);
                currRel.setType("FOUNDER_HIST");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<REG_ADDRESS_CUR> regAddressCurs = object.getRegAddressCurs();
            for (REG_ADDRESS_CUR relation: regAddressCurs) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getAddress().getId(), properties);
                currRel.setType("REG_ADDRESS_CUR");
                edges.add(currRel);
                if (!ids.contains(relation.getAddress().getId())) {
                    ids.add(relation.getAddress().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(relation.getAddress());
                    currNode.setId(relation.getAddress().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<REG_ADDRESS_HIST> REG_ADDRESS_HIST = object.getRegAddressHists();
            for (REG_ADDRESS_HIST relation: REG_ADDRESS_HIST) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getAddress().getId(), properties);
                currRel.setType("REG_ADDRESS_HIST");
                edges.add(currRel);
                if (!ids.contains(relation.getAddress().getId())) {
                    ids.add(relation.getAddress().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(relation.getAddress());
                    currNode.setId(relation.getAddress().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<REG_ADDRESS_CUR> REG_ADDRESS_CURs = object.getREG_ADDRESS_CUR();
            for (REG_ADDRESS_CUR relation: REG_ADDRESS_CURs) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getAddress().getId(), properties);
                currRel.setType("REG_ADDRESS_CUR");
                edges.add(currRel);
                if (!ids.contains(relation.getAddress().getId())) {
                    ids.add(relation.getAddress().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(relation.getAddress());
                    currNode.setId(relation.getAddress().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<REG_ADDRESS_HIST> REG_ADDESS_UL = object.getREG_ADDRESS_HIST();
            for (REG_ADDRESS_HIST relation: REG_ADDESS_UL) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getAddress().getId(), properties);
                currRel.setType("REG_ADDRESS_HIST");
                edges.add(currRel);
                if (!ids.contains(relation.getAddress().getId())) {
                    ids.add(relation.getAddress().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(relation.getAddress());
                    currNode.setId(relation.getAddress().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<COUSIN> COUSIN = object.getCousins();
            for (COUSIN relation: COUSIN) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPersons().getId(), properties);
                currRel.setType("COUSIN");
                edges.add(currRel);
                if (!ids.contains(relation.getPersons().getId())) {
                    ids.add(relation.getPersons().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPersons());
                    currNode = tryAddPhoto(currNode, relation.getPersons().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(object.getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPersons().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<BUHGALTER> buhgalters = object.getBuhgalters();
            for (BUHGALTER relation: buhgalters) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("BUHGALTER");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<DETDOM_HIST> detdomHistList = object.getDetdomHistList();
            for (DETDOM_HIST relation: detdomHistList) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("DETDOM_HIST");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<DFO_AFF_FIZ> dfoAffFizs = object.getDfoAffFizs();
            for (DFO_AFF_FIZ relation: dfoAffFizs) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("DFO_AFF_FIZ");
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
            List<ZAGS> zags = object.getZags();
            for (ZAGS relation: zags) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("ZAGS");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(object.getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<SLUZHIL> SLUZHIL = object.getSluzhils();
            for (SLUZHIL relation: SLUZHIL) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("SLUZHIL");
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
            List<SUDIM> SUDIM = object.getSudims();
            for (SUDIM relation: SUDIM) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("SUDIM");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(object.getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<PDL> PDL = object.getPdls();
            for (PDL relation: PDL) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("PDL");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<OPG> OPG = object.getOpgs();
            for (OPG relation: OPG) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("OPG");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<WORKER_HIST> WORKER_HIST = object.getWorkerHists();
            for (WORKER_HIST relation: WORKER_HIST) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("WORKER_HIST");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPerson().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<WORKER_CUR> WORKER_CURs = object.getWorkerCurs();
            for (WORKER_CUR relation: WORKER_CURs) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("WORKER_CUR");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPerson().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<IP_KX> IP_KX = object.getIP_KX();
            for (IP_KX relation: IP_KX) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("IP_KX");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPerson().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<dolzhniki_po_sudispol> dolzhniki_po_sudispol = object.getDolzhniki_po_sudispol();
            for (dolzhniki_po_sudispol relation: dolzhniki_po_sudispol) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("dolzhniki_po_sudispol");
                edges.add(currRel);
                if (!ids.contains(relation.getPerson().getId())) {
                    ids.add(relation.getPerson().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPerson());
                    currNode = tryAddPhoto(currNode, relation.getPerson().getIIN());
//                    photoDb photoDb = newPhotoService.getPhotoByIIN(relation.getPerson().getIIN());
//                    currNode.setPhotoDbf(photoDb);
                    currNode.setId(relation.getPerson().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<PHONE> PHONE = object.getPhones();
            for (PHONE relation: PHONE) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(relation.getPersons().getId(), object.getId(), properties);
                currRel.setType("PHONE");
                edges.add(currRel);
                if (!ids.contains(relation.getPersons().getId())) {
                    ids.add(relation.getPersons().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getPersons());
                    currNode.setId(relation.getPersons().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<PREDPOL_BENEFICIAR> PREDPOL_BENEFICIAR = object.getPREDPOL_BENEFICIAR();
            for (PREDPOL_BENEFICIAR relation: PREDPOL_BENEFICIAR) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getCompany().getId(), properties);
                currRel.setType("PREDPOL_BENEFICIAR");
                edges.add(currRel);
                if (!ids.contains(relation.getCompany().getId())) {
                    ids.add(relation.getCompany().getId());
                    Nodes currNode = new Nodes();
                    Map<String, Object> properties2 = getPropertyMap(User, relation.getCompany());
                    currNode.setId(relation.getCompany().getId());
                    currNode.setProperties(properties2);
                    nodes.add(currNode);
                }
            }
            List<operVipisk> operVipisk = object.getOperVipisk();
            for (operVipisk relation: operVipisk) {
                Map<String, Object> properties = getPropertyMap(relation);
                relationModel currRel = new relationModel(object.getId(), relation.getPerson().getId(), properties);
                currRel.setType("operVipisk");
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
        for (Long id: ids) {
            System.out.println(id);
        }
        doubleReturn newe = CountAll(doubleReturn);
        return newe;
    }


}
