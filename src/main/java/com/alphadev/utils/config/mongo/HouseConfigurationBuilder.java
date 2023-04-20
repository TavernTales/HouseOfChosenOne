package com.alphadev.utils.config.mongo;

import com.alphadev.entity.House;
import com.alphadev.repository.HouseRepository;

import java.util.List;

public class HouseConfigurationBuilder {

    private final HouseRepository repository = new HouseRepository();
    public HouseConfigurationBuilder() {
       List.of(zeroniaBuilder(),vlarolaBuilder()).forEach(this::populateHouseDatabase);
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
                 com outras Casas, apenas da rela\u00E7\u00E3o com algumas nao serem t\u00E3o positivas quando
                 outras, entretanto, seu grande ponto forte sempre foi o comercio e a busca de
                 grandes guerreiros para lutar em nome de Zeronia""")
                .setPolicy("Monarquia")
                .setAlign("Bom e Leal")
                .setAlly(List.of("Vlarolá"))
                .setNeutral(List.of("Frandha"))
                .setEnemy(List.of("Nashor", "Drakkaris"))
                .setObjective("Comércio local e internacional, crescimento da civilização e batalhas PVP")
                .setContribuition(0)
                .setPermissions(List.of());

    }


    private House vlarolaBuilder(){
        return new House()
                .setId(2L)
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
                .setAlly(List.of("Zeronia"))
                .setNeutral(List.of("Frandha", "Nashor"))
                .setEnemy(List.of("Drakkaris"))
                .setObjective("Comércio local e internacional, crescimento da civilização e batalhas PVP")
                .setContribuition(0)
                .setPermissions(List.of());
    }




}
