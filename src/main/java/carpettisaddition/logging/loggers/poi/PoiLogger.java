package carpettisaddition.logging.loggers.poi;

import carpet.logging.LoggerRegistry;
import carpettisaddition.logging.TISAdditionLoggerRegistry;
import carpettisaddition.logging.loggers.AbstractLogger;
import carpettisaddition.utils.Messenger;
import net.minecraft.entity.ai.brain.task.FindPointOfInterestTask;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.BaseText;
import net.minecraft.util.math.BlockPos;

import java.util.Set;

public class PoiLogger extends AbstractLogger {
	public static final String NAME = "poi";
	private static final PoiLogger instance = new PoiLogger();
	private Set<BlockPos> blockPosSet;
	private Path path;

	public PoiLogger() {
		super(NAME);
	}

	public static PoiLogger getInstance() {
		return instance;
	}

	public String OnSearch (){
		if (!TISAdditionLoggerRegistry.__poi){
			return null;
		}
		if (blockPosSet==null){
			return "";
		}

		StringBuilder out = new StringBuilder();
		out.append("SeachBlocks:");
		for (BlockPos blockPos:blockPosSet){
			out.append("["+blockPos.toShortString()+"]");
		}
		return out.toString();
	}
	public String OnPathing(){
		if (!TISAdditionLoggerRegistry.__poi){
			return null;
		}
		if (path==null){
			return "";
		}

		StringBuilder out = new StringBuilder();
		out.append("Pathing:");
		for (BlockPos blockPos:blockPosSet){
			out.append("["+blockPos.toShortString()+"]");
		}
		return out.toString();
	}

	public void setBlockPosSet(Set<BlockPos> blockPosSet) {
		System.out.println("seraching");
		this.blockPosSet = blockPosSet;
	}

	public void setPath(Path path) {
		System.out.println("pathing");
		this.path = path;
	}
	public void OnRun(ServerWorld serverWorld, PathAwareEntity pathAwareEntity, long init,long next){
		if (!TISAdditionLoggerRegistry.__poi){
			return;
		}
		System.out.println("running");
		StringBuffer output = new StringBuffer();
		output.append("Villager:["+pathAwareEntity.getUuid()+"] is searching Poi in["+init+"]\n");
		output.append("Next searching is in ["+next+"]\n");
		output.append(OnSearch()+"\n");
		output.append(OnPathing()+"\n");

		LoggerRegistry.getLogger(NAME).log(()-> {
			return new BaseText[]{
			Messenger.s(output.toString()
			)};
		});

	}
}