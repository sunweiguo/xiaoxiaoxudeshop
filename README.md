## 一个电商项目

 考量了下，发现微信公众号平台挺好的，是一个可以长久维持的平台，并且平台比较方便未来的互动等。所以觉得我写的还不错的话可以关注下，我考虑会将系统学习的知识体系如计算机网络、数据库、redis等都迁移到公众号上，比较清晰，利于管理：

<div align="center">
	<img src="http://bloghello.oursnail.cn/qrcode_for_gh_7c3862b48f98_258.jpg" width="350px"></div></br>
</div>


这次的YummyFood电商项目呢，是跟随慕课网的《Java架构师成长直通车》学习的，之前呢也有一个项目是mmall，不过这次呢，我打算重新开始，扬帆起航，学习几位大牛老师的编码思路。

<div align="center">
	<img src="http://bloghello.oursnail.cn/yummyfood0.0.png"></div></br>
</div>


而且很重要的一点是，这个前端项目不会浪费我额外的精力，代码直接拷贝，放在tomcat即可跑起来，实在是太简单了，看了下前端代码，理解起来也很简单，我打算改改前端代码，去掉原来的一些风格比如广告友链。

并且最重要的一点是，对于这个电商项目，从单体应用到分布式应用再到最后的容器化、性能调优阶段，我都尽量保留下笔记，方便你我他。笔记我会将我认为重要的地方单独拎出来讨论，初期的单体项目如果只是记录如何增删改查，是完全没有意义的，我想穿插自己的理解、知识点的汇总在里面，成体系地记录，最后形成的是一个免费的、完整的电商项目的搭建和知识框架。

## 项目记录
  
1. 单体项目代码：master-single这个分支,yummyfood这个文件夹是初步完成的单体项目后端源码、fossi-center和fossi-shop分别为前端代码，本地如何运行可先看我做的第一个笔记哦~

2. 下载本工程并运行遇到的问题
	- clone太慢甚至clone不下来，原因是`github.global.ssl.fastly.net`域名被限制了，解决方案可参照：https://www.jianshu.com/p/3f6477049ece
	- 初始打开项目，右下角的event log中应该可以找到`Add as Maven Project`的链接，让其按照maven进行依赖下载和项目构建。并进行`maven package`或`maven install`操作。
	- yummyfood-v2下载下来后，发现alipay的依赖找不到：`Could not find artifact com.alipay:sdk-java:pom:20161213 in nexus (http://maven.aliyun.com/nexus/content/groups/public/)`，解决方式是找到案例支付demo工程`xiaoxiaoxudeshop\支付宝当面付Demo_Java\TradePayDemo\WebRoot\WEB-INF\lib`下的`alipay-sdk-java-3.3.0.jar`和`alipay-trade-sdk-20161215.jar`两个jar包，在这个目录下执行命令，将jar添加到本体maven仓库，以添加`alipay-sdk-java-3.3.0.jar`为例:

```
mvn install:install-file -Dfile=alipay-sdk-java-3.3.0.jar -DgroupId=com.alipay -DartifactId=sdk-java -Dversion=20161213 -Dpackaging=jar
```

生成好之后，就对应了pom文件写的：

```
<dependency>
    <groupId>com.alipay</groupId>
    <artifactId>sdk-java</artifactId>
    <version>20161213</version>
</dependency>
```

  
3. 我将详细记录针对单体项目从0到1的笔记，达到看看我的笔记即可进行完整功能开发和上线部署的效果。敬请期待。
	- [项目整体笔记](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzI3MDc5Mjk1MA==&action=getalbum&album_id=1436607562610524160#wechat_redirect)

4. 关于git tag 老是忘记，tag就是一个标签，帮助我们回退到某个版本的代码，我们通过tag的名称即可回退，而不需要根据某个提冗长的commit ID来回退，算是版本记录的补充吧。这里记录下：
	- 查看本地tag：git tag 
	- 新建tag：git tag -a v2.0 -m '完成首页展示和商品详情展示'
	- 推送指定tag至远程：git push origin v2.0
	- 推送本地所有tag至远程：git push origin --tags
	- 删除本地tag：git tag -d v2.0 
	- 删除远程tag：git push origin --delete tag 2.0
	- 本地查看不同tag的代码：get checkout v1.0
	- git reset --hard  版本号  来回到最初的小程序初始化代码  （提交的版本号可以通过 git log查到）
	- 获取远程分支：git fetch origin tag V2.0

