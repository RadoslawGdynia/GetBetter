package GetBetter.Kalendarz;

import GetBetter.DoZrobienia.MyTask;
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
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;

public class MyCalendarController {


    //GENERAL:
    private int currentMonthNum;
    private int currentYearNum;
    private int currentDayNum;
    private MyDay selectedDay = MyCalendar.getDays().get(MyCalendar.getDayIndex(LocalDate.now()));
    private Predicate<LocalDate> monthCheck;

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
        private ComboBox<MyTask> taskSelectionCombo;
        @FXML
        private Pane TimePane;
        @FXML
        private Pane PlanningPane;

        //Todays tasks

        ProgressBar completion = new ProgressBar();



        @FXML
        private TreeTableView<MyTask> subtasksTreeTable;
        @FXML
        private TreeTableColumn<MyTask, String> subtaskNameColumn;
        @FXML
        private TreeTableColumn<MyTask, String> subtaskDeadlineColumn;
        @FXML
        private TreeTableColumn<MyTask, ProgressBar> subtaskProgressColumn;


        @FXML
        private TableView<MyTask> TVTasksTable;
        @FXML
        private TableColumn<MyTask, String> TVTaskName;
        @FXML
        private TableColumn<MyTask, String> TVTaskDeadline;
        @FXML
        private TableColumn<MyTask, Integer> TVNumberOfSubtasks;

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

    public int getCurrentMonthNum() {
        return currentMonthNum;
    }

