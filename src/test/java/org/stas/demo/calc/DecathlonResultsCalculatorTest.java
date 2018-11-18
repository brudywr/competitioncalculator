package org.stas.demo.calc;

import org.junit.Before;
import org.junit.Test;
import org.stas.demo.Event;
import org.stas.demo.PointsEntry;
import org.stas.demo.RawResultEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DecathlonResultsCalculatorTest {

    private Collection<RawResultEntry> rawData;

    @Before
    public void setUp() {
        rawData = new ArrayList<>();
    }

    @Test
    public void shouldCalculateEvenWithOneEvent() {
        rawData.add(new RawResultEntry("test1", Collections.singletonMap(Event.SHORT_SPRINT_RACE, 10.0)));

        DecathlonResultsCalculator calculator = new DecathlonResultsCalculator();
        Collection<PointsEntry> points = calculator.calculate(rawData);

        assertThat(points.toString(), equalTo("[name: test1, total points: 1097, position: 1]"));
    }

    @Test
    public void shouldCalculatePositions() {
        rawData.add(new RawResultEntry("test1", Collections.singletonMap(Event.SHORT_SPRINT_RACE, 10.0)));
        rawData.add(new RawResultEntry("test2", Collections.singletonMap(Event.SHORT_SPRINT_RACE, 12.0)));

        DecathlonResultsCalculator calculator = new DecathlonResultsCalculator();
        Collection<PointsEntry> points = calculator.calculate(rawData);

        assertThat(points.toString(), equalTo("[name: test1, total points: 1097, position: 1, name: test2, total points: 651, position: 2]"));
    }

    @Test
    public void shouldHandleSamePositions() {
        rawData.add(new RawResultEntry("test1", Collections.singletonMap(Event.SHORT_SPRINT_RACE, 10.0)));
        rawData.add(new RawResultEntry("test2", Collections.singletonMap(Event.SHORT_SPRINT_RACE, 12.0)));
        rawData.add(new RawResultEntry("test3", Collections.singletonMap(Event.SHORT_SPRINT_RACE, 12.0)));
        rawData.add(new RawResultEntry("test4", Collections.singletonMap(Event.SHORT_SPRINT_RACE, 11.0)));

        DecathlonResultsCalculator calculator = new DecathlonResultsCalculator();
        Collection<PointsEntry> points = calculator.calculate(rawData);

        assertThat(points.toString(), equalTo("[name: test1, total points: 1097, position: 1, name: test4, total points: 861, position: 2, name: test2, total points: 651, position: 3, name: test3, total points: 651, position: 3]"));
    }
}
