package com.java.desktopApp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class ContextManager {
    private static ContextManager contexto;

    private ConfigurableApplicationContext context;

    public static ContextManager getContexto(){
        if(contexto==null){
            contexto=new ContextManager();
        }
        return  contexto;
    }

    public void setContext(ConfigurableApplicationContext context_){
        context=context_;
    }


    public ConfigurableApplicationContext getContext(){
        return context;
    }
}
