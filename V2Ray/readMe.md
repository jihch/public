https://www.v2ray.com/index.html

https://www.youtube.com/watch?v=sovN5c7E8MY&t=302s

V2Ray 的核心功能是对流量的加密转发

# 域名解析
需要一个域名，指向 VPS 主机，配置 DNS 解析的 A 记录


<br/><br/><br/>

# 服务器配置 V2Ray
一键脚本
```shell
[root@your-vps ~]# bash <(curl -sL https://raw.githubusercontent.com/daveleung/hijkpw-scripts-mod/main/v2ray_mod1.sh)
```
按照脚本运行时的提示完成配置，加密方式选择 vmess + ws + tls


   
<br/><br/><br/>
# 客户单配置 V2Ray
https://github.com/2dust/v2rayN

下载 v2rayN-Core.zip，然后从剪贴板导入服务器配置 V2Ray 完成之后给出的 URL

<br/><br/><br/>

# 可能遇到的问题
## 服务器和客户端版本某些配置不兼容
https://github.com/v2ray/v2ray-core/issues/2966

比如服务器版本：
```shell
[root@your-vps v2ray]# ./v2ray -version
V2Ray 4.31.0 (V2Fly, a community-driven edition of V2Ray.) Custom (go1.15.2 linux/amd64)
A unified platform for anti-censorship.
[root@your-vps v2ray]# 
```

客户端版本:
```powershell
K:\record\2020\9\20\v2rayN-Core>v2ray -version
V2Ray 4.18.0 (Po) 20190228
A unified platform for anti-censorship.

K:\record\2020\9\20\v2rayN-Core>
```
解决参考

https://github.com/233boy/v2ray/issues/812