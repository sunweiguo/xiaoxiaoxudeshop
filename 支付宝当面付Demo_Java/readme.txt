# DEMO仅供参考，实际开发中需要结合具体业务场景修改使用
#
# 运行环境:jdk1.6及以上
# 当面付2.0demo使用前必读

# 直接运行demo步骤如下(eclipse直接导入工程即可):
1、请先确认zfbinfo.properties配置文件是否已配置完成
2、运行Main.java中的main方法

# 集成至商户系统步骤如下(以maven工程结构为例):
1、拷贝java目录下的Main.java（和DemoHbRunner.java,如果需要集成交易保障接口）至系统商源代码目录
2、将lib目录下所有jar文件添加至系统商lib目录，如果没有alipay-trade-sdk.jar(此jar包集成了当面付交易逻辑和交易保障接口)，则将TradePaySDK编译为该jar包
3、拷贝resources目录下的配置文件至系统商classpath根目录
4、在系统商项目中运行Main方法，确认集成无误
5、系统商使用main方法中的调用样例进行商户端系统开发

Q:扫码支付、统一下单+JSAPI唤起收银台支付后，支付成功异步通知如何处理？
1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
3、校验通知中的seller_id（或者seller_email) 是否为该笔交易对应的操作方（一个商户可能有多个seller_id/seller_email）
4、验证接口调用方的app_id。。

# 交易保障接口常见问题
https://doc.open.alipay.com/doc2/detail.htm?spm=0.0.0.0.yzagzo&treeId=26&articleId=103903&docType=1

### 当面付2.0demo代码结构TradePayDemo ###
src
└── main
    ├── java
    │   └── com
    │       └── alipay
    │           └── demo
    │               └── trade
    │                   ├── DemoHbRunner.java  # 交易保障数据收集器的范例实现，如果接入交易保障接口，参考此类实现，调用参考Main方法
    │                   └── Main.java          ######## 当面付2.0调用范例 ########
    ├── resources
    │   └── zfbinfo.properties  # 配置文件，调用Main方法之前请确认配置文件是否已正确配置
    └── webapp
        ├── WEB-INF
        │   ├── lib  # 依赖类库
        │   │   ├── alipay-sdk-java20151021120052-source.jar
        │   │   ├── alipay-sdk-java20151021120052.jar
        │   │   ├── commons-codec-1.10.jar
        │   │   ├── alipay-trade-sdk.jar              # TradePaySDK模块创建生成的lib
        │   │   ├── commons-configuration-1.10.jar
        │   │   ├── commons-lang-2.6.jar
        │   │   ├── commons-logging-1.1.1.jar
        │   │   ├── core-2.1.jar
        │   │   ├── gson-2.3.1.jar
        │   │   └── hamcrest-core-1.3.jar
        │   └── web.xml
        ├── images
        │   ├── alipay.gif
        │   └── new-btn-fixed.png
        ├── index.html            # web方式调用当面付的首页
        ├── trade_pay.jsp         # 当面付2.0条码支付web界面，本质和Main方法中条码支付方法一致
        ├── trade_precreate.jsp   # 当面付2.0预下单web界面，本质和Main方法中预下单方法一致
        ├── trade_query.jsp       # 当面付2.0查询web界面，本质和Main方法中查询方法一致
        └── trade_refund.jsp      # 当面付2.0退货界面，本质和Main方法中退货方法一致

