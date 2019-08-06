package com.xingyu.service;

import com.xingyu.service.cache.SdAssessItemCache;
import com.xingyu.service.cache.SysDictCache;
import com.xingyu.service.cache.SysParamsCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by zhujl on 2015/7/13.
 */
@Scope("singleton")
@Component
public class ScheduledTasks {
    private static Log logger = LogFactory.getLog(ScheduledTasks.class);

    @Autowired
    SysDictCache sysDictCache;
    @Autowired
    SysParamsCache sysParamsCache;
    @Autowired
    SdAssessItemCache assessItemCache;

    @Scheduled(fixedDelay = 1800000)   //60分钟执行一次
    public void refreshCache() {
        sysDictCache.refresh();
        sysParamsCache.refresh();
        assessItemCache.refresh();
    }
}
