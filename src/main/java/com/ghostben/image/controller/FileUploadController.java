package com.ghostben.image.controller;

import com.ghostben.image.service.imageToWord.BankCardFormate;
import com.ghostben.image.service.imageToWord.CarDataFormate;
import com.ghostben.image.service.imageToWord.GeneralBasicDataFormate;
import com.ghostben.image.service.imageToWord.IdCardFormate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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

@Controller
public class FileUploadController {
    private static final Log logger = LogFactory.getLog(FileUploadController.class);

    @Autowired
    private GeneralBasicDataFormate generalBasicDataFormate;
    @Autowired
    private CarDataFormate carDataFormate;
    @Autowired
    private BankCardFormate bankCardFormate;
    @Autowired
    private IdCardFormate idCardFormate;
    //通用文字识别文件名
    private static String generalFilename = " ";
    //车辆图片的文字识别
    private static String carFilename = " ";
    //银行卡图片识别
    private static String bankCardFilename = " ";
    //身份证信息识别
    private static String idCardFilename = " ";

    /**这里的是application.properties中配置的地址*/
    @Value("${uploadpic.path}")
    private String uploadPicPath;


    // 主界面

    @GetMapping("/upload")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("messages", "cpx");
        return "bankCard";
    }
    // GeneralBasic文件上传
    @PostMapping("/upload/generalImage")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   Model model, Map<String,Object> wordResult) throws Exception {
        // 存储图片到本地
        storeGeneralPic(file);
        redirectAttributes.addFlashAttribute("message", file.getOriginalFilename());
        logger.info("上传的文件名字：" + file.getOriginalFilename());
        model.addAttribute("picName", file.getOriginalFilename());
        /**
        *@author    : Microphoneben
        *@date      : 2018/10/16
        *@Parameter :  [file, redirectAttributes, model] 图片解析
        * start
        */
        wordResult.put("result", generalBasicDataFormate.formatData());
        //解析ends
        // 将文件传输成功之后的名字传回界面，用于展示图片
        return "result/generalBasic";
    }

    // BankCard图片上载Mapper
    @PostMapping("/upload/bankCardImage")
    public String handleBankFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   Model model, Map<String,Object> wordResult) throws Exception {
        // 存储图片到本地
        storeBankCardPic(file);
        redirectAttributes.addFlashAttribute("message", file.getOriginalFilename());
        logger.info("上传的文件名字：" + file.getOriginalFilename());
        model.addAttribute("picName", file.getOriginalFilename());

        wordResult.put("bankInfo", bankCardFormate.formatData());
        //解析ends
        // 将文件传输成功之后的名字传回界面，用于展示图片
        return "result/bankCard";
    }

    //车辆图片信息的上传Mapper
    @PostMapping("/upload/carImage")
    public String handleCarFileUp(@RequestParam("file") MultipartFile file,
                               RedirectAttributes redirectAttributes,
                               Model model, Map<String,Object> carNum) {
        //存储图片
        try {
            storeCarPic(file);
            redirectAttributes.addFlashAttribute("message","成功上载" + file.getOriginalFilename() + "!");
            logger.info("上传成功，文件的名字是 +" + file.getOriginalFilename());
            model.addAttribute("picName", file.getOriginalFilename());
            /**
            *@author    : Microphoneben
            *@date      : 2018/10/25
             * 图片获取与解析。
            */
            carNum.put("carNum",carDataFormate.formatData());

            //把数据交给要进行展示的页面
            return "result/multiCarPic";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result/multiCarPic";
    }

    //身份证图片信息的上传Mapper
    @PostMapping("/upload/idCardImage")
    public String handleIdCardFileUp(@RequestParam("file") MultipartFile file,
                                  RedirectAttributes redirectAttributes,
                                  Model model, Map<String,Object> idCardInfo) {
        //存储图片
        try {
            storeIdCardImage(file);
            redirectAttributes.addFlashAttribute("message","成功上载" + file.getOriginalFilename() + "!");
            logger.info("上传成功，文件的名字是 +" + file.getOriginalFilename());
            model.addAttribute("picName", file.getOriginalFilename());
            /**
             *@author    : Microphoneben
             *@date      : 2018/10/25
             * 图片获取与解析。
             */
            idCardInfo.put("idCardInfo", idCardFormate.formatData());

            //把数据交给要进行展示的页面
            return "result/idCard";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result/idCard";
    }


    private String storeGeneralPic(MultipartFile file) throws Exception {
        generalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadPicPath + generalFilename),
                        // 这里指定了下载的位置
                    StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new Exception("失败！" + generalFilename, e);
        }
        return generalFilename;
    }

    private String storeIdCardImage(MultipartFile file) throws Exception {
        idCardFilename = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadPicPath + idCardFilename),
                        // 这里指定了下载的位置
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new Exception("失败！" + idCardFilename, e);
        }
        return idCardFilename;
    }

    private String storeCarPic(MultipartFile file) throws Exception {
        carFilename = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadPicPath + carFilename),
                        // 这里指定了下载的位置
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new Exception("失败！" + carFilename, e);
        }
        return carFilename;
    }

    private String storeBankCardPic(MultipartFile file) throws Exception {
        bankCardFilename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadPicPath + bankCardFilename),
                        // 这里指定了下载的位置
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new Exception("失败！" + bankCardFilename, e);
        }
        return bankCardFilename;
    }

    public static String getCarFilePath(){
        return carFilename;
    }

    public static String getGeneralImagePath(){
       return generalFilename;
    }

    public static String getBankCardFilename() {
        return bankCardFilename;
    }

    public static String getIdCardFilename() {
        return idCardFilename;
    }
}