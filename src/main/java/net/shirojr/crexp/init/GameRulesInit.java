package net.shirojr.crexp.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public interface GameRulesInit {
    GameRules.Key<GameRules.IntRule> DAY_NIGHT_LIGHT_CHECK_INTERVAL = GameRuleRegistry.register(
            "day_night_light_check_interval",
            GameRules.Category.PLAYER,
            GameRuleFactory.createIntRule(80, 1)
    );


    static void initialize() {
        // static initialisation
    }
}
