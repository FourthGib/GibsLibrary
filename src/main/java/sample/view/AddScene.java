package sample.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.controller.Controller;
import sample.model.Book;
import javafx.scene.control.*;
import sample.model.Fiction;
import sample.model.NonFiction;

import java.awt.*;

public class AddScene extends Scene {

    private Scene mPrevScene;

    private Controller controller = Controller.getInstance();
    private ObservableList<Book> allBooksList;

    private TextField authorLastTF = new TextField();
    private Label authorLastLabel = new Label("Last name required.");

    private TextField authorFirstTF = new TextField();
    private Label authorFirstLabel = new Label("First name required.");

    private TextField titleTF = new TextField();
    private Label titleLabel = new Label("Title required.");

    private TextField genreSubjectTF = new TextField();
    private Label genreSubjectLabel = new Label("Genre:");
    private Label genreSubjectErrLabel = new Label("Genre required.");

    private Button addButton = new Button("Add Book");
    private Button cancelButton = new Button("Cancel");
    private ComboBox<String> bookTypeCB = new ComboBox<>();


    //TODO: Find a way to set book as fiction or non-fiction then add genre or subject (influencer - sponsorCompany?)

    public AddScene(Scene prevScene){
        super(new GridPane(), 450, 250);
        mPrevScene = prevScene;

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(bookTypeCB, 0, 0);
        bookTypeCB.getItems().addAll("Fiction", "NonFiction");
        bookTypeCB.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newValue) -> selectedType(newValue));
        bookTypeCB.setValue("Fiction");

        pane.add(new Label("Title:"), 0, 1);
        pane.add(titleTF, 1, 1);
        pane.add(titleLabel, 2, 1);
        titleLabel.setTextFill(Color.RED);
        titleLabel.setVisible(false);

        pane.add(new Label("Author(LastName):"), 0, 2);
        pane.add(authorLastTF, 1, 2);
        pane.add(authorLastLabel, 2, 2);
        authorLastLabel.setTextFill(Color.RED);
        authorLastLabel.setVisible(false);

        pane.add(new Label("Author(FirstName):"), 0, 3);
        pane.add(authorFirstTF, 1, 3);
        pane.add(authorFirstLabel, 2, 3);
        authorFirstLabel.setTextFill(Color.RED);
        authorFirstLabel.setVisible(false);

        pane.add(genreSubjectLabel, 0, 4);
        pane.add(genreSubjectTF, 1, 4);
        pane.add(genreSubjectErrLabel, 2, 4);
        genreSubjectErrLabel.setTextFill(Color.RED);
        genreSubjectErrLabel.setVisible(false);

        pane.add(cancelButton, 0, 5);
        pane.add(addButton, 1, 5);

        addButton.setOnAction(actionEvent -> addBook());
        cancelButton.setOnAction(actionEvent -> goBackToPrevScene());

        this.setRoot(pane);
    }

    private void selectedType(String newValue) {
        if(newValue.equals("Fiction")){
            genreSubjectLabel.setText("Genre:");
            genreSubjectErrLabel.setText("Genre is required.");
        }
        if(newValue.equals("NonFiction")) {
            genreSubjectLabel.setText("Subject:");
            genreSubjectErrLabel.setText("Subject is required.");
        }


    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Gib's Library", mPrevScene);
    }



    private void addBook() {
        String title, authorLast, authorFirst, genreSubject;

        title = titleTF.getText();
        titleLabel.setVisible(title.isEmpty());
        authorLast = authorLastTF.getText();
        authorLastLabel.setVisible(authorLast.isEmpty());
        authorFirst = authorFirstTF.getText();
        authorFirstLabel.setVisible(authorFirst.isEmpty());
        genreSubject = genreSubjectTF.getText();
        genreSubjectLabel.setVisible(genreSubject.isEmpty());

        if(titleLabel.isVisible() || authorLastLabel.isVisible() || authorFirstLabel.isVisible())
            return;
        else if (bookTypeCB.getValue().equals("Fiction")){
            controller.getAllBooks().add(new Fiction(title, authorLast, authorFirst, genreSubject));
        }
        else if (bookTypeCB.getValue().equals("NonFiction"))
            controller.getAllBooks().add(new NonFiction(title, authorLast, authorFirst, genreSubject));
        goBackToPrevScene();
    }

}
