# Pokemon API v1.0

This is an api that will retrieve pokemon information. The pokemon information can be retrieved individually or as a
list of paginated pokemon. Retrieving pokemon individually will return more information on the pokemon than if you
retrieve a list of pokemon. In the future, this api will be able to keep track of trainer information such as which
pokemon trainers have capture.

---

### Technology Stack Information

- IDE: IntelliJ IDEA Community Edition
- Container Platform: Docker (MySQL)
- Database Management: Liquibase
- Runtime: JVM v1.5.10
- Framework: Spring Boot v2.6.0-SNAPSHOT

---

### Requirements

1. IntelliJ IDEA Community Edition
2. Docker
3. Java 8 or 9
4. Liquibase
5. TablePlus (optional)

---

### Installation

```text
git clone git@bitbucket.org:myriadmobile/pokedexjm-api.git
```

The database can then be hosted on a Docker container through the docker-compose file with this command in in the
terminal

```text
docker compose up -d
```

The app can then be started through IntelliJ start. If TablePlus is downloaded, you can view your database tables after
inputting database information.

---

## API Documentation

[Click here for api documentation](http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config)

**This link will only work if the project is running on your local host on port 8080**