package com.wzg.creat.user.service.Bean;

import com.wzg.creat.common.page.Page;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserBean {
    private Short id;

    private String username;

    private String nickname;

    private String password;

    private String gender;

    private String email;

    private String description;

    private Page page;

    private List<String> testList = new ArrayList<>();
}
