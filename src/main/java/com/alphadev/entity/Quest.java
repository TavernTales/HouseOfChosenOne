package com.alphadev.entity;

import com.alphadev.enums.QuestTierEnum;
import com.alphadev.enums.QuestTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Entity
@Getter
@Setter
public class Quest {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private Integer contibuitionPoints;
    private List<ItemStack> itensRequired;
    private List<EntityType> mobsRequired;

    private QuestTypeEnum questTypeEnum;
    private QuestTierEnum questTierEnum;

    private Double vault;
    private Integer countRequired;
    private List<Player> playersInProgress;
    private House house;
    private Long currentTime;
}
