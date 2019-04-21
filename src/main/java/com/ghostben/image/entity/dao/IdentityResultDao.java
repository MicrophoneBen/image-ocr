package com.ghostben.image.entity.dao;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangbingquan
 * @desc 识别结果数据传输对象
 * @time 2019/4/5 13:47
 */
@Data
public class IdentityResultDao {
    private Integer identityId;

    private String identityResult;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    //一个存储结果的构造器
    public IdentityResultDao(String result){
        this.identityResult = result;
    }
}
