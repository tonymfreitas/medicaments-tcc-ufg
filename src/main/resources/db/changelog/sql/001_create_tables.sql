--liquibase formatted sql
--changeset tony.william:001_create_tables

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE classificationatc (
    id UUID NOT NULL DEFAULT uuid_generate_v1() ,
    codeatc VARCHAR(10),
    name VARCHAR(150),
    level int,
    codeatcparent VARCHAR(10),
    CONSTRAINT id_class_atc PRIMARY KEY (id),
    CONSTRAINT uk_codeatc UNIQUE (codeatc)
);

CREATE TABLE pharmaceuticalform (
  id UUID NOT NULL DEFAULT uuid_generate_v1(),
  code VARCHAR(20),
  description VARCHAR(150),
  CONSTRAINT id_pharma_form PRIMARY KEY (id),
  CONSTRAINT uk_pharma_form UNIQUE (code)
);

CREATE TABLE medicaments (
    id UUID NOT NULL DEFAULT uuid_generate_v1() ,
    idclassificationatc UUID not null,
    codeatc VARCHAR(10),
    name VARCHAR(150),
    unity VARCHAR(2),
    idpharmaceuticalform UUID not null,
    component VARCHAR(100),
    via VARCHAR(150),
    use VARCHAR(10),
    restriction VARCHAR(300),
    idavailability uuid,
    CONSTRAINT id_medicaments PRIMARY KEY (id),
    CONSTRAINT fk_classifc_atc FOREIGN KEY (idclassificationatc) REFERENCES classificationatc(id),
    CONSTRAINT fk_pharma_form FOREIGN KEY (idpharmaceuticalform) REFERENCES pharmaceuticalform(id),
    CONSTRAINT uk_medicament UNIQUE (name)
);

CREATE TABLE availability (
    id UUID NOT NULL DEFAULT uuid_generate_v1(),
    address VARCHAR(150),
    latitude numeric,
    longitude numeric,
    quantity bigint
);

