package comp3111.covid;

import java.util.HashMap;
import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Map;
public class Table3Controller {

    @FXML
    private Button Return;

    @FXML
    private TextArea TableAreaconsoletable;

    @FXML
    private TableColumn<CountryVacnumVacrate, String> countrycolumn;

    @FXML
    private TableView<CountryVacnumVacrate> tableview;

    @FXML
    private TextArea title;

    @FXML
    private TableColumn<CountryVacnumVacrate, Double> totalcasecolumn;

    @FXML
    private TableColumn<CountryVacnumVacrate, Double> totalpermillioncolumn;

    @FXML
    void Returntoui(ActionEvent event) {

    }
    
    public void initData(String data_date, LinkedList<String>selectedcountry) throws Exception {
    	countrycolumn.setCellValueFactory(new PropertyValueFactory<CountryVacnumVacrate,String>("countryName"));
		totalcasecolumn.setCellValueFactory(new PropertyValueFactory<CountryVacnumVacrate,Double>("peopleVaccinated"));
		totalpermillioncolumn.setCellValueFactory(new PropertyValueFactory<CountryVacnumVacrate,Double>("vaccinationRate"));
		// convert the linked list into an array
		String chosenCountries[]=new String[selectedcountry.size()];
		for(int i=0;i<selectedcountry.size();i++) {
			chosenCountries[i]=selectedcountry.get(i);
		}
		
		HashMap<String, CountryVacnumVacrate> mymap=Table3DataAnalysis.getVaccinationRateTAB3(chosenCountries,data_date);
		for(Map.Entry<String,CountryVacnumVacrate> entry:mymap.entrySet()) {
			tableview.getItems().add(entry.getValue());
		}
		title.setText("Rate of Vaccination against COVID-19 as of "+data_date);	
		


    }
    

}
