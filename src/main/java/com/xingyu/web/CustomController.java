package com.xingyu.web;

import com.xingyu.base.BizException;
import com.xingyu.service.CustomService;
import com.xingyu.service.cache.SysDictCache;
import com.xingyu.wrapper.ApiResponse;
import com.xingyu.wrapper.WrapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by zhujl @2019-07-21
 */

@RestController
@RequestMapping(value="/archive/custom")
public class CustomController extends BaseController {

    @Autowired
    CustomService customService;
    @Autowired
    SysDictCache sysDictCache;

    @RequestMapping(value="/sysDict")
    public ApiResponse sysDict() throws BizException {
        return WrapResponse.wrap(sysDictCache.getSysDictMap());
    }

    @RequestMapping(value="/teacher")
    public ApiResponse teacher() throws BizException {
        return WrapResponse.wrap(customService.getTeacher());
    }

    @RequestMapping(value="/assessItem")
    public ApiResponse assessItem() throws BizException {
        return WrapResponse.wrap(customService.getSdAssessItem());
    }
}
