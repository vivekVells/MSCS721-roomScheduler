/**
 * @author <a href="https://github.com/vivekVells">Vivek Vellaiyappan Surulimuthu</a>
 * @version 1.8
 */

/**
 * package to contain all room schedule activities 
 */
package roomscheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Room			Class that holds all the spare parts for the Room.
 *
 */
public class Room {	
	
	private String name, building, location;
	private int capacity;
	private List<Meeting> meetings;
	
	/**
	 * Room			Parameterized constructor that initializes the class member variable's values
	 * 
	 * @param newRoomName
	 * @param newRoomCapacity
	 */
	public Room(String newRoomName, int newRoomCapacity, String building, String location) {
		setName(newRoomName);
		setCapacity(newRoomCapacity);
		setBuilding(building);
		setLocation(location);
		setMeetings(new ArrayList<Meeting>());
	}
 
	/**
	 * addMeeting			Inserts newly received meeting reference to the class member meetings list
	 * 
	 * @param newMeeting
	 */
	public void addMeeting(Meeting newMeeting) {
		this.getMeetings().add(newMeeting);
	}

	/**
	 * getName			Provides the name of the room
	 * 
	 * @return		returns the name of the room
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName			initialize the received room name to the class member variable (this) reference
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * setBulding		initialize the received room building name to the class member variable (this) reference
	 * @param name
	 */
	public void setBuilding(String building) {
		this.building = building;
	}
	
	/**
	 * getBuilding			Provides the building name of the room
	 * 
	 * @return		returns the name of the room
	 */
	public String getBuilding() {
		return this.building;
	}
	
	/**
	 * setLocation		initialize the received room location name to the class member variable (this) reference
	 * @param name
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * getLocation			Provides the location name of the room
	 * 
	 * @return		returns the name of the room
	 */
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * getCapacity			Provides the capacity of the room
	 * 
	 * @return		returns the capacity of the room
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * setCapacity			Initialize the received room capacity to the class member variable (this) reference
	 * 
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * getMeetings		Provides the meetings list
	 * 
	 * @return 	returns the meeting list that holds all the meetings info reference
	 */
	public List<Meeting> getMeetings() {
	   return meetings;
	}

	/**
	 * setMeetings		Initialize the received meeting reference to the class member variable (this) reference
	 * 
	 * @param meetings
	 */
	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}
	
}
