package johan.asling.day;

import java.time.LocalDate;

public class Day {
	private LocalDate date;
	private String euroRate;
	private String dollarRate;
	private String poundRate;
	private String frankRate;
	private String yuanRate;
	
	public Day(LocalDate date, String euroRate, String dollarRate, String poundRate, String frankRate, String yuanRate){
		this.date = date;
		this.euroRate = euroRate;
		this.dollarRate = dollarRate;
		this.poundRate = poundRate;
		this.frankRate = frankRate;
		this.yuanRate = yuanRate;
	}
	
	public void printDay(){
		System.out.println("Date: " + date + ", Euro: " + euroRate + " dollar: " + dollarRate);
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		System.out.println("Date set");
		this.date = date;
	}

	public String getEuroRate() {
		return euroRate;
	}

	public void setEuroRate(String euroRate) {
		this.euroRate = euroRate;
	}

	public String getDollarRate() {
		return dollarRate;
	}

	public void setDollarRate(String dollarRate) {
		this.dollarRate = dollarRate;
	}

	public String getPoundRate() {
		return poundRate;
	}

	public void setPoundRate(String poundRate) {
		this.poundRate = poundRate;
	}

	public String getFrankRate() {
		return frankRate;
	}

	public void setFrankRate(String frankRate) {
		this.frankRate = frankRate;
	}

	public String getYuanRate() {
		return yuanRate;
	}

	public void setYuanRate(String yuanRate) {
		this.yuanRate = yuanRate;
	}
}
