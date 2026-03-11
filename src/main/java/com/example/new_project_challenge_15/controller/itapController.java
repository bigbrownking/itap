package com.example.new_project_challenge_15.controller;


import com.example.new_project_challenge_15.entity.DTO.doubleReturn;
import com.example.new_project_challenge_15.entity.DTO.statisticModel;
import com.example.new_project_challenge_15.models.User;
import com.example.new_project_challenge_15.models.log;
import com.example.new_project_challenge_15.models.user_roles;
import com.example.new_project_challenge_15.repository.*;
import com.example.new_project_challenge_15.security.services.UserDetailsServiceImpl;
import com.example.new_project_challenge_15.service.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
//import org.neo4j.springframework.data.core.Neo4jTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.reactive.function.client.WebClient;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/finpol/main")
public class itapController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    statisticService statisticService;
    @Autowired
    LogRepo logRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository rRepo;
    @Autowired
    PersonRepo personRepo;
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    FiPersonsService personsService;
    @Autowired
    LogsService logsService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRolesRepo userRolesRepo;
    @Autowired
    StoreLogs storeLogs;

    @GetMapping("/3users1")
    public List<User> get3users(){
        return statisticService.get3users1();
    }
    @GetMapping("/3users2")
    public List<User> get3users2(){
        return statisticService.get3users2();
    }

    @GetMapping("/logtest")
    public List<log> logTest(){
        List<log> logi = logRepo.findByUsername("derzeet@gmail.com");
        for(log log : logi){
            System.out.println(log.getApprovement_data());
            log.setUsername("logtest");
        }
        return logi;
    }

    @GetMapping("/general")
    public Map<String, Object> getAdminStat() {
        int todayRequests = logRepo.findNumberOfRequestsThatRequestedToday();
        int userNumber = userRepository.getUserNum();
        int allLogsNum = logRepo.Number();
        Map<String, Object> stat = new HashMap<>();
        stat.put("todayRequests", todayRequests);
        stat.put("userNumber", userNumber);
        stat.put("allLogsNum", allLogsNum);
        return stat;
    }

    @GetMapping("/admin/users")
    public List<User> getUsersSearch(@RequestParam String value) {
        return userRepository.getUsersByLike(value);
    }
    @GetMapping("/changeUserRole")
//    @PreAuthorize("hasRole('ADMIN')")
    public String getChangeUserRole(@RequestParam String user, @RequestParam String role) {
        Long u = Long.valueOf(user);
        Long r = Long.valueOf(role);
        try {
            user_roles userRoles = userRolesRepo.getById(u);
            userRolesRepo.delete(userRoles);
            userRoles.setUser_id(u);
            userRoles.setRole_id(r);
            userRolesRepo.save(userRoles);
            return "Роль успешно изменена";
        } catch (Exception e){
            user_roles userRoles = new user_roles();
            userRoles.setUser_id(u);
            userRoles.setRole_id(r);
            userRolesRepo.save(userRoles);
            return "Роль успешно изменена";
        } 
    }


    @GetMapping("/getusers")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/getLogsImp")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<log> getLogImp() {
        return logRepo.findImp();
    }
    @GetMapping("/getUserInfo")
//    @PreAuthorize("hasRole('ADMIN')")
    public User getUserInfo(Principal principal) {
        User user = userDetailsService.loadUserByUsernamek(principal);
        return user;
    }
    @GetMapping("/getuserdetails")
