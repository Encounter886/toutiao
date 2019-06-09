package com.zzx.toutiao.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.zzx.toutiao.entity.*;
import com.zzx.toutiao.repository.NewsRepository;
import com.zzx.toutiao.repository.UserRepository;
import com.zzx.toutiao.service.UserCRUD;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

//@Transactional
@RestController
@CrossOrigin
public class UserController {

    //slf4j.Logger
    private  static final Logger log = LoggerFactory.getLogger(UserController.class);
@Autowired
UserRepository userRepository;

@Autowired
    NewsRepository newsRepository;

@Autowired
UserCRUD userCRUD;


@RequestMapping(value = "/loginByUserName",method = RequestMethod.POST)
public User userLoginByName(@RequestParam("username") String username,
                            @RequestParam("password")String password){
//此处测试git
              System.out.println("进入controller层loginbyname:");
             User user = userCRUD.LoginByName(username,password);
              if(user==null)
              return null;
              else
                  return user;


}



  @PostMapping("/loginByPhoneNumber")
    public User userLoginByPhone(String phoneNumber,String  kdentifying_code){

      System.out.println("进入controller层loginbyphone:");
      User user = userCRUD.LoginByPhone(phoneNumber,kdentifying_code);
      if(user==null)
          return null;
      else
          return user;
    }

    @PostMapping("/register_phone")
    public User registerByPhone(String phone,
                                String  kdentifying_code){
        System.out.println("进入注册controller phone:"+phone);
      User user = new User();
      user.setPhone(phone);
      user.setUsername("default");
       this.userRepository.save(user);
       return  user;
    }

    @PostMapping("/visitor_list")//访客记录
    public Rsponse visitorList(@RequestParam("id")Integer user_id){
        System.out.println("进入查询访客记录模块");
         List<User> users = userCRUD.findVisitorList(user_id);
         if (users==null)
        return null;
        System.out.println("查询访客记录："+users);
        //此处注意逻辑关系
        Rsponse<List<User>> rsponse = new Rsponse<List<User>>();
        rsponse.setList(users);
        rsponse.setCode(200);
        System.out.println(rsponse);
        return rsponse ;
    }

    /**
     *
     * @param user_id
     * @return
     */
    @PostMapping("/fans_list")//粉丝列表
    public Rsponse fansList(@RequestParam("id")Integer user_id){

        System.out.println("进入查询粉丝列表模块");
        List<User> users = userCRUD.findFans(user_id);

        if (users==null)
            return null;
        System.out.println("查询粉丝记录："+users);

        Rsponse<List<User>> rsponse = new Rsponse<List<User>>();
        rsponse.setList(users);
        rsponse.setCode(200);
        System.out.println(rsponse);
        return rsponse;
    }

    @PostMapping("/article_list")//所有新闻列表,抓取服务器列表
    public Rsponse articleListAll(){
        System.out.println("进入抓取所有新闻接口");

        List<News> news =  newsRepository.findAll();
        if(news==null)
        return null;
        System.out.println("查询的新闻列表为："+news);
        Rsponse<List<News>> rsponse = new Rsponse<List<News>>();
        rsponse.setList(news);
        rsponse.setCode(200);
        System.out.println(rsponse);
        return rsponse;
    }

    @PostMapping("/article_listPerson")//个人所发表的新闻列表
    public Rsponse articleListByUser(@RequestParam("id") Integer user_id){
         if(user_id ==null)
        return null;
        System.out.println("进入个人发表新闻列表"+user_id);
        Set<News> news = userCRUD.findNewsById(user_id);
        if(news ==null)
        return null;

        System.out.println("查询出个人新闻列表为"+news);
        Rsponse<Set<News>> rsponse = new Rsponse<Set<News>>();
        rsponse.setList(news);
        rsponse.setCode(200);
        System.out.println(rsponse);
        return rsponse;
}



    @PostMapping("/search_history")//个人搜索历史  //传出去json数组
    public Rsponse searchHistory(@RequestParam("id")String user_id){
        Integer idu = Integer.valueOf(user_id);
        Set<String> searchs =  userCRUD.findSearchHistory(idu);

        System.out.println("查询出来的历史搜索为"+searchs);
        Rsponse<Set<String>> rsponse = new Rsponse<Set<String>>();
        rsponse.setList(searchs);
        rsponse.setCode(200);
        System.out.println(rsponse);
        return rsponse ;
    }

    @PostMapping("/myCollection")//个人收藏，通过id列表获得收藏新闻列表
    public Rsponse collectionList(@RequestParam("id")Integer user_id){
        Set<News> news = userCRUD.findNewsCollection(user_id);
        if(news == null)
            return null;
        System.out.println("查询出来的个人收藏为"+news);
        Rsponse<Set<News>> rsponse = new Rsponse<Set<News>>();
        rsponse.setList(news);
        rsponse.setCode(200);
        System.out.println(rsponse);
        return rsponse ;
    }

    @PostMapping("/scan_history")//个人浏览历史
    public Rsponse scanHistory(@RequestParam("id")Integer user_id){
        Set<News> news = userCRUD.findNewsHistory(user_id);
         if(news == null)
        return null;
        System.out.println("查询出来的个人浏览历史为"+news);
        Rsponse<Set<News>> rsponse = new Rsponse<Set<News>>();
        rsponse.setList(news);
        rsponse.setCode(200);
        System.out.println(rsponse);
        return rsponse ;
    }

    @PostMapping("/updataPersonInformation")//个人浏览历史
    public User updataPersonInformation(@RequestParam("user")User user,
                                        @RequestParam("id")Integer user_id){
        System.out.println("传进来的信息是"+user);
        //暂时不写

        return null;
    }
//以下是test模块

@GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {

    User user = userRepository.findById(1).get();
    System.out.println("进入查询user模块");//事实上懒加载是成功的只不过打印和返回序列化就自动get了
   //进入生产环境要把sout的语句注释掉
   // System.out.println("user:"+user+"-----------");
    return user;
}
@GetMapping("/user")
public User insertUser(User user){
    User save=userRepository.save(user);
    System.out.println("进入插入user模块");
    return save;
}

}
