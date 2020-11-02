package GetBetter.DoZrobienia;

import GetBetter.Kalendarz.MyDay;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomTaskTest {
    CustomTask testTask;


    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        final String taskName = "ZadanieTestowe";
        String details = "Szczegóły niniejszego zadania są zawarte wyłącznie w celach testowcy";
        LocalDate deadline = LocalDate.now().plusDays(1);
        testTask = new CustomTask(taskName, details, deadline);
        for (int i = 0; i <= 5; i++) {
            String nam = "subtaskName: " + i;
            String det = "details of task " + nam;
            testTask.addSubtask(new CustomTask(nam, det, deadline));

        }
        System.out.println("Stworzono instancję klasy Task. Przeprowadzam test.");
    }


    @org.junit.jupiter.api.Test
    void addSubtask() {
        int initialSize = testTask.getSubtasks().size();
        CustomTask subtaskTest = new CustomTask("Added", "Was it added?", LocalDate.now());
        System.out.println("Size of list subtasks BEFORE addition: " + testTask.getSubtasks().size());
        testTask.addSubtask(subtaskTest);
        System.out.println("Size of list subtasks AFTER addition: " + testTask.getSubtasks().size());
        assertEquals(initialSize + 1, testTask.getSubtasks().size());


        System.out.println("\n===== ADDING SUBTASK EQUAL TO ONE OF SUBTASKS (should fail) ====");
        CustomTask duplicate = new CustomTask("Added", "Was it added?", LocalDate.now());
        testTask.addSubtask(duplicate);
        assertEquals(initialSize + 1, testTask.getSubtasks().size());
    }

    @org.junit.jupiter.api.Test
    void addExistingSubtask() {
        int initialSize = testTask.getSubtasks().size();
        CustomTask subtaskTest = new CustomTask("Added", "Was it added?", LocalDate.now());
        testTask.addSubtask(subtaskTest);
        testTask.addSubtask(subtaskTest);
        assertEquals(initialSize + 1, testTask.getSubtasks().size());
    }

    @org.junit.jupiter.api.Test
    void addDuplicateSubtask() {
        int initialSize = testTask.getSubtasks().size();
        CustomTask subtaskTest = new CustomTask("Added", "Was it added?", LocalDate.now());
        testTask.addSubtask(subtaskTest);
        CustomTask duplicate = new CustomTask("Added", "Was it added?", LocalDate.now());
        testTask.addSubtask(duplicate);
        assertEquals(initialSize + 1, testTask.getSubtasks().size());
    }

    @org.junit.jupiter.api.Test
    void addSubtask_overDate() {

        int initialSize = testTask.getSubtasks().size();
        CustomTask overdue = new CustomTask("Sorry", "How could have I missed it?", LocalDate.now().plusDays(3));
        testTask.addSubtask(overdue);
        assertEquals(initialSize, testTask.getSubtasks().size());
    }

    @org.junit.jupiter.api.Test
    void cancelSubtask() {
        int initialSize = testTask.getSubtasks().size();
        System.out.println("Size of list subtasks BEFORE cancelation: " + testTask.getSubtasks().size());
        CustomTask subtaskToCancel = testTask.getSubtasks().get(testTask.getSubtasks().size() - 1);
        testTask.cancelSubtask(subtaskToCancel);
        System.out.println("Size of list subtasks AFTER cancelation: " + testTask.getSubtasks().size());
        assertEquals(initialSize - 1, testTask.getSubtasks().size());


    }

    @org.junit.jupiter.api.Test
    void cancelSubtask_NonExistent() {
        int initialSize = testTask.getSubtasks().size();

        CustomTask subtaskTest = new CustomTask("NotExistsInList", "Was it removed?", LocalDate.now());
        System.out.println("Size of list subtasks BEFORE cancelation: " + testTask.getSubtasks().size());
        testTask.cancelSubtask(subtaskTest);
        System.out.println("Size of list subtasks AFTER cancelation: " + testTask.getSubtasks().size());
        assertEquals(initialSize, testTask.getSubtasks().size());
    }


    @org.junit.jupiter.api.Test
    void testEquals_Task() {
        CustomTask equalsTest = new CustomTask("NotExistsInList", "Was it removed?", LocalDate.now());
        assertFalse(testTask.equals(equalsTest));
        CustomTask equal = new CustomTask("ZadanieTestowe", "Szczegóły niniejszego zadania są zawarte wyłącznie w celach testowcy", LocalDate.now().plusDays(1));
        assertTrue(testTask.equals(equal));
    }

    @org.junit.jupiter.api.Test
    void testEquals_Object() {
        Object equalsTest = new MyDay(LocalDate.now());
        assertFalse(testTask.equals(equalsTest));
    }

    @Test
    void compareToTest_positive() {
        CustomTask secondTask = new CustomTask("nazwaDrugiego", "opisDrugiego", LocalDate.now().plusDays(9));
        int testValue = testTask.compareTo(secondTask) ;
        assertEquals(1, testValue);
    }
    @Test
    void compareToTest_negative() {
        CustomTask secondTask = new CustomTask("nazwaDrugiego", "opisDrugiego", LocalDate.now());
        int testValue = testTask.compareTo(secondTask) ;
        assertEquals(-1, testValue);
    }


}