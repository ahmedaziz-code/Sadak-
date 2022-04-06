/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

import Connection.MyConnection;
import Entity.Famille;
import Entity.User;
import Services.FamilleService;
import com.jfoenix.controls.JFXComboBox;
import com.pdfjet.A4;
import com.pdfjet.CoreFont;
//import com.itextpdf.text.Font;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import org.omg.CORBA.portable.ValueFactory;
import sun.rmi.transport.Transport;


/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class FamilyManagementController implements Initializable {

    @FXML
    private TextField nomP;
    @FXML
    private TextField nomM;
    @FXML
    private TextField prenomP;
    @FXML
    private TextField prenomM;
    @FXML
    private DatePicker dateP;
    @FXML
    private JFXComboBox paysF;
    @FXML
    private JFXComboBox govF;
    @FXML
    private JFXComboBox villeF;
    @FXML
    private DatePicker dateM;
    @FXML
    private TextField teleM;
    @FXML
    private TextField teleP;
    @FXML
    private JFXComboBox comboM;
    @FXML
    private JFXComboBox comboP;
    @FXML
    private Spinner<Integer> nbrEnfM;
    @FXML
    private Spinner<Integer> nbrEnf;
    @FXML
    private JFXComboBox comboB;
    @FXML
    private Button btnF;
    @FXML
    private TableView<Famille> table;
    @FXML
    private TableColumn<Famille, String> col_name;
    @FXML
    private TableColumn<Famille, String> col_etat;
    @FXML
    private TableColumn<Famille, String> col_update;
    @FXML
    private TableColumn<Famille, String> col_verif;
    ObservableList<Famille> obList = FXCollections.observableArrayList();
    ObservableList<Famille> familleList = FXCollections.observableArrayList();
    @FXML
    private TextField revenu;
    @FXML
    private Button btnF1;
    @FXML
    private TextField id_f;
    public Connection cnx;
    public PreparedStatement ste;
    @FXML
    private Button btnF11;
    public Desktop desktop= Desktop.getDesktop();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initial();
    }    

    @FXML
    private void saveF(ActionEvent event) {
        
            String id = id_f.getText();
           
     
        if(!id.isEmpty()){
            String paysf = paysF.getSelectionModel().getSelectedItem().toString();
            
            String villef = villeF.getSelectionModel().getSelectedItem().toString();
            String govf = govF.getSelectionModel().getSelectedItem().toString();
            String nomp = nomP.getText();
            String nomm = nomM.getText();
            String prenomp = prenomP.getText();
            String prenom = prenomM.getText();
            LocalDate myDate = dateM.getValue();
            String datem = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate myDateP = dateP.getValue();
            String datep = myDateP.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int telep = Integer.parseInt(teleP.getText());
            int telem = Integer.parseInt(teleM.getText());
            String s_revenue_p = comboP.getSelectionModel().getSelectedItem().toString();
            String s_revenue_m = comboM.getSelectionModel().getSelectedItem().toString();
            Integer nbEnf = nbrEnf.getValue();
            Integer nbEnfM = nbrEnfM.getValue();
            String besoin_f = comboB.getSelectionModel().getSelectedItem().toString();
            float rev = Float.parseFloat(revenu.getText());
            int ID = Integer.parseInt(id);
            FamilleService fs = new FamilleService();
            fs.modifierFamille(ID, paysf, govf, villef, nomp, prenomp, telep, datep, s_revenue_p, nomm
                    , prenom, telem, datem, s_revenue_m, rev, nbEnf, nbEnfM, besoin_f, "non verifiee","pas encore","pas encore");
            setTable();
            clear();
            refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modifier Famille");
            alert.setHeaderText(null);
            alert.setContentText("Famille bien modifiee");
            alert.showAndWait();
        }
        else if ( id.isEmpty() & validateFields() & validateTeleM()& validateRevenu()& validateTeleP()){
            String paysf = paysF.getSelectionModel().getSelectedItem().toString();
            
            String villef = villeF.getSelectionModel().getSelectedItem().toString();
            String govf = govF.getSelectionModel().getSelectedItem().toString();
            String nomp = nomP.getText();
            String nomm = nomM.getText();
            String prenomp = prenomP.getText();
            String prenom = prenomM.getText();
            LocalDate myDate = dateM.getValue();
            String datem = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate myDateP = dateP.getValue();
            String datep = myDateP.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int telep = Integer.parseInt(teleP.getText());
            int telem = Integer.parseInt(teleM.getText());
            String s_revenue_p = comboP.getSelectionModel().getSelectedItem().toString();
            String s_revenue_m = comboM.getSelectionModel().getSelectedItem().toString();
            Integer nbEnf = nbrEnf.getValue();
            Integer nbEnfM = nbrEnfM.getValue();
            String besoin_f = comboB.getSelectionModel().getSelectedItem().toString();
            float rev = Float.parseFloat(revenu.getText());
            
        Famille f = new Famille(0, telep, telem, nbEnf, nbEnfM, 
                paysf, govf, villef, nomp, prenomp, datep, s_revenue_p, 
                nomm, prenom, datem, s_revenue_m, besoin_f, "non verifiee"
                ,"pas encore","pas encore", 
                rev);
        FamilleService fs = new FamilleService();
        fs.ajouterFamille(f);
        setTable();
        clear();
        refresh();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout Famille");
            alert.setHeaderText(null);
            alert.setContentText("Famille bien ajoutee");
            alert.showAndWait();
        }
    }
    
    public void initial(){
        
            //remplir le tableau
            refresh();
        
            //initialiser combobox source de revenu
            ObservableList<String> listRevenu = FXCollections.observableArrayList("Personne Active","Personne retraite","Personne sans emploi");
            comboP.setItems(listRevenu);
            comboM.setItems(listRevenu);
            
            //initialiser combobox
            ObservableList<String> listBesoi = FXCollections.observableArrayList("Nourriture","Medicale");
            comboB.setItems(listBesoi);
            
            //initialiser combobox pays
            ObservableList<String> listPays = FXCollections.observableArrayList("Tunisie","France");
            paysF.setItems(listPays);
            
            //spinner
            SpinnerValueFactory<Integer> valueFactory=
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
            valueFactory.setValue(1);
            SpinnerValueFactory<Integer> valueFactory1=
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
            valueFactory1.setValue(0);
            nbrEnf.setValueFactory(valueFactory);
            nbrEnfM.setValueFactory(valueFactory1);
            
            //setting the table
            col_name.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("verif_f"));    
            
            //add cell of button edit 
         Callback<TableColumn<Famille, String>, TableCell<Famille, String>> cellFoctory = (TableColumn<Famille, String> param) -> {
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

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Supprimer Famille");
                                alert.setHeaderText(null);
                                alert.setContentText("Vous ete sur de supprimer la famille ?");
                                Optional <ButtonType> action = alert.showAndWait();
                                if(action.get() == ButtonType.OK){
                                    try {
                                Famille famille = table.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM `famille` WHERE id_f  ="+famille.getId_f();
                                Connection cnx = MyConnection.getInstance().getConnection();
                                 PreparedStatement ste = cnx.prepareStatement(query);
                                 ste.executeUpdate();
                                
                                refresh();
                                
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                                }
                                refresh();
              
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            Famille famille = table.getSelectionModel().getSelectedItem();
                            //text field
                            nomP.setText(famille.getNom_p());
                            nomM.setText(famille.getNom_m());
                            prenomP.setText(famille.getPrenom_p());
                            prenomM.setText(famille.getPrenom_m());
                            teleM.setText(Integer.toString(famille.getTele_m()));
                            teleP.setText(Integer.toString(famille.getTele_p()));
                            revenu.setText(Float.toString(famille.getR_brut_f()));
                            id_f.setText(Integer.toString(famille.getId_f()));
                            //datepicker
                            LocalDate myDate = LocalDate.parse(famille.getD_naissace_p());
                            dateP.setValue(myDate);
                            LocalDate myDatep = LocalDate.parse(famille.getD_naissance_m());
                            dateM.setValue(myDatep);
                            //spinner
                            valueFactory.setValue(famille.getNbr_enf());
                            valueFactory1.setValue(famille.getNbr_enf_malade());
                            nbrEnf.setValueFactory(valueFactory);
                            nbrEnfM.setValueFactory(valueFactory1);
                            //combobox
                            paysF.setValue(famille.getPays_f());
                            govF.setValue(famille.getEtat_f());
                            villeF.setValue(famille.getVille_f());
                            comboM.setValue(famille.getS_revenue_m());
                            comboP.setValue(famille.getS_revenue_p());
                            comboB.setValue(famille.getBesoin_f());
                            
                            

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         col_update.setCellFactory(cellFoctory);
         
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
                            
                            Famille f = table.getSelectionModel().getSelectedItem();
                            String etat = f.getVerif_f();
                            int id = f.getId_f();
                            if(etat.equals("Demande Verification") | etat.equals("Verifiee")){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("La famille est deja en cours de verification");
                                alert.showAndWait();
                            }
                            else{
                                try {
                                    FamilleService fs  = new FamilleService();
                                    fs.modifierFaVe(id);
                                    setTable(); 
                                    refresh();
                                    User u = new User();
                                    print(f);
//                                    fs.sendEmail(u, f);
                                    String sql = "select * from utilisateur where type_u like 'vo%' and adresse_u like '"+f.getVille_f()+"' ";
                                    cnx = MyConnection.getInstance().getConnection();
                                    ste = cnx.prepareStatement(sql);
                                    ResultSet rs = ste.executeQuery();
                                    while(rs.next()){
                                        u.setUsername(rs.getString("username"));
                                        u.setNom_u(rs.getString("nom_u"));
                                        u.setPrenom_u(rs.getString("prenom_u"));
                                        u.setEmail_u(rs.getString("email_u"));
                                        u.setMdp_u(rs.getString("mdp_u"));
                                        u.setType_u(rs.getString("type_u"));
                                        fs.sendEmail(u, f);
                                        }
                                        
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(FamilyManagementController.class.getName()).log(Level.SEVERE, null, ex);
                                }
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
         col_verif.setCellFactory(cellVerif);
            
    }
    public void setTable(){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FamilyManagement.fxml"));
            Parent root = loader.load();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void clear(){
        id_f.setText("");
        nomP.setText("");
        nomM.setText("");
        prenomP.setText("");
        prenomM.setText("");
        teleP.setText("");
        teleM.setText("");
        revenu.setText("");
        //spinner
        SpinnerValueFactory<Integer> valueFactory=
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
            valueFactory.setValue(1);
        SpinnerValueFactory<Integer> valueFactory1=
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
            valueFactory1.setValue(0);
        nbrEnf.setValueFactory(valueFactory);
        nbrEnfM.setValueFactory(valueFactory1);
        dateM.setValue(null);
        dateP.setValue(null);
        comboB.setValue(null);
        comboM.setValue(null);
        comboP.setValue(null);
        paysF.setValue(null);
        govF.setValue(null);
        villeF.setValue(null);
    }
    
    public void refresh(){
        
        try {
            obList.clear();
            Connection con = MyConnection.getInstance().getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from famille where verif_f like 'non%'"
                    + " or verif_f like 'De%' or verif_f like 'V%'");
            
            while(rs.next()){
                obList.add(new Famille(rs.getInt("id_f"), rs.getInt("tele_p")
                        , rs.getInt("tele_m"), rs.getInt("nbr_enf"), rs.getInt("nbr_enf_malade")
                        , rs.getString("pays"), rs.getString("etat_f"), rs.getString("ville_f")
                        , rs.getString("nom_p"), rs.getString("prenom_p"), rs.getString("d_naissance_p")
                        , rs.getString("s_revenue_p"), rs.getString("nom_m"), rs.getString("prenom_m")
                        , rs.getString("d_naissance_m"), rs.getString("s_revenue_m")
                        , rs.getString("besoin_f"), rs.getString("verif_f"),rs.getString("remarque")
                        ,rs.getString("dateV"), rs.getFloat("r_brute_f") ));
                familleList.add(new Famille(rs.getInt("id_f"), rs.getString("nom_p"), rs.getString("verif_f")));
            }  
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());;
            }
        table.setItems(obList);
        }


    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void scroll(ActionEvent event) {
        if(paysF.getSelectionModel().getSelectedItem().toString().equals("Tunisie")){
            ObservableList<String> tunisie = FXCollections.observableArrayList("Tunis","Sfax",
                    "Sousse","Ariana","Beja","Ben Arous","Bizerte","Gabes","Gafsa","Jendouba",
                    "Kairouan","Kasserine","Kebili","Kef","Mahdia","Manouba","Medenine",
                    "Monastir","Nabeul","Sidi Bouzid","Siliana","Tataouine","Tozeur","Zaghouan");
            java.util.Collections.sort(tunisie);
        govF.setItems(tunisie);
        }
        else{
            ObservableList<String> france = FXCollections.observableArrayList("Paris","Marseille",
                    "Lion","Bordeaux","Toulouse","Toulon");
            java.util.Collections.sort(france);
            govF.setItems(france);
        }
        
    }

    @FXML
    private void scrollE(ActionEvent event) {
        if(govF.getSelectionModel().getSelectedItem().toString().equals("Ariana")){
            ObservableList<String> Ariana = FXCollections.observableArrayList("Ariana Ville"
                    ,"Ettadhamen","Kaleat el-Andalous","La Soukra","Mnihla","Raoued"
                    ,"Sidi Thabet","La petite Ariana");
            java.util.Collections.sort(Ariana);
            villeF.setItems(Ariana);
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Tunis")){
            ObservableList<String> Tunis = FXCollections.observableArrayList("Bab El Bhar"
                    ,"Bab Souika","Carthage","Cite El Khadra","Djebel Jelloud","El Kabaria"
                    ,"El Menzah","El Omrane","El Omrane superieur","El Ouardia","Ettahrir"
                    ,"Ezzouhour","Hraeria","La Goulette","La Marsa","Le Bardo","Le Kram"
                    ,"Medina","Sejoumi","Sidi El Bechir","Sidi Hassine");
            java.util.Collections.sort(Tunis);
            villeF.setItems(Tunis);
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Sfax")){
            ObservableList<String> Sfax = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Sousse")){
            ObservableList<String> Sousse = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Beja")){
            ObservableList<String> Beja = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Ben Arous")){
            ObservableList<String> Ben_Arous = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Bizerte")){
            ObservableList<String> Bizerte = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Gab�s")){
            ObservableList<String> Gabes = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Gafsa")){
            ObservableList<String> Gafsa = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Jendouba")){
            ObservableList<String> Jendouba = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Kairouan")){
            ObservableList<String> Kairouan = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Kasserine")){
            ObservableList<String> Kasserine = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Kebili")){
            ObservableList<String> Kebili = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Kef")){
            ObservableList<String> Kef = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Mahdia")){
            ObservableList<String> Mahdia = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Manouba")){
            ObservableList<String> Manouba = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Medenine")){
            ObservableList<String> Medenine = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Monastir")){
            ObservableList<String> Monastir = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Nabeul")){
            ObservableList<String> Nabeul = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Sidi Bouzid")){
            ObservableList<String> Sidi_Bouzid = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Siliana")){
            ObservableList<String> Siliana = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Tataouine")){
            ObservableList<String> Tataouine = FXCollections.observableArrayList();
        }
        else if(govF.getSelectionModel().getSelectedItem().toString().equals("Tozeur")){
            ObservableList<String> Tozeur = FXCollections.observableArrayList();
        }
        else {
            ObservableList<String> Zaghouan = FXCollections.observableArrayList();
        }
        
    }

    @FXML
    private void clearFamille(ActionEvent event) {
        clear();
    }
    
    public boolean validateFields(){
        if(nomM.getText().isEmpty() || prenomM.getText().isEmpty() ||
                nomP.getText().isEmpty() || prenomP.getText().isEmpty() ||
                dateM.getEditor().getText().isEmpty() || dateP.getEditor().getText().isEmpty() ||
                teleM.getText().isEmpty() || teleP.getText().isEmpty() || revenu.getText().isEmpty() |
                 comboM.getSelectionModel().isEmpty() |comboP.getSelectionModel().isEmpty() |
                comboB.getSelectionModel().isEmpty() | paysF.getSelectionModel().isEmpty() |
                villeF.getSelectionModel().isEmpty() | govF.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Vérifier les champs");
            alert.setHeaderText(null);
            alert.setContentText("Completer tous les champs");
            alert.showAndWait();
            
            return false;
        }
        
        return true;
    }
     public boolean validateTeleP(){
         Pattern p = Pattern.compile("[0-9]+");
         Matcher m = p.matcher(teleM.getText());
         
        if(m.find() && m.group().equals(teleM.getText())){
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Num de telephone du Pere");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le Num de tele du Pere !");
            alert.showAndWait();
            
            return false;
        }
        
    }
     public boolean validateTeleM(){
         Pattern p = Pattern.compile("[0-9]+");
         Matcher m = p.matcher(teleM.getText());
         if (m.find() && m.group().equals(teleM.getText())){
             return true;
         }
         else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Num de telephone de la Mere");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le Num de tele de la Mere !");
            alert.showAndWait();
            
            return false;
         }
     }
     public boolean validateRevenu(){
         Pattern p = Pattern.compile("[0-9]+");
         Matcher m = p.matcher(revenu.getText());
         
         if (m.find() && m.group().equals(revenu.getText())){
             return true;
         }
         else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Revenu");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le revenu");
            alert.showAndWait();
            
            return false;
         }
     }
    
    


                   
                

    @FXML
    private void verifFamille(ActionEvent event) {
        Stage stage1 = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage1.close();
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("FamilleVerifiee.fxml"));
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
    public void print(Famille f){
        try {
			
			TableView<Famille> tableView = new TableView<Famille>();
			
			TableColumn<Famille, String> col1 = new TableColumn<>("Nom Famille");
			col1.setCellValueFactory(new PropertyValueFactory<>("Nom Famille"));
			TableColumn<Famille, String> col2 = new TableColumn<>("Prenom Pere");
			col2.setCellValueFactory(new PropertyValueFactory<>("Prenom Pere"));
                        TableColumn<Famille, String> col3 = new TableColumn<>("Tele Pere");
			col2.setCellValueFactory(new PropertyValueFactory<>("Tele Pere"));
                        TableColumn<Famille, String> col4 = new TableColumn<>("Prenom Mere");
			col2.setCellValueFactory(new PropertyValueFactory<>("Prenom Mere"));
			TableColumn<Famille, String> col5 = new TableColumn<>("Tele Mere");
			col3.setCellValueFactory(new PropertyValueFactory<>("Tele Mere"));
			TableColumn<Famille, String> col6 = new TableColumn<>("Revenu");
			col4.setCellValueFactory(new PropertyValueFactory<>("revenu"));
                        TableColumn<Famille, String> col7 = new TableColumn<>("Type de besoin");
			col5.setCellValueFactory(new PropertyValueFactory<>("Type de besoin"));
			 TableColumn<Famille, String> col8 = new TableColumn<>("");
			col6.setCellValueFactory(new PropertyValueFactory<>(""));
			tableView.setVisible(false);
			tableView.getColumns().addAll(col1, col2, col3, col4,col5,col6,col7);
			
//			adding some sample data
//			for (int i = 0; i < 150; i++) {
//				tableView.getItems().add(new Event("A certain country","460,827,146", 
//						"test","test2","dfd"));
//			}
			tableView=table;
                                
//				we will create the pdf and page
				File out = new File(f.getNom_p()+".pdf");
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(out);
					PDF pdf = new PDF(fos);
					Page page = new Page(pdf, A4.PORTRAIT);
					
//					font for the table heading
					Font f1 = new Font(pdf , CoreFont.TIMES_ROMAN);
					
//					font for the pdf table data
					Font f2 = new Font(pdf, CoreFont.TIMES_ITALIC);
					
//					pdf table
					Table table = new Table();
					List<List<com.pdfjet.Cell>> tableData = new ArrayList<List<com.pdfjet.Cell>>();
					
//					table row
					List<com.pdfjet.Cell> tableRow = new ArrayList<com.pdfjet.Cell>();
					
//					let's create the headers and add them to the table row
					com.pdfjet.Cell cell = new com.pdfjet.Cell(f1, col1.getText());
					tableRow.add(cell);
					
					cell = new com.pdfjet.Cell(f1, col2.getText());
					tableRow.add(cell);
					
					cell = new com.pdfjet.Cell(f1, col3.getText());
					tableRow.add(cell);
					
					cell = new com.pdfjet.Cell(f1, col4.getText());
					tableRow.add(cell);
                                        
                                        cell = new com.pdfjet.Cell(f1, col5.getText());
					tableRow.add(cell);
                                        
                                        cell = new com.pdfjet.Cell(f1, col6.getText());
					tableRow.add(cell);
                                        
                                        cell = new com.pdfjet.Cell(f1, col7.getText());
					tableRow.add(cell);
                                        
//                                        cell = new com.pdfjet.Cell(f1, col5.getText());
//					tableRow.add(cell);
					
					//add row to table
					tableData.add(tableRow);
					
//					now let's get table view data, create a row for each and add row to table row
//					List<Famille> items = tableView.getItems();
					
//					for (Famille comm : items) {
						com.pdfjet.Cell country = new com.pdfjet.Cell(f2, f.getNom_p());
						com.pdfjet.Cell pop = new com.pdfjet.Cell(f2, f.getPrenom_p());
						com.pdfjet.Cell conss = new com.pdfjet.Cell(f2, String.valueOf(f.getTele_m()));
						com.pdfjet.Cell prod = new com.pdfjet.Cell(f2, f.getPrenom_m());
						com.pdfjet.Cell cons = new com.pdfjet.Cell(f2, String.valueOf(f.getTele_p()));
                                                com.pdfjet.Cell rev = new com.pdfjet.Cell(f2, String.valueOf(f.getR_brut_f()));
                                                com.pdfjet.Cell consta = new com.pdfjet.Cell(f2, f.getBesoin_f());
						
						tableRow = new ArrayList<com.pdfjet.Cell>();
						tableRow.add(country);
						tableRow.add(pop);
						tableRow.add(conss);
						tableRow.add(prod);
                                            	tableRow.add(cons);
                                                tableRow.add(rev);
                                                tableRow.add(consta);
						
//						add row to table
						tableData.add(tableRow);
				
					
					table.setData(tableData);
					table.setPosition(10f, 60f);
					table.setColumnWidth(0, 80f);
					table.setColumnWidth(1, 80f);
					table.setColumnWidth(2, 80f);
					table.setColumnWidth(3, 80f);
                                        table.setColumnWidth(4, 80f);
					table.setColumnWidth(5, 60f);
					table.setColumnWidth(6, 95f);
					
//					create a new page and add more table data if we get to the bottom of the current page
					while (true) {
						table.drawOn(page);
						if (!table.hasMoreData()) {
							table.resetRenderedPagesCount();
							break;
						}
						page = new Page(pdf, A4.PORTRAIT);
					}
					
					pdf.flush();
					fos.close();					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//desktop.open(out);
				System.out.println("Saved to " + out.getAbsolutePath());				
			

			
//			VBox root = new VBox(20, tableView, btnSaveToPDF);
//			root.setPadding(new Insets(30));
//			Scene scene = new Scene(root,500,500);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
		} catch(Exception e) {
		}
        
    }

       
}

