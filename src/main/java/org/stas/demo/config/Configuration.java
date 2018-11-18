package org.stas.demo.config;

import org.stas.demo.CompetitionType;

/**
 * structure storing any configuration propeeties
 */
public class Configuration {
    private String inputPath;
    private String outputPath;
    private CompetitionType competitionType;

    public Configuration(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }

    public Configuration(CompetitionType competitionType, String inputPath) {
        this.competitionType = competitionType;
        this.inputPath = inputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}
