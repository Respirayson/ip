package duke.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.UnrecognisedFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Handles loading tasks from storage and saving tasks to storage.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from storage.
     * @throws IOException If an I/O error occurs.
     */
    public List<Task> loadTasksFromStorage() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        assert file.exists() : "Storage file does not exist";

        // scan for the storage file
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {

                String curr = s.nextLine();
                assert curr != null;

                String[] segments = curr.split(" \\| ");

                // check for the correct format - minimum 3 different segments
                if ((!segments[0].equals("T") && !segments[0].equals("D")
                        && !segments[0].equals("E")) || segments.length < 3) {
                    s.close(); // need to close scanner otherwise cannot replace file
                    throw new UnrecognisedFormatException();
                }

                boolean isDone = segments[1].equals("1");

                if (segments[0].equals("T")) { // To do task
                    tasks.add(new ToDo(segments[2], isDone));
                } else if (segments[0].equals("D")) { // Duke.Tasks.Deadline task
                    tasks.add(new Deadline(segments[2], LocalDateTime.parse(segments[3]), isDone));
                } else { // Duke.Tasks.Event task
                    String[] times = segments[3].split("--");

                    assert times.length == 2 : "Format is not correct";

                    tasks.add(new Event(segments[2],
                            LocalDateTime.parse(times[0]),
                            LocalDateTime.parse(times[1]),
                            isDone));
                }

            }
        } catch (FileNotFoundException e) { // File does not exist
            try {
                if (new File(filePath.split("/")[0]).mkdir()) {
                    System.out.println("Sorry, directory does not exist. Creating now...");
                }
                if (file.createNewFile()) {
                    System.out.println("Sorry, file does not exist. Creating now...");
                }
            } catch (Exception error) {
                System.out.println(error.getMessage());
                System.out.println("Error... Unable to create files");
            }

        } catch (UnrecognisedFormatException e) { // File is corrupted
            try {
                if (file.delete()) {
                    System.out.println("Deleting corrupted file...");

                    if (file.createNewFile()) {
                        System.out.println("Replacing file now...");
                    }

                }
            } catch (Exception error) {
                System.out.println("Error... Unable to create new file...");
            }
        }
        return tasks;
    }

    /**
     * Writes the provided text to the storage file.
     *
     * @param text The text to be written to the storage file.
     */
    public void writeFile(String text) {

        assert text != null : "Text to be written is null";

        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(text);
            fw.close();
        } catch (Exception e) {
            System.out.println("Sorry... Unable to store tasks...");
        }

    }
}
