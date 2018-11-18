package org.stas.demo.config;

/**
 * covers different configuration strategies
 */
public interface ConfigurationHandler {

    /**
     * initializes the application basing on given arguments
     * @param args
     * @return
     */
    Configuration init(String[] args);
}
