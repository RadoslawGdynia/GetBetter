package GetBetter.DoZrobienia;

import java.time.LocalDate;
import java.util.*;


/**
 * Object <code>Task</code> represents things to do.
 */
public class Task implements Comparable<Task> {

    private final String taskName;
    private String details;
    // private Status status;  //status zadania będzie modyfikatorem wartości punktowej;
    private int pointValue;
    private LocalDate deadline;
    private boolean finalised; //weryfikuje czy już zrobione czy nie - checkbox w javaFX
    private List<Task> subtasks;
    private int deadlineChangeCounter;


    /**
     * Creates new Task Object in the program. Only some fields are possible for user to specify, others are internally specified
     */
    public Task(String taskName, String details, LocalDate deadline) {
        this.taskName = taskName;
        this.details = details;
        this.deadline = deadline;
        //this.status = status;
        this.pointValue = 1 + (int) (Math.random() * 10); //w wersji ostatecznej wartość 10 zostanie zmieniona na wartość przypisaną do statusu.
        this.subtasks = new ArrayList<>();
        this.finalised = false;
        this.deadlineChangeCounter = 0;
    }

    /**
     * Constructor with all fields being specified is necessary for loading the data from the file and recreating previous list.
     */
    public Task(String taskName, String details, int pointValue, LocalDate deadline, boolean finalised, List<Task> subtasks, int deadlineChangeCounter) {
        this.taskName = taskName;
        this.details = details;
        this.pointValue = pointValue;
        this.deadline = deadline;
        this.finalised = finalised;
        this.subtasks = subtasks;
        this.deadlineChangeCounter = deadlineChangeCounter;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDetails() {
        return details;
    }

    public int getPointValue() {
        return pointValue;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean getFinalised() {
        return finalised;
    }

    public List<Task> getSubtasks() {
        return this.subtasks;
    }

    public int getDeadlineChangeCounter() {
        return deadlineChangeCounter;
    }

    public void setFinalised(boolean done) {
        if (done) {
            for(Task sub: subtasks){
               if(!sub.getFinalised()){
                   System.out.println("Jedno lub więcej podzadań jest nieukończonych. Zakończ najpierw wszystkie podzadania by zamknąć zadanie.");
                   return;
               }
            }
            this.finalised = true;
        }
    }

    private void setDetails(String details) {
        this.details = details;
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
    private void editTaskDeadline(LocalDate date) {
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

        textTask.append(taskName);
        textTask.append(";");
        textTask.append(details);
        textTask.append(";");
        textTask.append(pointValue);
        textTask.append(";");
        textTask.append(deadline);
        textTask.append(";");
        textTask.append(finalised);
        textTask.append(";");
        textTask.append(deadlineChangeCounter);
        textTask.append(";");

        StringBuilder textSubtasks = new StringBuilder();
        if (subtasks.size() > 0) {
            for (Task sub : subtasks) {
                textSubtasks.append("\nPodzadanie; ");
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
    public void addSubtask(Task dodawane) {
        try {
            for (Task sub : this.subtasks) {
                if (sub.equals(dodawane)) {
                    System.out.println("Zadanie " + this.getTaskName() + " już zawiera " + dodawane.getTaskName() +
                            "\nOperacja przerwana.");
                    return;
                }
            }
            if (dodawane.getDeadline().isBefore(this.getDeadline()) || dodawane.getDeadline().equals(this.getDeadline())) {
                this.subtasks.add(dodawane);
                for (Task sub : this.subtasks){
                    sub.setPointValue(this.pointValue/this.getSubtasks().size());
                }
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
    public void cancelSubtask(Task kasowane) {
        try {
            int initialSubtasksNumber = this.getSubtasks().size();
            if (this.subtasks.isEmpty()) {
                System.out.println("Lista podzadań tego zadania jest pusta. Nie można skasować podzadania. Odrzucono.");
            } else {
                for (Task ver : this.subtasks) {
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
            System.out.println("");
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
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
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
    public int compareTo(Task o) {
        int timeForThis = LocalDate.now().compareTo(this.getDeadline());
        int timeForO = LocalDate.now().compareTo(o.getDeadline());
        if (timeForThis>timeForO) {
            return 1;
        } else if(timeForThis==timeForO){
            return 0;
        } else{
            return -1;
        }
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(taskName, deadline, deadlineChangeCounter) + 17;
    }
}
