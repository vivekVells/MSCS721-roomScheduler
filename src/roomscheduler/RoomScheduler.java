/**
 * @author <a href="https://github.com/vivekVells">Vivek Vellaiyappan Surulimuthu</a>
 * @version 1.8
 */



/**
 * To contain all room schedule activities 
 */
package roomscheduler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to schedule a room
 */
public class RoomScheduler {
	protected static Scanner keyboard = new Scanner(System.in);

	/**
	 * main function to execute the program
	 */
	public static void main(String[] args) {
		Boolean end = false;
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		while (!end) {
			switch (mainMenu()) {
				case 1:
					addRoom(rooms);
					break;
				case 2:
					removeRoom(rooms);
					break;
				case 3:
					scheduleRoom(rooms);
					break;
				case 4:
					listSchedule(rooms);
					break;
				case 5:
					listRooms(rooms);
					break;
				case 6:
					System.out.println("Quitting the program...");
					System.exit(0);
				default:
					System.out.println("Invalid choice selection. Input only number of choice appropriately. Redirecting to Room Scheduler Home Page Menu....");
					utility.Utility.sleepFor(2000);
					mainMenu();
			}
		}
	}
	
	public static void roomSchedulerBanner() {
		System.out.println("********************************************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t\tROOM SCHEDULER PAGE");
		System.out.println("********************************************************************************************************************************************************");
		System.out.println();
	}

	/**
	 * returns user choice and then can be used to call appropriate function
	 * @return user choice selected number
	 */
	protected static int mainMenu() {
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		
		System.out.println("\nMain Menu:");
		System.out.println("  1 - Add a room");
		System.out.println("  2 - Remove a room");
		System.out.println("  3 - Schedule a room");
		System.out.println("  4 - List Schedule");
		System.out.println("  5 - List Rooms");
		System.out.println("  6 - Quit");
		System.out.print("Enter your selection: ");
		if(keyboard.hasNextInt()) {
			return keyboard.nextInt();
		} else {
				try {
					System.out.println("Invalid choice selection. Input only number of choice appropriately. Redirecting to Room Scheduler Home Page Menu....");
					keyboard.next();
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return mainMenu();
		}
	}

	/**
	 * addRoom To add room with its capacity
	 * 
	 * @param roomList List that holds created room objects
	 * @return Room creation with its available capacity
	 */
	protected static void addRoom(ArrayList<Room> roomList) {
		
		String name = "";
		int capacity = 0;
		Room newRoom = null;
		
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		System.out.println("\t\t\t\t\t\t\t\tADD ROOM PAGE\n");
		
		System.out.print("Room Name: ");
		name = getRoomName();
		
		if (roomList.size() == 0 || !isRoomExists(roomList, name)) {
			System.out.print("Room capacity: ");
			
			if (keyboard.hasNextInt()) {
				capacity = keyboard.nextInt();
				
				// Q: why should not this capacity condition be handled in Room.java file since it holds all info related to room. 
				// RoomScheduler.java file here only helps to schedule. Hmmmmmmmmmmm.....
				
				if (capacity <=0 || capacity >10) {
					System.out.println("Maximum allowable room capacity is 10. \nRedirecting to main menu...");
					utility.Utility.sleepFor(2000);
				} else {
					newRoom = new Room(name, capacity);
					roomList.add(newRoom);
					System.out.println("Room '" + newRoom.getName() + "' is added successfully!");
					utility.Utility.sleepFor(2000);
				}
			} else {
				System.out.println("Input only capacity in numbers...\nRedirecting to main menu...");
				utility.Utility.sleepFor(2000);
			}
			System.out.println("findRoomIndex: " + findRoomIndex(roomList, name));
		} else {
				System.out.println("Room ALREADY EXISTS...");
				utility.Utility.sleepFor(3000);
				System.out.println("findRoomIndex: " + findRoomIndex(roomList, name));
		}
	}
	
	/**
	 * isRoomExists			Find whether the given room already exists
	 * 
	 * @param roomList
	 * @param roomName
	 * @return		returns true if given arg roomName already exists in the created room Pool
	 */
	protected static boolean isRoomExists(ArrayList<Room> roomList, String roomName) {
		boolean status = false;
		
		for (Room room : roomList) {
			if (room.getName().equals(roomName)) {
				status = true;
			}
		}
		
		return status;
	}
	
	/**
	 * findRoomIndex			finds the index of the available rooms by comparing it with given room name
	 * 
	 * @param roomList
	 * @param roomName
	 * @return		returns the index of the given room name
	 */
	protected static int findRoomIndex(ArrayList<Room> roomList, String roomName) {
		int roomIndex = 0;

		if (isRoomExists(roomList, roomName)) {
			for (Room room : roomList) {
				if (room.getName().compareTo(roomName) == 0) {
					break;
				}
				roomIndex++;
			}
		} else {
			roomIndex = -1;
		}
		
		System.out.println("Room Index: " + roomIndex);
		utility.Utility.sleepFor(2000);

		return roomIndex;
	}
	
	/**
	 * listRooms			Displays rooms that are created with its capacity
	 * 
	 * @param roomList
	 * @return
	 */
	protected static void listRooms(ArrayList<Room> roomList) {
		
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		System.out.println("\t\t\t\t\t\t\t\tROOM INFORMATION PAGE\n");
		
		if (roomList.size() == 0) {
			System.out.println("No rooms available as of now");
		} else {
				System.out.println(roomList.size() + " Room(s) available");
				for (int i=0; i<roomList.size(); i++) {
					System.out.println(i + ". " + roomList.get(i).getName() + " || Capacity: " + roomList.get(i).getCapacity());
				}
		}
		
		System.out.println("Redirecting to main home page in 4 seconds");
		utility.Utility.sleepFor(4000);
	}

	
	protected static String getRoomName() {
		// Q: nextLine() issue. unable to read the whole entry. some stuff happening... keyboard.next() before keyboard.nextLine() giving different results.
		return keyboard.next();
	}

	protected static String removeRoom(ArrayList<Room> roomList) {
		System.out.println("Remove a room:");
		roomList.remove(findRoomIndex(roomList, getRoomName()));

		return "Room removed successfully!";
	}


  /**
   * listSchedule		 
   * 
   * @param roomList
   * @return
   */
	protected static String listSchedule(ArrayList<Room> roomList) {
		String roomName = getRoomName();
		System.out.println(roomName + " Schedule");
		System.out.println("---------------------");
		
		for (Meeting m : getRoomFromName(roomList, roomName).getMeetings()) {
			System.out.println(m.toString());
		}

		return "";
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

}
