## 接口API
#### 1、环境配置
> - 网关 ：9000
> - sys-manager模块：8001
> - 访问网关负载到项目 测试：localhost:9000/sys/test
#### 2、接口
> - 1.用户登录接口
>
>   地址：localhost:9000/sys/login
>
>   | 字段名   | 类型     | 说明         |
>  | -------- | -------- | ------------ |
>   | user     | 用户对象 | User         |
    | username | String   | 用户名，必填 |
    | password | String   | 密码，必填   |
    
    参数：User对象,username和password不为空的
    
    响应：
    ```json
         {
                      "status": 200,
                      "msg": "success",
                      "data": {
                          "id": 1,
                          "username": "admin",
                          "password": "123",
                          "salt": null,
                          "phone": "13999999912",
                          "o": {
                              "id": 1,
                              "name": null,
                              "parentId": null,
                              "typeId": null,
                              "location": null,
                              "orgaNumber": null,
                              "ext": null
                          },
                          "roles": [
                              {
                                  "id": null,
                                  "name": "superManager",
                                  "ext": "超级管理员",
                                  "perms": [
                                      {
                                          "id": null,
                                          "name": "sys:*:*"
                                      },
                                      {
                                          "id": null,
                                          "name": "work:*:*"
                                      },
                                      {
                                          "id": null,
                                          "name": "model:*:*"
                                      }
                                  ]
                              }
                          ]
                      }
                  }
    }

 