package comp3111.covid;

import java.util.HashMap;
import org.apache.commons.csv.CSVRecord;

public class Table3DataAnalysis {
	// input: csvdata, country_code, date,
		 //	output:country, vaccination number,vaccination rate 
		 public static HashMap<String, CountryVacnumVacrate> getVaccinationRateTAB3(String[] countries,String date) {
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
							double peopleVaccinated=0;
							if(!rec.get("total_vaccinations").isEmpty()) 				
								peopleVaccinated=Double.parseDouble(rec.get("people_vaccinated"));
							CountryVacnumVacrate record =map.get(rec.get("location"));
							record.setPeopleVaccinated(peopleVaccinated);
							record.setVaccinationRate(vaccinationRate);
							record.setCountryName(rec.get("location"));
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
					HashMap<String, CountryVacnumVacrate> mymap=getVaccinationRateTAB3(countries,date);
					for (String country: mymap.keySet()) {
					    String key = country;
					    CountryVacnumVacrate value = mymap.get(country);
					    System.out.println(key + " " + value.getPeopleVaccinated()+" "+value.getVaccinationRate());
					}
				}
}
