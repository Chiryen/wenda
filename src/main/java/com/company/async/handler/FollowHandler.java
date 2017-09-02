package com.company.async.handler;

import com.company.async.EventHandler;
import com.company.async.EventModel;
import com.company.async.EventType;
import com.company.model.EntityType;
import com.company.model.Message;
import com.company.model.User;
import com.company.service.MessageService;
import com.company.service.QuestionService;
import com.company.service.UserService;
import com.company.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by John on 2017/7/15.
 */
@Component
public class FollowHandler implements EventHandler {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(WendaUtil.SYSTEM_USERID);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user = userService.getUser(model.getActorId());

        if (model.getEntityType() == EntityType.ENTITY_QUESTION) {
            //message.setContent("用戶" + user.getName() + "关注了你的问题，http://127.0.0.1:8080/question/" + model.getEntityId());
            message.setContent("用戶" + user.getName() + "关注了你的问题\"" + questionService.getById(model.getEntityId()).getContent() + "。\"");
        } else if (model.getEntityType() == EntityType.ENTITY_USER) {
            message.setContent("用户" + user.getName() + "关注了你。");
        }

        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.FOLLOW);
    }
}
