import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Comparator;


public class JavaFXApplication extends Application {
    Button tutti, studenti, atleti;
    ToggleButton perNome, perEta;
    ToggleGroup group;
    Button exit;
    ImpiantoSportivo palestra;

    public static void main(String[] args) {



        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        palestra = new ImpiantoSportivo();
        palestra.add(new Studente("Bianchi", "Anna", "4", 1990, "UniPD", "LM"));
        palestra.add(new Studente("Bianchi", "Giovanni", "3", 1995, "UniTN", "LT"));
        palestra.add(new StudenteAtleta("Ferrari", "Alberto", "7", 1993, "UniTN", "LM", "Atletica", "Internazionale"));
        palestra.add(new StudenteAtleta("Ferrari", "Vincenzo", "8", 1997, "UniVR", "LT", "Atletica", "Nazionale"));
        palestra.add(new Persona("Rossi", "Carla", "2", 1990));
        palestra.add(new Persona("Rossi", "Mario", "1", 1950));
        palestra.add(new Atleta("Verdi", "Alice", "6", 1967, "Curling", "Internazionale"));
        palestra.add(new Atleta("Verdi", "Giacomo", "5", 1991, "Nuoto", "Nazionale"));

        palestra.sort(new PersonaComparatorByCognome()); //default sorting by surname

        window.setTitle("Palestra");

        TextArea textArea = new TextArea();

        tutti = new Button("Tutti");
        studenti = new Button("Studenti");
        atleti = new Button("Atleti");
        perNome = new ToggleButton("Per Nome");
        perEta = new ToggleButton("Per Età");
        exit = new Button("exit");



        perNome.setSelected(true);
        perEta.setSelected(false);

        tutti.setOnAction(event -> {
//            palestra.stampaAbbonati("tutti");
            textArea.setText(palestra.getAbbonati("tutti"));
        });

        studenti.setOnAction(event -> {
//            palestra.stampaAbbonati("studenti");
            textArea.setText(palestra.getAbbonati("studenti"));
        });

        atleti.setOnAction(event -> {
//            palestra.stampaAbbonati("atleti");
            textArea.setText(palestra.getAbbonati("atleti"));
        });

        exit.setOnAction(event -> {
            window.close();
            System.out.println("bye!");
            System.exit(0);

        });

        perNome.setOnAction(e -> {
            palestra.sort(new PersonaComparatorByCognome());
            perEta.setSelected(false);
            perNome.setSelected(true);
        });

        perEta.setOnAction(e -> {
            palestra.sort(new PersonaComparatorByEta());
            perNome.setSelected(false);
            perEta.setSelected(true);
        });

        perEta.setToggleGroup(group);
        perNome.setToggleGroup(group);



        Insets padding = new Insets(10, 10, 10, 10);


        HBox topLeftMenu = new HBox();
        topLeftMenu.getChildren().addAll(tutti, studenti, atleti);
        topLeftMenu.setSpacing(8);

        HBox topRightMenu = new HBox();
        topRightMenu.getChildren().addAll(perNome, perEta);
        topRightMenu.setSpacing(8);

        AnchorPane topMenu = new AnchorPane();
        topMenu.getChildren().addAll(topLeftMenu, topRightMenu);
        topMenu.setLeftAnchor(topLeftMenu, 8.0);
        topMenu.setRightAnchor(topRightMenu, 8.0);
        topMenu.setPadding(padding);
        topMenu.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, CornerRadii.EMPTY, Insets.EMPTY)));


        AnchorPane bottomMenu = new AnchorPane();
        //HBox bottomMenu = new HBox();
        bottomMenu.getChildren().add(exit);
        //bottomMenu.setAlignment(Pos.BOTTOM_RIGHT);
        bottomMenu.setBottomAnchor(exit, 8.0);
        bottomMenu.setRightAnchor(exit, 5.0);


        BorderPane layout = new BorderPane();
        //layout.getChildren().addAll(topMenu, textArea, bottomMenu);
        layout.setTop(topMenu);
        layout.setCenter(textArea);
        layout.setBottom(bottomMenu);

        Scene scene = new Scene(layout, 500, 250);
        window.setScene(scene);
        window.show();
    }
}
