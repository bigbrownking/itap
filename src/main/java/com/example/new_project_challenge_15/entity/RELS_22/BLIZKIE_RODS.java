package com.example.new_project_challenge_15.entity.RELS_22;
import com.example.new_project_challenge_15.entity.Company;
import com.example.new_project_challenge_15.entity.Persons;
import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
@Node("BLIZKIE_RODS")
public class BLIZKIE_RODS {
    @Id
    @GeneratedValue
    public Long id;

    @Property("Описание")
    public String Description;
    @Property("Вид связи")
    public String Vid_svyaziey;
    @Property("Source")
    public String Source;

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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getVid_svyaziey() {
        return Vid_svyaziey;
    }

    public void setVid_svyaziey(String vid_svyaziey) {
        Vid_svyaziey = vid_svyaziey;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }
}
