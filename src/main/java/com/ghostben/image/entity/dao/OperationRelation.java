package com.ghostben.image.entity.dao;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangbingquan
 * @desc 图像识别结果与图像之间的映射表
 * @time 2019/4/5 14:34
 */
@Data
public class OperationRelation {

    private Integer relationId;

    private Integer imageId;

    private Integer identityId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    //一个存储关系的构造器
    public OperationRelation(Integer imageId, Integer identityId){
        this.imageId = imageId;
        this.identityId = identityId;
    }
}
