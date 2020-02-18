/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.User;
import Service.UserService;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class User_ManagementFXMLController implements Initializable {

    @FXML
    private Button bot1;
    @FXML
    private Button bot2;
    @FXML
    private Button bot3;
    @FXML
    private ChoiceBox choice;
UserService us;
    @FXML
    private Text txt;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> fname;
    @FXML
    private TableColumn<User,String> lname;
    @FXML
    private TableColumn<User, String> pass;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private TableColumn<User, String> mail;
    @FXML
    private TableColumn<User, String> photo;
    @FXML
    private TableColumn<User, Integer> number;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Integer> lst=FXCollections.observableArrayList();
        us=new UserService();
        ResultSet rs=us.displayid();
        try {
            while(rs.next())
            {
              lst.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User_ManagementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
           choice.setItems( lst);
          bot3.setVisible(false);
            choice.setVisible(false);
            txt.setVisible(false);  
            table.setVisible(false);
    }
        
    @FXML
    private void display(ActionEvent event) throws SQLException {
          table.setVisible(true);
    ObservableList<User> list=FXCollections.observableArrayList();
   List<User> lis=us.displayAll();
    for(User ls:lis)
    {
        list.add(ls);
   //     System.out.println(list);
    }
  id.setCellValueFactory(new PropertyValueFactory<User,Integer>("idu"));
  fname.setCellValueFactory(new PropertyValueFactory<User,String>("firstname_u"));
  lname.setCellValueFactory(new PropertyValueFactory<User,String>("lastname_u"));
  pass.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
  type.setCellValueFactory(new PropertyValueFactory<User,String>("usertype"));
  mail.setCellValueFactory(new PropertyValueFactory<User,String>("Email"));
  photo.setCellValueFactory(new PropertyValueFactory<User,String>("photo"));
  number.setCellValueFactory(new PropertyValueFactory<User,Integer>("number"));
  table.setItems(list);
    }

    @FXML
    private void delete(ActionEvent event) {
            table.setVisible(false);
            bot3.setVisible(true);
            choice.setVisible(true);
            txt.setVisible(true);   
    }

    @FXML
    private void supprimer(ActionEvent event) {
    Integer id=Integer.parseInt(choice.getValue().toString());
    if(choice.getValue().toString()!=null)
    {
        us.delete(id);
        JOptionPane.showMessageDialog(null, "       user was deleted");
        choice.hide();
    }
    }
}
