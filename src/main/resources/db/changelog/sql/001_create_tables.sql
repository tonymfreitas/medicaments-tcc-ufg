--liquibase formatted sql
--changeset tony.william:001_create_tables

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE classificationatc (
    id UUID NOT NULL DEFAULT uuid_generate_v1() ,
    code_atc VARCHAR(10),
    name VARCHAR(150),
    level int,
    code_atc_parent VARCHAR(10),
    CONSTRAINT id_class_atc PRIMARY KEY (id),
    CONSTRAINT uk_code_atc UNIQUE (code_atc)
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
    id_classification_atc UUID not null,
    code_atc VARCHAR(10),
    name VARCHAR(150),
    unity VARCHAR(2),
    id_pharmaceutical_form UUID not null,
    component VARCHAR(100),
    via VARCHAR(150),
    use VARCHAR(10),
    CONSTRAINT id_medicaments PRIMARY KEY (id),
    CONSTRAINT fk_classifc_atc FOREIGN KEY (id_classification_atc) REFERENCES classificationatc(id),
    CONSTRAINT fk_pharma_form FOREIGN KEY (id_pharmaceutical_form) REFERENCES pharmaceuticalform(id),
    CONSTRAINT uk_medicament UNIQUE (name)
);

CREATE TABLE states (
    id VARCHAR(2) PRIMARY KEY,
    name VARCHAR(50),
    code VARCHAR(2)
);

CREATE TABLE adresses (
    id UUID NOT NULL DEFAULT uuid_generate_v1(),
    street VARCHAR(100),
    number VARCHAR(10),
    complement VARCHAR(50),
    district VARCHAR(50),
    cep VARCHAR(20),
    id_state VARCHAR(2) NOT NULL,
    CONSTRAINT fk_state FOREIGN KEY (id_state) REFERENCES states(id)
);

CREATE TABLE establishments (
    id UUID NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
    code_cnes VARCHAR(30),
    cnpj VARCHAR(20),
    name VARCHAR(100),
    phone VARCHAR(30),
    type_id VARCHAR(50),
    id_address UUID
);


CREATE TABLE availability (
    id UUID NOT NULL DEFAULT uuid_generate_v1(),
    id_medicament uuid,
    id_establishment uuid,
    quantity bigint,
    restriction VARCHAR(300),
    CONSTRAINT fk_av_establishment FOREIGN KEY (id_establishment) REFERENCES establishments(id),
    CONSTRAINT fk_av_medicament FOREIGN KEY (id_medicament) REFERENCES medicaments(id)
);

CREATE TABLE outbox (
    id UUID NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
    event VARCHAR(30),
    status VARCHAR(30),
    data text
);

