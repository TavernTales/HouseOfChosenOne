package com.alphadev.utils.config.mongo;

import com.alphadev.entity.House;
import com.alphadev.repository.HouseRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HouseConfigurationBuilder {

    private final HouseRepository repository = new HouseRepository();
    public HouseConfigurationBuilder() {
        if (repository.isEmpty()) populateHouseDatabase();
    }
    private void populateHouseDatabase() {
        repository.saveAll(setHousesRelationship());
    }
    private House zeroniaBuilder(){
        return new House()
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
                .setObjective("Comércio local e internacional, crescimento da civilização e batalhas PVP")
                .setContribution(0)
                .setPermissions(List.of());
    }
    private House vlarolaBuilder(){
        return new House()
                .setName("Cidade de Vlarolá")
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
                .setObjective("Com\u00E9rcio local e internacional, crescimento da civiliza\u00E7\u00E3o e batalhas PVP")
                .setContribution(0)
                .setPermissions(List.of());
    }

    private House frandhraBuilder(){
        return new House()
                .setName("Monastério dos Frandhra")
                .setTag("[&2&lF&f]")
                .setDetails("""
                        O Monast\u00E9rio se encontra nos campos gelados da Taiga, sua bandeira simboliza
                        um grande Drag\u00E3o. Frandhra possui uma forte cren\u00E7a nos Drag\u00F5es, criaturas ancestrais
                        que dominaram o mundo com seu poder e magia, ainda hoje, eles acreditam fielmente
                        na existencia do ultimo da especie, apesar de nao saberem aonde ele se encontra,
                        por\u00E9m, com o conhecimento adquirido com o tempo, as po\u00E7\u00F5es e encantamentos foram
                        o que mais destacaram o Imp\u00E9rio, a Magia!""")
                .setPolicy("República")
                .setAlign("Neutro Puro")
                .setObjective("Com\u00E9rcio de po\u00E7\u00F5es e Encantamentos, explora\u00E7\u00E3o em larga escala e batalhas PVP")
                .setContribution(0)
                .setPermissions(List.of());
    }
    private House nashorBuilder(){
        return new House()
                .setName("Império de Nashor")
                .setTag("[&4&lN&r]")
                .setDetails("""
                        O Imp\u00E9rio de encontra nas grandes montanhas, seu simbolo \u00E9 uma F\u00EAnix.
                        O poderoso imp\u00E9rio de Nashor, nascidos e crescido nas batalhas, sua fonte indesejavel
                        de poder atinge todos, e se precisar lutar e matar para conseguir seus objetivos,
                        esse ser\u00E1 seu meio, com pouco objetivo de comercio e foco principal em domina\u00E7\u00E3o
                        atraves de combate, transforma todos os seus membros em verdadeiras maquinas de guerra!""")
                .setPolicy("Imperialista")
                .setAlign("Mal e Leal")
                .setObjective("Explora\u00E7\u00E3o em larga escala e combates PVP")
                .setContribution(0)
                .setPermissions(List.of());
    }

    private House drakkarisBuilder(){
        return new House()
                .setName("Guarnição Drakkaris")
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
                .setObjective("Saques de pessoas desprevinidas, Assasinatos por contrato e Batalhas PVP")
                .setContribution(0)
                .setPermissions(List.of());
    }
    private List<House> setHousesRelationship(){
        // Build das entidades "houses"
        House zeronia = zeroniaBuilder();
        House vlarola = vlarolaBuilder();
        House frandhra = frandhraBuilder();
        House nashor = nashorBuilder();
        House drakkaris = drakkarisBuilder();
        // Relationship de zeronia
        zeronia.setAlly(Collections.singletonList(vlarola));
        zeronia.setNeutral(Collections.singletonList(frandhra));
        zeronia.setEnemy(Arrays.asList(nashor,drakkaris));
        // Relationship de zeronia
        vlarola.setAlly(Collections.singletonList(zeronia));
        vlarola.setNeutral(Arrays.asList(frandhra,nashor));
        vlarola.setEnemy(Collections.singletonList(drakkaris));
        // Relationship de frandhra
        frandhra.setAlly(List.of());
        frandhra.setNeutral(Arrays.asList(vlarola,zeronia,nashor,drakkaris));
        frandhra.setEnemy(List.of());
        // Relationship de nashor
        nashor.setAlly(Collections.singletonList(drakkaris));
        nashor.setNeutral(Arrays.asList(vlarola,frandhra));
        nashor.setEnemy(Collections.singletonList(zeronia));
        // Relationship de drakkaris
        drakkaris.setAlly(Collections.singletonList(nashor));
        drakkaris.setNeutral(Collections.singletonList(frandhra));
        drakkaris.setEnemy(Arrays.asList(vlarola,zeronia));
        // Adiciona as casas na lista
        return Arrays.asList(zeronia,vlarola,frandhra,nashor,drakkaris);
    }
}
