package com.ghostben.image.service.imageToWord;

import com.ghostben.image.controller.FileUploadController;
import com.ghostben.image.dao.ImageMapper;
import com.ghostben.image.dao.OpRelationMapper;
import com.ghostben.image.dao.ResultMapper;
import com.ghostben.image.entity.CarCard;
import com.ghostben.image.entity.dao.ImagesDao;
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
 * @description: 汽车数据的获取与转化为对象数据
 * @author: ghostben
 * @create: 2018-10-25 19:41
 **/
@Component
public class CarDataFormate {
    private static final Log logger = LogFactory.getLog(CarDataFormate.class);

    @Autowired
    ImageData getImageJson;

    @Value("${uploadpic.path}")
    private String imageURL;

    public String formatData() {
        StringBuilder path = new StringBuilder(imageURL);
        /**
         * @author ben.zhang.b.q
         * @Time 2018/10/16 18:11
         * @return 拼出我们需要解析的图片的路径地址
         **/
        path.append(FileUploadController.getCarFilePath());

        String json = null;
        try {
            json = ImageData.checkLicensePlate(path.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        CarCard result = gson.fromJson(json,CarCard.class);
        logger.info(result.getWordsResultList());

        StringBuilder totalWord = new StringBuilder();

        for(CarCard.WordsResult wordsResult : result.getWordsResultList()){
            //取出汽车识别的车牌号数据
            logger.info(wordsResult.getNumber());
            totalWord.append(wordsResult.getNumber());
        }
        getImageJson.insertData(path.toString(), totalWord.toString());
        return totalWord.toString();
    }
}