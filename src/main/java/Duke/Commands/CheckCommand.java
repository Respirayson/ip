package Duke.Commands;

import Duke.Tools.Storage;
import Duke.Tools.TaskList;
import Duke.Tools.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command to check tasks scheduled on a specific date and time.
 */
public class CheckCommand extends Command {

    private final DateTimeFormatter formatter;

    /**
     * Constructs a CheckCommand with the specified command and date-time formatter.
     *
     * @param fullCommand The full command string.
     * @param formatter   The date-time formatter to parse date and time inputs.
     */
    public CheckCommand(String fullCommand, DateTimeFormatter formatter) {
        super(fullCommand);
        this.formatter = formatter;
    }

    /**
     * Executes the check command, displaying tasks scheduled on the specified date and time.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String content = this.fullCommand.replaceAll("^\\s*check\\s*", "");
        ui.printScheduledTasks(tasks, LocalDateTime.parse(content, this.formatter));
    }
}
