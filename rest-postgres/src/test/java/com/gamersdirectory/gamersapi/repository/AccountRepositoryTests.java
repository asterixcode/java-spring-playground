//package com.gamersdirectory.gamersapi.repository;
//
//import com.gamersdirectory.gamersapi.domain.Account;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Disabled
//@DataJpaTest
//@ActiveProfiles("test")
//class AccountRepositoryTests {
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Test
//    void shouldSaveAccount() {
//        Location locationTest = new Location();
//        locationTest.setId(null);
//        locationTest.setName("europe");
//
//        Account accountToSave = new Account(null, "test name", "test nickname", locationTest, Collections.emptyList());
//
//        Account savedAccount = accountRepository.save(accountToSave);
//
//        assertThat(savedAccount).usingRecursiveComparison().ignoringFields("id").isEqualTo(accountToSave);
//    }
//
//    @Test
//    @Sql("classpath:test-data.sql")
//    void shouldSaveAccountThroughSqlFile() {
//        Optional<Account> account = accountRepository.findAccountByName("test-name-sql");
//        assertThat(account).isNotEmpty();
//    }
//
//}
