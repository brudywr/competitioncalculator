package org.stas.demo.output;

import org.stas.demo.PointsEntry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 * a wrapper for all points entries for more convenient output generating
 */
@XmlRootElement(name = "contestants")
class PointsEntries {

    @XmlElement(name = "contestant")
    private Collection<PointsEntry> pointsEntries;

    void setPointsEntries(Collection<PointsEntry> pointsEntries) {
        this.pointsEntries = pointsEntries;
    }
}
