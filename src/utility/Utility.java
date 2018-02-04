package utility;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Utility {
	/**
	 * clearScreen() function as of now works only in windows to clear the previous statements available in console window
	 */
	public static void clearScreen() {  
    try {
        if (System.getProperty("os.name").contains("Windows"))
			try {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			Runtime.getRuntime().exec("clear");
    } catch (IOException ex) {System.out.println(ex);}
	}
	
	/**
	 * sleepFor To make the execution of next statement after given arg seconds
	 * 
	 * @param seconds
	 */
	public static void sleepFor(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * getCurrentTime			To provide current time. This is based on current locale machine time...
	 * 
	 * @return returns string of current time in a pattern "HH:mm:ss"
	 */
	public static String getCurrentTime() {
		// have to use pattern logic here. 
		LocalTime lt = LocalTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		return dtf.format(lt);
	}
	
	/**
	 * getCurrentDate			To provide current date. This is based on current locale machine time...
	 * 
	 * @return		returns string of current date in a pattern "yyyy-MM-dd"
	 */
	public static String getCurrentDate() {
		// have to use pattern logic here. 
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return dtf.format(ldt);
	}
	
	/**
	 * getCurrentDateTime			To provide current date and time. This is based on current locale machine time...
	 * @return
	 */
	public static String getCurrentDateTime() {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:MM:ss");
		return dtf.format(ldt);
	}
	
	/**
	 * validateDate			Validation of date over the inputed date
	 * 
	 * @param date
	 * @return		returns true if the received string arg is valid date; else false
	 */
	public static boolean isValidDate(String date) {
		try {
			LocalDate.parse(date);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	/**
	 * validateTime			Validation of time over the inputed time
	 * 
	 * @param time
	 * @return		returns true if the received string arg is valid time; else false
	 */
	public static boolean isValidTime(String time) {
		try {
			LocalTime.parse(time);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
	
	/**
	 * isDateBefore			Checks whether arg date 1 is before arg date 2
	 * 
	 * @param date1
	 * @param date2
	 * @return		returns true if arg date1 is before arg date 2
	 */
	public static boolean isDateBefore(String date1, String date2) {
		LocalDate a = LocalDate.of(Integer.valueOf(date1.split("-")[0]), Integer.valueOf(date1.split("-")[1]), Integer.valueOf(date1.split("-")[2]));
		LocalDate b = LocalDate.of(Integer.valueOf(date2.split("-")[0]), Integer.valueOf(date2.split("-")[1]), Integer.valueOf(date2.split("-")[2]));
		return a.isBefore(b);
	}
	
	/**
	 * getDateDiffInDays			Used to get date difference between two dates in days
	 * 
	 * @param date1
	 * @param date2
	 * @return		returns date difference between two dates in days 
	 */
	public static long getDateDiffInDays(String date1, String date2) {
		LocalDate a = LocalDate.of(Integer.valueOf(date1.split("-")[0]), Integer.valueOf(date1.split("-")[1]), Integer.valueOf(date1.split("-")[2]));
		LocalDate b = LocalDate.of(Integer.valueOf(date2.split("-")[0]), Integer.valueOf(date2.split("-")[1]), Integer.valueOf(date2.split("-")[2]));
		return ChronoUnit.DAYS.between(a, b);
	}
	
	public static long getTimeDiffInMinutes(String time1, String time2) {
	  // long timeDiff = LocalTime.parse(startTime).until(LocalTime.parse(endTime), MINUTES);
		// long timeDiff = MINUTES.between(LocalTime.parse(startTime), LocalTime.parse(endTime));
		return Duration.between(LocalTime.parse(time1), LocalTime.parse(time2)).toMinutes();
	}

	public static boolean isTargetBetweenStartAndStop(String targetTime, String startTime, String stopTime) {
		if (LocalTime.parse(targetTime).isAfter(LocalTime.parse(startTime)) && LocalTime.parse(targetTime).isBefore(LocalTime.parse(stopTime))) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isTargetBetweenStartAndStopExtend(String targetTime1, String targetTime2, String startTime, String stopTime) {
		if (LocalTime.parse(targetTime1).isBefore(LocalTime.parse(startTime)) && LocalTime.parse(targetTime1).isBefore(LocalTime.parse(stopTime)) 
				&& LocalTime.parse(targetTime2).isAfter(LocalTime.parse(startTime)) && LocalTime.parse(targetTime2).isAfter(LocalTime.parse(stopTime))) {
			return true;
		} else {
			return false;
		}
	}
}
