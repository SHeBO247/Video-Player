package video.player;

import java.io.File;
import static java.lang.Math.floor;
import static java.lang.String.format;
import javafx.application.Application;
import static javafx.application.Platform.runLater;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MP3Player extends Application {
    
    MediaPlayer mediaPlayer;
    private Label time;
    Duration duration;
    Scene scene;
    Media media;
    double width;
    double height;
    MediaView mediaView;
    //HBox toolBar;
    
    //Button filesButton, firstButton, backButton, playButton, pauseButton, forwardButton, lastButton, retryButton, fullScreenButton;
    
    @Override
    public void start(Stage primaryStage) {
        //The location of your file
        media = new Media(new File("C:/Users/SHeBO/Desktop/vid.mp4").toURI().toString());

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(false);
        mediaView = new MediaView(mediaPlayer);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(mediaView);
        
        HBox toolBar = addToolBar();
        borderPane.setBottom(toolBar);

        borderPane.setStyle("-fx-background-color: Black");

        Scene scene = new Scene(borderPane, 600, 600);
        scene.setFill(Color.BLACK);
        
        // Adding Images to buttons
        // Play Button
        Image playButtonImage = new Image(getClass().getResourceAsStream("Play.png"));
        Button playButton = new Button();
        playButton.setStyle("-fx-background-color: Transparnt");
        playButton.setGraphic(new ImageView(playButtonImage));

        playButton.setOnAction((ActionEvent e) -> {
            mediaPlayer.play();
        });
        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            playButton.setStyle("-fx-background-color: Black");
            playButton.setStyle("-fx-body-color: Black");
        });
        playButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            playButton.setStyle("-fx-background-color: Black");
        });
        
        // Pause Button
        Image pausedButtonImage = new Image(getClass().getResourceAsStream("Pause.png"));
        Button pauseButton = new Button();
        pauseButton.setStyle("-fx-background-color: Transparnt");
        pauseButton.setGraphic(new ImageView(pausedButtonImage));

        pauseButton.setOnAction((ActionEvent e) -> {
            mediaPlayer.pause();
        });

        pauseButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            pauseButton.setStyle("-fx-background-color: Black");
            pauseButton.setStyle("-fx-body-color: Black");
        });
        pauseButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            pauseButton.setStyle("-fx-background-color: Black");
        });
        
        // Files Button
        Image filesButtonImage = new Image(getClass().getResourceAsStream("Files.png"));
        Button filesButton = new Button();
        filesButton.setStyle("-fx-background-color: Transparnt");
        filesButton.setGraphic(new ImageView(filesButtonImage));

        filesButton.setOnAction((ActionEvent e) -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4"));
            
            File file = chooser.showOpenDialog(null);
            String filePath = "";
            if (file != null){
                filePath = file.getAbsolutePath();
                filePath = filePath.replace("\\", "/");
                primaryStage.setTitle(file.getName());

                media = new Media(new File(filePath).toURI().toString());
                mediaPlayer.stop();
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                
                updateValues();
                
                mediaView.setMediaPlayer(mediaPlayer);
            }
            else{
                return;
            }
            
        });
        filesButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            filesButton.setStyle("-fx-background-color: Black");
            filesButton.setStyle("-fx-body-color: Black");
        });
        filesButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            filesButton.setStyle("-fx-background-color: Black");
        });
        
        // First Button
        Image firstButtonImage = new Image(getClass().getResourceAsStream("First.png"));
        Button firstButton = new Button();
        firstButton.setStyle("-fx-background-color: Transparnt");
        firstButton.setGraphic(new ImageView(firstButtonImage));

        firstButton.setOnAction((ActionEvent e) -> {
            mediaPlayer.seek(mediaPlayer.getStartTime());
            mediaPlayer.stop();
        });

        firstButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            firstButton.setStyle("-fx-background-color: Black");
            firstButton.setStyle("-fx-body-color: Black");
        });
        firstButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            firstButton.setStyle("-fx-background-color: Black");
        });
        
        // Forward Button
        Image forwardButtonImage = new Image(getClass().getResourceAsStream("Forward.png"));
        Button forwardButton = new Button();
        forwardButton.setStyle("-fx-background-color: Transparent");
        forwardButton.setGraphic(new ImageView(forwardButtonImage));

        forwardButton.setOnAction((ActionEvent e) -> {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().multiply(1.5));
        });
        forwardButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            forwardButton.setStyle("-fx-background-color: Black");
            forwardButton.setStyle("-fx-body-color: Black");
        });
        forwardButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            forwardButton.setStyle("-fx-background-color: Black");
        });
        
        // Last Button
        Image lastButtonImage = new Image(getClass().getResourceAsStream("Last.png"));
        Button lastButton = new Button();
        lastButton.setStyle("-fx-background-color: Transparent");
        lastButton.setGraphic(new ImageView(lastButtonImage));

        lastButton.setOnAction((ActionEvent e) -> {
            mediaPlayer.seek(mediaPlayer.getTotalDuration());
            mediaPlayer.stop();
        });

        lastButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            lastButton.setStyle("-fx-background-color: Black");
            lastButton.setStyle("-fx-body-color: Black");
        });
        lastButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            lastButton.setStyle("-fx-background-color: Black");
        });
        
        // Back Button
        Image backButtonImage = new Image(getClass().getResourceAsStream("Back.png"));
        Button backButton = new Button();
        backButton.setStyle("-fx-background-color: Transparent");
        backButton.setGraphic(new ImageView(backButtonImage));

        backButton.setOnAction((ActionEvent e) -> {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().divide(1.5));
        });
        backButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            backButton.setStyle("-fx-background-color: Black");
            backButton.setStyle("-fx-body-color: Black");
        });
        backButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            backButton.setStyle("-fx-background-color: Black");
        });
        
        // Retry Button
        Image retryButtonImage = new Image(getClass().getResourceAsStream("Retry.png"));
        Button retryButton = new Button();
        retryButton.setStyle("-fx-background-color: Transparent");
        retryButton.setGraphic(new ImageView(retryButtonImage));

        retryButton.setOnAction((ActionEvent e) -> {
            mediaPlayer.seek(mediaPlayer.getStartTime());
        });

        retryButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            retryButton.setStyle("-fx-background-color: Black");
            retryButton.setStyle("-fx-body-color: Black");
        });
        retryButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            retryButton.setStyle("-fx-background-color: Black");
        });
        
        // Full Screen Button
        Image fullScreenButtonImage = new Image(getClass().getResourceAsStream("FullScreen.png"));
        Button fullScreenButton = new Button();
        fullScreenButton.setStyle("-fx-background-color: Transparent");
        fullScreenButton.setGraphic(new ImageView(fullScreenButtonImage));

        fullScreenButton.setOnAction((ActionEvent e) -> {
            if (primaryStage.isFullScreen()){
                primaryStage.setFullScreen(false);
            }
            else{
                primaryStage.setFullScreen(true);
            }
        });

        fullScreenButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            fullScreenButton.setStyle("-fx-background-color: Black");
            fullScreenButton.setStyle("-fx-body-color: Black");
        });
        fullScreenButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            fullScreenButton.setStyle("-fx-background-color: Black");
        });
        
        // Time Label
        time = new Label();
        time.setTextFill(Color.YELLOW);
        time.setPrefWidth(80);
        
        mediaPlayer.currentTimeProperty().addListener((Observable ov) -> {
            updateValues();
        });
        
        mediaPlayer.setOnReady(() -> {
            duration = mediaPlayer.getMedia().getDuration();
            updateValues();
        });
        
        // Adding Buttons to toolbar
        toolBar.getChildren().addAll(filesButton, firstButton, backButton, playButton, pauseButton, forwardButton, lastButton, retryButton, time, fullScreenButton);
    
        primaryStage.setTitle("Media Player!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    protected void updateValues() {
        if (time != null) {
            runLater(() -> {
            Duration currentTime = mediaPlayer.getCurrentTime();
                time.setText(formatTime(currentTime, duration));
            });
        }
    }
    
    private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

            if (durationHours > 0) {
                return format("%d:%02d:%02d/%d:%02d:%02d",
                elapsedHours, elapsedMinutes, elapsedSeconds,
                durationHours, durationMinutes, durationSeconds);
            } else {
                return format("%02d:%02d/%02d:%02d",
                elapsedMinutes, elapsedSeconds, durationMinutes,
                durationSeconds);
            }
        }
        else {
            if (elapsedHours > 0) {
                return format("%d:%02d:%02d", elapsedHours,
                elapsedMinutes, elapsedSeconds);
            } else {
                return format("%02d:%02d", elapsedMinutes,
                elapsedSeconds);
            }
        }
    }
    
    // Add ToolBar Method
    private HBox addToolBar() {
        HBox toolBar = new HBox();
        toolBar.setPadding(new Insets(20));
        toolBar.setAlignment(Pos.CENTER);
        toolBar.alignmentProperty().isBound();
        toolBar.setSpacing(5);
        toolBar.setStyle("-fx-background-color: Black");

        return toolBar;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}