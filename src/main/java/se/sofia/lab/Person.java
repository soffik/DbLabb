package se.sofia.lab;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String ssn;
    private String lastName;
    private String firstName;
    private String sex;
    private List<Address> addressList;

    public Person(String ssn, String lastName, String firstName, String sex) {

        this.ssn = ssn;
        this.lastName = lastName;
        this.firstName = firstName;
        this.sex = sex;
        this.addressList = new ArrayList<>();

    }


    public void addAddress(Address address) {

        addressList.add(address);
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder(
                "ssn: " + ssn + System.lineSeparator() +
                        "First name: " + firstName + System.lineSeparator() +
                        "Last name: " + lastName + System.lineSeparator() +
                        "Sex: " + sex + System.lineSeparator()
        );

        for (Address address : addressList) {
            stringBuilder.append(address.toString());
        }

        return stringBuilder.toString();
    }


}
