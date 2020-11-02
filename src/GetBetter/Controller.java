package GetBetter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void handleCalendarButtonClick(){
        try {
            Stage calendarStage = new Stage();
            FXMLLoader calendarLoader = new FXMLLoader();
            Pane root = calendarLoader.load(getClass().getResource("Kalendarz/MyCalendar.fxml").openStream());
            calendarStage.setScene(new Scene(root,1700, 800));
            calendarStage.setTitle("Kalendarz");
            calendarStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void handleDiaryButtonClick() {
        try {
            Stage diaryStage = new Stage();
            FXMLLoader diaryLoader = new FXMLLoader();
            Pane root = diaryLoader.load(getClass().getResource("Dziennik/MyDiary.fxml").openStream());
            diaryStage.setScene(new Scene(root,800, 500));
            diaryStage.setTitle("Dziennik");
            diaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleExitButtonClick () {
        Platform.exit();
    }


    public void handleTaskStatisticsButtonClick(ActionEvent event) {
    }
}
