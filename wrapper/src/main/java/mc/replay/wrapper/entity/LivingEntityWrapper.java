package mc.replay.wrapper.entity;

import mc.replay.wrapper.entity.metadata.LivingEntityMetadata;
import mc.replay.wrapper.item.ItemWrapper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LivingEntityWrapper extends EntityWrapper {

    protected final Map<EquipmentSlot, ItemWrapper> equipment = new HashMap<>();

    public LivingEntityWrapper(@NotNull EntityType entityType, int entityId, @NotNull UUID uuid) {
        super(entityType, entityId, uuid);
    }

    public LivingEntityWrapper(@NotNull EntityType entityType, @NotNull UUID uuid) {
        super(entityType, uuid);
    }

    public LivingEntityWrapper(@NotNull LivingEntity entity) {
        super(entity);

        if (entity.getEquipment() != null) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ItemStack item = entity.getEquipment().getItem(slot);
                if (item == null || item.getType().isAir()) continue;

                this.equipment.put(slot, new ItemWrapper(item));
            }
        }
    }

    public @Nullable ItemWrapper getEquipment(@NotNull EquipmentSlot slot) {
        return this.equipment.get(slot);
    }

    public @NotNull Map<EquipmentSlot, ItemWrapper> getEquipment() {
        return this.equipment;
    }

    public void setEquipment(@NotNull EquipmentSlot slot, @NotNull ItemWrapper item) {
        this.equipment.put(slot, item);
    }

    @Override
    public @NotNull LivingEntityMetadata getMetadata() {
        return this.getMetaData(LivingEntityMetadata.class);
    }

    @Override
    public @NotNull LivingEntityWrapper withUniqueId() {
        return (LivingEntityWrapper) super.withUniqueId();
    }
}