package kubala.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void newAccountShouldNotBeActiveAfterCreation() {
        //given
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive(), "Check if new account is not active");
    }

    @Test
    public void accountShouldBeActiveAfterActivation() {
        //given
        Account newAccount = new Account();

        //when
        newAccount.activate();

        //then
        assertTrue(newAccount.isActive());
    }

    @Test
    public void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet() {

        //given
        Account account = new Account();

        //while
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
    }

    @Test
    public void defaultDeliveryAddressShouldNotBeNullAfterBeingSet() {

        //given
        Address address = new Address("Krakowska", "6");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        //when
        Address defaultAddress = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(defaultAddress);
    }

}
