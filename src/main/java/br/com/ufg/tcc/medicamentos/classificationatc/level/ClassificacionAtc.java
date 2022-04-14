package br.com.ufg.tcc.medicamentos.classificationatc.level;

import java.util.List;
import java.util.UUID;

public class ClassificacionAtc {

    private UUID id;
    private String codeAtc;
    private String name;
    private Integer level;
    private String codeAtcParent;

    public List<ClassificacionAtc> getChildren() {
        return children;
    }

    public void setChildren(List<ClassificacionAtc> children) {
        this.children = children;
    }

    private List<ClassificacionAtc> children;

    public ClassificacionAtc() {

    }

    public ClassificacionAtc(UUID id, String codeAtc, String name, Integer level, String codeAtcParent) {
        this.id = id;
        this.codeAtc = codeAtc;
        this.name = name;
        this.level = level;
        this.codeAtcParent = codeAtcParent;
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
