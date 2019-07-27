package com.xingyu.service.impl;

import com.xingyu.base.BizException;
import com.xingyu.domain.dto.SdArchiveDto;
import com.xingyu.domain.dto.SdAssessTimesDto;
import com.xingyu.domain.po.*;
import com.xingyu.domain.vo.SdAssessScoreVo;
import com.xingyu.mapper.*;
import com.xingyu.domain.vo.ReqParameter;
import com.xingyu.service.SDArchiveService;
import com.xingyu.wrapper.ApiResponse;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * created by zhujl @2019-07-17
 */

@Service
public class SDArchiveServiceImpl implements SDArchiveService {
    private static Log logger = LogFactory.getLog(SDArchiveService.class);

    @Autowired
    CustomSQLMapper customSQLMapper;
    @Autowired
    SdArchiveInfoMapper sdArchiveInfoMapper;
    @Autowired
    SdFamilyInfoMapper sdFamilyInfoMapper;
    @Autowired
    SdSelfInfoMapper sdSelfInfoMapper;
    @Autowired
    SdAssessTimesMapper sdAssessTimesMapper;
    @Autowired
    SdAssessScoreMapper sdAssessScoreMapper;


    /**
     * 学生建档，查询，修改
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newStudentArchive(String sdArchiveNo,SdArchiveInfo sdArchiveInfo,SdFamilyInfo sdFamilyInfo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK,"【缺少参数：档案编号】");
        }
        if (null != sdArchiveInfo){
            sdArchiveInfo.setArchiveNo(sdArchiveNo);
            sdArchiveInfoMapper.insertSelective(sdArchiveInfo);
        }
        if (null != sdFamilyInfo){
            sdFamilyInfo.setArchiveNo(sdArchiveNo);
            sdFamilyInfoMapper.insertSelective(sdFamilyInfo);
        }
    }

    @Override
    public SdArchiveDto getStudentArchiveInfo(String sdArchiveNo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK,"【缺少参数：档案编号】");
        }
        SdArchiveDto sdArchiveDto = new SdArchiveDto();
        sdArchiveDto.setArchiveNo(sdArchiveNo);
        sdArchiveDto.setSdArchiveInfo(sdArchiveInfoMapper.selectByPrimaryKey(sdArchiveNo));
        sdArchiveDto.setSdFamilyInfo(sdFamilyInfoMapper.selectByPrimaryKey(sdArchiveNo));
        return sdArchiveDto;
    }

    private SdArchiveInfoExample _getSdArchiveQueryExample(SdArchiveInfo sdArchiveVo) throws BizException{
        try {
            SdArchiveInfoExample example = new SdArchiveInfoExample();
            SdArchiveInfoExample.Criteria criteria = example.createCriteria();
            if (!TextUtils.isBlank(sdArchiveVo.getArchiveNo())){
                criteria.andArchiveNoEqualTo(sdArchiveVo.getArchiveNo());
            }
            if (!TextUtils.isBlank(sdArchiveVo.getName())){
                criteria.andNameLike("%" + sdArchiveVo.getName() + "%");
            }
            if (!TextUtils.isBlank(sdArchiveVo.getIdNum())){
                criteria.andIdNumEqualTo(sdArchiveVo.getIdNum());
            }
            if (null != sdArchiveVo.getEnrollTime()){
                Date date = DateUtils.parseDate(DateFormatUtils.format(sdArchiveVo.getEnrollTime(),"yyyy"),"yyyy");
                criteria.andEnrollTimeBetween(date,DateUtils.addYears(date,1));
            }

            return example;
        }catch (Exception ex){
            logger.error("学生档案查询出错",ex);
            throw new BizException(BizException.CODE_SYSTEM_ERROR,BizException.MSG_SYSTEM_ERROR);
        }
    }

    @Override
    public List<SdArchiveInfo> getStudentArchiveList(SdArchiveInfo sdArchiveVo) throws BizException{
        return sdArchiveInfoMapper.selectByExample(_getSdArchiveQueryExample(sdArchiveVo));
    }

    @Override
    public ApiResponse.PageInfo getSDArchivePageInfo(ReqParameter reqParameter,SdArchiveInfo sdArchiveVo) throws BizException{
        int recordAmount = sdArchiveInfoMapper.countByExample(_getSdArchiveQueryExample(sdArchiveVo));
        ApiResponse.PageInfo pageInfo = new ApiResponse.PageInfo();
        pageInfo.setRecordAmount(recordAmount);
        pageInfo.setPageNo(reqParameter.getPageNo());
        pageInfo.setPageSize(reqParameter.getPageSize());
        pageInfo.setPageAmount((recordAmount == 0 || recordAmount % reqParameter.getPageSize() == 0) ? recordAmount / reqParameter.getPageSize() : recordAmount / reqParameter.getPageSize() + 1);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStudentArchiveInfo(String archiveNo,SdArchiveInfo sdArchiveInfo,SdFamilyInfo sdFamilyInfo) throws BizException{
        if (TextUtils.isBlank(sdArchiveInfo.getArchiveNo())){
            throw new BizException(BizException.CODE_PARAM_LACK,"【缺少参数：档案编号】");
        }
        if (null != sdArchiveInfo){
            sdArchiveInfo.setArchiveNo(archiveNo);
            sdArchiveInfoMapper.updateByPrimaryKeySelective(sdArchiveInfo);
        }
        if (null != sdFamilyInfo){
            sdFamilyInfo.setArchiveNo(archiveNo);
            sdFamilyInfoMapper.updateByPrimaryKeySelective(sdFamilyInfo);
        }

    }


    /**
     * 学生入学登记：家庭信息/自身信息
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newSdRegistrationInfo(String sdArchiveNo,SdFamilyInfo sdFamilyInfo, SdSelfInfo sdSelfInfo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK,"param lock:archiveNo");
        }
        if (null != sdFamilyInfo){
            sdFamilyInfo.setArchiveNo(sdArchiveNo);
            sdFamilyInfoMapper.insertSelective(sdFamilyInfo);
        }
        if (null != sdSelfInfo){
            sdSelfInfo.setArchiveNo(sdArchiveNo);
            sdSelfInfoMapper.insertSelective(sdSelfInfo);
        }
    }

    @Override
    public SdArchiveDto getSdRegistrationInfo(String sdArchiveNo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK,"param lock:archiveNo");
        }
        SdArchiveDto sdArchiveDto = new SdArchiveDto();
        sdArchiveDto.setArchiveNo(sdArchiveNo);
        sdArchiveDto.setSdArchiveInfo(sdArchiveInfoMapper.selectByPrimaryKey(sdArchiveNo));
        sdArchiveDto.setSdFamilyInfo(sdFamilyInfoMapper.selectByPrimaryKey(sdArchiveNo));
        sdArchiveDto.setSdSelfInfo(sdSelfInfoMapper.selectByPrimaryKey(sdArchiveNo));
        return sdArchiveDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSdRegistrationInfo(String sdArchiveNo,SdFamilyInfo sdFamilyInfo, SdSelfInfo sdSelfInfo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK,"param lock:archiveNo");
        }
        if (null != sdFamilyInfo){
            sdFamilyInfo.setArchiveNo(sdArchiveNo);
            sdFamilyInfoMapper.updateByPrimaryKeySelective(sdFamilyInfo);
        }
        if (null != sdSelfInfo){
            sdSelfInfo.setArchiveNo(sdArchiveNo);
            sdSelfInfoMapper.updateByPrimaryKeySelective(sdSelfInfo);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertSdAssessScore(SdAssessScoreVo sdAssessScoreVo) throws BizException{
        if (null == sdAssessScoreVo
                || TextUtils.isBlank(sdAssessScoreVo.getArchiveNo()) || TextUtils.isBlank(sdAssessScoreVo.getAssessTimes())){
            throw new BizException(BizException.CODE_PARAM_LACK,"param lock:archiveNo/assessTimes");
        }
        if (null == sdAssessScoreVo.getAssessDate()){
            sdAssessScoreVo.setAssessDate(new Date());
        }
        sdAssessTimesMapper.insertSelective(sdAssessScoreVo);
        if (null != sdAssessScoreVo.getAssessScoreList()){
            for (SdAssessScore assessScore:sdAssessScoreVo.getAssessScoreList()){
                assessScore.setArchiveNo(sdAssessScoreVo.getArchiveNo());
                assessScore.setAssessTimes(sdAssessScoreVo.getAssessTimes());
            }
            customSQLMapper.listInsertAssessScore(sdAssessScoreVo.getAssessScoreList());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSdAssessScore(Integer firstCatalog,SdAssessScoreVo sdAssessScoreVo) throws BizException{
        if (null == firstCatalog || null == sdAssessScoreVo
                || TextUtils.isBlank(sdAssessScoreVo.getArchiveNo()) || TextUtils.isBlank(sdAssessScoreVo.getAssessTimes())){
            throw new BizException(BizException.CODE_PARAM_LACK,"param lock:archiveNo/assessTimes");
        }
        sdAssessTimesMapper.updateByPrimaryKeySelective(sdAssessScoreVo);
        if (null != sdAssessScoreVo.getAssessScoreList()){
            for (SdAssessScore assessScore:sdAssessScoreVo.getAssessScoreList()){
                assessScore.setArchiveNo(sdAssessScoreVo.getArchiveNo());
                assessScore.setAssessTimes(sdAssessScoreVo.getAssessTimes());
            }
            SdAssessScoreExample deleteExample = new SdAssessScoreExample();
            SdAssessScoreExample.Criteria criteria = deleteExample.createCriteria();
            criteria.andArchiveNoEqualTo(sdAssessScoreVo.getArchiveNo())
                    .andAssessTimesEqualTo(sdAssessScoreVo.getAssessTimes())
                    .andCatalogFirstEqualTo(firstCatalog);
            sdAssessScoreMapper.deleteByExample(deleteExample);
            customSQLMapper.listInsertAssessScore(sdAssessScoreVo.getAssessScoreList());
        }
    }

    private SdArchiveInfo getSdarchiveInfo(String sdArchiveNo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK,"【缺少参数：档案编号】");
        }
        SdArchiveInfo sdArchiveInfo = sdArchiveInfoMapper.selectByPrimaryKey(sdArchiveNo);
        if (null == sdArchiveInfo){
            throw new BizException(BizException.CODE_PARAM_ERROR,"【档案不存在】" + sdArchiveNo);
        }
        return sdArchiveInfo;
    }

    @Override
    public SdArchiveDto sdAssessScoreStat(String sdArchiveNo) throws BizException {
        SdArchiveInfo sdArchiveInfo = this.getSdarchiveInfo(sdArchiveNo);
        SdArchiveDto sdArchiveDto = new SdArchiveDto();
        BeanUtils.copyProperties(sdArchiveInfo,sdArchiveDto);
        List<SdAssessScoreCnt> sdAssessScoreCntList = customSQLMapper.statSdAssessScoreAhead(sdArchiveNo);
        sdAssessScoreCntList.addAll(customSQLMapper.statSdAssessScoreLast(sdArchiveNo));
        String assessTimes = "";
        String scoreItem = "";

        List<SdAssessTimesDto> assessTimesDtoList = new ArrayList<>();
        SdAssessTimesDto sdAssessTimesDto = new SdAssessTimesDto();
        List<SdAssessTimesDto.SdAssessScoreCntDto> scoreCntGBItemList = new ArrayList<>();
        SdAssessTimesDto.SdAssessScoreCntDto scoreCntGBItemDto = new SdAssessTimesDto.SdAssessScoreCntDto();
        Map<String,Integer> scoreCntMap = null;
        for (SdAssessScoreCnt assessScoreCnt: sdAssessScoreCntList){
            if (!scoreItem.equals(assessScoreCnt.getName())){
                if (!TextUtils.isBlank(scoreItem)){
                    scoreCntGBItemDto.setScoreCnt(scoreCntMap);
                    scoreCntGBItemList.add(scoreCntGBItemDto);
                    scoreCntGBItemDto = new SdAssessTimesDto.SdAssessScoreCntDto();
                    scoreCntGBItemDto.setItemId(assessScoreCnt.getItemId());
                    scoreCntGBItemDto.setName(assessScoreCnt.getName());
                    scoreItem = assessScoreCnt.getName();
                }
                scoreCntMap = new HashMap<>();
                scoreCntMap.put(assessScoreCnt.getScore(),assessScoreCnt.getScoreCnt());
            }else {
                scoreCntMap.put(assessScoreCnt.getScore(),assessScoreCnt.getScoreCnt());
            }

            if (!assessTimes.equals(assessScoreCnt.getAssessTimes())){
                if (!TextUtils.isBlank(assessTimes)){
                    sdAssessTimesDto.setScoreCntList(scoreCntGBItemList);
                    assessTimesDtoList.add(sdAssessTimesDto);
                    sdAssessTimesDto = new SdAssessTimesDto();
                    BeanUtils.copyProperties(assessScoreCnt,sdAssessTimesDto);
                    assessTimes = assessScoreCnt.getAssessTimes();
                }
            }
        }
        sdAssessTimesDto.setScoreCntList(scoreCntGBItemList);
        assessTimesDtoList.add(sdAssessTimesDto);
        sdArchiveDto.setAssessTimesDtoList(assessTimesDtoList);
        return sdArchiveDto;
    }
}
