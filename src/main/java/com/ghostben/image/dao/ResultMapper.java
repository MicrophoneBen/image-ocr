package com.ghostben.image.dao;

import com.ghostben.image.entity.dao.IdentityResultDao;
import com.ghostben.image.entity.dao.ImagesDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangbingquan
 * @desc 识别结果数据传输对象
 * @time 2019/4/5 13:53
 */
@Repository
public interface ResultMapper {
    Integer insertResult(IdentityResultDao resultRecord);

    Integer updateResult(IdentityResultDao resultRecord);

    Integer deleteResult(Integer IdentityId);

    IdentityResultDao getResultById(Integer IdentityId);

    List<IdentityResultDao> getResultList();
}
