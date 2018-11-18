package org.stas.demo.config;

public class ConfigurationHandlerFactory {

    public static ConfigurationHandler createConfigurationHandler() {

        return new DefaultConfigurationHandler();
    }
}
