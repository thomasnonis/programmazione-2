package esame;

import esame.auto.*;
import esame.auto.motori.*;
import java.util.ArrayList;
//Note: Calendars range from 0 (January) to 11 (December)
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Esame 2018/07/05 - Concessionaria
 * <br>
 * Main class of the project.
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Concessionaria extends Application{
    /**
     * The width of the displayed window in pixels
     */
    public static final double WIDTH = 400;
    /**
     * The height of the displayed window in pixels
     */
    public static final double HEIGHT = 200;
    /**
     * The title of the displayed window
     */
    private static final String TITLE = "Concessionaria auto";
    /**
     * The ID of the currently displayed car. It is initialized to the first car on the list
     */
    private int displayedID = 1;
    /**
     * The list of cars
     */
    private List<Auto> cars = new ArrayList<>();
    
    Button leftBtn;
    Button rightBtn;
    Button orderBtn;
    HBox btns;
    Text text;
    AnchorPane root;
    Scene scene;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init(){
        /**
         * Layout initialization
         */
        leftBtn = new Button("<");
        rightBtn = new Button(">");
        orderBtn = new Button("id");
        btns = new HBox();
        btns.getChildren().addAll(orderBtn, leftBtn, rightBtn);
        
        text = new Text();
        
        
        root = new AnchorPane();
        root.getChildren().addAll(text, btns);
        root.setTopAnchor(text, 0.0);
        root.setBottomAnchor(btns, 6.0);
        root.setRightAnchor(btns, 8.0);
        
        scene = new Scene(root, WIDTH, HEIGHT);
        
        /**
         * Data initialization
         */
        
        cars.add(new Berlina("B1", new Benzina(2000), 2016, 20000.0, Calendar.FEBRUARY));
        cars.add(new Berlina("B2", new Diesel(1800), 2018, 30000.0, Calendar.JUNE));
        cars.add(new Berlina("B3", new Ibrido(2200), 2017, 35000.0, Calendar.APRIL));
        cars.add(new Utilitaria("U1", new Benzina(1000), 2018, 10000.0, Calendar.JANUARY));
        cars.add(new Utilitaria("U2", new Ibrido(1300), 2014, 18000.0, Calendar.FEBRUARY));
        cars.add(new Utilitaria("U3", new Diesel(1200), 2016, 12000.0, Calendar.JUNE));
        cars.add(new Sportiva("S1", new Benzina(3000), 2015, 50000.0, Calendar.MARCH, "spoiler", "cambio automatico"));
        cars.add(new Sportiva("S2", new Benzina(2800), 2018, 30000.0, Calendar.JUNE, "tetto a scomparsa", "sedili in pelle"));
        cars.add(new Sportiva("S3", new Benzina(3000), 2013, 65000.0, Calendar.MAY, "cromature", "volante ergonomico"));
        
        Collections.sort(cars, new ByIDComparator());
        text.setText(cars.get(displayedID - 1).toString());
        leftBtn.setDisable(true);
    }
    
    @Override
    public void start(Stage window){ 
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle(TITLE);
        window.show();      
       
        /**
         * Listeners
         */
        orderBtn.setOnAction((ActionEvent event) -> {
            if(orderBtn.getText().equals("id")){
                orderBtn.setText("mese");
                Collections.sort(cars, new ByMonthComparator());
                displayedID = 1;
                text.setText(cars.get(displayedID - 1).toString());
                leftBtn.setDisable(true);
                rightBtn.setDisable(false);
                System.out.println(">Order set to \"Mese\"");
            }else{
                orderBtn.setText("id");
                Collections.sort(cars, new ByIDComparator());
                displayedID = 1;
                text.setText(cars.get(displayedID - 1).toString());
                leftBtn.setDisable(true);
                rightBtn.setDisable(false);
                System.out.println(">Order set to \"Id\"");
            }
        });
        
        leftBtn.setOnAction((ActionEvent event) -> {
            if(displayedID == cars.size() - 1){
                rightBtn.setDisable(false);
            }
            if(displayedID > 1){
                text.setText(cars.get(--displayedID - 1).toString());
            }
            if(displayedID == 1){
                leftBtn.setDisable(true);
            }
            System.out.println(">Displaying " + displayedID);
            
        });
        
        rightBtn.setOnAction((ActionEvent event) -> {
            if(displayedID == 1){
                leftBtn.setDisable(false);
            }
            if(displayedID < cars.size()){
                text.setText(cars.get(++displayedID - 1).toString());
            }
            if(displayedID == cars.size()){
                rightBtn.setDisable(true);
            }
            System.out.println(">Displaying " + displayedID);
        });
        
    }
}
