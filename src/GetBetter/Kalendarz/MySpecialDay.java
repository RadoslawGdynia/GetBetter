package GetBetter.Kalendarz;

import java.time.LocalDate;

public class MySpecialDay extends MyDay {
    String specialFactor;

    public MySpecialDay(LocalDate date, String specialFactor) {
        super(date);
        this.specialFactor = specialFactor;
    }
}
