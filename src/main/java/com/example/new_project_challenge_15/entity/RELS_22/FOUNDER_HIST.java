package com.example.new_project_challenge_15.entity.RELS_22;

import com.example.new_project_challenge_15.entity.Company;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
@Node
public class FOUNDER_HIST {
    @Id
    @GeneratedValue
    public Long id;

    @Property("Вид связи")
    public String Vid_svyaziey;
    @Property("Сумма вклада тг")
    public String SumFundTG;
    @Property("Доля вклада проц")
    public String ShareFundPercent;
    @TargetNode
    private Company company;
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

    public String getSumFundTG() {
        return SumFundTG;
    }

    public void setSumFundTG(String sumFundTG) {
        SumFundTG = sumFundTG;
    }

    public String getShareFundPercent() {
        return ShareFundPercent;
    }

    public void setShareFundPercent(String shareFundPercent) {
        ShareFundPercent = shareFundPercent;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
