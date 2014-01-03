package com.caved_in.worldmanager.worlds;

import com.caved_in.commons.world.WorldHandler;
import com.caved_in.worldmanager.WorldManager;
import org.bukkit.Location;
import org.bukkit.World;

public class ManagedWorld {
	private String worldName;
	private boolean loadOnStart = false;
	private Location worldSpawn;
	private boolean allowEntities = true;

	public ManagedWorld(String worldName) {
		this.worldName = worldName;
		this.worldSpawn = WorldManager.getWorldSpawn(worldName);
	}

	public Location getWorldSpawn() {
		return worldSpawn;
	}

	public void setWorldSpawn(Location worldSpawn) {
		this.worldSpawn = worldSpawn;
		WorldHandler.setSpawn(worldSpawn.getWorld(),worldSpawn);
	}

	public String getWorldName() {
		return worldName;
	}

	public World getWorld() {
		return worldSpawn.getWorld();
	}

	public boolean isAllowEntities() {
		return allowEntities;
	}

	public void setAllowEntities(boolean allowEntities) {
		this.allowEntities = allowEntities;
	}

	public boolean isLoadOnStart() {
		return loadOnStart;
	}

	public void setLoadOnStart(boolean loadOnStart) {
		this.loadOnStart = loadOnStart;
	}
}
