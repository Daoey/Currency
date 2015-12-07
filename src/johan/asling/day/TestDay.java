package johan.asling.day;

import org.junit.Test;

import johan.asling.GUI.ApplicationRunner;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestDay {
	
	DayModel dayModel = new DayModel();
	ApplicationRunner ar = new ApplicationRunner();
	
	@Test
	public void testShowValuesAsXEqualsSek(){
		BigDecimal euroRate = new BigDecimal("0.1");
		BigDecimal dollarRate = new BigDecimal("0.1082");
		BigDecimal poundRate = new BigDecimal("1.3");
		BigDecimal frankRate = BigDecimal.ZERO;
		BigDecimal yuanRate = new BigDecimal("0.007");
				
		assertEquals(new BigDecimal("10.0000"), DayModel.getValuesAsXEqualsSek(euroRate));
		assertEquals(new BigDecimal("9.2421"), DayModel.getValuesAsXEqualsSek(dollarRate));
		assertEquals(new BigDecimal("0.7692"), DayModel.getValuesAsXEqualsSek(poundRate));
		assertEquals(BigDecimal.ZERO, DayModel.getValuesAsXEqualsSek(frankRate));
		assertEquals(new BigDecimal("142.8571"), DayModel.getValuesAsXEqualsSek(yuanRate));

	}
	
	
	@Test
	public void testGetYearAverage(){
		;
	}
	
	@Test
	public void getMonthAverage(){
		;
	}
	
	@Test
	public void testAverage(){
		ar.init();
		LocalDate fromDate = LocalDate.parse("2014-11-10");
		LocalDate toDate = LocalDate.parse("2014-11-12");
		assertEquals(new BigDecimal("0.1085"), dayModel.getAverage("euro", fromDate, toDate));
	}
	
	@Test
	public void testGetYearHigh(){
		ar.init();
		assertEquals(new BigDecimal("0.1038"), dayModel.getYearHigh("euro", 2015));
	}
	
	@Test
	public void testGetYearLow(){
		ar.init();
		assertEquals(new BigDecimal("0.1098"), dayModel.getYearLow("euro", 2015));
	}
	
	@Test
	public void testGetMonthHigh(){
		ar.init();
		assertEquals(new BigDecimal("0.1051"), dayModel.getMonthHigh("euro", 2015, 9));
	}
	
	@Test
	public void testGetMonthLow(){
		ar.init();
		assertEquals(new BigDecimal("0.1073"), dayModel.getMonthLow("euro", 2015, 9));
	}
	
	@Test
	public void testGetHighMark(){
		ar.init();
		assertEquals(new BigDecimal("0.0850"), dayModel.getHighMark("pound", LocalDate.parse("2014-11-11"), LocalDate.parse("2014-11-18")));
	}
	
	@Test
	public void testGetLowMark(){
		ar.init();
		assertEquals(new BigDecimal("0.8316"), dayModel.getLowMark("yuan", LocalDate.parse("2014-11-11"), LocalDate.parse("2014-11-18")));
	}
}
