package org.stas.demo.output;

import org.stas.demo.config.Configuration;

import static org.stas.demo.Utils.getFileExtension;

public class OutputGeneratorFactory {

    public static OutputGenerator createOutputHandler(Configuration configuration) {
        String outputFilePath = configuration.getOutputPath();
        String fileExtension = getFileExtension(outputFilePath);

        switch (fileExtension) {
            default: return new XmlOutputGenerator(configuration);
        }
    }
}
