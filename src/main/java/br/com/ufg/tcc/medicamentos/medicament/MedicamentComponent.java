package br.com.ufg.tcc.medicamentos.medicament;

public enum MedicamentComponent {
    SKILLED, HOSPITAL, STRATEGIC, BASIC;

    public static MedicamentComponent getByName(final String name) {
        MedicamentComponent medicamentComponent = MedicamentComponent.BASIC;
        try {
            medicamentComponent = valueOf(name);
        } catch (IllegalArgumentException e) {

        }
        return medicamentComponent;
    }

}