//    @PreAuthorize("hasRole('ADMIN')")
    public statisticModel getUserDetails(@RequestParam String username) {
        return statisticService.getByUsername(username);
    }
    @GetMapping("/getCurrUserDetails")
    public statisticModel getUserDetails(Principal principal) {
      User user = userDetailsService.loadUserByUsernamek(principal);
      return statisticService.getByUsername(user.getUsername());
    }



    @GetMapping("/fltree")
    public doubleReturn getFlTree(Principal principal, @RequestParam String person, @RequestParam List<String> relations, @RequestParam int depth, @RequestParam int limit,
                                  @RequestParam(required = false) String orderNum, @RequestParam(required = false) String approvement_type,
                                  @RequestParam(required = false) String caseNum,
                                  @RequestParam(required = false) String orderDate,
                                  @RequestParam(required = false) String articleName,
                                  @RequestParam(required = false) String checkingName,
                                  @RequestParam(required = false) String otherReasons,
                                  @RequestParam(required = false) String organName,
                                  @RequestParam(required = false) String sphereName,
                                  @RequestParam(required = false) String tematikName,
                                  @RequestParam(required = false) String rukName) throws JsonProcessingException {
        User user = userDetailsService.loadUserByUsernamek(principal);
        relations = personsService.filterRelations(user.getId(), relations);

        if (storeLogs.store(user, person, relations, depth, limit, orderNum, approvement_type,
                caseNum,
                orderDate,
                articleName,
                checkingName,
                otherReasons,
                organName,
                sphereName,
                tematikName,
                rukName)) {
            return personsService.getPersonTree(user.getId(), person, depth, limit, relations);
        } else {
            return null;
        }
    }
    @GetMapping("/flFIOtree")
    public doubleReturn getFlTree(@RequestParam String lastName1,@RequestParam String firstName1,@RequestParam String fatherName1, @RequestParam List<String> relations, @RequestParam int depth, @RequestParam int limit, @RequestParam(required = false) String orderNum,
                                  @RequestParam(required = false) String caseNum, @RequestParam(required = false) String approvement_type,
                                  @RequestParam(required = false) String orderDate,
                                  @RequestParam(required = false) String articleName,
                                  @RequestParam(required = false) String checkingName,
                                  @RequestParam(required = false) String otherReasons,
                                  @RequestParam(required = false) String organName,
                                  @RequestParam(required = false) String sphereName,
                                  @RequestParam(required = false) String tematikName,
                                  @RequestParam(required = false) String rukName,Principal principal,
                                  @RequestParam(required = false) boolean check1) {
        User user = userDetailsService.loadUserByUsernamek(principal);
        relations = personsService.filterRelations(user.getId(), relations);

        if (storeLogs.store(user, lastName1, firstName1, fatherName1, relations, depth, limit, orderNum, approvement_type,
                caseNum,
                orderDate,
                articleName,
                checkingName,
                otherReasons,
                organName,
                sphereName,
                tematikName,
                rukName)) {
            if (fatherName1.equals(".*") || fatherName1.equals(".*.*") ){
                return personsService.getPersonByFIO_withoutO(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(), depth, limit, relations);
            }
            if (fatherName1.contains(".*") ){
                return personsService.getPersonByFIO_(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(), depth, limit, relations);
            }
            if(fatherName1.equals("")  & check1 == true){
                return personsService.getPersonByFIO_Accurate(user.getId(),lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),depth,limit,relations);
            }
            if(!fatherName1.equals("")  & check1 == true){
                return personsService.getPersonByFIO_Accurate(user.getId(),lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),depth,limit,relations);
            }
            return personsService.getPersonByFIO_(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(), depth, limit, relations);
        } else {
            return null;
        }
    }

    @GetMapping("/shortestpaths")
    public doubleReturn getShortestPaths(@RequestParam String person, @RequestParam String person2, @RequestParam List<String> relations, @RequestParam(required = false) String orderNum,
                                         @RequestParam(required = false) String caseNum, @RequestParam(required = false) String approvement_type,
                                         @RequestParam(required = false) String orderDate,
                                         @RequestParam(required = false) String articleName,
                                         @RequestParam(required = false) String checkingName,
                                         @RequestParam(required = false) String otherReasons,
                                         @RequestParam(required = false) String organName,
                                         @RequestParam(required = false) String sphereName,
                                         @RequestParam(required = false) String tematikName,
                                         @RequestParam(required = false) String rukName,Principal principal) {
        User user = userDetailsService.loadUserByUsernamek(principal);
        relations = personsService.filterRelations(user.getId(), relations);

        if (storeLogs.store(user, person, person2, relations, orderNum, approvement_type,
                caseNum,
                orderDate,
                articleName,
                checkingName,
                otherReasons,
                organName,
                sphereName,
                tematikName,
                rukName)) {
            return personsService.getShortestPaths(user.getId(), person, person2, relations);
        } else {
            return null;
        }
    }
    @GetMapping("/shortestpathsByFIO")
    public doubleReturn getShortestPathsByFIO(
                                            @RequestParam String lastName1,@RequestParam String firstName1,@RequestParam String fatherName1,@RequestParam String lastName2,@RequestParam String firstName2,@RequestParam String fatherName2, @RequestParam List<String> relations, @RequestParam(required = false) String orderNum,
                                            @RequestParam(required = false) String caseNum, @RequestParam(required = false) String approvement_type,
                                            @RequestParam(required = false) String orderDate,
                                            @RequestParam(required = false) String articleName,
                                            @RequestParam(required = false) String checkingName,
                                            @RequestParam(required = false) String otherReasons,
                                            @RequestParam(required = false) String organName,
                                            @RequestParam(required = false) String sphereName,
                                            @RequestParam(required = false) String tematikName,
                                            @RequestParam(required = false) String rukName,Principal principal,
                                            @RequestParam(required = false) boolean check1,
                                            @RequestParam(required = false) boolean check2) {
        User user = userDetailsService.loadUserByUsernamek(principal);
        relations = personsService.filterRelations(user.getId(), relations);

        if (storeLogs.store(lastName1, firstName1, fatherName1, lastName2, firstName2, fatherName2, relations, orderNum,
                caseNum, approvement_type,
                orderDate,
                articleName,
                checkingName,
                otherReasons,
                organName,
                sphereName,
                tematikName,
                rukName, user, check1, check2)) {

            if(check1 == true & check2 == true){
                return personsService.getShortestPathWithFIOAccurate(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),lastName2.toUpperCase(),firstName2.toUpperCase(),fatherName2.toUpperCase(), relations);
            }
            if(check1 == true & check2 == false){
                return personsService.getShortestPathWithFIOAccurate12(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),lastName2.toUpperCase(),firstName2.toUpperCase(),fatherName2.toUpperCase(), relations);
            }
            if(check1 == false & check2 == true){
                return personsService.getShortestPathWithFIOAccurate21(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),lastName2.toUpperCase(),firstName2.toUpperCase(),fatherName2.toUpperCase(), relations);
            }
            if(fatherName1 == "" & fatherName2 == ""){
                return personsService.getShortestPathWithFIObezbez(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),lastName2.toUpperCase(),firstName2.toUpperCase(),fatherName2.toUpperCase(), relations);
            }
            if(fatherName1 == "" & fatherName2 != ""){
                return personsService.getShortestPathWithFIObezs(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),lastName2.toUpperCase(),firstName2.toUpperCase(),fatherName2.toUpperCase(), relations);
            }
            if(fatherName1 != "" & fatherName2 == ""){
                return personsService.getShortestPathWithFIOsbez(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),lastName2.toUpperCase(),firstName2.toUpperCase(),fatherName2.toUpperCase(), relations);
            }

            return personsService.getShortestPathWithFIO(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),lastName2.toUpperCase(),firstName2.toUpperCase(),fatherName2.toUpperCase(), relations);
        } else {
            return null;
        }

    }
    @GetMapping("/ultree")
    public doubleReturn getUlTree(@RequestParam String ul, @RequestParam List<String> relations, @RequestParam int depth, @RequestParam int limit, @RequestParam(required = false) String orderNum,
                                  @RequestParam(required = false) String caseNum, @RequestParam(required = false) String approvement_type,
                                  @RequestParam(required = false) String orderDate,
                                  @RequestParam(required = false) String articleName,
                                  @RequestParam(required = false) String checkingName,
                                  @RequestParam(required = false) String otherReasons,
                                  @RequestParam(required = false) String organName,
                                  @RequestParam(required = false) String sphereName,
                                  @RequestParam(required = false) String tematikName,
                                  @RequestParam(required = false) String rukName,Principal principal) {
        User user = userDetailsService.loadUserByUsernamek(principal);
        relations = personsService.filterRelations(user.getId(), relations);

        if (storeLogs.storeUl( ul, relations, depth, limit, orderNum,
                caseNum, approvement_type,
                orderDate,
                articleName,
                checkingName,
                otherReasons,
                organName,
                sphereName,
                tematikName,
                rukName, user)) {
            return personsService.getUlTree(user.getId(), ul, relations, depth, limit);
        } else {
            return null;
        }
    }
    @GetMapping("/flulpath")
    public doubleReturn getUlFlPath(@RequestParam String ul, @RequestParam String person, @RequestParam List<String> relations, @RequestParam(required = false) String orderNum,
                                    @RequestParam(required = false) String caseNum, @RequestParam(required = false) String approvement_type,
                                    @RequestParam(required = false) String orderDate,
                                    @RequestParam(required = false) String articleName,
                                    @RequestParam(required = false) String checkingName,
                                    @RequestParam(required = false) String otherReasons,
                                    @RequestParam(required = false) String organName,
                                    @RequestParam(required = false) String sphereName,
                                    @RequestParam(required = false) String tematikName,
                                    @RequestParam(required = false) String rukName,Principal principal) {
        User user = userDetailsService.loadUserByUsernamek(principal);
        relations = personsService.filterRelations(user.getId(), relations);

        if (storeLogs.storeFlUl( ul, person, relations, orderNum,
                caseNum, approvement_type,
                orderDate,
                articleName,
                checkingName,
                otherReasons,
                organName,
                sphereName,
                tematikName,
                rukName, user)) {
            return personsService.getUlFlPath(user.getId(), ul, person, relations);
        } else {
            return null;
        }
    }

    @GetMapping("/flulpathByFIO")
    public doubleReturn getUlFlPathByFIO(@RequestParam String ul, @RequestParam String lastName1,@RequestParam String firstName1,@RequestParam String fatherName1, @RequestParam List<String> relations, @RequestParam(required = false) String orderNum,
                                    @RequestParam(required = false) String caseNum, @RequestParam(required = false) String approvement_type,
                                    @RequestParam(required = false) String orderDate,
                                    @RequestParam(required = false) String articleName,
                                    @RequestParam(required = false) String checkingName,
                                    @RequestParam(required = false) String otherReasons,
                                    @RequestParam(required = false) String organName,
                                    @RequestParam(required = false) String sphereName,
                                    @RequestParam(required = false) String tematikName,
                                    @RequestParam(required = false) String rukName,Principal principal,
                                         @RequestParam(required = false) boolean check1) {
        User user = userDetailsService.loadUserByUsernamek(principal);
        relations = personsService.filterRelations(user.getId(), relations);
        if (storeLogs.storeFlUl( ul, lastName1, firstName1, fatherName1,  relations, orderNum,
                caseNum, approvement_type,
                orderDate,
                articleName,
                checkingName,
                otherReasons,
                organName,
                sphereName,
                tematikName,
                rukName, user,
                check1)) {
            if(fatherName1.equals("")  & check1 == true){
                return personsService.getUlFlPathByFIOAccurate(user.getId(),lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),ul,relations);
            }
            if (fatherName1.equals(".*") || fatherName1.equals(".*.*") ){
                System.out.println(lastName1.toUpperCase());
                return personsService.getUlFlPathByFIOwithoutO(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(), ul, relations);
            }
            if (fatherName1.contains(".*") ){
                System.out.println(lastName1.toUpperCase());
                return personsService.getUlFlPathByFIOwithoutO(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(), ul, relations);
            }
            if(!fatherName1.equals("")  & check1 == true){
                return personsService.getUlFlPathByFIOAccurate(user.getId(),lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),ul,relations);
            }
            return personsService.getUlFlPathByFIO(user.getId(), lastName1.toUpperCase(),firstName1.toUpperCase(),fatherName1.toUpperCase(),ul, relations);
        } else {
            return null;
        }
    }

    @GetMapping("/ululpath")
    public doubleReturn getUlUlPath(@RequestParam String ul1,@RequestParam String ul2,@RequestParam List<String> relations,
                                    @RequestParam(required = false) String approvement_type,
                                    @RequestParam(required = false) String orderNum,
                                    @RequestParam(required = false) String caseNum,
                                    @RequestParam(required = false) String orderDate,
                                    @RequestParam(required = false) String articleName,
                                    @RequestParam(required = false) String checkingName,
                                    @RequestParam(required = false) String otherReasons,
                                    @RequestParam(required = false) String organName,
                                    @RequestParam(required = false) String sphereName,
                                    @RequestParam(required = false) String tematikName,
                                    @RequestParam(required = false) String rukName,Principal principal) {
        User user = userDetailsService.loadUserByUsernamek(principal);
        relations = personsService.filterRelations(user.getId(), relations);
        if (storeLogs.storeUlUl( ul1, ul2,  relations,
                approvement_type,
                orderNum,
                caseNum,
                orderDate,
                articleName,
                checkingName,
                otherReasons,
                organName,
                sphereName,
                tematikName,
                rukName, user)) {
            return personsService.getUlUlPath(user.getId(), ul1, ul2, relations);
        } else {
            return null;
        }
    }

    @GetMapping("/shortopen")
    public doubleReturn getShortOpen(@RequestParam Long id, @RequestParam List<String> relations, @RequestParam int limit, Principal principal) {
        User user = userDetailsService.loadUserByUsernamek(principal);
        relations = personsService.filterRelations(user.getId(), relations);
        doubleReturn resul = personsService.shortOpen(user.getId(), id, relations, limit);
        return resul;
    }

    @GetMapping("/downloadedscheme")
    public void downloadScheme(@RequestParam String first, @RequestParam String second, Principal principal) {
        User user = userDetailsService.loadUserByUsernamek(principal);
        List<String> request_bodies = new ArrayList<>();
        request_bodies.add(first);
        request_bodies.add(second);
        log log = new log();
        String obwii = "Скачал схему: " + request_bodies.get(0) + ", " + request_bodies.get(1);
        log.setObwii(obwii);
        LocalDateTime current = LocalDateTime.now();
        log.setUsername(user.getUsername());
        log.setDate(current);
        log.setRequest_body(request_bodies);
        logsService.SaveLog(log);
    }



    @GetMapping("/newd")
    public String getNdew() {
        try{
            Robot robot = new Robot();
            System.out.println("sds");
            Rectangle rectangle = new Rectangle(0,0,500,500);
            System.out.println("sds");

            BufferedImage bufferedImage = robot.createScreenCapture(rectangle);

            ImageIO.write(bufferedImage,"JPG",new File("C:\\Users\\123\\Desktop\\project-aitab\\new_pj_15\\sreenshot.jpg"));
        }catch (Exception e){
            e.printStackTrace();
        }

        return "privet";
    }
