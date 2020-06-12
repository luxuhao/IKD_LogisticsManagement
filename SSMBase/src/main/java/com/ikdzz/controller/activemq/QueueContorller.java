package com.ikdzz.controller.activemq;

import com.ikdzz.service.Impl.activemq.queue.QueueConsumerService;
import com.ikdzz.service.Impl.activemq.queue.QueueProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

@RequestMapping("/queueContorller")
@Controller
public class QueueContorller {

    //队列消费者
    @Autowired
    QueueConsumerService queueConsumerService;

    //队列生产者
    @Autowired
    QueueProducerService queueProducerService;

    @Resource(name = "myQueueDestination")
    private Destination destination;

    @RequestMapping("/sendQueue")
    @ResponseBody
    public String sendQueueMessage(){
        queueProducerService.sendMessage(destination, "我向MQ服务器发送一条Queue消息");
        return "sendQueue success";
    }


    @RequestMapping("/receiveQueue")
    @ResponseBody
    public String receiveQueueMessage() throws Exception{
        TextMessage receive = queueConsumerService.receive(destination);
        return receive.getText();
    }


}
