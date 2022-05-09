package comp3111.covid;

import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.HashMap;


/**
 * 
 * Data Explorer on COVID-19
 * @author <a href=mailto:namkiu@ust.hk>Namkiu Chan</a>
 * @version	1.1
 * 
 */

public class DataAnalysis {
 
	public static CSVParser getFileParser(String dataset) {
	     FileResource fr = new FileResource("dataset/" + dataset);
	     return fr.getCSVParser(true);
		}
	

	public static String getConfirmedCases(String dataset, String iso_code) {
		String oReport = "";	
		long confirmedCases = 0;
		long numRecord = 0;
		long totalNumRecord = 0;
		
		for (CSVRecord rec : getFileParser(dataset)) {
			
			if (rec.get("iso_code").equals(iso_code)) {
				String s = rec.get("new_cases");
				if (!s.equals("")) {
					confirmedCases += Long.parseLong(s);
					numRecord++;
				}
			}		
			totalNumRecord++;
		}
		
		oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
		oReport += String.format("[Summary (%s)]\n", iso_code);
		oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
		oReport += String.format("Number of Days Reported: %,d\n", numRecord);
		
		return oReport;
	}
	
	 public static String getConfirmedDeaths(String dataset, String iso_code) {
			String oReport = "";	
			long confirmedDeaths = 0;
			long numRecord = 0;
			long totalNumRecord = 0;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("iso_code").equals(iso_code)) {
					String s = rec.get("new_deaths");
					if (!s.equals("")) {
						confirmedDeaths += Long.parseLong(s);
						numRecord++;
					}
				}		
				totalNumRecord++;
			}
			
			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
			oReport += String.format("[Summary (%s)]\n", iso_code);
			oReport += String.format("Number of Deaths: %,d\n", confirmedDeaths);
			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
			
			return oReport;
	 }
	 
	 public static String getRateOfVaccination(String dataset, String iso_code) {
			String oReport = "";	
			long fullyVaccinated = 0;
			long numRecord = 0;
			long totalNumRecord = 0;
			long population = 0;
			float rate = 0.0f;
						
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("iso_code").equals(iso_code)) {
					
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
			oReport += String.format("[Summary (%s)]\n", iso_code);
			oReport += String.format("Number of People Fully Vaccinated: %,d\n", fullyVaccinated);
			oReport += String.format("Population: %,d\n", population);
			oReport += String.format("Rate of Vaccination: %.2f%%\n", rate);
			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
			
			return oReport;
	 }
	 // input: csvdata, country_code, date,
	 //	output:country, vaccination number,vaccination rate 
	 public static HashMap<String, CountryVacnumVacrate> getVaccinationRateTAB3(String dataset, String[] iso_code,String date) {
		 //initialize the hash table
		 HashMap<String, CountryVacnumVacrate> map = new HashMap<>();
		 for(int i=0;i<iso_code.length;i++) 
		 	{
			 map.put(iso_code[i],new CountryVacnumVacrate() );
		 	}
		 
		 for (CSVRecord rec : getFileParser(dataset))
		 	{
			 if (rec.get("date").equals(date)) {
					if(map.containsKey(rec.get("iso_code"))) 
						{
						double vaccinationRate=0;
						if(rec.get("people_fully_vaccinated_per_hundred").isEmpty()==false)
							vaccinationRate=Double.parseDouble(rec.get("people_fully_vaccinated_per_hundred"));
						double peopleVaccinated=0;
						if(!rec.get("total_vaccinations").isEmpty()) 				
							peopleVaccinated=Double.parseDouble(rec.get("people_vaccinated"));
						CountryVacnumVacrate record =map.get(rec.get("iso_code"));
						record.setPeopleVaccinated(peopleVaccinated);
						record.setVaccinationRate(vaccinationRate);
						}
			}
		 }
		 return map;
	 }
	 
	// input: csvdata, country_code, startDate,endDate
	//	output:country, vaccination number,vaccination rate 
	 public static String getVaccinationRateChart3(String dataset, String iso_code,String startDate,String endDate) {
		 return "(country , date , vacinationrate)";
	}
	 
	 public static void main(String args[]) {
			System.out.println("HelloWorld");
			String countries[]= {"ABW"};
			String date="6/21/2021";
			HashMap<String, CountryVacnumVacrate> mymap=getVaccinationRateTAB3("COVID_Dataset_v1.0.csv",countries,date);
			for (String country: mymap.keySet()) {
			    String key = country;
			    CountryVacnumVacrate value = mymap.get(country);
			    System.out.println(key + " " + value.getPeopleVaccinated()+" "+value.getVaccinationRate());
			}
		}
}