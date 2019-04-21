package com.ghostben.image.service.recognize;

import com.ghostben.image.dao.ImageMapper;
import com.ghostben.image.dao.OpRelationMapper;
import com.ghostben.image.dao.ResultMapper;
import com.ghostben.image.entity.PlantEntity;
import com.ghostben.image.service.imageToWord.ImageData;
import com.ghostben.image.service.requestToken.ImageAnalysisUrlService;
import com.ghostben.image.util.BaseImg64;
import com.ghostben.image.util.commonAiUtil.Base64Util;
import com.ghostben.image.util.commonAiUtil.FileUtil;
import com.ghostben.image.util.commonAiUtil.HttpUtil;
import com.ghostben.image.util.commonAiUtil.ImageAPI;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

/**
 * @program: admin
 * @description: 花草识别的Service层
 * @author: ghostben
 * @create: 2018-10-17 12:40
 **/
@Component
public class FlowerAi {
    private static final Log logger = LogFactory.getLog(FlowerAi.class);
    @Value("${uploadpic.path}")
    private String imageURL;
    @Autowired
    ImageData getImageJson;

    public static void main(String[] args) throws Exception {
        //返回字符串
//		String result = getPlantResult("E:\\uploadPic\\海芋花.jpg", ImageAnalysisUrlService.getAuth());
        //String result = CheckImageData.checkFile("E:\\image\\鸢尾花.jpg");
//		System.out.println(result);

        //返回java对象
//		PlantEntity plant = getPlantBean("E:\\uploadPic\\海芋花.jpg", ImageAnalysisUrlService.getAuth());
//		System.out.println(plant.getResult().get(0).getName());
//        System.out.println(plant);
//        System.out.println(plant.getResultList().get(0).getScore() * 100 + "%");
    }

    /**
     * 植物识别
     * @param imagePath
     * @param accessToken
     * @return 字符串
     * @throws Exception
     */
    public static String getPlantResult(String imagePath,String accessToken) throws Exception{
        //转编码start
        String image = BaseImg64.getImageStrFromPath(imagePath);
        String param = "baike_num=1&image=" + image;
        //ends

        //byte[] imgData = FileUtil.readFileByBytes(imagePath);
        //String imgStr = Base64Util.encode(imgData);
        //String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        String result = HttpUtil.post(ImageAPI.PLANT_API, accessToken, param);
        System.out.println(result);
        return result;
    }
    /**
     * 植物识别
     * @param imagePath
     * @param accessToken
     * @return 植物对象
     * @throws Exception
     */
    public PlantEntity getPlantBean(String imagePath, String accessToken) throws Exception{
        byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
        String param = "top_num=2&baike_num=1&image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        String result = HttpUtil.post(ImageAPI.PLANT_API, accessToken, param);
        Gson gson = new Gson();
        PlantEntity plantEntity = gson.fromJson(result,PlantEntity.class);
//        logger.info(result);
        logger.info(plantEntity);
        getImageJson.insertData(imagePath, plantEntity.toString());
        return plantEntity;
    }

}