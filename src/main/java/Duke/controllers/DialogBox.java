package duke.controllers;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The `DialogBox` class represents a graphical control for displaying a dialog box in a JavaFX application.
 * It consists of an ImageView to represent the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new `DialogBox` with the specified text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image representing the speaker's face.
     */
    private DialogBox(String text, Image img) {
        try {
            assert MainWindow.class.getResource("/view/DialogBox.fxml") != null;
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box, repositioning the ImageView on the left and the text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates and returns a `DialogBox` representing a user's dialog.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image representing the user's face.
     * @return A `DialogBox` representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a `DialogBox` representing Respironix's (bot) dialog. The dialog is flipped to have
     * the ImageView on the left and the text on the right.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image representing Duke's (bot) face.
     * @return A flipped `DialogBox` representing Duke's dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}


