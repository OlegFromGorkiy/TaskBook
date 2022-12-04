package task;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class AnnualTask extends Task {
    public AnnualTask(String title, String description, TaskType type, LocalDateTime date) {
        super(title, description, type, date);
    }

    @Override
    LocalDateTime getNext(LocalDate data) {
        if (data.getDayOfYear() <= getDate().getDayOfYear()) {
            return getDate().withYear(data.getYear());
        } else {
            return getDate().withYear(data.getYear() + 1);
        }
    }
}
