package sassupdater;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class Controller {
    private File sassFile;
    private File cssFile;

    @FXML
    public Text sassFileText;
    public Text cssFileText;
    public VBox listOfAddedFiles;

    @FXML protected void onClickSassFile(ActionEvent event) {
        FileChooser FCSass = new FileChooser();
        FCSass.setTitle("Choose SASS File");
        FCSass.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("SASS/SCSS", "*.scss", "*.sass")
        );
        sassFile = FCSass.showOpenDialog(Main.primaryStage);
        sassFileText.setText(sassFile.getAbsoluteFile().toString());
    }

    @FXML protected void onClickCssFile(ActionEvent event) {
        FileChooser FCCss = new FileChooser();
        FCCss.setTitle("Choose CSS File");
        FCCss.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("CSS", "*.css")
        );
        cssFile = FCCss.showOpenDialog(Main.primaryStage);
        cssFileText.setText(cssFile.getAbsoluteFile().toString());
    }

    @FXML protected void onClickAddFileModel(ActionEvent event) {
        FileList.addFile(new SassFile(sassFile, cssFile));
        System.out.println(FileList.getFile(0));
        updateView();
    }

    private void updateView() {
        List<SassFile> fileList = FileList.getFileList();

        listOfAddedFiles.getChildren().clear();
        System.out.println(fileList);
        for (SassFile SObject: fileList) {
            Button button = new Button("Update");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    try {
                        SObject.update();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            listOfAddedFiles.getChildren().add(new HBox(new Text(SObject.sassfilename.getName()), button));
        }
    }
}