    public int getCurrentYearNum() {
        return currentYearNum;
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
            CalendarTile configurableTile;
            String display = (i - (firstDayOfMonth - 1))+"";
            if ((i >= firstDayOfMonth) && (i < (numberOfDaysInCurrentMonth + firstDayOfMonth))) {

                configurableTile = new CalendarTile(daysTilePane, idString, display, size, size);

            } else {
                configurableTile = new CalendarTile(daysTilePane, idString, display, size, size);
                configurableTile.setDisable(true);
                configurableTile.setVisible(false);
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
    public void configuteTimeTiles() {
        final int TILES_NUMBER = 72;
        int hour1 = 6;
        int hour2 = 6;
        int minutes1 = 0;
        int minutes2 = 15;
        final int minutesIncrement = 15;
        StringBuilder text = new StringBuilder();

        for(int i =1; i<=TILES_NUMBER; i++){
            text.append(hour1);
            text.append(":");
            text.append(minutes1);
            if(minutes1==0) text.append(0);
            text.append("-");
            text.append(hour2);
            text.append(":");
            text.append(minutes2);
            if(minutes2==0) text.append(0);

            TimeTile tile = new TimeTile(TimePane,text.toString(), 100, (int)(TimePane.getHeight()/TILES_NUMBER));


            minutes1+=minutesIncrement;
            minutes2+=minutesIncrement;

            if(minutes1==60){
                hour1++;
                minutes1=0;
            }
            if(minutes2 == 60){
                hour2++;
                minutes2=0;
            }

            text.delete(0, text.length());
        }

    }
    public void configurePlanTiles() {
        final int TILES_NUMBER = 72;
        for(int i =1; i<=TILES_NUMBER; i++) {
            PlanTile planTile = new PlanTile(PlanningPane, "Plan", 200, 9);
        }

    }



    // B. TODAYS TASK METHODS:


    private void configureTasksTable() {
        TVTasksTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TVTaskName.setCellValueFactory(new PropertyValueFactory<MyTask, String>("taskName"));
        TVTaskDeadline.setCellValueFactory(new PropertyValueFactory<MyTask, String>("deadline"));
        TVNumberOfSubtasks.setCellValueFactory(new PropertyValueFactory<MyTask, Integer>("subtaskQuantity"));

        TVTasksTable.setItems(selectedDay.getTodaysTasks());

    }
    public void handleTaskSelection(MouseEvent mouseEvent) {
        MyTask cTask = TVTasksTable.getSelectionModel().getSelectedItem();
        System.out.println("Currently selected task: " + cTask.getTaskName());
      //  subtasksTreeTable.setDisable(false);

        AddTaskButton.setDisable(false);
        EditTaskButton.setDisable(false);
        DeleteTaskButton.setDisable(false);

    }



    public void configureSubtasksTable() { //MyTask cTask) {
        TreeItem<MyTask> root = new TreeItem<>();

        subtaskNameColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MyTask, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MyTask, String> taskStringCellDataFeatures) {
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

        for(MyTask task : selectedDay.getTodaysTasks()) {
           TreeItem<MyTask> listableTask = new TreeItem<>(task);

            for (MyTask subtask : task.getSubtasks()) {
                TreeItem<MyTask> listableSubtask = new TreeItem<>(subtask);
                listableTask.getChildren().add(listableSubtask);
            }
            root.getChildren().add(listableTask);
        }
        subtasksTreeTable.setRoot(root);



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
            MyTask toAdd = newTaskToAdd.createTask();
            selectedDay.addTask(toAdd);

        }


    }
    public void handleAddSubtaskClick(ActionEvent event) {
        MyTask task = TVTasksTable.getSelectionModel().getSelectedItem();
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
                MyTask toAdd = newTaskToAdd.createTask();
                task.addSubtask(toAdd);
            }
        }
    }

    public void handleEditTaskClick() {
        MyTask task = TVTasksTable.getSelectionModel().getSelectedItem();
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

        MyTask task = TVTasksTable.getSelectionModel().getSelectedItem();
        deleteTask(task);
    }

    public void deleteTask(MyTask t) {
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



    class CalendarTile extends StackPane {
        private final Pane root;
        private final String ID;
        private String display;

        public CalendarTile(Pane tpane, String id, String display, int xPixels, int yPixels) {

            this.ID = id;
            this.root = tpane;
            this.display = display;
            int dayOfMonth = Integer.parseInt(display.trim());
            tpane.getChildren().add(this);
            Rectangle border = new Rectangle(xPixels, yPixels);

            if(currentYearNum<LocalDate.now().getYear()){
                border.setFill(Color.LIGHTGRAY);
            }
            else if(getCurrentMonthNum()==LocalDate.now().getMonth().getValue() &&
            getCurrentYearNum()==LocalDate.now().getYear()) {
                if (dayOfMonth < LocalDate.now().getDayOfMonth()) {
                    border.setFill(Color.LIGHTGRAY);
                } else if (dayOfMonth == LocalDate.now().getDayOfMonth()) {
                    border.setFill(Color.DARKCYAN);
                } else {
                    border.setFill(Color.DARKGREEN);
                }
            } else if (getCurrentMonthNum()>LocalDate.now().getMonth().getValue() || getCurrentYearNum()>LocalDate.now().getYear()) {
                border.setFill(Color.DARKGREEN);
            } else {
                border.setFill(Color.LIGHTGRAY);
            }


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
            showDay.setText("Plans for: " + selectedDay.getDate());
            detailsTabPane.setDisable(false);
          configureTasksTable();
          configureSubtasksTable();
          configuteTimeTiles();
          configurePlanTiles();
        }
    }
    class TimeTile extends StackPane {
        private final Pane root;
        private String display;

        public TimeTile(Pane tpane,  String display, int xPixels, int yPixels) {


            this.root = tpane;
            this.display = display;

            tpane.getChildren().add(this);
            Rectangle border = new Rectangle(xPixels, yPixels);
            border.setFill(Color.LIGHTGRAY);
            border.setStroke(Color.BLACK);
            Text text = new Text(display);
            text.setFont(Font.font("Calibri", 8));
            text.setTextAlignment(TextAlignment.CENTER);
            getChildren().addAll(border, text);
        }
    }
    class PlanTile extends StackPane {
        private final Pane root;
        private String display;

        public PlanTile(Pane tpane,  String display, int xPixels, int yPixels) {


            this.root = tpane;
            this.display = display;

            tpane.getChildren().add(this);
            Rectangle border = new Rectangle(xPixels, yPixels);
            border.setFill(Color.GHOSTWHITE);
            border.setStroke(Color.BLACK);
            Text text = new Text(display);
            text.setFont(Font.font("Calibri", 8));
            text.setTextAlignment(TextAlignment.CENTER);
            getChildren().addAll(border, text);
        }
    }
}


