/**
 * Encapsulates an Event.
 * Tasks that start at a specific date/time and
 * ends at a specific date/time.
 *
 * @author Rayson
 */
public class Event extends Task {

    String start;
    String deadline;

    public Event(String description, String start, String deadline) {
        super(description);
        this.start = start;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.deadline);
    }

}
