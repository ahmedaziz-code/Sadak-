/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Connection.MyConnection;
import Entity.Event;
import Services.EventService;
import com.pdfjet.A4;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author alaak
 */
public class EventManagementAdminController implements Initializable {

    @FXML
    private TableView<Event> EventTable;
    @FXML
    private TableColumn<Event,String> orgCol;
    @FXML
    private TableColumn<Event,String> dateCol;
    @FXML
    private TableColumn<Event,String> neCol;
    @FXML
    private TableColumn<Event,String> imageCol;
    @FXML
    private TableColumn<Event,String> desCol;
    @FXML
    private TableColumn<Event,String> buttonCol;

    /**
     * Initializes the controller class.
     */
    
    public Connection cnx;
    public PreparedStatement ste;
    
     
    ResultSet rs = null ;
    Event e = null ;
    ObservableList<Event> EventList=FXCollections.observableArrayList();
    
    @FXML
    private TableColumn<Event,String> idCol;
    @FXML
    private TextField filterField;
    @FXML
    private Button btnSaveToPDF;
    public String b="";
     public Desktop desktop= Desktop.getDesktop();
    @FXML
    private TableColumn<String, Event> dateColF;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
         b=b+"a"; 
    }    
   
    @FXML
    private void print(ActionEvent event) {
        try {
			
			TableView<Event> tableView = new TableView<Event>();
			
			TableColumn<Event, String> col1 = new TableColumn<>("Organisateur");
			col1.setCellValueFactory(new PropertyValueFactory<>("Organisateur"));
			TableColumn<Event, String> col2 = new TableColumn<>("Date");
			col2.setCellValueFactory(new PropertyValueFactory<>("Date"));
                        TableColumn<Event, String> col3 = new TableColumn<>("DateF");
			col2.setCellValueFactory(new PropertyValueFactory<>("DateF"));
			TableColumn<Event, String> col4 = new TableColumn<>("Nom événement");
			col3.setCellValueFactory(new PropertyValueFactory<>("Nom événement"));
			TableColumn<Event, String> col5 = new TableColumn<>("Description");
			col4.setCellValueFactory(new PropertyValueFactory<>("Description"));
                        TableColumn<Event, String> col6 = new TableColumn<>("");
			col5.setCellValueFactory(new PropertyValueFactory<>(""));
			 TableColumn<Event, String> col7 = new TableColumn<>("");
			col6.setCellValueFactory(new PropertyValueFactory<>(""));
			tableView.setVisible(false);
			tableView.getColumns().addAll(col1, col2, col3, col4,col5,col6);
			

			tableView=EventTable;
                                b=b+"a";
//				we will create the pdf and page
				File out = new File("a"+b+".pdf");
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(out);
					PDF pdf = new PDF(fos);
					Page page = new Page(pdf, A4.PORTRAIT);
					
//					font for the table heading
					Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD);
					
//					font for the pdf table data
					Font f2 = new Font(pdf, CoreFont.HELVETICA);
					
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
                                        
//                                        cell = new com.pdfjet.Cell(f1, col5.getText());
//					tableRow.add(cell);
					
					//add row to table
					tableData.add(tableRow);
					
//					now let's get table view data, create a row for each and add row to table row
					List<Event> items = tableView.getItems();
					
					for (Event comm : items) {
						com.pdfjet.Cell country = new com.pdfjet.Cell(f2, comm.getEvent_organiser());
						com.pdfjet.Cell pop = new com.pdfjet.Cell(f2, comm.getEvent_date());
						com.pdfjet.Cell conss = new com.pdfjet.Cell(f2, comm.getEvent_dateF());
						com.pdfjet.Cell prod = new com.pdfjet.Cell(f2, comm.getEvent_name());
						com.pdfjet.Cell cons = new com.pdfjet.Cell(f2, comm.getEvent_content());
						
						tableRow = new ArrayList<com.pdfjet.Cell>();
						tableRow.add(country);
						tableRow.add(pop);
						tableRow.add(conss);
						tableRow.add(prod);
                                            	tableRow.add(cons);
						
//						add row to table
						tableData.add(tableRow);
					}
					
					table.setData(tableData);
					table.setPosition(70f, 60f);
					table.setColumnWidth(0, 80f);
					table.setColumnWidth(1, 70f);
					table.setColumnWidth(2, 140f);
					table.setColumnWidth(3, 140f);
					
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
					
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}

				desktop.open(out);
				System.out.println("Saved to " + out.getAbsolutePath());				
			


		} catch(Exception e) {
		}
        
    }    
    
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    public void refreshTable() {
        
        try { 
            EventList.clear();
            Connection cnx = MyConnection.getInstance().getConnection();
            
            ResultSet rs=cnx.createStatement().executeQuery("select * from news");
           while (rs.next()){
               EventList.add(new Event(rs.getString("event_organiser"),rs.getInt("event_id"),rs.getString("event_name"),rs.getString("event_date"),rs.getString("event_dateF"),
                       rs.getString("event_content"),rs.getString("event_image")));
               EventTable.setItems(EventList);
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(EventManagementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void loadDate() {
        cnx = MyConnection.getInstance().getConnection();
        
       refreshTable();
        orgCol.setCellValueFactory(new PropertyValueFactory<>("event_organiser"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("event_date"));
        dateColF.setCellValueFactory(new PropertyValueFactory<>("event_dateF"));
        neCol.setCellValueFactory(new PropertyValueFactory<>("event_name"));
        imageCol.setCellValueFactory(new PropertyValueFactory<>("event_image"));
        desCol.setCellValueFactory(new PropertyValueFactory<>("event_content"));
       
        
        
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
                                + "-glyph-size:28px;"
                                + "-fx-fill:#000;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#f4a442;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            e = EventTable.getSelectionModel().getSelectedItem();
                            
                            EventService es=new EventService();
                            System.out.println(e.getEvent_id());
                            es.supprimerEvent(e.getEvent_id());
                            refreshTable();

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                           e = EventTable.getSelectionModel().getSelectedItem(); 
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/Gui/ModEvent.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(EventManagementAdminController.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                            ModEventController mec= loader.getController();
                            mec.setTextField(e.getEvent_id(),e.getEvent_organiser(),e.getEvent_name(),e.getEvent_date(),e.getEvent_dateF(),e.getEvent_image(),e.getEvent_content());
                             Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            
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
          
         EventTable.setItems(EventList);
         Rechercher();
         
    
        
    }
    private void Rechercher(){
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
				}else if (event.getEvent_image().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (event.getEvent_dateF().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
				else if (String.valueOf(event.getEvent_organiser()).indexOf(lowerCaseFilter)!=-1)
				     return true;
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
    }

    @FXML
    private void getAddView(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Gui/AddEvent.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
           // stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EventManagementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    

    


    
}
