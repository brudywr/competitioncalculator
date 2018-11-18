package org.stas.demo.output;

import org.stas.demo.PointsEntry;
import org.stas.demo.RawResultEntry;

import java.util.Collection;

/**
 * writes down calculated results to some external places
 */
public interface OutputGenerator {
    /**
     * writes down calculated points
     * @param pointsEntries
     */
    void writeResults(Collection<PointsEntry> pointsEntries);
}
