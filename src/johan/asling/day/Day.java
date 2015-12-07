package johan.asling.day;

import java.time.LocalDate;

public class Day {
	private LocalDate date;
	private double euroRate;
	private double dollarRate;
	private double poundRate;
	private double frankRate;
	private double yuanRate;
	
	public Day(LocalDate date, double euroRate, double dollarRate, double poundRate, double frankRate, double yuanRate){
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

	public double getEuroRate() {
		return euroRate;
	}

	public void setEuroRate(double euroRate) {
		this.euroRate = euroRate;
	}

	public double getDollarRate() {
		return dollarRate;
	}

	public void setDollarRate(double dollarRate) {
		this.dollarRate = dollarRate;
	}

	public double getPoundRate() {
		return poundRate;
	}

	public void setPoundRate(double poundRate) {
		this.poundRate = poundRate;
	}

	public double getFrankRate() {
		return frankRate;
	}

	public void setFrankRate(double frankRate) {
		this.frankRate = frankRate;
	}

	public double getYuanRate() {
		return yuanRate;
	}

	public void setYuanRate(double yuanRate) {
		this.yuanRate = yuanRate;
	}
}
