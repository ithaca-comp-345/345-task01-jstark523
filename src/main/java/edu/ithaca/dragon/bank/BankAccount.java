package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        if ((email.indexOf('@') == -1) || (email.indexOf('@') == 0)){ // the @ symbol must be present cannot be the first character
            return false;
        }
        else if (email.length() < 7){ //a@b.com, minimum length of 7 is required for a valid email address
            return false;
        }
        else if ((email.indexOf('.') == -1) || (email.indexOf('.') == 0)){ //the . symbol must be present and cannot be the first character
            return false;
        }
        else if (email.indexOf('@') == (email.indexOf('.')-1)){ //the @ symbol cannot immediately precede the . symbol, (a@.com) is invalid.
            return false;
        }
        else if (email.indexOf('.') < (email.indexOf('.')){ //the . symbol cannot come before the @ symbol in an email address
            return false;
        }
        return true;
    }
}
