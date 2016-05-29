package sassupdater;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    private Button sassButton;

    @FXML protected void onClickSassFile(ActionEvent event) {
        SassFile TestSassObject = new SassFile("test.scss", "test.css");
        if (TestSassObject.update()) {
            System.out.println(TestSassObject.sassfilename + " " + TestSassObject.cssfilename);
            sassButton.setText("sass file name location");
        } else {
            System.out.println("Failed");
        }
    }
}
