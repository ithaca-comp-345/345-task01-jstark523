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
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        if (isAmountValid(startingBalance)){
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Starting Balance is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    //I also fixed the javadoc below based on feedback
    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * throw illegal argument if amount less than 0 is withdrawn
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (BankAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be withdrawn");
        }
        else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }
    /**
     * 
     * @param amount to be deposited
     * must throw illegal argument error if amount is less than 0 and/or has more than 2 decimal points
     */
    public void deposit(double amount){
        if (BankAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be deposited");
        }
        else{
            balance += amount;
        }
    }
    /**
     * 
     * @param amount to be transferred from your account to the other
     * @param transferAccount to transfer the amount from
     * @param otherAccount to transfer the amount to
     * must throw illegal argument error if amount is less than 0 and/or has more than 2 decimal points or if same bankAccount is utilized
     * must throw insufficient fund error if amount to transfer is less than balance
     */
    public void transfer(double amount, BankAccount transferAccount, BankAccount otherAccount)throws InsufficientFundsException{
        if (BankAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be deposited");
        }
        else if (transferAccount == otherAccount){
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        else if (amount > transferAccount.balance){
            throw new InsufficientFundsException("Not enough money");
        }
        else{
            transferAccount.balance -= amount;
            otherAccount.balance += amount;
        }
    }


    public static boolean isEmailValid(String email){
        // the @ symbol must be present cannot be the first character
        if ((email.indexOf('@') == -1) || (email.indexOf('@') == 0)){ 
            return false;
        }
        //a@b.com, minimum length of 7 is required for a valid email address;maximum length of 45 is required for valid email address
        else if ((email.length() < 7) || (email.length() > 45)){ 
            return false;
        }
        //the . symbol must be present and cannot be the first character
        else if ((email.indexOf('.') == -1) || (email.indexOf('.') == 0)){ 
            return false;
        }
        //the . symbol cannot be before the @
        else if ((email.indexOf ('.') < email.indexOf('@'))){
            return false;
        }
        //the email shouldn't contain special characters
        else if((email.indexOf('~') != -1) || (email.indexOf('!') != -1) ||(email.indexOf('#') != -1) ||(email.indexOf('$') != -1) ||(email.indexOf('%') != -1) || (email.indexOf('^') != -1) ||(email.indexOf('&') != -1) ||(email.indexOf('*') != -1) ||(email.indexOf('(') != -1) ||(email.indexOf(')') != -1) ||(email.indexOf('_') != -1) ||(email.indexOf('+') != -1)){
            return false;
        }
        else{
        return true;
        }
    }
    /**
     * 
     * @param amount to be checked if valid
     * @return whether or not the amount is non-negative and has two decimal points or less
     */
    public static boolean isAmountValid(double amount){
        String amountStr = String.valueOf(amount);
        if(amount < 0){
            return false;
        }
        else if (amountStr.indexOf('.') == -1 ){
            return true;
        }
        else{
            String decimal = amountStr.substring(amountStr.indexOf('.') + 1);
            if(decimal.length() > 2){
                return false;
            }
            else{
                return true;
            }
        }

    }
}
