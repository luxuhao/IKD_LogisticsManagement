package com.ikdzz.controller.dispatcher;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


//控制页面跳转，访问 web-inf 下的jsp


@Controller
public class DispatcherController {

        @RequestMapping("/user_index")
        public String index() {
            return "user_index";
        }




}

