import java.io.IOException;

public class DeleteCommand extends Command {
    private final CommandType commandType;
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.commandType = CommandType.DELETE;
        this.taskIndex = taskIndex;
    }

    public void execute(Tasks tasks, Ui ui, Storage storage) throws InvalidTaskNumberException, SaveTaskFailedException {
        try {
            Task task = tasks.getTask(taskIndex);
            tasks.removeTask(taskIndex);
            storage.updateTasks(tasks);
            ui.printDeleteTask(task, tasks.getSize());
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskNumberException("The task to be deleted does not exist!");
        } catch (IOException ex) {
            throw new SaveTaskFailedException(taskIndex);
        }
    }
}
