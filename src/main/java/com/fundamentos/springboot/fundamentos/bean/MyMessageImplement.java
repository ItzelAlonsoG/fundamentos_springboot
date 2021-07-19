package com.fundamentos.springboot.fundamentos.bean;

import org.springframework.stereotype.Component;

@Component
public class MyMessageImplement implements MyMessage{

    @Override
    public void fax() {

        System.out.println("Este es el mensaje del fax");
    }
}
