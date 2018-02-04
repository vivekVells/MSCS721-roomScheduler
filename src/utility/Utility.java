package utility;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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
}
