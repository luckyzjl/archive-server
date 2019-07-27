package com.xingyu.web;

import com.xingyu.base.BizException;
import com.xingyu.service.cache.SysDictCache;
import com.xingyu.wrapper.ApiResponse;
import com.xingyu.wrapper.WrapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by zhujl @2019-07-19
 */

@RestController
@RequestMapping(value="/archive/system")
public class SystemController extends BaseController {
    @Autowired
    SysDictCache sysDictCache;

    @RequestMapping(value="/dict")
    public ApiResponse getSysDict() throws BizException{
        return WrapResponse.wrap(sysDictCache.getSysDictMap());
    }
}
