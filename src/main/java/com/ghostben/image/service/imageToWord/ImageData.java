package com.ghostben.image.service.imageToWord;
import com.ghostben.image.dao.ImageMapper;
import com.ghostben.image.dao.OpRelationMapper;
import com.ghostben.image.dao.ResultMapper;
import com.ghostben.image.entity.dao.IdentityResultDao;
import com.ghostben.image.entity.dao.ImagesDao;
import com.ghostben.image.entity.dao.OperationRelation;
import com.ghostben.image.util.BaseImg64;
import com.ghostben.image.service.requestToken.ImageToWordUrlService;
import com.ghostben.image.util.commonAiUtil.ImageWordAPI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 图像文字识别
 **
  * @author ben.zhang.b.q
  * @date 2018/10/17 15:23
  **/
@Component
public class ImageData {
    private static final Log logger = LogFactory.getLog(ImageData.class);
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private ResultMapper resultMapper;
    @Autowired
    private OpRelationMapper opRelationMapper;
    /**
     * @param path 本地图片地址
     * @return 识别结果，为json格式
     * @throws URISyntaxException URI打开异常
     * @throws IOException        io流异常
     */
    public static String checkGenralBasicFile(String path) throws URISyntaxException, IOException {
        String POST_URL = ImageWordAPI.general + ImageToWordUrlService.getAuth();
        File file = new File(path);
        if (!file.exists()) {
            throw new NullPointerException("图片不存在！！");
        }
        String param = parameter("image=",path);
        return post(POST_URL,param);
    }

    public static String checkBankCardFile(String path) throws URISyntaxException, IOException {
        String POST_URL = ImageWordAPI.bankCard + ImageToWordUrlService.getAuth();
        File file = new File(path);
        if (!file.exists()) {
            throw new NullPointerException("图片不存在！！");
        }
        String param = parameter("image=",path);
        return post(POST_URL,param);
    }

    public static String checkHandWriting(String path) throws URISyntaxException,IOException{
        String POST_URL = ImageWordAPI.handWriting + ImageToWordUrlService.getAuth();
        File file = new File(path);
        if(!file.exists()) {
            throw new NullPointerException("图片不存在！！");
        }
        String param = parameter("image=",path);
        return post(POST_URL,param);
    }

    public static String checkLicensePlate(String path) throws URISyntaxException, IOException{
        String POST_URL = ImageWordAPI.licensePlate + ImageToWordUrlService.getAuth();
        File file = new File(path);
        if(!file.exists()) {
            throw new NullPointerException("图片不存在！！");
        }
        String param = parameter("multi_detect=true&image=",path);
        return post(POST_URL,param);
    }

    public static String checkIdCardFrontFile(String path) throws URISyntaxException,IOException{
        /**
         *@description : 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back
         */
        String idCardFront = "id_card_side=front&detect_direction=true&detect_risk=true&detect_direction=true&";
        String POST_URL = ImageWordAPI.idCard + ImageToWordUrlService.getAuth();


        File file = new File(path);
        if (!file.exists()) {
            throw new NullPointerException("图片不存在");
        }
        String param = parameter(idCardFront + "image=",path);
        return post(POST_URL,param);
    }

    public static String checkIdCardBackFile(String path) throws URISyntaxException,IOException{
        /**
         *@description : 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back
         */
        String idCardBack = "id_card_side=back&detect_direction=true&detect_risk=true&detect_direction=true&";
        String POST_URL = ImageWordAPI.idCard + ImageToWordUrlService.getAuth();


        File file = new File(path);
        if (!file.exists()) {
            throw new NullPointerException("图片不存在");
        }
        String param = parameter(idCardBack + "image=",path);
        return post(POST_URL,param);
    }

    private static String parameter(String imagePrefix,String path) {
        //post请求中携带参数 image=图片的Base64编码
        String image = BaseImg64.getImageStrFromPath(path);
        return imagePrefix + image;
    }

    /**
     * @param url 图片url
     * @return 识别结果，为json格式
     */
    public static String checkUrl(String url) throws IOException, URISyntaxException {
        String POST_URL = ImageWordAPI.general + ImageToWordUrlService.getAuth();
        String param = "url=" + url;
        return post(POST_URL,param);
    }

    /**
     * 通过传递参数：url和image进行文字识别
     *
     * @param param 区分是url还是image识别
     * @return 识别结果
     * @throws URISyntaxException URI打开异常
     * @throws IOException        IO流异常
     */
    public static String post(String POST_URL,String param) throws URISyntaxException, IOException {
        //开始搭建post请求
        HttpClient httpClient = new DefaultHttpClient();
        HttpClientParams.setCookiePolicy(httpClient.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);

        HttpPost post = new HttpPost();
        URI url = new URI(POST_URL);
        post.setURI(url);
        //设置请求头，请求头必须为application/x-www-form-urlencoded，因为是传递一个很长的字符串，不能分段发送
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        StringEntity entity = new StringEntity(param);
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
        logger.info(response.toString());
        //System.out.println(response.toString());
        if (response.getStatusLine().getStatusCode() == 200) {
            String str;
            try {
                /*读取服务器返回过来的json字符串数据*/
                str = EntityUtils.toString(response.getEntity());
                logger.info(str);
                //System.out.println(str);
                return str;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> insertData(String path, String result) {
        Map<String, Object> resultMap = new HashMap<>(16);

        ImagesDao imagesDao = new ImagesDao(path);
        IdentityResultDao identityResultDao = new IdentityResultDao(result);
        OperationRelation operationRelation = new OperationRelation(imagesDao.getImageId(),
                identityResultDao.getIdentityId());
        //图片路径入库
        //imageMapper.insertImagesUrl(imagesDao);

        //识别结果入库
        //resultMapper.insertResult(identityResultDao);

        //维护识别结果关系
        //opRelationMapper.insetRelation(operationRelation);

        if (imageMapper.insertImagesUrl(imagesDao) > 0 &&
                resultMapper.insertResult(identityResultDao) > 0) {
            resultMap.put("code", 1);
            resultMap.put("message", "识别成功，数据已入库");
        } else {
            resultMap.put("code", 0);
            resultMap.put("message", "识别失败，数据已回滚");
        }
        return resultMap;
    }
}
