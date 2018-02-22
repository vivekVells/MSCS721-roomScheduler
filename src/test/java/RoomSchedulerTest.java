/*
 * Confirmation: values changed within a @Test function wont have scope outside their block?
 */

/**
 * @author <a href="https://github.com/vivekVells">Vivek Vellaiyappan Surulimuthu</a>
 * @version java 1.8 
 */

/**
 * Contains the JUnit testing of roomScheduler
 */
package test.java;

import roomscheduler.Room;
import roomscheduler.Meeting;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * RoomSchedulerTest		Contains all junit tests for src/roomscheduler/RoomScheduler.java file
 */
public class RoomSchedulerTest {
    
    private Room roomObj = null;
    private Meeting meetObj = null;
    private ArrayList<Room> roomList = null;
    private ArrayList<Meeting> meetingList = null;
    
    /**
     * main		Main execution file; but not needed; since maven test is independent of this presence. 
     * 			This is useful to run in IDEs especially in Eclipse, without this function you cannot run this pgm as junit
     * @param args
     */
    public static void main(String[] args) {}
    
    
    /**
     * init		Setup function before running test
     */
    @Before
    public void init() {
	this.roomList = new ArrayList<>();
	this.roomObj = new Room("CodingRoom", 5);
	this.roomList.add(roomObj);
	
	this.meetingList = new ArrayList<>();
	this.meetObj = new Meeting("2018-02-20", "03:00", "03:45", "CodingTime");
	this.meetingList.add(meetObj);
	
	this.roomList.get(0).addMeeting(this.meetingList.get(0));

    }
        
    /**
     * testAddRoom	Tests the adding room functionality
     */
    @Test
    public void testAddRoom() {
	assertEquals("CodingRoom", this.roomList.get(0).getName());
	assertEquals(5, this.roomList.get(0).getCapacity());
    }
    
    /**
     * testListRoom	Tests the listing room functionality
     */
    @Test
    public void testListRoom() {
	assertEquals(1, this.roomList.size());
	assertEquals("CodingRoom", this.roomList.get(this.roomList.size() - 1).getName() );
	assertEquals(5, this.roomList.get(this.roomList.size() - 1).getCapacity() );
    }
    
    /**
     * testMeetingStartDateAndTimeEndTimeSubject	tests meeting args 
     */
    @Test 
    public void testMeetingStartDateAndTimeEndTimeSubject() {
	assertEquals("2018-02-20", this.meetingList.get(0).getStartDate());
	assertEquals("03:00", this.meetingList.get(0).getStartTime());
	assertEquals("03:45", this.meetingList.get(0).getStopTime());
	assertEquals("CodingTime", this.meetingList.get(0).getSubject());
    }
     
    /**
     * testScheduleRooms	tests the scheduled rooms
     */
    @Test 
    public void testScheduleRooms() {
	
	// when this alone was present, the testListSchedule [] why????
	// this.roomList.get(0).addMeeting(this.meetingList.get(0));
	
	assertEquals("2018-02-20", this.roomList.get(0).getMeetings().get(0).getStartDate());
	assertEquals("03:00", this.roomList.get(0).getMeetings().get(0).getStartTime());
	assertEquals("03:45", this.roomList.get(0).getMeetings().get(0).getStopTime());
	assertEquals("CodingTime", this.roomList.get(0).getMeetings().get(0).getSubject());
    }
    
    /**
     * testListSchedule		tests the schedules avail in room
     */
    @Test 
    public void testListSchedule() {
	int roomSize = this.roomList.size();
	int meetingSize = this.meetingList.size();
	
	assertEquals("2018-02-20", this.roomList.get(roomSize - 1).getMeetings().get(meetingSize - 1).getStartDate());
	assertEquals("03:00", this.roomList.get(roomSize - 1).getMeetings().get(meetingSize - 1).getStartTime());
	assertEquals("03:45", this.roomList.get(roomSize - 1).getMeetings().get(meetingSize - 1).getStopTime());
	assertEquals("CodingTime", this.roomList.get(roomSize - 1).getMeetings().get(meetingSize - 1).getSubject());
    }
    
    /**
     * testRemoveRoom		Tests the remove room functionality
     */
    @Test 
    public void testRemoveRoom() {
	this.roomList.remove(this.roomList.size()-1);
	// have to do an indexOutOfBoundException over here and check it. assertThrows() here. Will do it.
	assertEquals(0, this.roomList.size());
    }
    
    /*
    @Test
    public void testExportRoomSchedule() {
	fail("Not yet implemented");
    }

    @Test
    public void testImportRoomSchedule() {
	fail("Not yet implemented");
    }

    @Test
    public void testAddRoom() {
	fail("Not yet implemented");
    }

    @Test
    public void testIsRoomExists() {
	fail("Not yet implemented");
    }

    @Test
    public void testFindRoomIndex() {
	fail("Not yet implemented");
    }

    @Test
    public void testListRooms() {
	fail("Not yet implemented");
    }

    @Test
    public void testGetRoomName() {
	fail("Not yet implemented");
    }

    @Test
    public void testRemoveRoom() {
	fail("Not yet implemented");
    }

    @Test
    public void testIsStartDateBeforeCurrentDate() {
	fail("Not yet implemented");
    }

    @Test
    public void testIsStartDate30DaysAfterCurrentDate() {
	fail("Not yet implemented");
    }

    @Test
    public void testIsMinutesRounded15Increments() {
	fail("Not yet implemented");
    }

    @Test
    public void testIsMinutesDurationNotExceed60() {
	fail("Not yet implemented");
    }

    @Test
    public void testIsSameRoomAndTimeBooked() {
	fail("Not yet implemented");
    }

    @Test
    public void testIsSameDateTimeDurationBooked() {
	fail("Not yet implemented");
    }

    @Test
    public void testScheduleRoom() {
	fail("Not yet implemented");
    }

    @Test
    public void testListSchedule() {
	fail("Not yet implemented");
    }

    @Test
    public void testListRoomSchedule() {
	fail("Not yet implemented");
    }

    @Test
    public void testGetRoomFromName() {
	fail("Not yet implemented");
    }

    @Test
    public void testObject() {
	fail("Not yet implemented");
    }

    @Test
    public void testGetClass() {
	fail("Not yet implemented");
    }

    @Test
    public void testHashCode() {
	fail("Not yet implemented");
    }

    @Test
    public void testEquals() {
	fail("Not yet implemented");
    }

    @Test
    public void testClone() {
	fail("Not yet implemented");
    }

    @Test
    public void testToString() {
	fail("Not yet implemented");
    }

    @Test
    public void testNotify() {
	fail("Not yet implemented");
    }

    @Test
    public void testNotifyAll() {
	fail("Not yet implemented");
    }

    @Test
    public void testWaitLong() {
	fail("Not yet implemented");
    }

    @Test
    public void testWaitLongInt() {
	fail("Not yet implemented");
    }

    @Test
    public void testWait() {
	fail("Not yet implemented");
    }

    @Test
    public void testFinalize() {
	fail("Not yet implemented");
    }
    */
}
