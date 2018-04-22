package com.qpf.model.test;

import com.qpf.model.db.User;
import com.qpf.model.db.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

public class UserTest {
    private Logger logger;
    private SqlSession session;

    @Before
    public void setUp() throws IOException {
        logger = Logger.getLogger(getClass());
        String resource = "config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        session = factory.openSession();
        logger.info("open Session");
    }
    @After
    public void tearDowm(){
        session.close();
        logger.info("close Session");
    }

    @Test
    public void testUser(){
        User user = new User("123456", "Shadaileng", "26", "1", "qpf0510@qq.com", "./res/heading.png", "2018-04-20", "2018-04-20");

        System.out.println(user);
        System.out.println(user.getFields());
        System.out.println(user.getFieldsLength());
    }

    @Test
    public void selectByFullSqlTest(){
        logger.info("working");
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<Map<String, Object>> maps = mapper.selectUserByFullSql("select * from user");
        logger.info(maps);
    }

    @Test
    public void selectUserByIdTest(){
        logger.info("working");
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User("1");
        user = mapper.selectUserById(user);
        logger.info(user);
    }

    @Test
    public void selectUserByBeanTest(){
        logger.info("working");
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User("1");
        user.setName("Shadaileng");
        List<User> users = mapper.selectUserByBean(user);
        logger.info(users);
    }

    @Test
    public void selectUserByConditionsTest(){
        logger.info("working");
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.selectUserByConditions("NAME = 'Shadaileng'", "AGE", "NAME");
        logger.info(users);
    }

    @Test
    public void updateUserByIdTest(){
        logger.info("working");
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User("123456", "Shadaileng", "26", "1", "qpf0510@qq.com", "./res/heading.png", "2018-04-20", "2018-04-20");
        user.setId("1");
        mapper.updateUserById(user);
    }

    @Test
    public void deleteUserByIdTest(){
        logger.info("working");
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User("123456", "Shadaileng", "26", "1", "qpf0510@qq.com", "./res/heading.png", "2018-04-20", "2018-04-20");
        user.setId("1");
        mapper.deleteUserById(user);
    }

    @Test
    public void insertUserTest(){
        logger.info("working");
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User("123456", "Shadaileng", "26", "1", "qpf0510@qq.com", "./res/heading.png", "2018-04-20", "2018-04-20");
        user.setId("1");
        mapper.insertUser(user);
        session.commit();
    }
}
