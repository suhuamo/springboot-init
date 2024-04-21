package com.suhuamo.init.util;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author suhuamo
 * @date 2024-04-21
 * @slogan 五秒钟内说出三个你爱的人的名字...5..4..3..2..1..你是不是没有说自己，也要爱自己哦。
 * @description cmd运行结果实体类
 */
@Data
@Accessors(chain = true)
public class ProcessResultDTO {
    /**
     * 状态码，0为异常，非0为正常
     * @version
     * @author suhuamo
     * @with {@link }
     */
    int exitCode;
    /**
     * 命令输出结果
     * @version
     * @author suhuamo
     * @with {@link }
     */
    String result;
}
