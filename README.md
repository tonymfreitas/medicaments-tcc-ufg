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

##### Rodar em ambiente local :
    Dar permissao chmod +x ao Makefile e depois executar
    Rodar backend local e front end.

    Os passos anteriores podem ser ignorados, e o ambiente completo pode ser iniciado apenas executando o docker-compose-local.yml
    

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

#### Version 3.0
    Ajustes na configuração local incluindo a utilização de containers docker.
    Modificado endpoint para gerar dados a partir de um csv.
    Adicionado projeto web para pré-visualização da classificação ATC e seus produtos.
    
