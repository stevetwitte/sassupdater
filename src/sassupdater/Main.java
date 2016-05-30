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

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainview.fxml"));
        primaryStage.setTitle("Sass Updater");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void compileSassFile(SassFile sassFile) throws IOException {
        String[] command = { "/home/stephen/.rbenv/shims/sass", "--no-cache", "--update", sassFile.sassfilename.getAbsoluteFile().toString() + ":" + sassFile.cssfilename.getAbsoluteFile().toString() };
        Process process = new ProcessBuilder(command).start();
        InputStream stream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

        System.out.println(Arrays.toString(command));
        while (bufferedReader.readLine() != null) {
            System.out.println(bufferedReader.readLine());
        }
    }
}
