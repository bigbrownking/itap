package com.example.new_project_challenge_15.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

@Node("Apartment")
@Setter
@Getter
public class Apartment {
    @Id
    @GeneratedValue
    public Long id;

    @Property("ИИН")
    public String IIN;

    @Property("РКА КОД")
    public String rka_code;

    @Property("ТИП")
    public String type;

    @Property("СТАТУС")
    public String status;

    @Property("АДРЕСС")
    public String address;

    @Property("ОБЩАЯ ПЛОЩАДЬ")
    public String area_total;

    @Property("ИСПОЛЬЗУЕМАЯ ПЛОЩАДЬ")
    public String area_useful;

    @Property("ЭТАЖ")
    public String floor;

    @Property("СТОИМОСТЬ")
    public String transaction_amount;

    @Property("РЕГИСТРАННОЙ ДАТА")
    public String reg_date;

    @Property("ДОГОВОР КУПЛИ ПРОДАЖ")
    public String emergence_rights;

    @Property("ФИО ПРОДОВЦА")
    public String owner_full_name;
}
