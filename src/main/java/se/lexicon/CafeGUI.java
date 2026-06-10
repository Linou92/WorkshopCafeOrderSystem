package se.lexicon;

import javax.swing.*;
import java.awt.*;

public class CafeGUI extends JFrame {

    private MenuItem[] menu = {
            new MenuItem("Espresso", 25),
            new MenuItem("Cappuccino", 35),
            new MenuItem("Latte", 40),
            new MenuItem("Croissant", 30),
            new MenuItem("Sandwich", 55)
    };

    private int customersServed = 0;
    private double totalRevenue = 0;

    private Order currentOrder;
    private JTextArea orderArea;
    private JTextArea receiptArea;
    private JTextField nameField;
    private JTextField quantityField;
    private JCheckBox memberBox;
    private JComboBox<MenuItem> menuBox;
    private ReceiptPrinter receiptPrinter = new ReceiptPrinter();
    private JLabel titleLabel;

    public CafeGUI() {

        setTitle("Lexicon Cafe");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        titleLabel = new JLabel("LEXICON CAFE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.DARK_GRAY);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Customer section
        JLabel nameLabel = new JLabel("Customer Name:");

        nameField = new JTextField(15);

        memberBox = new JCheckBox("Loyalty Member");

        // Order section
        menuBox = new JComboBox<>(menu);

        quantityField = new JTextField(5);

        JButton addButton = new JButton("Add Item");
        JButton finishButton = new JButton("Finish Order");
        JButton nextCustomerButton = new JButton("Next Customer");
        JButton endDayButton = new JButton("End Day Report");

        // Text areas
        orderArea = new JTextArea(10, 30);
        orderArea.setEditable(false);

        receiptArea = new JTextArea(15, 30);
        receiptArea.setEditable(false);

        // Add item event
        addButton.addActionListener(e -> addItem());

        // Finish order event
        finishButton.addActionListener(e -> finishOrder());

        // add next customer
        nextCustomerButton.addActionListener(e -> {
            nameField.setText("");
            quantityField.setText("");
            memberBox.setSelected(false);
            currentOrder = null;
            orderArea.setText("");
            receiptArea.setText("");
        });

        // show end report
        endDayButton.addActionListener(e -> {

            String report =
                    receiptPrinter.buildEndReport(customersServed, totalRevenue);

            JOptionPane.showMessageDialog(
                    this,
                    report
            );
        });

        // Panels
        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(memberBox);

        topPanel.add(formPanel, BorderLayout.CENTER);
        JPanel orderPanel = new JPanel();
        orderPanel.add(new JLabel("Item"));
        orderPanel.add(menuBox);
        orderPanel.add(new JLabel("Qty"));
        orderPanel.add(quantityField);
        orderPanel.add(addButton);
        orderPanel.add(finishButton);
        orderPanel.add(nextCustomerButton);
        orderPanel.add(endDayButton);

        setLayout(new BorderLayout());

        add(topPanel, BorderLayout.NORTH);
        add(orderPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        bottomPanel.add(new JScrollPane(orderArea));
        bottomPanel.add(new JScrollPane(receiptArea));

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addItem() {

        try {

            MenuItem selected =
                    (MenuItem) menuBox.getSelectedItem();

            int quantity =
                    Integer.parseInt(quantityField.getText());

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Quantity must be greater than 0."
                );
                return;
            }

            if (currentOrder == null) {

                String customerName = nameField.getText().trim();

                if (customerName.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Enter customer name."
                    );

                    return;
                }

                Customer customer =
                        new Customer(
                                customerName,
                                memberBox.isSelected()
                        );

                currentOrder = new Order(customer);
            }

            currentOrder.addItem(
                    new LineItem(selected, quantity)
            );

            orderArea.append(
                    selected.getName()
                            + " x "
                            + quantity
                            + "\n"
            );

            quantityField.setText("");

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid quantity."
            );
        }
    }

    private void finishOrder() {

        if (currentOrder == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No items in order."
            );

            return;
        }

        String receipt = receiptPrinter.buildReceipt(currentOrder);
        receiptArea.setText(receipt);

        // calculate total for tracking
        Calculator calculator = new Calculator();

        double subtotal = calculator.subtotal(currentOrder);
        double discount = calculator.discount(subtotal, currentOrder.getCustomer().isMember());
        double total = subtotal - discount + calculator.vat(subtotal - discount);

        // update stats
        customersServed++;
        totalRevenue += total;
        currentOrder = null; // reset for next order
        orderArea.setText(""); // clear current items
    }
}