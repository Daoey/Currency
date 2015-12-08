package johan.asling.day;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

import johan.asling.GUI.ApplicationRunner;

public class DayModel {

	/**Changes the display of rates from 1 SEK is worth x OTHERCURRENCY 
	 * to 1 OTHERCURRENCY is worth x SEK.
	 * @param rate The ratio between SEK and OTHERCURENCY.
	 * @return The ratio between OTHERCURRENCY and SEK. Invalid rates return -1.
	 */
	public static BigDecimal getValuesAsXEqualsSek(BigDecimal rate) {
		if(rate.compareTo(BigDecimal.ZERO)<=0)
			return BigDecimal.ZERO;
		return BigDecimal.ONE.divide(rate, 4, RoundingMode.HALF_UP);
	}
	
	/**Get average conversion rate this year in the desired currency
	 * 
	 * @param currency	The currency you would like to see the conversion rate of
	 * @param year	The year
	 * @return	The average conversion rate or 0 if no data on desired year or currency
	 */
	public BigDecimal getYearAverage(String currency, int year){
		int dayCounter = 0;
		BigDecimal rates = BigDecimal.ZERO;
		if(currency=="euro"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate())));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="dollar"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate())));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="pound"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate())));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="frank"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate())));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="yuan"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate())));
					dayCounter++;
				}
					
			}
		}
		if(dayCounter == 0){
			return BigDecimal.ZERO;
		}
		rates = rates.divide(new BigDecimal(dayCounter), 4, RoundingMode.HALF_UP);
		return rates;
		
	}
	
	/**Get average conversion rate this year and month in the desired currency 
	 * 
	 * @param currency The currency you would like to see the conversion rate of
	 * @param year The year
	 * @param month The month
	 * @return
	 */
	public BigDecimal getMonthAverage(String currency, int year, int month){
		
		int dayCounter = 0;
		BigDecimal rates = BigDecimal.ZERO;
		
		if(currency=="euro"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate())));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="dollar"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate())));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="pound"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate())));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="frank"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate())));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="yuan"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate())));
					dayCounter++;
				}
					
			}
		}
		if(dayCounter==0)
			return BigDecimal.ZERO;
		
		rates = rates.divide(new BigDecimal(dayCounter), 4, RoundingMode.HALF_UP);
		return rates;
	}
	
	/**This method calculates the average rate of a currency between two dates. Both dates are inclusively.
	 * 
	 * @param currency The currency you would like to see the conversion rate of
	 * @param fromDate From this date
	 * @param toDate To this date
	 * @return
	 */
	public BigDecimal getAverage(String currency, LocalDate fromDate, LocalDate toDate){
		int dayCounter = 0;
		BigDecimal rates = BigDecimal.ZERO;
		if(fromDate.isAfter(toDate))
			return BigDecimal.ZERO;
		if(currency=="euro"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
						|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate())));
					dayCounter++;
				}
			}
		}
		if(currency=="dollar"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
						|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate())));
					dayCounter++;
				}
			}
		}
		if(currency=="pound"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
						|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate())));
					dayCounter++;
				}
			}
		}
		if(currency=="frank"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
						|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate())));
					dayCounter++;
				}
			}
		}
		if(currency=="yuan"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
						|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
					rates = rates.add(DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate())));
					dayCounter++;
				}
			}
		}
		if(dayCounter ==0)
			return BigDecimal.ZERO;
		
		rates = rates.divide(new BigDecimal(dayCounter), 4, RoundingMode.HALF_UP);
		return rates;
	}

	/**This method returns the highest rate this year
	 * 
	 * @param currency	The currency you would like to see the conversion rate of
	 * @param year	The year
	 * @return	Highest rate this year or 0 if can't find any data
	 */
	 public BigDecimal getYearHigh(String currency, int year){
		 
		BigDecimal highest = BigDecimal.ZERO;
		 
		if(currency=="euro"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					if(highest==BigDecimal.ZERO)
						highest = new BigDecimal(ApplicationRunner.days.get(i).getEuroRate());
					highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate()));
				}
			}
		}
		
		else if(currency=="dollar"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					if(highest==BigDecimal.ZERO)
						highest = new BigDecimal(ApplicationRunner.days.get(i).getDollarRate());
					highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate()));
				}
			}
		}
		
		else if(currency=="pound"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					if(highest==BigDecimal.ZERO)
						highest = new BigDecimal(ApplicationRunner.days.get(i).getPoundRate());
					highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate()));
				}
			}
		}
		
		else if(currency=="frank"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					if(highest==BigDecimal.ZERO)
						highest = new BigDecimal(ApplicationRunner.days.get(i).getFrankRate());
					highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate()));
				}
			}
		}
		
		else if(currency=="yuan"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					if(highest==BigDecimal.ZERO)
						highest = new BigDecimal(ApplicationRunner.days.get(i).getYuanRate());
					highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate()));
				}
			}
		}

		return DayModel.getValuesAsXEqualsSek(highest.setScale(4, RoundingMode.HALF_UP));
	 }
	 
	 
	 /**This method returns the lowest rate this year
		 * 
		 * @param currency	The currency you would like to see the conversion rate of
		 * @param year	The year
		 * @return	Lowest rate this year or 0 if can't find any data
		 */
	 public BigDecimal getYearLow(String currency, int year){
		
		BigDecimal lowest = BigDecimal.ZERO;
		 
		if(currency=="euro"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					if(lowest==BigDecimal.ZERO)
						lowest = new BigDecimal(ApplicationRunner.days.get(i).getEuroRate());
					lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate()));
				}
			}
		}
		
		else if(currency=="dollar"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					if(lowest==BigDecimal.ZERO)
						lowest = new BigDecimal(ApplicationRunner.days.get(i).getDollarRate());
					lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate()));
				}
			}
		}
		
		else if(currency=="pound"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					if(lowest==BigDecimal.ZERO)
						lowest = new BigDecimal(ApplicationRunner.days.get(i).getPoundRate());
					lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate()));
				}
			}
		}
		
		else if(currency=="frank"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					if(lowest==BigDecimal.ZERO)
						lowest = new BigDecimal(ApplicationRunner.days.get(i).getFrankRate());
					lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate()));
				}
			}
		}
		
		else if(currency=="yuan"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					if(lowest==BigDecimal.ZERO)
						lowest = new BigDecimal(ApplicationRunner.days.get(i).getYuanRate());
					lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate()));
				}
			}
		}
		 return DayModel.getValuesAsXEqualsSek(lowest.setScale(4, RoundingMode.HALF_UP));
	 }
	 
	 /**This method returns the highest rate this year and month
		 * 
		 * @param currency	The currency you would like to see the conversion rate of
		 * @param year	The year
		 * @param month The month
		 * @return	Highest rate this year and month or 0 if can't find any data
		 */
	 public BigDecimal getMonthHigh(String currency, int year, int month){
		 BigDecimal highest = BigDecimal.ZERO;
		 
		 if(currency=="euro"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					if(highest==BigDecimal.ZERO)
						highest = new BigDecimal(ApplicationRunner.days.get(i).getEuroRate());
					highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate()));
				}
			}
		 }
		 
		 else if(currency=="dollar"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					if(highest==BigDecimal.ZERO)
						highest = new BigDecimal(ApplicationRunner.days.get(i).getDollarRate());
					highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate()));
				}
			}
		 }
		 
		 else if(currency=="pound"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
						if(highest==BigDecimal.ZERO)
							highest = new BigDecimal(ApplicationRunner.days.get(i).getPoundRate());
						highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate()));
					}
				}
			 }
		 
		 else if(currency=="frank"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
						if(highest==BigDecimal.ZERO)
							highest = new BigDecimal(ApplicationRunner.days.get(i).getFrankRate());
						highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate()));
					}
				}
			 }
		 
		 else if(currency=="yuan"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
						if(highest==BigDecimal.ZERO)
							highest = new BigDecimal(ApplicationRunner.days.get(i).getYuanRate());
						highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate()));
					}
				}
			 }
		 
		 return DayModel.getValuesAsXEqualsSek(highest.setScale(4, RoundingMode.HALF_UP));
	 }
	 
	 /**This method returns the lowest rate this year and month
		 * 
		 * @param currency	The currency you would like to see the conversion rate of
		 * @param year	The year
		 * @param month The month
		 * @return	Lowest rate this year and month or 0 if can't find any data
		 */
	 public BigDecimal getMonthLow(String currency, int year, int month){
		 
		 BigDecimal lowest = BigDecimal.ZERO;
		 
		 if(currency=="euro"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					if(lowest==BigDecimal.ZERO)
						lowest = new BigDecimal(ApplicationRunner.days.get(i).getEuroRate());
					lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate()));
				}
			}
		 }
		
		 else if(currency=="dollar"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					if(lowest==BigDecimal.ZERO)
						lowest = new BigDecimal(ApplicationRunner.days.get(i).getDollarRate());
					lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate()));
				}
			}
		}
		 
		 else if(currency=="pound"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
						if(lowest==BigDecimal.ZERO)
							lowest = new BigDecimal(ApplicationRunner.days.get(i).getPoundRate());
						lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate()));
					}
				}
			}
		 
		 else if(currency=="frank"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
						if(lowest==BigDecimal.ZERO)
							lowest = new BigDecimal(ApplicationRunner.days.get(i).getFrankRate());
						lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate()));
					}
				}
			}
		 
		 else if(currency=="yuan"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
						if(lowest==BigDecimal.ZERO)
							lowest = new BigDecimal(ApplicationRunner.days.get(i).getYuanRate());
						lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate()));
					}
				}
			}
		 
		 return DayModel.getValuesAsXEqualsSek(lowest.setScale(4, RoundingMode.HALF_UP));
	 }
	 
	 /**This method returns the highest rate this period. Both dates are inclusively
		 * 
		 * @param currency	The currency you would like to see the conversion rate of
		 * @param fromDate	From this date
		 * @param toDate The To this date
		 * @return	Highest rate this period or 0 if can't find any data
		 */
	 public BigDecimal getHighMark(String currency, LocalDate fromDate, LocalDate toDate){
		 
		 BigDecimal highest = BigDecimal.ZERO;
		 
		 if(fromDate.isAfter(toDate))
			return BigDecimal.ZERO;
		 
		 if(currency=="euro"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
						|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
					if(highest==BigDecimal.ZERO)
						highest = new BigDecimal(ApplicationRunner.days.get(i).getEuroRate());
					highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate()));
				}
			}
		}
		 
		 else if(currency=="dollar"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
							|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
						if(highest==BigDecimal.ZERO)
							highest = new BigDecimal(ApplicationRunner.days.get(i).getDollarRate());
						highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate()));
					}
				}
			}
		 
		 else if(currency=="pound"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
							|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
						if(highest==BigDecimal.ZERO)
							highest = new BigDecimal(ApplicationRunner.days.get(i).getPoundRate());
						highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate()));
					}
				}
			}
		 
		 else if(currency=="frank"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
							|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
						if(highest==BigDecimal.ZERO)
							highest = new BigDecimal(ApplicationRunner.days.get(i).getFrankRate());
						highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate()));
					}
				}
			}
		 
		 else if(currency=="yuan"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
							|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
						if(highest==BigDecimal.ZERO)
							highest = new BigDecimal(ApplicationRunner.days.get(i).getYuanRate());
						highest = highest.min(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate()));
					}
				}
			}
		
		return DayModel.getValuesAsXEqualsSek(highest.setScale(4, RoundingMode.HALF_UP));
	 }
	 
	 /**This method returns the lowest rate this period. Both dates are inclusively
		 * 
		 * @param currency	The currency you would like to see the conversion rate of
		 * @param fromDate	From this date
		 * @param toDate The To this date
		 * @return	Highest rate this period or 0 if can't find any data
		 */
	 public BigDecimal getLowMark(String currency, LocalDate fromDate, LocalDate toDate){
		 
		 BigDecimal lowest = BigDecimal.ZERO;
		 
		 if(fromDate.isAfter(toDate))
			return BigDecimal.ZERO;
		 
		 if(currency=="euro"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
						|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
					if(lowest==BigDecimal.ZERO)
						lowest = new BigDecimal(ApplicationRunner.days.get(i).getEuroRate());
					lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate()));
				}
			}
		 }
		 
		 else if(currency=="dollar"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
							|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
						if(lowest==BigDecimal.ZERO)
							lowest = new BigDecimal(ApplicationRunner.days.get(i).getDollarRate());
						lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate()));
					}
				}
		}
		 
		 else if(currency=="pound"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
							|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
						if(lowest==BigDecimal.ZERO)
							lowest = new BigDecimal(ApplicationRunner.days.get(i).getPoundRate());
						lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate()));
					}
				}
		}
		 
		 else if(currency=="frank"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
							|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
						if(lowest==BigDecimal.ZERO)
							lowest = new BigDecimal(ApplicationRunner.days.get(i).getFrankRate());
						lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate()));
					}
				}
		}
		 
		 else if(currency=="yuan"){
				for(int i=0; i<ApplicationRunner.days.size(); i++){
					if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
							|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
						if(lowest==BigDecimal.ZERO)
							lowest = new BigDecimal(ApplicationRunner.days.get(i).getYuanRate());
						lowest = lowest.max(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate()));
					}
				}
		}
		
		return DayModel.getValuesAsXEqualsSek(lowest.setScale(4, RoundingMode.HALF_UP));
	 }
	 
	 public String getMaxDeltaCurrency(LocalDate fromDate, LocalDate toDate){
		 
		 BigDecimal euroRateFirst = BigDecimal.ZERO;
		 BigDecimal dollarRateFirst = BigDecimal.ZERO;
		 BigDecimal poundRateFirst = BigDecimal.ZERO;
		 BigDecimal frankRateFirst = BigDecimal.ZERO;
		 BigDecimal yuanRateFirst = BigDecimal.ZERO;

		 BigDecimal euroRateAfter = BigDecimal.ZERO;
		 BigDecimal dollarRateAfter = BigDecimal.ZERO;
		 BigDecimal poundRateAfter = BigDecimal.ZERO;
		 BigDecimal frankRateAfter = BigDecimal.ZERO;
		 BigDecimal yuanRateAfter = BigDecimal.ZERO;

		 
		 for(int i=0; i<ApplicationRunner.days.size(); i++){
			 if(fromDate.compareTo(ApplicationRunner.days.elementAt(i).getDate())==0){
				 euroRateFirst = DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate()));
				 dollarRateFirst = DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate()));
				 poundRateFirst = DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate()));
				 frankRateFirst = DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate()));
				 yuanRateFirst = DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate()));
			 }
			 if(toDate.compareTo(ApplicationRunner.days.elementAt(i).getDate())==0){
				 euroRateAfter = DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate()));
				 dollarRateAfter = DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate()));
				 poundRateAfter = DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate()));
				 frankRateAfter = DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate()));
				 yuanRateAfter = DayModel.getValuesAsXEqualsSek(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate()));
			 }
		 }
		 
		 BigDecimal difference = euroRateAfter.subtract(euroRateFirst);
		 BigDecimal maxDelta = difference.abs();
		 String maxDeltaCurrency = "EUR";
		 
		 difference = dollarRateFirst.subtract(dollarRateAfter);
		 if(maxDelta.compareTo(difference.abs())<0){
			 maxDeltaCurrency = "USD";
			 maxDelta = difference.abs();
		 }
		 
		 difference = poundRateFirst.subtract(poundRateAfter);
		 if(maxDelta.compareTo(difference.abs())<0){
			 maxDelta = difference.abs();
			 maxDeltaCurrency = "GBP";
		 }
		 
		 difference = frankRateFirst.subtract(frankRateAfter);
		 if(maxDelta.compareTo(difference.abs())<0){
			 maxDelta = difference.abs();
			 maxDeltaCurrency = "CHF";
		 }
		 
		 difference = yuanRateFirst.subtract(yuanRateAfter);
		 if(maxDelta.compareTo(difference.abs())<0){
			 maxDeltaCurrency = "CNY";
			 maxDelta = difference.abs();
		 }
		 
		return maxDeltaCurrency;
	 }
	 
	 public String getMaxVolatilityCurrency(LocalDate fromDate, LocalDate toDate){
		 
		 BigDecimal maxEuroVolatality = getLowMark("euro", fromDate, toDate).subtract(getHighMark("euro", fromDate, toDate));
		 BigDecimal maxDollarVolatality = getLowMark("dollar", fromDate, toDate).subtract(getHighMark("dollar", fromDate, toDate));
		 BigDecimal maxPoundVolatality = getLowMark("pound", fromDate, toDate).subtract(getHighMark("pound", fromDate, toDate));
		 BigDecimal maxFrankVolatality = getLowMark("frank", fromDate, toDate).subtract(getHighMark("frank", fromDate, toDate));
		 BigDecimal maxYuanVolatality = getLowMark("yuan", fromDate, toDate).subtract(getHighMark("yuan", fromDate, toDate));
		 
		 String currencyMaxVolatility = "EUR";
		 BigDecimal maxVolatility = maxEuroVolatality.abs();
		 
		 if(maxVolatility.compareTo(maxDollarVolatality.abs())<0){
			 maxVolatility = maxDollarVolatality.abs();
			 currencyMaxVolatility = "USD";
		 }
		 if(maxVolatility.compareTo(maxPoundVolatality.abs())<0){
			 maxVolatility = maxPoundVolatality.abs();
			 currencyMaxVolatility = "GBP";
		 }
		 if(maxVolatility.compareTo(maxFrankVolatality.abs())<0){
			 maxVolatility = maxFrankVolatality.abs();
			 currencyMaxVolatility = "CHF";
		 }
		 if(maxVolatility.compareTo(maxYuanVolatality.abs())<0){
			 maxVolatility = maxYuanVolatality.abs();
			 currencyMaxVolatility = "CNY";
		 }
		 return currencyMaxVolatility;
	 }
	 
	 public int getMaxVolatilityWeek(String currency, int year){
		 
		 LocalDate fromDate;
		 if(year == 2015)
			 fromDate = LocalDate.parse("2014-12-29");
		 else if(year == 2014)
			 fromDate = LocalDate.parse("2013-12-30");
		 else
			 return 0;
		 
		 int maxVolatilityWeek = 0;
		 int weekCounter = 1;
		 BigDecimal volatility;
		 BigDecimal maxVolatility = BigDecimal.ZERO;
		 
		 LocalDate toDate = fromDate.plusDays(7);
		 System.out.println(toDate);
		 
		 while(toDate.isBefore(LocalDate.of(year + 1, 1, 4))){
			 System.out.println("Test");
			 volatility = getVolatilty(currency, fromDate, toDate);
			 if(volatility.compareTo(maxVolatility)>0){
				 maxVolatilityWeek = weekCounter;
				 maxVolatility = volatility;
			 }
			 weekCounter++;
			 fromDate = fromDate.plusDays(7);
			 toDate = toDate.plusDays(7);

		 }
		 
		 return maxVolatilityWeek;
	 }
	 
	 private BigDecimal getVolatilty(String currency, LocalDate fromDate, LocalDate toDate){
		 BigDecimal volatility;
		 if(currency=="euro"){
			 volatility = getLowMark("euro", fromDate, toDate).subtract(getHighMark("euro", fromDate, toDate));
			 return volatility.abs();
		 }
		 if(currency=="dollar"){
			 volatility = getLowMark("dollar", fromDate, toDate).subtract(getHighMark("dollar", fromDate, toDate));
			 return volatility.abs();
		 }
		 if(currency=="pound"){
			 volatility = getLowMark("pound", fromDate, toDate).subtract(getHighMark("pound", fromDate, toDate));
			 return volatility.abs();
		 }
		 if(currency=="frank"){
			 volatility = getLowMark("frank", fromDate, toDate).subtract(getHighMark("frank", fromDate, toDate));
			 return volatility.abs();
		 }
		 if(currency=="yuan"){
			 volatility = getLowMark("yuan", fromDate, toDate).subtract(getHighMark("yuan", fromDate, toDate));
			 return volatility.abs();
		 }
		 return BigDecimal.ZERO;
	 }
	 
	 public BigDecimal getAverage(String currency, LocalDate fromDate, LocalDate toDate, DayOfWeek weekDay){
		 return BigDecimal.ZERO;
	 }

}
