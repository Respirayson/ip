/**
 * Encapsulates a Task in the Chat bot.
 *
 * @author Rayson
 */
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getStatus() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return String.format("%s | %s", getStatus(), this.description);
    }

}
