package com.arthur.springdatajpastartup.repository;

import com.arthur.springdatajpastartup.SpringDataJpaStartupApplication;
import com.arthur.springdatajpastartup.entity.CreditLine;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataJpaStartupApplication.class)
@SqlGroup({
    @Sql("/init-4-test.sql"),
    @Sql(scripts = "/clean-4-test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class CreditLineRepositoryTest {


    private static Logger logger = LoggerFactory.getLogger(CreditLineRepositoryTest.class);

    @Autowired
    private CreditLineRepository creditLineRepository;

    @BeforeClass

    public static void setUpBeforeClass() throws Exception {
        logger.info("in setUpBeforeClass");
    }

    @AfterClass

    public static void tearDownAfterClass() throws Exception {
        logger.info("in tearDownAfterClass");
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void sumUserPointsOfSpeicalDayByChangeType() {

       Long sum = creditLineRepository.sumUserPointsOfSpeicalDayByChangeType(-1L,"ERN", "2018-08-06");
       assertThat(sum).isEqualTo(5L);
    }

    @Test
    public void findByUserIdAndChangeType() {
        List<CreditLine> lists = creditLineRepository.findByUserIdAndChangeType(-1L, "ERN");
        assertThat(lists).isNotNull();
        assertThat(lists.size()).isEqualTo(9);

    }


    @Test
    public void calculateSignInPoint() {

        Integer points = creditLineRepository.calculateSignInPoint(-1L, "2018-08-01");
        assertThat(points).isEqualTo(0);
        points = creditLineRepository.calculateSignInPoint(-1L, "2018-08-07");
        assertThat(points).isEqualTo(5);
        points = creditLineRepository.calculateSignInPoint(-1L, "2018-08-14");
        assertThat(points).isEqualTo(4);

    }


    @Test
    public void findByUserIdAndChangeTypePaginationByNameConvertion() {
        Pageable pageable = PageRequest.of(1, 2);
        Page<CreditLine> page =  creditLineRepository.findByUserIdAndChangeTypeOrderByChangeOnDttmAsc(-1L, "ERN", pageable);
        assertThat(page.getTotalElements()).isEqualTo(9);
        assertThat(page.getTotalPages()).isEqualTo(5);
        assertThat(page.getContent().get(0).getChangeOnDtStr()).isEqualTo("2018-08-03");
        assertThat(page.getContent().get(0).getCreditPoint()).isEqualTo(3);
    }


    @Test
    public void pageByUserIdAndChangeType() {
        Pageable pageable = PageRequest.of(1, 2);
        Page<CreditLine> page =  creditLineRepository.pageByUserIdAndChangeType(-1L, "ERN", pageable);
        assertThat(page.getTotalElements()).isEqualTo(9);
        assertThat(page.getTotalPages()).isEqualTo(5);
        assertThat(page.getContent().get(0).getChangeOnDtStr()).isEqualTo("2018-08-03");
        assertThat(page.getContent().get(0).getCreditPoint()).isEqualTo(3);
    }

    @Test
    public void pageByUserIdAndChangeTypeNativeSql() {
        Pageable pageable = PageRequest.of(1, 2);
        Page<CreditLine> page =  creditLineRepository.pageByUserIdAndChangeTypeNativeSql(-1L, "ERN", pageable);
        assertThat(page.getTotalElements()).isEqualTo(9);
        assertThat(page.getTotalPages()).isEqualTo(5);
        assertThat(page.getContent().get(0).getChangeOnDtStr()).isEqualTo("2018-08-03");
        assertThat(page.getContent().get(0).getCreditPoint()).isEqualTo(3);
    }

    @Test
    public void dynamicQueryByQueryByExample(){
        CreditLine line = new CreditLine();
        line.setUserId(-1L);
        line.setChangeType("ERN");
        line.setChangeOnDtStr("2018-08-13");
        Example<CreditLine> example = Example.of(line);
        List<CreditLine> list = creditLineRepository.findAll(example);
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getChangeOnDttm()).isEqualTo(9L);
    }


}