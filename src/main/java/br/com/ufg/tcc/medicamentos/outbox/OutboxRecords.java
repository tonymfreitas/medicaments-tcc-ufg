package br.com.ufg.tcc.medicamentos.outbox;

public interface OutboxRecords {

    public record OutboxDataSyncEstablishment(int version, String data) {}

}
