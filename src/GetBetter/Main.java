package GetBetter;

import GetBetter.DoZrobienia.CustomTaskDatasource;
import GetBetter.Kalendarz.MyCalendar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void init() throws Exception {
        if(!CustomTaskDatasource.getInstance().open()) {
            System.out.println("FATAL ERROR during load of database"); //zmienić na okno dialogowe
            Platform.exit();
        }
        MyCalendar.loadCalendar();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GetBetterMain.fxml"));
        primaryStage.setTitle("GetBetter!");
        primaryStage.setScene(new Scene(root, 400, 600));


        primaryStage.show();
    }


    public static void main(String[] args) {
       launch(args);
       MyCalendar.saveCalendar();

    }


    @Override
    public void stop() throws Exception {
        CustomTaskDatasource.getInstance().close();
    }
}
