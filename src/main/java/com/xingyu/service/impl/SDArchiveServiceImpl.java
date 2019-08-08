package com.xingyu.service.impl;

import com.xingyu.base.BizException;
import com.xingyu.domain.dto.*;
import com.xingyu.domain.po.*;
import com.xingyu.domain.vo.SdAssessScoreVo;
import com.xingyu.mapper.*;
import com.xingyu.domain.vo.ReqParameter;
import com.xingyu.service.CustomService;
import com.xingyu.service.SDArchiveService;
import com.xingyu.service.cache.SdAssessItemCache;
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
    SdAssessItemCache sdAssessItemCache;
    @Autowired
    CustomService customService;
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
    @Autowired
    SdAbilityAnalyseMapper sdAbilityAnalyseMapper;
    @Autowired
    SdIEPCatalogMapper sdIEPCatalogMapper;
    @Autowired
    SdIEPItemMapper sdIEPItemMapper;
    @Autowired
    SdTrainningRecordMapper sdTrainningRecordMapper;
    @Autowired
    SdProgressReportMapper sdProgressReportMapper;
    @Autowired
    SdMonthRecordMapper sdMonthRecordMapper;
    @Autowired
    SdLessonRecordMapper sdLessonRecordMapper;
    @Autowired
    SdYearSummaryMapper sdYearSummaryMapper;
    @Autowired
    SdEduTrackMapper sdEduTrackMapper;


    /**
     * 学生建档，查询，修改
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newStudentArchive(String sdArchiveNo, SdArchiveInfo sdArchiveInfo,SdFamilyInfo sdFamilyInfo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            sdArchiveNo = customService.getSdArchiveNo();
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
    @Transactional(rollbackFor = Exception.class)
    public void updateStudentArchiveInfo(String archiveNo,SdArchiveInfo sdArchiveInfo,SdFamilyInfo sdFamilyInfo) throws BizException{
        if (TextUtils.isBlank(archiveNo)){
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
            if (null != sdArchiveVo) {
                if (!TextUtils.isBlank(sdArchiveVo.getArchiveNo())) {
                    criteria.andArchiveNoEqualTo(sdArchiveVo.getArchiveNo());
                }
                if (!TextUtils.isBlank(sdArchiveVo.getName())) {
                    criteria.andNameLike("%" + sdArchiveVo.getName() + "%");
                }
                if (!TextUtils.isBlank(sdArchiveVo.getIdNum())) {
                    criteria.andIdNumEqualTo(sdArchiveVo.getIdNum());
                }
                if (null != sdArchiveVo.getEnrollTime()) {
                    Date date = DateUtils.parseDate(DateFormatUtils.format(sdArchiveVo.getEnrollTime(), "yyyy"), "yyyy");
                    criteria.andEnrollTimeBetween(date, DateUtils.addYears(date, 1));
                }
                if (!TextUtils.isBlank(sdArchiveVo.getBirthPlace())){
                    criteria.andBirthPlaceEqualTo(sdArchiveVo.getBirthPlace());
                }
                if (!TextUtils.isBlank(sdArchiveVo.getTrainingType())){
                    criteria.andTrainingTypeEqualTo(sdArchiveVo.getTrainingType());
                }
                if (null != sdArchiveVo.getEnrollTime()){
                    criteria.andEnrollTimeGreaterThanOrEqualTo(sdArchiveVo.getEnrollTime());
                }
                if (null != sdArchiveVo.getEndTime()){
                    criteria.andEndTimeLessThanOrEqualTo(sdArchiveVo.getEndTime());
                }
                if (null != sdArchiveVo.getStatus()){
                    criteria.andStatusEqualTo(sdArchiveVo.getStatus());
                }
            }
            return example;
        }catch (Exception ex){
            logger.error("学生档案查询出错",ex);
            throw new BizException(BizException.CODE_SYSTEM_ERROR,BizException.MSG_SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponse.PageInfo getSDArchivePageInfo(Integer pageNo,Integer pageSize,SdArchiveInfo sdArchiveVo) throws BizException{
        int recordAmount = sdArchiveInfoMapper.countByExample(_getSdArchiveQueryExample(sdArchiveVo));
        if (null == pageNo) pageNo = 1;
        if (null == pageSize) pageSize = 10;
        ApiResponse.PageInfo pageInfo = new ApiResponse.PageInfo();
        pageInfo.setRecordAmount(recordAmount);
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageAmount((recordAmount == 0 || recordAmount % pageSize == 0) ? recordAmount / pageSize : recordAmount / pageSize + 1);
        return pageInfo;
    }

    @Override
    public List<SdArchiveInfo> getStudentArchiveList(Integer pageNo,Integer pageSize,SdArchiveInfo sdArchiveVo) throws BizException{
        if (null == pageNo) pageNo = 1;
        if (null == pageSize) pageSize = 10;
        SdArchiveInfoExample example = _getSdArchiveQueryExample(sdArchiveVo);
        example.setOrderByClause("archive_no desc" + " limit " + (pageNo-1)*pageSize + "," + pageSize);
        return sdArchiveInfoMapper.selectByExample(example);
    }

    @Override
    public List<SdArchiveInfo> getStudentArchiveList(List<String> sdNoList) throws BizException{
        if (null == sdNoList || sdNoList.size() == 0){
            return new ArrayList<>();
        }
        SdArchiveInfoExample example = new SdArchiveInfoExample();
        SdArchiveInfoExample.Criteria criteria = example.createCriteria();
        criteria.andArchiveNoIn(sdNoList);
        example.setOrderByClause("archive_no desc");
        return sdArchiveInfoMapper.selectByExample(example);
    }


    /**
     * 1、学生入学登记：家庭信息/自身信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitSdRegistrationInfo(String sdArchiveNo,SdFamilyInfo sdFamilyInfo, SdSelfInfo sdSelfInfo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK,"param lock:archiveNo");
        }
        if (null != sdFamilyInfo){
            sdFamilyInfo.setArchiveNo(sdArchiveNo);
            if (null != sdFamilyInfoMapper.selectByPrimaryKey(sdArchiveNo)) {
                sdFamilyInfoMapper.updateByPrimaryKeySelective(sdFamilyInfo);
            }else {
                sdFamilyInfoMapper.insertSelective(sdFamilyInfo);
            }
        }
        if (null != sdSelfInfo){
            sdSelfInfo.setArchiveNo(sdArchiveNo);
            if (null != sdSelfInfoMapper.selectByPrimaryKey(sdArchiveNo)) {
                sdSelfInfoMapper.updateByPrimaryKeySelective(sdSelfInfo);
            }else {
                sdSelfInfoMapper.insertSelective(sdSelfInfo);
            }
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


    /**
     * 学生档案基本信息，几乎每一页都要显示
     */
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

    /**
     * 2、学生评估记分：评估记分，统计
     */
