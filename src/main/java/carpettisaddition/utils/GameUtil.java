package carpettisaddition.utils;

import carpettisaddition.CarpetTISAdditionServer;
import net.minecraft.world.World;

import java.util.Objects;

public class GameUtil
{
	public static long getGameTime()
	{
		return Objects.requireNonNull(CarpetTISAdditionServer.minecraft_server.getWorld(World.OVERWORLD)).getTime();
	}

	public boolean isOnServerThread()
	{
		return CarpetTISAdditionServer.minecraft_server != null && CarpetTISAdditionServer.minecraft_server.isOnThread();
	}
}
