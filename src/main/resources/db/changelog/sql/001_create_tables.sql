--liquibase formatted sql
--changeset tony.william:001_create_tables

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE classificationatc (
    id UUID NOT NULL DEFAULT uuid_generate_v1() ,
    codeatc VARCHAR(10),
    name VARCHAR(150),
    level int,
    codeatcparent VARCHAR(10),
    CONSTRAINT id_class_atc PRIMARY KEY ( id ),
    CONSTRAINT uk_codeatc UNIQUE (codeatc)
);

CREATE TABLE medicaments (
    id UUID NOT NULL DEFAULT uuid_generate_v1() ,
    idclassificationatc UUID not null,
    codeatc VARCHAR(10),
    name VARCHAR(150),
    unity VARCHAR(2),
    formphamaceutical VARCHAR(50),
    via VARCHAR(150),
    use VARCHAR(10),
    restriction VARCHAR(300),
    CONSTRAINT id_medicaments PRIMARY KEY ( id ),
    CONSTRAINT fk_classifc_atc FOREIGN KEY (idclassificationatc) REFERENCES classificationatc(id)
);