package com.example.new_project_challenge_15.entity.RELS_22;

import com.example.new_project_challenge_15.entity.Persons;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
@Node
public class UCHILSYA {
    @Id
    @GeneratedValue
    public Long id;
    @Property("Вид связи")
    public String Vid_svyaziey;
    @Property("Год поступления")
    public String data_nachalo;
    @Property("Год окончания")
    public String data_konca;
    @Property("Специальность")
    public String specialnost;
    @TargetNode
    private Persons person;

    public String getSpecialnost() {
        return specialnost;
    }

    public void setSpecialnost(String specialnost) {
        this.specialnost = specialnost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getLabel() {
//        return Label;
//    }

//    public void setLabel(String label) {
//        Label = label;
//    }

//    public String getSource() {
//        return Source;
//    }

//    public void setSource(String source) {
//        Source = source;
//    }

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

    public String getData_konca() {
        return data_konca;
    }

    public void setData_konca(String data_konca) {
        this.data_konca = data_konca;
    }

    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
        this.person = person;
    }
}
