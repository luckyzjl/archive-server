package com.xingyu.web;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.xingyu.base.BizException;
import com.xingyu.constants.SysParamKey;
import com.xingyu.service.CustomService;
import com.xingyu.service.cache.SysDictCache;
import com.xingyu.service.cache.SysParamsCache;
import com.xingyu.wrapper.ApiResponse;
import com.xingyu.wrapper.WrapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * created by zhujl @2019-07-21
 */

@RestController
@RequestMapping(value="/archive/custom")
public class CustomController extends BaseController {

    @Autowired
    SysParamsCache sysParamsCache;
    @Autowired
    CustomService customService;
    @Autowired
    SysDictCache sysDictCache;


    @RequestMapping(value="/sysDict")
    public ApiResponse sysDict() throws BizException {
        return WrapResponse.wrap(sysDictCache.getSysDictMap());
    }

    @RequestMapping(value="/teacher")
    public ApiResponse teacher() throws BizException {
        return WrapResponse.wrap(customService.getTeacher());
    }

//    @RequestMapping(value="/assessItem")
//    public ApiResponse assessItem() throws BizException {
//        return WrapResponse.wrap(customService.getSdAssessItem());
//    }

    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ApiResponse testUpload(HttpServletRequest request) throws IOException, NoSuchAlgorithmException, URISyntaxException {


        return WrapResponse.wrapSuccess();
    }

    // 文件允许格式
    private String[] allowFiles = {".gif", ".png", ".jpg", ".jpeg", ".bmp"};

    //检查文件后缀，判断是否为图片类型
    private boolean checkFileType(String fileName) {
        Iterator<String> type = Arrays.asList(this.allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    private void uploadFile(HttpServletRequest request,String qiniuResourceId) throws BizException,IOException,NoSuchAlgorithmException{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        //页面控件的文件流
        MultipartFile multipartFile = multipartRequest.getFile("file");
        // 获取文件名及后缀
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (checkFileType(suffix) ){
            //抽取摘要
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(multipartFile.getBytes());
            byte[] digesta = messageDigest.digest();

            BigInteger bigInteger = new BigInteger(1, digesta);
            String  uri = bigInteger.toString(16);

            UploadManager uploadManager = new UploadManager();
            Auth auth = Auth.create(sysParamsCache.getParameter(SysParamKey.QINIU_ACCESS_KEY),
                    sysParamsCache.getParameter(SysParamKey.QINIU_SECRET_KEY));
            String token = auth.uploadToken(SysParamKey.QINIU_BUCKET_NAME);

            Response qiniu_resp = uploadManager.put(multipartFile.getBytes(), fileName, token);

        }
    }
}
