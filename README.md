toolkit for java
---
[ ![Download](https://api.bintray.com/packages/truthpure/maven/toolkit/images/download.svg) ](https://bintray.com/truthpure/maven/toolkit/_latestVersion)

one commonly used utils lib for java, its come from every good idea.

## upload to bintray cmd
``
./gradlew clean build -x test generatePomFileForMavenPublication publishMavenPublicationToMavenLocal bintrayUpload -PbintrayUser=truthpure -PbintrayKey=BINTRAY_KEY -PdryRun=false
``

## Version History
- 0.0.4 第一个稳定版
- 0.0.5 HttpClientUtil 方法变化较大，不兼容早期
- 0.0.6
- 0.0.8 update GsonUtil
- 0.0.9 update HttpClientUtil: 增加两个运行时异常抛出

## tips
1. version cannot conflict every time when you upload
2. fix idea cannot attach source code, but still don't know why is wrong

## util explain

### HttpClientUtil
增加 HttpConnException 和 HttpProcessException 两个运行时异常，需要手动捕获；
checked 异常为 HttpCodeException，包含 http 错误码；


