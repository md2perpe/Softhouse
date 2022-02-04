import java.util.stream.Stream;

public class RowFormatParser {

    private ConditionalFilter<String> lineReader;

    public RowFormatParser(Stream<String> stream)
    {
        this.lineReader = new ConditionalFilter<String>(stream.iterator());
    }


    private class NullExtendedArray<T> {
        private T[] wrapped;

        public NullExtendedArray(T[] wrapped) {
            this.wrapped = wrapped;
        }

        public T at(int i) {
            if (i >= wrapped.length) {
                return null;
            }

            return wrapped[i];
        }
    }

    private NullExtendedArray<String> split(String line)
    {
        return new NullExtendedArray<>(line.split("\\|"));
    }


    public Person parsePerson()
    {
        String line = lineReader.nextIf(row -> row.startsWith("P"));
        if (line == null) {
            return null;
        }

        NullExtendedArray<String> parts = split(line);
        Person person = new Person(parts.at(1), parts.at(2));

        person.setPhone(parsePhone());
        person.setAddress(parseAddress());

        Family family;
        while ( (family=parseFamily()) != null) {
            person.addFamily(family);
        }

        return person;
    }

    public Phone parsePhone()
    {
        String line = lineReader.nextIf(row -> row.startsWith("T"));
        if (line == null) {
            return null;
        }

        NullExtendedArray<String> parts = split(line);
        Phone phone = new Phone(parts.at(1), parts.at(2));

        return phone;
    }

    public Address parseAddress()
    {
        String line = lineReader.nextIf(row -> row.startsWith("A"));
        if (line == null) {
            return null;
        }

        NullExtendedArray<String> parts = split(line);
        Address address = new Address(parts.at(1), parts.at(2), parts.at(3));

        return address;
    }

    public Family parseFamily()
    {
        String line = lineReader.nextIf(row -> row.startsWith("F"));
        if (line == null) {
            return null;
        }

        NullExtendedArray<String> parts = split(line);
        Family family = new Family(parts.at(1), parts.at(2));

        family.setPhone(parsePhone());
        family.setAddress(parseAddress());

        return family;
    }
}
