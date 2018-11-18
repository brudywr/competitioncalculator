package org.stas.demo.output;

import org.stas.demo.PointsEntry;
import org.stas.demo.config.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Collection;

/**
 * xml file based implementation of output generator - it generates xml files with calculated results
 */
class XmlOutputGenerator implements OutputGenerator {

    private Configuration configuration;

    XmlOutputGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void writeResults(Collection<PointsEntry> pointsEntries) {
        System.out.println("writing calculated points results to XML file: " + configuration.getOutputPath());
        PointsEntries pointsEntriesList = new PointsEntries();
        pointsEntriesList.setPointsEntries(pointsEntries);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PointsEntries.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(pointsEntriesList, new File(configuration.getOutputPath()));
            System.out.println("all data has been written successfully");
        } catch (JAXBException e) {
            System.err.println("an error occurred while writing xml document" + configuration.getOutputPath());
            e.printStackTrace();
        }
    }
}
