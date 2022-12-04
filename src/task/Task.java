package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task {
    private static int count;
    private final Integer id;
    private String title; //заголовок
    private String description; //описание
    private LocalDateTime date;//дата
    private boolean isDeleted;
    private TaskType type;

    public Task(String title, String description, TaskType type, LocalDateTime date) {
        count = count == 0 ? 100 : count + 1;
        this.id = count;
        this.type = type;
        setTitle(title);
        setDescription(description);
        setDate(date);
        this.isDeleted = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        return id == task.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        String result = this.getClass().getSimpleName() + " #" + getId() +
                " Категория: " + getType() + "\n" +
                getTitle() + "\n" +
                "Описание: " + getDescription() + "\n" +
                String.format("Дата: %1$td.%1$tm.%1$tY, время: %1$tH:%1$tM", getDate());
        if (isDeleted()){
            result += "\nСтатус: удалена";
        } else {
            result += "\nСтатус: актуальна";
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new NullPointerException("Input title is null!");
        }
        if (title.isBlank()) {
            throw new NullPointerException("Input title is blank!");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new NullPointerException("Input description is null!");
        }
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        if (date == null) {
            throw new NullPointerException("Input date is null!");
        }
        this.date = date;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    void setDeleted() {
        isDeleted = true;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    abstract LocalDateTime getNext(LocalDate data);
}