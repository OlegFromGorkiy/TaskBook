package task;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class MonthlyTask extends Task {
    public MonthlyTask(String title, String description, TaskType type, LocalDateTime date) {
        super(title, description, type, date);
    }

    @Override
    public LocalDateTime getNext(LocalDate date) {
        if (date.getDayOfMonth() <= getDate().getDayOfMonth()){
            return getDate().withYear(date.getYear()).withMonth(date.getMonthValue());
        }else {
            return getDate().withYear(date.getYear()).withMonth(date.getMonthValue()).plusMonths(1);
        }
    }
}
