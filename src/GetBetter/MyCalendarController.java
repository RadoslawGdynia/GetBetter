package GetBetter;

import GetBetter.DoZrobienia.Task;
import GetBetter.Kalendarz.MyCalendar;
import GetBetter.Kalendarz.MyDay;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class MyCalendarController {

    private final List<Button> dayButtonList = new ArrayList<>();
    private List<TreeItem<Task>> treeItemsList = new ArrayList<>();
    private int currentMonthNum;
    private int currentYearNum;
    private int currentDayNum;
    private MyDay selectedDay = MyCalendar.getDays().get(MyCalendar.getDayIndex(LocalDate.now()));
    private Task selectedTask;

    @FXML
    private AnchorPane mainPane;


    @FXML
    private Label monthName;
    @FXML
    private Label yearNumber;
    @FXML
    private TabPane detailsTabPane;
    @FXML
    private Label showDay;
    @FXML
    private ComboBox<Task> taskSelectionCombo;
    @FXML
    private TreeTableView<Task> taskListTableView;
    @FXML
    private TreeTableColumn<Task, String> nameColumn;
    @FXML
    private TreeTableColumn<Task, String> deadlineColumn;
    @FXML
    private TreeTableColumn<Task, Object> progressColumn;


    @FXML
    private Button dayButton1;
    @FXML
    private Button dayButton2;
    @FXML
    private Button dayButton3;
    @FXML
    private Button dayButton4;
    @FXML
    private Button dayButton5;
    @FXML
    private Button dayButton6;
    @FXML
    private Button dayButton7;
    @FXML
    private Button dayButton8;
    @FXML
    private Button dayButton9;
    @FXML
    private Button dayButton10;
    @FXML
    private Button dayButton11;
    @FXML
    private Button dayButton12;
    @FXML
    private Button dayButton13;
    @FXML
    private Button dayButton14;
    @FXML
    private Button dayButton15;
    @FXML
    private Button dayButton16;
    @FXML
    private Button dayButton17;
    @FXML
    private Button dayButton18;
    @FXML
    private Button dayButton19;
    @FXML
    private Button dayButton20;
    @FXML
    private Button dayButton21;
    @FXML
    private Button dayButton22;
    @FXML
    private Button dayButton23;
    @FXML
    private Button dayButton24;
    @FXML
    private Button dayButton25;
    @FXML
    private Button dayButton26;
    @FXML
    private Button dayButton27;
    @FXML
    private Button dayButton28;
    @FXML
    private Button dayButton29;
    @FXML
    private Button dayButton30;
    @FXML
    private Button dayButton31;
    @FXML
    private Button dayButton32;
    @FXML
    private Button dayButton33;
    @FXML
    private Button dayButton34;
    @FXML
    private Button dayButton35;
    @FXML
    private Button dayButton36;
    @FXML
    private Button dayButton37;
    @FXML
    private Button dayButton38;
    @FXML
    private Button dayButton39;
    @FXML
    private Button dayButton40;
    @FXML
    private Button dayButton41;
    @FXML
    private Button dayButton42;


    public void initialize() {
        detailsTabPane.setDisable(true);

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


        currentMonthNum = selectedDay.getDate().getMonthValue();
        currentYearNum = selectedDay.getDate().getYear();
        currentDayNum = selectedDay.getDate().getDayOfMonth();

        dayButtonConfiguration();
    }


    public void handleDayClick(ActionEvent e) {
        Button chosenButton = (Button) e.getSource();
        LocalDate chosenDay = LocalDate.of(currentYearNum, currentMonthNum, Integer.parseInt(chosenButton.getText()));
        selectedDay = MyCalendar.getDays().get(MyCalendar.getDayIndex(chosenDay));
        showDay.setText("Plans for: " + chosenDay.toString());
        detailsTabPane.setDisable(false);

        for (Task task : selectedDay.getTodaysTasks()) {
            TreeItem<Task> itemToAdd = new TreeItem<>(task);
            treeItemsList.add(itemToAdd);
            for (Task subtask : task.getSubtasks()) {
                TreeItem<Task> subitemToAdd = new TreeItem<>(subtask);
                itemToAdd.getChildren().add(subitemToAdd);
            }
            taskListTableView.setRoot(itemToAdd);
        }


        nameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Task, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Task, String> taskStringCellDataFeatures) {
                return new SimpleStringProperty(taskStringCellDataFeatures.getValue().getValue().getTaskName());
            }


        });


        deadlineColumn.setCellValueFactory(taskStringCellDataFeatures -> {
            if (taskStringCellDataFeatures == null) {
                return null;
            } else {
                return new SimpleStringProperty(taskStringCellDataFeatures.getValue().getValue().getDeadline().toString());
            }
        });

    }

    public void dayButtonConfiguration() {

        int firstDayOfMonth = LocalDate.of(currentYearNum, currentMonthNum, currentDayNum - (currentDayNum - 1)).getDayOfWeek().getValue();
        int dayNumeration = 1;
        int numberOfDaysInCurrentMonth = LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().maxLength();
        for (int i = 1; i <= dayButtonList.size(); i++) {
            if ((i >= firstDayOfMonth) && (i < (numberOfDaysInCurrentMonth + firstDayOfMonth))) {
                dayButtonList.get(i - 1).setText(String.valueOf(dayNumeration));
                dayButtonList.get(i - 1).setDisable(false);
                dayButtonList.get(i - 1).setVisible(true);
                dayNumeration++;
            } else {
                dayButtonList.get(i - 1).setText("");
                dayButtonList.get(i - 1).setDisable(true);
                dayButtonList.get(i - 1).setVisible(false);
            }
        }
        monthName.setText(LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        yearNumber.setText(String.valueOf(currentYearNum));
    }

    public void handleAddTaskClick() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Addition of task to the day: " + selectedDay);
        FXMLLoader fxmlLoader = new FXMLLoader();
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
        if (result.isPresent() && result.get() == ButtonType.OK) {
            AddTaskDialog newTaskToAdd = fxmlLoader.getController();
            Task toAdd = newTaskToAdd.createTask();
            selectedDay.addTask(toAdd);
        }


    }

    public void handleEditTaskClick(ActionEvent event) {
        System.out.println("Editing task is a work in progress. Wait patiently");
    }

    public void handleDeleteTaskClick(ActionEvent event) {
        selectedTask = taskListTableView.getSelectionModel().getSelectedItem().getValue();
        deleteTask(selectedTask);
    }

    public void deleteTask(Task t) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Task deletion");
        a.setHeaderText("You intend to delete task: " + t.getTaskName());
        a.setContentText("Are you sure you want to proceed? This operation is irreversible and you put this task in for a good reason");
        Optional<ButtonType> result = a.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            selectedDay.removeTask(t);
        }
    }

    public void handleMonthBack() {
        if (currentMonthNum == 1) {
            currentMonthNum = 12;
            currentYearNum--;
        } else {
            currentMonthNum--;
        }
        dayButtonConfiguration();
        System.out.println("Currently chosen day is: " + LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    }

    public void handleMonthForward() {
        if (currentMonthNum == 12) {
            currentMonthNum = 1;
            currentYearNum++;
        } else {
            currentMonthNum++;
        }
        dayButtonConfiguration();
        System.out.println("Currently chosen day is: " + LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    }
}
