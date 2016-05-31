package sassupdater;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Main class of application and sass commands
 * TODO: Refactor updateSassFile and forceSassFile into one method (possibly move them)
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

    /**
     * Runs the sass --update command with the provided SassFile
     * TODO: set the sass location, and options from config
     * @param sassFile SassFile
     * @throws IOException
     */
    public static void updateSassFile(SassFile sassFile) throws IOException {
        String[] command = { "/home/stephen/.rbenv/shims/sass", "--no-cache", "--update", sassFile.sassfilename.getAbsoluteFile().toString() + ":" + sassFile.cssfilename.getAbsoluteFile().toString() };
        Process process = new ProcessBuilder(command).start();
        InputStream stream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        StringBuilder builder = new StringBuilder();
        String line;
        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        String result = builder.toString();

        /**
         * TODO: Update view with result
         */
        System.out.println(Arrays.toString(command));
        System.out.println(result);
    }

    /**
     * Runs the sass --force command with the provided SassFile
     * TODO: set the sass location, and options from config
     * @param sassFile SassFile
     * @throws IOException
     */
    public static void forceSassFile(SassFile sassFile) throws IOException {
        String[] command = { "/home/stephen/.rbenv/shims/sass", "--no-cache", "--update", "--force", sassFile.sassfilename.getAbsoluteFile().toString() + ":" + sassFile.cssfilename.getAbsoluteFile().toString() };
        Process process = new ProcessBuilder(command).start();
        InputStream stream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        StringBuilder builder = new StringBuilder();
        String line;
        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        String result = builder.toString();

        /**
         * TODO: Update view with result
         */
        System.out.println(Arrays.toString(command));
        System.out.println(result);

//        Controller.updateStatus(Arrays.toString(command));
//        Controller.updateStatus(result);
    }
}
