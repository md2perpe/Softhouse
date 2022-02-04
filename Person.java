import java.util.ArrayList;
import java.util.List;

public class Person {

    private String firstName;
    private String lastName;

    private Phone phone;
    private Address address;

    private List<Family> families = new ArrayList<Family>();


    public Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }


    public List<Family> getFamilies() {
        return families;
    }

    public void addFamily(Family family) {
        this.families.add(family);
    }
}
