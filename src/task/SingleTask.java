package task;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class SingleTask extends Task {


    public SingleTask(String title, String description, TaskType type, LocalDateTime date) {
        super(title, description, type, date);
    }

    @Override
    public LocalDateTime getNext(LocalDate date) {
        return this.getDate();
    }

}