//    @GetMapping("/photo")
//    public photoDb getPhoto(){
//        photoDb photoDb = newPhotoRepo.findByIin("040210551264");
//        System.out.println(photoDb.getPhoto());
//        return newPhotoRepo.findByIin("040502651337");
//    }

//    @GetMapping("/test")
//    public doubleReturn testDoubleReturn() {
//        return companyPersonService.Test();
//    }
//
//    @GetMapping("/fltree")
//    public

//
//    @GetMapping("/statistic")
//    public statisticModel getUserLogs(@RequestParam String username) {
//        return statisticService.getByUsername(username);
//    }
//    @PostMapping("/admin/user/ban/{id}")
//    public void userBan(@PathVariable("id") Long id){
//        statisticService.userBan(id);
//    }
//    @PostMapping("/admin/user/moderator/{id}")
//    public void userModerator(@PathVariable("id") Long id){
//        statisticService.userSetAdministrator(id);
//    }
//
//    @PostMapping("/updaterole")
//    public void updateRole(@RequestParam Integer id, @RequestParam Integer Role) {
//        userRepository.updateRole(id, Role);
//    }
//
//    @GetMapping("/role")
//    public String getRole(@RequestParam Integer id) {
//        return rRepo.FindRole(id);
//    }
//
//    @GetMapping("/logs")
//    public List<log> getLogs() {
//        return logRepo.findAll();
//    }
//
//
//    @GetMapping("/users")
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//
//    @GetMapping("/person")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public doubleReturn getByIdLevelAndLimit(@RequestParam String person, @RequestParam List<String> relations, @RequestParam int depth, @RequestParam int limit, Principal principal) throws Exception{
//        User user  = userDetailsService.loadUserByUsernamek(principal);
//        List<String> request_bodies = new ArrayList<>();
//
//        request_bodies.add(person);
//
//        try{
//            log log = new log();
//            LocalDateTime current = LocalDateTime.now();
//            log.setDate(current);
//            log.setUsername(user.getUsername());
//            log.setRequest_body(request_bodies);
//            log.setLimit_(limit);
//            log.setDepth_(depth);
//            log.setRequest_rels(relations);
//            personService.SaveLog(log);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        List<User> users = userRepository.findAll();
//        System.out.println(users);
//        return personService.getPersonTree(person, relations, depth, limit);
//    }

