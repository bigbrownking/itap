package com.example.new_project_challenge_15.entity;

import com.example.new_project_challenge_15.entity.RELS_22.*;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node("Company")
public class Company {
    @Id
    @GeneratedValue
    private Long id;
    public String PersonID;
    @Property("БИН")
    public String IINBIN;
    @Property("ТИП")
    public String Type;
    @Property("Наименование")
    public String Name;
    @Property("Бухгалтер")
    public String Buhgalter;
    @Property("Дата начала регистрации")
    public String RegisterStartDate;
    @Property("Полное наим. ИП")
    public String FullNameIP;
    @Property("Source")
    public String Source;
    @Property("Дата окончании регистрации")
    public String RegisterEndDate;
    @Property("Блокировка ЭСФ")
    public String BLOCK_ESF;
    @Property("Статус")
    public String Status;
    @Property("Просрочка")
    public String DeadlinePassed;
    @Property("Дата регистрационного действия")
    public String DateRegisterAction;
    @Property("Статус ПФР")
    public String StatusPFR;
    @Property("ФПГ")
    public String FPG;
    @Property("Бездействующие ЮЛ")
    public String BEZDEYSTVIA_UL;
    @Property("ЕРДР")
    public String ERDR;
    @Property("Полное наименование на гос языке")
    public String FullNameNatLanguage;
    @Property("Лицензия")
    public String License;

    @Property("Доход_2020")
    public Double Dohod_2020;
    @Property("Доход_2021")
    public Double Dohod_2021;
    @Property("Доход_2022")
    public Double Dohod_2022;
    @Property("Поступления_2021")
    public Double Postuplenya_2021;
    @Property("Поступления_2022")
    public Double Postuplenya_2022;
    @Property("Поступления_2023")
    public Double Postuplenya_2023;


    public String getBankrot() {
        return Bankrot;
    }

    public void setBankrot(String bankrot) {
        Bankrot = bankrot;
    }

    @Property("Банкрот")
    public String Bankrot;

    public List<com.example.new_project_challenge_15.entity.RELS_22.esf_1000kk_10kkk> getEsf_1000kk_10kkk() {
        return esf_1000kk_10kkk;
    }

