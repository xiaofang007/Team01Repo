package comp3111.covid;

import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.Cursor;
import javafx.scene.chart.XYChart.Data;
import java.util.Set;
import javafx.scene.chart.LineChart;
import java.util.Calendar;
import javafx.scene.control.TextArea;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Data Explorer on COVID-19
 * 
 * @author <a href=mailto:myangba@connect.ust.hk>Yang Mobei</a>
 * @version 1.1
 * 
 */

public class Chart3Analysis {

	public static CSVParser getFileParser(String dataset) {
		FileResource fr = new FileResource("dataset/" + dataset);
		return fr.getCSVParser(true);
	}

	public static Date stringToDate(String stringDate) {

		Date date = null;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(stringDate);
		} catch (ParseException e) {
			System.out.println("This string " + stringDate + " is not a date");
		}
		return date;
	}

	// input: csvdata, country_code, date,
	// output:country, vaccination number,vaccination rate
	/*
	 * public static Series<String, Number>[] getVaccinationRateCha3(String[]
	 * countries, String startDate, String endDate, TextArea Textconsolechart) { //
	 * initialize the hash table Date dateStartDate = stringToDate(startDate); Date
	 * dateEndDate = stringToDate(endDate); Series<String, Number>[] chart3Series =
	 * new Series[countries.length]; Set<String> myset = new HashSet<String>(); //
	 * HashMap<String,Series<String ,Number> >map = new HashMap<>();
	 * 
	 * for (int i = 0; i < countries.length; i++) { myset.add(countries[i]);
	 * chart3Series[i] = new Series<String, Number>(); } String previousCountry =
	 * ""; String currentCountry = ""; String countriesOutofEndDate = ""; boolean
	 * retrieveData = false; int countryCount = 0; for (CSVRecord rec :
	 * getFileParser("COVID_Dataset_v1.0.csv")) { Date recDate =
	 * stringToDate(rec.get("date")); // if (dateStartDate.compareTo(recDate)<0 ||
	 * retrieveData == true) {// we begin // retrieve data from this point if
	 * (rec.get("date").equals(startDate) || retrieveData == true) { retrieveData =
	 * true;
	 * 
	 * if (myset.contains(rec.get("location"))) { currentCountry =
	 * rec.get("location"); // we should begin a new series if
	 * (!currentCountry.equals(previousCountry)) { countryCount++;
	 * 
	 * // it also means this country's enddate is out of range countriesOutofEndDate
	 * += previousCountry + "\n"; previousCountry = currentCountry; } double
	 * vaccinationRate = 0; if
	 * (rec.get("people_fully_vaccinated_per_hundred").isEmpty() == false)
	 * vaccinationRate =
	 * Double.parseDouble(rec.get("people_fully_vaccinated_per_hundred")); else {
	 * vaccinationRate =0.0;
	 * Textconsolechart.setText("we do not have data of fully vaccinated rate in " +
	 * rec.get("location") + " on " + rec.get("date") + "\n"); } Data<String,
	 * Number> nodedata = new Data<String, Number>(rec.get("date"),
	 * vaccinationRate); chart3Series[countryCount].getData().add(nodedata);
	 * 
	 * if (rec.get("date").equals(endDate)) { retrieveData = false;
	 * chart3Series[countryCount].setName(rec.get("location")); countryCount++; } }
	 * }
	 * 
	 * 
	 * }
	 * 
	 * String countriesLackData="";for( int i = 0;i<countries.length;i++) { if
	 * (chart3Series[i].getName() == null) { countriesLackData = countries[i] +
	 * "\n"; } }Textconsolechart.
	 * setText("The following countries are out of start date range"
	 * +countriesLackData+"\n"+"The following countries are out of end date range"
	 * +countriesOutofEndDate);return chart3Series; }
	 */
	public static Series<String, Number>[] getVaccinationRateCha3(String[] countries, String startDate, String endDate,
			TextArea Textconsolechart) {
		// initialize the hash table
		Date dateStartDate = stringToDate(startDate);
		Date dateEndDate = stringToDate(endDate);
		Series<String, Number>[] chart3Series = new Series[countries.length];
		Set<String> myset = new HashSet<String>();
		// HashMap<String,Series<String ,Number> >map = new HashMap<>();

		for (int i = 0; i < countries.length; i++) {
			System.out.println("country length "+countries.length);
			myset.add(countries[i]);
			chart3Series[i] = new Series<String, Number>();
		}
		boolean retrieveData = false;
		int countryCount = 0;
		String previousCountry = "country";
		String currentCountry = "";
		String countriesOutofEndDate = "";
		String countriesOutofBeginDate = "";
		String previousDate="";
		for (CSVRecord rec : getFileParser("COVID_Dataset_v1.0.csv")) {
			Date recDate = stringToDate(rec.get("date"));
			if (dateStartDate.compareTo(recDate) <= 0 && dateEndDate.compareTo(recDate) >= 0) {// we begin retrieve data
																								// from this point
				// retrieveData=true;
				if (myset.contains(rec.get("location"))) {

					currentCountry = rec.get("location");
					//System.out.println("we are here0 " + rec.get("date") + "current: " + currentCountry + " previous: "
							//+ previousCountry);
					if (currentCountry.equals(previousCountry) == false) {
						//test beginning boundary
						//System.out.println("we are here1 " + rec.get("date") + rec.get("location"));
						if(dateStartDate.compareTo(stringToDate(rec.get("date")))<0) {
							//System.out.println("we are here1.2 " + rec.get("date") + rec.get("location"));
							countriesOutofBeginDate +=rec.get("location")+"\n";
						}
	//					if(!endDate.equals(previousDate)) {
	//						countriesOutofEndDate+=rec.get("location"+"\n");
	//					}
						chart3Series[countryCount].setName(rec.get("location"));
						countryCount++;
						
						// it also means this country's enddate is out of range
						// countriesOutofEndDate += previousCountry + "\n";
						previousCountry = currentCountry;
					}
					double vaccinationRate = 0;
					//System.out.println("we are here2 " + rec.get("date") + rec.get("location"));
					if (rec.get("people_fully_vaccinated_per_hundred").isEmpty() == false)
						vaccinationRate = Double.parseDouble(rec.get("people_fully_vaccinated_per_hundred"));
					Data<String, Number> nodedata = new Data<String, Number>(rec.get("date"), vaccinationRate);
					//System.out.println(nodedata);
					//System.out.println("we are here3 " + rec.get("date") + rec.get("location")+" count"+countryCount);
					chart3Series[countryCount-1].getData().add(nodedata);
					previousDate=rec.get("date");
					//System.out.println("we are here " + chart3Series[countryCount].getData() + "\n");
					/*
					 * if(rec.get("date").equals(endDate)) {retrieveData=false;
					 * chart3Series[countryCount].setName(rec.get("location")); countryCount++; }
					 */
				}
			}
		}
		//Textconsolechart.setText("The following countries are out of end date range: "+countriesOutofEndDate);
		Textconsolechart.setText("The following countries are out of start date range: "+countriesOutofBeginDate);
		return chart3Series;
	}

	public static ArrayList<String> getallDates(String dBegin, String dEnd) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(format.parse(dBegin));
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(format.parse(dEnd));
		ArrayList<String> Datelist = new ArrayList<String>();
		Datelist.add(format.format(calBegin.getTime()));
		while (format.parse(dEnd).after(calBegin.getTime())) {
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			Datelist.add(format.format(calBegin.getTime()));
		}
		return Datelist;
	}

}