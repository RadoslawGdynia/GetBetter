package GetBetter.DoZrobienia;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;


/**
 * Object <code>CustomTask</code> represents things to do.
 */
public class CustomTask implements Comparable<CustomTask> {

    //Visible properties
    private final SimpleStringProperty taskName = new SimpleStringProperty("");
    private SimpleStringProperty details = new SimpleStringProperty("");
    private LocalDate deadline;
    private SimpleBooleanProperty finalised = new SimpleBooleanProperty();
    private ObservableList<CustomTask> subtasks;
    private SimpleIntegerProperty subtaskQuantity = new SimpleIntegerProperty(0);

    //Hidden properties

    private int pointValue;
    private int deadlineChangeCounter;

    /**
     * Creates new Task Object in the program. Only some fields are possible for user to specify, others are internally specified
     */
    public CustomTask(String taskName, String details, LocalDate deadline) {
        this.taskName.set(taskName);
        this.details.set(details);
        this.deadline = deadline;
        this.finalised.set(false);
        this.subtasks = FXCollections.observableArrayList();

        this.pointValue = 1 + (int) (Math.random() * 10); //w wersji ostatecznej wartość 10 zostanie zmieniona na wartość przypisaną do statusu.
        this.deadlineChangeCounter = 0;

    }

    /**
     * Constructor with all fields being specified is necessary for loading the data from the file and recreating previous list.
     */
    public CustomTask(String taskName, String details, int pointValue, LocalDate deadline, boolean finalised, List<CustomTask> addedSubtasks, int deadlineChangeCounter) {
        this.taskName.set(taskName);
        this.details.set(details);
        this.deadline = deadline;
        this.finalised.set(finalised);
        this.subtasks = FXCollections.observableArrayList();
        subtasks.addAll(addedSubtasks);

        this.pointValue = pointValue;
        this.deadlineChangeCounter = deadlineChangeCounter;

    }

    public String getTaskName() {
        return taskName.get();
    }

    public String getDetails() {
        return details.get();
    }

