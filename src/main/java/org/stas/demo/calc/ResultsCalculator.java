package org.stas.demo.calc;

import org.stas.demo.PointsEntry;
import org.stas.demo.RawResultEntry;

import java.util.Collection;

/**
 * final results calculation
 */
public interface ResultsCalculator {
    /**
     * calculates points from raw results entries
     * @param rawResultsEntries
     * @return collection of entries with points
     */
    Collection<PointsEntry> calculate(Collection<RawResultEntry> rawResultsEntries);
}
