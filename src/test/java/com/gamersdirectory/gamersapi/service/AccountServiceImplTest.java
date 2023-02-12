package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.AppConfigTest;
import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.dto.InterestDTO;
import com.gamersdirectory.gamersapi.entity.Account;
import com.gamersdirectory.gamersapi.entity.Location;
import com.gamersdirectory.gamersapi.exception.ApiNotFoundException;
import com.gamersdirectory.gamersapi.repository.AccountRepository;
import com.gamersdirectory.gamersapi.repository.GameRepository;
import com.gamersdirectory.gamersapi.repository.LevelRepository;
import com.gamersdirectory.gamersapi.repository.LocationRepository;
import com.gamersdirectory.gamersapi.utils.ErrorMessageEnum;
import com.gamersdirectory.gamersapi.validation.AccountForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

//@ExtendWith(MockitoExtension.class)
@DisplayName("AccountServiceImplTest")
class AccountServiceImplTest extends AppConfigTest {

    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private LocationRepository locationRepository;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private LevelRepository levelRepository;

    @Autowired
    private AccountServiceImpl accountService;

    @Captor
    private ArgumentCaptor<Account> accountArgumentCaptor;

    @Test
    @DisplayName("Should Find and Return Location given location String property")
    void shouldReturnLocation_WhenFindLocationMethodIsCalled_GivenLocationStringProperty() {
        String locationInput = "europe";
        Location locationMock = Mockito.mock(Location.class);
        Optional<Location> locationOptional = Optional.of(locationMock);
        Mockito.when(locationOptional.get().getId()).thenReturn(Long.valueOf("1"));
        Mockito.when(locationOptional.get().getName()).thenReturn(locationInput);

        AccountForm accountForm = new AccountForm("account-name", "account-nickname", locationInput, Collections.emptyList());

        Account accountMock = Mockito.mock(Account.class);
        Mockito.when(accountMock.getId()).thenReturn(1L);
        Mockito.when(accountMock.getName()).thenReturn(accountForm.getName());
        Mockito.when(accountMock.getNickname()).thenReturn(accountForm.getNickname());
        Mockito.when(accountMock.getLocation()).thenReturn(locationMock);

        AccountDTO accountDTO = Mockito.mock(AccountDTO.class);
        Mockito.when(accountDTO.getId()).thenReturn(1L);
        Mockito.when(accountDTO.getName()).thenReturn(accountForm.getName());
        Mockito.when(accountDTO.getNickname()).thenReturn(accountForm.getNickname());
        Mockito.when(accountDTO.getLocation()).thenReturn(accountForm.getLocation());
        Mockito.when(accountDTO.getInterests()).thenReturn(accountForm.getInterests());

        Mockito.when(locationRepository.findLocationByName(locationInput))
                .thenReturn(locationOptional);
        Mockito.when(accountRepository.save(accountMock)).thenReturn(accountMock);

        // TODO: add assertions

    }

    @Test
    @DisplayName("Throw ApiNotFoundException when Location Not Found")
    void shouldFailWhenLocationDoesNotExist() {
        AccountForm accountForm = new AccountForm();
        accountForm.setLocation("NOT A LOCATION");

        assertThatThrownBy(() -> accountService.save(accountForm))
                .isInstanceOf(ApiNotFoundException.class)
                .hasMessageContaining(ErrorMessageEnum.LOCATION_NOT_FOUND.getMessage(), accountForm.getLocation());
    }

    @Test
    @DisplayName("Should Save Account")
    void shouldSaveAccount() {
        InterestDTO interestDTO1 = new InterestDTO("gta", "noob");
        InterestDTO interestDTO2 = new InterestDTO("fortnite", "pro");
        List<InterestDTO> interestDTOList = new ArrayList<>(
                List.of(interestDTO1, interestDTO2)
        );
        AccountForm accountForm = new AccountForm("account name", "account nickname", "europe", interestDTOList);

        accountService.save(accountForm);

        // TODO: add assertions
    }

