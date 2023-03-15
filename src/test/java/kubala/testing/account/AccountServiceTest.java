package kubala.testing.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest { //implementacja mockow

    @Test
    void getAllActiveAccounts() {

        //given
        List<Account> accounts = prepareAccountData();
        AccountRepository accountRepository = mock(AccountRepository.class);  //korzystamy z mocka dzieki metodzie mock(), z biblioteki mockito
        AccountService accountService = new AccountService(accountRepository);
        //when(accountRepository.getAllAccounts()).thenReturn(accounts);        //jesli na accountRepository wywolamy metode getAllAccount, wtedy zwroc liste accounts.
        given(accountRepository.getAllAccounts()).willReturn(accounts);        //to samo co wyzej, tylko bez uzycia slow when, thenReturn a given, willReturn - paczka BDD

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(2));
    }

    @Test
    void getNoActiveAccounts() {

        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(Collections.emptyList());

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(0));
    }

    //metoda pomocniczna do getAllActiveAccounts()
    private List<Account> prepareAccountData() {
        Address address1 = new Address("Krakowska", "6");
        Account account1 = new Account(address1);                       // konto aktywne

        Account account2 = new Account();                               // konto nieaktywne (adres = null)

        Address address2 = new Address("Kanonicza", "2137");
        Account account3 = new Account(address2);                       //konto aktywne

        return Arrays.asList(account1, account2, account3);
    }
}
