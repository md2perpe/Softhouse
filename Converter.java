import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class Converter
{
    private BufferedReader in;
    private XMLStreamWriter out;

    boolean isWritingPerson = false;
    boolean isWritingFamily = false;

    public Converter(FileReader inFile, FileWriter outFile)
        throws XMLStreamException
    {
        in = new BufferedReader(inFile);
        out = XMLOutputFactory.newFactory().createXMLStreamWriter(outFile);
    }

    void run()
        throws IOException, XMLStreamException
    {
        out.writeStartDocument();
        out.writeStartElement("people");

        String line;
        while ((line=in.readLine()) != null)
        {
            String[] parts = line.split("\\|");

            switch (parts[0])
            {
                case "P": handleP(parts); break;
                case "T": handleT(parts); break;
                case "A": handleA(parts); break;
                case "F": handleF(parts); break;
            }
        }

        out.close();    // Writes necessary end tags
    }


    // Helper method to map a line of data into XML elements
    // Note: First element of `parts` is not used.
    // Example:
    //     String[] parts = new String[] { "X", "Barack", "Obama" }
    //     String[] tagNames = new String[] { "firstname", "lastname" }
    //     writeParts(parts, tagNames)
    // gives
    //     <firstname>Barack</firstname>
    //     <lastname>Obama</lastname>
    private void writeParts(String[] parts, String[] tagNames)
        throws XMLStreamException
    {
        int i;
        for (i=0; i+1<parts.length && i<tagNames.length; i++) {
            out.writeStartElement(tagNames[i]);
            out.writeCharacters(parts[i+1]);
            out.writeEndElement();
        }
    }

    private void handleP(String[] parts) throws XMLStreamException {
        if (isWritingFamily) {
            out.writeEndElement();
        }

        if (isWritingPerson) {
            out.writeEndElement();
        }

        isWritingPerson = true;
        isWritingFamily = false;

        out.writeStartElement("person");
        writeParts(parts, new String[] { "firstname", "lastname" });
    }

    private void handleT(String[] parts) throws XMLStreamException {
        if (parts.length < 2)
            return;

        out.writeStartElement("phone");
        writeParts(parts, new String[] { "mobile", "landline" });
        out.writeEndElement();
    }

    private void handleA(String[] parts) throws XMLStreamException {
        if (parts.length < 2)
            return;

        out.writeStartElement("address");
        writeParts(parts, new String[] { "street", "city", "zipcode" });
        out.writeEndElement();
    }

    private void handleF(String[] parts) throws XMLStreamException {
        if (isWritingFamily) {
            out.writeEndElement();
        }

        isWritingFamily = true;
        out.writeStartElement("family");
        writeParts(parts, new String[] {"name", "born" });
    }


    public static void main(String[] args)
    {
        try {
            Converter converter = new Converter(
                new FileReader(args[0]),
                new FileWriter(args[1])
            );
            converter.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}