package com.ikdzz.controller;

import com.ikdzz.domain.shiro.User;
import com.ikdzz.service.Impl.shiro.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping("/account/findAll")
    public String findAll(Model model){  //存数据， Model对象
        System.out.println("Controller表现层：查询所有账户...");
        // 调用service的方法
        List<User> list = userService.findAll();
        model.addAttribute("list",list);

        return "list";
    }

//    @RequestMapping("/account/save")
//    public void save(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
    //    userService.saveAccount(user);
     //   response.sendRedirect(request.getContextPath()+"/account/findAll");
    //    return;
    //}





    //定时器注解
    @Scheduled(cron="0/5 * * * * *")
    public void show(){
        System.out.println("5秒执行一次"+new Date());
    }


}
