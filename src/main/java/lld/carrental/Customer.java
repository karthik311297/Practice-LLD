package lld.carrental;

public class Customer {
    private String name;
    private String phno;
    private String licenseNumber;

    public Customer(String name, String phno, String licenseNumber) {
        this.name = name;
        this.phno = phno;
        this.licenseNumber = licenseNumber;
    }

    public String getPhno() {
        return phno;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
}
