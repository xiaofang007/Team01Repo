package comp3111.covid;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart.Data;

public class Chart3Controller {

	@FXML
	private Button Return;

	@FXML
	private TextArea Textconsolechart;

	@FXML
	private LineChart<String, Number> linechart;


	public ArrayList<String> getallDates(String dBegin, String dEnd) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(format.parse(dBegin));
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(format.parse(dEnd));
		ArrayList<String> Datelist = new ArrayList<String>();
		Datelist.add(format.format(calBegin.getTime()));
		while (format.parse(dEnd).after(calBegin.getTime())) {
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			Datelist.add(format.format(calBegin.getTime()));
		}
		return Datelist;
	}

	void setupchart(String begin, String end, String[] selectedcountry) {
		try {
			linechart.setTitle("Cumulative Rate of Vaccination against COVID-19");
			linechart.setCursor(Cursor.CROSSHAIR);
			ArrayList<String> datelist = Chart3Analysis.getallDates(begin, end);
			CategoryAxis xAxis = (CategoryAxis) linechart.getXAxis();
			ObservableList<String> categories = FXCollections.observableArrayList(datelist);
			xAxis.setCategories(categories);
			xAxis.setAutoRanging(true);
			// setted up the table
			Series<String, Number>[] dataSeries = Chart3Analysis.getVaccinationRateCha3(selectedcountry, begin, end,Textconsolechart);
			for (int i = 0; i < dataSeries.length; i++) {
				linechart.getData().add(dataSeries[i]);
			}
		} catch (Exception e) {
			Textconsolechart.setText("There is no data available in these days of all countries");
		}

	}
    
	@FXML
    void Returntoui(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));
		Scene uiscene = new Scene(root);
		Stage uiwindow = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
		uiwindow.setScene(uiscene);
		uiwindow.show();
    }
}
