package comp3111.covid;


import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.HashMap;
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

/**
 * 
 * Data Explorer on COVID-19
 * @author <a href=mailto:namkiu@ust.hk>Namkiu Chan</a>
 * @version	1.1
 * 
 */

public class DataAnalysis1 {
 
	public static CSVParser getFileParser(String dataset) {
	     FileResource fr = new FileResource("dataset/" + dataset);
	     return fr.getCSVParser(true);
		}
	

	public static String getConfirmedCases(String dataset, String countries) {
		String oReport = "";	
		long confirmedCases = 0;
		long numRecord = 0;
		long totalNumRecord = 0;
		
		for (CSVRecord rec : getFileParser(dataset)) {
			
			if (rec.get("countries").equals(countries)) {
				String s = rec.get("new_cases");
				if (!s.equals("")) {
					confirmedCases += Long.parseLong(s);
					numRecord++;
				}
			}		
			totalNumRecord++;
		}
		
		oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
		oReport += String.format("[Summary (%s)]\n", countries);
		oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
		oReport += String.format("Number of Days Reported: %,d\n", numRecord);
		
		return oReport;
	}
	
	 public static String getConfirmedDeaths(String dataset, String countries) {
			String oReport = "";	
			long confirmedDeaths = 0;
			long numRecord = 0;
			long totalNumRecord = 0;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("countries").equals(countries)) {
					String s = rec.get("new_deaths");
					if (!s.equals("")) {
						confirmedDeaths += Long.parseLong(s);
						numRecord++;
					}
				}		
				totalNumRecord++;
			}
			
			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
			oReport += String.format("[Summary (%s)]\n", countries);
			oReport += String.format("Number of Deaths: %,d\n", confirmedDeaths);
			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
			
			return oReport;
	 }
	 
	 public static String getRateOfVaccination(String dataset, String countries) {
			String oReport = "";	
			long fullyVaccinated = 0;
			long numRecord = 0;
			long totalNumRecord = 0;
			long population = 0;
			float rate = 0.0f;
						
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("countries").equals(countries)) {
					
					String s1 = rec.get("people_fully_vaccinated");
					String s2 = rec.get("population");		
					if (!s1.equals("") && !s2.equals("")) {
						fullyVaccinated = Long.parseLong(s1);
						population = Long.parseLong(s2);						
						numRecord++;
					}
				}		
				totalNumRecord++;
			}
			
			if (population !=0)
				rate = (float) fullyVaccinated / population * 100;
			
			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
			oReport += String.format("[Summary (%s)]\n", countries);
			oReport += String.format("Number of People Fully Vaccinated: %,d\n", fullyVaccinated);
			oReport += String.format("Population: %,d\n", population);
			oReport += String.format("Rate of Vaccination: %.2f%%\n", rate);
			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
			
			return oReport;
	 }
	 // input: csvdata, country_code, date,
	 //	output:country, vaccination number,vaccination rate 
	 public static Series<String ,Number>[] getVaccinationRateCha3(String[] countries,String startDate,String endDate) {
		 //initialize the hash table
		 Series<String ,Number>[] chart3Series=new Series[countries.length];
		 Set<String> myset= new HashSet<String>();
		 //HashMap<String,Series<String ,Number> >map = new HashMap<>();

		 for(int i=0;i<countries.length;i++) 
		 	{
			 myset.add(countries[i]);
			 chart3Series[i]=new Series<String ,Number>();
		 	}
		 boolean retrieveData=false;
		 int countryCount=0;
		 String previousCountry="";
		 for (CSVRecord rec : getFileParser("COVID_Dataset_v1.0.csv"))
		 	{
			 if (rec.get("date").equals(startDate)||retrieveData==true) {// we begin retrieve data from this point	
				 retrieveData=true;
				 if(myset.contains(rec.get("location"))) 
						{
						
					 	double vaccinationRate=0;
						if(rec.get("people_fully_vaccinated_per_hundred").isEmpty()==false)
							vaccinationRate=Double.parseDouble(rec.get("people_fully_vaccinated_per_hundred"));
						Data <String, Number> nodedata = new Data<String, Number>(rec.get("date"), vaccinationRate);
	        			chart3Series[countryCount].getData().add(nodedata);
	        			
	        			if(rec.get("date").equals(endDate)) 
							{retrieveData=false;
							chart3Series[countryCount].setName(rec.get("location"));
							countryCount++;
							}
						}
			}
		 }
		 
		 return chart3Series;
	 }
		
		public static ArrayList<String> getallDates(String dBegin, String dEnd) throws Exception{ 	     
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
	// input: csvdata, country_code, startDate,endDate
	//	output:country, vaccination number,vaccination rate 
	 public static String getVaccinationRateChart3(String countries,String startDate,String endDate) {
		 return "(country , date , vacinationrate)";
	}
	 
	 public static void main(String args[]) {
			System.out.println("HelloWorld");
			String countries[]= {"Belgium"};
			String sDate="6/21/2021";
			String eDate="6/27/2021";
			Series<String ,Number>[] mySeries=getVaccinationRateCha3(countries,sDate,eDate);
			for (int i=0;i< mySeries.length;i++) {
			    System.out.println(mySeries[i].getName()+" "+mySeries[i].getData().toString() );
			}
		}
}