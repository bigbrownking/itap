package com.example.new_project_challenge_15.entity.RELS_22;

import com.example.new_project_challenge_15.entity.Persons;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
@Node
public class DIRECTOR_CUR {
    @Id
    @GeneratedValue
    public Long id;
    @Property("Вид связи")
    public String Vid_svyaziey;
    @Property("Дата начало работы")
    public String data_nachalo;
    @TargetNode
    private Persons person;

    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
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

    public String getData_nachalo() {
        return data_nachalo;
    }

    public void setData_nachalo(String data_nachalo) {
        this.data_nachalo = data_nachalo;
    }

}
