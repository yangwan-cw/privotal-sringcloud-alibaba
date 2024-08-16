package com.ioomex.common.app.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

/**
 * 脱敏器,controller 的返回值，会被 springboot 统一处理
 * 内部会使用序列化 json 字符串，然后由 springboot 输出到客户端
 * 也就是我们这里只需要做到判断属性值是否存在是的自定义注解，如果存在注解的话
 * 将注解的值交给 serialize 方法然后通过 apply 进行转化
 */
public class DesensitizationJsonSerializable extends JsonSerializer<String> implements ContextualSerializer {

    //脱敏策略
    private DesensitizationStrategy desensitizationStrategy;

    @Override
    public void serialize(String s, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // 使用脱敏策略将字符串处理后序列化到json中
        gen.writeString(desensitizationStrategy.getDesensitization().apply(s));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        // 获取属性上的 Desensitization 注解
        Desensitization annotation = property.getAnnotation(Desensitization.class);
        // 注解不为空 && 属性类型必须是字符串类型
        if (Objects.nonNull(annotation) && Objects.equals(String.class, property.getType().getRawClass())) {
            //设置脱敏策略
            this.desensitizationStrategy = annotation.value();
            return this;
        }
        // 返回默认的序列化器
        return prov.findValueSerializer(property.getType(), property);
    }
}