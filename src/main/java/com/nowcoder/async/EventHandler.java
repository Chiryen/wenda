package com.nowcoder.async;

import java.util.List;

/**
 * Created by John on 2017/7/13.
 */
public interface EventHandler {

    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}
