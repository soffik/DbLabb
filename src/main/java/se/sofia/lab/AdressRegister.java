package se.sofia.lab;

import se.sofia.lab.dbutil.DBUtil;

import java.util.Scanner;

public class AdressRegister {

    public static void main(String[] args) {

        printHelp();
        System.out.println("What do you want to do?");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();


        while (!command.equalsIgnoreCase("q")) {

            switch (command) {
                case "1":
                    handleInsertPerson(scanner);
                    break;

                case "2":
                    handleInsertAddress(scanner);
                    break;

                case "3":
                    handleReadAll();
                    break;

                case "4":
                    handleReadBySsn(scanner);
                    break;

                default:
                    printHelp();
                    break;
            }

            System.out.println("What do you want to do?");
            command = scanner.next();
        }

    }

    private static void handleReadBySsn(Scanner scanner) {
        System.out.println("Enter ssn");
        String ssn = scanner.next();

        System.out.println(DBUtil.getBySsn(ssn));
    }

    private static void handleInsertAddress(Scanner scanner) {
        System.out.println("Enter ssn");
        String ssn = scanner.next();

        System.out.println("Enter street address");
        String streetAddress = scanner.next();

        System.out.println("Enter street number");
        String streetNumber = scanner.next();

        System.out.println("Enter zip code");
        String zipCode = scanner.next();

        System.out.println("Enter city");
        String city = scanner.next();

        System.out.println("Enter type of address");
        String addressType = scanner.next();

        DBUtil.insertAddress(ssn, streetAddress, streetNumber, city, zipCode, addressType);
    }

    private static void handleReadAll() {
        DBUtil.getAllPersonsWithAddresses().forEach(System.out::println);
    }

    private static void handleInsertPerson(Scanner scanner) {
        System.out.println("Enter ssn");
        String ssn = scanner.next();

        System.out.println("Enter first name");
        String firstName = scanner.next();

        System.out.println("Enter last name");
        String lastName = scanner.next();

        System.out.println("Enter sex");
        String sex = scanner.next();

        DBUtil.insertPerson(ssn, lastName, firstName, sex);
    }

    private static void printHelp() {
        System.out.println("To insert person enter 1");
        System.out.println("To insert address enter 2");
        System.out.println("To read all enter 3");
        System.out.println("To read by ssn enter 4");
        System.out.println("To quit enter q");
    }


}

