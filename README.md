
# Installations guide
## Overordnet
- Installér og kør [Docker Desktop](https://www.docker.com/products/docker-desktop) 
## Postgres
- Start Docker Desktop 
- Kør følgende kommandoer fra terminal:

```
docker pull postgres
docker run --name postgres -p 5432:5432 -d -e POSTGRES_PASSWORD=softdb -e POSTGRES_USER=softdb -e POSTGRES_DB=soft2021 -v pgdata:/var/lib/postgressql/data postgres
docker exec -it b7516d69d963 psql soft2021 -h localhost -U softdb

```
- Åben script fra følgende [URL](https://github.com/fred8728/SOFT_DBD_EXAM/tree/master/PostgreSQL)
- Indsæt queries i terminalen i følgende rækkefølge:

```
Setup.sql
Test_data.sql
Views.sql
Customer_storedProcedures.sql
Order_storedProcedures.sql

```
Du er nu klar til at kunne kører Customer- og OrderController klasserne.

## Neo4j
- Installer [Neo3j desktop](https://neo4j.com/download/)
- Åben Neo4j desktop og opret en ny database
- Åben den nye database og opret 3 lokale databaser med version 4.2.0
- Åben denne [mappe](https://github.com/fred8728/SOFT_DBD_EXAM/tree/master/Neo4j), som indeholder 3 Neo4j conf. 
Disse skal erstatte de originale filer der oprettes med de lokale databaser. Filerne kan findes under følgende sti: 
"neo4j\appData\relate-data\dbmss\<My-DatabaseName>\conf"
- Gå derefter ind i hver af de lokale databaser indtast følgende kommando:

```
\bin\neo4j install-service
\bin\neo4j start
```
Du er nu klar til at teste adgang til databasen på http://localhost på port 7474,7475 og 7476. Bemærk: Authentication er slået fra da det kun er beregnet til at køre på localhost da vi stadig er under udvikling. 

* Åben derefter denne [fil](https://github.com/fred8728/SOFT_DBD_EXAM/blob/master/Chocolate/src/main/java/com/example/spring/facade/ProductFacade.java) i Intellij og kør klassen.

Du kan nu også kører ProductController klassen.

## Redis
* Start Docker desktop
* Kør Docker Desktop
Kør disse to commands fra en terminal:

```
- docker pull redis
- docker run --name my-redis -v redis-data:/data -d redis:alpine
- docker exec -it my-redis redis-cli

```
Du er nu klar til at kunne oprette og hente data fra din redis container, samt at kører ShoppingCartController klassen.
