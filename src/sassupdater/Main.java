package sassupdater;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

/**
 * Main class of application and sass commands
 * TODO: Save config to a file
 * TODO: Show config screen on first load
 */

public class Main extends Application {

    public static Stage primaryStage;
    private static Scene mainScene;
    private static Scene configScene;
    private static Stage currentStage;
    public static File sassFileLocation = null;

    /**
     * Loads the FXML sets the title scene and shows the stage
     * @param primaryStage Stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        currentStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("mainview.fxml"));
        Parent config = FXMLLoader.load(getClass().getResource("configview.fxml"));
        mainScene = new Scene(root);
        configScene = new Scene(config);
        primaryStage.setTitle("Sass Updater");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Launches the JavaFX Application
     * @param args String[]
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void switchScene(String scene) {
        if (scene.equals("config")) {
            currentStage.setScene(configScene);
        } else {
            currentStage.setScene(mainScene);
        }
    }

    public static String getSassLocation() {
        if (sassFileLocation == null) {
            return null;
        } else {
            return sassFileLocation.getAbsolutePath();
        }
    }

}
