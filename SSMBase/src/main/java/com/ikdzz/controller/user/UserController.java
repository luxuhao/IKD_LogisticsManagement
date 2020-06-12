package com.ikdzz.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ikdzz.domain.shiro.Permission;
import com.ikdzz.domain.shiro.User;
import com.ikdzz.service.Impl.shiro.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/select")
    public Map operation_select_user(){
        int count = 0; //统计条数
        List<Map> allUserList= new ArrayList<>();  //存放用户字段
        ArrayList<User>  userList= new ArrayList<>();
        userList = (ArrayList<User>) userService.findAll();
        SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd" );

        count=userList.size();
        for (User user : userList) {
            Map mapUser = new HashMap();
            mapUser.put("user_id",user.getUser_id());
            mapUser.put("username",user.getUsername());
            mapUser.put("password",user.getPassword());
            mapUser.put("email",user.getEmail());
            mapUser.put("phone",user.getPhone());
            mapUser.put("sex",user.getSex());
            mapUser.put("age",user.getAge());
            mapUser.put("status",user.getStatus());
            mapUser.put("create_time",sdf1.format(user.getCreate_time()));
            mapUser.put("update_time",sdf1.format(user.getUpdate_time()));
            mapUser.put("last_login_time",sdf1.format(user.getLast_login_time()));
            allUserList.add(mapUser);
        }


        Map resultJsonMap = new HashMap();
        resultJsonMap.put("code","0");
        resultJsonMap.put("msg","查询成功");
        resultJsonMap.put("count",count);
        resultJsonMap.put("data",JSONArray.parseArray(JSON.toJSONString(allUserList)));


//        List<Map> resultJsonList= new ArrayList<>();
//        resultJsonList.add(resultJsonMap);

        return resultJsonMap;
    }
}
