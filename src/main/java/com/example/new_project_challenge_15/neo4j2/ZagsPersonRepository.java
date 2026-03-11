package com.example.new_project_challenge_15.neo4j2;


import org.neo4j.driver.Value;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ZagsPersonRepository extends Neo4jRepository<PersonZags,Long> {
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..1]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=1 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepthOne(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..2]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=2 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepthTwo(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..3]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=3 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepthThree(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..4]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=4 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepth4(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..5]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=5 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepth5(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..6]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=6 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepth6(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..7]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=7 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepth7(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..8]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=8 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepth8(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..9]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=9 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepth9(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..10]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=10 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepth10(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..11]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=11 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepth11(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..12]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=12 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepth12(String PERSON, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..13]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=13 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTreeDepth13(String PERSON, int LIMIT, List<String> RELS);
    //--------------------------------------------------------------------------------------------------
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` = ($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ($O) OPTIONAL MATCH p = (startNode)-[*1..1]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=1 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth1(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O) OPTIONAL MATCH p = (startNode)-[*1..2]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=2 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth2(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O) OPTIONAL MATCH p = (startNode)-[*1..3]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=3 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth3(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O) OPTIONAL MATCH p = (startNode)-[*1..4]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=4 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth4(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O) OPTIONAL MATCH p = (startNode)-[*1..5]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=5 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth5(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O) OPTIONAL MATCH p = (startNode)-[*1..6]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=6 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth6(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O) OPTIONAL MATCH p = (startNode)-[*1..7]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=7 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth7(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O) OPTIONAL MATCH p = (startNode)-[*1..8]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=8 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth8(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O) OPTIONAL MATCH p = (startNode)-[*1..9]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=9 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth9(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O)  OPTIONAL MATCH p = (startNode)-[*1..10]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=10 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth10(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O)  OPTIONAL MATCH p = (startNode)-[*1..11]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=11 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth11(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O)  OPTIONAL MATCH p = (startNode)-[*1..12]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=12 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth12(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) and startNode.`Отчество` =~ ($O)  OPTIONAL MATCH p = (startNode)-[*1..13]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=13 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth13(String F, String I, String O, int LIMIT, List<String> RELS);
    //--------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` = ($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ('.*' + $O) OPTIONAL MATCH p = (startNode)-[*1..1]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=1 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth1Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` = ($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ('.*' + $O) OPTIONAL MATCH p = (startNode)-[*1..2]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=2 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth2Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ('.*' + $O) OPTIONAL MATCH p = (startNode)-[*1..3]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=3 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth3Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =($F) and startNode.`Имя` =($I) and startNode.`Отчество` =~ ('.*' + $O) OPTIONAL MATCH p = (startNode)-[*1..4]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=4 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth4Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` = ($F) and startNode.`Имя` =($I) and startNode.`Отчество` =~ ('.*' + $O) OPTIONAL MATCH p = (startNode)-[*1..5]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=5 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth5Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` = ($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ('.*' + $O) OPTIONAL MATCH p = (startNode)-[*1..6]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=6 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth6Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ('.*' + $O) OPTIONAL MATCH p = (startNode)-[*1..7]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=7 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth7Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` = ($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ('.*' + $O) OPTIONAL MATCH p = (startNode)-[*1..8]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=8 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth8Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` = ($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ('.*' + $O) OPTIONAL MATCH p = (startNode)-[*1..9]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=9 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth9Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` = ($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ('.*' + $O)  OPTIONAL MATCH p = (startNode)-[*1..10]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=10 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth10Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` = ($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ('.*' + $O)  OPTIONAL MATCH p = (startNode)-[*1..11]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=11 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth11Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` = ($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ('.*' + $O)  OPTIONAL MATCH p = (startNode)-[*1..12]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=12 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth12Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` = ($F) and startNode.`Имя` = ($I) and startNode.`Отчество` =~ ('.*' + $O)  OPTIONAL MATCH p = (startNode)-[*1..13]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=13 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth13Accurate(String F, String I, String O, int LIMIT, List<String> RELS);
    //--------------------------------------------------------------------------------------------------
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I)   OPTIONAL MATCH p = (startNode)-[*1..1]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=1 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth1withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I)  OPTIONAL MATCH p = (startNode)-[*1..2]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=2 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth2withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I)   OPTIONAL MATCH p = (startNode)-[*1..3]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=3 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth3withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)  WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) OPTIONAL MATCH p = (startNode)-[*1..4]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=4 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth4withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) OPTIONAL MATCH p = (startNode)-[*1..5]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=5 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth5withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I)  OPTIONAL MATCH p = (startNode)-[*1..6]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=6 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth6withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags)WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I)  OPTIONAL MATCH p = (startNode)-[*1..7]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=7 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth7withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) OPTIONAL MATCH p = (startNode)-[*1..8]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=8 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth8withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I)  OPTIONAL MATCH p = (startNode)-[*1..9]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=9 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth9withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) OPTIONAL MATCH p = (startNode)-[*1..10]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=10 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth10withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) OPTIONAL MATCH p = (startNode)-[*1..11]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=11 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth11withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) OPTIONAL MATCH p = (startNode)-[*1..12]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=12 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth12withoutO(String F, String I, String O, int LIMIT, List<String> RELS);
    @Query("MATCH (startNode:Person_zags) WHERE startNode.`Фамилия` =~ ($F) and startNode.`Имя` =~ ($I) OPTIONAL MATCH p = (startNode)-[*1..13]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=13 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonByFIO_Depth13withoutO(String F, String I, String O, int LIMIT, List<String> RELS);



    //GET UL UL PATH --------------------------------------------------------
    @Query("MATCH p=shortestPath((a:Person_zags)-[r*]-(b:Person_zags)) where a.`ИИН`=($FIRST) and b.`ИИН`=($SECOND) and ALL(rel in relationships(p) WHERE type(rel) in $RELS) RETURN COLLECT(DISTINCT p)")
    List<PersonZags> getPathsWithIIN(String FIRST, String SECOND, List<String> RELS);
    @Query("MATCH p=shortestPath((a:Person_zags)-[r*]-(b:Person_zags)) where a.`Фамилия` =~ ($F1) and a.`Имя` =~ ($I1) and a.`Отчество` =~ ($O1) and b.`Фамилия` =~ ($F2) and b.`Имя` =~ ($I2) and b.`Отчество` =~ ($O2) and ALL(rel in relationships(p) WHERE type(rel) in $RELS) RETURN COLLECT(DISTINCT p)")
    List<PersonZags> getPathsWithFIO(String F1, String I1, String O1, String F2, String I2, String O2, List<String> RELS);
    @Query("MATCH p=shortestPath((a:Person_zags)-[r*]-(b:Person_zags)) where a.`Фамилия` = ($F1) and a.`Имя` = ($I1) and a.`Отчество` =~ ('.*' + $O1) and b.`Фамилия` = ($F2) and b.`Имя` = ($I2) and b.`Отчество` =~ ('.*' + $O2) and ALL(rel in relationships(p) WHERE type(rel) in $RELS) RETURN COLLECT(DISTINCT p)")
    List<PersonZags> getPathsWithFIOAccurate2(String F1, String I1, String O1, String F2, String I2, String O2, List<String> RELS);
    @Query("MATCH p=shortestPath((a:Person_zags)-[r*]-(b:Person_zags)) where a.`Фамилия` = ($F1) and a.`Имя` = ($I1) and a.`Отчество` =~ ('.*' + $O1) and b.`Фамилия` =~ ($F2) and b.`Имя` =~ ($I2) and b.`Отчество` =~ ($O2) and ALL(rel in relationships(p) WHERE type(rel) in $RELS) RETURN COLLECT(DISTINCT p)")
    List<PersonZags> getPathsWithFIOAccurate12(String F1, String I1, String O1, String F2, String I2, String O2, List<String> RELS);
    @Query("MATCH p=shortestPath((a:Person_zags)-[r*]-(b:Person_zags)) where a.`Фамилия` =~ ($F1) and a.`Имя` =~ ($I1) and a.`Отчество` =~ ($O1) and b.`Фамилия` = ($F2) and b.`Имя` = ($I2) and b.`Отчество` =~ ('.*' + $O2) and ALL(rel in relationships(p) WHERE type(rel) in $RELS) RETURN COLLECT(DISTINCT p)")
    List<PersonZags> getPathsWithFIOAccurate21(String F1, String I1, String O1, String F2, String I2, String O2, List<String> RELS);
    @Query("MATCH p=shortestPath((a:Person_zags)-[r*]-(b:Person_zags)) where a.`Фамилия` =~ ($F1) and a.`Имя` =~ ($I1) and b.`Фамилия` =~ ($F2) and b.`Имя` =~ ($I2) and b.`Отчество` =~ ($O2) and ALL(rel in relationships(p) WHERE type(rel) in $RELS) RETURN COLLECT(DISTINCT p)")
    List<PersonZags> getPathsWithFIObezs(String F1, String I1, String O1, String F2, String I2, String O2, List<String> RELS);
    @Query("MATCH p=shortestPath((a:Person_zags)-[r*]-(b:Person_zags)) where a.`Фамилия` =~ ($F1) and a.`Имя` =~ ($I1) and a.`Отчество` =~ ($O1) and b.`Фамилия` =~ ($F2) and b.`Имя` =~ ($I2)  and ALL(rel in relationships(p) WHERE type(rel) in $RELS) RETURN COLLECT(DISTINCT p)")
    List<PersonZags> getPathsWithFIOsbez(String F1, String I1, String O1, String F2, String I2, String O2, List<String> RELS);
    @Query("MATCH p=shortestPath((a:Person_zags)-[r*]-(b:Person_zags)) where a.`Фамилия` =~ ($F1) and a.`Имя` =~ ($I1)  and b.`Фамилия` =~ ($F2) and b.`Имя` =~ ($I2)  and ALL(rel in relationships(p) WHERE type(rel) in $RELS) RETURN COLLECT(DISTINCT p)")
    List<PersonZags> getPathsWithFIObezbez(String F1, String I1, String O1, String F2, String I2, String O2, List<String> RELS);

    @Query("WITH $DEPTH as d MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($PERSON) OPTIONAL MATCH p = (startNode)-[*1..10]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=d WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTree(String PERSON, @Param("depth") int DEPTH, int LIMIT, List<String> RELS);

    @Query("WITH $params.depth as d MATCH (startNode:Person_zags) WHERE startNode.`ИИН` = ($params.person) OPTIONAL MATCH p = (startNode)-[*1..$depth]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $params.relations) AND length(p)<=d WITH DISTINCT p as paths LIMIT $params.limit RETURN COLLECT(distinct paths)")
    List<PersonZags> getPersonTree(@Param("params") Value params, @Param("depth") Value depth);

    //    @Query("MATCH (startNode) WHERE id(startNode)=$ID OPTIONAL MATCH p = (startNode)-[r]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $relations) WITH DISTINCT p as paths RETURN COLLECT(distinct paths)")
    @Query("MATCH (startNode) WHERE id(startNode) = $ID OPTIONAL MATCH p = (startNode)-[*1..1]-(endNode) WHERE ALL(rel in relationships(p) WHERE type(rel) in $RELS) AND length(p)<=1 WITH DISTINCT p as paths LIMIT $LIMIT RETURN COLLECT(distinct paths)")
    List<PersonZags> shortOpen(Long ID, List<String> RELS, int LIMIT);

    @Query("MATCH p=(n)-[r]->(e)  where id(r) = $ID RETURN id(startNode(r)) LIMIT 1")
    Long getEndNodeId(Long ID);
    @Query("MATCH p=(n)-[r]->(e)  where id(r) = $ID RETURN id(startNode(r)) LIMIT 1")
    Long getStartNodeId(Long ID);
//    ---------------------------------------------

}
