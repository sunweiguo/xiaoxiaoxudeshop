package com.njupt.swg.controller.center;

import com.njupt.swg.bo.CenterUserBO;
import com.njupt.swg.config.FileConfig;
import com.njupt.swg.pojo.Users;
import com.njupt.swg.service.center.ICenterUserService;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.utils.CookieUtils;
import com.njupt.swg.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/9/19 15:45
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@Api(value = "个人中心-用户信息接口",tags = "个人中心-用户信息接口")
@RestController
@RequestMapping("userInfo")
public class CenterUserController {

    @Autowired
    private FileConfig fileConfig;
    @Autowired
    private ICenterUserService centerUserService;

    private Map<String,String> getErrors(BindingResult bindingResult){
        Map<String,String> map = new HashMap<>();
        List<FieldError> errorList = bindingResult.getFieldErrors();
        for(FieldError error : errorList){
            map.put(error.getField(),error.getDefaultMessage());
        }
        return map;
    }

    @ApiOperation(value = "用户信息更新",notes = "用户信息更新",httpMethod = "POST")
    @PostMapping("update")
    public CommonJsonResult update(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String userId,
            @RequestBody @Valid CenterUserBO centerUserBO,
            BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = getErrors(bindingResult);
            return CommonJsonResult.errorMap(errorMap);
        }
        Users updateUser = centerUserService.update(userId,centerUserBO);
        updateUser = setNullProperty(updateUser);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(updateUser),true);
        //TODO：增加令牌TOKEN，整合redis
        return CommonJsonResult.ok(updateUser);
    }

    @ApiOperation(value = "用户头像修改",notes = "用户头像修改",httpMethod = "POST")
    @PostMapping("uploadFace")
    public CommonJsonResult uploadFace(
            @ApiParam(name = "userId",value = "用户ID",required = true)
            @RequestParam String userId,
            @ApiParam(name = "file",value = "用户头像",required = true)
                    MultipartFile file,
            HttpServletRequest request, HttpServletResponse response){
        //头像的保存的硬盘实际地址
        String fileSpace = fileConfig.getImageUserFaceLocation();
        //路径上为每一个用户增加一个userid用于区分不同用户上传
        String uploadPathPrefix = userId;
        if(file != null){
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            try {
                String fileName = file.getOriginalFilename();
                if(StringUtils.isNotBlank(fileName)){
                    String[] filenameArr = fileName.split("\\.");
                    String suffix = filenameArr[filenameArr.length - 1];
                    if(!suffix.equals("png") && !suffix.equals("jpg") && !suffix.equals("jpeg")){
                        return CommonJsonResult.errorMsg("不支持的图片格式，请用.png或.jpg或.jpeg的格式");
                    }
                    //文件名称：前缀face-加userid加后缀图片格式
                    String newFileName = fileConfig.getImageNamePrfix() + userId +"." + suffix;
                    //上传的头像最终保存的位置，加上时间戳是为了防止浏览器不刷新
                    String finalFacePath = fileSpace + uploadPathPrefix + newFileName;
                    //更新uploadPathPrefix，这个就作为http访问的后面部分，userID的文件夹+文件名称newFileName
                    uploadPathPrefix += newFileName;
                    File outFile = new File(finalFacePath);
                    if(outFile.getParentFile()!=null){
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream,fileOutputStream);
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if(fileOutputStream!=null){
                    try {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }
            }
        }else{
            return CommonJsonResult.errorMsg("上传的图片不能为空");
        }
        Users updateUser = centerUserService.updateFaceUrl(userId,fileConfig.getImageServerUrl()+uploadPathPrefix+"?t="+System.currentTimeMillis());
        updateUser = setNullProperty(updateUser);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(updateUser),true);
        //TODO：增加令牌TOKEN，整合redis
        return CommonJsonResult.ok(updateUser);
    }


    private Users setNullProperty(Users userResult){
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

}
