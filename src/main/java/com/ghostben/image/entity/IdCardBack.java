package com.ghostben.image.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Map;

/**
 * @program: admin
 * @description: 身份证背面的识别结果
 * @author: ghostben
 * @create: 2018-10-20 08:57
 **/
@Data
public class IdCardBack {
    @SerializedName("log_id")
    private String logId;

    private String direction;

    @SerializedName("image_status")
    private String imageStatus;

    @SerializedName("risk_type")
    private String riskType;

    @SerializedName("words_result_num")
    private String wordResultNum;

//    @SerializedName("edit_tool")
//    private String editTool;

    @SerializedName("words_result")
    private Map<String,WordResult> wordResults;


    @Data
    private static class Location {
        private String left;
        private String top;
        private String width;
        private String height;
    }

    @Data
    private static class WordResult {
        private Location location;
        private String words;
    }
}