package br.org.fgp.core;

import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextConfig {

    private static ApplicationContext context;
    private static final Lock lock = new ReentrantLock();
    public ApplicationContextConfig() {
    }

    public static ApplicationContext getContext() {
        Locale.setDefault(new Locale("pt", "BR"));
        return context;
    }

    public static void setContext(ApplicationContext context) {
        ApplicationContextConfig.context = context;
    }
}
