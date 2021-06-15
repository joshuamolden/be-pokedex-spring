environments {
    local {
        spring {
            datasource {
                changelog = 'src/main/resources/db/changelog_master.yaml'
                url = 'jdbc:mariadb://localhost:13306/PokemonDB?allowPublicKeyRetrieval=true&useSSL=false'
                username = 'pokemon_admin'
                password = 'CatchEmAll!'
            }
        }
    }

    test {
        spring {
            datasource {
                changelog = 'src/main/resources/db/changelog_master.yaml'
                url = 'jdbc:mariadb://localhost:13306/PokemonDB?allowPublicKeyRetrieval=true&useSSL=false'
                username = 'pokemon_admin'
                password = 'CatchEmAll!'
            }
        }
    }
}