package com.ghostben.image.service.imageToWord;

import com.ghostben.image.controller.FileUploadController;
import com.ghostben.image.dao.ImageMapper;
import com.ghostben.image.dao.OpRelationMapper;
import com.ghostben.image.dao.ResultMapper;
import com.ghostben.image.entity.BankCard;
import com.ghostben.image.entity.CarCard;
import com.ghostben.image.entity.dao.IdentityResultDao;
import com.ghostben.image.entity.dao.ImagesDao;
import com.ghostben.image.entity.dao.OperationRelation;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: admin
 * @description: 银行卡信息装配
 * @author: ghostben
 * @create: 2019-03-13 22:14
 **/
@Component
public class BankCardFormate {
    private static final Log logger = LogFactory.getLog(BankCardFormate.class);
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
        path.append(FileUploadController.getBankCardFilename());


        String json = null;
        try {
            json = ImageData.checkBankCardFile(path.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        BankCard result = gson.fromJson(json, BankCard.class);
        logger.info(result.getWordResult());

        getImageJson.insertData(path.toString(), result.getWordResult().toString());

        return result.getWordResult().toString();
    }
}