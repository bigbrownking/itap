package com.example.new_project_challenge_15.entity;

import com.example.new_project_challenge_15.entity.RELS_22.OWNER;
import com.example.new_project_challenge_15.entity.RELS_22.REG_ADDRESS_HIST;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node("Car")
@Setter
@Getter
public class Car {

    @Id
    @GeneratedValue
    public Long id;

    @Property("ИИН")
    public String IIN;

    @Property("МОДЕЛЬ")
    public String brandModel;

    @Property("ДАТА СЕРТЕФИКАТА")
    public String dateCertificate;

    @Property("СЕРИЙНЫЙ НОМЕР")
    public String seriesRegNumber;

    @Property("РЕГИСТРАЦОННЫЙ НОМЕР")
    public String regNumber;

    @Property("КАТЕГОРИЯ")
    public String categoryControlTc;

    @Property("ВИН КУЗОВА")
    public String vinKuzovShassi;

    @Property("ОБЬЕМ ДВИГАТЕЛЯ")
    public String engineVolume;

    @Property("ВЕС")
    public String weight;

    @Property("ЦВЕТ")
    public String color;

    @Property("ДАТА СОЗДАНИЕ")
    public String releaseYearTc;

    @Property("РЕГИСТРИВОНА ЛИ")
    public boolean isRegistered;

    @Relationship(type = "OWNER", direction = Relationship.Direction.OUTGOING)
    private List<OWNER> OWNER;

}
