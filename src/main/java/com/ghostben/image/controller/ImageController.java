package com.ghostben.image.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author : Microphoneben
 * @date : 2018/10/24
 * @description : ImageController
 */
@Controller
public class ImageController {


    @RequestMapping(value = "/erp/get-products", method = RequestMethod.GET)
    public @ResponseBody
    String getProducts() {
        return "文件上传了";
    }

    @RequestMapping(value = "/erp/count-products", method = RequestMethod.GET)
    public @ResponseBody
    String countProducts() {
        return "文件已保存";
    }
}
