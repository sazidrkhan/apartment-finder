package applicationpackage; // Package containing the main classes of the application

// Importing the necessary classes
import javax.swing.*;

// Apartment Item class to store the details of the apartment
public class ApartmentItem {
    // Declaring the necessary variables
    String apartmentId, price, size, status, address, details;
    ImageIcon image;

    // Constructor to initialize the variables
    ApartmentItem(String apartmentId, String size, String price, String status, String address, String details, ImageIcon image) {
        // Initializing the variables
        this.apartmentId = apartmentId;
        this.size = size;
        this.price = price;
        this.status = status;
        this.address = address;
        this.details = details;
        this.image = image;
    }
}
