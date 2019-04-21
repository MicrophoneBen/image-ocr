package com.ghostben.image.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * @program: admin
 * @description: 植物数据包装类，含有百科数据，包装百科数据返回
 * @author: ghostben
 * @create: 2018-11-01 19:39
 **/
@Data
public class PlantEntity {
    @SerializedName("log_id")
    private String logId;
    @SerializedName("result")
    private List<Result> resultList;

    @Data
    public static class Result{
        @SerializedName("score")
        private double score;
        @SerializedName("name")
        private String name;

        @SerializedName("baike_info")
        private BaikeInfo baikeInfo;
    }

    @Data
    public static class BaikeInfo{
        @SerializedName("baike_url")
        private String baikeUrl;
        @SerializedName("image_url")
        private String imageUrl;
        @SerializedName("description")
        private String description;
    }
}