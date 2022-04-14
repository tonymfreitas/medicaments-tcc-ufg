package br.com.ufg.tcc.medicamentos.classificationatc;

import br.com.ufg.tcc.medicamentos.classificationatc.level.ClassificacionAtc;
import br.com.ufg.tcc.medicamentos.common.ResourceNotFoundException;
import br.com.ufg.tcc.medicamentos.medicament.MedicamentEntity;
import br.com.ufg.tcc.medicamentos.medicament.MedicamentRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClassificationAtcService {

    private final String FILE_NAME = "medicamentos.xlsx";

    @Autowired
    private ClassificationAtcRepository repository;

    @Autowired
    private MedicamentRepository medRepository;

    @Autowired
    private AwsClassificationAtcService awsClassificationAtcService;

    public List<ClassificacionAtc> getAll() {
        List<ClassificationAtcEntity> all = repository.findAll();
        List<ClassificacionAtc> classifications = new ArrayList<>();
        List<ClassificacionAtc> classificacionAtcs = new ArrayList<>();

        all.forEach(a -> {
            classifications.add(convert(a));
        });

        List<ClassificacionAtc> levelOne = classifications.stream().filter(c -> c.getLevel() == 1).collect(Collectors.toList());
        levelOne.forEach(l -> {

            if (l != null) {
                //Get level 1
                ClassificacionAtc oneLevel = new ClassificacionAtc(l.getId(), l.getCodeAtc(), l.getName(), l.getLevel(), l.getCodeAtcParent());

                //Get level 2
                List<ClassificacionAtc> twoLevels = getLevelsChildren(oneLevel, classifications);
                oneLevel.setChildren(twoLevels);

                twoLevels.forEach(two -> {
                    List<ClassificacionAtc> threeLevels = getLevelsChildren(two, classifications);
                    two.setChildren(threeLevels);

                    threeLevels.forEach(three -> {
                        List<ClassificacionAtc> fourLevels = getLevelsChildren(three, classifications);
                        three.setChildren(fourLevels);
                        fourLevels.forEach(four -> {
                            List<ClassificacionAtc> fiveLevels = getLevelsChildren(four, classifications);
                            four.setChildren(fiveLevels);
                        });
                    });
                });

                classificacionAtcs.add(oneLevel);
            }

        });

        return classificacionAtcs;
    }

    private List<ClassificacionAtc> getLevelsChildren(ClassificacionAtc level, List<ClassificacionAtc> classifications) {
        List<ClassificacionAtc> levelsChildren = classifications.stream().filter(a -> Objects.nonNull(a.getCodeAtcParent()) && a.getCodeAtcParent().equals(level.getCodeAtc()) && level.getCodeAtc() != a.getCodeAtc()).collect(Collectors.toList());
        return levelsChildren.isEmpty() ? new ArrayList<>() : levelsChildren;
    }

    public void save(ClassificacionAtc classificacionAtc) {
        repository.save(convert(classificacionAtc));
    }

    public void sendDataToAws() {
        awsClassificationAtcService.sendClassifications(getAll());
    }

    // Jeito rápido de ler o arquivo, definir um arquivo padrão em que poderíamos realizar o upload para alimentar o sistema.
    // Método que lê todos os dados da planilha e grava no banco de daos PostgreSQL.
    // Verificar se falta dados na planilha e se os que existem são satisfatórios para criar os serviços de cadastro.
    public void save() {
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            List<String> medicamentos = new ArrayList<>();
            List<ClassificationAtcEntity> classificationsAtc = new ArrayList<>();


            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                ClassificationAtcEntity grupoAtcMedicament = new ClassificationAtcEntity(UUID.randomUUID());

                int numberCell = 0;
                String medicamento = "";
                Boolean med = false;

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        int sizeCodeAtc = currentCell.getStringCellValue().length();
                        String codeAtc = currentCell.getStringCellValue();
                        if (numberCell == 0 && sizeCodeAtc < 6) {
                            grupoAtcMedicament.setCodeAtc(codeAtc);

                            if (sizeCodeAtc == 1) {
                                grupoAtcMedicament.setLevel(1);
                                grupoAtcMedicament.setCodeAtcParent(codeAtc);
                            }

                            if (sizeCodeAtc == 3) {
                                grupoAtcMedicament.setLevel(2);
                                grupoAtcMedicament.setCodeAtcParent(codeAtc.substring(0, 1));
                            }

                            if (sizeCodeAtc == 4) {
                                grupoAtcMedicament.setLevel(3);
                                grupoAtcMedicament.setCodeAtcParent(codeAtc.substring(0, 3));
                            }

                            if (sizeCodeAtc == 5) {
                                grupoAtcMedicament.setLevel(4);
                                grupoAtcMedicament.setCodeAtcParent(codeAtc.substring(0, 4));
                            }


                        } else if (numberCell == 1 && grupoAtcMedicament.getCodeAtc() != null && grupoAtcMedicament.getCodeAtc().length() < 6) {
                            grupoAtcMedicament.setName(currentCell.getStringCellValue());
                        } else {
                            if (numberCell == 0 && currentCell.getStringCellValue().length() > 5) {
                                med = true;
                                medicamento = medicamento + currentCell.getStringCellValue() + "-";
                            }

                            if (numberCell > 0 && med) {
                                medicamento = medicamento + currentCell.getStringCellValue() + "-";
                            }
                        }

                    }

                    numberCell++;

                }

                if (grupoAtcMedicament.getCodeAtc() != null) {
                    repository.save(grupoAtcMedicament);
                    classificationsAtc.add(grupoAtcMedicament);
                }

                if (!medicamento.isEmpty()) {
                    medicamentos.add(medicamento);
                }

            }

            String s = medicamentos.get(0);

            for (String medic : medicamentos) {

                if (!s.equals(medic)) {
                    MedicamentEntity param = new MedicamentEntity();
                    String[] values = medic.split("-");

                    if (values[2].length() > 2) {
                        System.out.println(medic);
                        continue;
                    }

                    List<ClassificationAtcEntity> groups = classificationsAtc
                            .stream()
                            .filter(c -> values[0].contains(c.getCodeAtc()))
                            .sorted(Comparator.comparing(ClassificationAtcEntity::getLevel).reversed())
                            .collect(Collectors.toList());

                    param.setId(UUID.randomUUID());
                    param.setIdclassificationatc(groups.get(0).getId());
                    param.setCodeAtc(values[0]);
                    param.setName(values[1]);
                    param.setUnity(values[2]);
                    param.setFormPhamaceutical(values[3]);
                    param.setVia(values[4]);
                    param.setUse(values[5]);
                    param.setRestriction(values[6]);

                    medRepository.save(param);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ClassificationAtcEntity convert(ClassificacionAtc classificacionAtc) {
        ClassificationAtcEntity entity = new ClassificationAtcEntity(Objects.nonNull(classificacionAtc.getId()) ? classificacionAtc.getId() : UUID.randomUUID());
        entity.setCodeAtc(classificacionAtc.getCodeAtc());
        entity.setCodeAtcParent(classificacionAtc.getCodeAtcParent());
        entity.setLevel(classificacionAtc.getLevel());
        entity.setName(classificacionAtc.getName());
        return entity;
    }

    private ClassificacionAtc convert(ClassificationAtcEntity entity) {
        ClassificacionAtc classificacionAtc = new ClassificacionAtc();
        classificacionAtc.setCodeAtc(entity.getCodeAtc());
        classificacionAtc.setCodeAtcParent(entity.getCodeAtcParent());
        classificacionAtc.setLevel(entity.getLevel());
        classificacionAtc.setName(entity.getName());
        classificacionAtc.setId(entity.getId());
        return classificacionAtc;
    }

    private List<ClassificacionAtc> convert(List<ClassificationAtcEntity> entitys) {
        List<ClassificacionAtc> classifications = new ArrayList<>();
        entitys.forEach(e -> classifications.add(convert(e)));
        return classifications;
    }

    public ClassificacionAtc findById(UUID uuid) {
        ClassificationAtcEntity entity = Optional.ofNullable(repository.findById(uuid).orElseThrow(RuntimeException::new)).get();
        return convert(entity);
    }

    private void setChildren(ClassificacionAtc classificationAtc) {
        List<ClassificationAtcEntity> children = repository.findByCodeAtcParent(classificationAtc.getCodeAtc());

        if(Objects.nonNull(children)) {
            classificationAtc.setChildren(convert(children));

            classificationAtc.getChildren().forEach(c -> {
                setChildren(c);
            });
        } else {
            classificationAtc.setChildren(new ArrayList<>());
        }
    }

    public ClassificacionAtc findByCode(String codeAtc) {
        ClassificationAtcEntity entityOpt = repository.findByCode(codeAtc).orElseThrow(ResourceNotFoundException::new);
        ClassificacionAtc classificationAtc = convert(entityOpt);

        setChildren(classificationAtc);

        return classificationAtc;
    }

    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    public void update(ClassificacionAtc classificacionAtc) {
        repository.update(classificacionAtc.getId(), classificacionAtc.getName());
    }
}
