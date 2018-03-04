package com.novts;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class JavaFXTextEditorLighting extends Application {
    
    Slider sliderDiffuseConstant;
    Slider sliderSpecularConstant;
    Slider sliderSurfaceScale;
    Slider sliderDistantAzimuth;
    Slider sliderDistantElevation;
    Slider sliderPointX;
    Slider sliderPointY;
    Slider sliderPointZ;
    Slider sliderSpotPointsAtX;
    Slider sliderSpotPointsAtY;
    Slider sliderSpotPointsAtZ;
    Slider sliderSpecularExponent;
    StackPane stack;
    Stage primaryStage;
    
    Button btnDistant;    
    Button btnPoint;
    Button btnSpot;

    WebView wv;
    Group stackG =new Group();
  
    VBox vboxR;
    Color cB=Color.WHITE;
    Color cT=Color.BLACK;
    String font_size="8pt";
    String font_fam="Georgia";
    String font_style="normal";
    String font_weight="normal";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        primaryStage=stage;
        primaryStage.setTitle("JavaFXTextEditor");
         Group root = new Group();
         Scene scene = new Scene(root, 1200, 650, Color.WHITE);

        wv = new WebView();
        wv.getEngine().load("http://ru.novts.com/adspage");
        
         Light.Distant lightDistant = new Light.Distant();   
        Light.Point lightPoint = new Light.Point(); 
        Light.Spot lightSpot = new Light.Spot(); 

         Lighting effect = new Lighting();
        effect.setLight(lightDistant);  
        
        FileChooser fileChooser=new FileChooser();
        fileChooser.setInitialDirectory(new java.io.File("C:/")); 
        fileChooser.setTitle("Select image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpg, png, bmp, gif", "*.jpg", "*.png", "*.bmp", "*.gif"));
       
      vboxR = new VBox();
      vboxR.setPadding(new Insets(10));
      vboxR.setSpacing(10);
      vboxR.setLayoutX(820);
      vboxR.setLayoutY(5);
      
       HBox hboxBtn = new HBox();
      hboxBtn.setPadding(new Insets(10));
      hboxBtn.setSpacing(10);
     
      
        ScrollPane sp=new ScrollPane();
       sp.setPrefSize(350, 300);
      
       vboxR.getChildren().addAll(hboxBtn,sp);
        
         VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
       
        
        sp.setContent(vbox);
        
         VBox vboxE = new VBox();
        vboxE.setPadding(new Insets(10));
        vboxE.setSpacing(10);       
        
        vbox.getChildren().add(vboxE);        
                
        Label labelDiffuseConstant=new Label("diffuseConstant");
        sliderDiffuseConstant=new Slider();
        sliderDiffuseConstant.setValue(1.0);
        sliderDiffuseConstant.setPrefWidth(300); 
        sliderDiffuseConstant.setMin(0.0);
        sliderDiffuseConstant.setMax(2.0);
        sliderDiffuseConstant.setShowTickMarks(true);
        sliderDiffuseConstant.setMajorTickUnit(0.2);
        sliderDiffuseConstant.setShowTickLabels(true);
        sliderDiffuseConstant.setMinorTickCount(3);        
        effect.diffuseConstantProperty().bind(sliderDiffuseConstant.valueProperty());       
        
        Label labelSpecularConstant=new Label("specularConstant");
        sliderSpecularConstant=new Slider();
        sliderSpecularConstant.setValue(0.3);
        sliderSpecularConstant.setPrefWidth(300); 
        sliderSpecularConstant.setMin(0.0);
        sliderSpecularConstant.setMax(2.0);
        sliderSpecularConstant.setShowTickMarks(true);
        sliderSpecularConstant.setMajorTickUnit(0.2);
        sliderSpecularConstant.setShowTickLabels(true);
        sliderSpecularConstant.setMinorTickCount(3);
        effect.specularConstantProperty().bind(sliderSpecularConstant.valueProperty());
                
        Label labelSurfaceScale=new Label("surfaceScale");
        sliderSurfaceScale=new Slider();
        sliderSurfaceScale.setValue(1.5);
        sliderSurfaceScale.setPrefWidth(300); 
        sliderSurfaceScale.setMin(0.0);
        sliderSurfaceScale.setMax(10.0);
        sliderSurfaceScale.setShowTickMarks(true);
        sliderSurfaceScale.setMajorTickUnit(1);
        sliderSurfaceScale.setShowTickLabels(true);
        effect.surfaceScaleProperty().bind(sliderSurfaceScale.valueProperty());       
       
        vboxE.getChildren().addAll(labelDiffuseConstant,sliderDiffuseConstant,labelSpecularConstant, sliderSpecularConstant,labelSurfaceScale, sliderSurfaceScale);
        
        VBox vboxL = new VBox();
        vboxL.setPadding(new Insets(10));
        vboxL.setSpacing(10);
        
        vbox.getChildren().add(vboxL);     
        
         Label labelDistantAzimuth=new Label("azimuth");
        sliderDistantAzimuth=new Slider();
        sliderDistantAzimuth.setValue(45.0);
        sliderDistantAzimuth.setPrefWidth(300); 
        sliderDistantAzimuth.setMin(-360.0);
        sliderDistantAzimuth.setMax(360.0);
        sliderDistantAzimuth.setShowTickMarks(true);
        sliderDistantAzimuth.setMajorTickUnit(50);
        sliderDistantAzimuth.setShowTickLabels(true);
        lightDistant.azimuthProperty().bind(sliderDistantAzimuth.valueProperty());
        
         Label labelDistantElevation=new Label("elevation");
        sliderDistantElevation=new Slider();
        sliderDistantElevation.setValue(45.0);
        sliderDistantElevation.setPrefWidth(300);         
        sliderDistantElevation.setMin(-360.0);
        sliderDistantElevation.setMax(360.0);
        sliderDistantElevation.setShowTickMarks(true);
        sliderDistantElevation.setMajorTickUnit(50);
        sliderDistantElevation.setShowTickLabels(true);
        lightDistant.elevationProperty().bind(sliderDistantElevation.valueProperty());
        
         Label labelPointX=new Label("X");
        sliderPointX=new Slider();        
        sliderPointX.setPrefWidth(300); 
        sliderPointX.setMin(-500.0);
        sliderPointX.setMax(500.0);
        sliderPointX.setShowTickMarks(true);
        sliderPointX.setMajorTickUnit(50);
        sliderPointX.setShowTickLabels(true);
        lightPoint.xProperty().bind(sliderPointX.valueProperty());
        lightSpot.xProperty().bind(sliderPointX.valueProperty());
        
         Label labelPointY=new Label("Y");
        sliderPointY=new Slider();        
        sliderPointY.setPrefWidth(300); 
        sliderPointY.setMin(-500.0);
        sliderPointY.setMax(500.0);
        sliderPointY.setShowTickMarks(true);
        sliderPointY.setMajorTickUnit(50);
        sliderPointY.setShowTickLabels(true);
        lightPoint.yProperty().bind(sliderPointY.valueProperty());
        lightSpot.yProperty().bind(sliderPointY.valueProperty());
        
         Label labelPointZ=new Label("Z");
        sliderPointZ=new Slider();  
        sliderPointZ.setValue(500.0);
        sliderPointZ.setPrefWidth(300); 
        sliderPointZ.setMin(0.0);
        sliderPointZ.setMax(1000.0);
        sliderPointZ.setShowTickMarks(true);
        sliderPointZ.setMajorTickUnit(100);
        sliderPointZ.setShowTickLabels(true);
        lightPoint.zProperty().bind(sliderPointZ.valueProperty());
        lightSpot.zProperty().bind(sliderPointZ.valueProperty());
        
         Label labelSpotPointsAtX =new Label("pointsAtX");
        sliderSpotPointsAtX=new Slider();          
        sliderSpotPointsAtX.setPrefWidth(300); 
        sliderSpotPointsAtX.setMin(-500.0);
        sliderSpotPointsAtX.setMax(500.0);
        sliderSpotPointsAtX.setShowTickMarks(true);
        sliderSpotPointsAtX.setMajorTickUnit(50);
        sliderSpotPointsAtX.setShowTickLabels(true);
        lightSpot.pointsAtXProperty().bind(sliderSpotPointsAtX.valueProperty());
        
         Label labelSpotPointsAtY =new Label("pointsAtY");
        sliderSpotPointsAtY=new Slider();         
        sliderSpotPointsAtY.setPrefWidth(300); 
        sliderSpotPointsAtY.setMin(-500.0);
        sliderSpotPointsAtY.setMax(500.0);
        sliderSpotPointsAtY.setShowTickMarks(true);
        sliderSpotPointsAtY.setMajorTickUnit(50);
        sliderSpotPointsAtY.setShowTickLabels(true);
        lightSpot.pointsAtYProperty().bind(sliderSpotPointsAtY.valueProperty());
        
         Label labelSpotPointsAtZ=new Label("pointsAtZ");
        sliderSpotPointsAtZ=new Slider();          
        sliderSpotPointsAtZ.setPrefWidth(300); 
        sliderSpotPointsAtZ.setMin(0.0);
        sliderSpotPointsAtZ.setMax(1000.0);
        sliderSpotPointsAtZ.setShowTickMarks(true);
        sliderSpotPointsAtZ.setMajorTickUnit(100);
        sliderSpotPointsAtZ.setShowTickLabels(true);
        lightSpot.pointsAtZProperty().bind(sliderSpotPointsAtZ.valueProperty());
        
         Label labelSpecularExponent=new Label("specularExponent");
        sliderSpecularExponent=new Slider();  
        sliderSpecularExponent.setValue(1.0);
        sliderSpecularExponent.setPrefWidth(300); 
        sliderSpecularExponent.setMin(0.0);
        sliderSpecularExponent.setMax(4.0);
        sliderSpecularExponent.setShowTickMarks(true);
        sliderSpecularExponent.setMajorTickUnit(0.4);
        sliderSpecularExponent.setShowTickLabels(true);
        sliderSpecularExponent.setMinorTickCount(3); 
        lightSpot.specularExponentProperty().bind(sliderSpecularExponent.valueProperty());
         
        vboxL.getChildren().addAll(labelDistantAzimuth,sliderDistantAzimuth, labelDistantElevation, sliderDistantElevation);
        
        btnDistant = new Button();        
        btnDistant.setLayoutX(10);
        btnDistant.setLayoutY(600);
        btnDistant.setText("Remote\n Light");
        btnDistant.setCursor(Cursor.CLOSED_HAND);          
        btnDistant.setOnAction((ActionEvent event)-> {
               effect.setLight(lightDistant);  
               vboxL.getChildren().clear();
               
               vboxL.getChildren().addAll(labelDistantAzimuth,sliderDistantAzimuth, labelDistantElevation, sliderDistantElevation);
            });
        
        btnPoint = new Button();        
        btnPoint.setLayoutX(170);
        btnPoint.setLayoutY(600);
        btnPoint.setText("Fixed\n Light");
        btnPoint.setCursor(Cursor.CLOSED_HAND);          
        btnPoint.setOnAction((ActionEvent event)-> {
               effect.setLight(lightPoint);  
               vboxL.getChildren().clear();
               
               vboxL.getChildren().addAll(labelPointX,sliderPointX, labelPointY,sliderPointY, labelPointZ,sliderPointZ);
            });
        
        btnSpot = new Button();        
        btnSpot.setLayoutX(300);
        btnSpot.setLayoutY(600);
        btnSpot.setText("Fixed\n light with a focus");
        btnSpot.setCursor(Cursor.CLOSED_HAND);          
        btnSpot.setOnAction((ActionEvent event)-> {
               effect.setLight(lightSpot);  
               vboxL.getChildren().clear();
                              
               vboxL.getChildren().addAll(labelPointX,sliderPointX, labelPointY,sliderPointY, labelPointZ,sliderPointZ, labelSpotPointsAtX,sliderSpotPointsAtX, labelSpotPointsAtY,sliderSpotPointsAtY, labelSpotPointsAtZ,sliderSpotPointsAtZ, labelSpecularExponent,sliderSpecularExponent);
            });
        
        hboxBtn.getChildren().addAll(btnDistant, btnPoint, btnSpot);
        
         Button btnR= new Button();        
       
        btnR.setText("Reset");
        btnR.setStyle("-fx-font: bold 16pt Georgia;" );          
        btnR.setOnMouseClicked((MouseEvent event)-> {
      initR();
});

        Button btnS= new Button();
       Label labelOP =new Label("Enter the text:");
       TextArea areaOp= new TextArea();
       areaOp.setPrefSize(250, 50);
       HBox hboxT=new HBox();
       hboxT.setSpacing(10);
       hboxT.getChildren().addAll(labelOP, areaOp);
       
        ColorPicker colorPickerB = new ColorPicker();
 colorPickerB.setOnAction((ActionEvent t)-> {
         cB = colorPickerB.getValue();        
     }
 );
 colorPickerB.setValue(Color.WHITE);
 
  ColorPicker colorPickerT = new ColorPicker();
 colorPickerT.setOnAction((ActionEvent t)-> {
         cT = colorPickerT.getValue();        
     }
 );
 colorPickerT.setValue(Color.BLACK);
 
  ObservableList<String> fontSize = FXCollections.observableArrayList(
          "8pt","10pt","12pt","14pt","16pt","18pt","20pt","22pt","24pt","26pt","28pt","30pt",
          "32pt","34pt","36pt","38pt","40pt","42pt","44pt","46pt","48pt","50pt","52pt","54pt","56pt",
          "58pt","60pt","62pt","64pt","66pt","68pt","70pt","72pt","74pt","76pt","78pt","80pt"
          );
 ChoiceBox choiceFS = new ChoiceBox<String>(fontSize); 
