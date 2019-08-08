package com.xingyu.web;

import com.xingyu.base.BizException;
import com.xingyu.domain.dto.ClassArchiveDto;
import com.xingyu.domain.po.SdArchiveInfo;
import com.xingyu.domain.vo.BizParameter;
import com.xingyu.domain.vo.ReqParameter;
import com.xingyu.service.ClassArchiveService;
import com.xingyu.wrapper.ApiResponse;
import com.xingyu.wrapper.WrapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by zhujl @2019-08-07
 */

@RestController
@RequestMapping(value="/archive/bms")
public class ClassBmsController {

    @Autowired
    ClassArchiveService classArchiveService;

    /**
     * 班级建档，查询，修改
     */
    @PostMapping(value="/class/new")
    public ApiResponse newClassArchive() throws BizException {
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        classArchiveService.newClassArchiveInfo(bizParameter.getClassArchiveInfo(),bizParameter.getSdArchiveNos());
        return WrapResponse.wrapSuccess();
    }

    @RequestMapping(value="/class/update")
    public ApiResponse updateSDArchiveInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        classArchiveService.updateClassArchiveInfo(bizParameter.getClassArchiveInfo(),bizParameter.getSdArchiveNos());
        return WrapResponse.wrapSuccess();
    }

    @RequestMapping(value="/class/info")
    public ApiResponse getSDArchiveInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(classArchiveService.getClassArchiveInfo(bizParameter.getArchiveNo()));
    }

    @RequestMapping(value="/class/list")
    public ApiResponse getSDArchiveList() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        List<ClassArchiveDto> classArchiveList = classArchiveService.getClassArchiveList(reqParameter.getPageNo(),reqParameter.getPageSize(),bizParameter.getClassArchiveInfo());
        return WrapResponse.wrap(classArchiveList,classArchiveService.getClassArchivePageInfo(reqParameter.getPageNo(),reqParameter.getPageSize(),bizParameter.getClassArchiveInfo()));
    }
}
