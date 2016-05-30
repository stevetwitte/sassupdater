package sassupdater;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
    public static TextArea statusOutput;

    @FXML protected void onClickSassFile(ActionEvent event) {
        FileChooser FCSass = new FileChooser();
        FCSass.setTitle("Choose SASS File");
        FCSass.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("SASS/SCSS", "*.scss", "*.sass")
        );
        sassFile = FCSass.showOpenDialog(Main.primaryStage);
        if (sassFile.getAbsoluteFile().toString().length() > 10) {
            sassFileText.setText("..." + sassFile.getAbsoluteFile().toString().substring(Math.max(0, 14)));
        } else {
            sassFileText.setText(sassFile.getAbsoluteFile().toString());
        }
    }

    @FXML protected void onClickCssFile(ActionEvent event) {
        FileChooser FCCss = new FileChooser();
        FCCss.setTitle("Choose CSS File");
        FCCss.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("CSS", "*.css")
        );
        cssFile = FCCss.showOpenDialog(Main.primaryStage);
        if (cssFile.getAbsoluteFile().toString().length() > 10) {
            cssFileText.setText("..." + cssFile.getAbsoluteFile().toString().substring(Math.max(0, 14)));
        } else {
            cssFileText.setText(cssFile.getAbsoluteFile().toString());
        }
    }

    @FXML protected void onClickAddFileModel(ActionEvent event) {
        new SassFile(sassFile, cssFile);
        updateView();
    }

    private void updateView() {
        List<SassFile> fileList = FileList.getFileList();

        listOfAddedFiles.getChildren().clear();
        for (SassFile SObject: fileList) {

            Button updateButton = new Button("U");
            updateButton.getStyleClass().addAll("button", "sm-button", "green");
            updateButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    try {
                        SObject.update();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            Button forceButton = new Button("F");
            forceButton.getStyleClass().addAll("button", "sm-button", "green");
            forceButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    try {
                        SObject.force();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            Button removeButton = new Button("X");
            removeButton.getStyleClass().addAll("button", "sm-button", "red");
            removeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    SObject.remove();
                    updateView();
                }
            });

            Text fileName = new Text(SObject.getDisplayName());
            fileName.getStyleClass().add("file-label");

            HBox itemBox = new HBox(fileName, updateButton, forceButton, removeButton);
            itemBox.getStyleClass().addAll("item-box", "light-grey");

            listOfAddedFiles.getChildren().add(itemBox);
        }
    }

    public static void updateStatus(String msgLine) {
        statusOutput.appendText(msgLine);
    }
}
