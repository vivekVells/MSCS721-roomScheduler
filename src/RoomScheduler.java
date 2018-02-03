package com.marist.mscs721;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomScheduler {
	protected static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		Boolean end = false;
		ArrayList<Room> rooms = new ArrayList<Room>();

		while (!end) {
			switch (mainMenu()) {

			case 1:
				System.out.println(addRoom(rooms));
				break;
			case 2:
				System.out.println(removeRoom(rooms));
				break;
			case 3:
				System.out.print(scheduleRoom(rooms));
				break;
			case 4:
				System.out.println(listSchedule(rooms));
				break;
			case 5:
				System.out.println(listRooms(rooms));
				break;
			}

		}

	}

	protected static String listSchedule(ArrayList<Room> roomList) {
		String roomName = getRoomName();
		System.out.println(roomName + " Schedule");
		System.out.println("---------------------");
		
		for (Meeting m : getRoomFromName(roomList, roomName).getMeetings()) {
			System.out.println(m.toString());
		}

		return "";
	}

	protected static int mainMenu() {
		System.out.println("Main Menu:");
		System.out.println("  1 - Add a room");
		System.out.println("  2 - Remove a room");
		System.out.println("  3 - Schedule a room");
		System.out.println("  4 - List Schedule");
		System.out.println("  5 - List Rooms");
		System.out.println("Enter your selection: ");

		return keyboard.nextInt();
	}

	protected static String addRoom(ArrayList<Room> roomList) {
		System.out.println("Add a room:");
		String name = getRoomName();
		System.out.println("Room capacity?");
		int capacity = keyboard.nextInt();

		Room newRoom = new Room(name, capacity);
		roomList.add(newRoom);

		return "Room '" + newRoom.getName() + "' added successfully!";
	}

	protected static String removeRoom(ArrayList<Room> roomList) {
		System.out.println("Remove a room:");
		roomList.remove(findRoomIndex(roomList, getRoomName()));

		return "Room removed successfully!";
	}

	protected static String listRooms(ArrayList<Room> roomList) {
		System.out.println("Room Name - Capacity");
		System.out.println("---------------------");

		for (Room room : roomList) {
			System.out.println(room.getName() + " - " + room.getCapacity());
		}

		System.out.println("---------------------");

		return roomList.size() + " Room(s)";
	}

	protected static String scheduleRoom(ArrayList<Room> roomList) {
		System.out.println("Schedule a room:");
		String name = getRoomName();

		System.out.println("Start Date? (yyyy-mm-dd):");
		String startDate = keyboard.next();
		System.out.println("Start Time?");
		String startTime = keyboard.next();
		startTime = startTime + ":00.0";

		System.out.println("End Date? (yyyy-mm-dd):");
		String endDate = keyboard.next();
		System.out.println("End Time?");
		String endTime = keyboard.next();
		endTime = endTime + ":00.0";

		Timestamp startTimestamp = Timestamp.valueOf(startDate + " " + startTime);
		Timestamp endTimestamp = Timestamp.valueOf(endDate + " " + endTime);

		System.out.println("Subject?");
		String subject = keyboard.nextLine();

		Room curRoom = getRoomFromName(roomList, name);

		Meeting meeting = new Meeting(startTimestamp, endTimestamp, subject);

		curRoom.addMeeting(meeting);

		return "Successfully scheduled meeting!";
	}

	protected static Room getRoomFromName(ArrayList<Room> roomList, String name) {
		return roomList.get(findRoomIndex(roomList, name));
	}

	protected static int findRoomIndex(ArrayList<Room> roomList, String roomName) {
		int roomIndex = 0;

		for (Room room : roomList) {
			if (room.getName().compareTo(roomName) == 0) {
				break;
			}
			roomIndex++;
		}

		return roomIndex;
	}

	protected static String getRoomName() {
		System.out.println("Room Name?");
		return keyboard.next();
	}

}
