# Pokemon API v1.0

This is an api that will retrieve pokemon information. The pokemon information can be retrieved individually or as a list
of paginated pokemon. Retrieving pokemon individually will return more information on the pokemon than if you retrieve a list of
pokemon. In the future, this api will be able to keep track of trainer information such as which pokemon trainers have capture.

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

The database can then be hosted on a Docker container through the docker-compose file with this command in in the terminal
```text
docker compose up -d
```

The app can then be started through IntelliJ start. If TablePlus is downloaded, you can view your database tables after inputting
database information.

---

## API Endpoints for Retrieving Pokemon Information
1. /api/v1/pokemon/

    - This endpoint will load a paginated list of pokemon (15 per page) that is sorted by the id of the pokemon.
    
    
2. /api/v1/pokemon/?name=
   
   - This endpoint takes an optional name parameter, which will search the database for pokemon that **contain** the string of 
      characters in the search parameter. The results for this query will also be returned in a paginated list (15 per page)
      that is sorted by the id of the pokemon.
     
      
3. /api/v1/pokemon/import

    - This endpoint will import all pokemon from a csv file. There are 553 pokemon in the file, and it will take around 35-40
    seconds to do this. This endpoint will only import the pokemon once.
      
      
4. /api/v1/pokemon/{pokemon_id}

    - This endpoint will return the full details of a pokemon based on the number passed to the query string. If an api call is
    made with an invalid pokemon_id, an HTTP status code 404 will be sent with an empty body.
      
    
#### Sample JSON response for details of a specific pokemon
```json5
{
    "id": 348,
    "name": "Armaldo",
    "types": [
        "bug",
        "rock"
    ],
    "height": 15.0,
    "weight": 682.0,
    "abilities": [
        "swift-swim",
        "battle-armor"
    ],
    "egg_groups": [
        "water3"
    ],
    "stats": {
        "hp": 75,
        "speed": 45,
        "attack": 125,
        "defense": 100,
        "special-attack": 70,
        "special-defense": 80
    },
    "genus": "Plate Pokémon",
    "description": "Armaldo is a Pokémon species that became extinct in\nprehistoric times. This Pokémon is said to have walked\non its hind legs, which would have been more convenient\nfor life on land."
}
```