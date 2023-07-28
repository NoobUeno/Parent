package com.yao.common.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Entity: User
 * @Author:
 * @Date: 2023/5/10
 * @Time: 15:57
 * @Description：<描述>
 **/
@Data
@NoArgsConstructor
public class User {
    @ExcelProperty("ID")
    private String id;
    @ExcelProperty("用户名")
    private String username;
    @ExcelProperty("密码")
    private String password;


    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
