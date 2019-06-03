package com.zzx.toutiao;

import com.zzx.toutiao.entity.*;
import com.zzx.toutiao.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ToutiaoApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Test
    public void contextLoads() {
    }


   @Test
    public void testadd(){
        User user  = new User();
        user.setName("wangmouren");

        Fans fans = new Fans();
         fans.setFans_id(1);

        Visitor visitor= new Visitor();
        visitor.setVisitors_id(1);

          News news = new News();
          news.setTime(new Date().toString());
          news.setTitle("haha");

         Collection collection =new Collection();
         collection.setNew_id(news.getId());


         Comment comment  = new Comment();
         comment.setContent("woshi评论");
         news.getComments().add(comment);
         comment.setNews_commment(news);

         History history = new History();
         history.setNew_id(1);


        user.getFans().add(fans);
        user.getVisitors().add(visitor);
          user.getNews().add(news);
          user.getCollections().add(collection);
          user.getHistories().add(history);

        fans.setUser(user);
        visitor.setUser_v(user);
        news.setUser_news(user);
        collection.setUser_collection(user);
        history.setUser_hsitory(user);
        this.userRepository.save(user);
   // System.out.println(this.userRepository.findById(1));
    }

@Transactional//加上事务就可以懒加载了
    @Test
    public void selectall(){
       List<User> u = this.userRepository.findAll();
       // List<User> u = (List<User>) this.userRepository.findAll();
       // System.out.println("一下是查询的内容"+u);

        System.out.println("-------------------------------");
        System.out.println(u.get(0)+"-----------");
    System.out.println(u.get(0).getFans()+"-----------");
    }


}
