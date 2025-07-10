package net.blaidd_Ddrwg613.testmod.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties
{
    public static final FoodProperties TOMATO = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.25f)
            .alwaysEdible()
            .fast()
            .effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST), 0.65f).build();
}
