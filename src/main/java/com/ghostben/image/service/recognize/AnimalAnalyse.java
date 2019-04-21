package com.ghostben.image.service.recognize;

import com.alibaba.fastjson.JSON;
import com.ghostben.image.dao.ImageMapper;
import com.ghostben.image.dao.OpRelationMapper;
import com.ghostben.image.dao.ResultMapper;
import com.ghostben.image.entity.Animal;
import com.ghostben.image.service.requestToken.ImageAnalysisUrlService;
import com.ghostben.image.util.BaseImg64;
import com.ghostben.image.util.commonAiUtil.ImageAPI;
import com.ghostben.image.util.commonAiUtil.Base64Util;
import com.ghostben.image.util.commonAiUtil.FileUtil;
import com.ghostben.image.util.commonAiUtil.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

/**
 * @program: admin
 * @description: 动物识别业务层，实现动物识别
 * @author: ghostben
 * @create: 2018-10-17 12:23
 **/
public class AnimalAnalyse {
    /**
     * 动物识别
     *
     * @author : Microphoneben
     * @date : 2018/10/17
     * @description : AnimalAnalyse
     */
    @Value("${uploadpic.path}")
    private static String imageURL;

    public static void main(String[] args) throws Exception {
        //返回字符串
        //		String result = getAnimalResult("G:/animal.jpg", "24.509012e6b7fd242cff7557c13dc436de.2592000.1512873097.282335-10131029");
        //		System.out.println(result);
        //返回java对象
        Animal animal = getAnimalBean(imageURL + "猴子.jpg", ImageAnalysisUrlService.getAuth());
        String result = getAnimalResult(imageURL + "猴子.jpg", ImageAnalysisUrlService.getAuth());
        System.out.println(result);
        System.out.println(animal);
        System.out.println(animal.getResult().get(0).getName());
        System.out.println(animal.getResult().get(0).getScore() * 100 + "%");
    }

    /**
     * 动物识别
     *
     * @param imagePath
     * @param accessToken
     * @return 字符串
     * @throws Exception
     */
    public static String getAnimalResult(String imagePath, String accessToken) throws Exception {
        //转编码start
        String image = BaseImg64.getImageStrFromPath(imagePath);
        String param = "baike_num=1&image=" + image;
        //ends

//            byte[] imgData = FileUtil.readFileByBytes(imagePath);
//            String imgStr = Base64Util.encode(imgData);
//            String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");

        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        String result = HttpUtil.post(ImageAPI.ANIMAL_API, accessToken, param);
        System.out.println(result);
        return result;
    }

    /**
     * 动物识别
     *
     * @param imagePath
     * @param accessToken
     * @return LOGO对象
     * @throws Exception
     */
    public static Animal getAnimalBean(String imagePath, String accessToken) throws Exception {
        byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
        String param = "image=" + URLEncoder.encode(imgStr, "UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        String result = HttpUtil.post(ImageAPI.ANIMAL_API, accessToken, param);
        Animal animal = JSON.parseObject(result, Animal.class);
        System.out.println(result);
        return animal;
    }
}