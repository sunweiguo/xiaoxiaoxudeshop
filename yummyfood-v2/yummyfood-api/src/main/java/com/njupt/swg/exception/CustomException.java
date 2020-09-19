package com.njupt.swg.exception;

import com.njupt.swg.utils.CommonJsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @Author swg.
 * @Date 2020/4/1 23:03
 * @CONTACT 317758022@qq.com
 * @DESC
 */
@RestControllerAdvice
public class CustomException {

    //上传的文件大小限制最大为500K，大于这个就触发这个异常，提示前端
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public CommonJsonResult handlerSizeException(MaxUploadSizeExceededException ex){
        return CommonJsonResult.errorMsg("文件上传大小超过500K，请压缩图片或者降低图片质量");
    }

}
