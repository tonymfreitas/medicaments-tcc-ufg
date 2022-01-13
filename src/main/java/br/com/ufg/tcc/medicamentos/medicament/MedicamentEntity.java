package br.com.ufg.tcc.medicamentos.medicament;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "medicaments")
@Data
public class MedicamentEntity {

    @Id
    private UUID id;

    private UUID idclassificationatc;

    @Column(name = "codeatc")
    private String codeAtc;

    private String name;

    private String unity;

    @Column(name = "formphamaceutical")
    private String formPhamaceutical;

    private String via;

    private String use;

    private String restriction;

    public MedicamentEntity(UUID id, String codeAtc, String name, String description, String unity, String formPhamaceutical, String via, String use, String restriction, String code, String dispensingPlace) {
        this.id = id;
        this.codeAtc = codeAtc;
        this.name = name;
        this.unity = unity;
        this.formPhamaceutical = formPhamaceutical;
        this.via = via;
        this.use = use;
        this.restriction = restriction;
    }

    public UUID getIdclassificationatc() {
        return idclassificationatc;
    }

    public void setIdclassificationatc(UUID idclassificationatc) {
        this.idclassificationatc = idclassificationatc;
    }

    public MedicamentEntity() {

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

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getFormPhamaceutical() {
        return formPhamaceutical;
    }

    public void setFormPhamaceutical(String formPhamaceutical) {
        this.formPhamaceutical = formPhamaceutical;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }
}