package comp3111.covid;

import java.util.HashMap;
import org.apache.commons.csv.CSVRecord;

public class Table3DataAnalysis {
	// input: csvdata, country_code, date,
		 //	output:country, vaccination number,vaccination rate 
		 public static HashMap<String, CountryVacnumVacrate> getVaccinationRateTAB3(String dataset, String[] iso_code,String date) {
			 //initialize the hash table
			 HashMap<String, CountryVacnumVacrate> map = new HashMap<>();
			 for(int i=0;i<iso_code.length;i++) 
			 	{
				 map.put(iso_code[i],new CountryVacnumVacrate() );
			 	}
			 
			 for (CSVRecord rec : DataAnalysis.getFileParser(dataset))
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
							record.setCountryName(rec.get("iso_code"));
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
