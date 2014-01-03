package com.caved_in.worldmanager.runnables;

import com.caved_in.commons.Commons;
import com.caved_in.worldmanager.WorldManager;
import org.bukkit.Bukkit;

public class UnloadRunnable implements Runnable {
	private String taskName = "";
	private String mapName = "";

	public UnloadRunnable(String Task, String Map) {
		this.taskName = Task;
		this.mapName = Map;
	}

	@Override
	public void run() {
		if (WorldManager.unloadMap(mapName)) {
			if (WorldManager.loadMap(mapName)) {
				Commons.messageConsole("Map [" + mapName + "] has been rolled back");
				this.cancelTask();
			} else {
				Commons.messageConsole("Map [" + mapName + "] Has unloaded, but failed to re-load; Attempting again");
			}
		} else {
			Commons.messageConsole("Map [" + mapName + "] has failed to unload, trying until resolved");
		}
	}

	public void cancelTask() {
		WorldManager.threadHandler.cancelTask(taskName);
	}

}
