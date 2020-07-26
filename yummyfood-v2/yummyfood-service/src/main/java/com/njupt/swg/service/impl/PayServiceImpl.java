package com.njupt.swg.service.impl;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;
import com.njupt.swg.pojo.OrderItems;
import com.njupt.swg.pojo.Orders;
import com.njupt.swg.service.IOrderService;
import com.njupt.swg.service.IPayService;
import com.njupt.swg.utils.BigDecimalUtil;
import com.njupt.swg.utils.CommonJsonResult;
import com.njupt.swg.utils.QRCodeUtil;
import com.njupt.swg.utils.QiniuFileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author swg.
 * @Date 2020/7/26 17:25
 * @CONTACT 317758022@qq.com
 * @DESC 支付宝预下单
 */
@Service
@Slf4j
@PropertySource(value = {"classpath:parameter.properties"})
public class PayServiceImpl implements IPayService {

    @Value("${alipay.callback.url}")
    private String alipayCallbackUrl;

    @Autowired
    private IOrderService orderService;

    private static AlipayTradeService tradeService;
    static {

        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
    }

    @Override
    public CommonJsonResult pay(String userId,String orderNo) {
        //查询订单
        Orders order = orderService.selectByOrderNo(orderNo);
        if(order == null){
            return CommonJsonResult.errorMsg("该用户没有该订单");
        }

        //支付宝下单
        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = order.getId();


        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = new StringBuilder().append("欢迎使用YummyFood商城扫码支付,订单号:").append(outTradeNo).toString();


        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = order.getRealPayAmount().toString();


        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";



        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = new StringBuilder().append("订单").append(outTradeNo).append("购买商品共").append(totalAmount).append("元").toString();


        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");


        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();

        //遍历订单
        List<OrderItems> orderItemList = orderService.getByOrderNoUserId(orderNo);
        for(OrderItems orderItem : orderItemList){
            GoodsDetail goods = GoodsDetail.newInstance(orderItem.getItemSpecId().toString(), orderItem.getItemName(),
                    BigDecimalUtil.mul(orderItem.getPrice().doubleValue(),new Double(100).doubleValue()).longValue(),
                    orderItem.getBuyCounts());
            goodsDetailList.add(goods);
        }

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                .setNotifyUrl(alipayCallbackUrl)//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList);


        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                String qrFileName = String.format("qr-%s.png",response.getOutTradeNo());
                Map<String,Object> retMap = new HashMap<String,Object>();
                retMap = QRCodeUtil.zxingCodeCreate(response.getQrCode(),400,400,qrFileName);
                String qrpath = null;
                if(null != retMap){
                    qrpath = (String) retMap.get("url");
                    log.info("上传至云存储的图片访问路径为："+qrpath);
                    return CommonJsonResult.ok(qrpath);
                }
                log.error("生成二维码失败");
                return CommonJsonResult.errorMsg("生成二维码失败");
            case FAILED:
                log.error("支付宝预下单失败!!!");
                return CommonJsonResult.errorMsg("支付宝预下单失败!!!");

            case UNKNOWN:
                log.error("系统异常，预下单状态未知!!!");
                return CommonJsonResult.errorMsg("系统异常，预下单状态未知!!!");

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                return CommonJsonResult.errorMsg("不支持的交易状态，交易返回异常!!!");
        }
    }

    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                        response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }
}
