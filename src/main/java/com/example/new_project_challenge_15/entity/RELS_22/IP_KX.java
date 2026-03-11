package com.example.new_project_challenge_15.entity.RELS_22;

import com.example.new_project_challenge_15.entity.Address;
import com.example.new_project_challenge_15.entity.Persons;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
@Node("IP-KX")
public class IP_KX {
    @Id
    @GeneratedValue
    public Long id;
    @Property("Вид связи")
    public String Vid_svyaziey;
    @TargetNode
    private Persons person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVid_svyaziey() {
        return Vid_svyaziey;
    }

    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
        this.person = person;
    }

    public void setVid_svyaziey(String vid_svyaziey) {
        Vid_svyaziey = vid_svyaziey;
    }

}
