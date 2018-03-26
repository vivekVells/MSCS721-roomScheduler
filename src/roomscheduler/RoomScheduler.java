/**
 * @author <a href="https://github.com/vivekVells">Vivek Vellaiyappan Surulimuthu</a>
 * @version java 1.8  || <a href="https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.1/">gson 2.8.1 jar</a>
 */

/**
 * To contain all room schedule activities 
 */
package roomscheduler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Performance Increase Ideas:
 * - Have a Single Room Name variable? ?? I am blabbering may be...
 * - Code optimization
 * 		- conditions check. combine all the conditions if necessary
 * 		- use JSON accordingly to the scope of this program. I wish to choose JACKSON since I love Captain Jack Sparrow in Pirates of Car..
 * 		- StringBuffer vs String
 * 		- Which writer to use
 * 		-  
 * 		- 
 * 		- 
 */

/**
 * RoomScheduler			Main driver program to coordinate and make the room scheduling action complete.
 * 
 */
public class RoomScheduler {
	private static final String REDIRECT_HOME_PAGE = "\n\nRedirecting to Home Page Menu...";
	private static final String ROOM_NAME = "Room Name: ";
	protected static Scanner keyboard = new Scanner(System.in);
	protected static final Logger log = Logger.getLogger(RoomScheduler.class);
	
