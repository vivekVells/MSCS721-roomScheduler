/**
 * @author <a href="https://github.com/vivekVells">Vivek Vellaiyappan Surulimuthu</a>
 * @version 1.8
 */



/**
 * To contain all room schedule activities 
 */
package roomscheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to schedule a room
 */
public class RoomScheduler {
	protected static Scanner keyboard = new Scanner(System.in);

	/**
	 * main			"What giveth will be taketh away!!!" - naaahhh!!!
	 * 
	 */
	public static void main(String[] args) {
		Boolean end = false;
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		while (!end) {
			switch (mainMenu()) {
				// Q: If i am not wrong, all the cases should return boolean value so that we can check somewhere whether there is success or failure in completing the option
							// say, remove a room. returns true if it gets removed. false if not... 
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
			}
		}
	}
	
	/**
	 * getCurrentDateTime			To provide current date and time. This is based on current locale machine time...
	 * @return
	 */
	protected static String getCurrentDateTime() {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:MM:ss");
		return dtf.format(ldt);
	}
	
	
	/**
	 * roomSchedulerBanner			Just a banner page. Title and stuffs.
	 */
	public static void roomSchedulerBanner() {
		System.out.println("********************************************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t\tROOM SCHEDULER PAGE");
		System.out.println("********************************************************************************************************************************************************");
		  
		System.out.println("Today: " + getCurrentDateTime());
		String date = "2018-12-31";
		String time = "12:23:00";
		try {
			LocalDate.parse(date);
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date");
		}
		try {
			LocalTime.parse(time);
		} catch (DateTimeParseException e) {
			System.out.println("Invalid time");
		}
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
				System.out.println("Invalid choice selection. Input only number of choice appropriately");
				keyboard.next();
				System.out.println("\nRedirecting to Room Scheduler Home Page Menu...");
				utility.Utility.sleepFor(2000);
				return mainMenu();
		}
	}

	/**
	 * addRoom To add room with its capacity
	 * 
	 * @param roomList List that holds created room objects
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
					System.out.println("Maximum allowable room capacity is 10");
				} else {
					newRoom = new Room(name, capacity);
					roomList.add(newRoom);
					System.out.println("Room '" + newRoom.getName() + "' is added successfully!");
				}
			} else {
				System.out.println("Input only capacity in numbers...");
			}
		} else {
				System.out.println("Room ALREADY EXISTS...");
		}
		
		System.out.println("\nRedirecting to Home Page Menu...");
		utility.Utility.sleepFor(3000);
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
		
		return roomIndex;
	}
	
	/**
	 * listRooms			Displays rooms that are created with its capacity
	 * 
	 * @param roomList
	 */
	protected static void listRooms(ArrayList<Room> roomList) {
		
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		System.out.println("\t\t\t\t\t\t\t\tROOM INFORMATION PAGE\n");
		
		if (roomList.size() == 0) {
			System.out.println("No rooms available as of now");
		} else {
				System.out.println(roomList.size() + " Room(s) available\n");
				for (int i=0; i<roomList.size(); i++) {
					System.out.println(i + ") " + roomList.get(i).getName() + " || Capacity: " + roomList.get(i).getCapacity());
				}
		}
		
		System.out.println("Redirecting to Home Page Menu...");
		utility.Utility.sleepFor(3000);
	}

	
	protected static String getRoomName() {
		// Q: nextLine() issue. unable to read the whole entry. some stuff happening... keyboard.next() before keyboard.nextLine() giving different results.
		return keyboard.next();
	}
	
	/**
	 * removeRoom			removes the room being provided
	 * 
	 * @param roomList
	 */
	protected static void removeRoom(ArrayList<Room> roomList) {
		String removeRoom = "";
		
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		System.out.println("\t\t\t\t\t\t\t\tROOM REMOVAL PAGE\n");
		
		if (roomList.size() == 0) {
			System.out.println("No rooms available to be removed...");
		} else {
			System.out.print("\nInput Room to remove: ");
			removeRoom = getRoomName();
			
			if (isRoomExists(roomList, removeRoom)) {
				roomList.remove(findRoomIndex(roomList, removeRoom));
				System.out.println("Room removed successfully!");
			} else {
				System.out.println("Inputted room does not exist or already removed...");
			}
		}
		
		System.out.println("\nRedirecting to Home Page Menu...");
		utility.Utility.sleepFor(3000);
	}

	/**
	 * scheduleRoom			schedule the room
	 * 
	 * @param roomList
	 */
	protected static void scheduleRoom(ArrayList<Room> roomList) {
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		System.out.println("\t\t\t\t\t\t\t\tROOM SCHEDULE PAGE\n");

		// Q: how to handle timezone based scheduling conflicts. Unable to find good date time validater. I have to figure out something for this one
		// Have to handle this aptly. 
		System.out.println("Note:"
				+ "\nRefer Today's date time stamp at top left corner and schedule accordingly."
				+ "\nInput of Date should be in \"YYYY-mm-dd\" - (e-g): 2018-02-28 "
				+ "\nInput of Time should be in \"HH:MM\" - (e-g): 10:10 "
				+ "\nStart and End Time should have minutes such that minutes are of round figures like HH:00 or HH:15 or HH:30 or HH:45"
				+ "\nStart and End Time difference should be minimum of 15 minutes and maximum of 60 minutes\n");
		
		if (roomList.size() == 0) {
			System.out.println("\nNo rooms available to schedule for now. Contact Room Schedule Manager...");
		} else {
				System.out.print("Room Name: ");
				String name = getRoomName();
				
				if (!isRoomExists(roomList, name)) {
					System.out.println("Inputted Room either not exists or removed...");
				} else {					
						/*
						System.out.println("End Date? (yyyy-mm-dd):");
						String endDate = keyboard.next();
						*/
	
						// Q: start date and end date. Hmmm.... should this be handled like timing same for different dates. book? 
						// 	let me remove end date for now.. will handle this concurrency soon
					
						// Q: endTimeStamp is confusing. Will any user will book a room for more than 1 or 5 hour (max)? 
						// 	As per endTimeStamp, say there are more than few days difference, say like 2 or 5 days, is that even possible? hmmmm....
						System.out.print("Start Date (yyyy-mm-dd): ");
						String startDate = keyboard.next();
						
						if (utility.Utility.isValidDate(startDate)) {
							System.out.print("Start Time (HH:MM): ");
							String startTime = keyboard.next();
							startTime = startTime + ":00";
							
							if (utility.Utility.isValidTime(startTime)) {
								System.out.print("End Time (HH:MM): ");
								String endTime = keyboard.next();
								endTime = endTime + ":00";
								
								if (utility.Utility.isValidTime(endTime)) {
									System.out.print("Subject: ");
									String subject = keyboard.next();
									
									Room curRoom = getRoomFromName(roomList, name);					
									Meeting meeting = new Meeting(startDate, startTime, endTime, subject);
									curRoom.addMeeting(meeting);
									
									System.out.println("Successfully scheduled meeting!");
								} else {
									System.out.println("Invalid end time inputed...");
								}
							} else {
								System.out.println("Invalid start time inputed...");
							}
						} else {
							System.out.println("Invalid start date inputed...");
						}
				}
		}
		
		System.out.println("\nRedirecting to Home Page Menu...");
		utility.Utility.sleepFor(3000);
	}

  /**
   * listSchedule		 
   * 
   * @param roomList
   * @return
   */
	protected static void listSchedule(ArrayList<Room> roomList) {
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		System.out.println("\t\t\t\t\t\t\t\tSCHEDULED ROOM INFORMATION PAGE\n");
		
		if (roomList.size() == 0) {
			System.out.println("No rooms available which are to be scheduled...");
		} else {
			System.out.print("Room Name: ");
			String roomName = getRoomName();
			System.out.println("\n" + roomName + "'s Schedule: \n");
			
			if (isRoomExists(roomList, roomName)) {
				if (getRoomFromName(roomList, roomName).getMeetings().size() == 0) {
					System.out.println("No Meetings being scheduled for this room");
				} else {
					int i = 0;
					for (Meeting m : getRoomFromName(roomList, roomName).getMeetings()) {
						System.out.println( i + 1 + ") " + m.toString());
						++i;
					}
				}
			} else {
				System.out.println("Inputed Room does not exits...");
			}
		}
		
		System.out.println("\nRedirecting to Home Page Menu...");
		utility.Utility.sleepFor(3000);
	}
	
	/**
	 * getRoomFromName			Finds the given room arg object reference
	 * @param roomList
	 * @param name
	 * @return		returns the object reference value of given room name arg
	 */
	protected static Room getRoomFromName(ArrayList<Room> roomList, String name) {
		if (isRoomExists(roomList, name)) {
			return roomList.get(findRoomIndex(roomList, name));
		} else {
			System.out.println("Given room does not exists...");
			return null;
		}
	}

}
