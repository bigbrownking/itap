package com.example.new_project_challenge_15.entity;

import com.example.new_project_challenge_15.entity.RELS_22.BUHGALTER;
import com.example.new_project_challenge_15.entity.RELS_22.*;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node("Person")
public class Persons {
    @Id
    @GeneratedValue
    public Long id;
    @Property("ИИН")
    public String IIN;
    @Property("Имя")
    public String Name;
    @Property("Фамилия")
    public String Family;
    @Property("Статус")
    public String Status;
    @Property("Бухгалтер")
    public String Buhgalter;
    @Property("ЧСИ")
    public String CHSI;
    @Property("Место рождения")
    public String Place_of_Birth;
    @Property("Гражданство")
    public String Citizenship;
    @Property("Нация")
    public String Nation;
    @Property("Дата рождения")
    public String Data_Rozhdenya;
    @Property("Отчество")
    public String Otchestvo;
    @Property("ФИО")
    public String FIO;
    public String Source;
    @Property("Аудитор")
    public String Autditor;
    @Property("Нотариус")
    public String Notarius;

    //RISKS
    @Property("Должник")
    public String Doljnik;
    @Property("ПРОПАВШИЙ")
    public String Propal;
    @Property("ПДЛ")
    public String PDL;
    @Property("Приставание в общественных местах")
    public String Pristavanie;
    @Property("Прекращено органом")
    public String SroppedByOrgan;
    @Property("Прекращено судом")
    public String StoppedBySud;
    @Property("Розыск")
    public String Rozisk;
    @Property("Просрочка")
    public String DeadlinePassed;

    @Property("Номер телефона")
    public String phone_number;

    public String getDeadlinePassed() {
        return DeadlinePassed;
    }

    public void setDeadlinePassed(String deadlinePassed) {
        DeadlinePassed = deadlinePassed;
    }

    @Property("Статус Минздрав")
    public String Status_Minzdrav;
    @Property("Статус ПФР")
    public String StatusPFR;
    @Property("Статус смерти")
    public String Death_Status;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIIN() {
        return IIN;
    }

