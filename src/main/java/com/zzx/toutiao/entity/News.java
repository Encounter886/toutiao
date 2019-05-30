package com.zzx.toutiao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity//jpa实体类和数据表映射

public class News implements Serializable {
//所发表的新闻，即动态
@Id//主键
@GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
private Integer id;

private String title;//文章标题

  private  String Content;//文本内容


    //此处先写实现一张图片和一个视频路径
    //之后再过来实现 多张图片，建立img类，通过关联实现

    private String img_path;//图片路径

    private  String video_path;//视频路径


   private String time;//发布时间



  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user_news;


  //comment评论
  @OneToMany(mappedBy = "news_commment",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
  private Set<Comment> comments = new HashSet<>();

  @Override
  public String toString() {
    return "News{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", Content='" + Content + '\'' +
            ", img_path='" + img_path + '\'' +
            ", video_path='" + video_path + '\'' +
            ", time='" + time + '\'' +
            ", comments=" + comments +
            '}';
  }
}
