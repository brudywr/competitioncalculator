package org.stas.demo.input;

import org.stas.demo.RawResultEntry;
import org.stas.demo.config.Configuration;

import java.util.Collection;

/**
 * handles loading raw data
 */
public interface InputHandler {
    /**
     * fetches some raw data and load them into collection
     * @return
     */
    Collection<RawResultEntry> loadRawData();
}
