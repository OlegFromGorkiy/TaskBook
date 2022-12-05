package task;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class DailyTask extends Task {


    public DailyTask(String title, String description, TaskType type, LocalDateTime date) {
        super(title, description, type, date);
    }

    @Override
    public LocalDateTime getNext(LocalDate date) {
        return date.atTime(getDate().getHour(), getDate().getMinute());

    }

}
