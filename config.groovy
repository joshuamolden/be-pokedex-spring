environments {
    local {
        spring {
            datasource {
                changelog = 'src/main/resources/db/changelog/db.changelog-master.xml'
                url = 'jdbc:mariadb://localhost:13307/PokemonDB?allowPublicKeyRetrieval=true&useSSL=false'
                username = 'pokemon_admin'
                password = 'CatchEmAll!'
            }
        }
    }

    test {
        spring {
            datasource {
                changelog = 'src/main/resources/db/changelog/db.changelog-master.xml'
                url = 'jdbc:mariadb://localhost:13307/PokemonDB?allowPublicKeyRetrieval=true&useSSL=false'
                username = 'pokemon_admin'
                password = 'CatchEmAll!'
            }
        }
    }
}