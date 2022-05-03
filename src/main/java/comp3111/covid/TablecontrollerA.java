package comp3111.covid;
import java.io.IOException;
import java.util.LinkedList;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * This is the controller class building on "tablegeneratorA.fxml" for generating table
 * @author fangxiao
 * 
 */
public class TablecontrollerA {  // this controller is used for generating table.
	private LinkedList<String> datalist;
    
    @FXML
    private TextArea title;
    
    @FXML
    private Button Return;
    
    @FXML
    private TextArea TableAreaconsoletable;
    
    @FXML
    private TableView<UtilA> tableview;
    
    @FXML
    private TableColumn<UtilA,String> countrycolumn;
    
    @FXML
    private TableColumn<UtilA,String> totalcasecolumn;
    
    @FXML
    private TableColumn<UtilA,String> totalpermillioncolumn;
	
    /**
     * This is the function to pass the data to the controller
     * @param data_date
     * @param selectedcountry
     * @throws Exception
     * 
     */
    public void initData(String data_date, LinkedList<String>selectedcountry) throws Exception {
    	TaskA1 temp = new TaskA1();
    	try {
    		datalist = temp.storedatacountry(data_date,selectedcountry);
    		datalist.getFirst();
    		try {
    			LinkedList<String> templist = temp.getNocountrydata();
    			String report ="";
    			report = templist.getFirst()+" ";
    			for(int i=1;i<templist.size();i++) {
    				report = report+templist.get(i)+" ";
    			}
    			TableAreaconsoletable.setText("We do not have data about: "+report+"on "+TaskA2.transformdate(data_date));
    		}
    		catch(Exception e) {
    			
    		}
    		setuptable(datalist);
    	}
    	catch(Exception e) {
    		TableAreaconsoletable.setText("We do not have data about all countries on "+TaskA2.transformdate(data_date));
    	}
	    title.setText("Number of Confirmed COVID-19 Cases as of "+TaskA2.transformdate(data_date));	
    }
    
    /**
     * This is the main function to perform table set up.
     * @param datalist
     * @throws Exception
     * 
     */
	public void setuptable(LinkedList<String> datalist) throws Exception {
		countrycolumn.setCellValueFactory(new PropertyValueFactory<UtilA,String>("country"));
		totalcasecolumn.setCellValueFactory(new PropertyValueFactory<UtilA,String>("totalcase"));
		totalpermillioncolumn.setCellValueFactory(new PropertyValueFactory<UtilA,String>("totalpermillion"));
		tableview.setItems(getdata());
	}
	
	/**
	 *  This method will return an ObservableList of String Objects which will be added to the table
	 * @throws Exception 
	 */
	private ObservableList<UtilA> getdata() throws Exception {
		ObservableList<UtilA> data = FXCollections.observableArrayList();
		for(int i=0;i<datalist.size();i++) {
			String[] split = null;
			split = datalist.get(i).split(",");
			data.add(new UtilA(new SimpleStringProperty(split[0]),new SimpleStringProperty(split[1]), new SimpleStringProperty(split[2])));
		}
		return data;
	}
	
	/**
	 * it is used to return to the main ui (ui.fxml)
	 * @param event
	 * @throws IOException
	 * 
	 */
	@FXML
    public void Returntoui(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));
		Scene uiscene = new Scene(root);
		Stage uiwindow = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
		uiwindow.setScene(uiscene);
		uiwindow.show();
    }
	
	/**
	 * get the console in the table ui
	 * @return
	 * 
	 */
	public TextArea getTextAreaconsoletable() {
		return TableAreaconsoletable;		
	}
	
	/**
	 * get the data list
	 * @return
	 * 
	 */
	public LinkedList<String> getdatalist(){
		return datalist;
	}
}
