/**
 * @author <a href="https://github.com/vivekVells">Vivek Vellaiyappan Surulimuthu</a>
 * @version 1.8
 */

/**
 * package to contain all room schedule activities 
 */
package roomscheduler;

import java.util.ArrayList;

public class Room {	
	
	private String name;
	private int capacity;
	private ArrayList<Meeting> meetings;
	
	
	public Room(String newRoomName, int newRoomCapacity) {
		setName(newRoomName);
		setCapacity(newRoomCapacity);
		setMeetings(new ArrayList<Meeting>());
	}

	public void addMeeting(Meeting newMeeting) {
		this.getMeetings().add(newMeeting);
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public ArrayList<Meeting> getMeetings() {
		return meetings;
	}


	public void setMeetings(ArrayList<Meeting> meetings) {
		this.meetings = meetings;
	}
	
}
