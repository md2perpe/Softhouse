public class Family {

    private String name;
    private String born;

    private Phone phone;
    private Address address;


    public Family(String name, String born)
    {
        this.name = name;
        this.born = born;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }


    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
