## 接口API

#### 一、环境配置

> - 网关 ：9000
> - sys-manager模块：8001
> - 访问网关负载到项目 测试：localhost:9000/sys/test
> - 错误类型：
    > - 200：响应正常
    > - 401：无权限
    > - 400：失败操作
    > - 402:session失效 

#### 二、接口

### 当分页是没传分页数码(pageNo)以及页面容量(pageSize)时，后端默认会将pageNo设为1，pageSize设为8

##### 1.用户登录接口

​ 地址：localhost:9000/sys/login

| 参数名   | 类型     | 说明         |
| -------- | -------- | ------------ |
| user     | 用户对象 | User         |
| username | String   | 用户名，必填 |
| password | String   | 密码，必填   |

参数：User对象,username和password不为空的

响应：

``` json

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
```

##### 2.机构查询接口

地址：localhost:9000/sys/orga?orgName=南京&pageNo=1&pageSize=1

请求：GET

| 参数名   | 类型   | 说明           |
| -------- | ------ | -------------- |
| orgaName | String | 查询关键字     |
| pageNo   | String | 分页的页码     |
| pageSize | String | 分页的页面大小 |

返回示例：

```json
{
  "status": 200,
  "msg": "success",
  "data": {
    "total": 1,
    "list": [
      {
        "id": 1,
        "name": "南京煤业有限公司",
        "parentId": null,
        "parentName": null,
        "typeId": null,
        "location": "南京雨花",
        "orgaNumber": "13339",
        "ext": null,
        "orgaType": {
          "id": null,
          "name": "矿业"
        }
      }
    ],
    "pageNum": 1,
    "pageSize": 1,
    "size": 1,
    "startRow": 0,
    "endRow": 0,
    "pages": 1,
    "prePage": 0,
    "nextPage": 0,
    "isFirstPage": true,
    "isLastPage": true,
    "hasPreviousPage": false,
    "hasNextPage": false,
    "navigatePages": 8,
    "navigatepageNums": [
      1
    ],
    "navigateFirstPage": 1,
    "navigateLastPage": 1
  }
}
```

##### 3.添加机构

地址：localhost:9000/sys/orga

请求：POST

参数实例：

```
{
"name":"江东集团", 
"parentOrga":{"id":"1"},
"orgaType":{"id":"2"},
"location":"江东南昌",
"orga_number":"test",
"ext":"bbbb"
}
```

返回示例：

```
{
    "status": 200,
    "msg": "添加成功",
    "data": null
}
```

##### 4.查询角色

地址：localhost:9000/sys/role?roleName=M&pageNo=1&pageSize=1

请求：GET

| 参数名   | 类型   | 说明           |
| -------- | ------ | -------------- |
| roleName | String | 查询关键字     |
| pageNo   | String | 页码           |
| pageSize | String | 分页的页面容量 |

返回示例：

```
{
    "status": 200,
    "msg": "success",
    "data": {
        "total": 2,
        "list": [
            {
                "id": 1,
                "name": "superManager",
                "ext": "超级管理员",
                "perms": null
            }
        ],
        "pageNum": 1,
        "pageSize": 1,
        "size": 1,
        "startRow": 1,
        "endRow": 1,
        "pages": 2,
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 2
    }
}
```

##### 5.添加角色

地址：localhost:9000/sys/role

请求：POST

参数示例：

```
{
    "name":"productManager",
    "ext":"销售业务员，负责销售执行"
}
```

返回示例

```
{
    "status": 200,
    "msg": "添加成功",
    "data": null
}
```

##### 6.删除角色

地址：localhost:9000/sys/role/{id}

请求：DELETE

| 参数名 | 类型 | 说明 |
| ------ | ---- | ---- |
| id     | int  |      |

返回示例：

```
{
    "status": 200,
    "msg": "删除成功",
    "data": null
}
```

##### 7.查询所有权限

地址：localhost:8001/sys/perm?pageNo=1&pageSize=1

请求：GET

| 参数名   | 类型   | 说明     |
| -------- | ------ | -------- |
| pageNo   | string | 页码     |
| pageSize | string | 页面容量 |

返回示例：

```
{
    "status": 200,
    "msg": "success",
    "data": {
        "total": 6,
        "list": [
            {
                "id": 1,
                "name": "sys:*:*"
            }
        ],
        "pageNum": 1,
        "pageSize": 1,
        "size": 6,
        "startRow": 0,
        "endRow": 5,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
```

##### 8.给角色添加权限

地址：localhost:9000/sys/role/perm/{角色id}/{权限id}

请求：POST

| 参数名  | 必选 | 类型    | 说明                  |
| ------- | ---- | ------- | --------------------- |
| role_id | 是   | Integer | 参数1,直接拼接在url上 |
| perm_id | 是   | Integer | 参数2,直接拼接在url上 |

返回示例：

```
{
    "status": 200,
    "msg": "给角色添加权限成功",
    "data": null
}
```

##### 9.查询用户

地址：localhost:8001/sys/user/key?key=138&pageNo=1&pageSize=2

*KEY为空时查询所有用户*

请求：GET

| 参数名   | 类型   | 说明       |
| -------- | ------ | ---------- |
| key      | string | 查询关键字 |
| pageNo   | int    | 页码       |
| pageSize | int    | 页面容量   |

返回示例：

