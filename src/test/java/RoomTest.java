/**
 * Yet Another Software License, 1.0
 *
 * Lots of text, specifying the users rights, and whatever ...
 */

package test.java;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import roomscheduler.Room;
import roomscheduler.Meeting;

public class RoomTest {
    Room roomObj;
    Meeting meetingObj;
    ArrayList<Meeting> meetingList = new ArrayList<>(); 
    
    String tempRoomName = "", tempBuilding = "", tempLocation = "";
    String tempStartDate = "", tempStartTime = "", tempStopTime = "", tempSubject = "";
    
    @Before
    public void init() {
	this.roomObj = new Room("CodingRoom", 5, "build", "loca");
	this.meetingObj = new Meeting("2018-02-25", "01:00:00", "01:30:00", "subject");
	this.meetingList.add(this.meetingObj);
	this.roomObj.setMeetings(this.meetingList);

	this.tempRoomName = this.roomObj.getName();
	this.tempBuilding = this.roomObj.getBuilding();
	this.tempLocation = this.roomObj.getLocation();
	this.tempStartDate = this.meetingList.get(0).getStartDate();
	this.tempStartTime = this.meetingList.get(0).getStartTime();
	this.tempStopTime =  this.meetingList.get(0).getStopTime();
	this.tempSubject = this.meetingList.get(0).getSubject();
    }
    
    /**
     * testSetName	Tests the adding room name functionality
     */
    @Test
    public void testSetName() {
	this.roomObj.setName("newCodingRoomName");
	assertEquals("newCodingRoomName", this.roomObj.getName());
    }
    
    /**
     * testGetName	Tests the room name functionality
     */
    @Test
    public void testGetName() {
	assertEquals(this.tempRoomName, this.roomObj.getName());
    }
    
    /**
     * testSetBuilding	Tests the settting of building room name functionality
     */
    @Test
    public void testSetBuilding() {
	this.roomObj.setBuilding("newBuildName");
	assertEquals("newBuildName", this.roomObj.getBuilding());
    }
    
    /**
     * testGetBuilding	Tests the retrieval of building room name functionality
     */
    @Test
    public void testGetBuilding() {
	assertEquals(this.tempBuilding, this.roomObj.getBuilding());
    }
    
    /**
     * testSetLocation	Tests the setting of room location name functionality
     */
    @Test
    public void testSetLocation() {
	this.roomObj.setLocation("newLocationName");
	assertEquals("newLocationName", this.roomObj.getLocation());
    }
    
    /**
     * testGetLocation	Tests the retrieval of room location name functionality
     */
    @Test
    public void testGetLocation() {
	assertEquals(this.tempLocation, this.roomObj.getLocation());
    }
    
    /**
     * testSetCapacity		Tests the setting of room capacity functionality
     */
    @Test
    public void testSetCapacity() {
	this.roomObj.setCapacity(8);
	assertEquals(8, this.roomObj.getCapacity());
	assertNotEquals(5, this.roomObj.getCapacity());
    }
    
    /**
     * testGetCapacity		Tests the retrieval of room capacity functionality
     */
    @Test
    public void testGetCapacity() {
	assertNotEquals(8, this.roomObj.getCapacity());
    }
    
    /**
     * testSetMeeting		Tests the setting of room meeting functionality
     */
    @Test
    public void testSetMeetings() {
	assertEquals("2018-02-25", this.roomObj.getMeetings().get(0).getStartDate());
	assertEquals("01:00:00", this.roomObj.getMeetings().get(0).getStartTime());
	assertEquals("01:30:00", this.roomObj.getMeetings().get(0).getStopTime());
	assertEquals("subject", this.roomObj.getMeetings().get(0).getSubject());
	
	Meeting newMeetingObj = new Meeting("2018-02-29", "03:00:00", "03:30:00", "newSubject");
	this.meetingList.add(newMeetingObj);
	assertNotEquals("2018-02-25", this.roomObj.getMeetings().get(1).getStartDate());
	assertNotEquals("01:00:00", this.roomObj.getMeetings().get(1).getStartTime());
	assertNotEquals("01:30:00", this.roomObj.getMeetings().get(1).getStopTime());
	assertNotEquals("subject", this.roomObj.getMeetings().get(1).getSubject());
	assertEquals("2018-02-29", this.roomObj.getMeetings().get(1).getStartDate());
	assertEquals("03:00:00", this.roomObj.getMeetings().get(1).getStartTime());
	assertEquals("03:30:00", this.roomObj.getMeetings().get(1).getStopTime());
	assertEquals("newSubject", this.roomObj.getMeetings().get(1).getSubject());
    }
    
    /**
     * testGetMeeting		Tests the retrieval of room meeting functionality
     */
    @Test
    public void testGetMeetings() {
	assertEquals(this.tempStartDate, this.roomObj.getMeetings().get(0).getStartDate());
	assertEquals(this.tempStartTime, this.roomObj.getMeetings().get(0).getStartTime());
	assertEquals(this.tempStopTime, this.roomObj.getMeetings().get(0).getStopTime());
	assertEquals(this.tempSubject, this.roomObj.getMeetings().get(0).getSubject());
    }         
}
