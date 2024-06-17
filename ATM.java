/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atm;

/**
 *
 * @author isabr
 */
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
    private static final String CORRECT_PIN = "1234";
    private static final double INITIAL_BALANCE = 1000.00;
    private static double balance = INITIAL_BALANCE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat currencyFormat = new DecimalFormat("$###,###.00");

        System.out.println("Bienvenido al cajero automático");

        // Opción de inicio
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Ingresar PIN");
        System.out.println("2. Otros movimientos");
        System.out.print("Opción: ");
        int startOption = scanner.nextInt();

        if (startOption == 1) {
            // Ingreso de PIN
            System.out.print("Ingrese su PIN: ");
            String pin = scanner.next();
            int attempts = 1;
            while (!pin.equals(CORRECT_PIN) && attempts < 3) {
                System.out.print("PIN incorrecto. Intente de nuevo: ");
                pin = scanner.next();
                attempts++;
            }

            if (attempts >= 3) {
                System.out.println("Demasiados intentos fallidos. El cajero se bloquea.");
                return;
            }

            // Transacciones
            boolean exit = false;
            while (!exit) {
                System.out.println("\nSeleccione una transacción:");
                System.out.println("1. Consultar saldo");
                System.out.println("2. Retirar dinero");
                System.out.println("3. Depositar dinero");
                System.out.println("4. Transferir dinero");
                System.out.println("5. Salir");
                System.out.print("Opción: ");
                int transactionOption = scanner.nextInt();

                switch (transactionOption) {
                    case 1:
                        // Consultar saldo
                        System.out.println("Su saldo actual es: " + currencyFormat.format(balance));
                        printTicket("Consulta de saldo", 0);
                        break;
                    case 2:
                        // Retirar dinero
                        System.out.print("Ingrese la cantidad a retirar (sin decimales): ");
                        int withdrawAmount = scanner.nextInt();
                        if (withdrawAmount <= balance && withdrawAmount > 0) {
                            balance -= withdrawAmount;
                            System.out.println("Retire su dinero. Su saldo actual es: " + currencyFormat.format(balance));
                            printTicket("Retiro", withdrawAmount);
                        } else {
                            System.out.println("Fondos insuficientes o cantidad no válida.");
                        }
                        break;
                    case 3:
                        // Depositar dinero
                        boolean validDeposit = false;
                        while (!validDeposit) {
                            try {
                                System.out.print("Ingrese el monto a depositar (solo montos igual o arriba de 100 y que sean enteros): ");
                                int depositAmount = scanner.nextInt();
                                if (depositAmount >= 100) {
                                    balance += depositAmount;
                                    System.out.println("Depósito exitoso. Su saldo actual es: " + currencyFormat.format(balance));
                                    validDeposit = true;
                                    printTicket("Depósito", depositAmount);
                                } else {
                                    System.out.println("Cantidad no válida para depósito.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("No es válido ingresar decimales, solo enteros.");
                                scanner.next(); // Limpiar el buffer del scanner
                            }
                        }
                        break;
                    case 4:
                        // Transferir dinero
                        System.out.print("Ingrese el número de tarjeta de la cuenta a transferir: ");
                        String transferAccountNumber = scanner.next();
                        System.out.print("Ingrese el monto a transferir (sin decimales): ");
                        int transferAmount = scanner.nextInt();
                        if (transferAmount <= balance && transferAmount > 0) {
                            balance -= transferAmount;
                            System.out.println("Transferencia exitosa. Su saldo actual es: " + currencyFormat.format(balance));
                            printTicket("Transferencia", transferAmount);
                        } else {
                            System.out.println("Fondos insuficientes o cantidad no válida.");
                        }
                        break;
                    case 5:
                        // Salir
                        System.out.println("Gracias por usar el cajero automático. Adiós.");
                        exit = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        } else if (startOption == 2) {
            // Otros movimientos
            System.out.println("\nSeleccione una transacción:");
            System.out.println("1. Retirar sin tarjeta");
            System.out.println("2. Depositar en efectivo");
            System.out.println("3. Depositar cheque");
            System.out.print("Opción: ");
            int otherOption = scanner.nextInt();

            if (otherOption == 1) {
                // Retirar sin tarjeta
                System.out.print("Ingrese la clave de retiro: ");
                String withdrawalKey = scanner.next();
                System.out.print("Ingrese el código de seguridad: ");
                String securityCode = scanner.next();
                // Aquí puedes verificar la clave de retiro y el código de seguridad
                // Si son correctos, puedes proceder con el retiro
                printTicket("Retiro sin tarjeta", 0);
            } else if (otherOption == 2) {
                // Depositar en efectivo
                System.out.print("Ingrese el número de tarjeta de la cuenta a depositar: ");
                String accountNumber = scanner.next();
                boolean validDeposit = false;
                while (!validDeposit) {
                    try {
                        System.out.print("Ingrese el monto a depositar (solo montos igual o arriba de 100 y que sean enteros): ");
                        int depositAmount = scanner.nextInt();
                        if (depositAmount >= 100) {
                            balance += depositAmount;
                            System.out.println("Depósito exitoso. Su saldo actual es: " + currencyFormat.format(balance));
                            validDeposit = true;
                            printTicket("Depósito en efectivo", depositAmount);
                        } else {
                            System.out.println("Cantidad no válida para depósito.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("No es válido ingresar decimales, solo enteros.");
                        scanner.next(); // Limpiar el buffer del scanner
                    }
                }
            } else if (otherOption == 3) {
                // Depositar cheque
                System.out.print("Ingrese el folio del cheque: ");
                String checkFolio = scanner.next();
                System.out.print("Ingrese el número de tarjeta de la cuenta a depositar: ");
                String accountNumber = scanner.next();
                // Aquí puedes verificar el folio del cheque y el número de cuenta
                // Si son correctos, puedes proceder con el depósito
                printTicket("Depósito de cheque", 0);
            }
        }

        scanner.close();
    }

    private static void printTicket(String transactionType, int amount) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n¿Desea imprimir un ticket?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        System.out.print("Opción: ");
        int printOption = scanner.nextInt();

        if (printOption == 1) {
            System.out.println("\nImprimiendo ticket...");
            System.out.println("Tipo de transacción: " + transactionType);
            System.out.println("Monto: $" + amount);
            System.out.println("Saldo actual: $" + balance);
        }
    }
}
