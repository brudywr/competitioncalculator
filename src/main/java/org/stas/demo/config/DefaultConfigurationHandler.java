package org.stas.demo.config;

import org.stas.demo.CompetitionType;

/**
 * basic and simple cmd based configuration handler implementation
 */
class DefaultConfigurationHandler implements ConfigurationHandler {

    private static final String DEFAULT_OUTPUT_FILE = "results.xml";

    @Override
    public Configuration init(String[] arguments) {
        Configuration configuration = new Configuration(CompetitionType.DECATHLON);
        configuration.setInputPath(arguments[0]);
        configuration.setOutputPath(arguments.length > 1 ? arguments[1] : DEFAULT_OUTPUT_FILE);

        System.out.println("configured input file: " + configuration.getInputPath());
        System.out.println("configured output file: " + configuration.getOutputPath());
        System.out.println("configured competition type: " + configuration.getCompetitionType().toString());
        return configuration;
    }
}