choiceFS.getSelectionModel().selectFirst();    
choiceFS.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue observable, Object oldValue, Object newValue)-> {
        font_size=newValue.toString();
});


  ObservableList<String> fontFamily = FXCollections.observableArrayList(
          "Georgia","Arial","Comic Sans MS","Impact","Lucida Console",
         "Tahoma","Times New Roman","Verdana","MS Serif"
          );
 ChoiceBox choiceFF = new ChoiceBox<String>(fontFamily); 
choiceFF.getSelectionModel().selectFirst();    
choiceFF.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue observable, Object oldValue, Object newValue)-> {
   font_fam=newValue.toString();
       });

ObservableList<String> fontStyle = FXCollections.observableArrayList(
          "normal","italic"
          );
 ChoiceBox choiceFSt = new ChoiceBox<String>(fontStyle); 
choiceFSt.getSelectionModel().selectFirst();    
choiceFSt.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue observable, Object oldValue, Object newValue)-> {
   font_style=newValue.toString();
       });

ObservableList<String> fontWeight = FXCollections.observableArrayList(
          "normal","bold"
          );
 ChoiceBox choiceFW = new ChoiceBox<String>(fontWeight); 
choiceFW.getSelectionModel().selectFirst();    
choiceFW.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue observable, Object oldValue, Object newValue)-> {
   font_weight=newValue.toString();
       });
 
  HBox hboxCB=new HBox();
       hboxCB.setSpacing(10);
       hboxCB.getChildren().addAll(colorPickerB,new Label(":Background color"),choiceFS,new Label(":Font size"));
       
 HBox hboxCT=new HBox();
       hboxCT.setSpacing(10);
       hboxCT.getChildren().addAll(colorPickerT,new Label(":Text color"),choiceFF,new Label(":Font") );
       
