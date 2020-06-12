package com.ikdzz.controller.verify;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ikdzz.domain.shiro.Permission;
import com.ikdzz.domain.shiro.User;
import com.ikdzz.service.Impl.shiro.PermissionService;
import com.ikdzz.service.Impl.shiro.UserService;
import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping(value = "/islogin")
    public String islogin(){
        return "login";
    }

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/login",method=RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletRequest request){

          User user =  new User();
          user.setUsername(username);
          user.setPassword(password);
          Subject subject=SecurityUtils.getSubject();
          UsernamePasswordToken token=new UsernamePasswordToken(username,password);
          try {
              //调用subject.login(token)进行登录，会自动委托给securityManager,调用之前
              subject.login(token);//会跳到我们自定义的realm中
              String loginUserName = subject.getPrincipal().toString();
              request.getSession().setAttribute("user",user);
              request.getSession().setAttribute("username",loginUserName);
              boolean test = subject.hasRole("101");
              System.out.print("登陆成功"+test);
              return "index";
          }
          catch (Exception e){
              e.printStackTrace();
              request.getSession().setAttribute("user",user);
              request.setAttribute("error","用户名或密码错误");
              return "login";
          }


    }

      //登陆后获取权限菜单 返回json
      @ResponseBody
      @RequestMapping(value = "/getmenu")
      public JSONArray getmenu(){

          System.out.print("初始化左侧菜单接口访问成功！");
          String username = (String) SecurityUtils.getSubject().getPrincipal();
          List<String> permissionsList = userService.findPermissionsByUserName(username);
          List<Map> resultList= new ArrayList<>();

          for (String permission_id : permissionsList) {
              Permission permission = permissionService.findPermissionById(permission_id);
              Map mapPermission = new HashMap();
              mapPermission.put("name",permission.getName());
              mapPermission.put("icon",permission.getIcon());
              mapPermission.put("url",permission.getUrl());
              mapPermission.put("hidden",false);

              List<Permission> menuChildrenList = permissionService.findMenuChirdlrensById(permission_id);
              List<Map> menuList= new ArrayList<>(); //子菜单集合
              for (Permission menuChildren : menuChildrenList) {
                  Map mapMenuChildren = new HashMap();
                  mapMenuChildren.put("name",menuChildren.getName());
                  mapMenuChildren.put("url",menuChildren.getUrl());
                  menuList.add(mapMenuChildren);
              }
              mapPermission.put("list",JSONArray.parseArray(JSON.toJSONString(menuList)));


              resultList.add(mapPermission);
          }

        JSONArray permissionsJsonArray = JSONArray.parseArray(JSON.toJSONString(resultList));
//          Map result=new HashMap();
//          if (!permissionsJsonArray.isEmpty()){
//              result.put("msg","success");
//              result.put("permissionsJson",permissionsJsonArray);
//          }else{
//              result.put("message","查询失败");
//          }

          return permissionsJsonArray;

      }




}



