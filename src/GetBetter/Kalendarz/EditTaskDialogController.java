package GetBetter.Kalendarz;

import GetBetter.DoZrobienia.MyTask;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class EditTaskDialogController {

    private static MyTask selectedTask;

    @FXML
    Label ETTaskNameLabel;
    @FXML
    TextField ETTaskNameField;
    @FXML
    Label TaskDetailsLabel;
    @FXML
    TextField taskDetailsText;
    @FXML
    Label taskDeadlineLabel;
    @FXML
    DatePicker ETDatePicker;


    public static void setSelectedTask(MyTask sTask) {
        System.out.println("ETC selectedTask = " + sTask.getTaskName());
        selectedTask = sTask;
    }

    public void initialize() {
        TaskDetailsLabel.setText("By changing content of the text and date below you will implement changes in task " + selectedTask.getTaskName());
        taskDetailsText.appendText(selectedTask.getDetails());
        taskDeadlineLabel.setText("Current deadline for " + selectedTask.getTaskName() + " is " + selectedTask.getDeadline().toString()+
                ". If you would like to change it, choose a date from DatePicker below.");
        ETDatePicker.setValue(selectedTask.getDeadline());
        ETTaskNameLabel.setText("Name of the task: ");
        ETTaskNameField.setText(selectedTask.getTaskName());

     }

     public void handleApplyChangesButton(){
        String previousDetails = selectedTask.getDetails();
         LocalDate previousDeadline = selectedTask.getDeadline();

         if(!selectedTask.getTaskName().equals(ETTaskNameField.getText().trim())) {
             selectedTask.editTaskName(ETTaskNameField.getText().trim());
         }
         else if((!previousDetails.equals(taskDetailsText.getText().trim()))){
             selectedTask.editTaskDescription(taskDetailsText.getText().trim());
         }
         else if (previousDeadline != ETDatePicker.getValue()) {
             selectedTask.editTaskDeadline(ETDatePicker.getValue());
         }
     }
}
