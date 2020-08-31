package duke.command;

import duke.Storage;
import duke.task.Tasks;
import duke.ui.Ui;

/**
 * The Bye command which terminates the program.
 */
public class ByeCommand extends Command {
    /**
     * Instantiates a new Bye command.
     */
    public ByeCommand() {
        this.commandType = CommandType.BYE;
    }

    /**
     * Returns a response consisting exit message.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @return the response to bye command.
     */
    @Override
    public CommandResponse execute(Tasks tasks, Ui ui, Storage storage) {
        return new CommandResponse(ui.getExitMessage(), this.isExit());
    }
}
