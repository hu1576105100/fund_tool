package com.money.money.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author hujun
 * @version 1.0
 * @date 2021/12/29 11:01
 */
@Data
@TableName(value = "z_fund")
public class Fund {
    private Integer id;
    private String code;
    private String sortEn;
    private String name;
    private String longEn;
    private String type;
    private Date createTime;
    private Date updateTime;
    private boolean delete;
}
