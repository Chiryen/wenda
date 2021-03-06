package com.company.async.handler;

import com.company.async.EventHandler;
import com.company.async.EventModel;
import com.company.async.EventType;
import com.company.model.Message;
import com.company.model.User;
import com.company.service.CommentService;
import com.company.service.MessageService;
import com.company.service.UserService;
import com.company.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by John on 2017/7/13.
 */
@Component
public class LikeHandler implements EventHandler {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(WendaUtil.SYSTEM_USERID);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user = userService.getUser(model.getActorId());
        //message.setContent("用户" + user.getName() + "赞了你的评论， http://127.0.0.1:8080/question/" + model.getExt("questionId"));
        String commentContent = commentService.getCommentById(model.getEntityId()).getContent();
        message.setContent("用户" + user.getName() + "赞了你的评论\"" + commentContent + "\"");
        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
 }
