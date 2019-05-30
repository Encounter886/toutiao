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
public class Visitor {
    //来访者
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;


    private Integer visitors_id;//访问者id

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_v;

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", visitors_id=" + visitors_id +
                '}';
    }
}
