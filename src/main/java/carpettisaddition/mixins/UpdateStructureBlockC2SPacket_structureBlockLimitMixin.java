package carpettisaddition.mixins;

import carpettisaddition.CarpetTISAdditionSettings;
import net.minecraft.server.network.packet.UpdateStructureBlockC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin(UpdateStructureBlockC2SPacket.class)
public abstract class UpdateStructureBlockC2SPacket_structureBlockLimitMixin
{
	@ModifyConstant(method = "read", require = 3, constant = @Constant(intValue = -32))
	private int structureBlockLimitNegative(int value)
	{
		return -CarpetTISAdditionSettings.structureBlockLimit;
	}

	@ModifyConstant(method = "read", require = 6, constant = @Constant(intValue = 32))
	private int structureBlockLimitPositive(int value)
	{
		return CarpetTISAdditionSettings.structureBlockLimit;
	}
}