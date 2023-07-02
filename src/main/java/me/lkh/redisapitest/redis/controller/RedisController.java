package me.lkh.redisapitest.redis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lkh")
public class RedisController {

    @RequestMapping("/test")
    public String helloWorld(){
        return "Hello World";
    }

}
