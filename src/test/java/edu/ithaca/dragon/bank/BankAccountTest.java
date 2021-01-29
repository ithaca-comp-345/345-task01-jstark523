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
        assertThrows(InsufficientFundsException.class, () -> bankAccount1.withdraw(-1)); // equivalence class of negative number

        BankAccount bankAccount3 = new BankAccount("a@b.com", 35.6);
        bankAccount3.withdraw(20.2); // equivalence class of positive double amount withdrawn less than positive double balance
        assertEquals(15.4, bankAccount3.getBalance()); 
        assertThrows(InsufficientFundsException.class, () -> bankAccount3.withdraw(20.5));// equivalence class of positive double amount withdrawn greater than positive double balance

        BankAccount bankAccount4 = new BankAccount("a@b.com", 35.6);
        bankAccount3.withdraw(20); // equivalence class of positive integer amount withdrawn less than positive double balance
        assertEquals(15.6, bankAccount3.getBalance()); 
        assertThrows(InsufficientFundsException.class, () -> bankAccount3.withdraw(20));// equivalence class of positive integer amount withdrawn greater than positive double balance
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse(BankAccount.isEmailValid(""));
        assertFalse(BankAccount.isEmailValid("@b.com"));
        assertFalse(BankAccount.isEmailValid("@.com"));
        assertFalse(BankAccount.isEmailValid("@b.co"));
        assertFalse(BankAccount.isEmailValid("@bcom"));
        assertFalse(BankAccount.isEmailValid("LongestEmailEver0123456789@longemaildomain.com"));
        assertFalse(BankAccount.isEmailValid("LongestEmailEver123456789@longemaildomain.com"));
        assertFalse(BankAccount.isEmailValid("LongestEmailEver23456789@longemaildomain.com"));


    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}