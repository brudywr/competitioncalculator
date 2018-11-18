package org.stas.demo.input;

import org.junit.Test;
import org.stas.demo.CompetitionType;
import org.stas.demo.RawResultEntry;
import org.stas.demo.config.Configuration;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CsvInputHandlerTest {

    private static final String DEFAULT_RESULTS_CSV_RAW_DATA = "[name: John Smith, events: {100m race=12.61, long jump=5.0, short put=9.22, high jump=1.5, 400m race=60.39, 110m hurdles race=16.43, discus throw=21.6, pole vault=2.6, javelin throw=35.81, 1500m race=325.72}, name: Jane Doe, events: {100m race=13.04, long jump=4.53, short put=7.79, high jump=1.55, 400m race=64.72, 110m hurdles race=18.74, discus throw=24.2, pole vault=2.4, javelin throw=28.2, 1500m race=410.76}, name: Jaan Lepp, events: {100m race=13.75, long jump=4.84, short put=10.12, high jump=1.5, 400m race=68.44, 110m hurdles race=19.18, discus throw=30.85, pole vault=2.8, javelin throw=33.88, 1500m race=382.75}, name: Foo Bar, events: {100m race=13.43, long jump=4.35, short put=8.64, high jump=1.5, 400m race=66.06, 110m hurdles race=19.05, discus throw=24.89, pole vault=2.2, javelin throw=33.48, 1500m race=411.01}]";

    @Test
    public void shouldLoadRawDataFromFile() {
        CsvInputHandler handler = new CsvInputHandler(new Configuration(CompetitionType.DECATHLON, "src/test/resources/results.csv"));
        Collection<RawResultEntry> rawData = handler.loadRawData();
        assertThat(rawData.toString(), equalTo(DEFAULT_RESULTS_CSV_RAW_DATA));
    }

    @Test
    public void shouldNotLoadRawDataFromNonExistingFile() {
        CsvInputHandler handler = new CsvInputHandler(new Configuration(CompetitionType.DECATHLON, "results!!!.csv"));
        Collection<RawResultEntry> rawData = handler.loadRawData();
        assertThat(rawData.toString(), equalTo("[]"));
    }

    @Test
    public void shouldHandleWrongNumericRawData() {
        CsvInputHandler handler = new CsvInputHandler(new Configuration(CompetitionType.DECATHLON, ""));
        Collection<String> rawDataEntry = Collections.singletonList("John Smith;a12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72 ");
        assertThat(handler.extractRawData(rawDataEntry).toString(), equalTo("[name: John Smith, events: {100m race=-1.0, long jump=5.0, short put=9.22, high jump=1.5, 400m race=60.39, 110m hurdles race=16.43, discus throw=21.6, pole vault=2.6, javelin throw=35.81, 1500m race=325.72}]"));
    }

    @Test
    public void shouldHandleWrongNumberOfEventsInRawData() {
        CsvInputHandler handler = new CsvInputHandler(new Configuration(CompetitionType.DECATHLON, ""));
        Collection<String> rawDataEntry = Collections.singletonList("John Smith;12.61;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72 ");
        assertThat(handler.extractRawData(rawDataEntry).toString(), equalTo("[name: John Smith, events: {100m race=12.61, long jump=9.22, short put=1.5, high jump=60.39, 400m race=16.43, 110m hurdles race=21.6, discus throw=2.6, pole vault=35.81, javelin throw=325.72}]"));
    }
}
