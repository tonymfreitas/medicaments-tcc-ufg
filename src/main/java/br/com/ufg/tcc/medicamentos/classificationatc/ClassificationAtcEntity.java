package br.com.ufg.tcc.medicamentos.classificationatc;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "classificationatc")
@Data
public class ClassificationAtcEntity {

    @Id
    private UUID id;

    @Column(name = "code_atc")
    private String codeAtc;

    private String name;

    private Integer level;

    @Column(name = "code_atc_parent")
    private String codeAtcParent;

    public ClassificationAtcEntity(UUID id) {
        this.id = id;
    }

    public ClassificationAtcEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodeAtc() {
        return codeAtc;
    }

    public void setCodeAtc(String codeAtc) {
        this.codeAtc = codeAtc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCodeAtcParent() {
        return codeAtcParent;
    }

    public void setCodeAtcParent(String codeAtcParent) {
        this.codeAtcParent = codeAtcParent;
    }
}
