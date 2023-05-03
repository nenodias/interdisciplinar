package br.org.fgp;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.view.Login;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class App {

    private static Future<?> threadLogin;
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-config.xml");
        ApplicationContextConfig.setContext(context);
        Supplier<Future<?>> supplier = () -> threadLogin;
        Login login = new Login(supplier);
        Runnable run = new Runnable() {
            public void run() {
                try {
                    login.setVisible(true);
                    login.setAlwaysOnTop(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ExecutorService executadorServico = Executors.newSingleThreadExecutor();
        threadLogin = executadorServico.submit(run);
    }

}
