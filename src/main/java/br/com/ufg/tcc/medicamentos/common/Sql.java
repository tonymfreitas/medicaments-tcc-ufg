package br.com.ufg.tcc.medicamentos.common;

public enum Sql {

    // Availability
    SAVE_AVAILABILITY("/save-availability.sql"),
    LIST_ALL_AVAILABILITY("/list-all-availability.sql"),
    LIST_AVAILABILITY_BY_STATE("/list-availability-by-state.sql"),

    // ClassificationAtc
    FIND_CLASSIFICATION_ATC_BY_CODE("/find-classification-atc-by-code.sql"),
    FIND_CLASSIFICATION_ATC_BY_PARENT("/find-classification-atc-by-parent.sql"),
    LIST_ALL_CLASSIFICATION_ATC("/list-all-classification-atc.sql"),
    SAVE_CLASSIFICATION_ATC("/save-classification-atc.sql"),
    FIND_CLASSIFICATION_ATC_BY_ID("/find-classification-atc-by-id.sql"),
    DELETE_CLASSIFICATION_ATC_BY_ID("/delete-classification-atc-by-id.sql"),
    UPDATE_CLASSIFICATION_ATC("/update-classification-atc.sql");




    private final String sql;

    Sql(String sql) {
        this.sql = sql;
    }

    public String sql() {
        return QueryFileReader.getContentFromQueryFile(sql);
    }

}
