package com.example.new_project_challenge_15.entity.RELS_22;

import com.example.new_project_challenge_15.entity.Persons;
import org.springframework.data.neo4j.core.schema.*;

import static java.awt.SystemColor.info;

@RelationshipProperties
@Node("operVipisk")
public class operVipisk {
    @Id
    @GeneratedValue
    public Long id;

    @Property("Info")
    public String info;

    @TargetNode
    private Persons person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
        this.person = person;
    }
}
