package task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, String description, TaskType type, LocalDateTime date) {
        super(title, description, type, date);
    }

    @Override
    LocalDateTime getNext(LocalDate data) {
        LocalDateTime result = data.atTime(getDate().getHour(), getDate().getMinute());
        DayOfWeek current = data.getDayOfWeek();
        DayOfWeek next = getDate().getDayOfWeek();
        int delta = next.getValue() - current.getValue();
        if (delta < 0) {
            return result.plusDays(7 + delta);
        } else {
            return result.plusDays(delta);
        }
    }
}
