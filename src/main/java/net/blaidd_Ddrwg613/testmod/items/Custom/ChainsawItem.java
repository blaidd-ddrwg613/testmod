package net.blaidd_Ddrwg613.testmod.items.Custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ChainsawItem extends Item
{
    public ChainsawItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        Player player = context.getPlayer();


        if (!level.isClientSide())
        {
            BlockPos blockPos = context.getClickedPos();
            if (level.getBlockState(blockPos).is(BlockTags.LOGS))
            {
                // Destroy and drop the block
                level.destroyBlock(blockPos,true, player);

                // Damage Item held in hand
                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) player),
                        item -> player.onEquippedItemBroken(context.getItemInHand().getItem(), EquipmentSlot.MAINHAND));

            }
        }
        return InteractionResult.CONSUME;
    }
}
