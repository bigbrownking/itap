package com.example.new_project_challenge_15.entity.RELS_22;

import com.example.new_project_challenge_15.entity.Company;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class esf_10kkk_bolshe {
    @Id
    @GeneratedValue
    public Long id;
    @Property("Вид связи")
    public String Vid_svyaziey;
    @Property("Сумма")
    public String sum;
    @Property("Сумма по периодам")
    public String sum_by_periods;
    @TargetNode
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getSum_by_periods() {
        return sum_by_periods;
    }

    public void setSum_by_periods(String sum_by_periods) {
        this.sum_by_periods = sum_by_periods;
    }
}
