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
        // the @ symbol must be present cannot be the first character
        if ((email.indexOf('@') == -1) || (email.indexOf('@') == 0)){ 
            return false;
        }
        //a@b.com, minimum length of 7 is required for a valid email address
        else if (email.length() < 7){ 
            return false;
        }
        //maximum length of 45 is required for valid email address
        else if (email.length() > 45){
            return false;
        }
        //the . symbol must be present and cannot be the first character
        else if ((email.indexOf('.') == -1) || (email.indexOf('.') == 0)){
            return false;
        }
        email.
        //the @ symbol cannot immediately precede the . symbol, (a@.com) is invalid.
        else if ((email.indexOf('@')) == ((email.indexOf('.'))-1)){ 
            return false;
        }
        else if (email.indexOf('.') < (email.indexOf('.')){ //the . symbol cannot come before the @ symbol in an email address
           return false;
        }
        return true;
    }
}
