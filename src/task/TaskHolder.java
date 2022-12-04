package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class TaskHolder {
    public TaskHolder() {
        //for test only
        addTask(new SingleTask("SingleTask", "for test only", TaskType.PRIVATE,
                LocalDateTime.of(2022, 12, 5, 12, 0)));
        addTask(new DailyTask("DailyTask", "for test only", TaskType.PRIVATE,
                LocalDateTime.of(2022, 12, 5, 12, 0)));
        addTask(new WeeklyTask("WeeklyTask", "for test only", TaskType.PRIVATE,
                LocalDateTime.of(2022, 12, 5, 12, 0)));
        addTask(new MonthlyTask("MonthlyTask", "for test only", TaskType.PRIVATE,
                LocalDateTime.of(2022, 12, 5, 12, 0)));
        addTask(new AnnualTask("AnnualTask", "for test only", TaskType.PRIVATE,
                LocalDateTime.of(2022, 12, 5, 12, 0)));
    }

    private final Map<Integer, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        if (task != null) {
            tasks.put(task.getId(), task);
        } else throw new NullPointerException("пустая задача");
    }

    public void deleteTask(int id) {
        tasks.get(id).setDeleted();
    }

    public List<Task> getTaskOnDay(LocalDate data) {
        ArrayList<Task> result = new ArrayList<>();
        for (Map.Entry<Integer, Task> pair : tasks.entrySet()) {
            if (pair.getValue().isDeleted()) continue;
            if (data.isEqual(LocalDate.from(pair.getValue().getNext(data)))) {
                result.add(pair.getValue());
            }
        }
        result.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return LocalTime.from(o1.getDate()).
                        compareTo(LocalTime.from(o2.getDate()));
            }
        });
        return result;
    }

    public Map<LocalDate, List<Task>> getTasks(LocalDate from, int days) {
        Map<LocalDate, List<Task>> result = new LinkedHashMap<>();
        for (int i = 0; i < days; i++) {
            LocalDate ld = from.plusDays(i);
            result.put(ld, getTaskOnDay(ld));
        }
        return result;
    }

    public List<Task> getAllTasks() {
        ArrayList<Task> result = new ArrayList<>();
        for (Integer id : tasks.keySet()) {
            result.add(tasks.get(id));
        }
        return result;
    }

    public List<Task> getDeleted() {
        ArrayList<Task> result = new ArrayList<>();
        for (Integer id : tasks.keySet()) {
            if (tasks.get(id).isDeleted()) result.add(tasks.get(id));
        }
        return result;
    }

    public boolean contains(int i) {
        return tasks.containsKey(i);
    }
}