//    @Override
//    public void newSdAssessTimes(SdAssessTimes sdAssessTimes) throws BizException{
//        if (null == sdAssessTimes
//                || TextUtils.isBlank(sdAssessTimes.getArchiveNo()) || TextUtils.isBlank(sdAssessTimes.getAssessTimes())){
//            throw new BizException(BizException.CODE_PARAM_LACK,"param lock:archiveNo/assessTimes");
//        }
//        if (null == sdAssessTimes.getAssessDate()){
//            sdAssessTimes.setAssessDate(new Date());
//        }
//        sdAssessTimesMapper.insertSelective(sdAssessTimes);
//    }

    @Override
    public SdArchiveDto getSdAssessTimesList(String sdArchiveNo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK,"缺少参数：archiveNo");
        }
        SdArchiveDto sdArchiveDto = new SdArchiveDto();

        //查询档案基本信息
        SdArchiveInfo sdArchiveInfo = this.getSdarchiveInfo(sdArchiveNo);
        BeanUtils.copyProperties(sdArchiveInfo,sdArchiveDto);

        SdAssessTimesExample example = new SdAssessTimesExample();
        SdAssessTimesExample.Criteria criteria = example.createCriteria();
        criteria.andArchiveNoEqualTo(sdArchiveNo);
        example.setOrderByClause("assess_date desc");
        sdArchiveDto.setAssessTimesList(sdAssessTimesMapper.selectByExample(example));
        return sdArchiveDto;
    }

    @Override
    public void submitSdAssessTimes(SdAssessTimes sdAssessTimes) throws BizException{
        if (null == sdAssessTimes
                || TextUtils.isBlank(sdAssessTimes.getArchiveNo()) || TextUtils.isBlank(sdAssessTimes.getAssessTimes())){
            throw new BizException(BizException.CODE_PARAM_LACK,"param lock:archiveNo/assessTimes");
        }
        if (null == sdAssessTimes.getAssessDate()){
            sdAssessTimes.setAssessDate(new Date());
        }
        SdAssessTimesKey assessTimesKey = new SdAssessTimesKey();
        assessTimesKey.setArchiveNo(sdAssessTimes.getArchiveNo());
        assessTimesKey.setAssessTimes(sdAssessTimes.getAssessTimes());
        if (null != sdAssessTimesMapper.selectByPrimaryKey(assessTimesKey)){
            sdAssessTimesMapper.updateByPrimaryKeySelective(sdAssessTimes);
        }else {
            sdAssessTimesMapper.insertSelective(sdAssessTimes);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitSdAssessScore(SdAssessScoreVo sdAssessScoreVo) throws BizException{
        if (null == sdAssessScoreVo || null == sdAssessScoreVo.getCatalogFirst()
                || TextUtils.isBlank(sdAssessScoreVo.getArchiveNo()) || TextUtils.isBlank(sdAssessScoreVo.getAssessTimes())){
            throw new BizException(BizException.CODE_PARAM_LACK,"param lock:archiveNo/assessTimes");
        }

        if (null != sdAssessScoreVo.getAssessScoreList()){
            for (SdAssessScore assessScore:sdAssessScoreVo.getAssessScoreList()){
                assessScore.setArchiveNo(sdAssessScoreVo.getArchiveNo());
                assessScore.setAssessTimes(sdAssessScoreVo.getAssessTimes());
                assessScore.setCatalogFirst(sdAssessScoreVo.getCatalogFirst());
                assessScore.setCatalogSecond(sdAssessScoreVo.getCatalogSecond());
            }
            SdAssessScoreExample deleteExample = new SdAssessScoreExample();
            SdAssessScoreExample.Criteria criteria = deleteExample.createCriteria();
            criteria.andArchiveNoEqualTo(sdAssessScoreVo.getArchiveNo())
                    .andAssessTimesEqualTo(sdAssessScoreVo.getAssessTimes())
                    .andCatalogFirstEqualTo(sdAssessScoreVo.getCatalogFirst());
            sdAssessScoreMapper.deleteByExample(deleteExample);
            customSQLMapper.listInsertAssessScore(sdAssessScoreVo.getAssessScoreList());
        }
    }

    @Override
    public List<SdAssessCatalogDto> getSdAssessScoreInfo(String sdArchiveNo, String assessTimes) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo) || TextUtils.isBlank(assessTimes)){
            throw new BizException(BizException.CODE_PARAM_LACK,"缺少参数");
        }
        List<SdAssessCatalogDto> assessCatalogDtoList = customService.getSdAssessCatalogList();
        Map<Integer,SdAssessScore> scoreMap = new HashMap<>();
        SdAssessScoreExample example = new SdAssessScoreExample();
        SdAssessScoreExample.Criteria criteria = example.createCriteria();
        criteria.andArchiveNoEqualTo(sdArchiveNo).andAssessTimesEqualTo(assessTimes);
        List<SdAssessScore> scoreList = sdAssessScoreMapper.selectByExample(example);
        if (scoreList.size() == 0){
            return assessCatalogDtoList;
        }
        for (SdAssessScore assessScore:scoreList){
            if (null == assessScore.getCatalogFirst()){
                continue;
            }
            scoreMap.put(assessScore.getItemId(),assessScore);
        }
        for (SdAssessCatalogDto assessCatalogDto:assessCatalogDtoList){
            for (SdAssessItemDto assessItemDto:assessCatalogDto.getAssessItemList()){
                if (scoreMap.containsKey(assessItemDto.getItemId())){
                    assessItemDto.setScore(scoreMap.get(assessItemDto.getItemId()).getScore());
                    assessItemDto.setRemark(scoreMap.get(assessItemDto.getItemId()).getRemark());
                }
            }
        }
        return assessCatalogDtoList;
    }

    @Override
    public SdArchiveDto sdAssessScoreResult(String sdArchiveNo) throws BizException {
        //查询档案基本信息
        SdArchiveInfo sdArchiveInfo = this.getSdarchiveInfo(sdArchiveNo);
        SdArchiveDto sdArchiveDto = new SdArchiveDto();
        BeanUtils.copyProperties(sdArchiveInfo,sdArchiveDto);

        List<SdAssessScoreCnt> sdAssessScoreCntList = customSQLMapper.statSdAssessScoreAhead(sdArchiveNo);
        sdAssessScoreCntList.addAll(customSQLMapper.statSdAssessScoreLast(sdArchiveNo));
        Map<String, SdAssessTimesDto.SdAssessScoreCntDto> assessScoreCntDtoMap = new HashMap<>();
        Map<String,SdAssessScoreCnt> assessScoreCntMap = new HashMap<>();
        //已评估项按（期数+评估项）分类统计P、E、F计数
        for (SdAssessScoreCnt assessScoreCnt:sdAssessScoreCntList){
            if (!assessScoreCntMap.containsKey(assessScoreCnt.getAssessTimes())){
                assessScoreCntMap.put(assessScoreCnt.getAssessTimes(),assessScoreCnt);
            }
            String mapKey = assessScoreCnt.getAssessTimes() + assessScoreCnt.getItemName();
            if (assessScoreCntDtoMap.containsKey(mapKey)){
                assessScoreCntDtoMap.get(mapKey).getScoreCnt().put(assessScoreCnt.getScore(), assessScoreCnt.getScoreCnt());
            }else {
                SdAssessTimesDto.SdAssessScoreCntDto assessScoreCntDto = new SdAssessTimesDto.SdAssessScoreCntDto();
                BeanUtils.copyProperties(assessScoreCnt,assessScoreCntDto);
                Map<String, Integer> scoreCntMap = new HashMap<>();
                scoreCntMap.put(assessScoreCnt.getScore(), assessScoreCnt.getScoreCnt());
                assessScoreCntDto.setScoreCnt(scoreCntMap);
                assessScoreCntDtoMap.put(mapKey,assessScoreCntDto);
            }
        }

        //组装返回数据结构
        List<SdAssessTimesDto> assessTimesDtoList = new ArrayList<>();
        for (int i=1;i<=3;i++){
            String strTimes = "";
            if (1==i) strTimes = "第一次";
            else if (2==i) strTimes = "第二次";
            else if (3==i) strTimes = "第三次";
            SdAssessTimesDto assessTimesDto = new SdAssessTimesDto();
            //第一层次统计
            for (SdAssessItem assessItem:sdAssessItemCache.getCatalogFirstList()){
                //第一层次的最后一个不统计
                if (sdAssessItemCache.getCatalogFirstList().indexOf(assessItem) == 7){
                    break;
                }
                String mapKey = strTimes + assessItem.getItemName();
                if (assessScoreCntDtoMap.containsKey(mapKey)){
                    if (TextUtils.isBlank(assessTimesDto.getAssessTimes())){
                        BeanUtils.copyProperties(assessScoreCntMap.get(strTimes),assessTimesDto);
                        List<SdAssessTimesDto.SdAssessScoreCntDto> scoreCntList = new ArrayList<>();
                        scoreCntList.add(assessScoreCntDtoMap.get(mapKey));
                        assessTimesDto.setScoreCntList(scoreCntList);
                    }else {
                        assessTimesDto.getScoreCntList().add(assessScoreCntDtoMap.get(mapKey));
                    }
                }else {
                    SdAssessTimesDto.SdAssessScoreCntDto assessScoreCntDto = new SdAssessTimesDto.SdAssessScoreCntDto();
                    assessScoreCntDto.setItemId(assessItem.getItemId());
                    assessScoreCntDto.setItemName(assessItem.getItemName());
                    assessScoreCntDto.setScoreCnt(new HashMap<>());
                    if (null == assessTimesDto.getScoreCntList()){
                        assessTimesDto.setAssessTimes(strTimes);
                        List<SdAssessTimesDto.SdAssessScoreCntDto> scoreCntList = new ArrayList<>();
                        scoreCntList.add(assessScoreCntDto);
                        assessTimesDto.setScoreCntList(scoreCntList);
                    }else {
                        assessTimesDto.getScoreCntList().add(assessScoreCntDto);
                    }
                }
            }
            //第二层次统计
            for (SdAssessItem assessItem:sdAssessItemCache.getCatalogSecondInLastCatalogFirstList()){
                String mapKey = strTimes + assessItem.getItemName();
                if (assessScoreCntDtoMap.containsKey(mapKey)){
                    if (TextUtils.isBlank(assessTimesDto.getAssessTimes())){
                        BeanUtils.copyProperties(assessScoreCntMap.get(strTimes),assessTimesDto);
                        List<SdAssessTimesDto.SdAssessScoreCntDto> scoreCntList = new ArrayList<>();
                        scoreCntList.add(assessScoreCntDtoMap.get(mapKey));
                        assessTimesDto.setScoreCntList(scoreCntList);
                    }else {
                        assessTimesDto.getScoreCntList().add(assessScoreCntDtoMap.get(mapKey));
                    }
                }else {
                    SdAssessTimesDto.SdAssessScoreCntDto assessScoreCntDto = new SdAssessTimesDto.SdAssessScoreCntDto();
                    assessScoreCntDto.setItemId(assessItem.getItemId());
                    assessScoreCntDto.setItemName(assessItem.getItemName());
                    assessScoreCntDto.setScoreCnt(new HashMap<>());
                    if (null == assessTimesDto.getScoreCntList()){
                        assessTimesDto.setAssessTimes(strTimes);
                        List<SdAssessTimesDto.SdAssessScoreCntDto> scoreCntList = new ArrayList<>();
                        scoreCntList.add(assessScoreCntDto);
                        assessTimesDto.setScoreCntList(scoreCntList);
                    }else {
                        assessTimesDto.getScoreCntList().add(assessScoreCntDto);
                    }
                }
            }
            assessTimesDtoList.add(assessTimesDto);
        }

        sdArchiveDto.setAssessTimesDtoList(assessTimesDtoList);

        return sdArchiveDto;
    }

    /**
     * 3、评估结果分析
     */

    @Override
    public SdArchiveDto getSdAnalyseInfo(String sdArchiveNo,String assessTimes) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo) || TextUtils.isBlank(assessTimes)){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:sdArchiveNo/assessTimes");
        }
        SdArchiveDto sdArchiveDto = new SdArchiveDto();
        //查询档案基本信息
        SdArchiveInfo sdArchiveInfo = this.getSdarchiveInfo(sdArchiveNo);
        BeanUtils.copyProperties(sdArchiveInfo,sdArchiveDto);
        //获取评估期数信息
        SdAssessTimesKey key = new SdAssessTimesKey();
        key.setArchiveNo(sdArchiveNo);
        key.setAssessTimes(assessTimes);
        SdAssessTimes sdAssessTimes = sdAssessTimesMapper.selectByPrimaryKey(key);
        BeanUtils.copyProperties(sdAssessTimes,sdArchiveDto);
        //评估结果分析信息(以评估项目第一级为架构返回)
        Map<Integer,SdAbilityAnalyse> abilityAnalyseMap = new HashMap<>();
        SdAbilityAnalyseExample example = new SdAbilityAnalyseExample();
        SdAbilityAnalyseExample.Criteria criteria = example.createCriteria();
        criteria.andArchiveNoEqualTo(sdArchiveNo).andAssessTimesEqualTo(assessTimes);
        example.setOrderByClause("catalog_id");
        List<SdAbilityAnalyse> curAbilityAnalyseList = sdAbilityAnalyseMapper.selectByExample(example);
        for (SdAbilityAnalyse abilityAnalyse:curAbilityAnalyseList){
            abilityAnalyse.setArchiveNo(null);
            abilityAnalyse.setAssessTimes(null);
            abilityAnalyseMap.put(abilityAnalyse.getCatalogId(),abilityAnalyse);
        }
        List<SdAbilityAnalyse> abilityAnalyseList = new ArrayList<>();
        List<SdAssessItem> firstCatalogItemList = sdAssessItemCache.getCatalogFirstList();
        for (SdAssessItem assessItem:firstCatalogItemList){
            if (abilityAnalyseMap.containsKey(assessItem.getItemId())){
                abilityAnalyseList.add(abilityAnalyseMap.get(assessItem.getItemId()));
            }else {
                SdAbilityAnalyse abilityAnalyse = new SdAbilityAnalyse();
                abilityAnalyse.setCatalogId(assessItem.getItemId());
                abilityAnalyse.setCatalogName(assessItem.getItemName());
                abilityAnalyseList.add(abilityAnalyse);
            }
        }
        sdArchiveDto.setAbilityAnalyseList(abilityAnalyseList);
        return sdArchiveDto;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitSdAnalyseInfo(SdAssessTimes sdAssessTimes,List<SdAbilityAnalyse> sdAbilityAnalyseList) throws BizException{
        if (TextUtils.isBlank(sdAssessTimes.getArchiveNo()) || TextUtils.isBlank(sdAssessTimes.getAssessTimes())){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:sdArchiveNo/assessTimes");
        }
        for (SdAbilityAnalyse sdAbilityAnalyse:sdAbilityAnalyseList) {
            if (TextUtils.isBlank(sdAbilityAnalyse.getCatalogName()) || null == sdAbilityAnalyse.getCatalogId()) {
                throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:catalogId/catalogName");
            }
            sdAbilityAnalyse.setArchiveNo(sdAssessTimes.getArchiveNo());
            sdAbilityAnalyse.setAssessTimes(sdAssessTimes.getAssessTimes());
        }
        if (null == sdAssessTimes.getAssessDate()){
            sdAssessTimes.setAssessDate(new Date());
        }
        sdAssessTimesMapper.updateByPrimaryKeySelective(sdAssessTimes);

        if (sdAbilityAnalyseList.size() > 0) {
            SdAbilityAnalyseExample deleteExample = new SdAbilityAnalyseExample();
            SdAbilityAnalyseExample.Criteria criteria = deleteExample.createCriteria();
            criteria.andArchiveNoEqualTo(sdAssessTimes.getArchiveNo()).andAssessTimesEqualTo(sdAssessTimes.getAssessTimes());
            sdAbilityAnalyseMapper.deleteByExample(deleteExample);
            customSQLMapper.listInsertAbilityAnalyse(sdAbilityAnalyseList);
        }
    }


    /**
     * 4、个别化教育计划（IEP）
     */
    @Override
    public SdArchiveDto getIEPInfo(String sdArchiveNo,String assessTimes) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo) || TextUtils.isBlank(assessTimes)){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:sdArchiveNo/assessTimes");
        }
        SdArchiveDto sdArchiveDto = new SdArchiveDto();

        //查询档案基本信息
        SdArchiveInfo sdArchiveInfo = this.getSdarchiveInfo(sdArchiveNo);
        BeanUtils.copyProperties(sdArchiveInfo,sdArchiveDto);

        //获取评估期数信息
        SdAssessTimesKey key = new SdAssessTimesKey();
        key.setArchiveNo(sdArchiveNo);
        key.setAssessTimes(assessTimes);
        SdAssessTimes sdAssessTimes = sdAssessTimesMapper.selectByPrimaryKey(key);
        BeanUtils.copyProperties(sdAssessTimes,sdArchiveDto);

        //IEP长期目标
        Map<Integer,SdIEPCatalog> iepCatalogMap = new HashMap<>();
        SdIEPCatalogExample catalogExample = new SdIEPCatalogExample();
        SdIEPCatalogExample.Criteria catalogCri = catalogExample.createCriteria();
        catalogCri.andArchiveNoEqualTo(sdArchiveNo).andAssessTimesEqualTo(assessTimes);
        catalogExample.setOrderByClause("catalog_id");
        List<SdIEPCatalog> iepCatalogList = sdIEPCatalogMapper.selectByExample(catalogExample);
        for (SdIEPCatalog iepCatalog:iepCatalogList){
            iepCatalog.setArchiveNo(null);
            iepCatalog.setAssessTimes(null);
            iepCatalogMap.put(iepCatalog.getCatalogId(),iepCatalog);
        }

        //IEP短期目标（如果IEP item表中还没有，从评估项目中获取结果为E的条目）
        Map<Integer,List<SdIEPItem>> iepItemMap = new HashMap<>();
        SdIEPItemExample itemExample = new SdIEPItemExample();
        SdIEPItemExample.Criteria itemCri = itemExample.createCriteria();
        itemCri.andArchiveNoEqualTo(sdArchiveNo).andAssessTimesEqualTo(assessTimes);
        itemExample.setOrderByClause("catalog_id");
        List<SdIEPItem> iepItemList = sdIEPItemMapper.selectByExample(itemExample);
        if (iepItemList.size() > 0){
            for (SdIEPItem iepItem:iepItemList){
                if (iepItemMap.containsKey(iepItem.getCatalogId())){
                    iepItemMap.get(iepItem.getCatalogId()).add(iepItem);
                }else {
                    List<SdIEPItem> iepItems = new ArrayList<>();
                    iepItems.add(iepItem);
                    iepItemMap.put(iepItem.getCatalogId(),iepItems);
                }
                iepItem.setArchiveNo(null);
                iepItem.setAssessTimes(null);
                iepItem.setCatalogId(null);
                iepItem.setCatalogName(null);
                iepItem.setProgressAssessResult(null);
            }
        }else {
            SdAssessScoreExample scoreExample = new SdAssessScoreExample();
            SdAssessScoreExample.Criteria scoreCir = scoreExample.createCriteria();
            scoreCir.andArchiveNoEqualTo(sdArchiveNo).andAssessTimesEqualTo(assessTimes)
                    .andScoreEqualTo("E");
            scoreExample.setOrderByClause("item_id asc");
            List<SdAssessScore> assessScoreList = sdAssessScoreMapper.selectByExample(scoreExample);
            for (SdAssessScore assessScore:assessScoreList){
                SdIEPItem iepItem = new SdIEPItem();
                iepItem.setPurposeItemId(assessScore.getItemId());
                iepItem.setPurposeItemName(sdAssessItemCache.getAssessItemDtoMap().get(assessScore.getItemId()).getItemName());
                Integer mapKey = sdAssessItemCache.getAssessItemDtoMap().get(assessScore.getItemId()).getCatalogFirstId();
                if (iepItemMap.containsKey(mapKey)){
                    iepItemMap.get(mapKey).add(iepItem);
                }else {
                    List<SdIEPItem> iepItems = new ArrayList<>();
                    iepItems.add(iepItem);
                    iepItemMap.put(mapKey,iepItems);
                }
            }
        }
        //以评估项目第一级为架构返回
        List<SdIEPCatalogDto> iepCatalogDtoList = new ArrayList<>();
        List<SdAssessItem> firstCatalogItemList = sdAssessItemCache.getCatalogFirstList();
        for (SdAssessItem assessItem:firstCatalogItemList){
            SdIEPCatalogDto iepCatalogDto = new SdIEPCatalogDto();
            iepCatalogDto.setCatalogId(assessItem.getItemId());
            iepCatalogDto.setCatalogName(assessItem.getItemName());
            if (iepCatalogMap.containsKey(assessItem.getItemId())) {
                iepCatalogDto.setCatalogPurpose(iepCatalogMap.get(assessItem.getItemId()).getCatalogPurpose());
            }
            if (iepItemMap.containsKey(assessItem.getItemId())){
                iepCatalogDto.setIepItemList(iepItemMap.get(assessItem.getItemId()));
            }
            iepCatalogDtoList.add(iepCatalogDto);
        }
        sdArchiveDto.setIepCatalogDtoList(iepCatalogDtoList);
        return sdArchiveDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitIEPInfo(SdAssessTimes sdAssessTimes,List<SdIEPCatalogDto> iepCatalogDtoList) throws BizException{
        if (TextUtils.isBlank(sdAssessTimes.getArchiveNo()) || TextUtils.isBlank(sdAssessTimes.getAssessTimes())){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:sdArchiveNo/assessTimes");
        }
        if (!TextUtils.isBlank(sdAssessTimes.getIepCharge()) || null != sdAssessTimes.getTrainStartTime()) {
            sdAssessTimesMapper.updateByPrimaryKeySelective(sdAssessTimes);
        }
        if (null == iepCatalogDtoList || iepCatalogDtoList.size() == 0)
            return;
        List<SdIEPCatalog> iepCatalogList = new ArrayList<>();
        List<SdIEPItem> iepItemList = new ArrayList<>();
        for (SdIEPCatalogDto iepCatalogDto:iepCatalogDtoList) {
            if (TextUtils.isBlank(iepCatalogDto.getCatalogName()) || null == iepCatalogDto.getCatalogId()) {
                throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:catalogId/catalogName");
            }
            SdIEPCatalog iepCatalog = new SdIEPCatalog();
            iepCatalog.setArchiveNo(sdAssessTimes.getArchiveNo());
            iepCatalog.setAssessTimes(sdAssessTimes.getAssessTimes());
            iepCatalog.setCatalogId(iepCatalogDto.getCatalogId());
            iepCatalog.setCatalogName(iepCatalogDto.getCatalogName());
            iepCatalog.setCatalogPurpose(iepCatalogDto.getCatalogPurpose());
            iepCatalogList.add(iepCatalog);
            if (null != iepCatalogDto.getIepItemList() && iepCatalogDto.getIepItemList().size() >0){
                for (SdIEPItem iepItem:iepCatalogDto.getIepItemList()){
                    iepItem.setArchiveNo(sdAssessTimes.getArchiveNo());
                    iepItem.setAssessTimes(sdAssessTimes.getAssessTimes());
                    iepItem.setCatalogId(iepCatalogDto.getCatalogId());
                    iepItem.setCatalogName(iepCatalogDto.getCatalogName());
                }
                iepItemList.addAll(iepCatalogDto.getIepItemList());
            }
        }

        if (iepCatalogList.size() > 0){
            SdIEPCatalogExample delExample = new SdIEPCatalogExample();
            SdIEPCatalogExample.Criteria delCir = delExample.createCriteria();
            delCir.andArchiveNoEqualTo(sdAssessTimes.getArchiveNo()).andAssessTimesEqualTo(sdAssessTimes.getAssessTimes());
            sdIEPCatalogMapper.deleteByExample(delExample);
            customSQLMapper.listInsertIEPCatalog(iepCatalogList);
        }

        if (iepItemList.size() > 0){
            SdIEPItemExample delExample = new SdIEPItemExample();
            SdIEPItemExample.Criteria delCir = delExample.createCriteria();
            delCir.andArchiveNoEqualTo(sdAssessTimes.getArchiveNo()).andAssessTimesEqualTo(sdAssessTimes.getAssessTimes());
            sdIEPItemMapper.deleteByExample(delExample);
            customSQLMapper.listInsertIEPItem(iepItemList);
        }
    }

    /**
     * 个别化语言训练 月教育教案记录
     */
    @Override
    public SdMonthRecordDto getSdMonthRecord(String sdArchiveNo,String month) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo) || TextUtils.isBlank(month)){
            throw new BizException(BizException.CODE_PARAM_LACK,"缺少参数：archiveNo/month");
        }
        SdMonthRecordDto monthRecordDto = new SdMonthRecordDto();
        SdMonthRecordKey recordKey = new SdMonthRecordKey();
        recordKey.setArchiveNo(sdArchiveNo);
        recordKey.setMonth(month);
        SdMonthRecord monthRecord = sdMonthRecordMapper.selectByPrimaryKey(recordKey);
        if (null == monthRecord){
            return monthRecordDto;
        }
        BeanUtils.copyProperties(monthRecord,monthRecordDto);
        SdLessonRecordExample lessonExample = new SdLessonRecordExample();
        SdLessonRecordExample.Criteria lessonCri = lessonExample.createCriteria();
        lessonCri.andArchiveNoEqualTo(sdArchiveNo).andMonthEqualTo(month);
        List<SdLessonRecord> lessonRecordList = sdLessonRecordMapper.selectByExample(lessonExample);
        monthRecordDto.setLessonRecordList(lessonRecordList);
        return monthRecordDto;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitSdMonthRecord(String sdArchiveNo,SdMonthRecordDto monthRecordDto) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK,"缺少参数：archiveNo");
        }
        if (null == monthRecordDto || TextUtils.isBlank(monthRecordDto.getMonth())){
            throw new BizException(BizException.CODE_PARAM_LACK,"缺少参数：sdMonthRecord.month");
        }
        monthRecordDto.setArchiveNo(sdArchiveNo);
        SdMonthRecordKey monthRecordKey = new SdMonthRecordKey();
        monthRecordKey.setArchiveNo(sdArchiveNo);
        monthRecordKey.setMonth(monthRecordDto.getMonth());
        SdMonthRecord monthRecord = sdMonthRecordMapper.selectByPrimaryKey(monthRecordKey);
        if (null == monthRecord) {
            sdMonthRecordMapper.insertSelective(monthRecordDto);
        }else {
            sdMonthRecordMapper.updateByPrimaryKeySelective(monthRecordDto);
        }
        if (null != monthRecordDto.getLessonRecordList() && monthRecordDto.getLessonRecordList().size() > 0){
            for (SdLessonRecord lessonRecord:monthRecordDto.getLessonRecordList()){
                lessonRecord.setArchiveNo(sdArchiveNo);
                lessonRecord.setMonth(monthRecordDto.getMonth());
            }
            SdLessonRecordExample lessonExample = new SdLessonRecordExample();
            SdLessonRecordExample.Criteria lessonCri = lessonExample.createCriteria();
            lessonCri.andArchiveNoEqualTo(sdArchiveNo).andMonthEqualTo(monthRecordDto.getMonth());
            sdLessonRecordMapper.deleteByExample(lessonExample);
            customSQLMapper.listInsertLessonRecord(monthRecordDto.getLessonRecordList());
        }
    }


    @Override
    public SdArchiveDto getSdMonthRecordList(String sdArchiveNo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK,"缺少参数：archiveNo");
        }
        SdArchiveDto sdArchiveDto = new SdArchiveDto();

        //查询档案基本信息
        SdArchiveInfo sdArchiveInfo = this.getSdarchiveInfo(sdArchiveNo);
        BeanUtils.copyProperties(sdArchiveInfo,sdArchiveDto);

        SdMonthRecordExample example = new SdMonthRecordExample();
        SdMonthRecordExample.Criteria criteria = example.createCriteria();
        criteria.andArchiveNoEqualTo(sdArchiveNo);
        example.setOrderByClause("month desc");
        sdArchiveDto.setMonthRecordList(sdMonthRecordMapper.selectByExample(example));
        return sdArchiveDto;
    }


    /**
     * 个别训练记录
     */
