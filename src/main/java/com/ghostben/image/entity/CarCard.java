package com.ghostben.image.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @program: admin
 * @description: 车牌号识别系统
 * @author: ghostben
 * @create: 2018-10-20 21:07
 **/
@Data
public class CarCard {
    /**
    *@author    : Microphoneben
    *@date      : 2018/10/20
    * 请求结果
    */

    @SerializedName("log_id")
    private String log_id;
    @SerializedName("words_result")
    private List<WordsResult> wordsResultList;

    @Data
    public static class WordsResult {
        @SerializedName("color")
        private String color;
        @SerializedName("number")
        private String number;
        @SerializedName("probability")
        private List<String> probability;
        @SerializedName("vertexes_location")
        private List<VeTxLocation> veTxLocations;
    }
    @Data
    private static class VeTxLocation{
        @SerializedName("x")
        private String x;

        @SerializedName("y")
        private String y;
    }

}