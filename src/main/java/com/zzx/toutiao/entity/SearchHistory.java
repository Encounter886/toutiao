package com.zzx.toutiao.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity//jpa实体类和数据表映射
public class SearchHistory {

    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    private String search_cache;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_search;

    @Override
    public String toString() {
        return "searchHistory{" +
                "id=" + id +
                ", search_cache='" + search_cache + '\'' +
                '}';
    }
}
