这是我自用的魔改版，主要是一些数值的调整

下载地址 [Release](https://github.com/CHanzyLazer/gregtech6-CH_Edition/releases)

[OmniOcular](https://github.com/CHanzyLazer/OmniOcular_GT6CHE)

# License

This Mod is licensed under the [GNU Lesser General Public License](LICENSE).
All assets, unless otherwise stated, are dedicated to the public domain
according to the [CC0 1.0 Universal Public Domain Dedication](src/main/resources/LICENSE.assets).
Any assets containing the [GregTech logo](src/main/resources/logos) or any
derivative of it are licensed under the
[Creative Commons Attribution-NonCommercial 4.0 International Public License](src/main/resources/LICENSE.logos).

# Changelog
## CH-0.1
- 配置文件：额外添加配置文件在 config/gregtechCH 文件夹，可以大部分调整数据
- 燃油引擎：现在可以在在配置文件选择禁用
- 燃油引擎：效率减少到 30%
- 燃油引擎：修改了合成表，现在耗材增加到了 80.5 个相应的材料
- 蒸汽锅炉：修改蒸汽锅炉的蒸馏水消耗逻辑，现在无论蒸汽锅炉 效率/钙化程度 为多少，消耗 1L 蒸馏水永远产生 160L 蒸汽
- 蒸汽锅炉：修改蒸汽锅炉超频逻辑，现在蒸汽锅炉可以连续超频了，也就是，在气压从 1/2 增加到 3/4 时，释放的蒸汽速率在 1 倍到 2 倍之间连续增加（超过 3/4 时固定为 2 倍）
- 蒸汽锅炉：为蒸汽锅炉额外添加了一个全局效率，现在蒸汽锅炉的实际效率为 `钙化程度效率 * 全局效率`
- 蒸汽锅炉：普通蒸汽锅炉全局效率为 50%，致密蒸汽锅炉全局效率为 60%
- 蒸汽涡轮：修改了蒸汽涡轮转换能量的逻辑，现在输出应该更加稳定了
- 燃烧室：  除了砖头燃烧室和流化床燃烧室，其余所有燃烧室的产热翻倍
- 核反应堆：工业冷却剂下燃料棒向周围释放中子数的 4 倍消弱到原来的 3/8，即 1.5 倍

# Start
目前我发现的指令：
- `./gradlew setupDevWorkspace setupDecompWorkspace` 设置工作环境
- `./gradlew assemble` 打包到 `build\lib`
- `./gradlew clean` 清理工作区
- `./gradlew runClient` 在客户端上运行
- `./gradlew runServer` 在服务端上运行
- 可以组合使用，例如 `./gradlew clean setupDevWorkspace setupDecompWorkspace`，清理并重新设置工作环境

# TODO
所有能量生成机器效率降低，让后期换用效率更高设备存在感知。

## RU 产生
- 为所有材料种类设置一个基准效率，而对应机器效率和基准效率有关
- 蒸汽涡轮效率 40%
- 大型锅炉效率 80%，大型蒸汽涡轮效率效率 70%
- 大型燃气涡轮效率 60%
- 燃气涡轮核心需要钻石晶体处理器

## EU 产生
- 需要修正一下一套蒸汽发电刚好卡在过载边缘的问题（也可能是 greg 故意为之？），现在一套下来应该要刚好超过电压线，具体数值待定
- 小发电机效率 60%
- 大发电机效率 95%
- 红石通量发电机效率 10%，主要由于 rf 的变态机器太多

## 核反应堆
- 添加新的核反应过程

## 合成路线
- 离心废水不再能直接得到金属粉而是离心矿物
- 添加铬铁合金，铬铁矿现在在坩埚会直接得到铬铁合金，可用于制造不锈钢
- 铬的合成路线修改，可能会参考 gt6u
- 同时自然生成的陨铁石含有的陨铁应当减少，保证不同线路的难度相当

