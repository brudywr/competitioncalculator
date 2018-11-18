package org.stas.demo.calc;

import org.stas.demo.config.Configuration;

public class ResultsCalculatorFactory {

    public static ResultsCalculator createResultsCalculator(Configuration configuration) {
        switch (configuration.getCompetitionType()) {
            default: return new DecathlonResultsCalculator();
        }
    }
}
