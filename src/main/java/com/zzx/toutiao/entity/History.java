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
public class History {
    //访问历史
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    private Integer new_id;//新闻id

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_hsitory;

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", new_id=" + new_id +
                '}';
    }
}
