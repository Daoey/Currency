package johan.asling.day;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate()));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="dollar"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate()));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="pound"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate()));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="frank"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate()));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="yuan"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate()));
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
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate()));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="dollar"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate()));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="pound"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate()));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="frank"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate()));
					dayCounter++;
				}
					
			}
		}
		else if(currency=="yuan"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if(ApplicationRunner.days.elementAt(i).getDate().getYear()==year && ApplicationRunner.days.elementAt(i).getDate().getMonthValue()==month){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate()));
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
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getEuroRate()));
					dayCounter++;
				}
			}
		}
		if(currency=="dollar"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
						|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getDollarRate()));
					dayCounter++;
				}
			}
		}
		if(currency=="pound"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
						|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getPoundRate()));
					dayCounter++;
				}
			}
		}
		if(currency=="frank"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
						|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getFrankRate()));
					dayCounter++;
				}
			}
		}
		if(currency=="yuan"){
			for(int i=0; i<ApplicationRunner.days.size(); i++){
				if((ApplicationRunner.days.elementAt(i).getDate().isAfter(fromDate) && ApplicationRunner.days.elementAt(i).getDate().isBefore(toDate))
						|| ApplicationRunner.days.elementAt(i).getDate().isEqual(fromDate) || ApplicationRunner.days.elementAt(i).getDate().isEqual(toDate)){
					rates = rates.add(new BigDecimal(ApplicationRunner.days.get(i).getYuanRate()));
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

		return highest.setScale(4, RoundingMode.HALF_UP);
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
		 return lowest.setScale(4, RoundingMode.HALF_UP);
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
		 
		 return highest.setScale(4, RoundingMode.HALF_UP);
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
		 
		 return lowest.setScale(4, RoundingMode.HALF_UP);
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
		
		return highest.setScale(4, RoundingMode.HALF_UP);
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
		
		return lowest.setScale(4, RoundingMode.HALF_UP);
		 
	 }

}
