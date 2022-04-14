package carpettisaddition.mixins.logger.poi;

import carpettisaddition.logging.TISAdditionLoggerRegistry;
import carpettisaddition.logging.loggers.poi.PoiLogger;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.FindPointOfInterestTask;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.poi.PointOfInterestStorage;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(FindPointOfInterestTask.class)
public class FindPointOfInterestTaskMixin {

	@Shadow private long positionExpireTimeLimit;

	@ModifyVariable(
			method = "run(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/mob/PathAwareEntity;J)V",
			at = @At("STORE"),
			ordinal = 1,
			print = true
	)
	private Path onPathCreate(Path path){
		System.out.println("pathing");
		PoiLogger.getInstance().setPath(path);
		return path;
	}
	@ModifyVariable(
			method = "run(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/mob/PathAwareEntity;J)V",
			at = @At("STORE"),
			ordinal = 1,
			print = true
	)
	private Set<BlockPos> onBlockSelected(Set<BlockPos> blockPosSet){
		System.out.println("seraching");
		PoiLogger.getInstance().setBlockPosSet(blockPosSet);
		return blockPosSet;
	}


	@Inject(
			method = "run(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/mob/PathAwareEntity;J)V",
			at = @At("RETURN")
	)
	 void run(ServerWorld world, PathAwareEntity entity, long time, CallbackInfo ci) {
		System.out.println("r");
		PoiLogger.getInstance().OnRun(world, entity, time, this.positionExpireTimeLimit);
	}
}


