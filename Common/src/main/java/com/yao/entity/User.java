package com.yao.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Entity: User
 * @Author:
 * @Date: 2023/5/10
 * @Time: 15:57
 * @Description：<描述>
 **/
@Data
public class User {
    @ExcelProperty("ID")
    private String id;
    @ExcelProperty("用户名")
    private String username;
    @ExcelProperty("密码")
    private String password;
}
