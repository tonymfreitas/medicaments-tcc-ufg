# Informações importantes

Este documento visa introduzir algumas informações sobre o projeto.

O que foi realizado neste projeto é uma iniciativa para verificar se um modelo de banco de dados relacional atenderia ao propósito do projeto.

Banco de dados: PostgreSQL.

##### Documentação da API LOCALHOST : 
    http://localhost:8080/swagger-ui.html

##### Documentação da API LOCALHOST :
    Autenticação Oauth Basic.
    username: admin
    password: password

##### JOB de envio das informações para um bucket S3 :
Exite um JOB que roda a cada hora enviando dois arquivos JSON para um bucket S3.
Da forma atual em que se encontra os arquivos são:
    - https://medicaments-tcc-ufg.s3.amazonaws.com/classifications.json
    - https://medicaments-tcc-ufg.s3.amazonaws.com/medicaments.json

#### Version 1.0 
    Criação do projeto e funcionalidades mínimas para uma primeira impressão dos dados.

#### Version 2.0
    Criação do CRUD completo de medicamento e classificação ATC
    Realização do envio para o BUCKET S3