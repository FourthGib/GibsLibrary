package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Model {

    public static final String BINARY_FILE = "books.dat";

    public static boolean binaryFileHasData(){
        File binaryFile = new File(BINARY_FILE);
        return binaryFile.exists() && binaryFile.length() > 0;
    }

    public static ObservableList<Book> populateListFromBinaryFile(){
        ObservableList<Book> allBooks = FXCollections.observableArrayList();

        File binaryFile = new File(BINARY_FILE);

        if(binaryFile.exists()){
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                Book[] temp = (Book[]) fileReader.readObject();
                allBooks.addAll(temp);
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error Reading: " + e.getMessage());
            }
        }
        return allBooks.sorted();
    }

    public static boolean writeDataToBinaryFile(ObservableList<Book> allBooksList){
        File binaryFile = new File(BINARY_FILE);

        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            Book[] temp = new Book[allBooksList.size()];

            for (int i = 0; i < temp.length; i++) {
                temp[i] = allBooksList.get(i);
            }
            fileWriter.writeObject(temp);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }
        return false;
    }
}
