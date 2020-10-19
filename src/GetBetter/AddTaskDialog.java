package GetBetter;

import GetBetter.DoZrobienia.Task;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class AddTaskDialog {
    @FXML
    TextField taskName;
    @FXML
    TextArea details;
    @FXML
    DatePicker deadlineDate;

    public Task createTask(){
        String name = taskName.getText().trim();
        String description = details.getText().trim();
        LocalDate deadline = deadlineDate.getValue();
        return new Task(name,description,deadline);
    }
}
