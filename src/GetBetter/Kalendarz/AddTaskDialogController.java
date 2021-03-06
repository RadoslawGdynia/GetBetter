package GetBetter.Kalendarz;

import GetBetter.DoZrobienia.MyTask;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class AddTaskDialogController {
    @FXML
    TextField taskName;
    @FXML
    TextArea details;
    @FXML
    DatePicker deadlineDate;

    public MyTask createTask(){
        String name = taskName.getText().trim();
        String description = details.getText().trim();
        LocalDate deadline = deadlineDate.getValue();
        return new MyTask(name,description,deadline);
    }
}
