/*
⚔️ POLYMORPHISM + ABSTRACT CLASS CHALLENGE
    Problem:

    We want to design a Payment System.
    There are different payment methods:
        CreditCard
        DebitCard
        UPI

    Structure:
    ➡️ Abstract Class: Payment
        Field: double amount
        Constructor: To initialize amount.
        Abstract Method: makePayment()
        Non-abstract Method: paymentDetails() → which prints the amount.

    ➡️ Child Classes:
    Each will extend Payment and override makePayment():
        CreditCard → Print: "Payment done using Credit Card."
        DebitCard → Print: "Payment done using Debit Card."
        UPI → Print: "Payment done using UPI."

    ➡️ Main Method:
        Create one Payment reference for each child object.
        Call makePayment() and paymentDetails() for each.

    (Use Runtime Polymorphism: Parent class reference → Child class object)

    Output:
        --- Credit Card Payment ---
        Payment done using Credit Card.
        Payment Amount: 5000.0

        --- Debit Card Payment ---
        Payment done using Debit Card.
        Payment Amount: 3000.0

        --- UPI Payment ---
        Payment done using UPI.
        Payment Amount: 1500.0

    🔥 Rules:
        Use Abstract Class properly.
        Implement Method Overriding.
        Apply Polymorphism (Payment p = new CreditCard(...)).
        Clean console output like above.

    ✨ BONUS (if you want more practice):
        Add a transactionId field to each child and print it randomly. (optional)

*/
package ObjectOrientedProgramming;

import java.util.Random;

abstract class Payment{
    private final double amount;
    private final int digit;

    Payment(double amount, int digit){
        this.amount = amount;
        this.digit = digit;
    }

    public abstract void makePayment();


    public String transactionalId(int idDigit){
        StringBuilder result = new StringBuilder(idDigit);
        String characters = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        for (int i = 0; i < idDigit; i++){
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }

    public void paymentDetails(){
        System.out.println("Payment Amount: " + amount);
        System.out.println("Transactional Id: " + transactionalId(digit));
    }

}

class CreditCard extends Payment{
    CreditCard(double amount){
        super(amount, 16);
    }

    @Override
    public void makePayment() {
        System.out.println("Payment done using Credit Card.");
    }
}

class DebitCard extends Payment{
    DebitCard(double amount){
        super(amount, 16);
    }

    @Override
    public void makePayment(){
        System.out.println("Payment done using Debit Card.");
    }
}

class UPI extends Payment{
    UPI(double amount){
        super(amount, 12);
    }

    @Override
    public void makePayment(){
        System.out.println("Payment done using UPI.");
    }
}

public class PracticeProgram2 {
    public static void main(String[] args) {
        Payment creditCard = new CreditCard(5000.0);
        Payment debitCard = new DebitCard(3000.0);
        Payment upi = new UPI(1500.0);

        System.out.println("--- Credit Card Payment ---");
        creditCard.makePayment();
        creditCard.paymentDetails();
        System.out.println();

        System.out.println("--- Debit Card Payment ---");
        debitCard.makePayment();
        debitCard.paymentDetails();
        System.out.println();

        System.out.println("--- UPI Payment ---");
        upi.makePayment();
        upi.paymentDetails();
        System.out.println();

    }
}
