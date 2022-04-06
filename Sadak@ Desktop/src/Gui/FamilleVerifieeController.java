/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Connection.MyConnection;
import Entity.Famille;
import Services.FamilleService;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class FamilleVerifieeController implements Initializable {

    @FXML
    private Label UerName;
    private TableView<Famille> table;
    @FXML
    private JFXComboBox comboRmq;
    @FXML
    private TableColumn<Famille,String> col_name;
    @FXML
    private TableColumn<Famille,String> col_etat;
    @FXML
    private TableColumn<Famille,String> col_rmq;
    @FXML
    private TableColumn<Famille,String> col_date;
    @FXML
    private TableColumn<Famille,String> col_action;
    ObservableList<Famille> obList = FXCollections.observableArrayList();
    @FXML
    private TableView<Famille> tableFaVe;
    @FXML
    private TextField id_f;
    @FXML
    private TextField nom_f;
    @FXML
    private VBox btnVerif;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initial();
    }    

    @FXML
    private void close(MouseEvent event) {
//        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//        stage.close();
        Stage stage1 = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage1.close();
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("FamilyManagement.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void initial() {
        refresh();
        ObservableList<String> listRmq = FXCollections.observableArrayList("Personne Active","Personne retraite","Personne sans emploi");
            comboRmq.setItems(listRmq);
            
        //setting the table
        col_name.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("verif_f"));
        col_rmq.setCellValueFactory(new PropertyValueFactory<>("remarque"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_v"));
        //add cell of button edit 
         Callback<TableColumn<Famille, String>, TableCell<Famille, String>> cellVerif = (TableColumn<Famille, String> param) -> {
            // make cell containing buttons
            final TableCell<Famille, String> cell = new TableCell<Famille, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView VerifIcon = new FontAwesomeIconView(FontAwesomeIcon.CHECK);
                        

                        VerifIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#3F51B5;"
                        );
                        
                        VerifIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Famille f = tableFaVe.getSelectionModel().getSelectedItem();
                            String etat = f.getVerif_f();
                            int id = f.getId_f();
                            if(etat.equals("Verifiee")){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("La famille est deja Verifiee");
                                alert.showAndWait();
                            }
                            else{
                                   id_f.setText(String.valueOf(f.getId_f()));
                                   nom_f.setText(f.getNom_p());

                            }
                            
                            
                        });
                        

                        HBox managebtn = new HBox(VerifIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(VerifIcon, new Insets(2, 2, 0, 3));
                        //HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         col_action.setCellFactory(cellVerif);
            
    }
    
    public void refresh(){
        
        try {
            obList.clear();
            Connection con = MyConnection.getInstance().getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from famille where verif_f like 've%' or verif_f like 'D%'");
            
            while(rs.next()){
                obList.add(new Famille(rs.getInt("id_f"), rs.getInt("tele_p")
                        , rs.getInt("tele_m"), rs.getInt("nbr_enf"), rs.getInt("nbr_enf_malade")
                        , rs.getString("pays"), rs.getString("etat_f"), rs.getString("ville_f")
                        , rs.getString("nom_p"), rs.getString("prenom_p"), rs.getString("d_naissance_p")
                        , rs.getString("s_revenue_p"), rs.getString("nom_m"), rs.getString("prenom_m")
                        , rs.getString("d_naissance_m"), rs.getString("s_revenue_m")
                        , rs.getString("besoin_f"), rs.getString("verif_f"),rs.getString("remarque")
                        ,rs.getString("date_v"), rs.getFloat("r_brute_f") ));
               
            }  
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            }
        tableFaVe.setItems(obList);
        }

    @FXML
    private void VerifierFamille(MouseEvent event) {
        
        
        String time = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        FamilleService fs = new FamilleService();
        
        if(validateNom() & validateCombo()){
            int ID = Integer.parseInt(id_f.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Verification");
            alert.setHeaderText(null);
            alert.setContentText("La Famille est verifie");
            alert.showAndWait();
            String rmq = comboRmq.getSelectionModel().getSelectedItem().toString();
            fs.modifierEtatFamille(ID,rmq,time);
            //setTable();
            clear();
            refresh();
        }
    }
    public boolean validateCombo(){
        if(comboRmq.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Remarque");
            alert.setHeaderText(null);
            alert.setContentText("Dennoez votre remarque !");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    public boolean validateNom(){
        if(id_f.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nom Famille");
            alert.setHeaderText(null);
            alert.setContentText("Entrer la nom de la famille a verifiee !");
            alert.showAndWait();
            return false;
        }
        return true;
    }
     public void setTable(){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FamilyVerifiee.fxml"));
            Parent root = loader.load();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void clear(){
         nom_f.setText("");
         comboRmq.setValue(null);
     }
    
    }

    
    

