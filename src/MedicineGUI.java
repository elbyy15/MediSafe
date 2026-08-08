import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MedicineGUI {


    JFrame frame;

    JLabel nameLabel, batchLabel, expiryLabel, manufacturerLabel, quantityLabel, priceLabel;

    JTextField nameField, batchField, expiryField, manufacturerField, quantityField, priceField;

    JButton addButton;
    JButton searchButton;
    JButton updateButton;
    JButton deleteButton;
    JButton clearButton;

    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;

    ArrayList<Medicine> medicines = new ArrayList<>();

    public MedicineGUI() {

        // Frame
        frame = new JFrame("MediSafe - Medicine Expiry Management");
        frame.setSize(720, 550);
        frame.setLayout(null);

        frame.getContentPane().setBackground(new Color(245,245,245));

        JLabel title = new JLabel("MediSafe");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(270, 10, 200, 30);
        title.setForeground(new Color(25, 55, 109));

        JLabel subtitle = new JLabel("Medicine Expiry Management System");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setBounds(215, 40, 260, 20);

        frame.add(title);
        frame.add(subtitle);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Labels
        nameLabel = new JLabel("Medicine Name:");
        nameLabel.setBounds(40, 90, 120, 25);

        batchLabel = new JLabel("Batch Number:");
        batchLabel.setBounds(40, 130, 120, 25);

        expiryLabel = new JLabel("Expiry Date:");
        expiryLabel.setBounds(40, 170, 120, 25);

        manufacturerLabel = new JLabel("Manufacturer:");
        manufacturerLabel.setBounds(40, 210, 120, 25);

        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(40, 250, 120, 25);

        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(40, 290, 120, 25);

        // Text Fields
        nameField = new JTextField();
        nameField.setBounds(170, 90, 200, 25);

        batchField = new JTextField();
        batchField.setBounds(170, 130, 200, 25);

        expiryField = new JTextField();
        expiryField.setBounds(170, 170, 200, 25);

        manufacturerField = new JTextField();
        manufacturerField.setBounds(170, 210, 200, 25);

        quantityField = new JTextField();
        quantityField.setBounds(170, 250, 200, 25);

        priceField = new JTextField();
        priceField.setBounds(170, 290, 200, 25);

        // Buttons
        addButton = new JButton("Add");
        addButton.setBounds(60, 340, 120, 35);

        searchButton = new JButton("Search");
        searchButton.setBounds(190, 340, 120, 35);

        updateButton = new JButton("Update");
        updateButton.setBounds(320, 340, 120, 35);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(450, 340, 120, 35);

        clearButton = new JButton("Clear");
        clearButton.setBounds(580, 340, 100, 35);

        // Button Colors
        addButton.setBackground(new Color(76,175,80));
        addButton.setForeground(Color.WHITE);

        searchButton.setBackground(new Color(33,150,243));
        searchButton.setForeground(Color.WHITE);

        updateButton.setBackground(new Color(255,152,0));
        updateButton.setForeground(Color.WHITE);

        deleteButton.setBackground(new Color(244,67,54));
        deleteButton.setForeground(Color.WHITE);

        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);

        // Table
        String[] columns = {
                "Name",
                "Batch",
                "Expiry",
                "Manufacturer",
                "Quantity",
                "Price"
        };

        model = new DefaultTableModel(columns,0){

            @Override
            public boolean isCellEditable(int row,int column){

                return false;

            }

        };

        table = new JTable(model);
        table.getTableHeader().setPreferredSize(new Dimension(0,30));
        table.setRowHeight(25);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,390,670,130);

        // Add Components
        frame.add(nameLabel);
        frame.add(batchLabel);
        frame.add(expiryLabel);
        frame.add(manufacturerLabel);
        frame.add(quantityLabel);
        frame.add(priceLabel);

        frame.add(nameField);
        frame.add(batchField);
        frame.add(expiryField);
        frame.add(manufacturerField);
        frame.add(quantityField);
        frame.add(priceField);

        frame.add(addButton);
        frame.add(searchButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(clearButton);

        frame.add(scrollPane);

        // Button Action
        addButton.addActionListener(e -> {

            try {

                String name = nameField.getText();
                String batch = batchField.getText();
                LocalDate expiry = LocalDate.parse(expiryField.getText());
                String manufacturer = manufacturerField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());

                Medicine med = new Medicine(
                        name,
                        batch,
                        expiry,
                        manufacturer,
                        quantity,
                        price
                );

                medicines.add(med);

                model.addRow(new Object[]{
                        med.name,
                        med.batchNo,
                        med.expiry,
                        med.manufacturer,
                        med.quantity,
                        med.price
                });

                JOptionPane.showMessageDialog(frame,
                        "Medicine Added Successfully!");

                // Clear Fields
                nameField.setText("");
                batchField.setText("");
                expiryField.setText("");
                manufacturerField.setText("");
                quantityField.setText("");
                priceField.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(frame,
                        "Please enter valid data!");

            }

        });

        searchButton.addActionListener(e -> {

            String searchName = JOptionPane.showInputDialog(
                    frame,
                    "Enter Medicine Name:"
            );

            boolean found = false;

            for (Medicine med : medicines) {

                if (med.name.equalsIgnoreCase(searchName)) {

                    nameField.setText(med.name);
                    batchField.setText(med.batchNo);
                    expiryField.setText(med.expiry.toString());
                    manufacturerField.setText(med.manufacturer);
                    quantityField.setText(String.valueOf(med.quantity));
                    priceField.setText(String.valueOf(med.price));

                    JOptionPane.showMessageDialog(frame,
                            "Medicine Found!");

                    found = true;
                    break;
                }

            }

            if (!found) {

                JOptionPane.showMessageDialog(frame,
                        "Medicine Not Found!");

            }

        });

        clearButton.addActionListener(e -> {

            nameField.setText("");
            batchField.setText("");
            expiryField.setText("");
            manufacturerField.setText("");
            quantityField.setText("");
            priceField.setText("");

        });

        frame.setVisible(true);
    }
}