package com.ghostben.image.dao;

import com.ghostben.image.entity.dao.ImagesDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangbingquan
 * @desc 图片URL数据传输对象
 * @time 2019/4/5 13:53
 */
@Repository
public interface ImageMapper {
     Integer insertImagesUrl(ImagesDao imageRecord);

     Integer updateImagesUrl(ImagesDao imageRecord);

     Integer deleteImagesUrl(Integer imageId);

     ImagesDao getImagesUrlById(Integer imageId);

     List<ImagesDao> getImagesList();
}
