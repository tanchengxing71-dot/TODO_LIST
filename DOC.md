TODO List 项目说明文档
1. 技术选型

   编程语言：Kotlin,Dart，理由：生态成熟、快速开发。
   框架/库：Flutter,Android原生，理由：混合开发，主流方法。
   数据库/存储：Room，理由：快捷、高效率。

2. 项目结构设计
   Android作为业务层，Flutter作为ui层，大型企业App主流开发方式。（之所以采用这种方式，
主要原因是，混合开发是主流，其次我的Android经验较少，顺便锻炼下Android原生开发）
   目录结构示例：

api/ 网络框架
base/ 基础架构
channel/ 平台通道链接Flutter
db/ 数据库管理
main/ 主activity
method/ 方法
model/ 模型类data class


3. 需求细节与决策


   任务排序逻辑：
      数据库默认按照时间来升序排列


4. AI 使用说明

   使用chatGpt，主要用于Flutter层dialog，page的基础绘制与数据库管理类的框架搭建。后续再由我优化，稳固。

5. 运行与测试方式

   优先克隆flutter层，然后pub get我们flutter module的依赖，生成了.android文件过后，再克隆Android项目， 在运行该项目前，需要将FlutterBoost.java中的
   FlutterMain.findAppBundlePath()改为FlutterInjector.instance().flutterLoader().findAppBundlePath()
   Flutter 版本 3.29.0
   java 版本 17，随后直接构建运行即可

6. 总结与反思

   如果有更多时间，你会如何改进？
      添加通知功能，云端存储，闹钟设置
   你觉得这个实现的最大亮点是什么？
      混合开发，路由框架稳定，架构成熟
