# jwt demo
include create & verify jwt
## 流程
1. 启动项目，访问http://localhost:8080/user/login?username=hhd&password=hhd获取token
2. 访问http://localhost:8080/user/test 并在请求头中加Authorization=1中返回的token