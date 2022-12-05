package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Repeatable {
    LocalDateTime getNext(LocalDate date);
}
