import task.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final TaskHolder taskHolder = new TaskHolder();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                int id;
                LocalDate date;
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            id = inputID(scanner);
                            if (id == 0) {
                                break;
                            } else taskHolder.deleteTask(id);
                            break;
                        case 3:
                            id = inputID(scanner);
                            if (id == 0) break;
                            System.out.println("Введите заголовок задачи: ");
                            String title = scanner.nextLine();
                            taskHolder.setTitle(id,title);
                            System.out.println("Введите описание задачи: ");
                            String description = scanner.nextLine();
                            taskHolder.setDescription(id,description);
                            TaskType taskType = inputTaskType(scanner);
                            taskHolder.setTaskType(id,taskType);
                            LocalDateTime localDateTime = LocalDateTime.of(inputDate(scanner),
                                    inputTime(scanner));
                            taskHolder.setDate(id,localDateTime);
                            break;
                        case 4:
                            date = inputDate(scanner);
                            for (Task t : taskHolder.getTaskOnDay(date)) {
                                System.out.println(t);
                            }
                            break;
                        case 5:
                            date = inputDate(scanner);
                            int days = inputNumberOfDay(scanner);
                            for (Map.Entry<LocalDate, List<Task>> pair : taskHolder.getTasks(date, days).entrySet()) {
                                System.out.printf("Дата: %1$td.%1$tm.%1$tY%n", pair.getKey());
                                for (Task t : pair.getValue()) {
                                    System.out.println(t);
                                    System.out.println("---");
                                }
                                System.out.print("\n");
                            }
                            break;
                        case 6:
                            System.out.println("Список всех задачь: ");
                            for (Task t : taskHolder.getAllTasks()) {
                                System.out.println(t);
                                System.out.println("---");
                            }
                            break;
                        case 7:
                            for (Task t : taskHolder.getDeleted()) {
                                System.out.println(t);
                            }
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.println("Введите заголовок задачи: ");
        String title = scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String description = scanner.nextLine();
        TaskType taskType = inputTaskType(scanner);
        LocalDateTime localDateTime = LocalDateTime.of(inputDate(scanner),
                inputTime(scanner));
        switch (inputPeriodical(scanner)) {
            case 1:
                taskHolder.addTask(new SingleTask(title, description, taskType, localDateTime));
                break;
            case 2:
                taskHolder.addTask(new DailyTask(title, description, taskType, localDateTime));
                break;
            case 3:
                taskHolder.addTask(new WeeklyTask(title, description, taskType, localDateTime));
                break;
            case 4:
                taskHolder.addTask(new MonthlyTask(title, description, taskType, localDateTime));
                break;
            default:
                taskHolder.addTask(new AnnualTask(title, description, taskType, localDateTime));
        }

    }


    private static LocalDate inputDate(Scanner scanner) {
        System.out.println("Введите дату задачи в формате \"ДД.ММ.ГГГГ\":");
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] date = input.split("\\.");
                return LocalDate.of(Integer.parseInt(date[2]),
                        Integer.parseInt(date[1]), Integer.parseInt(date[0]));
            } catch (DateTimeException e1) {
                System.out.println("Неверная дата! Введите коренктную дату в формате \"ДД.ММ.ГГГГ\"");
            } catch (Exception e2) {
                System.out.println("Некорректный ввод! Введите дату в формате \"ДД.ММ.ГГГГ\"");
            }
        }
    }

    private static LocalTime inputTime(Scanner scanner) {
        System.out.println("Введите время задачи в формате \"чч:мм\":");
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] time = input.split(":");
                return LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
            } catch (DateTimeException e1) {
                System.out.println("Неверное время! Введите корректное время в формате \"чч:мм\"");
            } catch (Exception e2) {
                System.out.println("Некорректный ввод! Введите время в формате \"чч:мм\"");
            }
        }
    }

    private static TaskType inputTaskType(Scanner scanner) {
        System.out.println("Укажите тип задачи:\n0 - личная\n1 - рабочая");
        while (true) {
            try {
                String input = scanner.nextLine();
                int i = Integer.parseInt(input);
                if (i == 0) {
                    return TaskType.PRIVATE;
                } else if (i == 1) {
                    return TaskType.WORK;
                } else {
                    throw new RuntimeException();
                }
            } catch (RuntimeException e) {
                System.out.println("Нужно ввести либо 0, либо 1!");
            }
        }
    }

    private static int inputID(Scanner scanner) {
        System.out.println("Введите ID задачи или 0 для выхода");
        while (true) {
            try {
                String line = scanner.nextLine();
                int i = Integer.parseInt(line);
                if (i == 0) return 0;
                if (taskHolder.contains(i)) {
                    return i;
                } else throw new RuntimeException();
            } catch (RuntimeException e) {
                System.out.println("Задачи с таким ID не существует! Введите корректный ID задачи или 0 для выхода\"");
            }
        }
    }

    private static int inputNumberOfDay(Scanner scanner) {
        System.out.println("Введите количество дней");
        while (true) {
            try {
                int i = scanner.nextInt();
                if (i < 1 || i > 365) throw new RuntimeException();
                return i;
            } catch (RuntimeException e) {
                System.out.println("Что то пошло не так. Введите целое число от 1 до 365");
            }
        }
    }

    private static int inputPeriodical(Scanner scanner) {
        System.out.println("Укажите повторяемость:");
        printPeriodical();
        while (true) {
            try {
                int i = scanner.nextInt();
                if (i < 1 || i > 5) {
                    throw new RuntimeException();
                } else return i;
            } catch (RuntimeException e) {
                System.out.println("Вы ввели некоректное значение.");
                printPeriodical();
                System.out.println("Требуется цифра от 1 до 5.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("***");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Редактирровать задачу");
        System.out.println("4. Получить задачи на день");
        System.out.println("5. Получить задачи на несколько дней");
        System.out.println("6. Получить все задачи");
        System.out.println("7. Получить удаленные задачи");
        System.out.println("0. Выход");
        System.out.println("***");
    }

    private static void printPeriodical() {
        System.out.println("1 - однократная");
        System.out.println("2 - ежедневная");
        System.out.println("3 - еженедельная");
        System.out.println("4 - ежемесячная");
        System.out.println("5 - ежегодная");
    }
}

