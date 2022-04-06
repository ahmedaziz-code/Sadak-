package Gui ;




import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Entity.Element_panier;
import Entity.produit;
import Entity.panier_holder;
import Services.produit_service;
import Services.PanierService;
import mylistener.mylistener;
import Gui.AfficherOPController;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class Shop2Controller implements Initializable {

    
    @FXML
    private Slider prixo;
    
      @FXML
    private TextField recho;
    @FXML
    private Button rechbtn;
    @FXML
    private ToggleGroup etat;
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;
      
    private produit oi;
    private Image image;
    private mylistener mylistener;
    
    produit_service os = new produit_service();
    PanierService cs = new PanierService();
    @FXML
    private Button filter;
    @FXML
    private CheckBox peint;
    @FXML
    private CheckBox art;
    @FXML
    private CheckBox deco;
    @FXML
    private CheckBox scul;
    @FXML
    private CheckBox lit;
    @FXML
    private RadioButton nouvo;
    @FXML
    private RadioButton rato;
    @FXML
    private Button oeuvres;
    @FXML
    private Button home;
    @FXML
    private Button emploi;
    @FXML
    private Button form;
    @FXML
    private Button events;
    @FXML
    private Button profil;
    @FXML
    private Button deconnexion;
    @FXML
    private Button panier;
  

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         List<produit> listOeuvre =new ArrayList<>();
        listOeuvre.addAll(os.afficherLO());
        if (listOeuvre.size() > 0) {
                
                mylistener = new mylistener() {
                    @Override
                    public void onClickListener(MouseEvent event ,produit oeuvre) {

                    }

                   

                    @Override
                    public void onClickListener(Element_panier facture) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                     @Override
                    public void onpressed(ActionEvent event, produit oeuvre) { 
                    
                                 //        changer 1 par id
        
   List<Element_panier> ListF =new ArrayList<>();
                ListF.addAll(cs.initPanier());
    Element_panier ep=new Element_panier();
                boolean existsElem=false;
        int i;
        for(i=0;i<ListF.size();i++)
        {
            if(ListF.get(i).getProd().equals(oeuvre))
            {
                existsElem=true;
                ep=ListF.get(i);
                
                 break;
            }
        }
        if(!existsElem)
        {   
            ep.setProd(oeuvre);
            ep.setQuantite_produit(1);
            ListF.add(ep);
            cs.createPanierTemp(1,ep.getProd().getRef_produit(),ep.getQuantite_produit());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Element ajouté ! ");
            alert.setContentText("Element ajouté avec sucèes ! ");
            alert.showAndWait(); 
        }
        else
        {   
            if(oeuvre.getQuantite_produit()>ep.getQuantite_produit())
            {
            cs.updatePanierTemp(1,ep.getProd().getRef_produit(),1);
             ep.setQuantite_produit(ep.getQuantite_produit()+1);
            
            ListF.set(i, ep);
            
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setHeaderText("Element existe ! ");
            alert.setContentText("Element existe deja, quantité incrementé ! ");
            alert.showAndWait(); 
            }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Stock insuffisant ! ");
            alert.setContentText("Stock insuffisant! ");
            alert.showAndWait(); 
            }
        }
            panier_holder.getInstance().setEP(ListF); }

                   

                
                
                
        
    
                        
                    
                };
            }
           
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/Gui/afficheop.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOPController itemController = fxmlLoader.getController();
                itemController.setData(listOeuvre.get(i),mylistener);
                if (column == 4) {
                    column = 0;
                    row++;
                }
                
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Shop2Controller.class.getName()).log(Level.SEVERE, null, ex);
               }
       
    }
    
    @FXML
    private void rechercheO(ActionEvent event) {
        List<produit> listOeuvre =new ArrayList<>();
       listOeuvre.addAll(os.afficherLO());
        boolean a = listOeuvre.stream().anyMatch(o -> o.getNom_produit().equalsIgnoreCase(recho.getText()));
        
        if (recho.getText().isEmpty()) {
           recho.setPromptText("veuillez remplir ce champs d'abord");
           recho.setStyle("-fx-text-inner-color: black;");
        }
        
        else if (a!=true) {
            int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/Gui/afficheop.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOPController itemController = fxmlLoader.getController();
                itemController.setData(listOeuvre.get(i),mylistener);

                if (column == 4) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Shop2Controller.class.getName()).log(Level.SEVERE, null, ex);
               }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Aucun produit avec ce nom");
            alert.showAndWait();
                    }
        
        else {
            List<produit> rechOeuvre;
            rechOeuvre =  listOeuvre.stream().filter(o -> o.getNom_produit().equalsIgnoreCase(recho.getText())).collect(Collectors.toList());
            System.out.println(rechOeuvre.size() );
            
           if (rechOeuvre.size() > 0) {
               System.out.println(rechOeuvre.get(0));
               
                mylistener = new mylistener() {
                   
                    public void onClickListener(MouseEvent event ,produit oeuvre) {

                            
                    }

                   
                   public void onpressed(ActionEvent event, produit oeuvre) { 
                       //        changer 1 par id
        List<Element_panier> ListF =new ArrayList<>();
                ListF.addAll(cs.initPanier());
    Element_panier ep=new Element_panier();
                boolean existsElem=false;
        int i;
        for(i=0;i<ListF.size();i++)
        {
            if(ListF.get(i).getProd().equals(oeuvre))
            {
                existsElem=true;
                ep=ListF.get(i);
                
                 break;
            }
        }
        if(!existsElem)
        {   
            ep.setProd(oeuvre);
            ep.setQuantite_produit(1);
            ListF.add(ep);
            cs.createPanierTemp(1,ep.getProd().getRef_produit(),ep.getQuantite_produit());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Element ajouté ! ");
            alert.setContentText("Element ajouté avec sucèes ! ");
            alert.showAndWait(); 
        }
        else
        {   
            if(oeuvre.getQuantite_produit()>ep.getQuantite_produit())
            {
            cs.updatePanierTemp(1,ep.getProd().getRef_produit(),1);
             ep.setQuantite_produit(ep.getQuantite_produit()+1);
            
            ListF.set(i, ep);
            
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setHeaderText("Element existe ! ");
            alert.setContentText("Element existe deja, quantité incrementé ! ");
            alert.showAndWait(); 
            }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Stock insuffisant ! ");
            alert.setContentText("Stock insuffisant! ");
            alert.showAndWait(); 
            }
        }
            panier_holder.getInstance().setEP(ListF); }
    

                   @Override
                   public void onClickListener(Element_panier facture) {  }

                 
                };
            }
           grid.getChildren().clear();
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < rechOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/Gui/afficheop.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                     AfficherOPController itemController = fxmlLoader.getController();
                itemController.setData(rechOeuvre.get(i),mylistener);

                if (column == 4) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                
                } catch (IOException ex) {
                   Logger.getLogger(Shop2Controller.class.getName()).log(Level.SEVERE, null, ex);
               }
        
    }
      
    }


    @FXML
    private void allerauxoeuvres(ActionEvent event) {
    }

    @FXML
    private void acuueil(ActionEvent event) {
    }

    @FXML
    private void gotoemploi(ActionEvent event) {
    }

    @FXML
    private void gotoform(ActionEvent event) {
    }

    @FXML
    private void gotoevents(ActionEvent event) {
        
        
    }

    @FXML
    private void gotoprofil(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void voirpanier(ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Gui/Panier.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(Produitdet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    
}