## 又一个电商项目

考量了下，发现微信公众号平台挺好的，是一个可以长久维持的平台，并且平台比较方便未来的互动等。所以觉得我写的还不错的话可以关注下，我考虑会将系统学习的知识体系如计算机网络、数据库、redis等都迁移到公众号上，比较清晰，利于管理：

![image](http://bloghello.oursnail.cn/qrcode_for_gh_7c3862b48f98_258.jpg)


这次的YummyFood电商项目呢，是跟随慕课网的《Java架构师成长直通车》学习的，之前呢也有一个项目是mmall，不过这次呢，我打算重新开始，扬帆起航，学习几位大牛老师的编码思路。

![image](http://bloghello.oursnail.cn/yummyfood0.0.png)

而且很重要的一点是，这个前端项目不会浪费我额外的精力，代码直接拷贝，放在tomcat即可跑起来，实在是太简单了，看了下前端代码，理解起来也很简单，我打算改改前端代码，去掉原来的一些风格比如广告友链。。。

并且最重要的一点是，对于这个电商项目，从单体应用到分布式应用再到最后的容器化、性能调优阶段，我都尽量保留下笔记，方便你我他。笔记我会将我认为重要的地方单独拎出来讨论，初期的单体项目如果只是记录如何增删改查，是完全没有意义的，我想穿插自己的理解、知识点的汇总在里面，成体系地记录，最后形成的是一个免费的、完整的电商项目的搭建和知识框架。

前端项目我将打磨一段时间，等符合我的要求后一起上传上来。

## 项目记录
  
1. 单体项目代码：master-single这个分支,yummyfood这个文件夹是初步完成的单体项目后端源码、fossi-center和fossi-shop分别为前端代码，本地如何运行可先看我做的第一个笔记哦~
  
2. 我将详细记录针对单体项目从0到1的笔记，达到看看我的笔记即可进行完整功能开发和上线部署的效果。敬请期待。
	- [1、【单体YummyFood商城】-项目本地预览](http://note.youdao.com/noteshare?id=e6b4757288b117ea5336f0297805ea89&sub=D614DA7716D04274A5E10A1941B5D641)
	- [2、【单体YummyFood商城】-后端工程初始化](http://note.youdao.com/noteshare?id=c3bbc66c0608e976acc6dcd38c6bd198&sub=B5D5FA2B291F48908FCC5CF533B55257)
	- [3、【单体YummyFood商城】-数据库表分析和业务逻辑简单梳理](http://note.youdao.com/noteshare?id=ce5b84c8942dac62eb78fe50c0ac8fa1&sub=01F7EB278687446993E2C7F2BAF0F23E)
	- [4、【单体YummyFood商城】-从写一个最简单的接口开始](http://note.youdao.com/noteshare?id=34f0b0dd5feaa073d8dc806a143fd944&sub=48B78AF12E80488CAE3C221286343A29)
		- [4-1、【单体YummyFood商城补充知识1】-SpringBoot通用知识深入（切面、异常、单元测试）](http://note.youdao.com/noteshare?id=ee7298b0e4bf0d8a7034e1fff8b19192&sub=9951BF15DC994202A18D9C9D9E332D42)
		- [4-2、【单体YummyFood商城补充知识2】-对于开闭原则、IOC、DI的理解](http://note.youdao.com/noteshare?id=ea3595daea99c8826f952e0409c60642&sub=4D376B8C0E9B495EA0D67542ECE361B0)
	- [5、【单体YummyFood商城】-实现用户的注册、日志配置、Spring事务](http://note.youdao.com/noteshare?id=b0c409090514ae4df7be19a6b9ac4f00&sub=E9AADF78128642979543D22FBAFF5F40)
	- [6、【单体YummyFood商城】-实现用户的登录和注销逻辑以及整合swagger2](http://note.youdao.com/noteshare?id=f8be880d71f4c72b48247affc22c37e2&sub=AD944B0310A3446F891D4109BE9B071F)
	- [7、【单体YummyFood商城】-实现商品分类展示+轮播图展示](http://note.youdao.com/noteshare?id=ad7ff6a8cc5ac3e759569bfa248ee938&sub=11A4B9262C4F49EB9469EF2C17B2B8F8)
	- [8、【单体YummyFood商城】-实现推荐商品列表展示和商品详情页](http://note.youdao.com/noteshare?id=f0ba9c23473d032ae5b80d8584bbf04e&sub=54E42DBC431448219E5906CEB1F936A7)

3. 关于git tag 老是忘记，tag就是一个标签，帮助我们回退到某个版本的代码，我们通过tag的名称即可回退，而不需要根据某个提冗长的commit ID来回退，算是版本记录的补充吧。这里记录下：
	- 查看本地tag：git tag 
	- 新建tag：git tag -a v2.0 -m '完成首页展示和商品详情展示'
	- 推送指定tag至远程：git push origin v2.0
	- 推送本地所有tag至远程：git push origin --tags
	- 删除本地tag：git tag -d v2.0 
	- 删除远程tag：git push origin --delete tag 2.0
	- 本地查看不同tag的代码：get checkout v1.0
	- git reset --hard  版本号  来回到最初的小程序初始化代码  （提交的版本号可以通过 git log查到）
	- 获取远程分支：git fetch origin tag V2.0

