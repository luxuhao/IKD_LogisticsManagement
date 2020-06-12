package com.ikdzz.service.Impl.activemq.Topic;

import com.ikdzz.domain.shiro.User;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

@Service
public class TopicProducerService {
    @Resource(name="jmsTopicTemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final String msg){
        System.out.println(Thread.currentThread().getName()+" 向主题"+destination.toString()+"发送消息---------------------->"+msg);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    public void sendObjectMessage(Destination destination){
        System.out.println(Thread.currentThread().getName()+" 向主题"+destination.toString()+"发送protobuf消息---------------------->");
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage();
                User builder = new User();
                builder.setId(2);
                builder.setUsername("德玛");
                builder.setAge(88);
                objectMessage.setObject(builder);
                return objectMessage;
            }
        });
    }





}




