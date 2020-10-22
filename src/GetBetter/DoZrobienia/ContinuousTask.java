package GetBetter.DoZrobienia;

import java.time.LocalDate;
import java.util.List;

/**
 * Class Continous task is prepared to allow configuration of tasks that extends for more than one day
 * */

public class ContinuousTask extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public ContinuousTask(String taskName, String details, LocalDate deadline) {
        super(taskName, details, deadline);
        this.startDate = LocalDate.now();
        this.endDate = deadline;
    }

    public ContinuousTask(String taskName, String details, int pointValue, LocalDate deadline, boolean finalised, List<Task> subtasks, int deadlineChangeCounter) {
        super(taskName, details, pointValue, deadline, finalised, subtasks, deadlineChangeCounter);
    }
}
