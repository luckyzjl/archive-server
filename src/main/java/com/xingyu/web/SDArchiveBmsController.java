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
        List<SdArchiveInfo> sdArchiveList = sdArchiveService.getStudentArchiveList(reqParameter.getPageNo(),reqParameter.getPageSize(),bizParameter.getSdArchiveInfo());
        return WrapResponse.wrap(sdArchiveList,sdArchiveService.getSDArchivePageInfo(reqParameter.getPageNo(),reqParameter.getPageSize(),bizParameter.getSdArchiveInfo()));
    }

    /**
     * 学生入学登记：家庭信息/自身信息
     */
    @RequestMapping(value="/sdreg/info")
    public ApiResponse getSdRegistrationInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getSdRegistrationInfo(bizParameter.getArchiveNo()));
    }

    @RequestMapping(value="/sdreg/submit")
    public ApiResponse submitSdRegistrationInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.submitSdRegistrationInfo(bizParameter.getArchiveNo(),bizParameter.getSdFamilyInfo(),bizParameter.getSdSelfInfo());
        return WrapResponse.wrapSuccess();
    }


    /**
     * 学生评估记分：评估记分，统计
     */

    @RequestMapping(value="/sdassess/times/submit")
    public ApiResponse submitSdAssessTimes() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.submitSdAssessTimes(bizParameter.getSdAssessTimes());
        return WrapResponse.wrapSuccess();
    }


    @RequestMapping(value="/sdassess/score/info")
    public ApiResponse getSdAssessScoreInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getSdAssessScoreInfo(bizParameter.getArchiveNo(),bizParameter.getAssessTimes()));
    }

    @RequestMapping(value="/sdassess/score/submit")
    public ApiResponse submitSdAssessScore() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.submitSdAssessScore(bizParameter.getSdAssessScoreVo());
        return WrapResponse.wrapSuccess();
    }

    @RequestMapping(value="/sdassess/score/result")
    public ApiResponse sdAssessScoreResult() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.sdAssessScoreResult(bizParameter.getArchiveNo()));
    }


    /**
     * 评估结果分析
     */

    @RequestMapping(value="/sdanalyse/info")
    public ApiResponse getSdAnalyseInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getSdAnalyseInfo(bizParameter.getArchiveNo(),bizParameter.getAssessTimes()));
    }

    @RequestMapping(value="/sdanalyse/submit")
    public ApiResponse submitSdAnalyseInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.submitSdAnalyseInfo(bizParameter.getSdAssessTimes(),bizParameter.getSdAbilityAnalyseList());
        return WrapResponse.wrapSuccess();
    }


    /**
     * 04个别化教育计划（IEP）
     */
    @RequestMapping(value="/sdiep/info")
    public ApiResponse getIEPInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getIEPInfo(bizParameter.getArchiveNo(),bizParameter.getAssessTimes()));
    }

    @RequestMapping(value="/sdiep/submit")
    public ApiResponse submitIEPInfo() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.submitIEPInfo(bizParameter.getSdAssessTimes(),bizParameter.getSdIEPCatalogDtoList());
        return WrapResponse.wrapSuccess();
    }

    /**
     * 05个别化语言训练 月教育教案记录
     */
    @RequestMapping(value="/sdrecord/month/info")
    public ApiResponse getSdMonthRecord() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getSdMonthRecord(bizParameter.getArchiveNo(),bizParameter.getMonth()));
    }

    @RequestMapping(value="/sdrecord/month/submit")
    public ApiResponse submitSdMonthRecord() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.submitSdMonthRecord(bizParameter.getArchiveNo(),bizParameter.getSdMonthRecordDto());
        return WrapResponse.wrapSuccess();
    }

    @RequestMapping(value="/sdrecord/month/list")
    public ApiResponse getSdMonthRecordList() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getSdMonthRecordList(bizParameter.getArchiveNo()));
    }

    /**
     * 06个别训练记录
     */
//    @RequestMapping(value="/sdtraining/record/new")
//    public ApiResponse newSdTrainningRecord() throws BizException{
//        ReqParameter reqParameter = RequestFacade.getParameters();
//        BizParameter bizParameter = RequestFacade.getBizParameters();
//        sdArchiveService.newSdTrainningRecord(bizParameter.getSdAssessTimes(),bizParameter.getSdTrainningRecord());
//        return WrapResponse.wrapSuccess();
//    }

    @RequestMapping(value="/sdrecord/training/info")
    public ApiResponse getSdTrainningRecord() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getSdTrainningRecord(bizParameter.getArchiveNo(),bizParameter.getAssessTimes()));
    }

    @RequestMapping(value="/sdrecord/training/submit")
    public ApiResponse submitSdTrainningRecord() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.submitSdTrainningRecord(bizParameter.getSdAssessTimes(),bizParameter.getSdTrainningRecord());
        return WrapResponse.wrapSuccess();
    }


    /**
     * 07训练进度报告
     */
//    @RequestMapping(value="/sdprogress/report/new")
//    public ApiResponse newSdProgressReport() throws BizException{
//        ReqParameter reqParameter = RequestFacade.getParameters();
//        BizParameter bizParameter = RequestFacade.getBizParameters();
//        sdArchiveService.newSdProgressReport(bizParameter.getSdAssessTimes(),bizParameter.get());
//        return WrapResponse.wrapSuccess();
//    }

    @RequestMapping(value="/sdprogress/info")
    public ApiResponse getSdProgressReport() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getSdProgressReport(bizParameter.getArchiveNo(),bizParameter.getAssessTimes()));
    }

    @RequestMapping(value="/sdprogress/submit")
    public ApiResponse submitSdProgressReport() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.submitSdProgressReport(bizParameter.getSdAssessTimes(),bizParameter.getSdProgressDto());
        return WrapResponse.wrapSuccess();
    }


    /**
     * 年度评估与总结
     */
    @RequestMapping(value="/sdsummary/year/info")
    public ApiResponse getSdYearSummary() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getSdYearSummary(bizParameter.getArchiveNo()));
    }

    @RequestMapping(value="/sdsummary/year/submit")
    public ApiResponse submitSdYearSummary() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.submitSdYearSummary(bizParameter.getArchiveNo(),bizParameter.getSdYearSummary());
        return WrapResponse.wrapSuccess();
    }

    /**
     * 后续教育跟踪
     */
    @RequestMapping(value="/sdtrack/info")
    public ApiResponse getSdEduTrack() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        return WrapResponse.wrap(sdArchiveService.getSdEduTrack(bizParameter.getArchiveNo()));
    }

    @RequestMapping(value="/sdtrack/submit")
    public ApiResponse submitSdEduTrack() throws BizException{
        ReqParameter reqParameter = RequestFacade.getParameters();
        BizParameter bizParameter = RequestFacade.getBizParameters();
        sdArchiveService.submitSdEduTrack(bizParameter.getArchiveNo(),bizParameter.getSdEduTrack());
        return WrapResponse.wrapSuccess();
    }
}
