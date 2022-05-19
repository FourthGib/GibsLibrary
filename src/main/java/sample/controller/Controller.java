package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Book;
import sample.model.Fiction;
import sample.model.Model;
import sample.model.NonFiction;

import java.io.File;

public class Controller {

    private static Controller theInstance;
    private ObservableList<Book> mAllBooksList;
    private ObservableList<Book> mFilteredBookList;

    private Controller() {};

    public static Controller getInstance(){
        if(theInstance == null){
            theInstance = new Controller();
            if(Model.binaryFileHasData())
                theInstance.mAllBooksList = Model.populateListFromBinaryFile();
            theInstance.mFilteredBookList = FXCollections.observableArrayList();
        }
        return theInstance;
    }

    public ObservableList<Book> getAllBooks() {return mAllBooksList;}

    public ObservableList<String> getDistinctAuthors(){
        ObservableList<String> authorList = FXCollections.observableArrayList();
        authorList.add(" ");
        for (Book b : mAllBooksList) {
            if (!authorList.contains(b.getAuthorLast()))
                authorList.add(b.getAuthorLast() + ", " + b.getAuthorFirst());
        }
        FXCollections.sort(authorList);
        return authorList;
    }

    public ObservableList<String> getDistinctGenres(){
        ObservableList<String> genreList = FXCollections.observableArrayList();
        genreList.add(" ");
        for(Book b : mAllBooksList)
            if(b instanceof Fiction){
                Fiction fB = (Fiction) b;
                if(!genreList.contains(fB.getGenre()))
                    genreList.add(fB.getGenre());
                FXCollections.sort(genreList);
            }
        return genreList;
    }

    public ObservableList<String> getDistinctSubjects(){
        ObservableList<String> subjectList = FXCollections.observableArrayList();
        subjectList.add(" ");
        for(Book b : mAllBooksList)
            if(b instanceof NonFiction){
                NonFiction nB = (NonFiction) b;
                if(!subjectList.contains(nB.getSubject()))
                    subjectList.add(nB.getSubject());
                FXCollections.sort(subjectList);
            }
        return subjectList;
    }

    public ObservableList<Book> filter(String authorFull, String genre, String subject){
        mFilteredBookList.clear();
        boolean meetsCriteria;
        Fiction fB;
        NonFiction nB;

        for(Book b : mAllBooksList){
            meetsCriteria = true;
            if(authorFull != null && !authorFull.equalsIgnoreCase(" ") && !authorFull.equalsIgnoreCase(b.getAuthorLast()))
                meetsCriteria = false;
            if(b instanceof Fiction) {
                fB = (Fiction) b;
                if(genre != null && !genre.equalsIgnoreCase(" ") && !genre.equalsIgnoreCase(fB.getGenre()));
                meetsCriteria = false;
            }
            if(b instanceof NonFiction){
                nB = (NonFiction) b;
                if(subject != null && !subject.equalsIgnoreCase(" ") && !subject.equalsIgnoreCase(nB.getSubject()));
                meetsCriteria = false;
            }
            if (meetsCriteria)
                mFilteredBookList.add(b);
        }
        return mFilteredBookList;
    }

    public void saveData() {
        Model.writeDataToBinaryFile(mAllBooksList);
    }


}
