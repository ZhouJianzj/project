## 接口API
#### 1、环境配置
> - 网关 ：9000
> - 项目：8001
> - 访问网关负载到项目 测试：localhost:9000/sys/test
#### 2、接口
> - 1.用户登录接口
>
>   地址：localhost:9000/sys/login
>
>   参数：User对象,username和password不为空的
>
>   响应：
    ```{
                  "status": 200,
                  "msg": "success",
                  "data": {
                      "id": "e9ca23d68d884d4ebb19d07889727dae",
                      "username": "admin",
                      "realName": "管理员",
                      "password": "ICy5YqxZB1uWSwcVLSNLcA==",
                      "salt": "RCGTeGiH",
                      "avatar": "http://minio.jeecg.com/otatest/temp/lgo33_1583397323099.png",
                      "birthday": "2018-12-04T16:00:00.000+0000",
                      "sex": true,
                      "email": "jeecg@163.com",
                      "phone": "18611111111",
                      "orgCode": "A01",
                      "workNo": "00001",
                      "post": "总经理",
                      "token": null
                  }
    }```

 