    public int getPointValue() {
        return pointValue;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean getFinalised() {
        return finalised.get();
    }

    public ObservableList<CustomTask> getSubtasks() {
        return subtasks;
    }

    public int getDeadlineChangeCounter() {
        return deadlineChangeCounter;
    }

    public int getSubtaskQuantity() {
        return subtaskQuantity.get();
    }

    public void setSubtaskQuantity(int subtaskQuantity) {
        this.subtaskQuantity.set(subtaskQuantity);
    }

    public void setFinalised(boolean done) {
        if (done) {
            for(CustomTask sub: subtasks){
               if(!sub.getFinalised()){
                   System.out.println("Jedno lub więcej podzadań jest nieukończonych. Zakończ najpierw wszystkie podzadania by zamknąć zadanie.");
                   return;
               }
            }
            this.finalised.setValue(true);
        }
    }

    private void setDetails(String details) {
        this.details.setValue(details);
    }

    private void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    private void setPointValue(int value) {
        this.pointValue = value;
    }

    public void editTaskDescription(String opis) {
        this.setDetails(opis);

    }

    /**
     * Allows to change the deadline of a given Task, but a limited number of times
     *
     * @param date provides a new date for deadline
     *             deadlineChangeCounter is a private counter that limits number of changes to value hard-coded in this method.
     */
    public void editTaskDeadline(LocalDate date) {
        if (this.deadlineChangeCounter <= 3) {
            System.out.println("Data deadline została zmieniona na " + date.toString() + ". Ilość możliwych do przeprowadzenia zmian: " + (3 - deadlineChangeCounter));
            this.deadlineChangeCounter++;
        } else {
            System.out.println("Przekroczono maksymalną liczbę zmian deadline. Operacja odrzucona.");
        }
    }

    /**
     * Allows to change all of the data contained in Object Task to form of String.
     * Goal is to set the method to a way which will allow to recreate the object while program start-up.
     */
    @Override
    public String toString() {

        StringBuilder textTask = new StringBuilder();

        textTask.append(taskName.getValue());
        textTask.append(";");
        textTask.append(details.getValue());
        textTask.append(";");
        textTask.append(pointValue);
        textTask.append(";");
        textTask.append(deadline);
        textTask.append(";");
        textTask.append(finalised.getValue());
        textTask.append(";");
        textTask.append(deadlineChangeCounter);
        textTask.append(";");

        StringBuilder textSubtasks = new StringBuilder();
        if (subtasks.size() > 0) {
            for (CustomTask sub : subtasks) {
                textSubtasks.append("\nPodzadanie;");
                textSubtasks.append(sub.toString());
                textSubtasks.append(";");
            }
            textTask.append(textSubtasks);

        }
        return textTask.toString();
    }

    /**
     *
     */
    public void addSubtask(CustomTask dodawane) {
        try {
            for (CustomTask sub : this.subtasks) {
                if (sub.equals(dodawane)) {
                    System.out.println("Zadanie " + this.getTaskName() + " już zawiera " + dodawane.getTaskName() +
                            "\nOperacja przerwana.");
                    return;
                }
            }
            if (dodawane.getDeadline().isBefore(this.getDeadline()) || dodawane.getDeadline().equals(this.getDeadline())) {
                this.subtasks.add(dodawane);
                for (CustomTask sub : this.subtasks){
                    sub.setPointValue(this.pointValue/this.getSubtasks().size());
                }
                System.out.println("Subtask " + dodawane.getTaskName()+ " was added to task " + this.getTaskName());
                this.subtaskQuantity.add(1);
            } else {
                System.out.println("Deadline podzadania nie może być później niż zadania nadrzędnego. Odrzucono.");
            }


        } catch (Exception e) {
            System.out.println("problem przy dodawaniu podzadania");
        }
    }

    /**
     * Method allows to remove the subtask from list of subtasks of this Task.
     */
    public void cancelSubtask(CustomTask kasowane) {
        try {
            int initialSubtasksNumber = this.getSubtasks().size();
            if (this.subtasks.isEmpty()) {
                System.out.println("Lista podzadań tego zadania jest pusta. Nie można skasować podzadania. Odrzucono.");
            } else {
                for (CustomTask ver : this.subtasks) {
                    if (kasowane.equals(ver)) {
                        this.subtasks.remove(ver);
                        System.out.println("Podzadanie " + kasowane.getTaskName() + " zostało skasowane z zadania " + this.getTaskName());
                        return;
                    }
                }
                if (this.getSubtasks().size() == initialSubtasksNumber) {
                    System.out.println(this.getTaskName() + " nie zawiera podzadania " + kasowane.getTaskName() + ". Operacja odrzucona.");
                }
            }
        } catch(ConcurrentModificationException e) {
            System.out.println();
            // Metoda wywołuje ConcurrentModificationException ponieważ wykorzystuje iterację do wyszukania elementu do skasowania,
            //przeprowadzenie operacji skasowania powoduje zmianę zasięgu iteracji w związku z czym iterator głupieje
        } catch(Exception e) {
            System.out.println("problem przy kasowaniu podzadania: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomTask)) return false;
        CustomTask task = (CustomTask) o;
        return this.deadlineChangeCounter == task.deadlineChangeCounter &&
                this.taskName.equals(task.taskName) &&
                Objects.equals(this.details, task.details) &&
                this.deadline.equals(task.deadline);
    }
    /**
     *Following method allows to compare Tasks to one another.
     * At the beginning only factors taken into account are closeness to deadline, but it will be extended by addition of
     * possibility of stating a status of task, which will describe its importance.
     */
    @Override
    public int compareTo(CustomTask o) {
        int timeForThis = LocalDate.now().compareTo(this.getDeadline());
        int timeForO = LocalDate.now().compareTo(o.getDeadline());
        return Integer.compare(timeForThis, timeForO);
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(taskName, deadline, deadlineChangeCounter) + 17;
    }
}