    public void setEsf_1000kk_10kkk(List<com.example.new_project_challenge_15.entity.RELS_22.esf_1000kk_10kkk> esf_1000kk_10kkk) {
        this.esf_1000kk_10kkk = esf_1000kk_10kkk;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.esf_100kk_1000kk> getEsf_100kk_1000kk() {
        return esf_100kk_1000kk;
    }

    public void setEsf_100kk_1000kk(List<com.example.new_project_challenge_15.entity.RELS_22.esf_100kk_1000kk> esf_100kk_1000kk) {
        this.esf_100kk_1000kk = esf_100kk_1000kk;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.esf_10kk_100kk> getEsf_10kk_100kk() {
        return esf_10kk_100kk;
    }

    public void setEsf_10kk_100kk(List<com.example.new_project_challenge_15.entity.RELS_22.esf_10kk_100kk> esf_10kk_100kk) {
        this.esf_10kk_100kk = esf_10kk_100kk;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.esf_10kkk_bolshe> getEsf_10kkk_bolshe() {
        return esf_10kkk_bolshe;
    }

    public void setEsf_10kkk_bolshe(List<com.example.new_project_challenge_15.entity.RELS_22.esf_10kkk_bolshe> esf_10kkk_bolshe) {
        this.esf_10kkk_bolshe = esf_10kkk_bolshe;
    }

    @Relationship(type = "esf_1000kk_10kkk", direction = Relationship.Direction.OUTGOING)
    private List<esf_1000kk_10kkk> esf_1000kk_10kkk;

    @Relationship(type = "esf_100kk_1000kk.csv", direction = Relationship.Direction.OUTGOING)
    private List<esf_100kk_1000kk> esf_100kk_1000kk;

    @Relationship(type = "esf_10kk_100kk", direction = Relationship.Direction.OUTGOING)
    private List<esf_10kk_100kk> esf_10kk_100kk;

    @Relationship(type = "esf_10kkk_bolshe", direction = Relationship.Direction.OUTGOING)
    private List<esf_10kkk_bolshe> esf_10kkk_bolshe;

    @Relationship(type = "REG_ADDRESS_CUR", direction = Relationship.Direction.OUTGOING)
    private List<REG_ADDRESS_CUR> REG_ADDRESS_CUR;
    @Relationship(type = "REG_ADDRESS_HIST", direction = Relationship.Direction.OUTGOING)
    private List<REG_ADDRESS_HIST> REG_ADDRESS_HIST;
    @Relationship(type = "INS_POLICY", direction = Relationship.Direction.OUTGOING)
    private List<INS_POLICY> INS_POLICY;



    @Relationship(type = "BUHGALTER", direction = Relationship.Direction.OUTGOING)
    private List<BUHGALTER> buhgalters;

    @Relationship(type = "DETDOM_HIST", direction = Relationship.Direction.OUTGOING)
    private List<DETDOM_HIST> detdomHistList;
    @Relationship(type = "DFO_AFF_UL", direction = Relationship.Direction.OUTGOING)
    private List<DFO_AFF_UL> dfoAffUls;

    @Relationship(type = "DIRECTOR_CUR", direction = Relationship.Direction.OUTGOING)
    private List<DIRECTOR_CUR> directorCurs;

    @Relationship(type = "DIRECTOR_HIST", direction = Relationship.Direction.OUTGOING)
    private List<DIRECTOR_HIST> directorHists;

    @Relationship(type = "FOUNDER_CUR", direction = Relationship.Direction.INCOMING)
    private List<FOUNDER_CUR> founderCurs;

    @Relationship(type = "FOUNDER_HIST", direction = Relationship.Direction.INCOMING)
    private List<FOUNDER_HIST> founderHists;

    @Relationship(type = "GOSZAKUP", direction = Relationship.Direction.OUTGOING)
    private List<GOSZAKUP> goszakups;


    @Relationship(type = "IP-KX", direction = Relationship.Direction.OUTGOING)
    private List<IP_KX> ipKxes;

    @Relationship(type = "OPG", direction = Relationship.Direction.OUTGOING)
    private List<OPG> opgs;

    @Relationship(type = "PDL", direction = Relationship.Direction.OUTGOING)
    private List<PDL> pdls;


    @Relationship(type = "SUDIM", direction = Relationship.Direction.OUTGOING)
    private List<SUDIM> sudims;

    @Relationship(type = "UCHILSYA", direction = Relationship.Direction.OUTGOING)
    private List<UCHILSYA> uchilsyas;

    @Relationship(type = "WORKER_CUR", direction = Relationship.Direction.OUTGOING)
    private List<WORKER_CUR> workerCurs;

    @Relationship(type = "WORKER_HIST", direction = Relationship.Direction.OUTGOING)
    private List<WORKER_HIST> workerHists;
    @Relationship(type = "dolzhniki_po_sudispol", direction = Relationship.Direction.OUTGOING)
    private List<dolzhniki_po_sudispol> dolzhnik_po_sudispol;

    @Relationship(type = "PREDPOL_BENEFICIAR", direction = Relationship.Direction.OUTGOING)
    private List<PREDPOL_BENEFICIAR> predpol_beneficiar;

    @Relationship(type = "DFO_AFF_FIZ", direction = Relationship.Direction.OUTGOING)
    private List<DFO_AFF_FIZ> DFO_AFF_FIZ;
    @Relationship(type = "DFO_AFF_UL", direction = Relationship.Direction.OUTGOING)
    private List<DFO_AFF_UL> DFO_AFF_UL;

    public List<com.example.new_project_challenge_15.entity.RELS_22.DFO_AFF_FIZ> getDFO_AFF_FIZ() {
        return DFO_AFF_FIZ;
    }

    public void setDFO_AFF_FIZ(List<com.example.new_project_challenge_15.entity.RELS_22.DFO_AFF_FIZ> DFO_AFF_FIZ) {
        this.DFO_AFF_FIZ = DFO_AFF_FIZ;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.DFO_AFF_UL> getDFO_AFF_UL() {
        return DFO_AFF_UL;
    }

    public void setDFO_AFF_UL(List<com.example.new_project_challenge_15.entity.RELS_22.DFO_AFF_UL> DFO_AFF_UL) {
        this.DFO_AFF_UL = DFO_AFF_UL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String personID) {
        PersonID = personID;
    }

    public String getIINBIN() {
        return IINBIN;
    }

    public void setIINBIN(String IINBIN) {
        this.IINBIN = IINBIN;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBuhgalter() {
        return Buhgalter;
    }

    public void setBuhgalter(String buhgalter) {
        Buhgalter = buhgalter;
    }

    public String getRegisterStartDate() {
        return RegisterStartDate;
    }

    public void setRegisterStartDate(String registerStartDate) {
        RegisterStartDate = registerStartDate;
    }

    public String getFullNameIP() {
        return FullNameIP;
    }

    public void setFullNameIP(String fullNameIP) {
        FullNameIP = fullNameIP;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getRegisterEndDate() {
        return RegisterEndDate;
    }

    public void setRegisterEndDate(String registerEndDate) {
        RegisterEndDate = registerEndDate;
    }

    public String getBLOCK_ESF() {
        return BLOCK_ESF;
    }

    public void setBLOCK_ESF(String BLOCK_ESF) {
        this.BLOCK_ESF = BLOCK_ESF;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDeadlinePassed() {
        return DeadlinePassed;
    }

    public void setDeadlinePassed(String deadlinePassed) {
        DeadlinePassed = deadlinePassed;
    }

    public String getDateRegisterAction() {
        return DateRegisterAction;
    }

    public void setDateRegisterAction(String dateRegisterAction) {
        DateRegisterAction = dateRegisterAction;
    }

    public String getStatusPFR() {
        return StatusPFR;
    }

    public void setStatusPFR(String statusPFR) {
        StatusPFR = statusPFR;
    }

    public String getFPG() {
        return FPG;
    }

    public void setFPG(String FPG) {
        this.FPG = FPG;
    }

    public String getBEZDEYSTVIA_UL() {
        return BEZDEYSTVIA_UL;
    }

    public void setBEZDEYSTVIA_UL(String BEZDEYSTVIA_UL) {
        this.BEZDEYSTVIA_UL = BEZDEYSTVIA_UL;
    }

    public String getERDR() {
        return ERDR;
    }

    public void setERDR(String ERDR) {
        this.ERDR = ERDR;
    }

    public String getFullNameNatLanguage() {
        return FullNameNatLanguage;
    }

    public void setFullNameNatLanguage(String fullNameNatLanguage) {
        FullNameNatLanguage = fullNameNatLanguage;
    }

    public String getLicense() {
        return License;
    }

    public void setLicense(String license) {
        License = license;
    }

    public Double getDohod_2020() {
        return Dohod_2020;
    }

    public void setDohod_2020(Double dohod_2020) {
        Dohod_2020 = dohod_2020;
    }

    public Double getDohod_2021() {
        return Dohod_2021;
    }

    public void setDohod_2021(Double dohod_2021) {
        Dohod_2021 = dohod_2021;
    }

    public Double getDohod_2022() {
        return Dohod_2022;
    }

    public void setDohod_2022(Double dohod_2022) {
        Dohod_2022 = dohod_2022;
    }

    public Double getPostuplenya_2021() {
        return Postuplenya_2021;
    }

    public void setPostuplenya_2021(Double postuplenya_2021) {
        Postuplenya_2021 = postuplenya_2021;
    }

    public Double getPostuplenya_2022() {
        return Postuplenya_2022;
    }

    public void setPostuplenya_2022(Double postuplenya_2022) {
        Postuplenya_2022 = postuplenya_2022;
    }

    public Double getPostuplenya_2023() {
        return Postuplenya_2023;
    }

    public void setPostuplenya_2023(Double postuplenya_2023) {
        Postuplenya_2023 = postuplenya_2023;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.REG_ADDRESS_CUR> getREG_ADDRESS_CUR() {
        return REG_ADDRESS_CUR;
    }

    public void setREG_ADDRESS_CUR(List<com.example.new_project_challenge_15.entity.RELS_22.REG_ADDRESS_CUR> REG_ADDRESS_CUR) {
        this.REG_ADDRESS_CUR = REG_ADDRESS_CUR;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.REG_ADDRESS_HIST> getREG_ADDRESS_HIST() {
        return REG_ADDRESS_HIST;
    }

    public void setREG_ADDRESS_HIST(List<com.example.new_project_challenge_15.entity.RELS_22.REG_ADDRESS_HIST> REG_ADDRESS_HIST) {
        this.REG_ADDRESS_HIST = REG_ADDRESS_HIST;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.INS_POLICY> getINS_POLICY() {
        return INS_POLICY;
    }

    public void setINS_POLICY(List<com.example.new_project_challenge_15.entity.RELS_22.INS_POLICY> INS_POLICY) {
        this.INS_POLICY = INS_POLICY;
    }

    public List<BUHGALTER> getBuhgalters() {
        return buhgalters;
    }

    public void setBuhgalters(List<BUHGALTER> buhgalters) {
        this.buhgalters = buhgalters;
    }

    public List<DETDOM_HIST> getDetdomHistList() {
        return detdomHistList;
    }

    public void setDetdomHistList(List<DETDOM_HIST> detdomHistList) {
        this.detdomHistList = detdomHistList;
    }

    public List<DFO_AFF_UL> getDfoAffUls() {
        return dfoAffUls;
    }

    public void setDfoAffUls(List<DFO_AFF_UL> dfoAffUls) {
        this.dfoAffUls = dfoAffUls;
    }

    public List<DIRECTOR_CUR> getDirectorCurs() {
        return directorCurs;
    }

    public void setDirectorCurs(List<DIRECTOR_CUR> directorCurs) {
        this.directorCurs = directorCurs;
    }

    public List<DIRECTOR_HIST> getDirectorHists() {
        return directorHists;
    }

    public void setDirectorHists(List<DIRECTOR_HIST> directorHists) {
        this.directorHists = directorHists;
    }

    public List<FOUNDER_CUR> getFounderCurs() {
        return founderCurs;
    }

    public void setFounderCurs(List<FOUNDER_CUR> founderCurs) {
        this.founderCurs = founderCurs;
    }

    public List<FOUNDER_HIST> getFounderHists() {
        return founderHists;
    }

    public void setFounderHists(List<FOUNDER_HIST> founderHists) {
        this.founderHists = founderHists;
    }

    public List<GOSZAKUP> getGoszakups() {
        return goszakups;
    }

    public void setGoszakups(List<GOSZAKUP> goszakups) {
        this.goszakups = goszakups;
    }

    public List<IP_KX> getIpKxes() {
        return ipKxes;
    }

    public void setIpKxes(List<IP_KX> ipKxes) {
        this.ipKxes = ipKxes;
    }

    public List<OPG> getOpgs() {
        return opgs;
    }

    public void setOpgs(List<OPG> opgs) {
        this.opgs = opgs;
    }

    public List<PDL> getPdls() {
        return pdls;
    }

    public void setPdls(List<PDL> pdls) {
        this.pdls = pdls;
    }

    public List<SUDIM> getSudims() {
        return sudims;
    }

    public void setSudims(List<SUDIM> sudims) {
        this.sudims = sudims;
    }

    public List<UCHILSYA> getUchilsyas() {
        return uchilsyas;
    }

    public void setUchilsyas(List<UCHILSYA> uchilsyas) {
        this.uchilsyas = uchilsyas;
    }

    public List<WORKER_CUR> getWorkerCurs() {
        return workerCurs;
    }

    public void setWorkerCurs(List<WORKER_CUR> workerCurs) {
        this.workerCurs = workerCurs;
    }

    public List<WORKER_HIST> getWorkerHists() {
        return workerHists;
    }

    public void setWorkerHists(List<WORKER_HIST> workerHists) {
        this.workerHists = workerHists;
    }

    public List<dolzhniki_po_sudispol> getDolzhnik_po_sudispol() {
        return dolzhnik_po_sudispol;
    }

    public void setDolzhnik_po_sudispol(List<dolzhniki_po_sudispol> dolzhnik_po_sudispol) {
        this.dolzhnik_po_sudispol = dolzhnik_po_sudispol;
    }

    public List<PREDPOL_BENEFICIAR> getPredpol_beneficiar() {
        return predpol_beneficiar;
    }

    public void setPredpol_beneficiar(List<PREDPOL_BENEFICIAR> predpol_beneficiar) {
        this.predpol_beneficiar = predpol_beneficiar;
    }


}
