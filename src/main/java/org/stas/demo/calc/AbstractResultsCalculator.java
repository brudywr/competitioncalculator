package org.stas.demo.calc;

import org.stas.demo.Event;
import org.stas.demo.PointsEntry;
import org.stas.demo.RawResultEntry;

import java.util.*;
import java.util.stream.Collectors;

abstract class AbstractResultsCalculator implements ResultsCalculator {

    protected abstract PointCalculator getEventCalculator(Event event);
    protected abstract String getCalculationName();

    @Override
    public Collection<PointsEntry> calculate(Collection<RawResultEntry> rawResultsEntries) {
        System.out.println("starting calculating points for " + getCalculationName());
        List<PointsEntry> calculatedResults = new ArrayList<>();
        for (RawResultEntry rawResultEntry : rawResultsEntries) {
            System.out.println("calculating data for a contestant: " + rawResultEntry.getName());
            Map<Event, Double> events = rawResultEntry.getEvents();
            int accumulatedPoints = 0;
            for (Map.Entry<Event, Double> eventEntry : events.entrySet()) {
                Event event = eventEntry.getKey();
                Double rawResult = eventEntry.getValue();

                PointCalculator calculator = getEventCalculator(event);
                if (calculator != null) {
                    accumulatedPoints += calculator.calculate(rawResult);
                }
            }
            PointsEntry entry = new PointsEntry(rawResultEntry.getName(), events, accumulatedPoints, 0);
            calculatedResults.add(entry);
            System.out.println("calculated points: " + accumulatedPoints);
        }

        sortResults(calculatedResults);

        return calculatedResults;
    }

    private void sortResults(List<PointsEntry> calculatedResults) {
        calculatedResults.sort(Comparator.comparing(PointsEntry::getTotalPoints).reversed());
        Map<Integer, List<PointsEntry>> sortedPointsEntries = new TreeMap<>(Collections.reverseOrder());
        sortedPointsEntries.putAll(calculatedResults.stream().collect(Collectors.groupingBy(
                PointsEntry::getTotalPoints, Collectors.toList())));

        int position = 1;
        for (List<PointsEntry> equalPointsEntries : sortedPointsEntries.values()) {
            for (PointsEntry pointsEntry : equalPointsEntries) {
                pointsEntry.setPosition(position);
            }
            position++;
        }
    }
}
