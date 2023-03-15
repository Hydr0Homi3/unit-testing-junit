package kubala.testing.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepositoryStub implements AccountRepository{
    @Override
    public List<Account> getAllAccounts() { // metoda stubowa. prawdziwa implementacja interfejsu, ktora zwraca jakis przykladowy zestaw danych.

        Address address1 = new Address("Krakowska", "6");
        Account account1 = new Account(address1);                       // konto aktywne

        Account account2 = new Account();                               // konto nieaktywne (adres = null)

        Address address2 = new Address("Kanonicza", "2137");
        Account account3 = new Account(address2);                       //konto aktywne

        return Arrays.asList(account1, account2, account3);
    }
}
