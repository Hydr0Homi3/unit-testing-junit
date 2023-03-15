package kubala.testing.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

class AccountServiceStubTest {

    @Test
    void getAllActiveAccounts() {

        //given
        AccountRepository accountRepositoryStub = new AccountRepositoryStub();
        AccountService accountService = new AccountService(accountRepositoryStub);  //w new AccountService(stub) tworzymy stuba gdyz nie mamy dostepu do prawdziwej metody ktora zwraca dane z bazy

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(2));
    }
}