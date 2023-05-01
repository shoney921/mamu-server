package com.example.mamuserver.controller.user;

import com.example.mamuserver.controller.user.dto.UserCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    @PostMapping("/user")
    public int saveUser(@RequestBody UserCreateReq req) {
        String sql = "insert into user (name, age) values (?, ?)";
        int update = jdbcTemplate.update(sql, req.getName(), req.getAge());
        return update;
    }
}
