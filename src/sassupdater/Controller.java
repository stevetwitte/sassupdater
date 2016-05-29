package sassupdater;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.File;


public class Controller {
    private File sassFile;
    private File cssFile;

    @FXML
    public Text sassFileText;
    public Text cssFileText;

    @FXML protected void onClickSassFile(ActionEvent event) {
        FileChooser fileChooserSass = new FileChooser();
        fileChooserSass.setTitle("Choose Sass/Scss File");
        fileChooserSass.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("SASS/SCSS", "*.scss", "*.sass")
        );
        sassFile = fileChooserSass.showOpenDialog(Main.primaryStage);
        sassFileText.setText(sassFile.getAbsoluteFile().toString());
    }

    @FXML protected void onClickCssFile(ActionEvent event) {
        FileChooser fileChooserCss = new FileChooser();
        fileChooserCss.setTitle("Choose CSS File");
        fileChooserCss.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSS", "*.css")
        );
        cssFile = fileChooserCss.showOpenDialog(Main.primaryStage);
        cssFileText.setText(cssFile.getAbsoluteFile().toString());
    }
}
