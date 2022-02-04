import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import javax.xml.stream.XMLStreamException;


public class Converter
{
    Path inPath;
    Path outPath;

    public Converter(Path inPath, Path outPath)
    {
        this.inPath = inPath;
        this.outPath = outPath;
    }

    public void run()
        throws IOException, XMLStreamException
    {
        Stream<String> rowIterable = Files.lines(inPath);
        RowFormatParser parser = new RowFormatParser(rowIterable);

        PeopleXMLWriter writer = new PeopleXMLWriter(
            new FileWriter(outPath.toString())
        );

        writer.open();
        Person person;
        while ( (person=parser.parsePerson()) != null ) {
            writer.writePerson(person);
        }
        writer.close();
    }


    public static void main(String[] args)
    {
        try {
            Converter converter = new Converter(
                Path.of(args[0]),
                Path.of(args[1])
            );
            converter.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}