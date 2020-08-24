package pl.adambaranowski.testing.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pl.adambaranowski.testing.account.Account;
import pl.adambaranowski.testing.account.Address;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {

    @Test
     void newAccuontShouldNotBeActiveAfterCreation(){
        //given
        //when
        Account account = new Account();
        //then
        assertFalse(account.isActive(), "Check if new account is not active");
    }

    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account account = new Account();

        //when
        account.activte();
        //then
        assertTrue(account.isActive());
        assertThat(account.isActive(), equalTo(true));
    }

    @Test
    void accountShouldNotBeActiveAfterActivation(){
        //given
        Account account = new Account();

        //when
        //then
        assertFalse(account.isActive());
        assertThat(account.isActive(), equalTo(false));


    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet(){
        //given
        Account  account= new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address, is(nullValue()));
    }

    @RepeatedTest(20)
    void newAccountWithNotNullAddressShouldBeActive(){
        //given
        Address address = new Address("Puławska", "65");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address!=null, () -> {
            assertTrue(account.isActive());
        });

    }
}
