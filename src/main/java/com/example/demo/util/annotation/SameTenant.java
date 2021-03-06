package com.example.demo.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.lang3.StringUtils;

/**
 * uid是否属于当前上下文租户
 * 注：当被校验对象为空时，校验为通过，所以如果需要校验对象必填，请自行结合相关校验注解
 * Created by Zhang JinLong(150429) on 2016-09-18.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SameTenant.StringChecker.class, SameTenant.LongChecker.class})
@Documented
public @interface SameTenant {
 
    String message() default "用户不存在或者不属于当前组织";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
    
    //也可以写成外部类
    class StringChecker implements ConstraintValidator<SameTenant, String> {
 
        @Override
        public void initialize(SameTenant arg0) {
        }
 
        @Override
        public boolean isValid(String uid, ConstraintValidatorContext context) {
 
            System.out.print("到注解里面了"+uid);
            if (StringUtils.isBlank(uid)) {
 
                return true;
            }
            return false;
        }
    }
 
    class LongChecker implements ConstraintValidator<SameTenant, Long> {
 
        @Override
        public void initialize(SameTenant arg0) {
        }
 
        @Override
        public boolean isValid(Long uid, ConstraintValidatorContext context) {
            if (null == uid) {
                return true;
            }
            return false;
        }
    }
}