//    @Override
//    public void newSdTrainningRecord(SdAssessTimes sdAssessTimes,SdTrainningRecord sdTrainningRecord) throws BizException{
//        if (TextUtils.isBlank(sdAssessTimes.getArchiveNo()) || TextUtils.isBlank(sdAssessTimes.getAssessTimes())){
//            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:sdArchiveNo/assessTimes");
//        }
//        sdAssessTimesMapper.updateByPrimaryKeySelective(sdAssessTimes);
//        if (null != sdTrainningRecord){
//            sdTrainningRecord.setArchiveNo(sdAssessTimes.getArchiveNo());
//            sdTrainningRecord.setAssessTimes(sdAssessTimes.getAssessTimes());
//            sdTrainningRecordMapper.insertSelective(sdTrainningRecord);
//        }
//    }

    @Override
    public SdArchiveDto getSdTrainningRecord(String sdArchiveNo,String assessTimes) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo) || TextUtils.isBlank(assessTimes)){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:sdArchiveNo/assessTimes");
        }
        SdArchiveDto sdArchiveDto = new SdArchiveDto();

        //查询档案基本信息
        SdArchiveInfo sdArchiveInfo = this.getSdarchiveInfo(sdArchiveNo);
        BeanUtils.copyProperties(sdArchiveInfo,sdArchiveDto);

        //获取评估期数信息
        SdAssessTimesKey assessTimesKey = new SdAssessTimesKey();
        assessTimesKey.setArchiveNo(sdArchiveNo);
        assessTimesKey.setAssessTimes(assessTimes);
        SdAssessTimes sdAssessTimes = sdAssessTimesMapper.selectByPrimaryKey(assessTimesKey);
        BeanUtils.copyProperties(sdAssessTimes,sdArchiveDto);

        //训练记录信息
        SdTrainningRecordKey trainningRecordKey = new SdTrainningRecordKey();
        trainningRecordKey.setArchiveNo(sdArchiveNo);
        trainningRecordKey.setAssessTimes(assessTimes);
        SdTrainningRecord trainningRecord = sdTrainningRecordMapper.selectByPrimaryKey(trainningRecordKey);
        sdArchiveDto.setTrainningRecord(trainningRecord);
        return sdArchiveDto;
    }

    @Override
    public void submitSdTrainningRecord(SdAssessTimes sdAssessTimes,SdTrainningRecord sdTrainningRecord) throws BizException{
        if (TextUtils.isBlank(sdAssessTimes.getArchiveNo()) || TextUtils.isBlank(sdAssessTimes.getAssessTimes())){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:sdArchiveNo/assessTimes");
        }
        if (!TextUtils.isBlank(sdAssessTimes.getTrainTeacher()) || null != sdAssessTimes.getTrainStartTime()) {
            sdAssessTimesMapper.updateByPrimaryKeySelective(sdAssessTimes);
        }

        if (null != sdTrainningRecord){
            sdTrainningRecord.setArchiveNo(sdAssessTimes.getArchiveNo());
            sdTrainningRecord.setAssessTimes(sdAssessTimes.getAssessTimes());
            SdTrainningRecordKey trainningRecordKey = new SdTrainningRecordKey();
            trainningRecordKey.setArchiveNo(sdAssessTimes.getArchiveNo());
            trainningRecordKey.setAssessTimes(sdAssessTimes.getAssessTimes());
            if (null != sdTrainningRecordMapper.selectByPrimaryKey(trainningRecordKey)) {
                sdTrainningRecordMapper.updateByPrimaryKeySelective(sdTrainningRecord);
            }else {
                sdTrainningRecordMapper.insertSelective(sdTrainningRecord);
            }
        }
    }


    /**
     * 训练进度报告
     */
