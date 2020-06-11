package com.ikdzz.test;

import com.ikdzz.dao.shirodao.IUserdao;
import com.ikdzz.domain.shiro.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMyBatis {

    @Test
    public void run1() throws IOException {
        User user =new User();
        user.setUsername("lu");
       // user.setMoney(200);
        // 加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqlSession对象
        SqlSession session = factory.openSession();
        // 获取到代理对象
        IUserdao dao = session.getMapper(IUserdao.class);

        // 保存
        //dao.saveAccount(user);

        // 提交事务
        session.commit();

        // 关闭资源
        session.close();
        in.close();
    }

    @Test
    public void run2() throws Exception {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        SqlSession session = factory.openSession();

        IUserdao dao = session.getMapper(IUserdao.class);

        List<User> list = dao.findAll();
        for (User user : list ) {
            System.out.println(user);
        }

        session.close();
        in.close();
    }


}

