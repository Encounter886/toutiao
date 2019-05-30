package com.zzx.toutiao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity//jpa实体类和数据表映射
//news的评论
public class Comment {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    private  Integer user_id;


    private  String content;//评论内容

    private  String time;//评论时间

    @ManyToOne
    @JoinColumn(name = "news_id")
    @JsonIgnore
    private News news_commment;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
