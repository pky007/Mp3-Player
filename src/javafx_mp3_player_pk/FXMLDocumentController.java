package javafx_mp3_player_pk;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.net.URL;
//import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author PK YADAV
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Pane paneDown;
    @FXML
    private JFXButton pre;
    @FXML
    private JFXButton next;
    @FXML
    private JFXButton play;
    @FXML
    private Pane paneTop;
    @FXML
    private JFXHamburger menu;
    @FXML
    private MediaView mediaView;
    @FXML
    private JFXProgressBar sliderBar;
    @FXML
    private AnchorPane sidePane;
    @FXML
    private JFXButton fileChooser;
    private MediaPlayer mediaPlayer;
    private String path;
    @FXML
    private FontAwesomeIconView playButton;
    private File file;
   
    @FXML
    private JFXSlider vmSlider;
    @FXML
    private Label nowPlaying;
    @FXML
    private Label nowPlaying1;
    HamburgerBackArrowBasicTransition ht;
    @FXML
    private ListView list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        
        

         ht = new HamburgerBackArrowBasicTransition(menu);
        ht.setRate(-1);
        menu.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            ht.setRate(ht.getRate() * -1);
            ht.play();
        

            if (!sidePane.isVisible()) {
                sidePane.setVisible(true);
            } else {
                sidePane.setVisible(false);
            }

        });

    }
    
  
    
    
    
    

    @FXML
    private void previous(ActionEvent event) {
        mediaPlayer.setRate(.75);
    }

    @FXML
    private void next(ActionEvent event) {
        mediaPlayer.setRate(1.5);
    }

    @FXML
    private void play(ActionEvent event) {

        if (file != null && playButton.getGlyphName().equals("PAUSE_CIRCLE")) {
            mediaPlayer.pause();
            // mediaPlayer.get
            playButton.setGlyphName("PLAY_CIRCLE");
        } else if (file != null && playButton.getGlyphName().equals("PLAY_CIRCLE")) {
            mediaPlayer.setRate(1);
            mediaPlayer.play();
            playButton.setGlyphName("PAUSE_CIRCLE");
        }

    }

    @FXML
    private void exitMain(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void openSongs(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select a file(*.mp3)", "*.mp3");

        fileChooser.getExtensionFilters().add(filter);
        file = fileChooser.showOpenDialog(null);
   // File  files=  fileChooser.showOpenDialog(null);
       if(file!=null)
       {
       
            // for(int i=0; i<files.size();i++)
                list.getItems().add(file.getAbsoluteFile());
                 
       }
        
        path = file.toURI().toString();
        System.out.println(path);
        sidePane.setVisible(false);         
          ht.setRate(-1);
          ht.play();
        
         
              
            if (file != null) {            
            Media media = new Media(path);          
             
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            
                 

            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    //sliderBar.setProgress(oldValue.toSeconds());
                    //  sliderBar.setProgress(newValue.divide(mediaPlayer.getMedia().getDuration()).toMillis());
                    //  sliderBar.setValue(newValue.toSeconds());

                    sliderBar.setProgress(1.0 * mediaPlayer.getCurrentTime().toMillis() / mediaPlayer.getTotalDuration().toMillis());

                    // newPlayer.currentTimeProperty().addListener(progressChangeListener);
                    String source = mediaPlayer.getMedia().getSource();
                    source = source.substring(0, source.length() - ".mp3".length());
                    source = source.substring(source.lastIndexOf("/") + 1).replaceAll("%20", " ");
                    nowPlaying.setText(source);
                    nowPlaying1.setText(source);

                }

            });

            sliderBar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    // mediaPlayer.seek(Duration.seconds(sliderBar.));

                    //sliderBar.
                }

            });

            vmSlider.setValue(mediaPlayer.getVolume() * 100);
            vmSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(vmSlider.getValue() / 100);
                }

            });

            playButton.setGlyphName("PAUSE_CIRCLE");
            mediaPlayer.play();

        }

    }

}
