package org.pingus.model;

import java.util.HashSet;
import java.util.Set;

import org.pingus.backass.AssGame;
import org.pingus.utils.Constants;

public class Room {

	private final String roomId;
	private final int capacity;
	private final Set<String> registeredPlayers;
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

		registeredPlayers.add(playerId);

		if (registeredPlayers.size() == capacity) {
			int[] arrayOfPositions = new int[4];
			arrayOfPositions[Constants.ASS] = registeredPlayers.size() - 1;
			arrayOfPositions[Constants.VICEASS] = registeredPlayers.size() - 2;
			arrayOfPositions[Constants.VICEPRESIDENT] = 1;
			arrayOfPositions[Constants.PRESIDENT] = 0;
			Player[] players = new Player[registeredPlayers.size()];

			int index = 0;
			for (String registeredPlayer : registeredPlayers) {
				// TODO Recuperar nombres de la base de datos
				players[index++] = new Player("name", Integer.parseInt(registeredPlayer));
			}

			this.roomGame = new AssGame(players, arrayOfPositions);
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

	public Set<String> getRegisteredPlayers() {
		return registeredPlayers;
	}

	public AssGame getRoomGame() {
		return roomGame;
	}
}
