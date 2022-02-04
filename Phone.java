

public class Phone {

    private String mobile;
    private String landline;

    public Phone(String mobile, String landline)
    {
        this.mobile = mobile;
        this.landline = landline;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }
}
