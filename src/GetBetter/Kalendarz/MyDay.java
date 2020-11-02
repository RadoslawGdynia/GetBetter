package GetBetter.Kalendarz;

import GetBetter.DoZrobienia.CustomTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Objects;

public class MyDay {
    private final LocalDate date;
    private final ObservableList<CustomTask> todaysTasks;

    public MyDay(LocalDate date) {
        this.date = date;
        this.todaysTasks = FXCollections.observableArrayList();
    }

    public LocalDate getDate() {
        return date;
    }

    public ObservableList<CustomTask> getTodaysTasks() {
        return todaysTasks;
    }
    private boolean findTask(CustomTask szukane) {
        for(CustomTask task : todaysTasks) {
            if (szukane.equals(task)) {
                return true;
            }
        }
        return false;
    }
    public void addTask(CustomTask newTask) {
        if(findTask(newTask)) {
            System.out.println("Zadanie " + newTask.getTaskName() + " jest już zapisane w tym dniu. Operacja odrzucona");
        } else {
            if(newTask.getDeadline().isBefore(this.getDate())) {
                System.out.println("Deadline cannot be in the past. Please correct deadline of the added task to today or future.");
            } else {
                todaysTasks.add(newTask);
                System.out.println("Zadanie " + newTask.getTaskName() + " zostało dodane do dnia: " + this.getDate());
            }
        }
    }
    public boolean removeTask(CustomTask taskToDelete) {
        if(todaysTasks.isEmpty()){
            System.out.println("Lista zadań na dzień " + this.getDate() + " jest pusta, skasowania zadania niemożliwe. Zarzucono.");
            return false;
        } else {
            if (findTask(taskToDelete)) {
                todaysTasks.remove(taskToDelete);
                System.out.println("Zadanie o nazwie " + taskToDelete.getTaskName() + " zostało skasowane");
                return true;
            } else {
                System.out.println("Zadania o nazwie " + taskToDelete.getTaskName() + " nie ma na liście zadań. Odrzucono");
                return false;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Date;");
        sb.append(this.getDate());
        sb.append("\n");
        if(todaysTasks.isEmpty()) {
            return sb.toString();
        } else {
            for(CustomTask task : todaysTasks){
                sb.append(task.toString());
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyDay)) return false;
        MyDay day = (MyDay) o;
        return this.date.equals(day.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date)*31;
    }
}
