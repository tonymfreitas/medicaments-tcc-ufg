#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE SCHEMA ifood_logistics_finance_leasing_agreement_manager;
    ALTER USER "$POSTGRES_USER" SET search_path TO 'ifood_logistics_finance_leasing_agreement_manager';
EOSQL