### 当面付2.0sdk代码结构TradePaySDK ###
src
└── main
    ├── java
    │   └── com
    │       └── alipay
    │           └── demo
    │               └── trade
    │                   ├── config
    │                   │   ├── Configs.java    # 配置文件,解析properties文件
    │                   │   └── Constants.java  # 常量定义
    │                   ├── model
    │                   │   ├── ExtendParams.java  # 扩展参数
    │                   │   ├── GoodsDetail.java
    │                   │   ├── TradeStatus.java
    │                   │   ├── builder  # 当面付2.0请求包
    │                   │   │   ├── AlipayHeartbeatSynContentBuilder.java    # 服务保障接口请求bizContent结构体
    │                   │   │   ├── AlipayTradePayContentBuilder.java        # 条码支付请求bizContent结构体
    │                   │   │   ├── AlipayTradePrecreateContentBuilder.java  # 扫码支付(产生二维码)请求bizContent结构体
    │                   │   │   ├── AlipayTradeQueryCententBuilder.java      # 当面付2.0查询请求bizContent结构体
    │                   │   │   ├── AlipayTradeRefundContentBuilder.java     # 当面付2.0退款请求bizContent结构体
    │                   │   │   └── RequestBuilder.java    # 请求抽象类
    │                   │   ├── hb  # 服务保障接口数据模型
    │                   │   │   ├── EquipStatus.java           # 发送心跳时的设备状态枚举
    │                   │   │   ├── EquipStatusAdapter.java
    │                   │   │   ├── ExceptionInfo.java         # 异常信息枚举
    │                   │   │   ├── ExceptionInfoAdapter.java
    │                   │   │   ├── HbStatus.java              # 交易状态
    │                   │   │   ├── PosTradeInfo.java          # 机具商同步使用的交易结构体
    │                   │   │   ├── Product.java               # 发送心跳的设备所依赖的支付宝产品
    │                   │   │   ├── SysTradeInfo.java          # 系统商同步使用的交易结构体
    │                   │   │   ├── TradeInfo.java             # 交易结构体接口，用于隐藏系统商交易结构体和机具商交易结构体的不同
    │                   │   │   ├── TradeInfoAdapter.java
    │                   │   │   └── Type.java                  # 发送心跳的设备类型枚举
    │                   │   └── result  当面付2.0应答包
    │                   │       ├── AlipayF2FPayResult.java        # 当面付2.0支付应答
    │                   │       ├── AlipayF2FPrecreateResult.java  # 当面付2.0预下单（产生二维码）应答
    │                   │       ├── AlipayF2FQueryResult.java      # 当面付2.0查询应答
    │                   │       ├── AlipayF2FRefundResult.java     # 当面付2.0退货应答
    │                   │       └── Result.java
    │                   ├── service
    │                   │   ├── AlipayMonitorService.java   # 提供交易保障服务
    │                   │   ├── AlipayTradeService.java     # 提供当面付2.0服务
    │                   │   └── impl
    │                   │       ├── AbsAlipayService.java        # 服务实现类抽象类，所有的服务实现类的父类
    │                   │       ├── AbsAlipayTradeService.java   # 当面付服务实现类抽象类，包含了当面付2.0预下单、查询、退货交易的封装
    │                   │       ├── AlipayMonitorServiceImpl.java       # 交易保障服务具体实现
    │                   │       ├── AlipayTradeServiceImpl.java         # 当面付2.0具体实现（不提供交易保障接口的集成）
    │                   │       ├── AlipayTradeWithHBServiceImpl.java   # 当面付2.0具体实现（集成了交易保障逻辑）
    │                   │       └── hb  # 交易保障逻辑相关代码
    │                   │           ├── AbsHbRunner.java    # 抽象的交易保障数据收集器，系统商创建自己的子类用于自定义收集数据,参考DemoHbRunner
    │                   │           ├── HbListener.java     # 交易保障监听器实现，每当交易完成后，将交易耗时写入队列
    │                   │           ├── HbQueue.java        # 保存交易数据的队列，添加队列每次只加入1条交易，读取队列每次尽量读满30条交易
    │                   │           └── TradeListener.java  # 交易监听接口，在当面付交易流程中加入监听逻辑
    │                   └── utils
    │                       ├── GsonFactory.java  # 使用了gson序列化
    │                       ├── Utils.java        # 杂物工具类
    │                       └── ZxingUtils.java   # 使用了zxing库进行二维码的生成
    └── lib # 依赖类库
        ├── alipay-sdk-java20151021120052-source.jar  # 支付宝底层api调用的sdk源码
        ├── alipay-sdk-java20151021120052.jar
        ├── commons-codec-1.10.jar
        ├── commons-configuration-1.10.jar
        ├── commons-lang-2.6.jar
        ├── commons-logging-1.1.1.jar
        ├── core-2.1.jar
        ├── gson-2.3.1.jar
        └── hamcrest-core-1.3.jar

# DEMO仅供参考，实际开发中需要结合具体业务场景修改使用