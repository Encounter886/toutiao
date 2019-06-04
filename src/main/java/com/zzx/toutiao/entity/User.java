package com.zzx.toutiao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//@Proxy(lazy = true)//,默认开启懒加载
@Entity//jpa实体类和数据表映射
//@Table(name="tb_user")//指定和哪个表对应，省略就采用默认user
//@JsonIgnoreProperties(value = {"fans","visitors"})//需要一一列出需要忽略转json的属性，
// 还是在属性上面添加好一点
public class User  implements Serializable{
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

    //fans粉丝  ,fetch = FetchType.LAZY  测试懒加载
   @JsonIgnore  //可以避免被序列化，返回的json就没有这个属性
    //@ToString.Exclude  //可以避免被打印出来
    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    private Set<Fans> fans = new HashSet<>();

    //visitor访客
    @JsonIgnore
    @OneToMany(mappedBy = "user_v",cascade = CascadeType.PERSIST)
    private Set<Visitor> visitors = new HashSet<>();

    //新闻  news  ,fetch = FetchType.LAZY
    @JsonIgnore
    @OneToMany(mappedBy = "user_news",cascade = CascadeType.PERSIST)
    private Set<News> news = new HashSet<>();


    //history 访问历史  测试懒加载 只能通过这种弄避免被序列化的方式实现懒加载
   @JsonIgnore
    @OneToMany(mappedBy = "user_hsitory",cascade = CascadeType.PERSIST)
    private Set<History> histories = new HashSet<>();

    //clllection收藏
    @JsonIgnore
    @OneToMany(mappedBy = "user_collection",cascade = CascadeType.PERSIST)
    private Set<Collection> collections = new HashSet<>();

    //搜搜历史
    @JsonIgnore
    @OneToMany(mappedBy = "user_search",cascade = CascadeType.PERSIST)
    private Set<SearchHistory> searchHistories = new HashSet<>();
}
