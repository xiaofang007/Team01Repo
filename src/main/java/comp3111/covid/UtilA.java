package comp3111.covid;
import org.apache.commons.csv.*;
import edu.duke.*;
import javafx.beans.property.SimpleStringProperty;


public class UtilA {   // this is class is about utilization 
	private SimpleStringProperty country;
	private SimpleStringProperty totalcase;
	private SimpleStringProperty totalpermillion;
	
	public UtilA(SimpleStringProperty c,SimpleStringProperty tc, SimpleStringProperty tp) throws Exception {
		country = c;
		totalcase = tc;
		totalpermillion = tp;
	}
	
	public static CSVParser getFileParser(String dataset) {
	     FileResource fr = new FileResource("dataset/" + dataset);
	     return fr.getCSVParser(true);
	}

	public String getCountry() {
		return country.get();
	}

	public String getTotalcase() {
		return totalcase.get();
	}

	public String getTotalpermillion() {
		return totalpermillion.get();
	}
	
}
