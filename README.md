#工具类使用介绍
1. 文件操作使用org.apache.commons.io.FileUtils

#配置
##虚拟路径配置
真实路径为：~/MingSource---虚拟路径/resources

！[配置图片](/resources/虚拟路径配置.jpg)
修改时注意同步修改对象SourceConfig中root的路径

##JWT密钥
keytool -genkey -alias "ming" 
-keyalg "RSA" 
-keystore "mykeystore.jks " 
-dname "CN=172.28.16.227, OU=localhost, O=localhost, L=SH, ST=SH, C=CN" 
-validity 3600 -keypass "123456" 
-storepass "123456"