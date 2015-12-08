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
		/*
		För 2015:
		EUR: 9,3649
		USD: 8,4263
		GBP: 12,9051
		CHF: 8,7889
		CNY: 1,3580
		
		2014:
		EUR: 9,0987
		USD: 6,8636
		GBP: 11,2967
		CHF: 7,4923
		CNY: 1,1170
		
		2013:
		EUR: 8,9504
		USD: 6,5455
		GBP: 10,7080
		CHF: 7,3031
		CNY: 1,0699
		 */
		assertEquals(new BigDecimal("9.3649"), dayModel.getYearAverage("euro", 2015));
		assertEquals(new BigDecimal("8.4263"), dayModel.getYearAverage("dollar", 2015));
		assertEquals(new BigDecimal("12.9051"), dayModel.getYearAverage("pound", 2015));
		assertEquals(new BigDecimal("8.7889"), dayModel.getYearAverage("frank", 2015));
		assertEquals(new BigDecimal("1.3580"), dayModel.getYearAverage("yuan", 2015));
		
		assertEquals(new BigDecimal("9.0987"), dayModel.getYearAverage("euro", 2014));
		assertEquals(new BigDecimal("6.8636"), dayModel.getYearAverage("dollar", 2014));
		assertEquals(new BigDecimal("11.2967"), dayModel.getYearAverage("pound", 2014));
		//assertEquals(new BigDecimal("7.4923"), dayModel.getYearAverage("frank", 2014));
		assertEquals(new BigDecimal("1.1170"), dayModel.getYearAverage("yuan", 2014));

		assertEquals(new BigDecimal("8.9504"), dayModel.getYearAverage("euro", 2013));
		assertEquals(new BigDecimal("6.5455"), dayModel.getYearAverage("dollar", 2013));
		assertEquals(new BigDecimal("10.7080"), dayModel.getYearAverage("pound", 2013));
		//assertEquals(new BigDecimal("7.3031"), dayModel.getYearAverage("frank", 2013));
		assertEquals(new BigDecimal("1.0699"), dayModel.getYearAverage("yuan", 2013));
	}
	
	@Test
	public void getMonthAverage(){
		/*
		Mar 2014:
		EUR: 8,8666
		USD: 6,4135
		GBP: 10,6672
		CHF: 7,2848
		CNY: 1,0439
		
		Sep 2014:
		EUR: 9,1952
		USD: 7,1315
		GBP: 11,6292
		CHF: 7,6135
		CNY: 1,1595
		
		Feb 2015:
		EUR: 9,4854
		USD: 8,3568
		GBP: 12,8133
		CHF: 8,9424
		CNY: 1,3591
		 */
		assertEquals(new BigDecimal("9.4854"), dayModel.getMonthAverage("euro", 2015, 2));
		assertEquals(new BigDecimal("8.3568"), dayModel.getMonthAverage("dollar", 2015, 2));
		assertEquals(new BigDecimal("12.8133"), dayModel.getMonthAverage("pound", 2015, 2));
		assertEquals(new BigDecimal("8.9424"), dayModel.getMonthAverage("frank", 2015, 2));
		assertEquals(new BigDecimal("1.3591"), dayModel.getMonthAverage("yuan", 2015, 2));

		assertEquals(new BigDecimal("9.1952"), dayModel.getMonthAverage("euro", 2014, 9));
		assertEquals(new BigDecimal("7.1315"), dayModel.getMonthAverage("dollar", 2014, 9));
		assertEquals(new BigDecimal("11.6292"), dayModel.getMonthAverage("pound", 2014, 9));
		assertEquals(new BigDecimal("7.6135"), dayModel.getMonthAverage("frank", 2014, 9));
		assertEquals(new BigDecimal("1.1595"), dayModel.getMonthAverage("yuan", 2014, 9));

		assertEquals(new BigDecimal("8.8666"), dayModel.getMonthAverage("euro", 2014, 3));
		assertEquals(new BigDecimal("6.4135"), dayModel.getMonthAverage("dollar", 2014, 3));
		assertEquals(new BigDecimal("10.6672"), dayModel.getMonthAverage("pound", 2014, 3));
		assertEquals(new BigDecimal("7.2848"), dayModel.getMonthAverage("frank", 2014, 3));
		assertEquals(new BigDecimal("1.0439"), dayModel.getMonthAverage("yuan", 2014, 3));

	}
	
	@Test
	public void testAverage(){
		/*
		7 Nov 2014 tom 25 Jan 2015
		EUR: 9,3734
		USD: 7,7072
		GBP: 11,9571
		CHF: 8,0046
		CNY: 1,2562
		 */
		ar.init();
		LocalDate fromDate = LocalDate.parse("2014-11-07");
		LocalDate toDate = LocalDate.parse("2015-01-25");
		assertEquals(new BigDecimal("9.3734"), dayModel.getAverage("euro", fromDate, toDate));
		assertEquals(new BigDecimal("7.7072"), dayModel.getAverage("dollar", fromDate, toDate));
		assertEquals(new BigDecimal("11.9571"), dayModel.getAverage("pound", fromDate, toDate));
		assertEquals(new BigDecimal("8.0046"), dayModel.getAverage("frank", fromDate, toDate));
		assertEquals(new BigDecimal("1.2562"), dayModel.getAverage("yuan", fromDate, toDate));

	}
	
	@Test
	public void testGetYearHigh(){
		/*
		2015:
		EUR: 9,6339 - 9,1075
		USD: 8,8261 - 7,8064
		GBP: 13,5870 - 12,0192
		CHF: 9,4967 - 7,8431
		CNY: 1,4434 - 1,2698
		
		2014: 
		EUR: 9,5602 - 8,7566
		USD: 7,8309 - 6,3492
		GBP: 12,1803 - 10,5485
		CHF: 7,9302 - 7,1073
		CNY: 1,2752 - 1,0325
		
		2013: 
		EUR: 9,0580 - 8,8417
		USD: 6,5920 - 6,4226
		GBP: 10,7643 - 10,6157
		CHF: 7,4129 - 7,1994
		CNY: 1,0785 - 1,0516
		 */
		ar.init();
		assertEquals(new BigDecimal("9.6339"), dayModel.getYearHigh("euro", 2015));
		assertEquals(new BigDecimal("8.8261"), dayModel.getYearHigh("dollar", 2015));
		assertEquals(new BigDecimal("13.5870"), dayModel.getYearHigh("pound", 2015));
		assertEquals(new BigDecimal("9.4967"), dayModel.getYearHigh("frank", 2015));
		assertEquals(new BigDecimal("1.4434"), dayModel.getYearHigh("yuan", 2015));

		assertEquals(new BigDecimal("9.5602"), dayModel.getYearHigh("euro", 2014));
		assertEquals(new BigDecimal("7.8309"), dayModel.getYearHigh("dollar", 2014));
		assertEquals(new BigDecimal("12.1803"), dayModel.getYearHigh("pound", 2014));
		assertEquals(new BigDecimal("7.9302"), dayModel.getYearHigh("frank", 2014));
		assertEquals(new BigDecimal("1.2752"), dayModel.getYearHigh("yuan", 2014));
		
		assertEquals(new BigDecimal("9.0580"), dayModel.getYearHigh("euro", 2013));
		assertEquals(new BigDecimal("6.5920"), dayModel.getYearHigh("dollar", 2013));
		assertEquals(new BigDecimal("10.7643"), dayModel.getYearHigh("pound", 2013));
		assertEquals(new BigDecimal("7.4129"), dayModel.getYearHigh("frank", 2013));
		assertEquals(new BigDecimal("1.0785"), dayModel.getYearHigh("yuan", 2013));


	}
	
	@Test
	public void testGetYearLow(){
		ar.init();
		
		/*
		2015:
		EUR: 9,6339 - 9,1075
		USD: 8,8261 - 7,8064
		GBP: 13,5870 - 12,0192
		CHF: 9,4967 - 7,8431
		CNY: 1,4434 - 1,2698
		
		2014: 
		EUR: 9,5602 - 8,7566
		USD: 7,8309 - 6,3492
		GBP: 12,1803 - 10,5485
		CHF: 7,9302 - 7,1073
		CNY: 1,2752 - 1,0325
		
		2013: 
		EUR: 9,0580 - 8,8417
		USD: 6,5920 - 6,4226
		GBP: 10,7643 - 10,6157
		CHF: 7,4129 - 7,1994
		CNY: 1,0785 - 1,0516
		*/
		assertEquals(new BigDecimal("9.1075"), dayModel.getYearLow("euro", 2015));
		assertEquals(new BigDecimal("7.8064"), dayModel.getYearLow("dollar", 2015));
		assertEquals(new BigDecimal("12.0192"), dayModel.getYearLow("pound", 2015));
		assertEquals(new BigDecimal("7.8431"), dayModel.getYearLow("frank", 2015));
		assertEquals(new BigDecimal("1.2698"), dayModel.getYearLow("yuan", 2015));

		assertEquals(new BigDecimal("8.7566"), dayModel.getYearLow("euro", 2014));
		assertEquals(new BigDecimal("6.3492"), dayModel.getYearLow("dollar", 2014));
		assertEquals(new BigDecimal("10.5485"), dayModel.getYearLow("pound", 2014));
		assertEquals(new BigDecimal("7.1073"), dayModel.getYearLow("frank", 2014));
		assertEquals(new BigDecimal("1.0325"), dayModel.getYearLow("yuan", 2014));
		
		assertEquals(new BigDecimal("8.8417"), dayModel.getYearLow("euro", 2013));
		assertEquals(new BigDecimal("6.4226"), dayModel.getYearLow("dollar", 2013));
		assertEquals(new BigDecimal("10.6157"), dayModel.getYearLow("pound", 2013));
		assertEquals(new BigDecimal("7.1994"), dayModel.getYearLow("frank", 2013));
		assertEquals(new BigDecimal("1.0516"), dayModel.getYearLow("yuan", 2013));



	}
	
	@Test
	public void testGetMonthHigh(){
		ar.init();
		/*
		Dec 2013
		EUR: 9,0580 - 8,8417
		USD: 6,5920 - 6,4226
		GBP: 10,7643 - 10,6157
		CHF: 7,4129 - 7,1994
		CNY: 1,0785 - 1,0516
		
		Feb 2014
		EUR: 8,9767 - 8,7719
		USD: 6,5488 - 6,4392
		GBP: 10,8932 - 10,6157
		CHF: 7,3584 - 7,1633
		CNY: 1,0722 - 1,0543
		
		Jun 2015
		EUR: 9,3897 - 9,1996
		USD: 8,5543 - 8,0972
		GBP: 13,0890 - 12,7226
		CHF: 9,0662 - 8,7796
		CNY: 1,4010 - 1,3264
		 */
		assertEquals(new BigDecimal("9.3897"), dayModel.getMonthHigh("euro", 2015, 6));
		assertEquals(new BigDecimal("8.5543"), dayModel.getMonthHigh("dollar", 2015, 6));
		assertEquals(new BigDecimal("13.0890"), dayModel.getMonthHigh("pound", 2015, 6));
		assertEquals(new BigDecimal("9.0662"), dayModel.getMonthHigh("frank", 2015, 6));
		assertEquals(new BigDecimal("1.4010"), dayModel.getMonthHigh("yuan", 2015, 6));

		assertEquals(new BigDecimal("8.9767"), dayModel.getMonthHigh("euro", 2014, 2));
		assertEquals(new BigDecimal("6.5488"), dayModel.getMonthHigh("dollar", 2014, 2));
		assertEquals(new BigDecimal("10.8932"), dayModel.getMonthHigh("pound", 2014, 2));
		assertEquals(new BigDecimal("7.3584"), dayModel.getMonthHigh("frank", 2014, 2));
		assertEquals(new BigDecimal("1.0722"), dayModel.getMonthHigh("yuan", 2014, 2));

		assertEquals(new BigDecimal("9.0580"), dayModel.getMonthHigh("euro", 2013, 12));
		assertEquals(new BigDecimal("6.5920"), dayModel.getMonthHigh("dollar", 2013, 12));
		assertEquals(new BigDecimal("10.7643"), dayModel.getMonthHigh("pound", 2013, 12));
		assertEquals(new BigDecimal("7.4129"), dayModel.getMonthHigh("frank", 2013, 12));
		assertEquals(new BigDecimal("1.0785"), dayModel.getMonthHigh("yuan", 2013, 12));



		
	}
	
	@Test
	public void testGetMonthLow(){
		ar.init();
		/*
		 Dec 2013
		EUR: 9,0580 - 8,8417
		USD: 6,5920 - 6,4226
		GBP: 10,7643 - 10,6157
		CHF: 7,4129 - 7,1994
		CNY: 1,0785 - 1,0516
		
		Feb 2014
		EUR: 8,9767 - 8,7719
		USD: 6,5488 - 6,4392
		GBP: 10,8932 - 10,6157
		CHF: 7,3584 - 7,1633
		CNY: 1,0722 - 1,0543
		
		Jun 2015
		EUR: 9,3897 - 9,1996
		USD: 8,5543 - 8,0972
		GBP: 13,0890 - 12,7226
		CHF: 9,0662 - 8,7796
		CNY: 1,4010 - 1,3264
		 */
		assertEquals(new BigDecimal("9.1996"), dayModel.getMonthLow("euro", 2015, 6));
		assertEquals(new BigDecimal("8.0972"), dayModel.getMonthLow("dollar", 2015, 6));
		assertEquals(new BigDecimal("12.7226"), dayModel.getMonthLow("pound", 2015, 6));
		assertEquals(new BigDecimal("8.7796"), dayModel.getMonthLow("frank", 2015, 6));
		assertEquals(new BigDecimal("1.3264"), dayModel.getMonthLow("yuan", 2015, 6));

		assertEquals(new BigDecimal("8.7719"), dayModel.getMonthLow("euro", 2014, 2));
		assertEquals(new BigDecimal("6.4392"), dayModel.getMonthLow("dollar", 2014, 2));
		assertEquals(new BigDecimal("10.6157"), dayModel.getMonthLow("pound", 2014, 2));
		assertEquals(new BigDecimal("7.1633"), dayModel.getMonthLow("frank", 2014, 2));
		assertEquals(new BigDecimal("1.0543"), dayModel.getMonthLow("yuan", 2014, 2));

		assertEquals(new BigDecimal("8.8417"), dayModel.getMonthLow("euro", 2013, 12));
		assertEquals(new BigDecimal("6.4226"), dayModel.getMonthLow("dollar", 2013, 12));
		assertEquals(new BigDecimal("10.6157"), dayModel.getMonthLow("pound", 2013, 12));
		assertEquals(new BigDecimal("7.1994"), dayModel.getMonthLow("frank", 2013, 12));
		assertEquals(new BigDecimal("1.0516"), dayModel.getMonthLow("yuan", 2013, 12));
	}
	
	@Test
	public void testGetHighMark(){
		/*
		17 jan 2014 tom 3 mar 2015
		EUR: 9,6061 - 8,7566
		USD: 8,4246 - 6,3492
		GBP: 12,9870 - 10,5485
		CHF: 9,4967 - 7,1073
		CNY: 1,3723 - 1,0325
		*/
		ar.init();
		assertEquals(new BigDecimal("9.6061"), dayModel.getHighMark("euro", LocalDate.parse("2014-01-17"), LocalDate.parse("2015-03-03")));
		assertEquals(new BigDecimal("8.4246"), dayModel.getHighMark("dollar", LocalDate.parse("2014-01-17"), LocalDate.parse("2015-03-03")));
		assertEquals(new BigDecimal("12.9870"), dayModel.getHighMark("pound", LocalDate.parse("2014-01-17"), LocalDate.parse("2015-03-03")));
		assertEquals(new BigDecimal("9.4967"), dayModel.getHighMark("frank", LocalDate.parse("2014-01-17"), LocalDate.parse("2015-03-03")));
		assertEquals(new BigDecimal("1.3723"), dayModel.getHighMark("yuan", LocalDate.parse("2014-01-17"), LocalDate.parse("2015-03-03")));
	}
	
	@Test
	public void testGetLowMark(){
		/*
		17 jan 2014 tom 3 mar 2015
		EUR: 9,6061 - 8,7566
		USD: 8,4246 - 6,3492
		GBP: 12,9870 - 10,5485
		CHF: 9,4967 - 7,1073
		CNY: 1,3723 - 1,0325
		*/
		ar.init();
		assertEquals(new BigDecimal("8.7566"), dayModel.getLowMark("euro", LocalDate.parse("2014-01-17"), LocalDate.parse("2015-03-03")));
		assertEquals(new BigDecimal("6.3492"), dayModel.getLowMark("dollar", LocalDate.parse("2014-01-17"), LocalDate.parse("2015-03-03")));
		assertEquals(new BigDecimal("10.5485"), dayModel.getLowMark("pound", LocalDate.parse("2014-01-17"), LocalDate.parse("2015-03-03")));
		assertEquals(new BigDecimal("7.1073"), dayModel.getLowMark("frank", LocalDate.parse("2014-01-17"), LocalDate.parse("2015-03-03")));
		assertEquals(new BigDecimal("1.0325"), dayModel.getLowMark("yuan", LocalDate.parse("2014-01-17"), LocalDate.parse("2015-03-03")));
	}
	
	@Test
	public void testGetMaxDeltaCurrency(){
		/*
		 * 
		 20151115 tom 20151105: USD
			20150519 - 20140911: GBP
		 */
		ar.init();
		assertEquals("USD", dayModel.getMaxDeltaCurrency(LocalDate.parse("2015-11-05"), LocalDate.parse("2015-11-15")));
		assertEquals("GBP", dayModel.getMaxDeltaCurrency(LocalDate.parse("2014-09-11"), LocalDate.parse("2015-05-19")));
	}
	
	@Test
	public void testGetMaxVolatilityCurrency(){
		/*
		 * 	20151115 tom 20151016: GBP
			20150519 - 20140911: CHF
		 */
		assertEquals("GBP", dayModel.getMaxVolatilityCurrency(LocalDate.parse("2015-10-16"), LocalDate.parse("2015-11-15")));
		assertEquals("CHF", dayModel.getMaxVolatilityCurrency(LocalDate.parse("2014-09-11"), LocalDate.parse("2015-05-19")));
	}
	
	@Test
	public void testGetMaxVolatalityWeek(){
		/*
		 * 	2015
			EUR: 9
			USD: 18
			GBP: 18
			CHF: 3
			CNY: 33
		 */
		assertEquals(9, dayModel.getMaxVolatilityWeek("euro", 2015));
		//assertEquals(18, dayModel.getMaxVolatilityWeek("dollar", 2015));
		assertEquals(18, dayModel.getMaxVolatilityWeek("pound", 2015));
		assertEquals(3, dayModel.getMaxVolatilityWeek("frank", 2015));
		assertEquals(33, dayModel.getMaxVolatilityWeek("yuan", 2015));
	}
	
	@Test
	public void testGetAverage(){
		/*
		 * 	2015-11-14 tom 2015-11-30 - monday
			EUR:9,2737
			
			2015-11-16 tom 2015-11-30 - monday
			USD:8,7260
			
			2015-11-17 tom 2015-11-30 - monday
			GBP: 13,1755
			
			2015-11-17 tom 2015-11-29 - monday
			CHF: 8,5543
			
			2014-02-02 tom 2015-05-15 - wednesday
			CNY: 1,1927 (count= 67, sum=70,9088)
		 */
		;
	}
}
