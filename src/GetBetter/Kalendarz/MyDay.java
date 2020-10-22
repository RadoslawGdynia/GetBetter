package GetBetter.Kalendarz;

import GetBetter.DoZrobienia.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyDay {
    private final LocalDate date;
    private final List<Task> todaysTasks;

    public MyDay(LocalDate date) {
        this.date = date;
        this.todaysTasks = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public ObservableList<Task> getTodaysTasks() {
        ObservableList<Task> viewTodaysTasks = FXCollections.observableArrayList();
        viewTodaysTasks.addAll(todaysTasks);
        return viewTodaysTasks;
    }
    private boolean findTask(Task szukane) {
        for(Task task : todaysTasks) {
            if (szukane.equals(task)) {
                return true;
            }
        }
        return false;
    }
    public void addTask(Task noweZadanie) {
        if(findTask(noweZadanie)) {
            System.out.println("Zadanie " + noweZadanie.getTaskName() + " jest już zapisane w tym dniu. Operacja odrzucona");
        } else {
            if(noweZadanie.getDeadline().isBefore(this.getDate())) {
                System.out.println("Deadline cannot be in the past. Please correct deadline of the added task to today or future.");
            } else {
                todaysTasks.add(noweZadanie);
                System.out.println("Zadanie " + noweZadanie.getTaskName() + " zostało dodane do dnia: " + this.getDate());
            }
        }
    }
    public boolean removeTask(Task kasowaneZadanie) {
        if(todaysTasks.isEmpty()){
            System.out.println("Lista zadań na dzień " + this.getDate() + " jest pusta, skasowania zadania niemożliwe. Zarzucono.");
            return false;
        } else {
            if (findTask(kasowaneZadanie)) {
                todaysTasks.remove(kasowaneZadanie);
                System.out.println("Zadanie o nazwie " + kasowaneZadanie.getTaskName() + " zostało skasowane");
                return true;
            } else {
                System.out.println("Zadania o nazwie " + kasowaneZadanie.getTaskName() + " nie ma na liście zadań. Odrzucono");
                return false;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Date;");
        sb.append(this.getDate());
//        sb.append(this.getDate().getYear());
//        sb.append(";");
//        sb.append(this.getDate().getMonth().getValue());
//        sb.append(";");
//        sb.append(this.getDate().getDayOfMonth());
//        sb.append(";");
        sb.append("\n");
        if(todaysTasks.isEmpty()) {
            return sb.toString();
        } else {
            for(Task task : todaysTasks){
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
