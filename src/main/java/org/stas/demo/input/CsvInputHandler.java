package org.stas.demo.input;

import org.stas.demo.CompetitionType;
import org.stas.demo.Event;
import org.stas.demo.RawResultEntry;
import org.stas.demo.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * csv file based implementation of input handler
 */
class CsvInputHandler implements InputHandler {

    private static final String CSV_DELIMITER = ";";
    private static final String TIME_WITH_MINUTES = "\\d+\\.\\d+\\.\\d+";
    private static final int SECONDS_IN_MINUTE = 60;
    private static final String TIME_PART_DELIMITER = ".";
    private static final double WRONG_VALUE_TOKEN = -1.0;

    private Configuration configuration;

    CsvInputHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Collection<RawResultEntry> loadRawData() {
        System.out.println("starting csv input file loading: " + configuration.getInputPath());

        Collection<RawResultEntry> rawResultEntries = new LinkedList<>();
        Collection<String> fileLines = loadCsvFile(configuration.getInputPath());
        if (!fileLines.isEmpty()) {
            rawResultEntries = extractRawData(fileLines);
        } else {
            System.err.println(configuration.getInputPath() + " file doesn't exist or empty");
        }
        return rawResultEntries;
    }

    /**
     * fetches data from csv file into collection of lines
     * @param fileName
     * @return
     */
    private Collection<String> loadCsvFile(String fileName) {
        File inputFile = new File(fileName);

        Collection<String> fileLines = new LinkedList<>();
        if (inputFile.exists()) {
            System.out.println(fileName + " exists");
            try {
                fileLines = Files.readAllLines(inputFile.toPath());
            } catch (IOException e) {
                System.out.println("some error occurred while reading input file: " + fileName);
                e.printStackTrace();
            }
        }
        return fileLines;
    }

    /**
     * extract raw data from csv file lines into collection of raw result entries
     * @param lines
     * @return
     */
    Collection<RawResultEntry> extractRawData(Collection<String> lines) {
        Collection<RawResultEntry> rawResultEntries = new LinkedList<>();
        Event[] eventsOrder = CompetitionType.getEventsOrder(configuration.getCompetitionType());
        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            String[] lineParts = line.split(CSV_DELIMITER);
            String name = lineParts[0];
            Map<Event, Double> events = new LinkedHashMap<>();
            for (int index = 1; index < lineParts.length; index++) {
                String linePart = lineParts[index].trim();

                try {
                    if (linePart.matches(TIME_WITH_MINUTES)) {
                        events.put(eventsOrder[index - 1], calculateTimeWithMinutes(linePart));
                    } else {
                        events.put(eventsOrder[index - 1], Double.parseDouble(linePart));
                    }
                } catch (NumberFormatException e) {
                    System.err.println("raw input data for contestant " + name + " contains wrong numeric value: " +
                            linePart);
                    events.put(eventsOrder[index - 1], WRONG_VALUE_TOKEN);
                }
            }
            rawResultEntries.add(new RawResultEntry(name, events));
        }
        return rawResultEntries;
    }

    /**
     * extracts event value from a format "minutes.seconds.milliseconds" and converts it to "seconds.milliseconds"
     *
     * @param linePart
     * @return
     */
    private Double calculateTimeWithMinutes(String linePart) {
        String minutes = linePart.substring(0, linePart.indexOf(TIME_PART_DELIMITER));
        String seconds = linePart.substring(linePart.indexOf(TIME_PART_DELIMITER) + 1);
        return Integer.parseInt(minutes) * SECONDS_IN_MINUTE + Double.parseDouble(seconds);
    }
}
