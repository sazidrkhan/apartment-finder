package applicationpackage;

import javax.swing.*;

public class ApartmentItem {
    String address, price, size, status, details;
    ImageIcon image;

    ApartmentItem(String size, String price, String status, String address, String details, ImageIcon image) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.status = status;
        this.details = details;
        this.image = image;
    }
}
