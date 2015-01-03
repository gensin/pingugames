package org.pingus.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.pingus.backass.AssGame;
import org.pingus.utils.Constants;

public class Room {

	private final String roomId;
	private final int capacity;
	private final Set<Player> registeredPlayers;
	private AssGame roomGame = null;

	public Room(String roomId, int capacity) {
		this.roomId = roomId;
		this.capacity = capacity;
		this.registeredPlayers = new HashSet<>();
	}

	/**
	 *
	 * @param playerId
	 *            the player wishing to join this room
	 * @return true iff the player successfully joined the room
	 */
	public boolean registerPlayer(String playerId) {
		if (registeredPlayers.size() == capacity) {
			return false;
		}

		// TODO Recuperar nombres de la base de datos
		registeredPlayers.add(new Player("name", Integer.parseInt(playerId)));

		if (registeredPlayers.size() == capacity) {
			int[] arrayOfPositions = new int[4];
			arrayOfPositions[Constants.ASS] = registeredPlayers.size() - 1;
			arrayOfPositions[Constants.VICEASS] = registeredPlayers.size();
			arrayOfPositions[Constants.VICEPRESIDENT] = 1;
			arrayOfPositions[Constants.PRESIDENT] = 0;
			this.roomGame = new AssGame((Player[]) registeredPlayers.toArray(),
					arrayOfPositions);
		}

		return true;
	}

	public void unregisterPlayer(String playerId) {
		// TODO notify front end to update numbers of this room
		registeredPlayers.remove(playerId);
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean isFull() {
		return capacity == registeredPlayers.size();
	}

	public boolean hasPlayer(String playerId) {
		return registeredPlayers.contains(playerId);
	}

	public RoomInformation getStatus() {
		return new RoomInformation(capacity, registeredPlayers.size(), roomId);
	}

	public String getRoomId() {
		return roomId;
	}

	public Set<Player> getRegisteredPlayers() {
		return registeredPlayers;
	}

	public AssGame getRoomGame() {
		return roomGame;
	}
}
