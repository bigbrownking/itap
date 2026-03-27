package com.example.new_project_challenge_15.entity.RELS_22;

import com.example.new_project_challenge_15.entity.Car;
import com.example.new_project_challenge_15.entity.Persons;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
@Node
@Getter
@Setter
public class OWNER {
    @Id
    @GeneratedValue
    public Long id;

    @Property("Вид связи")
    public String Vid_svyaziey;

    @Property("Дата начало владений")
    public String Start_Date;

    @Property("Дата окончания владений")
    public String End_Date;

    @TargetNode
    private Car car;
}