//    @GetMapping("/shortestpaths")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public doubleReturn getShortestPaths(@RequestParam String person, @RequestParam String person2, @RequestParam List<String> relations, Principal principal) {
//        User user  = userDetailsService.loadUserByUsernamek(principal);
//        List<String> request_bodies = new ArrayList<>();
//        request_bodies.add(person);
//        request_bodies.add(person2);
//        try{
//            log log = new log();
//            LocalDateTime current = LocalDateTime.now();
//            log.setDate(current);
//            log.setUsername(user.getUsername());
//            log.setRequest_body(request_bodies);
//            log.setRequest_rels(relations);
//            personService.SaveLog(log);
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return personService.getShortestPaths(person, person2, relations);
//    }
//
//    @GetMapping("/movie")
//    public doubleReturn retrieveMovie(@RequestParam String title, @RequestParam List<String> relations, Principal principal) {
//        User user  = userDetailsService.loadUserByUsernamek(principal);
//        List<String> request_bodies = new ArrayList<>();
//        request_bodies.add(title);
//        try{
//            log log = new log();
//            LocalDateTime current = LocalDateTime.now();
//            log.setDate(current);
//            log.setUsername(user.getUsername());
//            log.setRequest_body(request_bodies);
//            log.setRequest_rels(relations);
//            personService.SaveLog(log);
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return personService.getByMovie(title, relations);
//    }
//    @GetMapping("/movieperson")
//    public doubleReturn moviePersonRelation(@RequestParam String person, @RequestParam String movie, @RequestParam List<String> relations, Principal principal ) {
//        User user  = userDetailsService.loadUserByUsernamek(principal);
//        List<String> request_bodies = new ArrayList<>();
//        request_bodies.add(person);
//        request_bodies.add(movie);
//        try{
//            log log = new log();
//            LocalDateTime current = LocalDateTime.now();
//            log.setDate(current);
//            log.setUsername(user.getUsername());
//            log.setRequest_body(request_bodies);
//            log.setRequest_rels(relations);
//            personService.SaveLog(log);
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return personService.getMoviePersonRelation(person, movie, relations);
//    }
//    @GetMapping("/shortopen")
//    public doubleReturn shortOpen(@RequestParam Long id) {
//        return personService.shortOpen(id);
//    }

}
