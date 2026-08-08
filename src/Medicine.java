import java.time.LocalDate;
public class Medicine {

    String name;
    String batchNo;
    LocalDate expiry;
    String manufacturer;
    int quantity;
    double price;

    // Constructor
    public Medicine(String name, String batchNo, LocalDate expiry,
                    String manufacturer, int quantity, double price) {

        this.name = name;
        this.batchNo = batchNo;
        this.expiry = expiry;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
    }

    // Display Method
    public void displayMedicine() {
        System.out.println("Name         : " + name);
        System.out.println("Batch Number : " + batchNo);
        System.out.println("Expiry Date  : " + expiry);
        System.out.println("Manufacturer : " + manufacturer);
        System.out.println("Quantity     : " + quantity);
        System.out.println("Price        : ₹" + price);
    }
}