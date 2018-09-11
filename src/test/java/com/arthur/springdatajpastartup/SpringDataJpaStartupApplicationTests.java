package com.arthur.springdatajpastartup;

import com.arthur.springdatajpastartup.repository.CreditLineRepositoryTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@RunWith(Suite.class)
@Suite.SuiteClasses({CreditLineRepositoryTest.class})
//@SqlGroup({
//        @Sql("/init-4-test.sql"),
//        @Sql(scripts = "/clean-4-test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//})
public class SpringDataJpaStartupApplicationTests {

    @Test
    public void contextLoads() {
    }

}
