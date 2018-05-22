import Controller.BoardController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        BoardController boardController = new BoardController();

        Scene scene = new Scene(boardController.getBoardView(), 500, 700);

        primaryStage.setTitle("Amazing Board Games !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
