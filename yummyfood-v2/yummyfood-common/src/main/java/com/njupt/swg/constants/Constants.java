package com.njupt.swg.constants;


/**
 * @Author swg.
 * @Date 2020/4/1 21:27
 * @CONTACT 317758022@qq.com
 * @DESC
 */
public class Constants {
    /***七牛keys start****/
    public static final String QINIU_ACCESS_KEY="_yM_DR8SeqFWpOXUIRBPOt2f_rqHd64xPRY97ipU";

    public static final String QINIU_SECRET_KEY="2TEWpK3xE-EwjqiaSCxlY43TkPY3H4-oufS_s2Km";

    public static final String QINIU_HEAD_IMG_BUCKET_NAME="blog";

    public static final String QINIU_HEAD_IMG_BUCKET_URL="http://bloghello.oursnail.cn/";
    /***七牛keys end****/

    /***支付宝相关状态****/
    public interface  AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }
    /***支付宝相关状态****/
    public enum OrderStatusEnum{
        CANCELED(0,"已取消"),
        NO_PAY(10,"未支付"),
        PAID(20,"已付款"),
        SHIPPED(30,"已发货"),
        ORDER_SUCCESS(40,"订单完成"),
        ORDER_CLOSE(50,"订单关闭");


        OrderStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
        public static OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

    public enum PayPlatformEnum{
        ALIPAY(1,"支付宝");

        PayPlatformEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }
}
