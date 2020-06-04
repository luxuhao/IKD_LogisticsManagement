package com.ikdzz.dao;

import com.ikdzz.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  //此注解代表这是一个持久层，用法类似@controller、@service 饿呢
public interface IAccountdao {

    @Select("select * from account")
    public List<Account> findAll();
    @Insert("insert into account (name,money) values (#{name},#{money})")
    public void saveAccount(Account account);
}
