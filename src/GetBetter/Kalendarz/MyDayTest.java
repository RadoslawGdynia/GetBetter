package GetBetter.Kalendarz;

import GetBetter.DoZrobienia.MyTask;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MyDayTest {

    MyDay test;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        test = new MyDay(LocalDate.now());
        MyTask task1 = new MyTask("nazwa1", "opis1", LocalDate.now().plusDays(3));
        MyTask task3 = new MyTask("nazwa3", "opis3", LocalDate.now().plusDays(3));
        MyTask task4 = new MyTask("nazwa4", "opis4", LocalDate.now().plusDays(3));
        MyTask subtask1 = new MyTask("podzadanie1", "opis podzadanie 1", LocalDate.now());
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
        MyTask task1 = new MyTask("nazwa4", "opis1", LocalDate.now().plusDays(3));
        MyTask task3 = new MyTask("nazwa35", "opis35", LocalDate.now().plusDays(3));
        MyTask task4 = new MyTask("nazwa55", "opis55", LocalDate.now().plusDays(3));
        test.addTask(task1);
        test.addTask(task3);
        test.addTask(task4);
        assertEquals(initialNumberTasks+3, test.getTodaysTasks().size());
    }

    @org.junit.jupiter.api.Test
    void addTask_Alreadyin() {
        int initialNumberTasks = test.getTodaysTasks().size();

        MyTask task1 = new MyTask("nazwa1", "opis1", LocalDate.now().plusDays(3));
        MyTask task3 = new MyTask("nazwa3", "opis3", LocalDate.now().plusDays(3));
        MyTask task4 = new MyTask("nazwa4", "opis4", LocalDate.now().plusDays(3));
        test.addTask(task1);
        test.addTask(task3);
        test.addTask(task4);
        assertEquals(initialNumberTasks, test.getTodaysTasks().size());
    }
    @org.junit.jupiter.api.Test
    void addSubTask() {
        String s = test.getTodaysTasks().get(0).toString();
        System.out.println(s);
        MyTask task2 = new MyTask("nazwa2", "opis2", LocalDate.now().plusDays(3));
        test.getTodaysTasks().get(0).addSubtask(task2);
        String s2 = test.getTodaysTasks().get(0).toString();
        System.out.println(s2);
        assertNotEquals(s2, s);
        MyTask task6 = new MyTask("nazwa6", "opis6", LocalDate.now().plusDays(3));
        test.getTodaysTasks().get(0).addSubtask(task6);
        String s6 = test.getTodaysTasks().get(0).toString();
        System.out.println(s2+"\n" + s6);
        assertNotEquals(s6, s);


    }

    @org.junit.jupiter.api.Test

    void removeTask() {
        int initialSize = test.getTodaysTasks().size();
        MyTask task1 = new MyTask("nazwa1", "opis1", LocalDate.now().plusDays(3));
        test.removeTask(task1);
        assertEquals(initialSize-1, test.getTodaysTasks().size());
    }

    @org.junit.jupiter.api.Test
    void removeNonExistentTask() {
        int initialSize = test.getTodaysTasks().size();
        MyTask task7 = new MyTask("nazwa7", "opis7", LocalDate.now().plusDays(9));
        test.removeTask(task7);
        assertEquals(initialSize, test.getTodaysTasks().size());
    }

    @org.junit.jupiter.api.Test
    void removeTaskEmptyList() {
        test.getTodaysTasks().clear();
        int initialSize = test.getTodaysTasks().size();
        System.out.println("Initial size = " + initialSize);
        MyTask task1 = new MyTask("nazwa1", "opis1", LocalDate.now().plusDays(3));
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