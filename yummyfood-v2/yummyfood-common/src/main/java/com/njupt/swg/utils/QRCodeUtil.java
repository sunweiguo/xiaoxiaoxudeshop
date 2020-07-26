package com.njupt.swg.utils;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * @Author swg.
 * @Date 2020/7/26 21:33
 * @CONTACT 317758022@qq.com
 * @DESC 二维码工具类
 */
public class QRCodeUtil {
    //二维码颜色
    private static final int BLACK = 0xFF000000;
    //二维码颜色
    private static final int WHITE = 0xFFFFFFFF;


    /**  生成二维码
     * @param text    二维码内容
     * @param width    二维码宽
     * @param height    二维码高
     */
    public static Map<String,Object> zxingCodeCreate(String text, int width, int height,String name){
        Map<EncodeHintType, String> his = new HashMap<EncodeHintType, String>();
        //设置编码字符集
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            //1、生成二维码
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, his);
            //2、获取二维码宽高
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();
            //3、生成彩色二维码
            int[] data = new int[codeWidth * codeHeight];
            boolean flag1=true;
            int stopx=0;
            for (int y = 0; y < codeHeight; y++) {
                for (int x = 0; x < codeWidth; x++) {
                    if(encode.get(x, y)){
                        if(flag1){
                            flag1=false;
                        }
                    }else{
                        if(flag1==false){
                            stopx =x;
                            break;
                        }
                    }
                }
                if(flag1==false)
                    break;
            }

            for (int y = 0; y < codeHeight; y++) {
                for (int x = 0; x < codeWidth; x++) {
                    if(encode.get(x, y)){
                        if((x<stopx)&&(y<stopx)){
                            Color color = new Color(231, 144, 56);
                            int colorInt = color.getRGB();
                            data[y * width + x] =colorInt;
                        }else{
                            int num1 = (int) (50 - (50.0 - 13.0)/ encode.getHeight()* (y + 1));
                            int num2 = (int) (165 - (165.0 - 72.0) / encode.getHeight()* (y + 1));
                            int num3 = (int) (162 - (162.0 - 107.0)/ encode.getHeight() * (y + 1));
                            Color color = new Color(num1, num2, num3);
                            int colorInt = color.getRGB();
                            data[y * codeWidth + x] = colorInt;
                        }
                    }else{
                        data[y * codeWidth + x] = -1;//白色
                    }
                }
            }
            //4、将二维码放入缓冲流
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            image.getRaster().setDataElements(0, 0, width, height, data);
            //5、创建一个输出流
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            //6、将图片写出到指定位置（复制图片）
            ImageIO.write(image, "jpg", os);
            // 7、创建输入流获取存放输出流信息
            // toByteArray(): 创建一个新分配的字节数组,数组的大小和当前输出流的大小,内容是当前输出流的拷贝。
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            // 8、定义一个 Map 集合 存放返回值
            // UploadUtils.upload：调用七牛云的方法（因为返回值是Map类型的，所以用Map来存放）
            Map<String,Object> retMap = QiniuFileUploadUtil.upload(is,name);
            return retMap;
            /*//存储到本地
            File outPutImage = new File(outPutPath);
            //如果图片不存在创建图片
            if(!outPutImage.exists())
            	//outPutImage.createNewFile();
            //5、将二维码写入图片
            ImageIO.write(image, imageType, outPutImage);  */
        } catch (WriterException e) {
            e.printStackTrace();
            System.out.println("二维码生成失败");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("生成二维码图片失败");
        }
        return null;
    }

    /** 二维码解析
     * @param analyzePath    二维码路径
     * @return
     * @throws IOException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String zxingCodeAnalyze(String analyzePath) throws Exception{
        MultiFormatReader formatReader = new MultiFormatReader();
        String resultStr = null;
        try {
            File file = new File(analyzePath);
            if (!file.exists())
            {
                return "二维码不存在";
            }
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            Result result = formatReader.decode(binaryBitmap, hints);
            resultStr = result.getText();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(resultStr);
        return resultStr;
    }
}