	// its better to create gson instance using GsonBuilder instead of just Gson. 
	// Advantages include ExposeAnnotation - serializing nulls - custom instance creators - set version support - pretty printing - custom serialize  and deserialize  
	protected static GsonBuilder builder = new GsonBuilder();
	protected static Gson gson = builder.create();
	
	
	/**
	 * main			"What giveth will be taketh away!!!" 
	 */
	public static void main(String[] args) {
		Boolean end = false;
		ArrayList<Room> rooms = new ArrayList<>();
		
		// having hard time configuring the log4j.properties file in the project
		// using this basic configurator to use basic operation stuffs
		BasicConfigurator.configure();
		
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
					listRooms(rooms);
					break;
				case 4:
					scheduleRoom(rooms);
					break;
				case 5:
					listSchedule(rooms);
					break;
				case 6:
					importRoomSchedule(rooms);
					break;
				case 7:
					exportRoomSchedule(rooms);
					break;
				case 8:
					System.out.println("Quitting the program...");
					log.info("Quitting the program...");
					utility.Utility.sleepFor(2000);
					utility.Utility.clearScreen();
					end = true;
					break;
				default:
					log.warn("Invalid choice selection. Input only number of choice appropriately...");
					System.out.println("Invalid choice selection. Input only number of choice appropriately...");
					System.out.println(REDIRECT_HOME_PAGE);
					utility.Utility.sleepFor(2000);
			}
		}
	}	
	
	/**
	 * roomSchedulerBanner			Just a banner page. Title and stuffs.
	 */
	public static void roomSchedulerBanner() {
		System.out.println("********************************************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t\t\tROOM SCHEDULER PAGE");
		System.out.println("********************************************************************************************************************************************************");
		System.out.println("Today: " + utility.Utility.getCurrentDateTime() + "\n");		
	}

	/**
	 * returns user choice and then can be used to call appropriate function
	 * @return user choice selected number
	 */
	protected static int mainMenu() {
		// Q: confused which to use for frequent usage. static methods vs instance methods? 
		// 		Though i donot have to create instance methods when it is static, i am using lots of static methods here. 
		// 		So, how much difference am I going to see here coz I have to create only one object and access all other methods through that
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		
		System.out.println("\nMain Menu:");
		System.out.println(" 1 - Add a room");
		System.out.println(" 2 - Remove a room");
		System.out.println(" 3 - List Rooms");
		System.out.println(" 4 - Schedule a room");
		System.out.println(" 5 - List Schedule");
		System.out.println(" 6 - Import Room Schedule");
		System.out.println(" 7 - Export Room Schedule");
		System.out.println(" 8 - Quit");
		System.out.print("Enter your selection: ");
		if(keyboard.hasNextInt()) {
			return keyboard.nextInt();
		} else {
				log.warn("\nInvalid choice selection. Input only number of choice appropriately");
				System.out.println("\nInvalid choice selection. Input only number of choice appropriately");
				keyboard.next();
				System.out.println(REDIRECT_HOME_PAGE);
				utility.Utility.sleepFor(2000);
				return mainMenu();
		}
	}
	
	/**
	 * exportRoomSchedule			Export the room's schedule as JSON file
	 * <p>
	 * There is a huge discussion going on over JSON.org vs GSON vs JACKSON vs BOON; As of now, I am going to use JSON.org
	 * 
	 * @param roomList
	 */
	protected static void exportRoomSchedule(ArrayList<Room> roomList) {
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		System.out.println("\n\t\t\t\t\t\t\t\tEXPORT ROOM SCHEDULE PAGE\n");
		// correct with no check condition for export
				
		String json = gson.toJson(roomList);
		System.out.println("json: " + json);
		// Have to resolve the platform dependent path issue
		String filePath =  "src\\resources\\files\\roomExportedFiles\\";
		String fileName =  "allRooms.json";
		
		// using try-with-resources to auto close the writer
		try (BufferedWriter bWriter = new BufferedWriter(new FileWriter (filePath + fileName))) {
			bWriter.write(json);
			log.info("\nFile: " + fileName + " was successfully exported. \nLocation: " + filePath + fileName);
			System.out.println("\nFile: " + fileName + " was successfully exported. \nLocation: " + filePath + fileName);
		} catch (IOException e) {
			log.trace(e);
			System.out.println(e);
			System.out.println("\nFile creation not succeeded...");
		}
		
		System.out.println(REDIRECT_HOME_PAGE);
		utility.Utility.sleepFor(3000); 
	}
	
	/**
	 * importRoomSchedule			Import the valid JSON file and ....
	 * 
	 * @param roomList
	 */
	protected static void importRoomSchedule(ArrayList<Room> roomList) {
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		System.out.println("\n\t\t\t\t\t\t\t\tIMPORT ROOM SCHEDULE PAGE\n");
		
		// Have to resolve the platform dependent path issue
		String filePath =  "src\\resources\\files\\roomExportedFiles\\";
		String fileName =  "allRooms.json";
		String jsonString = null;
		Boolean duplicateRoomExists = false;

		try (BufferedReader br = new BufferedReader(new FileReader(filePath + fileName))) {
			jsonString = br.readLine();
			System.out.println(utility.Utility.getPrettyPrintJson(jsonString) + "\n");
			
			Room[] rooms = gson.fromJson(jsonString, Room[].class);
			ArrayList<Room> tempRoomList = new ArrayList<>();
			
			if (rooms == null) {
				System.out.println("No rooms being imported...\nCheck the json file...");
			} else {
				
				for (Room room : rooms) {
					if (isRoomExists(roomList, room.getName())) {
						duplicateRoomExists = true;
					}
					
					if (!isRoomExists(roomList, room.getName())) {
						roomList.add(room);
						tempRoomList.add(room);						
					}
				}
				
				System.out.println("File Imported from: " + filePath + fileName);
				System.out.println("Imported Room(s) & their Schedule(s): \n");
				
				for (Room room: tempRoomList) {
					if (!roomList.isEmpty()) {
						System.out.println("\n" + room.getName() + "'s Schedule: \n");
						listRoomSchedule(roomList, room.getName());
					} else {
						System.out.println("No rooms available which are being scheduled...");
					}
				}	
			}
		} catch (FileNotFoundException e) {
			log.trace(e);
			log.fatal(e);
		} catch (IOException e) {
			System.out.println("\nUnable to open the json file...");
			log.trace(e);
			log.error(e);
		}
		
		if (duplicateRoomExists) {
			log.warn("Duplicate room import found... Ignored such room imports alone...");
			System.out.println("Duplicate room import found... Ignored such room imports alone...");
		}
				
		System.out.println(REDIRECT_HOME_PAGE);
		utility.Utility.sleepFor(4000); 
	}

	/**
	 * addRoom To add room with its capacity
	 * 
	 * @param roomList List that holds created room objects
	 */
	protected static void addRoom(ArrayList<Room> roomList) {		
		String name, building, location;
		int capacity = 0;
		Room newRoom = null;
		
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		System.out.println("\n\t\t\t\t\t\t\t\tADD ROOM PAGE\n");
		
		System.out.println("Note: Maximum Allowed Room Capacity: 10\n\n");
		
		System.out.print(ROOM_NAME);
		name = getRoomName();
		
		System.out.print("Building Name: ");
		building = keyboard.next();
		
		System.out.print("Location: ");
		location = keyboard.next();
		
		if (roomList.isEmpty() || !isRoomExists(roomList, name)) {
			System.out.print("Room capacity: ");
			
			if (keyboard.hasNextInt()) {
				capacity = keyboard.nextInt();
				
				// Q: why should not this capacity condition be handled in Room.java file since it holds all info related to room. 
				// RoomScheduler.java file here only helps to schedule. Hmmmmmmmmmmm.....				
				if (capacity <=0 || capacity >10) {
					System.out.println("\nMaximum allowable room capacity is 10...");
				} else {
					newRoom = new Room(name, capacity, building, location);
					roomList.add(newRoom);
					System.out.println("\nRoom '" + newRoom.getName() + "' is added successfully!");
				}
			} else {
				System.out.println("\nInput only capacity in numbers...");
			}
		} else {
			System.out.println("\nRoom ALREADY EXISTS...");
		}
		 
		System.out.println(REDIRECT_HOME_PAGE);
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
		System.out.println("\n\t\t\t\t\t\t\t\tROOM INFORMATION PAGE\n");
		
		if (roomList.isEmpty()) {
			System.out.println("\nNo rooms available as of now");
		} else {
				System.out.println(roomList.size() + " Room(s) available\n\n") ;
				for (int i=0; i<roomList.size(); i++) {
					System.out.println(i+1 + ") " + roomList.get(i).getName() 
							+ " || Capacity: " + roomList.get(i).getCapacity()
							+ " || Building: " + roomList.get(i).getBuilding()
							+ " || Location: " + roomList.get(i).getLocation() + "\n");
				}
		}
		
		System.out.println(REDIRECT_HOME_PAGE);
		utility.Utility.sleepFor(3000);
	}

	/**
	 * getRoomName			Since used often to get room name, a function is used
	 * 
	 * @return		returns inputted keyboard
	 */
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
		System.out.println("\n\t\t\t\t\t\t\t\tROOM REMOVAL PAGE\n");
		
		if (roomList.isEmpty()) {
			log.warn("\nNo rooms available to be removed...");
			System.out.println("\nNo rooms available to be removed...");
		} else {
			System.out.print("\nInput Room to remove: ");
			removeRoom = getRoomName();
			
			if (isRoomExists(roomList, removeRoom)) {
				roomList.remove(findRoomIndex(roomList, removeRoom));
				System.out.println("\nRoom removed successfully!");
			} else {
				log.warn("\nInputted room does not exist or already removed...");
				System.out.println("\nInputted room does not exist or already removed...");
			}
		}
		
		System.out.println(REDIRECT_HOME_PAGE);
		utility.Utility.sleepFor(3000);
	}
	
	/**
	 * isCurrentDateBefore			Compare two dates and check whether one date is before another			
	 * 
	 * @param startDate
	 * @param currentDate
	 * @return		returns true if given startDate arg is before currentDate arg
	 */
	protected static boolean isStartDateBeforeCurrentDate(String startDate, String currentDate) {
		if (utility.Utility.isDateBefore(startDate, currentDate)) {
			log.warn("\n\nStart Date is before current date...");
			System.out.println("\n\nStart Date is before current date...");
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * isStartDate30DaysAfterCurrentDate			Checks whether the start date is 30 days ahead of current date
	 * 
	 * @param startDate
	 * @param currentDate
	 * @return		returns true if start date is 30 days ahead of current date; else false
	 */
	protected static boolean isStartDate30DaysAfterCurrentDate(String startDate, String currentDate) {
		long dateDiffInDays = utility.Utility.getDateDiffInDays(currentDate, startDate); 
		if ( dateDiffInDays > 30) {
			log.warn("\nStart Date is 30 days ahead of current date...\n30 days are the maximum allowed duration between start date and current date");
			System.out.println("\nStart Date is 30 days ahead of current date...\n30 days are the maximum allowed duration between start date and current date");
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * isMinutesRounded15Increments			Verifies the minute part of time to be either in 00 or 15 or 30 or 45
	 * 
	 * @param time
	 * @return		returns true if minute in time arg is off 15 increments or 00; else false
	 */
	protected static boolean isMinutesRounded15Increments(String time) {
		String minuteStamp = time.split(":")[1];
		Boolean status = true;
		if (!(minuteStamp.equals("00") || minuteStamp.equals("15") || minuteStamp.equals("30") || minuteStamp.equals("45"))) {
			status = false;
		}
		return status;
	}
	
	/**
	 * isMinutesDurationNotExceed60			Verifies that the time duration difference not exceed 1 hour and within 15 steps increments
	 * 
	 * @param startTime
	 * @param endTime
	 * @return		true if start and end time duration does not exceed 1 hour and within 15 steps increments
	 */
	protected static boolean isMinutesDurationNotExceed60(String startTime, String endTime) {
		long timeDiff = utility.Utility.getTimeDiffInMinutes(startTime, endTime);
		
		if (timeDiff == 15 || timeDiff == 30 || timeDiff == 45 || timeDiff == 60) {
			return true;
		} else {
			log.warn("\nThe start time and end time duration difference exceeds 1 hour...\nMaximum allowed duration per meeting is 1 hour...");
			System.out.println("\nThe start time and end time duration difference exceeds 1 hour...\nMaximum allowed duration per meeting is 1 hour...");
			return false;
		}
	}
	
	/**
	 * isSameRoomAndTimeBooked			Finds whether a room is booked with same date and timings
	 * 
	 * @param roomList
	 * @param name
	 * @param startDate
	 * @param startTime
	 * @param endTime
	 * @return		returns true if a room with same date and timing booked against the given start and end time arg
	 */
	protected static boolean isSameRoomAndTimeBooked(ArrayList<Room> roomList, String name, String startDate, String startTime, String endTime) {
		boolean status = false;
		Room currentRoom = getRoomFromName(roomList, name);
		
		if (currentRoom == null) {
			return false;
		}
		
		for (Meeting m : currentRoom.getMeetings()) {
			if (isSameDateTimeDurationBooked(roomList, name, startDate, startTime, endTime)) {
				if (m.getStartTime().equals(startTime) 
						|| m.getStopTime().equals(endTime) 
						|| utility.Utility.isTargetBetweenStartAndStop(startTime, m.getStartTime(), m.getStopTime())
						|| utility.Utility.isTargetBetweenStartAndStop(endTime, m.getStartTime(), m.getStopTime()) 
						|| utility.Utility.isTargetBetweenStartAndStopExtend(startTime, endTime, m.getStartTime(), m.getStopTime())) {
					status = true;
					break;
				}
			}
		}
		
		if (status) {
			log.warn("\nRoom already booked in this timing range...\nTry different time duration...");
			System.out.println("\nRoom already booked in this timing range...\nTry different time duration...");
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * isSameDateTimeDurationBooked		checks whether inputted date is already booked or not
	 * 
	 * @param roomList
	 * @param roomName
	 * @param startDate
	 * @param startTime
	 * @param endTime
	 * @return		returns true if the inputted date is already booked; else false
	 */
	protected static boolean isSameDateTimeDurationBooked(ArrayList<Room> roomList, String roomName, String startDate, String startTime, String endTime) {
		boolean status = false;
		if (!getRoomFromName(roomList, roomName).getMeetings().isEmpty()) {
			for (Meeting m : getRoomFromName(roomList, roomName).getMeetings()) {
				if(m.getStartDate().equals(startDate) && m.getStartTime().equals(startTime) && m.getStopTime().equals(endTime)) {
					status = true;
					break;
				}
			}
		}
		
		if (status) {
			log.warn("\nRoom already booked in this date & timing range...");
			System.out.println("\nRoom already booked in this date & timing range...");
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * scheduleRoom			schedule the room
	 * 
	 * @param roomList
	 */
	protected static void scheduleRoom(ArrayList<Room> roomList) {
		utility.Utility.clearScreen();
		roomSchedulerBanner();
		System.out.println("\n\t\t\t\t\t\t\t\tROOM SCHEDULE PAGE\n");

		// Q: how to handle timezone based scheduling conflicts. Unable to find good date time validater. I have to figure out something for this one
		// Have to handle this aptly. 
		System.out.println("\nNote:"
				+ "\nRefer Today's date time stamp at top left corner and schedule accordingly."
				+ "\nInput of Date should be in \"YYYY-mm-dd\" - (e-g): 2018-02-28 "
				+ "\nInput of Time should be in \"HH:MM\" - (e-g): 10:10 and is in 24 hours format"
				+ "\nStart Date should not be before current date and should not be more than 30 days from now"
				+ "\nStart and End Time should have minutes such that minutes are of round figures like HH:00 or HH:15 or HH:30 or HH:45"
				+ "\nStart and End Time difference should be minimum of 15 minutes and maximum of 60 minutes\n");
		
		if (roomList.isEmpty()) {
			System.out.println("\n\nNo rooms available to schedule for now. Contact Room Schedule Manager...");
		} else {
			System.out.print(ROOM_NAME);
				String name = getRoomName();
				
				if (!isRoomExists(roomList, name)) {
					System.out.println("\nInputted Room either not exists or removed...");
				} else {					
	
						// Q: start date and end date. Hmmm.... should this be handled like timing same for different dates. book? 
						// 	let me remove end date for now.. will handle this concurrency soon
					
						// Q: endTimeStamp is confusing. Will any user will book a room for more than 1 or 5 hour (max)? 
						// 	As per endTimeStamp, say there are more than few days difference, say like 2 or 5 days, is that even possible? hmmmm....
						// M: handle entries that are not in the specified format (applies to date and time)
					System.out.print("Start Date (yyyy-mm-dd): ");
						String startDate = keyboard.next();
						
						if (utility.Utility.isValidDate(startDate) 
								&& !isStartDateBeforeCurrentDate(startDate, utility.Utility.getCurrentDate()) 
								&& !isStartDate30DaysAfterCurrentDate(startDate, utility.Utility.getCurrentDate())) {
							
							System.out.print("Start Time (HH:MM): ");
							String startTime = keyboard.next();
							startTime = startTime + ":00";
							
							if (utility.Utility.isValidTime(startTime) && isMinutesRounded15Increments(startTime)) {
								System.out.print("End Time (HH:MM): ");
								String endTime = keyboard.next();
								endTime = endTime + ":00";
								
								// have to format this one. throw catch thingy tryout!!
								// c here... diff date + s timing - ct here
								if (utility.Utility.isValidTime(endTime) && isMinutesRounded15Increments(endTime)) {	
									if (isMinutesDurationNotExceed60(startTime, endTime)) {
										if (!isSameRoomAndTimeBooked(roomList, name, startDate, startTime, endTime)) {
											System.out.print("Subject: ");
											String subject = keyboard.next();
											
											Room curRoom = getRoomFromName(roomList, name);					
											Meeting meeting = new Meeting(startDate, startTime, endTime, subject);
											
											if (curRoom != null) {
												curRoom.addMeeting(meeting);												
												System.out.println("\nSuccessfully scheduled meeting!");
											} else {
												  log.error("Error occurred while scheduling the meeting...");
												  System.out.println("Error occurred while scheduling the meeting...");
											}
										} else {
											  log.warn("\nInvalid start and end time inputed...");								
											  System.out.println("\nInvalid start and end time inputed...");
										}
									} else {
										  log.warn("\nInvalid start and end time duration inputed...");
										  System.out.println("\nInvalid start and end time duration inputed...");
									}
								} else {
									  log.warn("\nInvalid end time inputed...");
									  System.out.println("\nInvalid end time inputed...");
								}
							} else {
								  log.warn("\nInvalid start time inputed...");
								  System.out.println("\nInvalid start time inputed...");
							}
						} else {
							  log.warn("\nInvalid start date inputed...");
							  System.out.println("\nInvalid start date inputed...");
						}
				}
		}
		
		System.out.println(REDIRECT_HOME_PAGE);
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
		System.out.println("\n\t\t\t\t\t\t\t\tSCHEDULED ROOM INFORMATION PAGE\n");
		
		System.out.println("Note: "
												+ "Input exactly this word to list all room schedules: ALL_ROOMS\n\n");
		
		if (roomList.isEmpty()) {
			System.out.println("\nNo rooms available which are being scheduled...");
		} else {
			System.out.print(ROOM_NAME);
			String roomName = getRoomName();
			System.out.println();
			
			if (roomName.equals("ALL_ROOMS")) {
				for (Room room: roomList) {
					System.out.println("\n" + room.getName() + "'s Schedule: \n");
					listRoomSchedule(roomList, room.getName());
				} 
			} else if (!roomName.equals("ALL_ROOMS")) {
				listRoomSchedule(roomList, roomName);
			} else {
				log.warn("No rooms available which are being scheduled...");
				System.out.println("No rooms available which are being scheduled...");
			}
		}
		
		System.out.println(REDIRECT_HOME_PAGE);
		utility.Utility.sleepFor(3000); 
	}
	
	/**
	 * listRoomSchedule			checks and prints all available schedule of the provided args room
	 * 
	 * @param roomList
	 * @param roomName
	 */
	protected static void listRoomSchedule(ArrayList<Room> roomList, String roomName) {
		if (isRoomExists(roomList, roomName)) {
			if (getRoomFromName(roomList, roomName).getMeetings().isEmpty()) {
				System.out.println("No Meetings being scheduled for this room\n");
			} else {
				int i = 0;
				for (Meeting m : getRoomFromName(roomList, roomName).getMeetings()) {
					System.out.println( i + 1 + ") " + m.toString() + "\n");
					++i;
				}
			}
		} else {
			log.warn("\nInputed Room does not exits...");
			System.out.println("\nInputed Room does not exits...");
		}
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
			log.warn("\nGiven room does not exists...");
			System.out.println("\nGiven room does not exists...");
			return null;
		}
	}
}
