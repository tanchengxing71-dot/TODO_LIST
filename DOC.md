# TODO List 项目说明文档

## 1. 技术选型

- **编程语言**：Kotlin、Dart  
  理由：生态成熟、开发效率高。
- **框架/库**：Flutter、Android 原生  
  理由：混合开发是当下主流方式，易维护、扩展性强。
- **数据库/存储**：Room  
  理由：快捷、高效、适合本地持久化。

---

## 2. 项目结构设计

Android 作为业务层，Flutter 作为 UI 层，是大型企业 App 常用的混合开发结构。  
采用这种方式的主要原因：

1. 混合开发已是主流趋势；
2. 本项目可顺便提升 Android 原生开发能力。

### 📁 目录结构示例

```
api/        网络框架
base/       基础架构
channel/    平台通道，用于连接 Flutter
db/         数据库管理（Room）
main/       主 Activity
method/     工具方法模块
model/      模型类（data class）
```

---

## 3. 需求细节与决策

### ✔ 任务排序逻辑
- 数据库默认按时间 **升序** 排列。

---

## 4. AI 使用说明

- 使用工具：**ChatGPT**
- AI 主要辅助内容：
   - Flutter 层 dialog 与 page 的基础绘制
   - 数据库管理类的架构搭建
- AI 输出后会由我进一步优化与完善，以确保代码质量与稳定性。

---

## 5. 运行与测试方式

### ✔ 运行步骤

1. **先克隆 Flutter 层项目**。
2. 执行 `flutter pub get` 生成 `.android` 目录。
3. 再克隆 Android 项目。
4. 启动前需要在 `FlutterBoost.java` 中手动修改：

   ```java
   // 原有写法：
   FlutterMain.findAppBundlePath()

   // 替换为：
   FlutterInjector.instance().flutterLoader().findAppBundlePath()
   ```

5. 使用以下环境测试通过：
   - Flutter 版本：**3.29.0**
   - Java 版本：**17**

6. 然后即可构建并运行项目。

---

## 6. 总结与反思

### 如果有更多时间，我会改进：
- 添加系统通知功能
- 云端存储同步（例如 Firebase / LeanCloud）
- 添加任务闹钟 / 时间提醒

### 本项目的最大亮点：
- **混合开发架构成熟、稳定**
- 路由框架清晰
- Flutter + Android 协作良好，可扩展性强
