package sample.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewNavigator {
    private static Stage mainStage;

    /**
     * Sets the main stage (window) when the application first loads (in the View class)
     * @param stage The main stage
     */
    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    /**
     * Navigates from one scene to another, e.g. from the MainScene to the AddScene or ModifyScene.
     * @param title The title to display on the stage (e.g. "Add Burrito")
     * @param scene The new scene to load
     */
    public static void loadScene(String title, Scene scene) {
        mainStage.setTitle(title);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
