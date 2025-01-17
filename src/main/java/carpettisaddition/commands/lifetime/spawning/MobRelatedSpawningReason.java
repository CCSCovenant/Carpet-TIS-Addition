package carpettisaddition.commands.lifetime.spawning;

import net.minecraft.entity.EntityType;

import java.util.Objects;

public abstract class MobRelatedSpawningReason extends SpawningReason
{
	protected final EntityType<?> entityType;

	public MobRelatedSpawningReason(EntityType<?> entityType)
	{
		this.entityType = entityType;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MobRelatedSpawningReason that = (MobRelatedSpawningReason) o;
		return Objects.equals(entityType, that.entityType);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(entityType);
	}
}
