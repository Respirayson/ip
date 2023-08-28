import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a Deadline.
 * Deadlines are tasks that need to be done
 * before a specific date/time.
 *
 * @author Rayson
 */
public class Deadline extends Task{

    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    @Override
    public String formatForStorage() {
        return String.format("D | %s | %s", super.formatForStorage(), this.deadline);
    }

    @Override
    public boolean checkSameDate(LocalDateTime date) {
        return this.deadline.equals(date);
    }
}
