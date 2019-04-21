package com.ghostben.image.service.imageToWord;

import com.ghostben.image.controller.FileUploadController;
import com.ghostben.image.dao.ImageMapper;
import com.ghostben.image.dao.OpRelationMapper;
import com.ghostben.image.dao.ResultMapper;
import com.ghostben.image.entity.BankCard;
import com.ghostben.image.entity.IdCardFront;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @program: admin
 * @description: 身份证识别信息的数据处理类
 * @author: ghostben
 * @create: 2019-03-14 03:01
 **/
@Component
public class IdCardFormate {
    private static final Log logger = LogFactory.getLog(IdCardFormate.class);

    @Autowired
    ImageData getImageJson;
    @Value("${uploadpic.path}")
    private String imageURL;

    public String formatData() {
        StringBuffer path = new StringBuffer(imageURL);
        /**
         * @author ben.zhang.b.q
         * @Time 2018/10/16 18:11
         * @return 拼出我们需要解析的图片的路径地址
         **/
        path.append(FileUploadController.getIdCardFilename());

        String json = null;
        try {
            json = ImageData.checkIdCardFrontFile(path.toString());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        IdCardFront result = gson.fromJson(json,IdCardFront.class);
        logger.info(result.toString());
        getImageJson.insertData(path.toString(), result.toString());
        return result.toString();
    }
}