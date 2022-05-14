package comp3111.covid;

import java.util.HashMap;
import org.apache.commons.csv.CSVRecord;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextArea;
public class Table3DataAnalysis {
	// input: csvdata, country_code, date,
		 //	output:country, vaccination number,vaccination rate 
		 public static HashMap<String, CountryVacnumVacrate> getVaccinationRateTAB3(String[] countries,String date, TextArea tableAreaconsoletable) {
			 //initialize the hash table
			 HashMap<String, CountryVacnumVacrate> map = new HashMap<>();
			 for(int i=0;i<countries.length;i++) 
			 	{
				 map.put(countries[i],new CountryVacnumVacrate() );
			 	}
			 
			 for (CSVRecord rec : DataAnalysis.getFileParser("COVID_Dataset_v1.0.csv"))
			 	{
				 if (rec.get("date").equals(date)) {
						if(map.containsKey(rec.get("location"))) 
							{
							double vaccinationRate=0;
							if(rec.get("people_fully_vaccinated_per_hundred").isEmpty()==false)
								vaccinationRate=Double.parseDouble(rec.get("people_fully_vaccinated_per_hundred"));
							else {tableAreaconsoletable.setText("we do not have data of fully vaccinated rate in "+rec.get("location")+" on "+rec.get("date"));}
							double peopleVaccinated=0;
							if(!rec.get("total_vaccinations").isEmpty()) 				
								peopleVaccinated=Double.parseDouble(rec.get("people_vaccinated"));
							else {
								tableAreaconsoletable.setText("we do not have data of number fully vaccinated people  in "+rec.get("location")+" on "+rec.get("date"));}
							
							CountryVacnumVacrate record =map.get(rec.get("location"));
							record.setPeopleVaccinated(peopleVaccinated);
							record.setVaccinationRate(vaccinationRate);
							record.setCountryName(rec.get("location"));
							}
				}
			 }
			 return map;
		 }
		 

			 

}
