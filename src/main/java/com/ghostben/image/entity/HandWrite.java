package com.ghostben.image.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @program: admin
 * @description: 手写文字的识别类，转对象
 * @author: ghostben
 * @create: 2018-10-21 04:52
 **/
@Data
public class HandWrite {
    @SerializedName("log_id")
    private String logId;

    @SerializedName("words_result_num")
    private String WordResultNum;

    @SerializedName("words_result")
    private List<WordResult> wordResults;

    @Data
    private static class WordResult{
        @SerializedName("location")
        private Location location;

        @SerializedName("words")
        private String words;
    }

    @Data
    private static class Location{
        private String width;
        private String top;
        private String left;
        private String height;
    }
}