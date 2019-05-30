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
public class Collection {
    //收藏
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    private Integer new_id;//新闻id


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user_collection;

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", new_id=" + new_id +
                '}';
    }
}