    public void setIIN(String IIN) {
        this.IIN = IIN;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
        Family = family;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getBuhgalter() {
        return Buhgalter;
    }

    public void setBuhgalter(String buhgalter) {
        Buhgalter = buhgalter;
    }

    public String getStoppedBySud() {
        return StoppedBySud;
    }

    public void setStoppedBySud(String stoppedBySud) {
        StoppedBySud = stoppedBySud;
    }

    public String getCHSI() {
        return CHSI;
    }

    public void setCHSI(String CHSI) {
        this.CHSI = CHSI;
    }

    public String getPDL() {
        return PDL;
    }

    public void setPDL(String PDL) {
        this.PDL = PDL;
    }

    public String getSroppedByOrgan() {
        return SroppedByOrgan;
    }

    public void setSroppedByOrgan(String sroppedByOrgan) {
        SroppedByOrgan = sroppedByOrgan;
    }

    public String getPlace_of_Birth() {
        return Place_of_Birth;
    }

    public void setPlace_of_Birth(String place_of_Birth) {
        Place_of_Birth = place_of_Birth;
    }

    public String getCitizenship() {
        return Citizenship;
    }

    public void setCitizenship(String citizenship) {
        Citizenship = citizenship;
    }

    public String getNation() {
        return Nation;
    }

    public void setNation(String nation) {
        Nation = nation;
    }

    public String getData_Rozhdenya() {
        return Data_Rozhdenya;
    }

    public void setData_Rozhdenya(String data_Rozhdenya) {
        Data_Rozhdenya = data_Rozhdenya;
    }

    public String getOtchestvo() {
        return Otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        Otchestvo = otchestvo;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getDeath_Status() {
        return Death_Status;
    }

    public void setDeath_Status(String death_Status) {
        Death_Status = death_Status;
    }

    public String getStatus_Minzdrav() {
        return Status_Minzdrav;
    }

    public void setStatus_Minzdrav(String status_Minzdrav) {
        Status_Minzdrav = status_Minzdrav;
    }

    public String getPristavanie() {
        return Pristavanie;
    }

    public void setPristavanie(String pristavanie) {
        Pristavanie = pristavanie;
    }

    public String getAutditor() {
        return Autditor;
    }

    public void setAutditor(String autditor) {
        Autditor = autditor;
    }

    public String getNotarius() {
        return Notarius;
    }

    public void setNotarius(String notarius) {
        Notarius = notarius;
    }

    public String getPropal() {
        return Propal;
    }

    public void setPropal(String propal) {
        Propal = propal;
    }

    public String getDoljnik() {
        return Doljnik;
    }

    public void setDoljnik(String doljnik) {
        Doljnik = doljnik;
    }

    public String getRozisk() {
        return Rozisk;
    }

    public void setRozisk(String rozisk) {
        Rozisk = rozisk;
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

    public List<DFO_AFF_FIZ> getDfoAffFizs() {
        return dfoAffFizs;
    }

    public void setDfoAffFizs(List<DFO_AFF_FIZ> dfoAffFizs) {
        this.dfoAffFizs = dfoAffFizs;
    }

    public List<BLIZKIE_RODS> getBlizkieRods() {
        return blizkieRods;
    }

    public void setBlizkieRods(List<BLIZKIE_RODS> blizkieRods) {
        this.blizkieRods = blizkieRods;
    }

    public List<BLIZKIE_RODS> getBlizkieRodsOut() {
        return blizkieRodsOut;
    }

    public void setBlizkieRodsOut(List<BLIZKIE_RODS> blizkieRodsOut) {
        this.blizkieRodsOut = blizkieRodsOut;
    }

    public List<SIBLING> getSiblings() {
        return siblings;
    }

    public void setSiblings(List<SIBLING> siblings) {
        this.siblings = siblings;
    }

    public List<COUSIN> getCousins() {
        return cousins;
    }

    public void setCousins(List<COUSIN> cousins) {
        this.cousins = cousins;
    }

    public List<SIBLING> getSiblingsOut() {
        return siblingsOut;
    }

    public void setSiblingsOut(List<SIBLING> siblingsOut) {
        this.siblingsOut = siblingsOut;
    }

    public List<COUSIN> getCousinsOut() {
        return cousinsOut;
    }

    public void setCousinsOut(List<COUSIN> cousinsOut) {
        this.cousinsOut = cousinsOut;
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

    public List<REGISTER_BENEFICIAR> getRegister_beneficiar() {
        return register_beneficiar;
    }

    public void setRegister_beneficiar(List<REGISTER_BENEFICIAR> register_beneficiar) {
        this.register_beneficiar = register_beneficiar;
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

    public List<OPG> getOpgs() {
        return opgs;
    }

    public void setOpgs(List<OPG> opgs) {
        this.opgs = opgs;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.PDL> getPdls() {
        return pdls;
    }

    public void setPdls(List<com.example.new_project_challenge_15.entity.RELS_22.PDL> pdls) {
        this.pdls = pdls;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.REG_ADDRESS_CUR> getRegAddressCurs() {
        return regAddressCurs;
    }

    public void setRegAddressCurs(List<com.example.new_project_challenge_15.entity.RELS_22.REG_ADDRESS_CUR> regAddressCurs) {
        this.regAddressCurs = regAddressCurs;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.REG_ADDRESS_HIST> getRegAddressHists() {
        return regAddressHists;
    }

    public void setRegAddressHists(List<com.example.new_project_challenge_15.entity.RELS_22.REG_ADDRESS_HIST> regAddressHists) {
        this.regAddressHists = regAddressHists;
    }

    public List<SLUZHIL> getSluzhils() {
        return sluzhils;
    }

    public void setSluzhils(List<SLUZHIL> sluzhils) {
        this.sluzhils = sluzhils;
    }

    public List<SUDIM> getSudims() {
        return sudims;
    }

    public void setSudims(List<SUDIM> sudims) {
        this.sudims = sudims;
    }

    public List<ZAGS> getZags() {
        return zags;
    }

    public void setZags(List<ZAGS> zags) {
        this.zags = zags;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.BERKUT> getBERKUT() {
        return BERKUT;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.operVipisk> getOperVipisk() {
        return operVipisk;
    }

    public void setoperVipisk(List<com.example.new_project_challenge_15.entity.RELS_22.operVipisk> operVipisk) {
        this.operVipisk = operVipisk;
    }

    public void setBERKUT(List<com.example.new_project_challenge_15.entity.RELS_22.BERKUT> BERKUT) {
        this.BERKUT = BERKUT;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.INS_POLICY> getINS_POLICY() {
        return INS_POLICY;
    }

    public void setINS_POLICY(List<com.example.new_project_challenge_15.entity.RELS_22.INS_POLICY> INS_POLICY) {
        this.INS_POLICY = INS_POLICY;
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



    public String getStatusPFR() {
        return StatusPFR;
    }

    public void setStatusPFR(String statusPFR) {
        StatusPFR = statusPFR;
    }
    @Relationship(type = "PHONE",  direction = Relationship.Direction.OUTGOING)
    private List<PHONE> phones;

    public List<PHONE> getPhones() {
        return phones;
    }

    public void setPhones(List<PHONE> phones) {
        this.phones = phones;
    }

    @Relationship(type = "BUHGALTER", direction = Relationship.Direction.OUTGOING)
    private List<BUHGALTER> buhgalters;
    @Relationship(type = "DETDOM_HIST", direction = Relationship.Direction.OUTGOING)
    private List<DETDOM_HIST> detdomHistList;
    @Relationship(type = "DFO_AFF_FIZ", direction = Relationship.Direction.OUTGOING)
    private List<DFO_AFF_FIZ> dfoAffFizs;
    @Relationship(type = "BLIZKIE_RODS", direction = Relationship.Direction.INCOMING)
    private List<BLIZKIE_RODS> blizkieRods;
    @Relationship(type = "BLIZKIE_RODS", direction = Relationship.Direction.OUTGOING)
    private List<BLIZKIE_RODS> blizkieRodsOut;
    @Relationship(type = "SIBLING", direction = Relationship.Direction.INCOMING)
    private List<SIBLING> siblings;
    @Relationship(type = "COUSIN", direction = Relationship.Direction.INCOMING)
    private List<COUSIN> cousins;
    @Relationship(type = "SIBLING", direction = Relationship.Direction.OUTGOING)
    private List<SIBLING> siblingsOut;
    @Relationship(type = "COUSIN", direction = Relationship.Direction.OUTGOING)
    private List<COUSIN> cousinsOut;
    @Relationship(type = "WORKER_CUR", direction = Relationship.Direction.INCOMING)
    private List<WORKER_CUR> workerCurs;
    @Relationship(type = "WORKER_HIST", direction = Relationship.Direction.INCOMING)
    private List<WORKER_HIST> workerHists;
    @Relationship(type = "REGISTER_BENEFICIAR", direction = Relationship.Direction.OUTGOING)
    private List<REGISTER_BENEFICIAR> register_beneficiar;
    @Relationship(type = "FOUNDER_CUR", direction = Relationship.Direction.INCOMING)
    private List<FOUNDER_CUR> founderCurs;
    @Relationship(type = "FOUNDER_HIST", direction = Relationship.Direction.INCOMING)
    private List<FOUNDER_HIST> founderHists;
    @Relationship(type = "OPG", direction = Relationship.Direction.OUTGOING)
    private List<OPG> opgs;
    @Relationship(type = "PDL", direction = Relationship.Direction.OUTGOING)
    private List<PDL> pdls;
    @Relationship(type = "REG_ADDRESS_CUR", direction = Relationship.Direction.OUTGOING)
    private List<REG_ADDRESS_CUR> regAddressCurs;
    @Relationship(type = "REG_ADDRESS_HIST", direction = Relationship.Direction.OUTGOING)
    private List<REG_ADDRESS_HIST> regAddressHists;
    @Relationship(type = "SLUZHIL", direction = Relationship.Direction.OUTGOING)
    private List<SLUZHIL> sluzhils;
    @Relationship(type = "SUDIM", direction = Relationship.Direction.OUTGOING)
    private List<SUDIM> sudims;
    @Relationship(type = "ZAGS", direction = Relationship.Direction.OUTGOING)
    private List<ZAGS> zags;
    @Relationship(type = "BERKUT", direction = Relationship.Direction.OUTGOING)
    private List<BERKUT> BERKUT;
    @Relationship(type = "INS_POLICY", direction = Relationship.Direction.OUTGOING)
    private List<INS_POLICY> INS_POLICY;
    @Relationship(type = "REG_ADDRESS_CUR", direction = Relationship.Direction.OUTGOING)
    private List<REG_ADDRESS_CUR> REG_ADDRESS_CUR;
    @Relationship(type = "REG_ADDRESS_HIST", direction = Relationship.Direction.OUTGOING)
    private List<REG_ADDRESS_HIST> REG_ADDRESS_HIST;
    @Relationship(type = "dolzhniki_po_sudispol", direction = Relationship.Direction.OUTGOING)
    private List<dolzhniki_po_sudispol> dolzhniki_po_sudispol;
    @Relationship(type = "IP-KX", direction = Relationship.Direction.OUTGOING)
    private List<IP_KX> IP_KX;
    @Relationship(type = "PREDPOL_BENEFICIAR", direction = Relationship.Direction.OUTGOING)
    private List<PREDPOL_BENEFICIAR> PREDPOL_BENEFICIAR;
    @Relationship(type = "operVipisk", direction = Relationship.Direction.OUTGOING)
    private List<operVipisk> operVipisk;

    public List<com.example.new_project_challenge_15.entity.RELS_22.PREDPOL_BENEFICIAR> getPREDPOL_BENEFICIAR() {
        return PREDPOL_BENEFICIAR;
    }

    public void setPREDPOL_BENEFICIAR(List<com.example.new_project_challenge_15.entity.RELS_22.PREDPOL_BENEFICIAR> PREDPOL_BENEFICIAR) {
        this.PREDPOL_BENEFICIAR = PREDPOL_BENEFICIAR;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.IP_KX> getIP_KX() {
        return IP_KX;
    }

    public void setIP_KX(List<com.example.new_project_challenge_15.entity.RELS_22.IP_KX> IP_KX) {
        this.IP_KX = IP_KX;
    }

    public List<com.example.new_project_challenge_15.entity.RELS_22.dolzhniki_po_sudispol> getDolzhniki_po_sudispol() {
        return dolzhniki_po_sudispol;
    }

    public void setDolzhniki_po_sudispol(List<com.example.new_project_challenge_15.entity.RELS_22.dolzhniki_po_sudispol> dolzhniki_po_sudispol) {
        this.dolzhniki_po_sudispol = dolzhniki_po_sudispol;
    }
}
