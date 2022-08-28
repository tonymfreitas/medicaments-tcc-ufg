start-infrastructure: ## Run all infrastructure
	docker-compose -f ./local/docker-compose.yml build
	docker-compose -f ./local/docker-compose.yml up -d

stop-infrastructure: ## Stop all infrastructure
	docker-compose -f ./local/docker-compose.yml down

execute-liquibase:
	/bin/bash local/db/liquibase/update.sh

start-backend:
	./local/start.sh
