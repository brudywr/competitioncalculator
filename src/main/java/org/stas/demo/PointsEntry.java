package org.stas.demo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * contains calculated points entry that corresponds to one contestant
 */
@XmlRootElement(name = "contestant")
public class PointsEntry {
    @XmlAttribute(name = "name")
    private String name;
    @XmlElementWrapper(name="events")
    private Map<String, Double> events;
    @XmlAttribute(name = "total points")
    private int totalPoints;
    @XmlAttribute(name = "position")
    private int position;

    public PointsEntry() {

    }

    public PointsEntry(String name, Map<Event, Double> events, int totalPoints, int position) {
        this.name = name;
        Map<String, Double> mapWithCaptions = new LinkedHashMap<>();
        for (Map.Entry<Event, Double> eventEntry : events.entrySet()) {
            mapWithCaptions.put(eventEntry.getKey().toString(), eventEntry.getValue());
        }
        this.events = mapWithCaptions;
        this.totalPoints = totalPoints;
        this.position = position;
    }

    /**
     * contestant's name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * raw results
     * @return
     */
    public Map<String, Double> getEvents() {
        return events;
    }

    /**
     * calculated total points value
     * @return
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * calculated position
     * @return
     */
    @XmlTransient
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name: ").append(name)
                .append(", total points: ").append(totalPoints)
                .append(", position: ").append(position);
        return stringBuilder.toString();
    }
}
