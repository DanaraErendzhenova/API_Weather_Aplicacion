package com.mycompany.api_weather;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;

public class PrimaryController {

    @FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	// los variables que vemos en ventana
	@FXML
	private TextField setData;

	@FXML
	private Text temp_info;

	@FXML
	private Text temp_max;

	@FXML
	private Text temp_min;

	@FXML
	private Button getData;

	@FXML
	private Text temp_feels;

	@FXML
	private ImageView image;

	@FXML
	private Text pressure;
        
        @FXML
        private Text description;
        
        @FXML
        private Text humidity;
        
        @FXML
        private Text speed;

	@FXML
	void inicialize() {
		// Cuando pulsas boton
		getData.setOnAction(event -> {
			// obtenemos datos que han introducido
			String getUserCity = setData.getText().trim();
			if(!getUserCity.equals("")) { // Si han introducido algo
				// obtenemos datos de  openweathermap
				String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity +"&appid=4d76cfc598c85fcd7b75f83414df4104&units=metric");
                                System.out.println("IT`S WORKING");
				if (!output.isEmpty()) { // Si no hay falta y la siudad existe
					JSONObject obj = new JSONObject(output);
					// Trabajamos con JSON y ponemos datos en ventanas de app
					temp_info.setText("Temperature: " + obj.getJSONObject("main").getDouble("temp") + "°C");
					temp_feels.setText("Feels like: " + obj.getJSONObject("main").getDouble("feels_like") + "°C");
					temp_max.setText("Max temperature: " + obj.getJSONObject("main").getDouble("temp_max") + "°C");
					temp_min.setText("Min temperature: " + obj.getJSONObject("main").getDouble("temp_min") + "°C");
					pressure.setText("Pressure: " + obj.getJSONObject("main").getDouble("pressure") + " mbar");
                                        humidity.setText("Humidity: " + obj.getJSONObject("main").getDouble("humidity") + "%");
                                        speed.setText("Wind speed: " + obj.getJSONObject("wind").getDouble("speed") + " km/h");
                                            JSONArray weatherArray = obj.getJSONArray("weather");     
                                            JSONObject weatherObject = weatherArray.getJSONObject(0);                               
                                        description.setText("Weather: " + weatherObject.getString("description"));
                                       
                                        
                                        
				}
			}
		});
	}

	// trabajamos con URL y obtenemos datos de el
	private static String getUrlContent(String urlAdress) {
		StringBuffer content = new StringBuffer();

		try {
				URL url = new URL(urlAdress);
				URLConnection urlConn = url.openConnection();

				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				String line;

				while((line = bufferedReader.readLine()) != null) {
					content.append(line + "\n");
				}
				bufferedReader.close();
			} catch(Exception e) {
				System.out.println("We can not find this city!");
			}
			return content.toString();
	}

        
}
