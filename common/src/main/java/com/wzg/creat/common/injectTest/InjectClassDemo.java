package com.wzg.creat.common.injectTest;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class InjectClassDemo {

    private String id;

    private String name;

    private List<Integer> ages = new ArrayList<>();
}
