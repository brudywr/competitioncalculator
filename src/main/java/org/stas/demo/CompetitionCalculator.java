package org.stas.demo;

import org.stas.demo.config.Configuration;

import java.util.Collection;

import static org.stas.demo.calc.ResultsCalculatorFactory.createResultsCalculator;
import static org.stas.demo.config.ConfigurationHandlerFactory.createConfigurationHandler;
import static org.stas.demo.input.InputHandlerFactory.createInputHandler;
import static org.stas.demo.output.OutputGeneratorFactory.createOutputHandler;

class CompetitionCalculator {

    void run(String[] args) {
        Configuration configuration = createConfigurationHandler().init(args);
        Collection<RawResultEntry> rawResults = createInputHandler(configuration).loadRawData();
        if (rawResults.isEmpty()) {
            System.err.println("nothing to calculate");
            return;
        }
        Collection<PointsEntry> calculatedResults = createResultsCalculator(configuration).calculate(rawResults);
        createOutputHandler(configuration).writeResults(calculatedResults);
    }
}
