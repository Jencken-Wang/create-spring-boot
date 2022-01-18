package com.wzg.util;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerInjectUtil {

    @InitBinder
    public void initBinder(WebDataBinder binder) {}


    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("test", "test1232131");
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        System.out.println("advice" + ex.getMessage());
        return map;
    }


}
