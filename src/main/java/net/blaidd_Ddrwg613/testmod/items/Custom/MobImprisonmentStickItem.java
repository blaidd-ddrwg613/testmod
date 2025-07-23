package net.blaidd_Ddrwg613.testmod.items.Custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MobImprisonmentStickItem extends Item {
    public MobImprisonmentStickItem(Properties properties) {
        super(properties.stacksTo(1).component(DataComponents.ENTITY_DATA, CustomData.EMPTY));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        ItemStack heldItem = player.getMainHandItem();

        if (heldItem.getItem() instanceof MobImprisonmentStickItem item && interactionTarget instanceof Mob mob && interactionTarget.isAlive()) {
            if (!item.hasStoredEntity(heldItem)) {
                if (!player.level().isClientSide()) {
                    if (interactionTarget.hasCustomName()) {
                        heldItem.set(DataComponents.CUSTOM_NAME, interactionTarget.getCustomName());
                    }
                    CompoundTag tag = saveEntity(interactionTarget);
                    interactionTarget.discard();
                    heldItem.set(DataComponents.ENTITY_DATA, CustomData.of(tag));
                }
            }
        }
        return InteractionResult.CONSUME;
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (this.hasStoredEntity(context.getItemInHand())) {
            Level level = context.getLevel();
            if (level.isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                ItemStack itemInHand = context.getItemInHand();
                BlockPos blockPos = context.getClickedPos();
                Direction direction = context.getClickedFace();
                BlockState blockState = level.getBlockState(blockPos);

                BlockPos releasePos;
                if (blockState.getCollisionShape(level, blockPos).isEmpty()) {
                    releasePos = blockPos;
                } else {
                    releasePos = blockPos.relative(direction);
                }

                this.releaseContents(context.getPlayer(), level, itemInHand, blockPos, releasePos);

                return InteractionResult.CONSUME;
            }
        } else {
            return InteractionResult.PASS;
        }
    }

    public void releaseContents(@Nullable Entity entity, Level level, ItemStack itemStack, BlockPos clickedPos, BlockPos releasePos) {

        CompoundTag tag = itemStack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY).copyTag();
        if (this.releaseContentAt(tag, level, releasePos, itemStack)) {

            level.gameEvent(entity, GameEvent.ENTITY_PLACE, clickedPos);
        }

        itemStack.set(DataComponents.ENTITY_DATA, CustomData.EMPTY);
        itemStack.remove(DataComponents.CUSTOM_NAME);
    }

    private boolean releaseContentAt(CompoundTag tag, Level level, BlockPos pos, ItemStack itemStack) {
        if (!level.isClientSide && !tag.isEmpty()) {
            return EntityType.create(tag, level).map((entity) -> {

                moveEntityTo(entity, level, pos, true);
                entity.setDeltaMovement(Vec3.ZERO);

                level.addFreshEntity(entity);

                if (itemStack.has(DataComponents.CUSTOM_NAME) && entity instanceof LivingEntity) {
                    entity.setCustomName(itemStack.getHoverName());
                }
                return entity;
            }).isPresent();
        }

        return false;
    }

    public static void moveEntityTo(Entity entity, Level level, BlockPos pos, boolean shouldOffsetY) {
        double offsetY;
        if (shouldOffsetY) {
            entity.setPos((double) pos.getX() + 0.5D, pos.getY() + 1, (double) pos.getZ() + 0.5D);
            offsetY = getYOffset(level, pos, entity.getBoundingBox());
        } else {
            offsetY = 0.0D;
        }

        entity.moveTo((double) pos.getX() + 0.5D,
                (double) pos.getY() + offsetY,
                (double) pos.getZ() + 0.5D,
                Mth.wrapDegrees(level.random.nextFloat() * 360.0F),
                0.0F
        );
        if (entity instanceof Mob mob) {
            mob.yHeadRot = mob.getYRot();
            mob.yBodyRot = mob.getYRot();
            mob.playAmbientSound();
        }
    }

    private static double getYOffset(LevelReader level, BlockPos pos, AABB box) {
        AABB aabb = new AABB(pos);
        Iterable<VoxelShape> iterable = level.getCollisions(null, aabb);
        return 1.0D + Shapes.collide(Direction.Axis.Y, box, iterable, -1.0D);
    }

    public boolean hasStoredEntity(ItemStack itemStack) {
        return !itemStack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY).isEmpty();
    }

    @Nullable
    public EntityType<?> getStoredEntityType(ItemStack itemStack) {
        CustomData customData = itemStack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);
        return EntityType.by(customData.getUnsafe()).orElse(null);
    }

    public static CompoundTag saveEntity(Entity entity) {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putString("id", EntityType.getKey(entity.getType()).toString());
        entity.saveWithoutId(compoundTag);
        return compoundTag;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (context != TooltipContext.EMPTY) {
            if (this.hasStoredEntity(stack)) {
                MutableComponent component = Component.translatable("gui.entity_tooltip.type", this.getStoredEntityType(stack).getDescription());
                tooltipComponents.add(Component.translatable("gui.entity_tooltip.shift").withStyle(ChatFormatting.BLUE));
                tooltipComponents.add(component.withStyle(ChatFormatting.BLUE));
            } else {
                tooltipComponents.add(Component.translatable("gui.entity_tooltip_empty").withStyle(ChatFormatting.GOLD));
            }
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        }
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        ItemStack itemStack = itemEntity.getItem();
        if (this.hasStoredEntity(itemStack)) {
            CompoundTag storedEntity = itemStack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY).copyTag();
            this.releaseContentAt(storedEntity, itemEntity.level(), itemEntity.blockPosition(), itemStack);
        }
    }
}
