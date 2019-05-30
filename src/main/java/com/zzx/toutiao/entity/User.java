package com.zzx.toutiao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Getter @Setter @ToString
@Proxy(lazy = true)//,懒加载
@Entity//jpa实体类和数据表映射
//@Table(name="tb_user")//指定和哪个表对应，省略就采用默认user

public class User implements Serializable {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    @Column(name="username")
    private String username;


    @Column(name="password")
    private String password;

    @Column(name="phone")
    private  String phone;

    @Column(name="head_portrait")
    private String head_portrait;//头像路径

    @Column(name="name",length = 50)
    private  String name;

    @Column//省略默认列名就是属性名
    private String  sex;

    @Column
    private  String birthday;//生日

    //fans粉丝
    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    private Set<Fans> fans = new HashSet<>();

    //visitor访客
    @OneToMany(mappedBy = "user_v",cascade = CascadeType.PERSIST)
    private Set<Visitor> visitors = new HashSet<>();

    //新闻  news
    @OneToMany(mappedBy = "user_news",cascade = CascadeType.PERSIST)
    private Set<News> news = new HashSet<>();


    //history 访问历史  测试懒加载
    @ToString.Exclude
    @OneToMany(mappedBy = "user_hsitory",cascade = CascadeType.PERSIST)
    private Set<History> histories = new HashSet<>();

    //clllection收藏
    @OneToMany(mappedBy = "user_collection",cascade = CascadeType.PERSIST)
    private Set<Collection> collections = new HashSet<>();

    //clllection收藏
    @OneToMany(mappedBy = "user_search",cascade = CascadeType.PERSIST)
    private Set<SearchHistory> searchHistories = new HashSet<>();
}
