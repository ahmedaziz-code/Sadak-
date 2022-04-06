// بسم الله الرحمن الرحيم 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Connection.MyConnection;
import Entity.Event;
import Services.EventService;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author alaak
 */
public class EventManagementVLController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEname;
    @FXML
    private TextField txtdate;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private TextField txtDescription;
    @FXML
    private Button btnimage;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnAjouter1;
    public boolean addMod;
     int EventId;
    @FXML
    private TextField txtpath;
    @FXML
    TableView<Event> EventTable;
    @FXML
    private TableColumn<Event, String> neCol;
    @FXML
    private TableColumn<Event, String> dateCol;
    @FXML
    private TableColumn<Event, String> buttonCol;
    @FXML
    private TableColumn<Event, String> idCol;
    @FXML
    private TextField filterField;
    @FXML
    private JFXDatePicker datePickerF;
    @FXML
    private TableColumn<Event,String> dateFCol;
    @FXML
    private TextField txtDateF;

    public EventManagementVLController() {
        this.addMod = false;
    }
        public File getfile() {
        return file;}
    public Image imag;
     private FileChooser fileChooser;
    private File file;
    public Stage primaryStage;
    private Desktop desktop= Desktop.getDesktop();
     private Image myImage;
    /**
     * Initializes the controller class.
     */
     public Connection cnx;
    public PreparedStatement ste;
    String i="";
     int eventId;
    ResultSet rs = null ;
    Event e = null ;
    ObservableList<Event> EventList=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       loadDate();
        
        
    } 
    
    public void refreshTable() {
        
        try { 
            EventList.clear();
            Connection cnx = MyConnection.getInstance().getConnection();
            
            ResultSet rs=cnx.createStatement().executeQuery("select * from news");
           while (rs.next()){
               EventList.add(new Event(rs.getString("event_organiser"),rs.getInt("event_id"),rs.getString("event_name"),rs.getString("event_date"),rs.getString("event_dateF"),
                       rs.getString("event_content"),rs.getString("event_image")));
               EventTable.setItems(EventList);
               loadReverse();
           }
        } catch (SQLException ex) {
            Logger.getLogger(EventManagementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    private void loadReverse(){
        FilteredList<Event> filteredData = new FilteredList<>(EventList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(event -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (event.getEvent_date().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (event.getEvent_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (event.getEvent_dateF().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Event> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(EventTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		EventTable.setItems(sortedData);
        // EventTable.setItems(EventList);
    }
    
    private void loadDate() {
        cnx = MyConnection.getInstance().getConnection();
        
       refreshTable();
       neCol.setCellValueFactory(new PropertyValueFactory<>("event_name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("event_date"));
        dateFCol.setCellValueFactory(new PropertyValueFactory<>("event_dateF"));
        
       
    //add cell of button edit 
         Callback<TableColumn<Event, String>, TableCell<Event, String>> cellFoctory = (TableColumn<Event, String> param) -> {
            // make cell containing buttons
            final TableCell<Event, String> cell = new TableCell<Event, String>() {
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
                                + "-glyph-size:20px;"
                                + "-fx-fill:#000;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill:#f4a442;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            e = EventTable.getSelectionModel().getSelectedItem();
                            
                            EventService es=new EventService();
                            System.out.println(e.getEvent_id());
                            es.supprimerEvent(e.getEvent_id());
                            refreshTable();
                            loadReverse();
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            btnAjouter.setText("Modifier");
                           e = EventTable.getSelectionModel().getSelectedItem(); 
                            setMod(true);
                            setTextField(e.getEvent_id(),e.getEvent_organiser(),e.getEvent_name(),e.getEvent_date(),e.getEvent_dateF(),e.getEvent_image(),e.getEvent_content());
                           refreshTable(); 
                           loadReverse();
                           
                            
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
         buttonCol.setCellFactory(cellFoctory);
         //
        EventTable.setItems(EventList);
          
         loadReverse();
    
        
    }
    public Image imagee;
    void setTextField(int id,String org,String name,String date,String dateF,String image,String desc){
        eventId=id;
        txtName.setText(org);
        txtEname.setText(name);
        imagee=new Image("file:"+image);
       imageView.setImage(imagee);
        //imageView.setImage(null);
        txtdate.setText(date);
        txtDescription.setText(desc);
        txtpath.setText(image);
        txtDateF.setText(dateF);
    }
    
    @FXML
    private void setDate(ActionEvent event) {
        String Date=String.valueOf(datePicker.getValue());
        txtdate.setText(Date);
    }

   
   
    @FXML
    public void ImageChooser(ActionEvent event)  {
        FileChooser imageChooser = new FileChooser();
       
        imageChooser.getExtensionFilters().addAll(
               new ExtensionFilter("Image Files","*.png","*.jpg","*.gif")
       );
        imageChooser.setTitle("Selectionnez une Image");
        file=imageChooser.showOpenDialog(primaryStage);
        
        if(file!=null){
            //desktop.open(file);
            //textAreaImage.setText(file.getAbsolutePath());
            
            myImage = new Image(file.toURI().toString());
            imageView.setImage(myImage);
            
        }
       
       
        i=i+"b";
       
       String a=file.getAbsolutePath();
          if  (a.endsWith("png")){
              
              a="C:\\xampp\\htdocs\\img\\"+i+".png";
              
          }  else if (file.getAbsolutePath().endsWith("jpg")){
              
              a="C:\\xampp\\htdocs\\img\\"+i+".jpg";
              
        }else {
               
              a="C:\\xampp\\htdocs\\img\\"+i+".gif";
             
                }
      File fil=new File(a);
   txtpath.setText(fil.getAbsolutePath());
       // System.out.println(a);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(myImage, null), "png", fil);
        } catch (IOException ex) {
            Logger.getLogger(EventManagementVLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    void setMod(boolean b){
        addMod=b;
    }
    
    @FXML
    private void SaveEvent(ActionEvent event) {
        String Name=txtName.getText();
        String Date=String.valueOf(datePicker.getValue());
        String DateF=String.valueOf(datePickerF.getValue());
        String Ename=txtEname.getText();
        String Description=txtDescription.getText();
       //  Event e=new Event(Name,Date,Description,fis);
       
      String Path=txtpath.getText();
        
        if ( Date.isEmpty() || Ename.isEmpty() ||Description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            
            alert.setHeaderText(null);
            alert.setContentText("Remplir les donnés nécessaires s'il vous plaît");
            alert.showAndWait();

        } else{
            Event e=new Event(eventId,Ename,Description,Name,Date,DateF,Path);
            EventService es=new EventService();
            if (addMod==false){
            es.ajouterEvent(e);
            } else {
            es.modifierEvent(e);
                    }
            Alert alertt = new Alert(Alert.AlertType.CONFIRMATION);
            alertt.setHeaderText(null);
            alertt.setContentText("Sahit");
            alertt.showAndWait();
        }
       
            btnAjouter.setText("Ajouter");
             
        refreshTable();
    }
    
    @FXML
    private void clean() {
       //txtName.setText();
        txtDescription.setText(null);
        txtEname.setText(null);
        imageView.setImage(null);
        txtdate.setText(null);
        txtDateF.setText(null);
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ImageChooser(MouseEvent event) {
    }

    @FXML
    private void setDateF(ActionEvent event) {
        String Date=String.valueOf(datePickerF.getValue());
        txtDateF.setText(Date);
    }

    
    
    

    

    

    

    
    
}
