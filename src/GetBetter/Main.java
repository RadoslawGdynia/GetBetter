package GetBetter;

import GetBetter.DoZrobienia.TaskDatasource;
import GetBetter.Kalendarz.MyCalendar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void init() throws Exception {
        MyCalendar.loadCalendar();
        TaskDatasource td = new TaskDatasource();
        if(!td.open()){
            System.out.println("Could not open datasource");
        }
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
    }
}
