package engpol;

import engpol.constants.AppConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.application");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(AppConstants.MAIN_FXML));
        loader.setResources(bundle);
        BorderPane pane = loader.load();
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(AppConstants.CSS_FILE);
        primaryStage.setScene(scene);
        primaryStage.setTitle(bundle.getString("app.title"));
        primaryStage.show();
    }
}
