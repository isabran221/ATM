/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atm_gui;

/**
 *
 * @author isabr
 */
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class ATM_GUI extends JFrame {
    private static final String CORRECT_PIN = "1234";
    private static final double INITIAL_BALANCE = 1000.00;
    private double balance = INITIAL_BALANCE;
    private DecimalFormat currencyFormat = new DecimalFormat("$###,###.00");

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    private JTextField pinField = new JTextField(10);
    private JTextArea displayArea = new JTextArea(10, 30);

    private JTextField withdrawField = new JTextField(10);
    private JTextField depositField = new JTextField(10);
    private JTextField transferField = new JTextField(10);
    private JTextField transferAccountField = new JTextField(10);
    private JTextField withdrawKeyField = new JTextField(10);
    private JTextField securityCodeField = new JTextField(10);
    private JTextField accountNumberField = new JTextField(10);
    private JTextField cashDepositField = new JTextField(10);
    private JTextField checkFolioField = new JTextField(10);
    private JTextField checkDepositField = new JTextField(10);

    public ATM_GUI() {
        setTitle("ATM Simulation");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Start Panel
        JPanel startPanel = new JPanel();
        JButton enterPinButton = new JButton("Ingresar PIN");
        JButton otherMovementsButton = new JButton("Otros movimientos");
        startPanel.add(enterPinButton);
        startPanel.add(otherMovementsButton);

        enterPinButton.addActionListener(e -> cardLayout.show(cardPanel, "PIN"));
        otherMovementsButton.addActionListener(e -> cardLayout.show(cardPanel, "OtherMovements"));

        // PIN Panel
        JPanel pinPanel = new JPanel(new BorderLayout());
        pinPanel.add(new JLabel("Ingrese su PIN:"), BorderLayout.NORTH);
        pinPanel.add(pinField, BorderLayout.CENTER);
        JButton submitPinButton = new JButton("Submit");
        pinPanel.add(submitPinButton, BorderLayout.SOUTH);
        submitPinButton.addActionListener(e -> verifyPin());

        // Transaction Panel
        JPanel transactionPanel = new JPanel(new GridLayout(6, 1));
        JButton checkBalanceButton = new JButton("Consultar saldo");
        JButton withdrawButton = new JButton("Retirar dinero");
        JButton depositButton = new JButton("Depositar dinero");
        JButton transferButton = new JButton("Transferir dinero");
        JButton exitButton = new JButton("Salir");
        JButton backButton = new JButton("Regresar");

        checkBalanceButton.addActionListener(e -> checkBalance());
        withdrawButton.addActionListener(e -> showWithdrawPanel());
        depositButton.addActionListener(e -> showDepositPanel());
        transferButton.addActionListener(e -> showTransferPanel());
        exitButton.addActionListener(e -> System.exit(0));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Start"));

        transactionPanel.add(checkBalanceButton);
        transactionPanel.add(withdrawButton);
        transactionPanel.add(depositButton);
        transactionPanel.add(transferButton);
        transactionPanel.add(exitButton);
        transactionPanel.add(backButton);

        // Withdraw Panel
        JPanel withdrawPanel = new JPanel(new GridLayout(4, 1));
        withdrawPanel.add(new JLabel("Ingrese la cantidad a retirar:"));
        withdrawPanel.add(withdrawField);
        JButton submitWithdrawButton = new JButton("Retirar");
        JButton backFromWithdrawButton = new JButton("Regresar");
        withdrawPanel.add(submitWithdrawButton);
        withdrawPanel.add(backFromWithdrawButton);
        submitWithdrawButton.addActionListener(e -> withdrawMoney());
        backFromWithdrawButton.addActionListener(e -> cardLayout.show(cardPanel, "Transaction"));

        // Deposit Panel
        JPanel depositPanel = new JPanel(new GridLayout(4, 1));
        depositPanel.add(new JLabel("Ingrese el monto a depositar:"));
        depositPanel.add(depositField);
        JButton submitDepositButton = new JButton("Depositar");
        JButton backFromDepositButton = new JButton("Regresar");
        depositPanel.add(submitDepositButton);
        depositPanel.add(backFromDepositButton);
        submitDepositButton.addActionListener(e -> depositMoney());
        backFromDepositButton.addActionListener(e -> cardLayout.show(cardPanel, "Transaction"));

        // Transfer Panel
        JPanel transferPanel = new JPanel(new GridLayout(4, 1));
        transferPanel.add(new JLabel("Ingrese el número de cuenta:"));
        transferPanel.add(transferAccountField);
        transferPanel.add(new JLabel("Ingrese el monto a transferir:"));
        transferPanel.add(transferField);
        JButton submitTransferButton = new JButton("Transferir");
        JButton backFromTransferButton = new JButton("Regresar");
        transferPanel.add(submitTransferButton);
        transferPanel.add(backFromTransferButton);
        submitTransferButton.addActionListener(e -> transferMoney());
        backFromTransferButton.addActionListener(e -> cardLayout.show(cardPanel, "Transaction"));

        // Other Movements Panel
        JPanel otherMovementsPanel = new JPanel(new GridLayout(4, 1));
        JButton withdrawWithoutCardButton = new JButton("Retirar sin tarjeta");
        JButton cashDepositButton = new JButton("Depositar en efectivo");
        JButton checkDepositButton = new JButton("Depositar cheque");
        JButton backFromOtherMovementsButton = new JButton("Regresar");

        withdrawWithoutCardButton.addActionListener(e -> showWithdrawWithoutCardPanel());
        cashDepositButton.addActionListener(e -> showCashDepositPanel());
        checkDepositButton.addActionListener(e -> showCheckDepositPanel());
        backFromOtherMovementsButton.addActionListener(e -> cardLayout.show(cardPanel, "Start"));

        otherMovementsPanel.add(withdrawWithoutCardButton);
        otherMovementsPanel.add(cashDepositButton);
        otherMovementsPanel.add(checkDepositButton);
        otherMovementsPanel.add(backFromOtherMovementsButton);

        // Withdraw Without Card Panel
        JPanel withdrawWithoutCardPanel = new JPanel(new GridLayout(4, 1));
        withdrawWithoutCardPanel.add(new JLabel("Ingrese la clave de retiro:"));
        withdrawWithoutCardPanel.add(withdrawKeyField);
        withdrawWithoutCardPanel.add(new JLabel("Ingrese el código de seguridad:"));
        withdrawWithoutCardPanel.add(securityCodeField);
        JButton submitWithdrawWithoutCardButton = new JButton("Retirar");
        JButton backFromWithdrawWithoutCardButton = new JButton("Regresar");
        withdrawWithoutCardPanel.add(submitWithdrawWithoutCardButton);
        withdrawWithoutCardPanel.add(backFromWithdrawWithoutCardButton);
        submitWithdrawWithoutCardButton.addActionListener(e -> withdrawWithoutCard());
        backFromWithdrawWithoutCardButton.addActionListener(e -> cardLayout.show(cardPanel, "OtherMovements"));

        // Cash Deposit Panel
        JPanel cashDepositPanel = new JPanel(new GridLayout(5, 1));
        cashDepositPanel.add(new JLabel("Ingrese el número de tarjeta de la cuenta a depositar:"));
        cashDepositPanel.add(accountNumberField);
        cashDepositPanel.add(new JLabel("Ingrese el monto a depositar:"));
        cashDepositPanel.add(cashDepositField);
        JButton submitCashDepositButton = new JButton("Depositar");
        JButton backFromCashDepositButton = new JButton("Regresar");
        cashDepositPanel.add(submitCashDepositButton);
        cashDepositPanel.add(backFromCashDepositButton);
        submitCashDepositButton.addActionListener(e -> depositCash());
        backFromCashDepositButton.addActionListener(e -> cardLayout.show(cardPanel, "OtherMovements"));

        // Check Deposit Panel
        JPanel checkDepositPanel = new JPanel(new GridLayout(6, 1));
        checkDepositPanel.add(new JLabel("Ingrese el folio del cheque:"));
        checkDepositPanel.add(checkFolioField);
        checkDepositPanel.add(new JLabel("Ingrese el número de tarjeta de la cuenta a depositar:"));
        checkDepositPanel.add(accountNumberField);
        checkDepositPanel.add(new JLabel("Ingrese el monto del cheque:"));
        checkDepositPanel.add(checkDepositField);
        JButton submitCheckDepositButton = new JButton("Depositar");
        JButton backFromCheckDepositButton = new JButton("Regresar");
        checkDepositPanel.add(submitCheckDepositButton);
        checkDepositPanel.add(backFromCheckDepositButton);
        submitCheckDepositButton.addActionListener(e -> depositCheck());
        backFromCheckDepositButton.addActionListener(e -> cardLayout.show(cardPanel, "OtherMovements"));

        // Display Panel
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        JButton backFromDisplayButton = new JButton("Regresar");
        displayPanel.add(backFromDisplayButton, BorderLayout.SOUTH);
        backFromDisplayButton.addActionListener(e -> cardLayout.show(cardPanel, "Transaction"));

        // Add panels to card layout
        cardPanel.add(startPanel, "Start");
        cardPanel.add(pinPanel, "PIN");
        cardPanel.add(transactionPanel, "Transaction");
        cardPanel.add(withdrawWithoutCardPanel, "WithdrawWithoutCard");
        cardPanel.add(cashDepositPanel, "CashDeposit");
        cardPanel.add(checkDepositPanel, "CheckDeposit");
        cardPanel.add(displayPanel, "Display");
        cardPanel.add(withdrawPanel, "Withdraw");
        cardPanel.add(depositPanel, "Deposit");
        cardPanel.add(transferPanel, "Transfer");
        cardPanel.add(otherMovementsPanel, "OtherMovements");

        add(cardPanel);
        cardLayout.show(cardPanel, "Start");
    }

    private void verifyPin() {
        if (pinField.getText().equals(CORRECT_PIN)) {
            cardLayout.show(cardPanel, "Transaction");
        } else {
            JOptionPane.showMessageDialog(this, "PIN incorrecto. Intente de nuevo.");
        }
    }

    private void checkBalance() {
        displayArea.setText("Su saldo actual es: " + currencyFormat.format(balance));
        cardLayout.show(cardPanel, "Display");
        askForTicket("Consulta de saldo", 0);
    }

    private void withdrawMoney() {
        try {
            int withdrawAmount = Integer.parseInt(withdrawField.getText());
            if (withdrawAmount > 0 && withdrawAmount <= balance) {
                balance -= withdrawAmount;
                displayArea.setText("Retire su dinero. Su saldo actual es: " + currencyFormat.format(balance));
                askForTicket("Retiro", withdrawAmount);
            } else {
                displayArea.setText("Fondos insuficientes o cantidad no válida.");
            }
            cardLayout.show(cardPanel, "Display");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.");
        }
    }

    private void depositMoney() {
        try {
            int depositAmount = Integer.parseInt(depositField.getText());
            if (depositAmount >= 100) {
                balance += depositAmount;
                displayArea.setText("Depósito exitoso. Su saldo actual es: " + currencyFormat.format(balance));
                askForTicket("Depósito", depositAmount);
            } else {
                displayArea.setText("Cantidad no válida para depósito.");
            }
            cardLayout.show(cardPanel, "Display");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.");
        }
    }

    private void transferMoney() {
        try {
            int transferAmount = Integer.parseInt(transferField.getText());
            if (transferAmount > 0 && transferAmount <= balance) {
                balance -= transferAmount;
                displayArea.setText("Transferencia exitosa. Su saldo actual es: " + currencyFormat.format(balance));
                askForTicket("Transferencia", transferAmount);
            } else {
                displayArea.setText("Fondos insuficientes o cantidad no válida.");
            }
            cardLayout.show(cardPanel, "Display");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.");
        }
    }

    private void withdrawWithoutCard() {
        try {
            String withdrawKey = withdrawKeyField.getText();
            String securityCode = securityCodeField.getText();
            if (!withdrawKey.isEmpty() && !securityCode.isEmpty()) {
                displayArea.setText("Retiro sin tarjeta exitoso.");
                askForTicket("Retiro sin tarjeta", 0);
            } else {
                displayArea.setText("Clave de retiro o código de seguridad no válidos.");
            }
            cardLayout.show(cardPanel, "Display");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la operación.");
        }
    }

    private void depositCash() {
        try {
            String accountNumber = accountNumberField.getText();
            int depositAmount = Integer.parseInt(cashDepositField.getText());
            if (!accountNumber.isEmpty() && depositAmount > 0) {
                balance += depositAmount;
                displayArea.setText("Depósito en efectivo exitoso.");
                askForTicket("Depósito en efectivo", depositAmount);
            } else {
                displayArea.setText("Número de tarjeta o monto no válido.");
            }
            cardLayout.show(cardPanel, "Display");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.");
        }
    }

    private void depositCheck() {
        try {
            String checkFolio = checkFolioField.getText();
            int checkAmount = Integer.parseInt(checkDepositField.getText());
            if (!checkFolio.isEmpty() && !accountNumberField.getText().isEmpty() && checkAmount > 0) {
                balance += checkAmount;
                displayArea.setText("Depósito de cheque exitoso.");
                askForTicket("Depósito de cheque", checkAmount);
            } else {
                displayArea.setText("Folio del cheque, número de tarjeta o monto no válidos.");
            }
            cardLayout.show(cardPanel, "Display");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.");
        }
    }

    private void askForTicket(String transactionType, int amount) {
        int response = JOptionPane.showConfirmDialog(this, "¿Desea imprimir el ticket?", "Imprimir Ticket",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            printTicket(transactionType, amount);
        }
    }

    private void printTicket(String transactionType, int amount) {
        String ticket = "------- TICKET -------\n";
        ticket += "Tipo de Transacción: " + transactionType + "\n";
        if (amount > 0) {
            ticket += "Monto: " + currencyFormat.format(amount) + "\n";
        }
        ticket += "Saldo Actual: " + currencyFormat.format(balance) + "\n";
        ticket += "-----------------------\n";
        JOptionPane.showMessageDialog(this, ticket, "Ticket", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showWithdrawPanel() {
        withdrawField.setText("");
        cardLayout.show(cardPanel, "Withdraw");
    }

    private void showDepositPanel() {
        depositField.setText("");
        cardLayout.show(cardPanel, "Deposit");
    }

    private void showTransferPanel() {
        transferField.setText("");
        transferAccountField.setText("");
        cardLayout.show(cardPanel, "Transfer");
    }

    private void showWithdrawWithoutCardPanel() {
        withdrawKeyField.setText("");
        securityCodeField.setText("");
        cardLayout.show(cardPanel, "WithdrawWithoutCard");
    }

    private void showCashDepositPanel() {
        accountNumberField.setText("");
        cashDepositField.setText("");
        cardLayout.show(cardPanel, "CashDeposit");
    }

    private void showCheckDepositPanel() {
        checkFolioField.setText("");
        accountNumberField.setText("");
        checkDepositField.setText("");
        cardLayout.show(cardPanel, "CheckDeposit");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ATM_GUI frame = new ATM_GUI();
            frame.setVisible(true);
        });
    }
}
