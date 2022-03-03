package sample.view;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.controller.Controller;

import java.io.File;

public class View extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Changing the icon (top left of stage) to custom burrito icon
        // Burrito icon credit goes to:
        // <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a>
        // from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images" + File.separator + "32x32.png")));
        ViewNavigator.setStage(primaryStage);
        ViewNavigator.loadScene("Gib's Library", new MainScene());
    }
    @Override
    public void stop() throws Exception {
        Controller.getInstance().saveData();
    }

    public static void main(String[] args) {
        Application.launch(args);

    }
}
