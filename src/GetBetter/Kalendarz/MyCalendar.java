package GetBetter.Kalendarz;

import GetBetter.DoZrobienia.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyCalendar {
    private static List<MyDay> days;
    private static final String savingFile = "kalendarz.txt";


    public static ObservableList<MyDay> getDays() {
        ObservableList<MyDay> viewDays = FXCollections.observableArrayList();
        viewDays.addAll(days);
        return viewDays;
    }

    public static int getDayIndex(LocalDate date) {
        try {
            for (MyDay day : days) {
                if (day.getDate().equals(date)) {
                    return days.indexOf(day);
                }
            }
            System.out.println("Nie znaleziono szukanego dnia. Zwracam dzień dzisiejszy!");
            for (MyDay day : days) {
                if (day.getDate().equals(LocalDate.now())) {
                    return days.indexOf(day);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Brak daty w bazie");
        }
        System.out.println("Przetwarzane mogą być dni jedynie dziś i następne");
        return 0;
    }

    public static void saveCalendar() {

        try (BufferedWriter bufwrit = new BufferedWriter(new FileWriter(savingFile))) {

            for (MyDay toWrite : days) {
                bufwrit.write(toWrite.toString());

            }
        } catch (IOException e) {
            System.out.println("Wystąpił problem przy zapisie kalendarza: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void loadCalendar() {
        days = new ArrayList<>();
        Path path = Paths.get(savingFile);
        try (BufferedReader bufred = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufred.readLine()) != null) {
                String[] splitLine = line.split(";");
                if(splitLine[0].equals("Date")){
                    LocalDate date = LocalDate.parse(splitLine[1]);
                    MyDay entry = new MyDay(date);
                    days.add(entry);
                }else if(splitLine[0].equals("Podzadanie")){
                    List<Task> subtasks = new ArrayList<>();
                    Task subtaskToAdd = new Task(splitLine[1], splitLine[2],Integer.parseInt(splitLine[3]),LocalDate.parse(splitLine[4]),
                            Boolean.parseBoolean(splitLine[5]),subtasks,Integer.parseInt(splitLine[6]));
                    List<Task> tasks = days.get((days.size()-1)).getTodaysTasks();
                    tasks.get((tasks.size()-1)).addSubtask(subtaskToAdd);
                } else {
                    List<Task> tasks = new ArrayList<>();
                    Task taskToAdd = new Task(splitLine[0], splitLine[1],Integer.parseInt(splitLine[2]),LocalDate.parse(splitLine[3]),
                            Boolean.parseBoolean(splitLine[4]),tasks,Integer.parseInt(splitLine[5]));
                    days.get((days.size()-1)).addTask(taskToAdd);
                }
            }
            if(days.isEmpty()){
                LocalDate startDate = LocalDate.now().minusYears(1);
                LocalDate endDate = LocalDate.now().plusYears(1);
                while(startDate.compareTo(endDate)<1){
                    MyDay entry = new MyDay(startDate);
                    days.add(entry);
                    startDate = startDate.plusDays(1);
                }
            }
            if(days.get(days.size()-1).getDate().isBefore(LocalDate.now().plusYears(1))){
                LocalDate date = days.get(days.size()-1).getDate().plusDays(1);
                while(days.get(days.size()-1).getDate().isBefore(LocalDate.now().plusYears(1))){
                    days.add(new MyDay(date));
                    date = date.plusDays(1);
                }
            }

        } catch (IOException e) {
            System.out.println("Problem przy odczytywaniu pliku zadania.txt: " + e.getMessage() + "\n Stacktrace: ");
            e.printStackTrace();
        } catch (NullPointerException f) {
            System.out.println("Brak szukanego pliku");
        }
    }
}
