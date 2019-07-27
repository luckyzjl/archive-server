package com.xingyu.web;

import com.xingyu.base.BizException;
import com.xingyu.domain.vo.BizParameter;
import com.xingyu.domain.vo.ReqParameter;
import com.xingyu.domain.po.SdArchiveInfo;
import com.xingyu.service.SDArchiveService;
import com.xingyu.wrapper.ApiResponse;
import com.xingyu.wrapper.WrapResponse;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by zhujl @2019-07-18
 */

@RestController
@RequestMapping(value="/archive/bms")
public class SDArchiveBmsController extends BaseController {

    @Autowired
    SDArchiveService sdArchiveService;

    /**
     * 学生建档，查询，修改
     */
    @PostMapping(value="/sd/new")
    public ApiResponse newSDArchive() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.newStudentArchive(bizParameter.getArchiveNo(),bizParameter.getSdArchiveInfo(),bizParameter.getSdFamilyInfo());
        return WrapResponse.wrapSuccess();
    }

    @RequestMapping(value="/sd/update")
    public ApiResponse updateSDArchiveInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.updateStudentArchiveInfo(bizParameter.getArchiveNo(),bizParameter.getSdArchiveInfo(),bizParameter.getSdFamilyInfo());
        return WrapResponse.wrapSuccess();
    }

    @RequestMapping(value="/sd/info")
    public ApiResponse getSDArchiveInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getStudentArchiveInfo(bizParameter.getArchiveNo()));
    }

    @RequestMapping(value="/sd/list")
    public ApiResponse getSDArchiveList() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        List<SdArchiveInfo> sdArchiveList = sdArchiveService.getStudentArchiveList(bizParameter.getSdArchiveInfo());
        return WrapResponse.wrap(sdArchiveList,sdArchiveService.getSDArchivePageInfo(reqParameter,bizParameter.getSdArchiveInfo()));
    }

    /**
     * 学生入学登记：家庭信息/自身信息
     */
    @PostMapping(value="/sdreg/new")
    public ApiResponse newSdRegistrationInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.newSdRegistrationInfo(bizParameter.getArchiveNo(),bizParameter.getSdFamilyInfo(),bizParameter.getSdSelfInfo());
        return WrapResponse.wrapSuccess();
    }

    @RequestMapping(value="/sdreg/update")
    public ApiResponse updateSdRegistrationInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.updateSdRegistrationInfo(bizParameter.getArchiveNo(),bizParameter.getSdFamilyInfo(),bizParameter.getSdSelfInfo());
        return WrapResponse.wrapSuccess();
    }

    @RequestMapping(value="/sdreg/info")
    public ApiResponse getSdRegistrationInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getSdRegistrationInfo(bizParameter.getArchiveNo()));
    }


}
