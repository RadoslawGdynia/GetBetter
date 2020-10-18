package GetBetter.Kalendarz;

import GetBetter.DoZrobienia.Task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MyDayTest {

    MyDay test;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        test = new MyDay(LocalDate.now());
        Task task1 = new  Task("nazwa1", "opis1", LocalDate.now().plusDays(3));
        Task task3 = new  Task("nazwa3", "opis3", LocalDate.now().plusDays(3));
        Task task4 = new  Task("nazwa4", "opis4", LocalDate.now().plusDays(3));
        Task subtask1 = new Task("podzadanie1", "opis podzadanie 1", LocalDate.now());
        test.addTask(task1);
        test.addTask(task3);
        test.addTask(task4);
        test.getTodaysTasks().get(2).addSubtask(subtask1);

        System.out.println("Stworzono instancję klasy Task. Przeprowadzam test.");
    }

    @org.junit.jupiter.api.Test
    void getTodaysTasks() {
    }

    @org.junit.jupiter.api.Test
    void addTask() {
        int initialNumberTasks = test.getTodaysTasks().size();
        Task task1 = new  Task("nazwa4", "opis1", LocalDate.now().plusDays(3));
        Task task3 = new  Task("nazwa35", "opis35", LocalDate.now().plusDays(3));
        Task task4 = new  Task("nazwa55", "opis55", LocalDate.now().plusDays(3));
        test.addTask(task1);
        test.addTask(task3);
        test.addTask(task4);
        assertEquals(initialNumberTasks+3, test.getTodaysTasks().size());
    }

    @org.junit.jupiter.api.Test
    void addTask_Alreadyin() {
        int initialNumberTasks = test.getTodaysTasks().size();

        Task task1 = new  Task("nazwa1", "opis1", LocalDate.now().plusDays(3));
        Task task3 = new  Task("nazwa3", "opis3", LocalDate.now().plusDays(3));
        Task task4 = new  Task("nazwa4", "opis4", LocalDate.now().plusDays(3));
        test.addTask(task1);
        test.addTask(task3);
        test.addTask(task4);
        assertEquals(initialNumberTasks, test.getTodaysTasks().size());
    }
    @org.junit.jupiter.api.Test
    void addSubTask() {
        String s = test.getTodaysTasks().get(0).toString();
        System.out.println(s);
        Task task2 = new  Task("nazwa2", "opis2", LocalDate.now().plusDays(3));
        test.getTodaysTasks().get(0).addSubtask(task2);
        String s2 = test.getTodaysTasks().get(0).toString();
        System.out.println(s2);
        assertNotEquals(s2, s);
        Task task6 = new  Task("nazwa6", "opis6", LocalDate.now().plusDays(3));
        test.getTodaysTasks().get(0).addSubtask(task6);
        String s6 = test.getTodaysTasks().get(0).toString();
        System.out.println(s2+"\n" + s6);
        assertNotEquals(s6, s);


    }

    @org.junit.jupiter.api.Test

    void removeTask() {
        int initialSize = test.getTodaysTasks().size();
        Task task1 = new  Task("nazwa1", "opis1", LocalDate.now().plusDays(3));
        test.removeTask(task1);
        assertEquals(initialSize-1, test.getTodaysTasks().size());
    }

    @org.junit.jupiter.api.Test
    void removeNonExistentTask() {
        int initialSize = test.getTodaysTasks().size();
        Task task7 = new  Task("nazwa7", "opis7", LocalDate.now().plusDays(9));
        test.removeTask(task7);
        assertEquals(initialSize, test.getTodaysTasks().size());
    }

    @org.junit.jupiter.api.Test
    void removeTaskEmptyList() {
        test.getTodaysTasks().clear();
        int initialSize = test.getTodaysTasks().size();
        System.out.println("Initial size = " + initialSize);
        Task task1 = new  Task("nazwa1", "opis1", LocalDate.now().plusDays(3));
        assertFalse(test.removeTask(task1));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        System.out.println(test.toString());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
    }
}