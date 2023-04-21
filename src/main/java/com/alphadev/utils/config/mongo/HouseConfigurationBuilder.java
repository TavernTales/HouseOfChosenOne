package com.alphadev.utils.config.mongo;

import com.alphadev.entity.House;
import com.alphadev.repository.HouseRepository;

import java.util.List;

public class HouseConfigurationBuilder {

    private final HouseRepository repository = new HouseRepository();
    public HouseConfigurationBuilder() {
       List.of(zeroniaBuilder(),vlarolaBuilder(),frandhraBuilder(),nashorBuilder(),drakkarisBuilder()).forEach(this::populateHouseDatabase);
    }
    private void populateHouseDatabase(House house){
        if(repository.findById(house.getId()).isPresent()) return;

        repository.save(house);
    }

    private House zeroniaBuilder(){
        return new House()
                .setId(1L)
                .setName("Reino de Zeronia")
                .setTag("[&c&lZ&r]")
                .setDetails("""
                 Zeronia \u00E9 um Reino que se encontra nas Planicies e Florestas ao redor,
                 seu simbolo \u00E9 relacionado ao sol enquanto o vermelho simboliza a paix\u00E3o pelo
                 seu reino. Zeronia \u00E9 conhecida pelo bem estar de seu povo e desenvolvimento
                 com outras Casas, apenas da rela\u00E7\u00E3o com algumas n\u00E3o serem t\u00E3o positivas quando
                 outras, entretanto, seu grande ponto forte sempre foi o comercio e a busca de
                 grandes guerreiros para lutar em nome de Zeronia""")
                .setPolicy("Monarquia")
                .setAlign("Bom e Leal")
                .setAlly(List.of("Vlarol\u00E1"))
                .setNeutral(List.of("Frandha"))
                .setEnemy(List.of("Nashor", "Drakkaris"))
                .setObjective("Com\u00E9rcio local e internacional, crescimento da civiliza\u00E7\u00E3o e batalhas PVP")
                .setContribuition(0)
                .setPermissions(List.of());

    }
    private House vlarolaBuilder(){
        return new House()
                .setId(2L)
                .setName("Cidade de Vlarol\u00E1")
                .setTag("[&9&lV&r]")
                .setDetails("""
                    A Cidade de Vlarol\u00E1 se encontra nos grandes Desertos, sua bandeira simboliza
                    o conhecimento e constru\u00E7\u00E3o. Vlarol\u00E1 possui um forte vinculo com Zeronia, principalmente
                    no comercio de materias para constru\u00E7\u00F5es e alimentos, entretanto, nada impede
                    de fazer vinculos com outras casas para o bem de sua civiliza\u00E7\u00E3o, o foco da
                    Cidade \u00E9 ser extremamente avan\u00E7ado na Tecnologia de Redstone e constru\u00E7\u00F5es,
                    alem de uma grande produ\u00E7\u00E3o de comida e alimento para comercio.""")
                .setPolicy("Democracia")
                .setAlign("Neutro Bom")
                .setAlly(List.of("Zeronia"))
                .setNeutral(List.of("Frandha", "Nashor"))
                .setEnemy(List.of("Drakkaris"))
                .setObjective("Com\u00E9rcio local e internacional, crescimento da civiliza\u00E7\u00E3o e batalhas PVP")
                .setContribuition(0)
                .setPermissions(List.of());
    }

    private House frandhraBuilder(){
        return new House()
                .setId(3L)
                .setName("Monast\u00E9rio dos Frandhra")
                .setTag("[&2&lF&f]")
                .setDetails("""
                        O Monast\u00E9rio se encontra nos campos gelados da Taiga, sua bandeira simboliza
                        um grande Drag\u00E3o. Frandhra possui uma forte cren\u00E7a nos Drag\u00F5es, criaturas ancestrais
                        que dominaram o mundo com seu poder e magia, ainda hoje, eles acreditam fielmente
                        na existencia do ultimo da especie, apesar de nao saberem aonde ele se encontra,
                        por\u00E9m, com o conhecimento adquirido com o tempo, as po\u00E7\u00F5es e encantamentos foram
                        o que mais destacaram o Imp\u00E9rio, a Magia!""")
                .setPolicy("Rep\u00FAblica")
                .setAlign("Neutro Puro")
                .setAlly(List.of())
                .setNeutral(List.of("Vlarol\u00E1", "Zeronia","Nashor","Drakkaris"))
                .setEnemy(List.of(""))
                .setObjective("Com\u00E9rcio de po\u00E7\u00F5es e Encantamentos, explora\u00E7\u00E3o em larga escala e batalhas PVP")
                .setContribuition(0)
                .setPermissions(List.of());
    }
    private House nashorBuilder(){
        return new House()
                .setId(4L)
                .setName("Imp\u00E9rio de Nashor")
                .setTag("[&4&lN&r]")
                .setDetails("""
                        O Imp\u00E9rio de encontra nas grandes montanhas, seu simbolo \u00E9 uma F\u00EAnix.
                        O poderoso imp\u00E9rio de Nashor, nascidos e crescido nas batalhas, sua fonte indesejavel
                        de poder atinge todos, e se precisar lutar e matar para conseguir seus objetivos,
                        esse ser\u00E1 seu meio, com pouco objetivo de comercio e foco principal em domina\u00E7\u00E3o
                        atraves de combate, transforma todos os seus membros em verdadeiras maquinas de guerra!""")
                .setPolicy("Imperialista")
                .setAlign("Mal e Leal")
                .setAlly(List.of("Drakkaris"))
                .setNeutral(List.of("Vlarol\u00E1", "Frandha"))
                .setEnemy(List.of("Zeronia"))
                .setObjective("Explora\u00E7\u00E3o em larga escala e combates PVP")
                .setContribuition(0)
                .setPermissions(List.of());
    }

    private House drakkarisBuilder(){
        return new House()
                .setId(5L)
                .setName("Guarni\u00E7\u00E3o Drakkaris")
                .setTag("[&8&lD&r]")
                .setDetails("""
                        Os Drakkaris se encontram nos Pantanos, seu simbolo j\u00E1 diz por si s\u00F3
                        o que eles gostam de fazer. Os Drakkaris s\u00E3o extremamente individualistas, n\u00E3o
                        se importanto com quem esta ao seu lado ou quem est\u00E1 do outro lado, nem se v\u00E3o
                        parar na pris\u00F5es ou executados, um bando de piratas aonde o saque e a ca\u00E7a s\u00E3o
                        sua principal fonte de renda, apesar de n\u00E3o possuirem comercio e nem uma larga
                        constru\u00E7\u00E3o como as casas rivais, eles s\u00E3o especialistas na espionagem, na ca\u00E7a,
                        e no saque, sendo contratados para fazer suas fun\u00E7\u00F5es, mesmo que seu alvo seja
                        membro da propria Guilda""")
                .setPolicy("Anarquia")
                .setAlign("Caotico Mal")
                .setAlly(List.of("Nashor"))
                .setNeutral(List.of("Frandha"))
                .setEnemy(List.of("Vlarol\u00E1","Zeronia"))
                .setObjective("Saques de pessoas desprevinidas, Assasinatos por contrato e Batalhas PVP")
                .setContribuition(0)
                .setPermissions(List.of());
    }

}
