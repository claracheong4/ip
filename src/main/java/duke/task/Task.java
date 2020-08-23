package duke.task;

import duke.exception.ReadFailedException;

import java.time.LocalDate;

import java.util.function.Function;

public class Task {
    protected String description;
    protected boolean isDone;
    protected final TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }
    
    public boolean hasDate() {
        return this.type != TaskType.TODO;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static Task createTask(String[] stringArr) throws ReadFailedException {
        Function<String, Boolean> isDone = num -> num.equals("1");
        Function<String, LocalDate> toDate = date -> LocalDate.parse(date.trim());

        switch (stringArr[0]) {
            case "T":
                return new Todo(stringArr[2], isDone.apply(stringArr[0]));
            case "E":
                return new Event(stringArr[2], toDate.apply(stringArr[3]), isDone.apply(stringArr[0]));
            case "D":
                return new Deadline(stringArr[2], toDate.apply(stringArr[3]), isDone.apply(stringArr[0]));
            default:
                throw new ReadFailedException("tasks");
        }
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); // return tick or cross symbols
    }

    public String getData() {
        return String.format("%s_%s_%s", type.toString().charAt(0), isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[%S][%s] %s", type.toString().charAt(0), this.getStatusIcon(), this.description);
    }
}
