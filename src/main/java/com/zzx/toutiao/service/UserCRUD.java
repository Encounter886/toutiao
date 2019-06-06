package com.zzx.toutiao.service;

import com.zzx.toutiao.entity.*;
import com.zzx.toutiao.repository.NewsRepository;
import com.zzx.toutiao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserCRUD {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NewsRepository newsRepository;

    public User LoginByName(String username,String password){
        User user = userRepository.findByUsernameAndPassword(username,password);
        System.out.println("loginbyname查询模块结果"+user);
        if(user != null)
            return user;
        System.out.println("登录失败");
        return null;
    }

    public User  LoginByPhone(String phone,String code){
        User user = userRepository.findByPhone(phone);
        System.out.println("loginbyname查询模块结果"+user);
        if(user != null)
            return user;
        System.out.println("登录失败");
        return null;
    }

    @Transactional
   public List<User> findVisitorList(Integer user_id){

        User user= userRepository.findById(user_id).get();

       Set<Visitor> visitors = user.getVisitors();
        if(visitors ==null)
        return null;
        List<User> users = new ArrayList<>();
        for (Visitor v:visitors
             ) {
            Integer id =  v.getVisitors_id();
            User u = userRepository.findById(id).get();
           users.add(u);

        }
        return users;
   }


    public List<User> findFans(Integer user_id){

        User user= userRepository.findById(user_id).get();

        Set<Fans> fans = user.getFans();
        if(fans ==null)
            return null;
        List<User> users = new ArrayList<>();
        for (Fans f:fans
        ) {
            Integer id =  f.getFans_id();
            User u = userRepository.findById(id).get();
            users.add(u);

        }
        return users;
    }





    public Set<News> findNewsById(Integer user_id){

        User user= userRepository.findById(user_id).get();

        Set<News> news = user.getNews();
        if(news ==null)
            return null;

        return news;
    }

    public  Set<String> findSearchHistory(Integer id){

        User user= userRepository.findById(id).get();
        Set<SearchHistory>  searchHistorys =  user.getSearchHistories();
       if(searchHistorys == null)
        return null;
        Set<String> searchs = new HashSet<>();
        for (SearchHistory s:searchHistorys
             ) {
            searchs.add(s.getSearch_cache());
        }
        return searchs;
    }


    public Set<News> findNewsHistory(Integer id){

        User user= userRepository.findById(id).get();
        Set<History> histories = user.getHistories();
        Set<News>  historyNews = new HashSet<>();
        for (History h:histories
             ) {
            Integer h_id = h.getNew_id();
             News n = newsRepository.findById(h_id).get();
            historyNews.add(n);
        }
         if (historyNews == null)
        return null;
         return historyNews;
    }

    public Set<News> findNewsCollection(Integer id){

        User user= userRepository.findById(id).get();
        Set<Collection> collections = user.getCollections();
        Set<News> collectionNews = new HashSet<>();
        for (Collection c:collections
        ) {
            Integer c_id = c.getNew_id();
            News n = newsRepository.findById(c_id).get();
            collectionNews.add(n);
        }
        if (collectionNews == null)
            return null;
        return collectionNews;
    }


}
