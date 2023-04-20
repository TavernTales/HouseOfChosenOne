package com.alphadev.utils;

import com.alphadev.enums.QuestTierEnum;
import com.alphadev.enums.QuestTypeEnum;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class HelpUtils {

    private HelpUtils(){}
    // Constantes para cálculos e listas pré-definidas
    private static final int MAX_COUNT_REQUIRED = 10;
    private static final int MAX_CONTRIBUTION_POINTS = 50;
    private static final double MAX_VAULT = 100.0;
    public static final List<String> HOUSES = List.of("zeronia", "vlarola", "frandhra", "nashor", "drakkaris");

    // Objeto randomizador para uso nas funções seguintes
    private static final Random RAND = new Random();
    // Gerador de porcentagem

    public static double sortPercent() {
        return Math.random() * 100;
    }
    // Gera o tipo de quest

    public static QuestTypeEnum getRandomQuestType() {
        QuestTypeEnum[] questTypeEnums = QuestTypeEnum.values();
        return questTypeEnums[RAND.nextInt(questTypeEnums.length)];
    }
    public static String setPageBook(int type){
        String page;
        switch (type){
            case 1:
                page = "A Guerra contra o Mal \nDescricao: Enfrente as criaturas mais terriveis do mundo. Elimine hordas de monstros que ameacam a paz. Use armas, magias e estrategias. Ganhe experiencia, itens e respeito.";
                return page;
            case 2:
                page = "O Desafio Supremo \nDescricao: Lute contra outros jogadores em uma arena mortal. Ganhe recompensas e gloria. Cuidado com os traidores.";
                return page;
            case 3:
                page = "A Busca pela Presa \nDescricao: Rastreie e atire em animais raros e perigosos. Capture ou mate suas presas e ganhe trofeus e recursos. Mas cuidado: voce tambem pode ser cacado.";
                return page;
            case 5:
                page = "A Arte da Colheita \nDescricao: Plante e cuide de suas culturas. Crie alimentos deliciosos e uteis. Mas atencao: nem todas as plantas sao amigaveis.";
                return page;
            default:
                page = "Ops";
                return page;
        }
    }
    // Gera o quantidade necessária para para concluir a missão
    public static int getRandomCountRequired() {
        return (int) (Math.random() * MAX_COUNT_REQUIRED) + 1;
    }


    // Gera o total de recompensa
    public static int getRandomContributionPoints() {
        return (int) (Math.random() * MAX_CONTRIBUTION_POINTS) + 1;
    }

    public static double getRandomVault() {
        return Math.round((Math.random() * MAX_VAULT) * 100.0) / 100.0;
    }
    // Cria uma tier para uma quest conforme o sortPercent()
    public static QuestTierEnum getRandomQuestTier() {
        QuestTierEnum[] questTierEnums = QuestTierEnum.values();
        int percent = (int) sortPercent();
        if (percent <= 60) return questTierEnums[0];
        if (percent <= 85) return questTierEnums[1];
        if (percent <= 95) return questTierEnums[2];
        return questTierEnums[3];
    }
    // Gera uma entidade para o tier da quest
    public static EntityType generateEntityObjective(int tier, int type) {
        if (type == 1) {
            switch (tier) {
                case 1:
                    return EntityType.valueOf(COMMON_DEFEAT_OPTIONS.get(RAND.nextInt(COMMON_DEFEAT_OPTIONS.size())));
                case 2:
                    return EntityType.valueOf(UNCOMMON_DEFEAT_OPTIONS.get(RAND.nextInt(UNCOMMON_DEFEAT_OPTIONS.size())));
                case 3:
                    return EntityType.valueOf(RARE_DEFEAT_OPTIONS.get(RAND.nextInt(RARE_DEFEAT_OPTIONS.size())));
                case 4:
                    return EntityType.valueOf(LEGENDARY_DEFEAT_OPTIONS.get(RAND.nextInt(LEGENDARY_DEFEAT_OPTIONS.size())));
                default:
                    break;
            }
        }
        if (type == 3) {
            switch (tier) {
                case 1:
                    return EntityType.valueOf(COMMON_HUNTER_OPTIONS.get(RAND.nextInt(COMMON_HUNTER_OPTIONS.size())));
                case 2:
                    return EntityType.valueOf(UNCOMMON_HUNTER_OPTIONS.get(RAND.nextInt(UNCOMMON_HUNTER_OPTIONS.size())));
                case 3:
                    return EntityType.valueOf(RARE_HUNTER_OPTIONS.get(RAND.nextInt(RARE_HUNTER_OPTIONS.size())));
                case 4:
                    return EntityType.valueOf(LEGENDARY_HUNTER_OPTIONS.get(RAND.nextInt(LEGENDARY_HUNTER_OPTIONS.size())));
                default:
                    break;
            }
        }
        return EntityType.PLAYER;
    }

    public static ItemStack generateItemObjective(int type, int tier) {
        switch (tier) {
            case 1:
                return new ItemStack(Material.valueOf(COMMON_HARVEST_OPTIONS.get(RAND.nextInt(COMMON_HARVEST_OPTIONS.size()))));
            case 2:
                return new ItemStack(Material.valueOf(UNCOMMON_HARVEST_OPTIONS.get(RAND.nextInt(UNCOMMON_HARVEST_OPTIONS.size()))));
            case 3:
                return new ItemStack(Material.valueOf(RARE_HARVEST_OPTIONS.get(RAND.nextInt(RARE_HARVEST_OPTIONS.size()))));
            case 4:
                return new ItemStack(Material.valueOf(LEGENDARY_HARVEST_OPTIONS.get(RAND.nextInt(LEGENDARY_HARVEST_OPTIONS.size()))));
            default:
                break;
        }
        return new ItemStack(Material.FROSTED_ICE);
    }

    public static boolean isNullOrEmpty(Collection<?> collections){
        return  collections == null || collections.isEmpty();
    }

    // TODAS AS CONFIGURAÇÕES DAS QUESTS
    private static final List<String> COMMON_HUNTER_OPTIONS = Arrays.asList(
            "COW", "PIG", "SHEEP", "CHICKEN", "COD");
    private static final List<String> COMMON_HARVEST_OPTIONS = Arrays.asList(
            "WHEAT", "CARROT", "SUGAR_CANE", "POTATOES", "BEETROOTS");
    private static final List<String> COMMON_DEFEAT_OPTIONS = Arrays.asList(
            "SKELETON", "CREEPER", "SLIME", "ENDERMAN", "DROWNED", "HUSK", "PHANTOM", "PILLAGER", "SPIDER", "STRAY", "ZOMBIE_VILLAGER");

    private static final List<String> UNCOMMON_HUNTER_OPTIONS = Arrays.asList(
            "GOAT", "RABBIT", "SQUID", "SALOM", "WOLF");
    private static final List<String> UNCOMMON_HARVEST_OPTIONS = Arrays.asList(
            "SWEET_BERRY_BUSH", "MELON", "PUMPKIN", "CACTUS", "KELP_PLANT", "BROWN_MUSHROOM", "RED_MUSHROOM");
    private static final List<String> UNCOMMON_DEFEAT_OPTIONS = Arrays.asList(
            "BLAZE", "MAGMA_CUBE", "PIGLIN_BRUTE", "ENDERMITE", "GHAST", "HOGLIN", "ZOMBFIED_PIGLIN", "ZOGLIN", "WITHER_SKELNTON", "WITCH", "CAVE_SPIDER");

    private static final List<String> RARE_HUNTER_OPTIONS = Arrays.asList(
            "GLOW_SQUID", "AXOLOTL", "PANDA", "PARROT", "FOX", "OCELOT");
    private static final List<String> RARE_HARVEST_OPTIONS = Arrays.asList(
            "SEA_PICKLE", "NETHER_WART", "CRIMSON_FUNGUS", "BAMBOO");
    private static final List<String> RARE_DEFEAT_OPTIONS = Arrays.asList(
            "ENDERMITE", "EVOKER", "GUARDIAN", "PILLAGER", "SHULKER", "VEX", "VINDICATOR");

    private static final List<String> LEGENDARY_HUNTER_OPTIONS = Arrays.asList(
            "MUSHROOM_COW", "FROG", "TURTLE", "POLAR_BEAR", "DOLPHIN", "TROPICAL_FISH", "PUFFERFISH");
    private static final List<String> LEGENDARY_HARVEST_OPTIONS = Arrays.asList(
            "CHORUS_PLANT", "SPORE_BLOSSOM");
    private static final List<String> LEGENDARY_DEFEAT_OPTIONS = Arrays.asList(
            "ELDER_GUARDIAN", "ENDER_DRAGON", "WITHER", "WARDEN", "ILLUSIONER");

}
