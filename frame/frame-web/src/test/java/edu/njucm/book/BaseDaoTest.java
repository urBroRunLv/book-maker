package edu.njucm.book;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单元测试基类
 *
 * @author lvrongwang
 * @since 2020/2/6 14:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/spring.xml")
@Transactional(transactionManager = "dataSourceTransactionManager")
@Rollback(value = false)
public abstract class BaseDaoTest {

    @Before
    public void before() {
        System.out.println("============================= TEST START =============================");
    }

    @After
    public void after() {
        System.out.println("============================= TEST END =============================");
    }
}
