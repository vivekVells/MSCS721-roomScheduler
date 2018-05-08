/**
 * Yet Another Software License, 1.0
 *
 * Lots of text, specifying the users rights, and whatever ...
 */

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
import roomscheduler.RoomScheduler;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * RoomSchedulerTest		Contains all junit tests for src/roomscheduler/RoomScheduler.java file
 */
public class RoomSchedulerTest extends roomscheduler.RoomScheduler {
    
    private Room roomObj = null;
    private Meeting meetObj = null;
    private RoomScheduler rsObj = null;
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
	this.roomObj = new Room("CodingRoom", 5, "build", "loca");
	this.roomList.add(roomObj);
	
	this.meetingList = new ArrayList<>();
	this.meetObj = new Meeting(LocalDate.now().toString(), "03:00", "03:45", "CodingTime");
	this.meetingList.add(meetObj);
	
	this.roomList.get(0).addMeeting(this.meetingList.get(0));
	
	this.rsObj = new RoomScheduler();
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
	assertEquals(LocalDate.now().toString(), this.meetingList.get(0).getStartDate());
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
	
	assertEquals(LocalDate.now().toString(), this.roomList.get(0).getMeetings().get(0).getStartDate());
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
	
	assertEquals(LocalDate.now().toString(), this.roomList.get(roomSize - 1).getMeetings().get(meetingSize - 1).getStartDate());
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
    
    /**
     * testIsRoomExists		Tests whether the given room exists
     */
    @Test
    public void testIsRoomExists() {
	this.roomObj = new Room("PartyRoom", 10,"build", "loca");
	this.roomList.add(roomObj);
	this.roomObj = new Room("RelaxingRoom", 2, "build", "loca");
	this.roomList.add(roomObj);
	
	String toFindRoom = "RelaxingRoom";
	
	Boolean statusValid = RoomScheduler.isRoomExists(this.roomList, toFindRoom);
	assertTrue(statusValid);
	
	Boolean statusInvalid = RoomScheduler.isRoomExists(this.roomList, "InvalidRoomName");
	assertFalse(statusInvalid);	
    }
    
    /**
     * testIsMinutesRounded15Increments		Tests for given time args in rounded steps of 15
     */
    @Test
    public void testIsMinutesRounded15Increments() {
	String valid1 = "02:00";
	String valid2 = "02:15";
	String valid3 = "02:30";
	String valid4 = "02:45";
	String invalid1 = "02:12";
	String invalid2 = "02:19";
	
	assertTrue(RoomScheduler.isMinutesRounded15Increments(valid1));
	assertTrue(RoomScheduler.isMinutesRounded15Increments(valid2));
	assertTrue(RoomScheduler.isMinutesRounded15Increments(valid3));
	assertTrue(RoomScheduler.isMinutesRounded15Increments(valid4));
	assertFalse(RoomScheduler.isMinutesRounded15Increments(invalid1));
	assertFalse(RoomScheduler.isMinutesRounded15Increments(invalid2));
    }
    
    /**
     * testGetRoomFromName	Tests for roomObj based on given room name0
     */
    @Test
    public void testGetRoomFromName() {
	this.roomObj = new Room("PartyRoom", 10, "build", "loca");
	this.roomList.add(roomObj);
	this.roomObj = new Room("RelaxingRoom", 2, "build", "loca");
	this.roomList.add(roomObj);
	
	Room toFindRoomObj = this.roomList.get(1);
	
	Room foundRoomObjValid = RoomScheduler.getRoomFromName(roomList, this.roomList.get(1).getName());
	Room foundRoomObjInvalid = RoomScheduler.getRoomFromName(roomList, "sdfsdfsd");
	
	assertTrue(toFindRoomObj.equals(foundRoomObjValid));
	assertFalse(toFindRoomObj.equals(foundRoomObjInvalid));	
    }
    
    /**
     * testIsMinutesDurationNotExceed60		Tests for given minutes not exceed 60
     */
    @Test
    public void testIsMinutesDurationNotExceed60() {
	String valid1 = "02:00";
	String valid2 = "02:15";
	String valid3 = "02:30";
	String valid4 = "02:45";
	String invalid1 = "02:12";
	String invalid2 = "02:19";
	
	assertTrue(RoomScheduler.isMinutesDurationNotExceed60(valid1, valid2));
	assertTrue(RoomScheduler.isMinutesDurationNotExceed60(valid2, valid4));
	assertFalse(RoomScheduler.isMinutesDurationNotExceed60(valid3, valid1));
	assertFalse(RoomScheduler.isMinutesDurationNotExceed60(invalid1, invalid2));
    }
    
    /**
     * testIsStartDateBeforeCurrentDate		Tests whether the given start date is before current date
     */
    @Test
    public void testIsStartDateBeforeCurrentDate() {	
	LocalDate today = LocalDate.now();
	LocalDate tomorrow = today.plusDays(1);
	LocalDate yesterday = today.minusDays(1);

	assertFalse(RoomScheduler.isStartDateBeforeCurrentDate(tomorrow.toString(), today.toString()));
	assertTrue(RoomScheduler.isStartDateBeforeCurrentDate(yesterday.toString(), today.toString()));	
    }
    
    /**
     * testIsStartDate30DaysAfterCurrentDate	Tests whether start date 30 days after today
     */
    @Test
    public void testIsStartDate30DaysAfterCurrentDate() {
	LocalDate today = LocalDate.now();
	LocalDate dateAfter30DaysFromToday = today.plusDays(31);
	LocalDate dateAfterTodayNotExceeding30Days = today.plusDays(15);

	assertFalse(RoomScheduler.isStartDate30DaysAfterCurrentDate(dateAfterTodayNotExceeding30Days.toString(), today.toString()));
	assertTrue(RoomScheduler.isStartDate30DaysAfterCurrentDate(dateAfter30DaysFromToday.toString(), today.toString()));
    }
    
    /**
     * testIsSameDateTimeDurationBooked	Tests whether the same date time duration is already booked or not
     */
    @Test
    public void testIsSameDateTimeDurationBooked() {
	// Same Date time duration booked
	assertTrue(RoomScheduler.isSameDateTimeDurationBooked(
		this.roomList, 
		"CodingRoom", 
		this.meetingList.get(0).getStartDate(), 
		this.meetingList.get(0).getStartTime(),
		this.meetingList.get(0).getStopTime()
		));
	
	// Different Date with same time duration scheduled
	assertFalse(RoomScheduler.isSameDateTimeDurationBooked(
		this.roomList, 
		"CodingRoom", 
		LocalDate.now().plusDays(15).toString(), 
		this.meetingList.get(0).getStartTime(),
		this.meetingList.get(0).getStopTime()
		));
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
