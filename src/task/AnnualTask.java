package task;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class AnnualTask extends Task {
    public AnnualTask(String title, String description, TaskType type, LocalDateTime date) {
        super(title, description, type, date);
    }

    @Override
    public LocalDateTime getNext(LocalDate date) {
        if (date.getDayOfYear() <= getDate().getDayOfYear()) {
            return getDate().withYear(date.getYear());
        } else {
            return getDate().withYear(date.getYear() + 1);
        }
    }
}
