package com.ikdzz.service.Impl.activemq.Topic;

import com.ikdzz.domain.shiro.User;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class TopicMessageListener2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        ObjectMessage om = (ObjectMessage) message;
        try {
            User person = (User) om.getObject();

            System.out.println("TopicMessageListener2监听到了文本消息：\t"
                    + person.getId()+"*"+person.getUsername()+"*"+person.getAge());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