HBox hboxF=new HBox();
       hboxF.setSpacing(10);
       hboxF.getChildren().addAll(choiceFSt,new Label(":Text style"),choiceFW,new Label(":Font width") );       
              
        Button btnOp= new Button(); 
       HBox hboxB=new HBox();
       hboxB.setSpacing(10);
       hboxB.getChildren().addAll(btnOp,btnR, btnS);
       vboxR.getChildren().addAll(hboxT,hboxCB,hboxCT, hboxF,hboxB);
       
       
        btnOp.setText("Download");
        btnOp.setStyle("-fx-font: bold 16pt Georgia;" );          
        btnOp.setOnMouseClicked((MouseEvent event)-> {
                try { 
        String text=areaOp.getText();
        Label lb=new Label(text);
        lb.setWrapText(true);
        lb.setMaxSize(800,600);
        String colorB=cB.toString();
        colorB=colorB.replace("0x", "#");
        String colorT=cT.toString();
        colorT=colorT.replace("0x", "#");
        lb.setStyle("-fx-font-weight:"+font_weight+";-fx-font-style:"+font_style+";-fx-font-family:"+font_fam+";-fx-font-size:"+font_size+";-fx-background-color:"+colorB+";-fx-text-fill:"+colorT+"; -fx-padding: 25px;" );
        lb.setEffect(effect);
        stack = new StackPane();         
        stack.getChildren().add(lb);       
        stack.setPadding(new Insets(5,5,5,5));
        stack.setStyle("-fx-border-color:black; -fx-border-width: 3pt;");
        stack.setLayoutX(5);
        stack.setLayoutY(110);

        wv.setPrefHeight(100);
        
        root.getChildren().clear();
        root.getChildren().addAll(vboxR);
                    stackG.getChildren().clear();
                    stackG.getChildren().addAll(wv, stack) ;
                    root.getChildren().add(stackG);
        } catch (Exception ex) {                
                }
});
       
       
        btnS.setText("Save");
        btnS.setStyle("-fx-font: bold 16pt Georgia;" );          
        btnS.setOnMouseClicked((MouseEvent event)-> {
         
  root.getChildren().clear();
  Button btnSIn= new Button();
  btnSIn.setText("Save Image");
  btnSIn.setStyle("-fx-font: bold 16pt Georgia;" );
  btnSIn.setOnMouseClicked((MouseEvent e)-> {
    try {
        WritableImage wimg = stack.snapshot(new SnapshotParameters(), null);
        BufferedImage screenShot = SwingFXUtils.fromFXImage(wimg, null);
FileChooser fileChooserIn=new FileChooser();
fileChooserIn.setInitialDirectory(new java.io.File("C:/users"));
fileChooserIn.setTitle("Save Image");
fileChooserIn.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpg, png, bmp, gif", "*.jpg", "*.png", "*.bmp", "*.gif"));
java.io.File file=fileChooserIn.showSaveDialog(primaryStage);
int extIndex=file.getPath().lastIndexOf(".");
String ext=file.getPath().substring(extIndex+1);
ImageIO.write(screenShot, ext, file);   
 root.getChildren().clear();
 stack.setStyle("-fx-border-color:black; -fx-border-width: 3pt;");
 root.getChildren().addAll(vboxR);
        stackG.getChildren().clear();
        stackG.getChildren().addAll(wv, stack);
        root.getChildren().add(stackG);
 
       } catch (Exception ex) {
                   
                }      
            });

Button btnB= new Button();  
btnB.setText("Back");
btnB.setStyle("-fx-font: bold 16pt Georgia;" );          
btnB.setOnMouseClicked((MouseEvent ev)-> {
    root.getChildren().clear();
    root.getChildren().addAll(vboxR);
    stackG.getChildren().clear();
    stackG.getChildren().addAll(wv, stack);
    root.getChildren().add(stackG);
            });
      
VBox vboxIn=new VBox();
vboxIn.setPadding(new Insets(10));
vboxIn.setSpacing(30);
vboxIn.setLayoutX(820);
vboxIn.setLayoutY(20);
vboxIn.getChildren().addAll(btnSIn,btnB) ;
stack.setStyle("-fx-border-color:white; -fx-border-width: 0pt;");
            stackG.getChildren().clear();
            stackG.getChildren().addAll(wv, stack);
            root.getChildren().addAll(stackG, vboxIn);
});


        stackG.getChildren().addAll(wv) ;
        root.getChildren().addAll(stackG, vboxR);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void initR(){      
        
        sliderDiffuseConstant.setValue(1.0);          
        
        sliderSpecularConstant.setValue(0.3);
       
        sliderSurfaceScale.setValue(1.5);
        
        sliderDistantAzimuth.setValue(45.0);
       
        sliderDistantElevation.setValue(45.0);
       
        sliderPointX.setValue(0.0);
       
        sliderPointY.setValue(0.0);
        
        sliderPointZ.setValue(500.0);
       
        sliderSpotPointsAtX.setValue(0.0);
       
        sliderSpotPointsAtY.setValue(0.0);
        
        sliderSpotPointsAtZ.setValue(0.0);
        
        sliderSpecularExponent.setValue(1.0);
    }
}
