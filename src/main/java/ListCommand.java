public class ListCommand extends Command {

    public ListCommand() {
        super("");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskListException {
        if (tasks.size() == 0) {
            throw new EmptyTaskListException();
        }
        ui.printTasks(tasks);
    }
}
