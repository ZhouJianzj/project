# item模块



# 

#### 1.查询项目

- 1.请求url：/item/item

- 2.请求方式：GET

- 参数：

  | 参数名   | 类型   | 说明                                                   |      |
  | -------- | ------ | ------------------------------------------------------ | ---- |
  | pageNo   | string | 页码 默认1                                             |      |
  | pageSize | string | 页面容量 返回空时默认8                                 |      |
  | key      | string | 查询关键字，会从name，addr，以及所属部门name，location |      |

  

返回示例：

```
{
    "status": 200,
    "msg": "success",
    "data": {
        "total": 5,
        "list": [
            {
                "id": 1,
                "name": "神话一号项目",
                "number": "饿泥豆腐",
                "orgaId": 0,
                "organize": null,
                "addr": "南天门33重天",
                "phone": "16887667811",
                "createTime": "2021-11-02T12:35:25.000+0000"
            },
            {
                "id": 2,
                "name": "神话二号项目",
                "number": "99猴头",
                "orgaId": 0,
                "organize": null,
                "addr": "南海观音莲花池",
                "phone": "16112312311",
                "createTime": "2021-11-02T12:35:25.000+0000"
            },
            {
                "id": 3,
                "name": "嫦娥一号",
                "number": null,
                "orgaId": 0,
                "organize": null,
                "addr": "月亮",
                "phone": "11223432545",
                "createTime": "2021-11-04T02:43:18.000+0000"
            },
            {
                "id": 11,
                "name": "后裔",
                "number": "aa",
                "orgaId": 0,
                "organize": null,
                "addr": "南京路101号",
                "phone": "12345678910",
                "createTime": "2021-11-04T09:12:56.000+0000"
            },
            {
                "id": 12,
                "name": "后裔",
                "number": "aa",
                "orgaId": 0,
                "organize": null,
                "addr": "南京路11号",
                "phone": "12345678910",
                "createTime": "2021-11-04T09:23:04.000+0000"
            }
        ],
        "pageNum": 1,
        "pageSize": 9,
        "size": 5,
        "startRow": 1,
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

#### 2.根据id删除项目

- 请求地址：/item/item

- 请求方式：delete

- 请求参数：id

- 返回示例：

  ```
  {
      "status": 400,
      "msg": "删除失败",
      "data": null
  }
  ```

  

#### 3. 添加项目

- 请求地址：/item/item

- 请求方式：post

- 参数：item

- 参数示例：

  ```
  {
      "name":"后裔",
      "number":"aa",
      "orgaId":"1",
      "addr":"南京路11号",
      "phone":"12345678910"
  }
  ```

  

- 返回示例：

  ```
  {
      "status": 200,
      "msg": "新增成功",
      "data": null
  }
  ```

  

#### 4.修改项目

- 地址：/item/item

- 请求：put

- 参数：item

- 参数示例：

  ```
  {
      "id":11,
      "name":"后裔",
      "number":"aa",
      "orgaId":"2",
      "addr":"南京路11号",
      "phone":"12345678910"
  }
  ```

- 返回示例：

  ```
  {
      "status": 200,
      "msg": "修改成功",
      "data": null
  }
  ```

  
