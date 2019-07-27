package com.xingyu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by zhujl on 2015/7/13.
 */
@Scope("singleton")
@Component
public class ScheduledTasks {
    private static Log logger = LogFactory.getLog(ScheduledTasks.class);


}
