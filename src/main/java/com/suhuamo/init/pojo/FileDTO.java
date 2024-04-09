package com.suhuamo.init.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yuanchuncheng
 * @date 2024-04-09
 * @description
 */
@Data
@Accessors(chain = true)
public class FileDTO {
    /**
     * 文件
     * @version
     * @author suhuamo
     * @with {@link }
     */
    private MultipartFile multipartFile;
    /**
     * 文件名称--如果没传，则用上传文件的名称
     * @version
     * @author yuanchuncheng
     * @with {@link }
     */
    private String name;
}
