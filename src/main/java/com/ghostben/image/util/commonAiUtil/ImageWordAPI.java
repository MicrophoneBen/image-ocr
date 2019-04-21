package com.ghostben.image.util.commonAiUtil;

import lombok.Data;

/**
 * @program: admin
 * @description: Image转Word的所有API集合类，包括各种识别的API
 * @author: ghostben
 * @create: 2018-10-18 00:21
 **/
@Data
public class ImageWordAPI {
    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  通用文字识别的地址请求
    */
    public static String general = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";

    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  高精度文字识别地址请求
    */
    public static String accurate = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";

    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  含有文字位置信息的通用文字识别
    */
    public static String generalAddress = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  含有文字位置信息的通用文字识别高精度版
    */
    public static String accrateAddress = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate";

    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  通用文字识别含有生僻字的识别
    */
    public static String generalEnhanced = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_enhanced";

    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  网络上背景复杂，有特殊字体的文字
    */
    public static String webImage = "https://aip.baidubce.com/rest/2.0/ocr/v1/webimage";

    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  识别手写的汉字，数字
    */
    public static String handWriting = "https://aip.baidubce.com/rest/2.0/ocr/v1/handwriting";

    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  识别身份证信息，包括正面和反面信息
    */
    public static String idCard = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
    
    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  识别银行卡，返回卡号，发卡行，以及卡片信息
    */
    public static String bankCard = "https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard";
    
    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  机动车驾驶证关键字段识别
    */
    public static String drivingLicense = "https://aip.baidubce.com/rest/2.0/ocr/v1/driving_license";
    
    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  机动车行驶证正本关键字段进行识别
    */
    public static String vehicleLicense = "https://aip.baidubce.com/rest/2.0/ocr/v1/vehicle_license";
    
    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  识别大陆机动车车牌（包含新能源车牌），并返回签发地和号牌。
    */
    public static String licensePlate = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";

    /**
    *@author    : Microphoneben
    *@date      : 2018/10/18
    *@Parameter :  【此接口需要您在申请页面中提交合作咨询开通权限】支持对大陆火车票的车票号、始发站、目的站、车次、日期、票价、席别、姓名进行结构化识别
    */
    public static String trainTicket = "https://aip.baidubce.com/rest/2.0/ocr/v1/train_ticket";
}