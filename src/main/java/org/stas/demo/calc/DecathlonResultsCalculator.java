package org.stas.demo.calc;

import org.stas.demo.*;

import java.util.*;

import static org.stas.demo.Event.*;

/**
 * Men's Decathlon results calculator implementation
 */
class DecathlonResultsCalculator extends AbstractResultsCalculator {

    static private Map<Event, PointCalculator> eventsCalculators = new LinkedHashMap<>();
    static {
        eventsCalculators.put(SHORT_SPRINT_RACE, new TrackPointCalculator(25.4347, 18.0, 1.81));
        eventsCalculators.put(LONG_JUMP, new FieldPointCalculator(0.14354, 220.0, 1.4));
        eventsCalculators.put(SHORT_PUT, new FieldPointCalculator(51.39, 1.5, 1.05));
        eventsCalculators.put(HIGH_JUMP, new FieldPointCalculator(0.8465, 75.0, 1.42));
        eventsCalculators.put(LONG_SPRINT, new TrackPointCalculator(1.53775, 82.0, 1.81));
        eventsCalculators.put(HURDLES_RACE, new TrackPointCalculator(5.74352, 28.5, 1.92));
        eventsCalculators.put(DISCUS_THROW, new FieldPointCalculator(12.91, 4.0, 1.1));
        eventsCalculators.put(POLE_VAULT, new FieldPointCalculator(0.2797, 100.0, 1.35));
        eventsCalculators.put(JAVELIN_THROW, new FieldPointCalculator(10.14, 7.0, 1.08));
        eventsCalculators.put(MIDDLE_RACE, new TrackPointCalculator(0.03768, 480.0, 1.85));
    }

    @Override
    protected PointCalculator getEventCalculator(Event event) {
        return eventsCalculators.get(event);
    }

    @Override
    protected String getCalculationName() {
        return "decahlon competition";
    }

    private static abstract class AbstractPointCalculator implements PointCalculator {
        double[] coefficients;

        AbstractPointCalculator(double[] coefficients) {
            this.coefficients = coefficients;
        }
    }

    /**
     * implements point calculation for track events (time related competitions)
     */
    private static class TrackPointCalculator extends AbstractPointCalculator {
        TrackPointCalculator(double... coefficients) {
            super(coefficients);
        }

        @Override
        public int calculate(double rawResult) {
            return (int) Math.round(coefficients[0] * Math.pow(coefficients[1] - rawResult, coefficients[2]));
        }
    }
    /**
     * implements point calculation for field events (distance related competitions)
     */
    private static class FieldPointCalculator extends AbstractPointCalculator  {
        FieldPointCalculator(double... coefficients) {
            super(coefficients);
        }

        @Override
        public int calculate(double rawResult) {
            return (int) Math.round(coefficients[0] * Math.pow(rawResult - coefficients[1], coefficients[2]));
        }
    }
}
