toolkit for java
---
[ ![Download](https://api.bintray.com/packages/truthpure/maven/toolkit/images/download.svg) ](https://bintray.com/truthpure/maven/toolkit/_latestVersion)

one commonly used utils lib for java, its come from every good idea.

## upload to bintray cmd
``
./gradlew clean build -x test bintrayUpload -PbintrayUser=truthpure -PbintrayKey=xxx -PdryRun=false
``
## utils
- GsonUtil
- JscksonUtil
- FastjsonUtil
- ...

## commons
- ...

## tips
1. version cannot conflict every time when you upload
2. fix idea cannot attach source code, but still don't know why is wrong

## Version History
- 0.0.4 第一个稳定版
- 0.0.5 HttpClientUtil 方法变化较大，不兼容早期
- 0.0.6
- 0.0.7 update GsonUtil