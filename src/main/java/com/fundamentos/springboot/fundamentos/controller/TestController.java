package com.fundamentos.springboot.fundamentos.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> function (){
        return new ResponseEntity<>("Hello from Controller un cambio", HttpStatus.OK);
    }

}
