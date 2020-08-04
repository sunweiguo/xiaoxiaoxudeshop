package com.njupt.swg.utils;

import com.google.gson.Gson;
import com.njupt.swg.constants.Constants;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/7/26 21:17
 * @CONTACT 317758022@qq.com
 * @DESC 上传至七牛云工具类
 */
@Component
@PropertySource(value = {"classpath:parameter.properties"})
public class QiniuFileUploadUtil {
    public static Map<String,Object> upload(InputStream stream,String name) {

        Map<String,Object> retMap = new HashMap<String,Object>();
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        // ...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // ...生成上传凭证，然后准备上传
        String accessKey = Constants.QINIU_ACCESS_KEY;
        String secretKey = Constants.QINIU_SECRET_KEY;
        String bucket = Constants.QINIU_HEAD_IMG_BUCKET_NAME;
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket,name);
            try {
                Response response = uploadManager.put(stream, name, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                //指定一个前缀的域名+上传的文件名，就是最后的路径
                retMap.put("url",Constants.QINIU_HEAD_IMG_BUCKET_URL+putRet.key);
                retMap.put("hash",putRet.hash);
//				System.out.println(putRet.key);
//				System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    ex2.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return retMap;
    }
}
