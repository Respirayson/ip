package Duke.Tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    private Event event;

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .optionalStart().appendPattern(" HH:mm").optionalEnd()
            .optionalStart().appendPattern(" HHmm").optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();


    @BeforeEach
    public void init() {
        this.event = new Event("do cs2103t",
                LocalDateTime.parse("2023-08-30 22:19", FORMATTER),
                LocalDateTime.parse("2023-09-20", FORMATTER));
    }

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] do cs2103t (from: Aug 30 2023 22:19 to Sep 20 2023 00:00)", this.event.toString());
    }

    @Test
    public void testStorageFileConversion() {
        assertEquals("E | 0 | do cs2103t | 2023-08-30T22:19--2023-09-20T00:00", this.event.formatForStorage());
    }

    @Test
    public void testMarkDone() {
        this.event.markDone();
        assertEquals("[E][X] do cs2103t (from: Aug 30 2023 22:19 to Sep 20 2023 00:00)", this.event.toString());
    }

    @Test
    public void testWithinDateRange_success() {
        assertTrue(this.event.isWithinDateRange(LocalDateTime.parse("2023-09-02", FORMATTER)));
    }

    @Test
    public void testWithinDateRange_failure() {
        assertFalse(this.event.isWithinDateRange(LocalDateTime.parse("2023-01-01", FORMATTER)));
    }

}
