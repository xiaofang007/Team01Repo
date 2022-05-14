package comp3111.covid;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextArea;
public class Table3DataAnalysis {
	// input: csvdata, country_code, date,
		 //	output:country, vaccination number,vaccination rate 
		 public static HashMap<String, CountryVacnumVacrate> getVaccinationRateTAB3(String[] countries,String date, TextArea tableAreaconsoletable) {
			 //initialize the hash table
			 boolean outofRange=true;
			 String previousCountry="";
			 HashMap<String, CountryVacnumVacrate> map = new HashMap<>();
			 for(int i=0;i<countries.length;i++) 
			 	{
				 map.put(countries[i],new CountryVacnumVacrate() );
			 	}
			 
			 for (CSVRecord rec : DataAnalysis.getFileParser("COVID_Dataset_v1.0.csv"))
			 	{
				 if (rec.get("date").equals(date)) {
					outofRange=false;	
					 if(map.containsKey(rec.get("location"))) 
							{
						 	if(previousCountry.equals(rec.get("location"))) {
						 		
						 	}
							boolean haveToType1=true;
							boolean haveToType2=true;
							boolean haveToType=true;
							String toType1="print";
							double vaccinationRate=0;
							if(rec.get("people_fully_vaccinated_per_hundred").isEmpty()==false)
								vaccinationRate=Double.parseDouble(rec.get("people_fully_vaccinated_per_hundred"));
							else {tableAreaconsoletable.setText("we do not have data of fully vaccinated rate in "+rec.get("location")+" on "+rec.get("date")+"\n");
								toType1="we do not have data of fully vaccinated rate in "+rec.get("location")+" on "+rec.get("date")+"\n";
								haveToType1=false;}
							double peopleVaccinated=0;
							if(rec.get("total_vaccinations").isEmpty()==false) 				
								peopleVaccinated=Double.parseDouble(rec.get("people_vaccinated"));
							else {
								if(toType1.equals("print")) {
								tableAreaconsoletable.setText("we do not have data of number fully vaccinated people  in "+rec.get("location")+" on "+rec.get("date"));}
								else {tableAreaconsoletable.setText(toType1+"we do not have data of number fully vaccinated people  in "+rec.get("location")+" on "+rec.get("date"));}
								haveToType2=false;}
							System.out.println(toType1);
							haveToType=haveToType1||haveToType2;
							CountryVacnumVacrate record =map.get(rec.get("location"));
							record.setPeopleVaccinated(peopleVaccinated);
							record.setVaccinationRate(vaccinationRate);
							record.setCountryName(rec.get("location"));
							record.setToType(haveToType);
							}
				}
			 }
			 int countriesCount=0;
			 for(Map.Entry<String,CountryVacnumVacrate> entry:map.entrySet()) {
				 if(entry.getValue().getCountryName()==null) {
					 countriesCount++;
				 }
			 }
			 if(countriesCount!=0)
				 countriesCount=0;
			 String countriesLackData="";
			 for(Map.Entry<String,CountryVacnumVacrate> entry:map.entrySet()) {
				 if(entry.getValue().getCountryName()==null) {
					 countriesLackData+=entry.getKey()+"\n";
					 entry.getValue().setToType(false);
					 countriesCount++;
				 }
			 }
			 if(countriesCount!=0)
				 tableAreaconsoletable.setText("the following countries' date is out of range "+countriesLackData);
			 return map;
		 }
		 

			 

}
