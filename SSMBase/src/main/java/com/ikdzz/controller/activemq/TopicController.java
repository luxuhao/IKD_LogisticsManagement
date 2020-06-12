package com.ikdzz.controller.activemq;

import com.ikdzz.service.Impl.activemq.Topic.TopicProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;

@RequestMapping("/topicController")
@Controller
public class TopicController {
    @Autowired
    TopicProducerService topicProducerService;
    @Resource(name = "myTopicDestination")
    private Destination destination;

    @RequestMapping("/sendTopic")
    @ResponseBody
    public String sendTopicMessage(){
        topicProducerService.sendMessage(destination, "我向MQ服务器发送一条Topic消息");
        return "sendTopicMessage success";
    }

    @RequestMapping("/sendObjTopic")
    @ResponseBody
    public String sendObjTopicMessage(){
        topicProducerService.sendObjectMessage(destination);
        return "sendObjTopicMessage success";
    }

}

