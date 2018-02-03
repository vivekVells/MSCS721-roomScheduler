package utility;

import java.io.IOException;

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
}
