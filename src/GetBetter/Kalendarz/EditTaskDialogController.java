package GetBetter.Kalendarz;

import GetBetter.DoZrobienia.CustomTask;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class EditTaskDialogController {

    private static CustomTask selectedTask;

    @FXML
    Label taskNameLabel;
    @FXML
    TextField taskDetailsText;
    @FXML
    Label taskDeadlineLabel;
    @FXML
    DatePicker ETDatePicker;


    public static void setSelectedTask(CustomTask sTask) {
        System.out.println("ETC selectedTask = " + sTask.getTaskName());
        selectedTask = sTask;
    }

    public void initialize() {
        taskNameLabel.setText("By changing content of the text and date below you will implement changes in task " + selectedTask.getTaskName());
        taskDetailsText.appendText(selectedTask.getDetails());
        taskDeadlineLabel.setText("Current deadline for " + selectedTask.getTaskName() + " is " + selectedTask.getDeadline().toString()+
                ". If you would like to change it, choose a date from DatePicker below.");
        ETDatePicker.setValue(selectedTask.getDeadline());

     }

     public void handleApplyChangesButton(){
        String previousDetails = selectedTask.getDetails();
         LocalDate previousDeadline = selectedTask.getDeadline();

         if((!previousDetails.equals(taskDetailsText.getText().trim()))||
                 (previousDeadline != ETDatePicker.getValue())){
             selectedTask.editTaskDescription(taskDetailsText.getText().trim());
             selectedTask.editTaskDeadline(ETDatePicker.getValue());
         }
     }
}
