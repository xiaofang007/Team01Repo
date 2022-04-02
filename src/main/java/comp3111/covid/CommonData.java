package comp3111.covid;
import org.apache.commons.csv.*;
import edu.duke.*;


public class CommonData {   // this is a base class that will collect common attributes
	protected String date;
	protected String country;
	public CommonData() {
		date = "";
		country = "";
	}
	
	public CommonData(String date, String country) {
		this.date = date;
		this.country = country;
	}
	
	public static CSVParser getFileParser(String dataset) {
	     FileResource fr = new FileResource("dataset/" + dataset);
	     return fr.getCSVParser(true);
	}
	
	public void setcountry(String dataset, String iso_code) {
		for(CSVRecord rec: getFileParser(dataset)) {
			if (rec.get("iso_code").equals(iso_code)) {
				country = rec.get("location");
				break;
			}
		}
	}
	
	public String getdate() {
		return date;
	}
	
	public String getcountry() {
		return country;
	}
}
