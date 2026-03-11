package com.example.new_project_challenge_15.entity.RELS_22;

import com.example.new_project_challenge_15.entity.Persons;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
@Node
public class dolzhniki_po_sudispol {
    @Id
    @GeneratedValue
    public Long id;
    @Property("Вид связи")
    public String Vid_svyaziey;
    @Property("Дата")
    public String dataa;
    @Property("Должник")
    public String dolzhnik;
    @Property("Категория")
    public String kategoria;
    @Property("Орган")
    public String organ;
    @Property("Сумма")
    public String summa;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Property("ТИП")
    public String Type;
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

    public void setVid_svyaziey(String vid_svyaziey) {
        Vid_svyaziey = vid_svyaziey;
    }

    public String getDataa() {
        return dataa;
    }

    public void setDataa(String dataa) {
        this.dataa = dataa;
    }

    public String getDolzhnik() {
        return dolzhnik;
    }

    public void setDolzhnik(String dolzhnik) {
        this.dolzhnik = dolzhnik;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getSumma() {
        return summa;
    }

    public void setSumma(String summa) {
        this.summa = summa;
    }

    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
        this.person = person;
    }
}
