package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.entity.Account;
import com.gamersdirectory.gamersapi.entity.Location;
import com.gamersdirectory.gamersapi.exception.ApiNotFoundException;
import com.gamersdirectory.gamersapi.repository.AccountRepository;
import com.gamersdirectory.gamersapi.repository.GameRepository;
import com.gamersdirectory.gamersapi.repository.LevelRepository;
import com.gamersdirectory.gamersapi.repository.LocationRepository;
import com.gamersdirectory.gamersapi.validation.AccountForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private LevelRepository levelRepository;
    private AccountService accountService;

    @Captor
    private ArgumentCaptor<Account> accountArgumentCaptor;

    @BeforeEach
    public void setup() {
        accountService = new AccountServiceImpl(accountRepository, locationRepository, gameRepository, levelRepository);
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
    @DisplayName("Throw ApiRequestException when Location doesn't exist")
    void shouldFailWhenLocationDoesNotExist() {
        AccountForm accountForm = new AccountForm();
        accountForm.setLocation("WRONG-LOCATION");

        assertThatThrownBy(() -> accountService.save(accountForm))
                .isInstanceOf(ApiNotFoundException.class)
                .hasMessageContaining("Location [ %s ] does not exist.", accountForm.getLocation());
    }
}