package com.ghostben.image;

import com.ghostben.image.controller.FileUploadController;
import com.ghostben.image.entity.BankCard;
import com.ghostben.image.entity.CarCard;
import com.ghostben.image.entity.HandWrite;
import com.ghostben.image.entity.IdCardFront;
import com.ghostben.image.service.imageToWord.BankCardFormate;
import com.ghostben.image.service.imageToWord.ImageData;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageAppTest {

    @Autowired
    ImageData imageData;

    @Test
    public void contextLoads() {
        try {
            Gson gson = new Gson();
//            ImageData.checkIdCardFrontFile("D:\\image\\idcard_front.jpg");
//            IdCardFront result = gson.fromJson(ImageData.checkIdCardFrontFile("D:\\image\\idcard_front.jpg"),IdCardFront.class);
//            System.out.println(result);


            ImageData.checkLicensePlate("D:\\image\\car-license.jpg");
            CarCard carCard = gson.fromJson(ImageData.checkLicensePlate("D:\\image\\car-license.jpg"),CarCard.class);
            System.out.println(carCard);

//            for(CarCard.WordsResult wordsResult : carCard.getWordsResults()) {
//                System.out.println(wordsResult);
//            }

//            ImageData.checkHandWriting("D:\\image\\handwriting.jpg");
//            HandWrite handWrite = gson.fromJson(ImageData.checkHandWriting("D:\\image\\handwriting.jpg"),HandWrite.class);
//            System.out.println(handWrite);

//            ImageData.checkBankCardFile("D:\\image\\bank2.jpg");
//            BankCard bankCard = gson.fromJson(ImageData.checkBankCardFile("D:\\image\\bank2.jpg"), BankCard.class);
//            System.out.println(bankCard);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getSubString(){
        System.out.println("123456789".substring(5,7));
    }

}

