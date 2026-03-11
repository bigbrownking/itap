package com.example.new_project_challenge_15.neo4j2;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Person_zags")
@AllArgsConstructor
@NoArgsConstructor
public class PersonZags {
    @Id
    @GeneratedValue
    private Long id;
    @Property("key_person")
    public String key_person;
    @Property("Дата рождения")
    public String Data_Rozhdenya;

    @Property("Фамилия")
    public String Familia;
    @Property("Отчество")
    public String Otchestvo;
    @Property("Имя")
    public String Name;

    @Property("ИИН")
    public String IIN;

    @Relationship(type="new_zags")
    private List<NewZags> zagsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey_person() {
        return key_person;
    }

    public void setKey_person(String key_person) {
        this.key_person = key_person;
    }

    public String getData_Rozhdenya() {
        return Data_Rozhdenya;
    }

    public void setData_Rozhdenya(String data_Rozhdenya) {
        Data_Rozhdenya = data_Rozhdenya;
    }

    public String getFamilia() {
        return Familia;
    }

    public void setFamilia(String familia) {
        Familia = familia;
    }

    public String getOtchestvo() {
        return Otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        Otchestvo = otchestvo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIIN() {
        return IIN;
    }

    public void setIIN(String IIN) {
        this.IIN = IIN;
    }

    public List<NewZags> getZagsList() {
        return zagsList;
    }

    public void setZagsList(List<NewZags> zagsList) {
        this.zagsList = zagsList;
    }
}