```
{
    "status": 200,
    "msg": "success",
    "data": {
        "total": 2,
        "list": [
            {
                "id": 2,
                "username": "a2",
                "password": "JQz4tRx3Pz+NyLS+hnqaAg==",
                "salt": null,
                "phone": "13899999912",
                "o": {
                    "id": 2,
                    "name": "北京地铁公司",
                    "parentId": null,
                    "parentName": null,
                    "typeId": null,
                    "location": null,
                    "orgaNumber": null,
                    "ext": null,
                    "orgaType": null
                },
                "roles": [
                    {
                        "id": null,
                        "name": "userManager",
                        "ext": "普通管理员",
                        "perms": [
                            {
                                "id": null,
                                "name": "work:*:*"
                            }
                        ]
                    },
                    {
                        "id": null,
                        "name": "worker",
                        "ext": "安装工人",
                        "perms": [
                            {
                                "id": null,
                                "name": "model:*:*"
                            }
                        ]
                    }
                ]
            },
            {
                "id": 3,
                "username": "a3",
                "password": "aAU68pI+ACBMPKfGoxUM9w==",
                "salt": null,
                "phone": "13899999912",
                "o": {
                    "id": 3,
                    "name": "北京地铁公司",
                    "parentId": null,
                    "parentName": null,
                    "typeId": null,
                    "location": null,
                    "orgaNumber": null,
                    "ext": null,
                    "orgaType": null
                },
                "roles": []
            }
        ],
        "pageNum": 1,
        "pageSize": 2,
        "size": 2,
        "startRow": 0,
        "endRow": 1,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
```

##### 10.添加用户

地址：localhost:9000/sys/user

请求：POST

参数示例：

```
{
    "username": "zj",
    "password": "123",
    "salt": "",
    "phone": "18851020101"
}
```

返回示例：

```
{
    "status": 200,
    "msg": "添加成功",
    "data": null
}
```

##### 11.删除用户

地址：localhost:9000/sys/user?id=5

请求：DELETE

| 参数名 | 类型 | 说明 |
| ------ | ---- | ---- |
| id     | int  |      |

返回示例：

```
{
    "status": 200,
    "msg": "删除成功",
    "data": null
}
```

##### 12.查询单个用户

地址：localhost:9000/sys/user/id?id=1

请求：GET

| 参数名  | 必选 | 类型 | 说明   |
| ------- | ---- | ---- | ------ |
| user_id | 是   | int  | 用户id |

返回示例：

```
{
    "status": 200,
    "msg": "success",
    "data": {
        "id": 1,
        "username": "a1",
        "password": "ICy5YqxZB1uWSwcVLSNLcA==",
        "salt": null,
        "phone": "13999999912",
        "o": {
            "id": 1,
            "name": "南京煤业有限公司",
            "parentId": null,
            "parentName": null,
            "typeId": null,
            "location": null,
            "orgaNumber": null,
            "ext": null,
            "orgaType": null
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
                    }
                ]
            }
        ]
    }
}
```

##### 13.用户登录

地址：localhost:9000/sys/login

请求：POST

| 参数名      | 必选 | 类型        | 说明         |
| ----------- | ---- | ----------- | ------------ |
| userManager | 是   | UserManager | 表单提交数据 |

请求示例：

```
{
    "username":"a1",
    "password":"123"
}
```

返回示例：

```
{
    "status": 200,
    "msg": "success",
    "data": {
        "id": 1,
        "username": "a1",
        "password": "ICy5YqxZB1uWSwcVLSNLcA==",
        "salt": null,
        "phone": "13999999912",
        "o": {
            "id": 1,
            "name": "南京煤业有限公司",
            "parentId": null,
            "parentName": null,
            "typeId": null,
            "location": null,
            "orgaNumber": null,
            "ext": null,
            "orgaType": null
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
```

##### 14.查询日志

地址：localhost:9000/sys/log

请求：GET

| 参数名   | 必选 | 类型   | 说明         |
| -------- | ---- | ------ | ------------ |
| pageNo   | 是   | string | 分页的页码   |
| pageSize | 是   | String | 分页页面容量 |

返回示例：

```
{
    "status": 200,
    "msg": "success",
    "data": {
        "total": 3,
        "list": [
            {
                "id": 1,
                "username": "a1",
                "operType": "add",
                "moduleName": "orga",
                "result": true,
                "operTime": "2021-09-24T07:53:15.000+0000",
                "operContent": "添加组织"
            },
            {
                "id": 2,
                "username": "a1",
                "operType": "add",
                "moduleName": "role",
                "result": true,
                "operTime": "2021-09-24T08:06:23.000+0000",
                "operContent": "添加角色"
            }
        ],
        "pageNum": 1,
        "pageSize": 2,
        "size": 2,
        "startRow": 1,
        "endRow": 2,
        "pages": 2,
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 2,
        "lastPage": 2,
        "firstPage": 1
    }
}
```

##### 15.用户退出登录

地址：localhost:9000/sys/log

请求：GET

##### 16.修改用户

地址：localhost:9000/sys/user/modify

请求：PUT

请求参数示例：

```
{
	"id":1,
	"username":a22,
	"phone":12312312311
}
```

返回参数示例：

```
{
    "status": 200,
    "msg": "修改成功",
    "data": null
}
```

##### 17.根据id查询用户

地址：localhost:9000/sys/user/id

请求：GET

请求参数：id

返回参数示例：

```
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
```

##### 根据用户名查询用户是否存在
      
  - 地址：sys/user/name
      
  - 请求：GET
      
  - 请求参数：key
      
  - 返回参数类型：Boolean
      
 ##### 19.修改密码
      
  - sys/user/password
      
  - 请求：GET
      
  - 请求参数：id,password
      
  - 返回数据示例：
```
{
    "status": 200,
    "msg": "修改密码成功",
    "data": null
}
```