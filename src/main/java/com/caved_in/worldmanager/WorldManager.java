package com.caved_in.worldmanager;

import com.caved_in.commons.Commons;
import com.caved_in.commons.threading.RunnableManager;
import com.caved_in.worldmanager.runnables.UnloadRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldManager extends JavaPlugin {
	public static RunnableManager threadHandler;

	@Override
	public void onEnable() {
		threadHandler = new RunnableManager(this);
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
		Bukkit.getScheduler().cancelTasks(this);
	}

	public static boolean unloadMap(String worldName) {
		if (Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(worldName), false)) {
			Commons.messageConsole(worldName + " has been unloaded");
			return true;
		} else {
			Commons.messageConsole(worldName + " has failed to unload, well fuck...");
			return false;
		}
	}

	public static Location getWorldSpawn(String worldName) {
		return Bukkit.getWorld(worldName).getSpawnLocation();
	}

	public static boolean loadMap(String worldName) {
		try {
			Bukkit.getServer().createWorld(new WorldCreator(worldName));
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static void rollbackMap(String worldName) {
		threadHandler.registerSynchRepeatTask("Rollback_" + worldName, new UnloadRunnable("Rollback_" + worldName, worldName), 20, 200);
	}

}
