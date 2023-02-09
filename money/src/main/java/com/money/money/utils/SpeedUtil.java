package com.money.money.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SpeedUtil {

    private final Map<Long,Long> upTimes = new HashMap<>();

    public void culTime() {
        culTime(null);
    }

    /**
     * 计算方法耗时
     * @param msg
     */
    public void culTime(String msg) {
        Long thread = Thread.currentThread().getId();
        Long upTime = upTimes.get(thread);
        if(BeanUtil.isNotEmpty(upTime)){
            log.info(msg+"耗时（毫秒）："+(System.currentTimeMillis()-upTime));
        }
        upTimes.put(thread,System.currentTimeMillis());
    }

    public SpeedUtil(){
        this.upTimes.put(Thread.currentThread().getId(),System.currentTimeMillis());
    }
}
