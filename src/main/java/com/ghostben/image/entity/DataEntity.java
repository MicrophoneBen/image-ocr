package com.ghostben.image.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @program: admin
 * @description: 装配图片解析出来的json数据块
 * @author: ghostben
 * @create: 2018-10-16 22:31
 **/
@Data
public class DataEntity {
    @SerializedName("log_id")
    private String logId;
    @SerializedName("words_result_num")
    private String wordsResultNum;
    @SerializedName("words_result")
    private List<WordResult> wordResults;

    @Data
    public static class WordResult {
        @SerializedName("words")
        private String words;

        @Override
        public String toString() {
            return words;
        }
    }
}