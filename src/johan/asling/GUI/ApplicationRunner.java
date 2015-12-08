package johan.asling.GUI;

import johan.asling.day.Day;
import johan.asling.day.DayModel;
import johan.asling.readers.DayReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Vector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplicationRunner extends Application{
	
	public static Vector<Day> days = new Vector<Day>();
	public DayModel dayModel = new DayModel();
	public DatePicker mainDatePicker, fromDatePicker, toDatePicker;
	Label buttonResult, errorLabel;
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void init(){
		try {
			days = DayReader.readDays("textFiles/rates.csv");
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage window) throws Exception {
		
		window.setTitle("Currency program");
		Scene scene = createScene();
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	}

	private Scene createScene() {
		
		Label programDescription = new Label("Enter a date between 24-11-2013 and 30-11-2015 to display the rates of that day");
		buttonResult = new Label("No results yet");
		errorLabel = new Label("");
		
		HBox datePickerRow = getDatePickerRow();
		
		HBox euroRow = getEuroRow();
		Label euroLabel = new Label("0 EUR = 0 SEK");
		euroRow.getChildren().add(0, euroLabel);
		
		HBox dollarRow = getDollarRow();
		Label dollarLabel = new Label("0 USD = 0 SEK");
		dollarRow.getChildren().add(0, dollarLabel);
		
		HBox poundRow = getPoundRow();
		Label poundLabel = new Label("0 GBP = 0 SEK");
		poundRow.getChildren().add(0, poundLabel);

		HBox frankRow = getFrankRow();
		Label frankLabel = new Label("0 CHF = 0 SEK");
		frankRow.getChildren().add(0, frankLabel);

		HBox yuanRow = getYuanRow();
		Label yuanLabel = new Label("0 CNY = 0 SEK");
		yuanRow.getChildren().add(0, yuanLabel);

				
		VBox root = new VBox();
		root.getChildren().addAll(programDescription, datePickerRow, errorLabel, euroRow, dollarRow, poundRow, frankRow, yuanRow, buttonResult);
		root.setMinSize(900, 400);
		
		Scene scene = new Scene(root);
		
		mainDatePicker.setOnAction(event->{
			updateText(euroLabel, dollarLabel, poundLabel, frankLabel, yuanLabel);
		});
		updateText(euroLabel, dollarLabel, poundLabel, frankLabel, yuanLabel);
		
		return scene;
	}

	private HBox getDatePickerRow() {
		
		mainDatePicker = new DatePicker();
		mainDatePicker.setValue(LocalDate.parse("2014-12-12"));
		
		Label fromLabel = new Label("Arbitrary period, from: ");
		
		fromDatePicker = new DatePicker();
		fromDatePicker.setValue(LocalDate.parse("2014-12-12"));
		
		
		Label toLabel = new Label(" to: ");

		toDatePicker = new DatePicker();
		toDatePicker.setValue(LocalDate.parse("2014-12-15"));
		
		HBox datePickerRow = new HBox();
		datePickerRow.getChildren().addAll(mainDatePicker, fromLabel, fromDatePicker, toLabel, toDatePicker);
				
		return datePickerRow;
	}

	private HBox getEuroRow() {
				
		Button averageRateYearEuro = new Button("Average this year");
		averageRateYearEuro.setOnAction(event->{
			BigDecimal bd = dayModel.getYearAverage("euro", mainDatePicker.getValue().getYear());
			buttonResult.setText("Average euro rate this year was: 1 EUR = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button averageRateMonthEuro = new Button("Average this month");
		averageRateMonthEuro.setOnAction(event->{
			BigDecimal bd = dayModel.getMonthAverage("euro", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			buttonResult.setText("Average euro rate this month and year was: 1 EUR = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button averageRateArbitraryEuro = new Button("Average this period");
		averageRateArbitraryEuro.setOnAction(event->{
			BigDecimal bd = dayModel.getAverage("euro", fromDatePicker.getValue(), toDatePicker.getValue());
			buttonResult.setText("Average euro rate this period was: 1 EUR = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button getHighLowYearEuro = new Button("Highest/lowest this year");
		getHighLowYearEuro.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getYearHigh("euro", mainDatePicker.getValue().getYear());
			BigDecimal bdLow = dayModel.getYearLow("euro", mainDatePicker.getValue().getYear());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 EUR = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		Button getHighLowMonthEuro = new Button("Highest/lowest this month");
		getHighLowMonthEuro.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getMonthHigh("euro", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			BigDecimal bdLow = dayModel.getMonthLow("euro", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 EUR = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		Button getHighLowArbitraryEuro = new Button("Highest/lowest this period");
		getHighLowArbitraryEuro.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getHighMark("euro", fromDatePicker.getValue(), toDatePicker.getValue());
			BigDecimal bdLow = dayModel.getLowMark("euro", fromDatePicker.getValue(), toDatePicker.getValue());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 EUR = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		HBox euroRow = new HBox();
		euroRow.getChildren().addAll(averageRateYearEuro, averageRateMonthEuro, averageRateArbitraryEuro, getHighLowYearEuro, getHighLowMonthEuro, getHighLowArbitraryEuro);
		return euroRow;
	}

	private HBox getDollarRow() {
		
		Button averageRateYearDollar = new Button("Average this year");
		averageRateYearDollar.setOnAction(event->{
			BigDecimal bd = dayModel.getYearAverage("dollar", mainDatePicker.getValue().getYear());
			buttonResult.setText("Average dollar rate this year was: 1 USD = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button averageRateMonthDollar = new Button("Average this month");
		averageRateMonthDollar.setOnAction(event->{
			BigDecimal bd = dayModel.getMonthAverage("dollar", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			buttonResult.setText("Average dollar rate this month and year was: 1 USD = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button averageRateArbitraryDollar = new Button("Average this period");
		averageRateArbitraryDollar.setOnAction(event->{
			BigDecimal bd = dayModel.getAverage("dollar", fromDatePicker.getValue(), toDatePicker.getValue());
			buttonResult.setText("Average dollar rate this period was: 1 USD = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button getHighLowYearDollar = new Button("Highest/lowest this year");
		getHighLowYearDollar.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getYearHigh("dollar", mainDatePicker.getValue().getYear());
			BigDecimal bdLow = dayModel.getYearLow("dollar", mainDatePicker.getValue().getYear());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 USD = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		Button getHighLowMonthDollar = new Button("Highest/lowest this month");
		getHighLowMonthDollar.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getMonthHigh("dollar", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			BigDecimal bdLow = dayModel.getMonthLow("dollar", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 USD = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		Button getHighLowArbitraryDollar = new Button("Highest/lowest this period");
		getHighLowArbitraryDollar.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getHighMark("dollar", fromDatePicker.getValue(), toDatePicker.getValue());
			BigDecimal bdLow = dayModel.getLowMark("dollar", fromDatePicker.getValue(), toDatePicker.getValue());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 USD = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		HBox dollarRow = new HBox();
		dollarRow.getChildren().addAll(averageRateYearDollar, averageRateMonthDollar, averageRateArbitraryDollar, getHighLowYearDollar, getHighLowMonthDollar, getHighLowArbitraryDollar);
		return dollarRow;
	}

	private HBox getPoundRow() {
		
		Button averageRateYearPound = new Button("Average this year");
		averageRateYearPound.setOnAction(event->{
			BigDecimal bd = dayModel.getYearAverage("pound", mainDatePicker.getValue().getYear());
			buttonResult.setText("Average pound rate this year was: 1 GBP = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button averageRateMonthPound = new Button("Average this month");
		averageRateMonthPound.setOnAction(event->{
			BigDecimal bd = dayModel.getMonthAverage("pound", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			buttonResult.setText("Average pound rate this month and year was: 1 GBP = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button averageRateArbitraryPound = new Button("Average this period");
		averageRateArbitraryPound.setOnAction(event->{
			BigDecimal bd = dayModel.getAverage("pound", fromDatePicker.getValue(), toDatePicker.getValue());
			buttonResult.setText("Average pound rate this period was: 1 GBP = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button getHighLowYearPound = new Button("Highest/lowest this year");
		getHighLowYearPound.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getYearHigh("pound", mainDatePicker.getValue().getYear());
			BigDecimal bdLow = dayModel.getYearLow("pound", mainDatePicker.getValue().getYear());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 GBP = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		Button getHighLowMonthEuro = new Button("Highest/lowest this month");
		getHighLowMonthEuro.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getMonthHigh("pound", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			BigDecimal bdLow = dayModel.getMonthLow("pound", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 GBP = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		Button getHighLowArbitraryPound = new Button("Highest/lowest this period");
		getHighLowArbitraryPound.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getHighMark("pound", fromDatePicker.getValue(), toDatePicker.getValue());
			BigDecimal bdLow = dayModel.getLowMark("pound", fromDatePicker.getValue(), toDatePicker.getValue());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 GBP = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		HBox poundRow = new HBox();
		poundRow.getChildren().addAll(averageRateYearPound, averageRateMonthPound, averageRateArbitraryPound, getHighLowYearPound, getHighLowMonthEuro, getHighLowArbitraryPound);
		return poundRow;
	}

	private HBox getFrankRow() {
		
		Button averageRateYearFrank = new Button("Average this year");
		averageRateYearFrank.setOnAction(event->{
			BigDecimal bd = dayModel.getYearAverage("frank", mainDatePicker.getValue().getYear());
			buttonResult.setText("Average frank rate this year was: 1 CHF = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button averageRateMonthFrank = new Button("Average this month");
		averageRateMonthFrank.setOnAction(event->{
			BigDecimal bd = dayModel.getMonthAverage("frank", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			buttonResult.setText("Average frank rate this month and year was: 1 CHF = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button averageRateArbitraryFrank = new Button("Average this period");
		averageRateArbitraryFrank.setOnAction(event->{
			BigDecimal bd = dayModel.getAverage("frank", fromDatePicker.getValue(), toDatePicker.getValue());
			buttonResult.setText("Average frank rate this period was: 1 CHF = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button getHighLowYearFrank = new Button("Highest/lowest this year");
		getHighLowYearFrank.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getYearHigh("frank", mainDatePicker.getValue().getYear());
			BigDecimal bdLow = dayModel.getYearLow("frank", mainDatePicker.getValue().getYear());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 CHF = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		Button getHighLowMonthFrank = new Button("Highest/lowest this month");
		getHighLowMonthFrank.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getMonthHigh("frank", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			BigDecimal bdLow = dayModel.getMonthLow("frank", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 CHF = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		Button getHighLowArbitraryFrank = new Button("Highest/lowest this period");
		getHighLowArbitraryFrank.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getHighMark("frank", fromDatePicker.getValue(), toDatePicker.getValue());
			BigDecimal bdLow = dayModel.getLowMark("frank", fromDatePicker.getValue(), toDatePicker.getValue());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 CHF = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		HBox frankRow = new HBox();
		frankRow.getChildren().addAll(averageRateYearFrank, averageRateMonthFrank, averageRateArbitraryFrank, getHighLowYearFrank, getHighLowMonthFrank, getHighLowArbitraryFrank);
		return frankRow;
	}

	private HBox getYuanRow() {
		
		Button averageRateYearYuan = new Button("Average this year");
		averageRateYearYuan.setOnAction(event->{
			BigDecimal bd = dayModel.getYearAverage("yuan", mainDatePicker.getValue().getYear());
			buttonResult.setText("Average yuan rate this year was: 1 CNY = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button averageRateMonthYuan = new Button("Average this month");
		averageRateMonthYuan.setOnAction(event->{
			BigDecimal bd = dayModel.getMonthAverage("yuan", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			buttonResult.setText("Average yuan rate this month and year was: 1 CNY = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button averageRateArbitraryYuan = new Button("Average this period");
		averageRateArbitraryYuan.setOnAction(event->{
			BigDecimal bd = dayModel.getAverage("yuan", fromDatePicker.getValue(), toDatePicker.getValue());
			buttonResult.setText("Average yuan rate this period was: 1 CNY = " + DayModel.getValuesAsXEqualsSek(bd) + " SEK");
		});
		
		Button getHighLowYearYuan = new Button("Highest/lowest this year");
		getHighLowYearYuan.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getYearHigh("yuan", mainDatePicker.getValue().getYear());
			BigDecimal bdLow = dayModel.getYearLow("yuan", mainDatePicker.getValue().getYear());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 CNY = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		Button getHighLowMonthYuan = new Button("Highest/lowest this month");
		getHighLowMonthYuan.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getMonthHigh("yuan", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			BigDecimal bdLow = dayModel.getMonthLow("yuan", mainDatePicker.getValue().getYear(), mainDatePicker.getValue().getMonthValue());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 CNY = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		Button getHighLowArbitraryYuan = new Button("Highest/lowest this period");
		getHighLowArbitraryYuan.setOnAction(event->{
			BigDecimal bdHigh = dayModel.getHighMark("yuan", fromDatePicker.getValue(), toDatePicker.getValue());
			BigDecimal bdLow = dayModel.getLowMark("yuan", fromDatePicker.getValue(), toDatePicker.getValue());
			errorLabel.setText("");
			buttonResult.setText("Highest rate: 1 CNY = " + DayModel.getValuesAsXEqualsSek(bdHigh) + " SEK and lowest: " + DayModel.getValuesAsXEqualsSek(bdLow) + " SEK");
		});
		
		HBox yuanRow = new HBox();
		yuanRow.getChildren().addAll(averageRateYearYuan, averageRateMonthYuan, averageRateArbitraryYuan, getHighLowYearYuan, getHighLowMonthYuan, getHighLowArbitraryYuan);
		return yuanRow;
	}

	private void updateText(Label euroLabel, Label dollarLabel, Label poundLabel, Label frankLabel, Label yuanLabel) {
		
		for(int i=0; i<days.size(); i++){
			if(mainDatePicker.getValue().isEqual(days.get(i).getDate())){
				euroLabel.setText("1 EUR = " + DayModel.getValuesAsXEqualsSek(new BigDecimal(days.get(i).getEuroRate())) + " SEK");
				dollarLabel.setText("1 USD = " + DayModel.getValuesAsXEqualsSek(new BigDecimal(days.get(i).getDollarRate())) + " SEK");
				poundLabel.setText("1 GBP = " + DayModel.getValuesAsXEqualsSek(new BigDecimal(days.get(i).getPoundRate())) + " SEK");
				frankLabel.setText("1 CHF = " + DayModel.getValuesAsXEqualsSek(new BigDecimal(days.get(i).getFrankRate())) + " SEK");
				yuanLabel.setText("1 CNY = " + DayModel.getValuesAsXEqualsSek(new BigDecimal(days.get(i).getYuanRate())) + " SEK");
				return;
			}
		}
		//This runs if date wasn't found
		errorLabel.setText("Please select a date between 24-11-2013 and 30-11-2015");
		euroLabel.setText("0 EUR = 0 SEK");
		dollarLabel.setText("0 USD = 0 SEK");
		poundLabel.setText("0 GBP = 0 SEK");
		frankLabel.setText("0 CHF = 0 SEK");
		yuanLabel.setText("0 CNY = 0 SEK");
	}
}