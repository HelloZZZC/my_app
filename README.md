
<h1 align="center"></h1><div align="center">

![Static Badge](https://img.shields.io/badge/Language-java-blue)
![Static Badge](https://img.shields.io/badge/Framework-SpringBoot-yellow)
![Static Badge](https://img.shields.io/badge/Framework-Shiro-Green)
![Static Badge](https://img.shields.io/badge/Framework-Jwt-purple)


## RBAC脚手架

目的是用来接一些Admin的外快，自己写的脚手架。🔥

---
</div>

### There

1. [文件结构](#文件结构)
2. [表结构](#表结构设计)
3. [TODO](#TODO) 

### 文件结构
```
├── db
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── heartbeat
│   │   │           └── myapp
│   │   │               ├── biz
│   │   │               │   └── impl
│   │   │               ├── components
│   │   │               │   └── shiro
│   │   │               ├── config
│   │   │               ├── constant
│   │   │               ├── dao
│   │   │               │   ├── dataobject
│   │   │               │   └── mapper
│   │   │               ├── domain
│   │   │               │   └── model
│   │   │               ├── dp
│   │   │               │   └── identifier
│   │   │               ├── dto
│   │   │               ├── enums
│   │   │               ├── exception
│   │   │               │   └── errorcode
│   │   │               ├── handler
│   │   │               ├── repository
│   │   │               │   ├── converter
│   │   │               │   └── impl
│   │   │               ├── util
│   │   │               └── web
│   │   │                   ├── controller
│   │   │                   ├── model
│   │   │                   └── param
│   │   └── resources
│   │       └── mapper
│   └── test
│       └── java
│           └── com
│               └── heartbeat
│                   └── myapp
└── target
    ├── classes
    │   └── com
    │       └── heartbeat
    │           └── myapp
    │               ├── biz
    │               │   └── impl
    │               ├── components
    │               │   └── shiro
    │               ├── config
    │               ├── constant
    │               ├── dao
    │               │   ├── dataobject
    │               │   └── mapper
    │               ├── domain
    │               │   └── model
    │               ├── dp
    │               │   └── identifier
    │               ├── dto
    │               ├── enums
    │               ├── repository
    │               │   ├── converter
    │               │   └── impl
    │               ├── util
    │               └── web
    │                   ├── controller
    │                   ├── model
    │                   └── param
    ├── generated-sources
    │   └── annotations
    ├── generated-test-sources
    │   └── test-annotations
    └── test-classes
        └── com
            └── example
                └── myapp
```

### 表结构
#### 系统账号Table

| 字段 | 类型 | Comment |
| ---- | ---- | ---- |
| id | bigint | 系统账号ID |
| username | varchar | 用户名 |
| password | varchar | 密码 |
| salt | varchar | 盐值 |
| staff_id | bigint | 职工ID |
| create_time | datetime | 创建时间 |
| update_time | datetime | 更新时间 |
| is_deleted | tinyint | 是否删除 0:否 1:是 |

#### 系统职工Table

| 字段 | 类型 | Comment |
| ---- | ---- | ---- |
| id | bigint | 系统职工ID |
| nickname | varchar | 职工昵称 |
| real_name | varchar | 职工真名 |
| email | varchar | 职工邮箱 |
| avatar | varchar | 职工头像 |
| gender | tinyint | 性别 0:男性 1:女性 2:未知 |
| status | tinyint | 在职状态 0:离职 1:在职 |
| roleId | bigint | 系统角色ID |
| departmentId | bigint | 系统部门ID |
| creator_id | bigint | 创建人ID |
| create_time | datetime | 创建时间 |
| operator_id | bigint | 最后操作人ID |
| update_time | datetime | 更新时间 |
| is_deleted | tinyint | 是否删除 0:否 1:是 |

#### 系统部门Table

| 字段 | 类型 | Comment |
| ---- | ---- | ---- |
| id | bigint | 系统部门ID |
| title | varchar | 部门标题 |
| parent_id | bigint | 上级部门ID |
| path | varchar | 上级部门路径 |
| level | int | 层级 |
| sort | int | 排序 |
| about | varchar | 关于 |
| creator_id | bigint | 创建人ID |
| create_time | datetime | 创建时间 |
| operator_id | bigint | 最后操作人ID |
| update_time | datetime | 更新时间 |
| is_deleted | tinyint | 是否删除 0:否 1:是 |

#### 系统权限Table

| 字段 | 类型 | Comment |
| ---- | ---- | ---- |
| id | bigint | 系统权限ID |
| title | varchar | 权限标题 |
| code | varchar | 编码 |
| about | varchar | 关于 |
| creator_id | bigint | 创建人ID |
| create_time | datetime | 创建时间 |
| operator_id | bigint | 最后操作人ID |
| update_time | datetime | 更新时间 |
| is_deleted | tinyint | 是否删除 0:否 1:是 |

#### 系统角色权限关联Table

| 字段 | 类型 | Comment |
| ---- | ---- | ---- |
| id | bigint | 系统角色权限ID |
| role_id | bigint | 角色ID |
| permission_id | bigint | 权限ID |
| create_time | datetime | 创建时间 |
| update_time | datetime | 更新时间 |
| is_deleted | tinyint | 是否删除 0:否 1:是 |

#### 系统角色Table


| 字段 | 类型 | Comment |
| ---- | ---- | ---- |
| id | bigint | 系统角色ID |
| title | varchar | 角色标题 |
| code | varchar | 编码 |
| about | varchar | 关于 |
| creator_id | bigint | 创建人ID |
| create_time | datetime | 创建时间 |
| operator_id | bigint | 最后操作人ID |
| update_time | datetime | 更新时间 |
| is_deleted | tinyint | 是否删除 0:否 1:是 |

### TODO

1. [ ] 接口补充完整
2. [x] 代码优化
3. [ ] 单元测试 
