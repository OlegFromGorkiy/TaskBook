package task;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class MonthlyTask extends Task {
    public MonthlyTask(String title, String description, TaskType type, LocalDateTime date) {
        super(title, description, type, date);
    }

    @Override
    LocalDateTime getNext(LocalDate data) {
        if (data.getDayOfMonth() <= getDate().getDayOfMonth()){
            return getDate().withYear(data.getYear()).withMonth(data.getMonthValue());
        }else {
            return getDate().withYear(data.getYear()).withMonth(data.getMonthValue()).plusMonths(1);
        }
    }
}
