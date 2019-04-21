package com.ghostben.image.service.imageToWord;

import com.ghostben.image.dao.ImageMapper;
import com.ghostben.image.dao.OpRelationMapper;
import com.ghostben.image.dao.ResultMapper;
import com.ghostben.image.entity.DataEntity;
import com.ghostben.image.controller.FileUploadController;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @program: admin
 * @description: 把图片的Json数据块Format，转为Object数据，好处理输出显示
 * @author: ghostben
 * @create: 2018-10-16 22:38
 **/
@Component
public class GeneralBasicDataFormate {
    private static final Log logger = LogFactory.getLog(GeneralBasicDataFormate.class);

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
        path.append(FileUploadController.getGeneralImagePath());

        String json = null;
        try {
            long now = System.currentTimeMillis();
            json = ImageData.checkGenralBasicFile(path.toString());
            logger.info("耗时：" + (System.currentTimeMillis() - now) / 1000 + "s");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        DataEntity result = gson.fromJson(json,DataEntity.class);

        StringBuilder totalWord = new StringBuilder();
        List<DataEntity.WordResult> wordList = result.getWordResults();
        for(DataEntity.WordResult word : wordList) {
            totalWord.append(word);
        }
        logger.info(result.getLogId()+"  " + result.getWordsResultNum() +"   " + totalWord.toString());

        getImageJson.insertData(path.toString(), totalWord.toString());
        return totalWord.toString();
    }
}