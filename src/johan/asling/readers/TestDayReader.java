package johan.asling.readers;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Vector;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import johan.asling.day.Day;

public class TestDayReader {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testReadDaysCorrectFile(){
		Vector<Day> daysRead = new Vector<Day>();
		try {
			daysRead = DayReader.readDays("textFiles/testFiles/testRates.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			e.printStackTrace();
		}
		
		assertEquals(3, daysRead.size());
		assertEquals("0.1083", daysRead.get(0).getEuroRate());
		assertEquals("0.1144" ,daysRead.get(1).getDollarRate());
		assertEquals(LocalDate.parse("2015-11-30"), daysRead.get(0).getDate());
		assertEquals(LocalDate.parse("2015-11-28"), daysRead.get(2).getDate());
		assertEquals("0.1179", daysRead.get(1).getFrankRate());
		assertEquals("0.0761", daysRead.get(2).getPoundRate());
		assertEquals("0.7319", daysRead.get(2).getYuanRate());
	}
	
	@Test
	public void testReadDaysIncorrectFileName() throws NumberFormatException, IOException{
		thrown.expect(FileNotFoundException.class);
		thrown.expectMessage("Incorrect file name or path");
		@SuppressWarnings("unused")	//suppressed since it is used to check for exception
		Vector<Day> daysRead = DayReader.readDays("textfiles/testFiles/wrongFileName.csv");
	}
}
