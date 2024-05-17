package applicationpackage;

import javax.swing.*;

public class ApartmentItem {
    String apartmentId, price, size, status, address, details;
    ImageIcon image;

    ApartmentItem(String apartmentId, String size, String price, String status, String address, String details, ImageIcon image) {
        this.apartmentId = apartmentId;
        this.size = size;
        this.price = price;
        this.status = status;
        this.address = address;
        this.details = details;
        this.image = image;
    }
}
