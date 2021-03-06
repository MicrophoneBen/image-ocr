### image-ocr
基于RCNN的文字识别系统

系统已经基于源码的基础上进行了Docker镜像打包，考虑到识别准确率以及提高系统健壮性，使用了百度的API进行识别调用，我本地也也有使用YOLOv3
搭建了一个自己本地的OCR，由于数据集以及神经网络的算法没有进行优化，效果比较一般。

### 基于本地的部署

- 环境要求
  1. window系统或者Linux系统
  2. 已经安装好Java1.8 的开发环境或者运行环境
  3. 需要Maven做版本依赖管理
  4. 如果使用docker容器进行安装，还需要本地进行Docker引擎的安装
以上的环境配置在网络上有许多的博客教程可以参考，这里不再进行详述。

1. 拉取源码之后，在本地进行打包运行，首先，需要Maven做依赖管理，所以你可能需要下载Maven3.0及以上的版本，然后配置依赖镜像仓库。
2. 关于Maven的版本和Maven的路径配置，请参考网络上的其他博客文章，这里不再详述。
3. 进行Maven打包。 打开源码所在位置，使用
```shell
# maven 清理项目
mvn clean
# 使用Maven构建项目
mvn package
```
4. 以上两步运行之后，在当前项目的路径下，你会发现出来了target目录，这个目录下有安装好的软件包，使用java -jar   XXX-SNAPSHOT.jar运行项目
5. 打开http://127.0.0.1:8088就可以使用项目。


### 基于Docker的本地或者云环境部署
1. 在本地已经安装了Docker 引擎的基础上，进行项目的镜像打包
2. 参考上面的步骤进行软件包生成
3. 使用 docker build -t my-ocr . 进行本地docker镜像生成。
3. 使用docker run -d -p8088:8088 -v /你本地机地址:/data/images
4. 打开浏览器，访问http://localhost:8080 既可以访问项目
