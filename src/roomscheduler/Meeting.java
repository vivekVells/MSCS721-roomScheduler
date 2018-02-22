/**
 * @author <a href="https://github.com/vivekVells">Vivek Vellaiyappan Surulimuthu</a>
 * @version 1.8
 */

/**
 * package to contain all room schedule activities 
 */
package roomscheduler;

/**
 * Meeting			class contains all spare parts for a room to hold a meeting
 *
 */
public class Meeting {
	
	private String startDate = null;
	private String startTime = null;
	private String stopTime = null;
	private String subject = null;

	/**
	 * Meeting			Parametrized constructor to initialize the passed arg values to the class member variables
	 * 
	 * @param newStartDate
	 * @param newStartTime
	 * @param newEndTime
	 * @param newSubject
	 */
	public Meeting(String newStartDate, String newStartTime, String newEndTime, String newSubject) {
		setStartDate(newStartDate);
		setStartTime(newStartTime);
		setStopTime(newEndTime);
		
		if (newSubject.isEmpty()) {
			setSubject("N/A"); 
		}	else {
			setSubject(newSubject);
		}
	}

	/**
	 * toString		Predefined return string type 
	 * 
	 * @return		returns the meeting information entities
	 */
	public String toString() {
		return getStartDate() + " | " + getSubject() + ": " + this.getStartTime() + " - " + this.getStopTime();
	}
	
	/**
	 * setStartDate			Sets the start date
	 * 
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * getStartDate		Provides the start date of meeting reference (this)
	 * 
	 * @return		returns start date of meeting reference (this)
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * getStartTime			Provides the start time of meeting reference (this)
	 * 
	 * @return		returns start time of meeting reference (this)
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * setStartTime			Sets the start time
	 * 
	 * @param startStartTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * getStopTime			Provides the stop time of meeting reference (this)
	 * 
	 * @return		returns stop time of meeting reference (this)
	 */
	public String getStopTime() {
		return stopTime;
	}

	/**
	 * setStopTime			Sets the stop time
	 * 
	 * @param setStopTime
	 */
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * getSubject			Provides the subject of meeting reference (this)
	 * 
	 * @return		returns subject of meeting reference (this)
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * setSubject			Sets the subject
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

}
