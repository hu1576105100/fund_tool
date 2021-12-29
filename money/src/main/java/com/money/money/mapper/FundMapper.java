package com.money.money.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.money.money.entity.po.Fund;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author hujun
 * @version 1.0
 * @date 2021/12/29 11:05
 */
@Repository
public interface FundMapper extends BaseMapper<Fund> {
    void saveAndUpdate(List<Fund> list);
}
