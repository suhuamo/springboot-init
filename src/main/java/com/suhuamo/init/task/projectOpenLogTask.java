package com.suhuamo.init.task;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.util.Objects;

/**
 * @author suhuamo
 * @date 2023-12-31
 * @slogan 也许散落在浩瀚宇宙的小行星们也知道
 * 项目启动时执行任务,输出项目的基本信息
 */
@Slf4j
@Configuration
public class projectOpenLogTask implements EnvironmentAware {

    @SneakyThrows
    @Override
    public void setEnvironment(@NotNull Environment environment) {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        if(Objects.isNull(port)) {
            port = "8080";
        }
        log.info("");
        log.info("\n################## 项目启动成功 ####################"+
                "\n----------------------------------------------------------\n\t" +
                "本次启动的项目的访问地址:\n\t" +
                "本机: \t\t http://localhost:" + port + "/\n\t" +
                "外部访问: \t http://" + ip + ":" + port + "/\n\t" +
                "Knife4j增强文档: \t http://localhost:" + port + "/doc.html\n" +
                "----------------------------------------------------------");
    }
}
