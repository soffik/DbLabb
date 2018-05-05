package se.sofia.lab;

public class Address {

    private String streetAddress;
    private String streetNumber;
    private String city;
    private String zipCode;
    private String addressType;

    public Address(String streetAddress, String streetNumber, String city, String zipCode, String addressType){

        this.streetAddress = streetAddress;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.addressType = addressType;

    }


    @Override
    public String toString(){

        return
                "Street address: " + streetAddress + System.lineSeparator() +
                        "Street number: " + streetNumber + System.lineSeparator() +
                        "Zip code: " + zipCode + System.lineSeparator() +
                        "City: " + city + System.lineSeparator() +
                        "Type of address: " + addressType + System.lineSeparator();

    }


}
