package se.sofia.lab.dbutil;

import se.sofia.lab.Address;
import se.sofia.lab.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    private DBUtil() {
        //Prevent instantiation
    }


    private static Connection connect() {

        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:database/addressregistry.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            //System.out.println("Connection to SQLite has been established.");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }


    public static void insertPerson(String ssn, String lastName, String firstName, String sex) {

        Connection conn = connect();

        try {

            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO Person (ssn, lastName, firstName, sex) values(?, ?, ?, ?)");
            insertStatement.setString(1, ssn);
            insertStatement.setString(2, lastName);
            insertStatement.setString(3, firstName);
            insertStatement.setString(4, sex);

            insertStatement.execute();

            insertStatement.close();


            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void insertAddress(String ssn, String streetAddress, String streetNumber, String city, String zipCode, String addressType) {

        Connection conn = connect();

        try {

            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO Address (ssn, streetAddress, streetNumber, city, zipCode, addressType) values(?, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, ssn);
            insertStatement.setString(2, streetAddress);
            insertStatement.setString(3, streetNumber);
            insertStatement.setString(4, city);
            insertStatement.setString(5, zipCode);
            insertStatement.setString(6, addressType);

            insertStatement.execute();

            insertStatement.close();


            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static List<Person> getAllPersonsWithAddresses() {

        List<Person> personList = new ArrayList<>();

        ResultSet personResultSet = null;
        Connection conn = connect();

        try {

            PreparedStatement selectPersonStatement = conn.prepareStatement("SELECT * FROM Person");


            personResultSet = selectPersonStatement.executeQuery();


            while (personResultSet.next()) {

                String ssn = personResultSet.getString("ssn");

                Person person = new Person(
                        ssn,
                        personResultSet.getString("lastName"),
                        personResultSet.getString("firstName"),
                        personResultSet.getString("sex")
                );

                PreparedStatement selectAddressStatement = conn.prepareStatement("SELECT * FROM Address WHERE ssn=?");
                selectAddressStatement.setString(1, ssn);
                ResultSet addressResultSet = selectAddressStatement.executeQuery();

                while(addressResultSet.next()){

                    Address address = new Address(
                            addressResultSet.getString("streetAddress"),
                            addressResultSet.getString("streetNumber"),
                            addressResultSet.getString("city"),
                            addressResultSet.getString("zipCode"),
                            addressResultSet.getString("addressType")
                    );

                    person.addAddress(address);
                }

                personList.add(person);

                addressResultSet.close();
                selectAddressStatement.close();
            }



            personResultSet.close();
            selectPersonStatement.close();

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return personList;

    }

    public static Person getBySsn(String ssn) {
        Person person = null;

        ResultSet personResultSet = null;
        Connection conn = connect();

        try {

            PreparedStatement selectPersonStatement = conn.prepareStatement("SELECT * FROM Person WHERE ssn=?");
            selectPersonStatement.setString(1, ssn);


            personResultSet = selectPersonStatement.executeQuery();


            if (personResultSet.next()) {


                person = new Person(
                        ssn,
                        personResultSet.getString("lastName"),
                        personResultSet.getString("firstName"),
                        personResultSet.getString("sex")
                );

                PreparedStatement selectAddressStatement = conn.prepareStatement("SELECT * FROM Address WHERE ssn=?");
                selectAddressStatement.setString(1, ssn);
                ResultSet addressResultSet = selectAddressStatement.executeQuery();

                while(addressResultSet.next()){

                    Address address = new Address(
                            addressResultSet.getString("streetAddress"),
                            addressResultSet.getString("streetNumber"),
                            addressResultSet.getString("city"),
                            addressResultSet.getString("zipCode"),
                            addressResultSet.getString("addressType")
                    );

                    person.addAddress(address);
                }


                addressResultSet.close();
                selectAddressStatement.close();
            }



            personResultSet.close();
            selectPersonStatement.close();

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return person;

    }
}
