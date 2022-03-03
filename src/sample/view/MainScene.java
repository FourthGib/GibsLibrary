package sample.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import sample.controller.Controller;
import sample.model.Book;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class MainScene extends Scene {

    public static final int WIDTH = 850;
    public static final int HEIGHT = 850;

    private ImageView libraryIV = new ImageView();
    private ComboBox<String> authorsCB;
    private ComboBox<String> genreCB;
    private ComboBox<String> subjectCB;
    private ListView<Book> booksLV = new ListView<>();
    private Button borrowBookButton = new Button("|Borrow A Book|");
    private Button addBookButton = new Button("+ Add Book");
    private Button removeBookButton = new Button("- Remove Book");
    private Button returnBookButton = new Button("(Return A Book)");

    private Controller controller = Controller.getInstance();
    private ObservableList<Book> booksList;
    private Book selectedBook;

    public MainScene(){
        super(new GridPane(), WIDTH, HEIGHT);

        booksList = controller.getAllBooks();
        booksLV.setItems(booksList);
        booksLV.setPrefWidth(WIDTH);

        booksLV.getSelectionModel().selectedItemProperty().addListener((observableValue, book, newValue) -> bookSelected(newValue));

        authorsCB = new ComboBox<>(controller.getDistinctAuthors());
        authorsCB.setOnAction(actionEvent -> filter());

        genreCB = new ComboBox<>(controller.getDistinctGenres());
        genreCB.setOnAction(actionEvent -> filter());

        subjectCB = new ComboBox<>((controller.getDistinctSubjects()));
        subjectCB.setOnAction(actionEvent -> filter());

        borrowBookButton.setDisable(true);
        borrowBookButton.setOnAction(actionEvent -> borrowBook());

        returnBookButton.setDisable(true);
        returnBookButton.setOnAction(actionEvent -> returnBook());

        addBookButton.setOnAction(actionEvent -> addBook());

        removeBookButton.setDisable(true);
        removeBookButton.setOnAction(actionEvent -> removeBook());

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));
        pane.add(libraryIV, 0, 0, 3, 1);
        libraryIV.setImage(new Image(getClass().getResourceAsStream("images" + File.separator + "books.png")));
        libraryIV.setFitWidth(WIDTH);

        pane.add(new Label("Search by:"), 0, 1);
        pane.add(new Label("Author"), 0, 2);
        pane.add(authorsCB, 1, 2);
        pane.add(new Label("Genre"), 0, 3);
        pane.add(genreCB, 1, 3);
        pane.add(new Label("Subject"), 0, 4);
        pane.add(subjectCB, 1, 4);

        pane.add(addBookButton, 0, 5, 1, 1);
        pane.add(removeBookButton, 1, 5, 3, 1);
        pane.add(booksLV, 0 , 6, 2, 1);
        booksLV.setMinWidth(WIDTH - 5);
        pane.add(borrowBookButton, 0, 7, 1, 1);
        pane.add(returnBookButton, 1, 7, 3, 1);

        this.setRoot(pane);

    }

    private void returnBook() {
        if(selectedBook == null)
            return;
        else{
            selectedBook.setBorrowed(false);
        }
        updateDisplay();

    }

    private void removeBook() {
        if(selectedBook == null)
            return;
        else
            controller.getAllBooks().remove(selectedBook);
        updateDisplay();
    }

    private void bookSelected(Book newValue) {
        selectedBook = newValue;
        borrowBookButton.setDisable(selectedBook.isBorrowed());
        removeBookButton.setDisable(selectedBook == null);
        returnBookButton.setDisable(!selectedBook.isBorrowed());
    }

    private void addBook() {
        ViewNavigator.loadScene("Add Book", new AddScene(this));
        updateDisplay();
    }

    private void borrowBook() {
        if(selectedBook == null)
            return;
        else
            ViewNavigator.loadScene("Check Out", new BorrowScene(this, selectedBook));
        updateDisplay();
    }

    private void updateDisplay() {
        booksLV.refresh();
        authorsCB.setItems(controller.getDistinctAuthors());
        genreCB.setItems(controller.getDistinctGenres());
        subjectCB.setItems(controller.getDistinctSubjects());
    }

    private void filter() {
        String author = authorsCB.getSelectionModel().getSelectedItem();
        String genre = genreCB.getSelectionModel().getSelectedItem();
        String subject = subjectCB.getSelectionModel().getSelectedItem();
        booksList = controller.filter(author, genre, subject);
        booksLV.setItems(booksList);

    }

}
