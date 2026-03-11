package com.example.new_project_challenge_15.service;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Service
public class WeightedRelationSearch {
    private List<String> FirstLevelRels = new ArrayList<>(Arrays.asList("ZAGS", "BLIZKIE_RODS", "OPG", "REG_ADDRESS_CUR"));
    private List<String> SecondLevelRels = new ArrayList<>(Arrays.asList("COUSIN", "DIRECTOR_CUR", "FOUNDER_CUR", "SIBLING", "WORKER_CUR"));
    private List<String> ThirdLevelRels = new ArrayList<>(Arrays.asList("BERKUT", "BUHGALTER", "DIRECTOR_HIST", "FOUNDER_HIST", "REG_ADDRESS_HIST", "SLIZHIL", "PHONE", "Phone"));
    private List<String> FourthLevelRels = new ArrayList<>(Arrays.asList("UCHILSYA", "INS_POLICY", "GOSZAKUP", "PDL", "WORKER_HIST"));
    private List<String> FifthLevelRels = new ArrayList<>(Arrays.asList("DETDOM_HIST", "DFO_AFF_FIZ", "DFO_AFF_UL", "dolzhniki_po_sudispol", "IP-KX", "PREDPOL_BENEFICIAR", "REGISTER_BENEFICIAR", "SUDIM", "esf_1000kk_10kkk", "esf_100kk_1000kk.csv", "esf_10kkk_bolshe", "esf_10kk_100kk", "SAYS", "operation", "testRel", "WORK_200_05"));

    public List<String> FirstLevelSearch(List<String> rels) {
        List<String> intersection = new ArrayList<>();
        for (String s: rels) {
            if (FirstLevelRels.contains(s)) {
                intersection.add(s);
            }
        }
        return intersection;
    }

    public List<String> SecondLevelSearch(List<String> rels) {
        List<String> finalList = FirstLevelRels;
        finalList.addAll(SecondLevelRels);
        List<String> intersection = new ArrayList<>();
        for (String s: rels) {
            if (finalList.contains(s)) {
                intersection.add(s);
            }
        }
        return intersection;
    }
    public List<String> ThirdLevelSearch(List<String> rels) {
        List<String> finalList = FirstLevelRels;
        finalList.addAll(SecondLevelRels);
        finalList.addAll(ThirdLevelRels);
        List<String> intersection = new ArrayList<>();
        for (String s: rels) {
            if (finalList.contains(s)) {
                intersection.add(s);
            }
        }
        return intersection;

    }
    public List<String> FourthLevelSearch(List<String> rels) {
        List<String> finalList = FirstLevelRels;

        finalList.addAll(SecondLevelRels);
        finalList.addAll(ThirdLevelRels);
        finalList.addAll(FourthLevelRels);
        List<String> intersection = new ArrayList<>();
        for (String s: rels) {
            if (finalList.contains(s)) {
                intersection.add(s);
            }
        }
        return intersection;
    }
    public List<String> FifthLevelSearch(List<String> rels) {
        List<String> finalList = FirstLevelRels;

        finalList.addAll(SecondLevelRels);
        finalList.addAll(ThirdLevelRels);
        finalList.addAll(FourthLevelRels);
        finalList.addAll(FifthLevelRels);
        List<String> intersection = new ArrayList<>();
        for (String s: rels) {
            if (finalList.contains(s)) {
                intersection.add(s);
            }
        }
        return intersection;
    }
}
