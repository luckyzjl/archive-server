package com.xingyu.service.cache;

import com.xingyu.base.BizException;
import com.xingyu.domain.dto.SysDictDto;
import com.xingyu.domain.po.SysDict;
import com.xingyu.domain.po.SysDictExample;
import com.xingyu.mapper.SysDictMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by zhujl @2019-07-16
 */

@Scope("singleton")
@Service
public class SysDictCache {
    private static Log logger = LogFactory.getLog(SysDictCache.class);

    @Autowired
    SysDictMapper sysDictMapper;

    private Map<String, List<SysDictDto>> sysDictMap = new HashMap<>();

    @PostConstruct
    public void init() throws BizException{
        List<SysDict> sysDictList = sysDictMapper.selectByExample(new SysDictExample());
        sysDictMap.clear();
        for (SysDict sysDict:sysDictList){
            SysDictDto sysDictDto = new SysDictDto();
            sysDictDto.setDictKey(sysDict.getDictKey());
            sysDictDto.setDictValue(sysDict.getDictKey());
            sysDictDto.setDictDesc(sysDict.getDictDesc());
            if (sysDictMap.containsKey(sysDict.getDictName())){
                sysDictMap.get(sysDict.getDictName()).add(sysDictDto);
            }else {
                List<SysDictDto> sysDictDtoList = new ArrayList<>();
                sysDictDtoList.add(sysDictDto);
                sysDictMap.put(sysDict.getDictName(),sysDictDtoList);
            }
        }
    }

    public Map<String, List<SysDictDto>> getSysDictMap() {
        return sysDictMap;
    }

    public void setSysDictMap(Map<String, List<SysDictDto>> sysDictMap) {
        this.sysDictMap = sysDictMap;
    }

    public void refresh() {
        logger.debug("reload system dict");
        init();
    }
}
