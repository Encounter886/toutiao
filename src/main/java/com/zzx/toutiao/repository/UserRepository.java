package com.zzx.toutiao.repository;


import com.zzx.toutiao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//继承jparepository操作数据库
public interface UserRepository extends JpaRepository<User,Integer> {


       User findByUsernameAndPassword(String username,String password);

       User findByPhone(String phone);
}
