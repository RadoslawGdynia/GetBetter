package GetBetter.Kalendarz;

import GetBetter.DoZrobienia.CustomTask;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class MyCalendarController {


    //GENERAL:
    private List<TreeItem<CustomTask>> treeItemsList = new ArrayList<>();
    private int currentMonthNum;
    private int currentYearNum;
    private int currentDayNum;
    private MyDay selectedDay = MyCalendar.getDays().get(MyCalendar.getDayIndex(LocalDate.now()));
    private CustomTask selectedTask;

    //CALENDAR

    @FXML
    private Label monthName;
    @FXML
    private Label yearNumber;
    @FXML
    private Pane daysTilePane;


    //DAY DETAILS
    @FXML
    private TabPane detailsTabPane;



        //Day Plan
        @FXML
        private Label showDay;
        @FXML
        private ComboBox<CustomTask> taskSelectionCombo;
        @FXML
        private Pane TimePane;
        @FXML
        private Pane PlanningPane;

        //Todays tasks

        @FXML
        private TreeTableView<CustomTask> subtasksTreeTable;
        @FXML
        private TreeTableColumn<CustomTask, String> subtaskNameColumn;
        @FXML
        private TreeTableColumn<CustomTask, String> subtaskDeadlineColumn;
        @FXML
        private TreeTableColumn<CustomTask, Integer> subtaskProgressColumn;

        @FXML
        private ListView<CustomTask> taskList;


        @FXML
        private TableView<CustomTask> TVTasksTable;
        @FXML
        private TableColumn<CustomTask, String> TVTaskName;
        @FXML
        private TableColumn<CustomTask, String> TVTaskDeadline;
        @FXML
        private TableColumn<CustomTask, Integer> TVNumberOfSubtasks;

        @FXML
        Button AddTaskButton;
        @FXML
        Button EditTaskButton;
        @FXML
        Button DeleteTaskButton;



        //GENERAL METHODS:


    public void initialize() {
        detailsTabPane.setDisable(true);
        currentMonthNum = selectedDay.getDate().getMonthValue();
        currentYearNum = selectedDay.getDate().getYear();
        currentDayNum = selectedDay.getDate().getDayOfMonth();
        configureCalendarPane();
    }
    //CALENDAR AREA METHODS:
    //=====================

    private void configureCalendarPane() {

        monthName.setText(LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        yearNumber.setText(String.valueOf(currentYearNum));

        int firstDayOfMonth = LocalDate.of(currentYearNum, currentMonthNum, currentDayNum - (currentDayNum - 1)).getDayOfWeek().getValue();
        int numberOfDaysInCurrentMonth = LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().maxLength();

        int NUM_OF_TILES = 42;
        int size = 90;

        for (int i = 1; i <= NUM_OF_TILES; i++) {

            String idString = "CalendarTile" + i;
            DayField dayField;

            if ((i >= firstDayOfMonth) && (i < (numberOfDaysInCurrentMonth + firstDayOfMonth))) {
                String display = "" + (i - (firstDayOfMonth - 1));
                dayField = new DayField(daysTilePane, idString, display, size, size);


            } else {

                dayField = new DayField(daysTilePane, idString, "", size, size);
                dayField.setDisable(true);
                dayField.setVisible(false);


            }
        }
    }
    public void handleMonthBack() {
        daysTilePane.getChildren().clear();
        if (currentMonthNum == 1) {
            currentMonthNum = 12;
            currentYearNum--;
        } else {
            currentMonthNum--;
        }
        detailsTabPane.setDisable(true);
        configureCalendarPane();
        System.out.println("Currently chosen month is: " + LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    }

    public void handleMonthForward() {
        daysTilePane.getChildren().clear();
        if (currentMonthNum == 12) {
            currentMonthNum = 1;
            currentYearNum++;
        } else {
            currentMonthNum++;
        }
        detailsTabPane.setDisable(true);
        configureCalendarPane();
        System.out.println("Currently chosen month is: " + LocalDate.of(currentYearNum, currentMonthNum, currentDayNum).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    }

    //DAY DETAILS METHODS:
    //===================

    private void noTaskSelected(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Missing information!");
        alert.setHeaderText("No task was chosen. Chosen action requires a selected task to be performed.");
        alert.setContentText("Please select one of the tasks from the list and proceed.");
        alert.showAndWait();
    }


    // A. DAY PLAN METHODS:



    // B. TODAYS TASK METHODS:


    private void configureTasksTable() {
        TVTasksTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TVTasksTable.setItems(selectedDay.getTodaysTasks());

        TVTaskName.setCellValueFactory(new PropertyValueFactory<CustomTask, String>("taskName"));
        TVTaskDeadline.setCellValueFactory(new PropertyValueFactory<CustomTask, String>("deadline"));
        TVNumberOfSubtasks.setCellValueFactory(new PropertyValueFactory<CustomTask, Integer>("subtaskQuantity"));
    }
    public void handleTaskSelection(MouseEvent mouseEvent) {
        CustomTask cTask = TVTasksTable.getSelectionModel().getSelectedItem();
        System.out.println("Currently selected task: " + cTask.getTaskName());
        subtasksTreeTable.setDisable(false);
        configureSubtasksTable(cTask);
        AddTaskButton.setDisable(false);
        EditTaskButton.setDisable(false);
        DeleteTaskButton.setDisable(false);

    }



    public void configureSubtasksTable(CustomTask cTask) {

        TreeItem<CustomTask> root = new TreeItem<>(cTask);
        for(CustomTask subtask : cTask.getSubtasks()){
            TreeItem<CustomTask> subtree = new TreeItem<>(subtask);
            root.getChildren().add(subtree);
        }
        subtasksTreeTable.setRoot(root);

        subtaskNameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CustomTask, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CustomTask, String> taskStringCellDataFeatures) {
                return new SimpleStringProperty(taskStringCellDataFeatures.getValue().getValue().getTaskName());
            }


        });

        subtaskDeadlineColumn.setCellValueFactory(taskStringCellDataFeatures -> {
            if (taskStringCellDataFeatures == null) {
                return null;
            } else {
                return new SimpleStringProperty(taskStringCellDataFeatures.getValue().getValue().getDeadline().toString());
            }
        });

    }

    public void handleAddTaskClick() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Addition of task to the day: " + selectedDay.getDate());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddTaskDialog.fxml"));


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
            AddTaskDialogController newTaskToAdd = fxmlLoader.getController();
            CustomTask toAdd = newTaskToAdd.createTask();
            selectedDay.addTask(toAdd);

        }


    }
    public void handleAddSubtaskClick(ActionEvent event) {
        CustomTask task = TVTasksTable.getSelectionModel().getSelectedItem();
        if (task == null) {
            noTaskSelected();
        } else {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Addition of subtask to task: " + task.getTaskName());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddTaskDialog.fxml"));

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
                AddTaskDialogController newTaskToAdd = fxmlLoader.getController();
                CustomTask toAdd = newTaskToAdd.createTask();
                task.addSubtask(toAdd);
            }
        }
    }

    public void handleEditTaskClick() {
        CustomTask task = TVTasksTable.getSelectionModel().getSelectedItem();
        if(task == null){
            noTaskSelected();
            return;
        } else {
            EditTaskDialogController.setSelectedTask(task);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Editing task: " + task.getTaskName());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("EditTaskDialog.fxml"));

            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
            } catch (IOException e) {
                System.out.println("Could not load the dialog");
                e.printStackTrace();
                return;
            }
            dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            EditTaskDialogController ETDialogController = fxmlLoader.getController();

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.APPLY) {
               ETDialogController.handleApplyChangesButton();

            }
        }
    }

    public void handleDeleteTaskClick(ActionEvent event) {

        CustomTask task = TVTasksTable.getSelectionModel().getSelectedItem();
        deleteTask(task);
    }

    public void deleteTask(CustomTask t) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Task deletion");
        a.setHeaderText("You intend to delete task: " + t.getTaskName());
        a.setContentText("Are you sure you want to proceed? This operation is irreversible and you put this task in for a good reason");
        Optional<ButtonType> result = a.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            selectedDay.removeTask(t);
            //visibleTasks.removeVisibleTask(t);
        }
    }



    class DayField extends StackPane {
        private final Pane root;
        private final String ID;
        private String display;

        public DayField(Pane tpane, String id, String display, int xPixels, int yPixels) {

            this.ID = id;
            this.root = tpane;
            this.display = display;


            tpane.getChildren().add(this);
            Rectangle border = new Rectangle(xPixels, yPixels);
            border.setFill(Color.DARKGREEN);
            border.setStroke(Color.BLACK);
            Text text = new Text(display);
            text.setFont(Font.font("Calibri", 30));
            text.setTextAlignment(TextAlignment.CENTER);
            getChildren().addAll(border, text);

            this.setOnMouseClicked(event -> handleDayClick());

        }

        private void handleDayClick() {
            selectedDay = MyCalendar.getDays().get(MyCalendar.
                    getDayIndex(LocalDate.of(
                            currentYearNum,
                            currentMonthNum,
                            Integer.parseInt(this.display))));
            showDay.setText("Plans for: " + this.display);
            detailsTabPane.setDisable(false);
          configureTasksTable();
          subtasksTreeTable.refresh();
          subtasksTreeTable.setDisable(true);
        }
    }
}


