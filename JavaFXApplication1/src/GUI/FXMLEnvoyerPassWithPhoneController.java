/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
 import java.net.*;
import java.io.*;

/**
 * FXML Controller class
 *
 * @author HP
 */
//import com.teknikindustries.bulksms.SMS;
public class FXMLEnvoyerPassWithPhoneController implements Initializable {

   @FXML
    private Button bot1;
    @FXML
    private TextField text;
    @FXML
    private Text txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          text.setText("WELCOME "+FXMLRecuperPassController.Nom+" !");
    }    

    @FXML
    private void send(ActionEvent event)  {
       try {
			// Construct data
			String apiKey = "apikey=" + "/F2AyLn99kE-0z2f3UVHnwwTHdTIV57DDNFcO9bcVN";
			String message = "&message=" + "This is your message"+FXMLRecuperPassController.pass;
			String sender = "&sender=" + "hazem";
			String numbers = "&numbers=" + "+21690118795";
			
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
		         JOptionPane.showMessageDialog(null, "message"+line);
			}
			rd.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error SMS "+e);
			
		}
    }

    @FXML
    private void retour(MouseEvent event) {
  try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLRecuperPass.fxml"));
                           Parent root1 = (Parent) fxmlLoader.load();
                           Stage stage = new Stage();
                           stage.initStyle(StageStyle.UNDECORATED);
                           stage.setScene(new Scene(root1));  
                          stage.show();
                           Stage stage1 = (Stage) txt.getScene().getWindow(); 
                           stage1.close();
                           } catch (IOException ex) {
                              showAlertWithHeaderText("page not found");
                            }
    }
     private void showAlertWithHeaderText(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account");
        alert.setHeaderText("Results:");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
