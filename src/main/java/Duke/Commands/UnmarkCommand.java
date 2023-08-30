package Duke.Commands;

import Duke.Exceptions.NoIndexException;
import Duke.Tools.Storage;
import Duke.Tasks.Task;
import Duke.Tools.TaskList;
import Duke.Tools.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs an UnmarkCommand with the given full command string.
     *
     * @param fullCommand The full command string.
     */
    public UnmarkCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Marks a task as not done.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler (not used in this command).
     * @throws NoIndexException If the provided index is invalid or missing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoIndexException {
        String desc = this.fullCommand.replaceAll("[^0-9]", "");
        if (desc.equals("")) {
            throw new NoIndexException("No Index");
        }
        int index = Integer.parseInt(desc);
        if (tasks.size() < index || index < 1) {
            throw new NoIndexException(Integer.toString(index));
        }

        Task task = tasks.get(index - 1);
        task.markUndone();
        ui.showUnmarkMessage(task);
    }
}
