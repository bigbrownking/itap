package com.example.new_project_challenge_15.service;

import com.example.new_project_challenge_15.models.User;
import com.example.new_project_challenge_15.models.log;
import com.example.new_project_challenge_15.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class StoreLogs {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    FiPersonsService personsService;
    @Autowired
    LogsService logsService;

    private String placeApprovementText(String orderNum, String approvement_type,
                                        String caseNum,
                                        String orderDate,
                                        String articleName,
                                        String checkingName,
                                        String otherReasons,
                                        String organName,
                                        String sphereName,
                                        String tematikName,
                                        String rukName) {
        List<String> approvements = new ArrayList<>();
        String approvement_data = "";
        if (approvement_type != null && !approvement_type.equals("")) {
            approvements.add("ОСНОВАНИЕ ПРОВЕРКИ: " + approvement_type);
        }
        if (orderNum != null && !orderNum.equals("")) {
            approvements.add("НОМЕР ПРИКАЗА:" + orderNum);
        }
        if (caseNum != null && !caseNum.equals("")) {
            approvements.add("НОМЕР ДЕЛА: " + caseNum);
        }
        if (orderDate != null && !orderDate.equals("")) {
            approvements.add("ДАТА ПРИКАЗА: " + orderDate);
        }
        if (articleName != null && !articleName.equals("")) {
            approvements.add("НАЗВАНИЕ СТАТЬИ: " + articleName);
        }
        if (checkingName != null && !checkingName.equals("")) {
            approvements.add("ИМЯ ПРОВЕРЯЮЩЕГО: " + checkingName);
        }
        if (otherReasons != null && !otherReasons.equals("")) {
            approvements.add("ДРУГАЯ ПРИЧИНА: " + otherReasons);
        }
        if (organName != null && !organName.equals("")) {
            approvements.add("НАЗВАНИЕ ОРГАНА: " + organName);
        }
        if (sphereName != null && !sphereName.equals("")) {
            approvements.add("НАЗВАНИЕ СФЕРЫ: " + sphereName);
        }
        if (tematikName != null && !tematikName.equals("")) {
            approvements.add("НАЗВАНИЕ ТЕМАТИКИ: " + tematikName);
        }
        if (rukName != null && !rukName.equals("")) {
            approvements.add("ИМЯ РУКОВОДИТЕЛЯ: " + rukName);
        }
        for (String reason: approvements) {
            approvement_data = approvement_data + reason + ", ";
        }
        return approvement_data;
    }

    public boolean store(User user, String person, List<String> relations, int depth, int limit,
                               String orderNum, String approvement_type,
                               String caseNum,
                               String orderDate,
                               String articleName,
                               String checkingName,
                               String otherReasons,
                               String organName,
                               String sphereName,
                               String tematikName,
                               String rukName) {
        try {
            List<String> request_bodies = new ArrayList<>();
            log log = new log();
            String obwii = "Раскрыть связь ФЛ: " + person+ ", лимит: " + limit + ", уровень: " + depth;
            log.setObwii(obwii);
            String approvement_data = placeApprovementText(orderNum, approvement_type,
                    caseNum,
                    orderDate,
                    articleName,
                    checkingName,
                    otherReasons,
                    organName,
                    sphereName,
                    tematikName,
                    rukName);
            log.setApprovement_data(approvement_data);
            LocalDateTime current = LocalDateTime.now();
            log.setUsername(user.getUsername());
            log.setDate(current);
            log.setLimit_(limit);
            log.setDepth_(depth);
            log.setRequest_body(request_bodies);
            log.setRequest_rels(relations);
            logsService.SaveLog(log);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean store(User user,  String lastName1, String firstName1, String fatherName1 , List<String> relations, int depth, int limit,
                         String orderNum, String approvement_type,
                         String caseNum,
                         String orderDate,
                         String articleName,
                         String checkingName,
                         String otherReasons,
                         String organName,
                         String sphereName,
                         String tematikName,
                         String rukName) {
        try {
            List<String> request_bodies = new ArrayList<>();
            request_bodies.add(lastName1 + " " + firstName1 + " " + fatherName1);
            log log = new log();
            String obwii = "Раскрыть связь ФЛ: " + lastName1 + " " + firstName1 + " " + fatherName1 + ", лимит: " + limit + ", уровень: " + depth;

            log.setObwii(obwii);
            String approvement_data = placeApprovementText(orderNum, approvement_type,
                    caseNum,
                    orderDate,
                    articleName,
                    checkingName,
                    otherReasons,
                    organName,
                    sphereName,
                    tematikName,
                    rukName);
            log.setApprovement_data(approvement_data);
            LocalDateTime current = LocalDateTime.now();
            log.setUsername(user.getUsername());
            log.setDate(current);
            log.setLimit_(limit);
            log.setDepth_(depth);
            log.setRequest_body(request_bodies);
            log.setRequest_rels(relations);
            logsService.SaveLog(log);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean store(User user,  String person, String person2 , List<String> relations,
                         String orderNum, String approvement_type,
                         String caseNum,
                         String orderDate,
                         String articleName,
                         String checkingName,
                         String otherReasons,
                         String organName,
                         String sphereName,
                         String tematikName,
                         String rukName) {
        try {
            List<String> request_bodies = new ArrayList<>();
            request_bodies.add(person);
            request_bodies.add(person2);
            log log = new log();
            String obwii = "Ракрыть путь ФЛ-ФЛ: " + person+ " и " + person2;
            log.setObwii(obwii);
            String approvement_data = placeApprovementText(orderNum, approvement_type,
                    caseNum,
                    orderDate,
                    articleName,
                    checkingName,
                    otherReasons,
                    organName,
                    sphereName,
                    tematikName,
                    rukName);
            log.setApprovement_data(approvement_data);
            LocalDateTime current = LocalDateTime.now();
            log.setUsername(user.getUsername());
            log.setDate(current);
            log.setRequest_body(request_bodies);
            log.setRequest_rels(relations);
            logsService.SaveLog(log);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean store( String lastName1, String firstName1, String fatherName1, String lastName2, String firstName2, String fatherName2, List<String> relations, String orderNum,
                          String caseNum, String approvement_type,
                          String orderDate,
                          String articleName,
                          String checkingName,
                          String otherReasons,
                          String organName,
                          String sphereName,
                          String tematikName,
                          String rukName,User user,
                          boolean check1,
                          boolean check2) {
        try {
            List<String> request_bodies = new ArrayList<>();
            request_bodies.add("Фамилия: " + lastName1 + ", имя: " + firstName1 + ", отчество: " + fatherName1);
            request_bodies.add("Фамилия: " + lastName2 + ", имя: " + firstName2 + ", отчество: " + fatherName2);
            log log = new log();
            String obwii = "Ракрыть путь ФЛ-ФЛ: " + request_bodies.get(0) + ", " + request_bodies.get(1);
            log.setObwii(obwii);
            String approvement_data = placeApprovementText(orderNum, approvement_type,
                    caseNum,
                    orderDate,
                    articleName,
                    checkingName,
                    otherReasons,
                    organName,
                    sphereName,
                    tematikName,
                    rukName);
            log.setApprovement_data(approvement_data);
            LocalDateTime current = LocalDateTime.now();
            log.setUsername(user.getUsername());
            log.setDate(current);
            log.setRequest_body(request_bodies);
            log.setRequest_rels(relations);
            logsService.SaveLog(log);
            System.out.println(check1);
            System.out.println(check2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean storeUl(String ul, List<String> relations, int depth, int limit, String orderNum,
                           String caseNum, String approvement_type,
                           String orderDate,
                           String articleName,
                           String checkingName,
                           String otherReasons,
                           String organName,
                           String sphereName,
                           String tematikName,
                           String rukName,User user) {
        try {
            List<String> request_bodies = new ArrayList<>();
            request_bodies.add(ul);
            log log = new log();
            String obwii = "Раскрыть связь ЮЛ: " + ul + ", лимит: " + limit + ", уровень: " + depth;
            log.setObwii(obwii);
            String approvement_data = placeApprovementText(orderNum, approvement_type,
                    caseNum,
                    orderDate,
                    articleName,
                    checkingName,
                    otherReasons,
                    organName,
                    sphereName,
                    tematikName,
                    rukName);
            log.setApprovement_data(approvement_data);
            LocalDateTime current = LocalDateTime.now();
            log.setUsername(user.getUsername());
            log.setDate(current);
            log.setLimit_(limit);
            log.setDepth_(depth);
            log.setRequest_body(request_bodies);
            log.setRequest_rels(relations);
            logsService.SaveLog(log);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean storeFlUl(String ul, String person,  List<String> relations, String orderNum,
                             String caseNum, String approvement_type,
                             String orderDate,
                             String articleName,
                             String checkingName,
                             String otherReasons,
                             String organName,
                             String sphereName,
                             String tematikName,
                             String rukName,User user) {
        try {
            List<String> request_bodies = new ArrayList<>();
            request_bodies.add("ФЛ: " + person);
            request_bodies.add("ЮЛ: " + ul);
            log log = new log();
            String obwii = "Ракрыть путь ФЛ-ЮЛ: " + request_bodies.get(0) + ", " + request_bodies.get(1);
            log.setObwii(obwii);
            String approvement_data = placeApprovementText(orderNum, approvement_type,
                    caseNum,
                    orderDate,
                    articleName,
                    checkingName,
                    otherReasons,
                    organName,
                    sphereName,
                    tematikName,
                    rukName);
            log.setApprovement_data(approvement_data);
            LocalDateTime current = LocalDateTime.now();
            log.setUsername(user.getUsername());
            log.setDate(current);
            log.setRequest_body(request_bodies);
            log.setRequest_rels(relations);
            logsService.SaveLog(log);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean storeFlUl( String ul,  String lastName1, String firstName1, String fatherName1, List<String> relations, String orderNum,
                             String caseNum, String approvement_type,
                             String orderDate,
                             String articleName,
                             String checkingName,
                             String otherReasons,
                             String organName,
                             String sphereName,
                             String tematikName,
                             String rukName,User user,
                             boolean check1) {
        try {
            List<String> request_bodies = new ArrayList<>();
            request_bodies.add("Фамилия: " + lastName1 + ", имя: " + firstName1 + ", отчество: " + fatherName1);
            request_bodies.add("ЮЛ: " + ul);
            log log = new log();
            String obwii = "Ракрыть путь ФЛ-ЮЛ: " + request_bodies.get(0) + ", " + request_bodies.get(1);
            log.setObwii(obwii);
            String approvement_data = placeApprovementText(orderNum, approvement_type,
                    caseNum,
                    orderDate,
                    articleName,
                    checkingName,
                    otherReasons,
                    organName,
                    sphereName,
                    tematikName,
                    rukName);
            log.setApprovement_data(approvement_data);
            LocalDateTime current = LocalDateTime.now();
            log.setUsername(user.getUsername());
            log.setDate(current);
            log.setRequest_body(request_bodies);
            log.setRequest_rels(relations);
            logsService.SaveLog(log);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean storeUlUl(String ul1, String ul2, List<String> relations,
                             String approvement_type,
                             String orderNum,
                             String caseNum,
                             String orderDate,
                             String articleName,
                             String checkingName,
                             String otherReasons,
                             String organName,
                             String sphereName,
                             String tematikName,
                             String rukName, User user) {
        try {
            List<String> request_bodies = new ArrayList<>();
            request_bodies.add(ul1);
            request_bodies.add(ul2);
            log log = new log();
            String obwii = "Ракрыть путь ЮЛ-ЮЛ: " + request_bodies.get(0) + ", " + request_bodies.get(1);
            log.setObwii(obwii);
            String approvement_data = placeApprovementText(orderNum, approvement_type,
                    caseNum,
                    orderDate,
                    articleName,
                    checkingName,
                    otherReasons,
                    organName,
                    sphereName,
                    tematikName,
                    rukName);
            log.setApprovement_data(approvement_data);
            LocalDateTime current = LocalDateTime.now();
            log.setUsername(user.getUsername());
            log.setDate(current);
            log.setRequest_body(request_bodies);
            log.setRequest_rels(relations);
            logsService.SaveLog(log);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Storing auth Log
    public boolean storeAuth(String username,String action) {
        try {
            LocalDateTime current = LocalDateTime.now();
            log log = new log();

            List<String> request_bodies = new ArrayList<>();
            List<String> relations = new ArrayList<>();
            String approvement_data = "";
            String obwii="";

            switch (action) {
                case "login":
                    obwii = "Вход в аккаунт пользователя " + username;
                    break;
                case "signup":
                    obwii = "Регистрация нового пользователя " + username;
                    break;
                case "changepassword":
                    obwii = "Пользователь " + username + " поменял свой пароль";
                    break;
            }

            log.setApprovement_data(approvement_data);
            log.setDate(current);
            log.setObwii(obwii);
            log.setRequest_body(request_bodies);
            log.setRequest_rels(relations);
            log.setUsername(username);
            logsService.SaveLog(log);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
