package task;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class SingleTask extends Task {


    public SingleTask(String title, String description, TaskType type, LocalDateTime date) {
        super(title, description, type, date);
    }

    @Override
    LocalDateTime getNext(LocalDate data) {
        return this.getDate();
    }

}
