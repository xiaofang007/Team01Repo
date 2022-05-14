package comp3111.covid;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Map;
public class Table3Controller {

    @FXML
    private Button Return;

    @FXML
    private TextArea TableAreaconsoletable;
    
    public TextArea getTableAreaconsoletable() {
    	return TableAreaconsoletable;
    }

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
    void Returntoui(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));
		Scene uiscene = new Scene(root);
		Stage uiwindow = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
		uiwindow.setScene(uiscene);
		uiwindow.show();
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
		
		HashMap<String, CountryVacnumVacrate> mymap=Table3DataAnalysis.getVaccinationRateTAB3(chosenCountries,data_date,TableAreaconsoletable);
		for(Map.Entry<String,CountryVacnumVacrate> entry:mymap.entrySet()) {
			tableview.getItems().add(entry.getValue());
		}
		title.setText("Rate of Vaccination against COVID-19 as of "+data_date);	
		


    }
    

}
