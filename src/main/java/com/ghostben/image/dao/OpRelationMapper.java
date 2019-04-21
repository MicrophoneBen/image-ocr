package com.ghostben.image.dao;

import com.ghostben.image.entity.dao.OperationRelation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangbingquan
 * @desc 图片与识别结果关系表
 * @time 2019/4/5 15:45
 */
@Repository
public interface OpRelationMapper {
    Integer insetRelation(OperationRelation relationRecord);

    Integer updateRelation(OperationRelation relationRecord);

    Integer deleteRelation(Integer relationId);

    OperationRelation getRelationById(Integer relationId);

    List<OperationRelation> getRelationList();
}
