FROM postgres:14-alpine

RUN apk add --update htop

COPY setup-postgres.sh /docker-entrypoint-initdb.d/setup-postgres.sh

RUN chmod 0666 /docker-entrypoint-initdb.d/setup-postgres.sh