//    @Override
//    public void newSdProgressReport(SdAssessTimes sdAssessTimes,SdProgressReport sdProgressReport) throws BizException{
//        if (TextUtils.isBlank(sdAssessTimes.getArchiveNo()) || TextUtils.isBlank(sdAssessTimes.getAssessTimes())){
//            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:sdArchiveNo/assessTimes");
//        }
//        sdAssessTimesMapper.updateByPrimaryKeySelective(sdAssessTimes);
//        if (null != sdProgressReport){
//            sdProgressReport.setArchiveNo(sdAssessTimes.getArchiveNo());
//            sdProgressReport.setAssessTimes(sdAssessTimes.getAssessTimes());
//            sdProgressReportMapper.insertSelective(sdProgressReport);
//        }
//    }

    @Override
    public SdArchiveDto getSdProgressReport(String sdArchiveNo,String assessTimes) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo) || TextUtils.isBlank(assessTimes)){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:sdArchiveNo/assessTimes");
        }
        SdArchiveDto sdArchiveDto = new SdArchiveDto();

        //查询档案基本信息
        SdArchiveInfo sdArchiveInfo = this.getSdarchiveInfo(sdArchiveNo);
        BeanUtils.copyProperties(sdArchiveInfo,sdArchiveDto);

        //获取评估期数信息
        SdAssessTimesKey assessTimesKey = new SdAssessTimesKey();
        assessTimesKey.setArchiveNo(sdArchiveNo);
        assessTimesKey.setAssessTimes(assessTimes);
        SdAssessTimes sdAssessTimes = sdAssessTimesMapper.selectByPrimaryKey(assessTimesKey);
        BeanUtils.copyProperties(sdAssessTimes,sdArchiveDto);

        //训练进度
        SdProgressDto sdProgressDto = new SdProgressDto();
        SdProgressReportKey progressReportKey = new SdProgressReportKey();
        progressReportKey.setArchiveNo(sdArchiveNo);
        progressReportKey.setAssessTimes(assessTimes);
        SdProgressReport progressReport = sdProgressReportMapper.selectByPrimaryKey(progressReportKey);
        if (null != progressReport){
            sdProgressDto.setReviewTeacher(progressReport.getReviewTeacher());
            sdProgressDto.setFeedback(progressReport.getFeedback());
        }

        //训练进度条目
        Map<Integer,List<SdIEPItem>> iepItemMap = new HashMap<>();
        SdIEPItemExample itemExample = new SdIEPItemExample();
        SdIEPItemExample.Criteria itemCri = itemExample.createCriteria();
        itemCri.andArchiveNoEqualTo(sdArchiveNo).andAssessTimesEqualTo(assessTimes);
        itemExample.setOrderByClause("catalog_id");
        List<SdIEPItem> iepItemList = sdIEPItemMapper.selectByExample(itemExample);
        if (iepItemList.size() > 0){
            for (SdIEPItem iepItem:iepItemList){
                if (iepItemMap.containsKey(iepItem.getCatalogId())){
                    iepItemMap.get(iepItem.getCatalogId()).add(iepItem);
                }else {
                    List<SdIEPItem> iepItems = new ArrayList<>();
                    iepItems.add(iepItem);
                    iepItemMap.put(iepItem.getCatalogId(),iepItems);
                }
                iepItem.setArchiveNo(null);
                iepItem.setAssessTimes(null);
            }
        }
        //以评估项目第一级为架构返回
        List<SdProgressItemDto> progressItemDtoList = new ArrayList<>();
        List<SdAssessItem> firstCatalogItemList = sdAssessItemCache.getCatalogFirstList();
        for (SdAssessItem assessItem:firstCatalogItemList){
            SdProgressItemDto progressItemDto = new SdProgressItemDto();
            progressItemDto.setCatalogId(assessItem.getItemId());
            progressItemDto.setCatalogName(assessItem.getItemName());
            if (iepItemMap.containsKey(assessItem.getItemId())){
                progressItemDto.setIepItemList(iepItemMap.get(assessItem.getItemId()));
            }
            progressItemDtoList.add(progressItemDto);
        }
        sdProgressDto.setProgressItemDtoList(progressItemDtoList);
        sdArchiveDto.setSdProgressDto(sdProgressDto);
        return sdArchiveDto;
    }


    @Override
    public void submitSdProgressReport(SdAssessTimes sdAssessTimes,SdProgressDto sdProgressDto) throws BizException{
        if (TextUtils.isBlank(sdAssessTimes.getArchiveNo()) || TextUtils.isBlank(sdAssessTimes.getAssessTimes())){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:sdArchiveNo/assessTimes");
        }

        if (!TextUtils.isBlank(sdAssessTimes.getTrainTeacher()) || null != sdAssessTimes.getTrainStartTime()) {
            sdAssessTimesMapper.updateByPrimaryKeySelective(sdAssessTimes);
        }
        if (null == sdProgressDto)
            return;
        if (null == sdProgressDto)
            return;
        sdProgressDto.setArchiveNo(sdAssessTimes.getArchiveNo());
        sdProgressDto.setAssessTimes(sdAssessTimes.getAssessTimes());
        if (!TextUtils.isBlank(sdProgressDto.getReviewTeacher()) || !TextUtils.isBlank(sdProgressDto.getFeedback())) {
            sdProgressReportMapper.updateByPrimaryKeySelective(sdProgressDto);
        }

        List<SdIEPItem> iepItemList = new ArrayList<>();
        if (null == sdProgressDto.getProgressItemDtoList() || sdProgressDto.getProgressItemDtoList().size() == 0) {
            return;
        }
        for (SdProgressItemDto progressItemDto:sdProgressDto.getProgressItemDtoList()){
            if (null == progressItemDto.getIepItemList() || progressItemDto.getIepItemList().size() == 0){
                continue;
            }
            if (TextUtils.isBlank(progressItemDto.getCatalogName()) || null == progressItemDto.getCatalogId()) {
                throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数:catalogId/catalogName");
            }
            for (SdIEPItem iepItem:progressItemDto.getIepItemList()){
                iepItem.setArchiveNo(sdAssessTimes.getArchiveNo());
                iepItem.setAssessTimes(sdAssessTimes.getAssessTimes());
                iepItem.setCatalogId(progressItemDto.getCatalogId());
                iepItem.setCatalogName(progressItemDto.getCatalogName());
                iepItemList.add(iepItem);
            }
        }
        if (iepItemList.size() > 0){
            SdIEPItemExample delExample = new SdIEPItemExample();
            SdIEPItemExample.Criteria delCir = delExample.createCriteria();
            delCir.andArchiveNoEqualTo(sdAssessTimes.getArchiveNo()).andAssessTimesEqualTo(sdAssessTimes.getAssessTimes());
            sdIEPItemMapper.deleteByExample(delExample);
            customSQLMapper.listInsertIEPItem(iepItemList);
        }
    }

    /**
     * 年度评估与总结
     */
    @Override
    public SdArchiveDto getSdYearSummary(String sdArchiveNo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数：archiveNo");
        }
        SdArchiveDto sdArchiveDto = new SdArchiveDto();
        //查询档案基本信息
        SdArchiveInfo sdArchiveInfo = this.getSdarchiveInfo(sdArchiveNo);
        BeanUtils.copyProperties(sdArchiveInfo,sdArchiveDto);
        sdArchiveDto.setYearSummary(sdYearSummaryMapper.selectByPrimaryKey(sdArchiveNo));
        return sdArchiveDto;
    }

    @Override
    public void submitSdYearSummary(String sdArchiveNo,SdYearSummary yearSummary) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数：archiveNo");
        }
        if (null == yearSummary){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少对象");
        }
        yearSummary.setArchiveNo(sdArchiveNo);
        SdYearSummary curYearSummary = sdYearSummaryMapper.selectByPrimaryKey(sdArchiveNo);
        if (null == curYearSummary){
            sdYearSummaryMapper.insertSelective(yearSummary);
        }else {
            sdYearSummaryMapper.updateByPrimaryKey(yearSummary);
        }
    }


    /**
     * 后续教育跟踪
     */
    @Override
    public SdArchiveDto getSdEduTrack(String sdArchiveNo) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数：archiveNo");
        }
        SdArchiveDto sdArchiveDto = new SdArchiveDto();
        //查询档案基本信息
        SdArchiveInfo sdArchiveInfo = this.getSdarchiveInfo(sdArchiveNo);
        BeanUtils.copyProperties(sdArchiveInfo,sdArchiveDto);
        sdArchiveDto.setEduTrack(sdEduTrackMapper.selectByPrimaryKey(sdArchiveNo));
        return sdArchiveDto;
    }

    @Override
    public void submitSdEduTrack(String sdArchiveNo,SdEduTrack eduTrack) throws BizException{
        if (TextUtils.isBlank(sdArchiveNo)){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少参数：archiveNo");
        }
        if (null == eduTrack){
            throw new BizException(BizException.CODE_PARAM_LACK, "缺少对象");
        }
        eduTrack.setArchiveNo(sdArchiveNo);
        SdEduTrack curEduTrack = sdEduTrackMapper.selectByPrimaryKey(sdArchiveNo);
        if (null == curEduTrack){
            sdEduTrackMapper.insertSelective(eduTrack);
        }else {
            sdEduTrackMapper.updateByPrimaryKey(eduTrack);
        }
    }

}
