import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Converter
{

    public static void main(String[] args)
    {
        try {

            String infile = args[0];
            String outfile = args[1];

            BufferedReader lineReader = new BufferedReader(
                new FileReader(infile)
            );

            XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = outputFactory.createXMLStreamWriter(
                new FileWriter(outfile)
            );

            convert(lineReader, xmlStreamWriter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void convert(BufferedReader in, XMLStreamWriter out)
        throws IOException, XMLStreamException
    {
        out.writeStartDocument();
        out.writeStartElement("people");

        boolean isWritingPerson = false;
        boolean isWritingFamily = false;

        String line;
        while ((line=in.readLine()) != null)
        {
            String[] parts = line.split("\\|");
            switch (parts[0])
            {
                case "P":
                    if (isWritingFamily) {
                        out.writeEndElement();
                    }

                    if (isWritingPerson) {
                        out.writeEndElement();
                    }

                    isWritingPerson = true;
                    isWritingFamily = false;

                    out.writeStartElement("person");

                    if (parts.length > 1)
                    {
                        out.writeStartElement("firstname");
                        out.writeCharacters(parts[1]);
                        out.writeEndElement();

                        if (parts.length > 2)
                        {
                            out.writeStartElement("lastname");
                            out.writeCharacters(parts[2]);
                            out.writeEndElement();
                        }
                    }
                    break;

                case "T":
                    out.writeStartElement("phone");

                    if (parts.length > 1)
                    {
                        out.writeStartElement("mobile");
                        out.writeCharacters(parts[1]);
                        out.writeEndElement();

                        if (parts.length > 2)
                        {
                            out.writeStartElement("landline");
                            out.writeCharacters(parts[2]);
                            out.writeEndElement();
                        }
                    }

                    out.writeEndElement();
                    break;

                case "A":
                    out.writeStartElement("address");

                    if (parts.length > 1)
                    {
                        out.writeStartElement("street");
                        out.writeCharacters(parts[1]);
                        out.writeEndElement();

                        if (parts.length > 2)
                        {
                            out.writeStartElement("city");
                            out.writeCharacters(parts[2]);
                            out.writeEndElement();
                        }

                        if (parts.length > 3)
                        {
                            out.writeStartElement("zipcode");
                            out.writeCharacters(parts[3]);
                            out.writeEndElement();
                        }
                    }

                    out.writeEndElement();
                    break;

                case "F":
                    if (isWritingFamily) {
                        out.writeEndElement();
                    }

                    isWritingFamily = true;
                    out.writeStartElement("family");

                    if (parts.length > 1)
                    {
                        out.writeStartElement("name");
                        out.writeCharacters(parts[1]);
                        out.writeEndElement();

                        if (parts.length > 2)
                        {
                            out.writeStartElement("born");
                            out.writeCharacters(parts[2]);
                            out.writeEndElement();
                        }
                    }
                    break;

            }
        }

        out.close();    // Writes necessary end tags
    }
}