    @DisplayName("Should Save Account With Empty Interests List")
    void shouldSaveAccountWithEmptyInterestsList() {
        AccountForm accountForm = new AccountForm("account-name", "account-nickname", "europe", Collections.emptyList());

        Location locationTest = new Location();
        locationTest.setId(1L);
        locationTest.setName("europe");

        Account expectedPersistedAccount = new Account(1L, accountForm.getName(), accountForm.getNickname(), locationTest, Collections.emptyList());
        AccountDTO expectedAccountDTOResponse = new AccountDTO(expectedPersistedAccount);

        Mockito.when(locationRepository.findLocationByName(accountForm.getLocation())).thenReturn(Optional.of(locationTest));

        Mockito.when(accountService.save(accountForm)).thenReturn(expectedAccountDTOResponse);

        accountService.save(accountForm);
        Mockito.verify(accountRepository, Mockito.times(1)).save(accountArgumentCaptor.capture());

        Assertions.assertThat(accountArgumentCaptor.getValue().getId()).isEqualTo(1);
        Assertions.assertThat(accountArgumentCaptor.getValue().getName()).isEqualTo("account-name");
        Assertions.assertThat(accountArgumentCaptor.getValue().getNickname()).isEqualTo("account-nickname");
        Assertions.assertThat(accountArgumentCaptor.getValue().getLocation().getName()).isEqualTo("europe");
        Assertions.assertThat(accountArgumentCaptor.getValue().getInterests()).isEmpty();
    }

    @Test
    @DisplayName("Should Find Account By Id With Empty Interests List")
    void shouldFindAccountByIdWithEmptyInterestsList() {
        AccountForm accountForm = new AccountForm("account-name", "account-nickname", "europe", Collections.emptyList());

        Location locationTest = new Location();
        locationTest.setId(1L);
        locationTest.setName(accountForm.getName());

        Account accountToSave = new Account();
        accountToSave.setName(accountForm.getName());
        accountToSave.setNickname(accountForm.getNickname());
        accountToSave.setLocation(locationTest);

        Account expectedPersistedAccount = new Account(1L, accountForm.getName(), accountForm.getNickname(), locationTest, Collections.emptyList());
        AccountDTO expectedAccountDTOResponse = new AccountDTO(expectedPersistedAccount);

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(expectedPersistedAccount));

        AccountDTO actualAccountDTOResponse = accountService.findById(1L);

        Assertions.assertThat(actualAccountDTOResponse.getId()).isEqualTo(expectedPersistedAccount.getId());
        Assertions.assertThat(actualAccountDTOResponse.getName()).isEqualTo(expectedPersistedAccount.getName());
        Assertions.assertThat(actualAccountDTOResponse.getNickname()).isEqualTo(expectedPersistedAccount.getNickname());
        Assertions.assertThat(actualAccountDTOResponse.getLocation()).isEqualTo(expectedPersistedAccount.getLocation().getName());
        Assertions.assertThat(actualAccountDTOResponse.getInterests()).isEmpty();
    }



    @Test
    @DisplayName("Throw ApiNotFoundException when Account Id Not Found")
    void shouldThrowApiNotFoundException_WhenGetAccountById() {
        Long accountId = 5L;

        assertThatThrownBy(() -> accountService.findById(accountId))
                .isInstanceOf(ApiNotFoundException.class)
                .hasMessageContaining(ErrorMessageEnum.ACCOUNT_ID_NOT_FOUND.getMessage(), accountId);
    }

    private AccountForm createAccountForm() {
        InterestDTO interestDTO1 = new InterestDTO("gta", "noob");
        InterestDTO interestDTO2 = new InterestDTO("fortnite", "pro");
        List<InterestDTO> interestDTOList = new ArrayList<>(
                List.of(interestDTO1, interestDTO2)
        );

        return new AccountForm("account name", "account nickname", "europe", interestDTOList);
    }

    private AccountForm createAccountFormWithEmptyInterestList() {
        AccountForm accountForm = Mockito.mock(AccountForm.class);
        Mockito.when(accountForm.getName()).thenReturn("Account Form Name");
        Mockito.when(accountForm.getNickname()).thenReturn("Account Form Nickname");
        Mockito.when(accountForm.getLocation()).thenReturn("asia");
        Mockito.when(accountForm.getInterests()).thenReturn(Collections.emptyList());
        return accountForm;
    }
}