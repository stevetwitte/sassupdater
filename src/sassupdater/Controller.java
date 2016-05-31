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

/**
 * JavaFX controller for the main view
 */
public class Controller {
    private File sassFile;
    private File cssFile;

    @FXML
    public Text sassFileText;
    public Text cssFileText;
    public VBox listOfAddedFiles;
    public static TextArea statusOutput;

    /**
     * Event that opens a file chooser with constraints on type
     * After choose a file the text is updated in the view and the local variable sassFile is set
     * TODO: Handle not selecting a file, currently throws exception and crashes
     * @param event ActionEvent
     */
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

    /**
     * Event that opens a file chooser with constraints on type
     * After choose a file the text is updated in the view and the local variable sassFile is set
     * TODO: Handle not selecting a file, currently throws exception and crashes
     * @param event ActionEvent
     */
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

    /**
     * Event that creates a new SassFile from local variables and updates the view
     * @param event ActionEvent
     */
    @FXML protected void onClickAddFileModel(ActionEvent event) {
        new SassFile(sassFile, cssFile);
        updateView();
    }

    /**
     * Updates the view
     * More specifically updates the list of files in the view
     * TODO: Should also clear local variables after adding
     */
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

    /**
     * Updates the status box at the bottom of the view
     * TODO: Code This
     * @param msgLine String
     */
    public static void updateStatus(String msgLine) {
        statusOutput.appendText(msgLine);
    }
}
