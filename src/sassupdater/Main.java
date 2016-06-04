package sassupdater;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class of application and sass commands
 *
 */

public class Main extends Application {

    public static Stage primaryStage;

    /**
     * Loads the FXML sets the title scene and shows the stage
     * @param primaryStage Stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainview.fxml"));
        primaryStage.setTitle("Sass Updater");
        primaryStage.setScene(new Scene(root));
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


}
