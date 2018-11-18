package org.stas.demo.input;

import org.stas.demo.config.Configuration;

import static org.stas.demo.Utils.getFileExtension;

public class InputHandlerFactory {



    public static InputHandler createInputHandler(Configuration configuration) {
        String inputFilePath = configuration.getInputPath();
        String fileExtension = getFileExtension(inputFilePath);

        switch (fileExtension) {
            default: return new CsvInputHandler(configuration);
        }
    }
}
