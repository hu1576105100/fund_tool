package com.money.money.serviceImpl;

import com.money.money.mapper.FundMapper;
import com.money.money.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author hujun
 * @version 1.0
 * @date 2021/12/29 11:09
 */
public class FundServiceImpl implements FundService {

    @Resource
    private FundMapper fundMapper;

    @Override
    public void saveAndUpdateFund() {
        fundMapper.saveAndUpdate();
    }
}
