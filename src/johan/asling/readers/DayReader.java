package johan.asling.readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Vector;

import johan.asling.day.Day;

public class DayReader{
	public static Vector<Day> readDays(String fileName) throws FileNotFoundException, IOException, NumberFormatException {
		Vector<Day> days = new Vector<Day>();
		int lineCounter = 1;
		try(FileReader fileReader = new FileReader(fileName)){
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();			//read first line and toss it away
			lineCounter++;
			while((line = bufferedReader.readLine()) != null){	//continue reading till end of file
				String[] parts = line.split(";");
				LocalDate date = getLocalDate(parts[0]);
				double euroRate = Double.parseDouble(parts[1]);
				double dollarRate = Double.parseDouble(parts[2]);
				double poundRate = Double.parseDouble(parts[3]);
				double frankRate = Double.parseDouble(parts[4]);
				double yuanRate = Double.parseDouble(parts[5]);
				days.add(new Day(date, euroRate, dollarRate, poundRate, frankRate, yuanRate));
				lineCounter++;
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Incorrect file name or path");
		} catch (NumberFormatException e){
			throw new NumberFormatException("Error parsing double, check " + fileName + " at line " + lineCounter); 
		}
		return days;
	}

	private static LocalDate getLocalDate(String string) {
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM dd, uuuu", Locale.ENGLISH);
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d, uuuu", Locale.ENGLISH);
		LocalDate myDate;
		try{
			myDate = LocalDate.parse(string, formatter1);
		}catch(Exception e){
			myDate = LocalDate.parse(string, formatter2);
		}
		return myDate;
	}
	
	
}