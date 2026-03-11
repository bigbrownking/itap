package com.example.new_project_challenge_15.neo4j2;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
@Node("new_zags")
public class NewZags {
    @Id
    @GeneratedValue
    public Long id;

    @Property("Вид связи")
    public String Vid_svyaziey;
    @Property("Возраст отца")
    public String Vozrast_OTSA;

    @TargetNode
    private PersonZags person;

    public PersonZags getPerson() {
        return person;
    }

    public void setPerson(PersonZags person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVid_svyaziey() {
        return Vid_svyaziey;
    }

    public void setVid_svyaziey(String vid_svyaziey) {
        Vid_svyaziey = vid_svyaziey;
    }

    public String getVozrast_OTSA() {
        return Vozrast_OTSA;
    }

    public void setVozrast_OTSA(String vozrast_OTSA) {
        Vozrast_OTSA = vozrast_OTSA;
    }
}
