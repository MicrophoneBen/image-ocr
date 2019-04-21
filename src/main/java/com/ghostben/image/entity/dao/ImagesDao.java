package com.ghostben.image.entity.dao;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangbingquan
 * @desc 图片路径入库的数据传输对象
 * @time 2019/4/5 13:43
 */
@Data
public class ImagesDao {
    private Integer imageId;

    private String imageUrl;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    //一个存储URL的构造器
    public ImagesDao(String url){
        this.imageUrl = url;
    }
}
