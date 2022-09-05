package br.com.ufg.tcc.medicamentos.classificationatc;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassificationAtc {

    private UUID id;

    private String codeAtc;

    private String name;

    private Integer level;

    private String codeAtcParent;

}
