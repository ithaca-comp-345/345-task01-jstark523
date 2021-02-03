package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance()); //positve integer equivalence class
        BankAccount bankAccount2 = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount2.getBalance()); //0 equivalence class, also a border case which is the reason for the values of next two tests
        BankAccount bankAccount3 = new BankAccount("a@b.com", -1);
        assertEquals(-1, bankAccount3.getBalance()); //Negative integer equivalece class
        BankAccount bankAccount4 = new BankAccount("a@b.com", 1);
        assertEquals(1, bankAccount4.getBalance()); //positive integer equivalence class
        BankAccount bankAccount5 = new BankAccount("a@b.com", 3.5);
        assertEquals(3.5, bankAccount5.getBalance()); //positive double equivalence class
        BankAccount bankAccount6 = new BankAccount("a@b.com", -18.2);
        assertEquals(-18.2, bankAccount6.getBalance()); //negative double equivalence class
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100); 
        assertEquals(100, bankAccount.getBalance());// equivalence class of positive integer amount withdrawn less than positive integer balance
        bankAccount.withdraw(5.2);
        assertEquals(94.8, bankAccount.getBalance());// equivalence class of positive double amount withdrawn less than positive integer amount
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

        BankAccount bankAccount1 = new BankAccount("a@b.com", 0); // border case
        bankAccount1.withdraw(0); //equivalence class of 0 or even amounts
        assertEquals(0, bankAccount1.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount1.withdraw(1)); // equivalence class of positive integer greater than balance
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.withdraw(-1)); // equivalence class of negative number

        BankAccount bankAccount3 = new BankAccount("a@b.com", 35.32);
        bankAccount3.withdraw(20.18); // equivalence class of positive double amount withdrawn less than positive double balance
        assertEquals(15.14, bankAccount3.getBalance()); 
        assertThrows(InsufficientFundsException.class, () -> bankAccount3.withdraw(20.5));// equivalence class of positive double amount withdrawn greater than positive double balance

        BankAccount bankAccount4 = new BankAccount("a@b.com", 35.75);
        bankAccount4.withdraw(20); // equivalence class of positive integer amount withdrawn less than positive double balance
        assertEquals(15.75, bankAccount4.getBalance()); 
        assertThrows(InsufficientFundsException.class, () -> bankAccount4.withdraw(20));// equivalence class of positive integer amount withdrawn greater than positive double balance
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com")); //Border Case: Minimum characters necessary for a valid email, as the ".com" suffix is the same amount of characters as most others.
        assertFalse(BankAccount.isEmailValid("")); //Border Case: Bare minimum input, string of length 0 if nothing is input
        assertFalse(BankAccount.isEmailValid("@b.com")); // Not Border Case: Equivalence Case, missing required characters
        assertFalse(BankAccount.isEmailValid("@.com")); // Not Border Case: Equivalence Case, missing required characters
        assertFalse(BankAccount.isEmailValid("@b.co")); // Not Border Case: Equivalence Case, missing required characters
        assertFalse(BankAccount.isEmailValid("@bcom")); // Not Border Case: Equivalence Case, missing required characters
        assertFalse(BankAccount.isEmailValid(".@bcom")); // Not Border Case: Equivalence Case, missing required characters
        assertFalse(BankAccount.isEmailValid("LongestEmailEver0123456789@longemaildomain.com")); // Edge Case: Too many characters for a valid email
        assertTrue(BankAccount.isEmailValid("LongestEmailEver123456789@longemaildomain.com")); // Border Case: Max for a valid email
        assertTrue(BankAccount.isEmailValid("LongestEmailEver23456789@longemaildomain.com")); // Edge Case: One less than max characters for a valid email
        
        //Missing Cases
        //Equivalency Case, having the "." Present without the "@" (ab.com)
        //Equivalency Case, having the "." Present before the "@", (a.b@com)
        //Equivalency Case, having the a present but not the b. (a@.com)
        //Equivalency Case, having the "." And the "@" missing, but the a&b. (abcom)


    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    @Test
    void isAmountValidTest(){
        assertTrue(BankAccount.isAmountValid(16.20)); //Equivalence Class: Valid Positive Double with two decimal places
        assertTrue(BankAccount.isAmountValid(8.6)); //Equivalence Class: Valid Positive Double with one decimal place
        assertTrue(BankAccount.isAmountValid(3)); //Equivalence Class: Valid Positive Double with 0 decimal places (essentially an int)
        assertTrue(BankAccount.isAmountValid(0)); //Border Case: on the border between positive and negative Equivalence Class: Valid non-negative Double with 0 decimal places (essentially an int)
        assertFalse(BankAccount.isAmountValid(-1)); //Equivalence Class: Invalid Negative Double with 0 decimal places (essentially an int)
        assertFalse(BankAccount.isAmountValid(-11.6)); //Equivalence Class: Invalid Negative Double with 1 decimal place
        assertFalse(BankAccount.isAmountValid(-23.67)); //Equivalence Class: Invalid Negative Double with 2 decimal places
        assertFalse(BankAccount.isAmountValid(16.204)); //Equivalence Class: Invalid Positive Double with three decimal places
        assertFalse(BankAccount.isAmountValid(12.540)); //Equivalence Class: Invalid Positive Double with three decimal places with the third being 0
        assertFalse(BankAccount.isAmountValid(-37.675)); //Equivalence Class: Invalid Negative Double with 3 decimal places
        assertFalse(BankAccount.isAmountValid(-87.210)); //Equivalence Class: Invalid Negative Double with three decimal places with the third being 0 
    }

}
