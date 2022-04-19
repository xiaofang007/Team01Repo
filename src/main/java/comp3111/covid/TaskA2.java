package comp3111.covid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import org.apache.commons.csv.CSVRecord;

public class TaskA2 {
	private ArrayList<String> datalist = new ArrayList<String>();
	private HashSet<String> available_country = new HashSet<String>();
	
	public ArrayList<String> storedata(String begin, String end,LinkedList<String>selectedcountry){
		begin = transformdate(begin);
		end = transformdate(end);
		for(int i=0;i<selectedcountry.size();i++) {
			String country = selectedcountry.get(i);
			for(CSVRecord rec: DataAnalysis.getFileParser("COVID_Dataset_v1.0.csv")) {
				if(country.equals(rec.get("location"))) {
					// here we get back the transformed date
					String date = transformdate(rec.get("date"));
					if (date.compareTo(begin)>=0 && date.compareTo(end)<=0) {
						String confirmedpermillion = rec.get("total_cases_per_million");
						if(rec.get("total_cases_per_million").equals("")) {
							confirmedpermillion = "0";
						}
						String data = country+" "+date+" "+confirmedpermillion;  
						datalist.add(data);
						available_country.add(country);
					}
				}
			}
		}
		return datalist;	
	}

	public HashSet<String> getAvailable_country() {
		return available_country;
	}
	
    /*
     *  this function is used to transform the data date to YYYY-MM-DD, i.e 2020/04/01
     */
	String transformdate(String date) {
    	String [] all = date.split("/");
    	String month = all[0];
    	if(month.length()==1) month = "0"+month; // 1->01
    	String day = all[1];
    	if(day.length()==1) day = "0"+day; // 1->01
    	String year = all[2];
    	String origin_date = year+"/"+month+"/"+day;
    	return origin_date;
    }

}
