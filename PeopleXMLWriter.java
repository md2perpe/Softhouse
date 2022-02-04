import java.io.FileWriter;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class PeopleXMLWriter {

    private XMLStreamWriter out;

    public PeopleXMLWriter(FileWriter outFile)
        throws XMLStreamException
    {
        out = XMLOutputFactory.newFactory().createXMLStreamWriter(outFile);
    }

    public void open()
        throws XMLStreamException
    {
        out.writeStartDocument();
        begin("people");
    }

    public void close()
        throws XMLStreamException
    {
        end();
        out.close();
    }

    private void begin(String tagName)
        throws XMLStreamException
    {
        out.writeStartElement(tagName);
    }

    private void end()
        throws XMLStreamException
    {
        out.writeEndElement();
    }

    private void writeElement(String tagName, String content)
        throws XMLStreamException
    {
        if (content != null) {
            begin(tagName);
            out.writeCharacters(content);
            end();
        }
    }

    public void writePerson(Person person)
        throws XMLStreamException
    {
        begin("person");
        writeElement("firstname", person.getFirstName());
        writeElement("lastname", person.getLastName());
        writeAddress(person.getAddress());
        writePhone(person.getPhone());
        writeFamilies(person.getFamilies());
        end();
    }

    public void writeAddress(Address address)
        throws XMLStreamException
    {
        if (address != null)
        {
            begin("address");
            writeElement("street", address.getStreet());
            writeElement("city", address.getCity());
            writeElement("zipcode", address.getZipCode());
            end();
        }
    }

    public void writePhone(Phone phone)
        throws XMLStreamException
    {
        if (phone != null)
        {
            begin("phone");
            writeElement("mobile", phone.getMobile());
            writeElement("landline", phone.getLandline());
            end();
        }
    }

    public void writeFamilies(List<Family> families)
        throws XMLStreamException
    {
        for (Family family : families) {
            writeFamily(family);
        }
    }

    public void writeFamily(Family family)
        throws XMLStreamException
    {
        begin("family");
        writeElement("name", family.getName());
        writeElement("born", family.getBorn());
        writePhone(family.getPhone());
        writeAddress(family.getAddress());
        end();
    }

}
