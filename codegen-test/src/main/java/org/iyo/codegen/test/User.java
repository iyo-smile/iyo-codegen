package org.iyo.codegen.test;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.iyo.codegen.processor.dto.GenDto;
import org.iyo.codegen.processor.dto.IgnoreDto;

import java.io.Serializable;

/**
 * @Author:iyo
 * @Date:2024/4/10 8:42
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@GenDto(pkgName = "org.iyo.codegen.dto")
public class User implements Serializable {
    public static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String userName;

    private Integer age;

    @IgnoreDto
    private String nickName;


}
