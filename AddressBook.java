import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import java.util.logging.Logger;

public class AddressBook {

    private TreeMap<String, ContactDetails> addressBookRegistry;
    private static Logger logger;

    public AddressBook() {
        addressBookRegistry = new TreeMap<>(); // diamond notasjon
    }

    public static void main(String[] args) {

        //HashMap<String, ContactDetails>;


        // Open connection to database
        AddressBook addressBook = new AddressBook();

        logger = Logger.getLogger(AddressBook.class.toString());

        try {
            ContactDetails contactDetails1 = new ContactDetails("Pippi S", "12345", "Stocklhom", 21);
            System.out.println("Name:: " + contactDetails1.getName()
                    + "\nPhone number:: "
                    + contactDetails1.getPhoneNumber()
                    + "\nAddress:: " + contactDetails1.getAddress());
            ContactDetails contactDetails2 = new ContactDetails("Håkon", "999999", "Gjøvik", -20);
            System.out.println("Name:: " + contactDetails2.getName()
                    + "\nPhone number:: "
                    + contactDetails2.getPhoneNumber()
                    + "\nAddress:: " + contactDetails2.getAddress());

            ContactDetails contactDetails3 = new ContactDetails("Vegard", "999998", "Gjøvik", 20);
            System.out.println("Name:: " + contactDetails3.getName()
                    + "\nPhone number:: "
                    + contactDetails3.getPhoneNumber()
                    + "\nAddress:: " + contactDetails3.getAddress());

            addressBook.addressBookRegistry.put(contactDetails1.getName(), contactDetails1);
            addressBook.addressBookRegistry.put(contactDetails2.getName(), contactDetails2);
            addressBook.addressBookRegistry.put(contactDetails3.getName(), contactDetails3);


            logger.info("Added the entries");
            System.out.println(addressBook.addressBookRegistry.toString());
            addressBook.saveToFile();
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("One of the argument was not correct or invalid");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        } catch (Exception e) {
            System.out.println("Ta kontakt med Julius, han skal fikse denne feilen.");
            System.out.println("System was not happy with Håkon");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        } finally {
            // close connection to database
        }


    }

    public void saveToFile() throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("ContactDetails1.txt");
            for (ContactDetails contactDetails : addressBookRegistry.values()) {
                fileWriter.write("Name:: " + contactDetails.getName()
                        + " Phone number:: "
                        + contactDetails.getPhoneNumber()
                        + " Address:: " + contactDetails.getAddress() + "\n");
            }
        } finally {
            fileWriter.close();
        }
    }
}
