package com.ghostben.image.controller;

import com.ghostben.image.entity.PlantEntity;
import com.ghostben.image.service.recognize.FlowerAi;
import com.ghostben.image.service.requestToken.ImageAnalysisUrlService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

/**
 * @program: admin
 * @description: 动植物图片识别控制层接口类的逻辑处理
 * @author: ghostben
 * @create: 2018-11-01 19:17
 **/
@Controller
public class ImageAnalysisController {
    private static final Log logger = LogFactory.getLog(ImageAnalysisController.class);
    //花草存储路径
    private String flowerImage;

    @Autowired
    private FlowerAi flowerAi;

    @Value("${uploadpic.path}")
    private String uploadPicPath;

    //车辆图片信息的上传Mapper
    @PostMapping("/upload/flower")
    public String handleFileUp(@RequestParam("file") MultipartFile file,
                               RedirectAttributes redirectAttributes,
                               Model model, Map<String,Object> flower) {
        PlantEntity plantEntity;
        //存储图片
        try {
            storeCarPic(file);
            redirectAttributes.addFlashAttribute("message","成功上载"
                    + file.getOriginalFilename() + "!");
            logger.info("上传成功，文件的名字是 " + file.getOriginalFilename());
            model.addAttribute("picName", file.getOriginalFilename());
            /**
             *@author    : Microphoneben
             *@date      : 2018/10/25
             * 图片获取与解析。
             */

            plantEntity = flowerAi.getPlantBean(uploadPicPath+flowerImage, ImageAnalysisUrlService.getAuth());
            logger.info("数据装配返回 ：" + plantEntity);

            flower.put("name",plantEntity.getResultList().get(0).getName());
            flower.put("score",Math.round(plantEntity.getResultList().get(0).getScore() * 10000) * 0.01);
            flower.put("desc",plantEntity.getResultList().get(0).getBaikeInfo().getDescription());
            flower.put("imageLink", plantEntity.getResultList().get(0).getBaikeInfo().getImageUrl());
            flower.put("pageLink",plantEntity.getResultList().get(0).getBaikeInfo().getBaikeUrl());

            //把数据交给要进行展示的页面
            return "result/plant";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result/plant";
    }

    private String storeCarPic(MultipartFile file) throws Exception {
        flowerImage = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadPicPath + flowerImage),
                        // 这里指定了下载的位置
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new Exception("失败！" + flowerImage, e);
        }
        return flowerImage;
    }


    public String getFlowerpath(){
        return flowerImage;
    }

}