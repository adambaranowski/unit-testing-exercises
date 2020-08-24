package pl.adambaranowski.testing.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Test
    void getAllActiveAccounts() {
        //given
        AccountRepository accountRepository = new AccountRepositoryStub();
        AccountService accountService = new AccountService(accountRepository);

        //when
        List<Account>  accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(2));
    }

    //Mock instead stub

    @Test
    void getAllActiveAccountsMock(){
        //given
        List<Account> accounts = prepareAccountData();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);

        //First Option
        //when(accountRepository.getAllAccounts()).thenReturn(accounts);

        //Second Option
        given(accountRepository.getAllAccounts()).willReturn(accounts);

        //when
        List<Account>  accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(2));
    }

    @Test
    void getNoActiveAccountsMock(){
        //given
        List<Account> accounts = prepareAccountData();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);

        //First Option
        //when(accountRepository.getAllAccounts()).thenReturn(accounts);

        //Second Option
        given(accountRepository.getAllAccounts()).willReturn(Collections.emptyList());

        //when
        List<Account>  accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(0));
    }

    private List<Account> prepareAccountData(){
        Address address1 = new Address("puciakow", "33/5");
        Account account1 = new Account(address1);

        Account account2 = new Account();

        Address address3 = new Address("słodziakowa", "23/5");
        Account account3 = new Account(address3);

        List.of(account1,account2,account3);
        return Arrays.asList(account1, account2, account3);

    }


}