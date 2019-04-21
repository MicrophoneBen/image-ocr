package com.ghostben.image.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @program: admin
 * @description: 银行卡识别结果对象类
 * @author: ghostben
 * @create: 2018-10-21 04:30
 **/
@Data
public class BankCard {
    @SerializedName("log_id")
    private String logId;

    @SerializedName("result")
    private WordResult wordResult;

    @Data
    public static class WordResult{
        @SerializedName("bank_card_number")
        private String bankCardNumber;

        /**
        *@author    : Microphoneben
        *@description : No Valid 表示卡片没有过期
        */
        @SerializedName("valid_date")
        private String validDate;

        /**
        *@author    : Microphoneben
        *银行卡类型，0:不能识别; 1: 借记卡; 2: 信用卡
        */
        @SerializedName("bank_card_type")
        private String bankCardType;

        /**
        *@author    : Microphoneben
        *@description : 银行卡的所在银行
        */
        @SerializedName("bank_name")
        private String bankName;
    }
}