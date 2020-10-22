package GetBetter;

import GetBetter.DoZrobienia.Task;
import GetBetter.Kalendarz.MyCalendar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class MyCalendarController {

    List<Button> dayButtonList = new ArrayList<>();
    int currentMonthNum;
    int currentYearNum;
    int currentDayNum;

    @FXML
    AnchorPane mainPane;


    @FXML
    Label monthName;
    @FXML
    Label yearNumber;
    @FXML
    Accordion taskAccordion;
    @FXML
    Label showDay;
    @FXML
    ListView<Task> showTasksList;

    @FXML
    Button dayButton1;
    @FXML
    Button dayButton2;
    @FXML
    Button dayButton3;
    @FXML
    Button dayButton4;
    @FXML
    Button dayButton5;
    @FXML
    Button dayButton6;
    @FXML
    Button dayButton7;
    @FXML
    Button dayButton8;
    @FXML
    Button dayButton9;
    @FXML
    Button dayButton10;
    @FXML
    Button dayButton11;
    @FXML
    Button dayButton12;
    @FXML
    Button dayButton13;
    @FXML
    Button dayButton14;
    @FXML
    Button dayButton15;
    @FXML
    Button dayButton16;
    @FXML
    Button dayButton17;
    @FXML
    Button dayButton18;
    @FXML
    Button dayButton19;
    @FXML
    Button dayButton20;
    @FXML
    Button dayButton21;
    @FXML
    Button dayButton22;
    @FXML
    Button dayButton23;
    @FXML
    Button dayButton24;
    @FXML
    Button dayButton25;
    @FXML
    Button dayButton26;
    @FXML
    Button dayButton27;
    @FXML
    Button dayButton28;
    @FXML
    Button dayButton29;
    @FXML
    Button dayButton30;
    @FXML
    Button dayButton31;
    @FXML
    Button dayButton32;
    @FXML
    Button dayButton33;
    @FXML
    Button dayButton34;
    @FXML
    Button dayButton35;
    @FXML
    Button dayButton36;
    @FXML
    Button dayButton37;
    @FXML
    Button dayButton38;
    @FXML
    Button dayButton39;
    @FXML
    Button dayButton40;
    @FXML
    Button dayButton41;
    @FXML
    Button dayButton42;

    @FXML
    Button addTaskButton;
    @FXML
    Button editTaskButton;
    @FXML
    Button cancelTaskButton;


    

    public void initialize() {
        taskAccordion.setDisable(true);

        dayButtonList.add(dayButton1);
        dayButtonList.add(dayButton2);
        dayButtonList.add(dayButton3);
        dayButtonList.add(dayButton4);
        dayButtonList.add(dayButton5);
        dayButtonList.add(dayButton6);
        dayButtonList.add(dayButton7);
        dayButtonList.add(dayButton8);
        dayButtonList.add(dayButton9);
        dayButtonList.add(dayButton10);
        dayButtonList.add(dayButton11);
        dayButtonList.add(dayButton12);
        dayButtonList.add(dayButton13);
        dayButtonList.add(dayButton14);
        dayButtonList.add(dayButton15);
        dayButtonList.add(dayButton16);
        dayButtonList.add(dayButton17);
        dayButtonList.add(dayButton18);
        dayButtonList.add(dayButton19);
        dayButtonList.add(dayButton20);
        dayButtonList.add(dayButton21);
        dayButtonList.add(dayButton22);
        dayButtonList.add(dayButton23);
        dayButtonList.add(dayButton24);
        dayButtonList.add(dayButton25);
        dayButtonList.add(dayButton26);
        dayButtonList.add(dayButton27);
        dayButtonList.add(dayButton28);
        dayButtonList.add(dayButton29);
        dayButtonList.add(dayButton30);
        dayButtonList.add(dayButton31);
        dayButtonList.add(dayButton32);
        dayButtonList.add(dayButton33);
        dayButtonList.add(dayButton34);
        dayButtonList.add(dayButton35);
        dayButtonList.add(dayButton36);
        dayButtonList.add(dayButton37);
        dayButtonList.add(dayButton38);
        dayButtonList.add(dayButton39);
        dayButtonList.add(dayButton40);
        dayButtonList.add(dayButton41);
        dayButtonList.add(dayButton42);


        currentMonthNum = MyCalendar.getSelectedDay().getDate().getMonthValue();
        currentYearNum = MyCalendar.getSelectedDay().getDate().getYear();
        currentDayNum = MyCalendar.getSelectedDay().getDate().getDayOfMonth();

        monthName.setText(LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        yearNumber.setText(String.valueOf(currentYearNum));

        int firstDayOfMonth = LocalDate.of(currentYearNum,currentMonthNum,currentDayNum-(currentDayNum-1)).getDayOfWeek().getValue();
        int dayNumeration = 1;
        int numberOfDaysInCurrentMonth = LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().maxLength();

        for(int i = 1; i<=dayButtonList.size(); i++) {
            if((i>=firstDayOfMonth) && (i<(numberOfDaysInCurrentMonth+firstDayOfMonth))) {
                dayButtonList.get(i-1).setText(String.valueOf(dayNumeration));
                dayNumeration++;
            }else {
                dayButtonList.get(i-1).setText("");
                dayButtonList.get(i-1).setDisable(true);


            }
        }
    }


    public void handleDayClick(ActionEvent e) {
        Button chosenButton = (Button) e.getSource();
        LocalDate chosenDay = LocalDate.of(currentYearNum,currentMonthNum,Integer.parseInt(chosenButton.getText()));
        MyCalendar.setSelectedDay(MyCalendar.getDays().get(MyCalendar.getDayIndex(chosenDay)));
        showDay.setText("View for of the day: " + chosenDay.toString());
        taskAccordion.setDisable(false);

        showTasksList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        showTasksList.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> taskListView) {
                ListCell<Task> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(Task task, boolean b) {
                        super.updateItem(task, b);
                        if(b){
                            setText(null);
                        }
                        else {
                            setText(task.getTaskName());
                            if(task.getDeadline().isEqual(LocalDate.now())) {
                                setTextFill(Color.DARKRED);
                            } else if(task.getDeadline().isAfter(task.getDeadline().minusDays(3)) && task.getDeadline().isBefore(task.getDeadline())) {
                                setTextFill(Color.DARKGOLDENROD);
                            } else {
                                setTextFill(Color.DARKGREEN);
                            }
                        }
                    }
                };
                return cell;
            }
        });

        showTasksList.setItems(MyCalendar.getDays().get(MyCalendar.getDayIndex(chosenDay)).getTodaysTasks());
        showTasksList.getSelectionModel().selectFirst();

    }

    public void handleAddTaskClick() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Addition of task to the day: " + MyCalendar.getSelectedDay());
        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Kalendarz/AddTaskDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK) {
            AddTaskDialog newTaskToAdd = fxmlLoader.getController();
            Task toAdd = newTaskToAdd.createTask();
            MyCalendar.getDays().get(MyCalendar.getDayIndex(MyCalendar.getSelectedDay().getDate())).addTask(toAdd);
        }



    }

    public void handleEditTaskClick(ActionEvent event) {
        System.out.println("Editing task is a work in progress. Wait patiently");
    }

    public void handleCancelTaskClick(ActionEvent event) {
        EventHandler<ActionEvent> delete = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Task taskToCancel = showTasksList.getSelectionModel().getSelectedItem();
                deleteTask(taskToCancel);
            }
        };
    }
    public void deleteTask(Task t) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Task deletion");
            a.setHeaderText("You intend to delete task: " + t.getTaskName());
            a.setContentText("Are you sure you want to proceed? This operation is irreversible and you put this task in for a good reason");
            Optional<ButtonType> result = a.showAndWait();

            if(result.isPresent() && result.get()==ButtonType.OK) {
                MyCalendar.getDays().get(MyCalendar.getDayIndex(MyCalendar.getSelectedDay().getDate())).getTodaysTasks().remove(t);
        }
    }
}
