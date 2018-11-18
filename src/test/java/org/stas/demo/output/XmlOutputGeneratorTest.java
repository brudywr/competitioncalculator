package org.stas.demo.output;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.stas.demo.CompetitionType;
import org.stas.demo.Event;
import org.stas.demo.PointsEntry;
import org.stas.demo.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class XmlOutputGeneratorTest {

    private static final String TEST_XML_OUTPUT_CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<contestants>\n    <contestant name=\"test\" total points=\"100\" position=\"1\">\n        <events>\n  " +
            "          <entry>\n                <key>100m race</key>\n                <value>10.0</value>\n            </entry>\n" +
            "            <entry>\n                <key>long jump</key>\n                <value>10.0</value>\n            </entry>\n" +
            "            <entry>\n                <key>1500m race</key>\n                <value>10.0</value>\n            </entry>\n" +
            "            <entry>\n                <key>400m race</key>\n                <value>10.0</value>\n            </entry>\n" +
            "            <entry>\n                <key>discus throw</key>\n                <value>10.0</value>\n            </entry>\n" +
            "            <entry>\n                <key>110m hurdles race</key>\n                <value>10.0</value>\n            </entry>\n" +
            "            <entry>\n                <key>javelin throw</key>\n                <value>10.0</value>\n            </entry>\n" +
            "            <entry>\n                <key>pole vault</key>\n                <value>10.0</value>\n            </entry>\n" +
            "            <entry>\n                <key>short put</key>\n                <value>10.0</value>\n            </entry>\n" +
            "            <entry>\n                <key>high jump</key>\n                <value>10.0</value>\n            </entry>\n" +
            "        </events>\n    </contestant>\n</contestants>\n";

    private Configuration configuration;
    private Map<Event, Double> eventsMap;

    @Before
    public void setUp() {
        configuration = new Configuration(CompetitionType.DECATHLON);
        eventsMap = new LinkedHashMap<>();
    }

    @Test
    public void shouldGenerateXml() throws IOException {
        configuration.setOutputPath("test.xml");
        XmlOutputGenerator generator = new XmlOutputGenerator(configuration);

        eventsMap.put(Event.SHORT_SPRINT_RACE, 10.0);
        eventsMap.put(Event.LONG_JUMP, 10.0);
        eventsMap.put(Event.MIDDLE_RACE, 10.0);
        eventsMap.put(Event.LONG_SPRINT, 10.0);
        eventsMap.put(Event.DISCUS_THROW, 10.0);
        eventsMap.put(Event.HURDLES_RACE, 10.0);
        eventsMap.put(Event.JAVELIN_THROW, 10.0);
        eventsMap.put(Event.POLE_VAULT, 10.0);
        eventsMap.put(Event.SHORT_PUT, 10.0);
        eventsMap.put(Event.HIGH_JUMP, 10.0);

        Collection<PointsEntry> pointsEntries = Collections.singletonList(new PointsEntry("test", eventsMap, 100, 1));
        generator.writeResults(pointsEntries);

        File outputFile = new File("test.xml");
        assertThat(outputFile.exists(), equalTo(true));

        String outputContent = new String(Files.readAllBytes(outputFile.toPath()));
        assertThat(outputContent, equalTo(TEST_XML_OUTPUT_CONTENT));
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get("test.xml"));
    }
}
