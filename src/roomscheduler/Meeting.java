/**
 * @author <a href="https://github.com/vivekVells">Vivek Vellaiyappan Surulimuthu</a>
 * @version 1.8
 */

/**
 * package to contain all room schedule activities 
 */
package roomscheduler;

public class Meeting {
	
	private String startDate = null;
	private String startTime = null;
	private String stopTime = null;
	private String subject = null;

	
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

	public String toString() {
		return getStartDate() + " | " + getSubject() + ": " + this.getStartTime().toString() + " - " + this.getStopTime();
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
