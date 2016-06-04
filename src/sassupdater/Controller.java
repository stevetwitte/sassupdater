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
    private File sassFile = null;
    private File cssFile = null;
    private File sassGem = null;

    @FXML
    public Text sassFileText;
    public Text cssFileText;
    public Text sassFileLocationText;
    public VBox listOfAddedFiles;
    public TextArea statusOutput;

    /**
     * Event that opens a file chooser with constraints on type
     * After choose a file the text is updated in the view and the local variable sassFile is set
     *
     * @param event ActionEvent
     */
    @FXML protected void onClickSassFile(ActionEvent event) {
        FileChooser FCSass = new FileChooser();
        FCSass.setTitle("Choose SASS File");
        FCSass.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("SASS/SCSS", "*.scss", "*.sass")
        );
        sassFile = FCSass.showOpenDialog(Main.primaryStage);

        if (sassFile != null) {
            if (sassFile.getAbsoluteFile().toString().length() > 10) {
                sassFileText.setText("..." + sassFile.getAbsoluteFile().toString().substring(Math.max(0, 14)));
            } else {
                sassFileText.setText(sassFile.getAbsoluteFile().toString());
            }
        } else {
            this.updateStatus(new String[] {"No SASS/SCSS File Selected"});
        }
    }

    /**
     * Event that opens a file chooser with constraints on type
     * After choose a file the text is updated in the view and the local variable sassFile is set
     *
     * @param event ActionEvent
     */
    @FXML protected void onClickCssFile(ActionEvent event) {
        FileChooser FCCss = new FileChooser();
        FCCss.setTitle("Choose CSS File");
        FCCss.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("CSS", "*.css")
        );
        cssFile = FCCss.showOpenDialog(Main.primaryStage);
        if (cssFile != null) {
            if (cssFile.getAbsoluteFile().toString().length() > 10) {
                cssFileText.setText("..." + cssFile.getAbsoluteFile().toString().substring(Math.max(0, 14)));
            } else {
                cssFileText.setText(cssFile.getAbsoluteFile().toString());
            }
        } else {
            this.updateStatus(new String[] {"No CSS File Selected"});
        }
    }

    /**
     * Event that creates a new SassFile from local variables and updates the view
     * @param event ActionEvent
     */
    @FXML protected void onClickAddFileModel(ActionEvent event) {
        if (sassFile == null) {
            updateStatus(new String[] {"No SASS/SCSS file selected"});
            return;
        }
        if (cssFile == null) {
            updateStatus(new String[] {"No CSS file selected"});
            return;
        }
        updateStatus(new String[] {new SassFile(sassFile, cssFile).getDisplayName() + " Added"});
        sassFileText.setText("choose a sass/scss file");
        sassFile = null;
        cssFileText.setText("choose a css file");
        cssFile = null;
        updateView();
    }

    /**
     * Event that calls main method to switch view to the config screen
     *
     */
    @FXML protected void onClickConfig(ActionEvent event) {
        Main.switchScene("config");
    }

    /**
     * Event that calls main method to save config and switch view to main screen
     */
    @FXML protected void onClickConfigSave(ActionEvent event) {
        Main.sassFileLocation = sassGem;
        Main.switchScene("main");
    }

    /**
     * Event that calls main method to save config and switch view to main screen
     */
    @FXML protected void onClickConfigCancel(ActionEvent event) {
        if (Main.sassFileLocation != null) {
            if (Main.sassFileLocation.getAbsoluteFile().toString().length() > 30) {
                sassFileLocationText.setText("..." + sassGem.getAbsoluteFile().toString().substring(Math.max(0, 5)));
            } else {
                sassFileLocationText.setText(sassGem.getAbsoluteFile().toString());
            }
        } else {
            sassFileLocationText.setText("choose your sass gem");
        }
        Main.switchScene("main");
    }

    /**
     * Event that handle setting the sass executable location
     */
    @FXML protected void onClickSassLocation(ActionEvent event) {
        FileChooser FCSassLocation = new FileChooser();
        FCSassLocation.setTitle("Choose Sass Gem Location");
        FCSassLocation.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Sass Gem", "*.")
        );
        sassGem = FCSassLocation.showOpenDialog(Main.primaryStage);

        if (sassGem != null) {
            if (sassGem.getAbsoluteFile().toString().length() > 30) {
                sassFileLocationText.setText("..." + sassGem.getAbsoluteFile().toString().substring(Math.max(0, 5)));
            } else {
                sassFileLocationText.setText(sassGem.getAbsoluteFile().toString());
            }
        }
    }

    /**
     * Updates the view
     */
    private void updateView() {
        List<SassFile> fileList = FileList.getInstance().getFileList();

        listOfAddedFiles.getChildren().clear();
        for (SassFile SObject: fileList) {

            Button updateButton = new Button("U");
            updateButton.getStyleClass().addAll("button", "sm-button", "green");
            updateButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    try {
                        updateStatus(SObject.update());
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
                        updateStatus(SObject.force());
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
                    updateStatus(new String[] {SObject.getDisplayName() + " Removed"});
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
     *
     * @param Message String[]
     */
    public void updateStatus(String[] Message) {
        for (String msgLine : Message) {
            statusOutput.appendText(" \n");
            statusOutput.appendText(msgLine);
        }
    }
}
