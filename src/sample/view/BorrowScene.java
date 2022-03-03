package sample.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.controller.Controller;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.model.Book;
import javafx.scene.control.Button;

import java.awt.*;

public class BorrowScene extends Scene {

    private Scene mPrevScene;
    private Book mBorrowedBook;

    private Controller controller = Controller.getInstance();
    private ObservableList<Book> allBooksList;
    private Book selectedBook;

    private TextField nameTF = new TextField();
    private Label nameLabel = new Label("Borrower's name is required.");

    private TextField dateTF = new TextField();
    private Label dateLabel = new Label("Date borrowed is required.");

    private Button borrowButton = new Button("Borrow");
    private Button cancelButton = new Button("Cancel");

    public BorrowScene(Scene prevScene, Book borrowedBook){
        super(new GridPane(), 450, 250);
        mPrevScene = prevScene;
        mBorrowedBook = borrowedBook;

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(new Label("Borrower:"), 0, 0);
        pane.add(nameTF, 1, 0);
        pane.add(nameLabel, 2, 0);
        nameLabel.setTextFill(Color.RED);
        nameLabel.setVisible(false);

        pane.add(new Label("Date:"), 0, 1);
        pane.add(dateTF, 1, 1);
        pane.add(dateLabel, 2, 1);
        dateLabel.setTextFill(Color.RED);
        dateLabel.setVisible(false);

        pane.add(new Label("Book Selected:"), 0, 2);
        pane.add(new Label(mBorrowedBook.getTitle()), 1, 2);
        pane.add(new Label("Author:"), 0, 3);
        pane.add(new Label(mBorrowedBook.getAuthorLast() + ", " + mBorrowedBook.getAuthorFirst()), 1, 3);


        pane.add(cancelButton, 0, 4);
        pane.add(borrowButton, 1, 4);

        this.setRoot(pane);

        cancelButton.setOnAction(actionEvent -> goBackToPrevScene());
        borrowButton.setOnAction(actionEvent -> borrowBook());

    }

    private void borrowBook() {
        String borrower, date;

        borrower = nameTF.getText();
        nameLabel.setVisible(borrower.isEmpty());

        date = dateTF.getText();
        dateLabel.setVisible(date.isEmpty());

        if(nameLabel.isVisible() || dateLabel.isVisible())
            return;

        mBorrowedBook.setBorrowed(true);
        mBorrowedBook.setBorrower(borrower);
        mBorrowedBook.setDate(date);

        goBackToPrevScene();
    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Gib's Library", mPrevScene);
    }
}
