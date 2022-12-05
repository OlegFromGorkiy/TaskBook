package task;

public enum TaskType {
    PRIVATE("Личное"),
    WORK("Рабочее");

    final String title;

    TaskType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
