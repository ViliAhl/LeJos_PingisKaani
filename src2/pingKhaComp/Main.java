package pingKhaComp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {

    private Controller control;
    private Button btn, btn2, btn3, btn4, btn5,btn6;
    private Label label, label2;
    private ListView lista, lista2;
    private VBox vbox,vbox2;
    private Text text;
    private Node root;
    private HBox hbox, hbox2;
    private TextField Tfield;
    public void init(){

    }

    @Override
    public void start(Stage primaryStage) {
        try {

        	control = new Controller();

            hbox = new HBox();
            hbox2 = new HBox();
            vbox = new VBox();
            vbox2 = new VBox();
            btn = new Button();
            btn2 = new Button();
            btn3 = new Button();
            btn4 = new Button();
            btn5 = new Button();
            btn6 = new Button();
            label = new Label();

            text = new Text("Paina 'Ota ohjaus käyttöön' -nappia \n" +"ottaaksesi manuaalisen ohjauksen käyttöön");
            hbox.setAlignment(Pos.CENTER_LEFT);
            vbox.setAlignment(Pos.CENTER_LEFT);
            hbox2.setAlignment(Pos.CENTER_LEFT);
            vbox2.setAlignment(Pos.CENTER);

            hbox.setSpacing(8);
            btn2.setText("Vasen"+"\n"+"(A)");
            btn2.setStyle("-fx-focus-color: transparent;");
            btn3.setText("Oikea"+"\n"+"(D)");
            btn.setText("Lyönti"+"\n"+"(W)");
            btn4.setText("Ota ohjaus käyttöön");
            btn5.setText("Lopeta ohjaus");
            btn6.setText("Stop\n(S)");
            vbox2.getChildren().addAll(btn6,hbox2);
            vbox2.setVisible(false);
            hbox2.getChildren().addAll(btn2, btn, btn3);
            vbox.getChildren().addAll(text,btn4, btn5);
            hbox.getChildren().addAll(vbox2,vbox);
            hbox.setMaxSize(200, 200);
            hbox.setPrefWidth(200);

            btn6.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent e){
                        try {
							control.stop();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
                }
            });

            btn5.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent e){
                        try {
                        	vbox2.setVisible(false);
							control.lopeta();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
                }
            });
            btn2.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent e){
                        try {
							control.vasen();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
                }
            });
            btn4.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent e){
                        try {
                        	vbox2.setVisible(true);
							control.ohjaus();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
                }
            });
            btn3.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent e){
                        try {
							control.oikea();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
                }
            });
            btn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                        try {
							control.lyonti();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
            }
            });

            Scene scene = new Scene(hbox, 530,150);
            setEventHandlerVasen(hbox);
            setEventHandlerOikea(hbox);
            setEventHandlerLyonti(hbox);
            setEventHandlerStop(hbox);

            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    private void setEventHandlerVasen(Node root) {
        root.addEventHandler(KeyEvent.KEY_PRESSED, vasen -> {
            if (vasen.getCode() == KeyCode.D)
                btn2.fire();
                vasen.consume();
            });

    }
    private void setEventHandlerStop(Node root4) {
        root4.addEventHandler(KeyEvent.KEY_PRESSED, stop -> {
            if (stop.getCode() == KeyCode.S)
                btn6.fire();
                stop.consume();
            });

    }

    private void setEventHandlerOikea(Node root2) {
        root2.addEventHandler(KeyEvent.KEY_PRESSED, oikea -> {
            if (oikea.getCode() == KeyCode.A)
                btn3.fire();
                oikea.consume();
            });

    }

    private void setEventHandlerLyonti(Node root3) {
        root3.addEventHandler(KeyEvent.KEY_PRESSED, lyonti -> {
            if (lyonti.getCode() == KeyCode.W)
                btn.fire();
                lyonti.consume();
            });
    }


    public static void main(String[] args) {
        launch(args);
    }
}