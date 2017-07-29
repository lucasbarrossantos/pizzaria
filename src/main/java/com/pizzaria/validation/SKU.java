package com.pizzaria.validation;

/**
 * Created by lucasbarros on 26/07/2017.
 */
import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE}) // Pode usar em elementos, métodos e anotações
@Retention(RetentionPolicy.RUNTIME) // Lida em tempo de execução
@Constraint(validatedBy = {}) // Se tivesse uma classe para implementar a restrinção, então, colocaria aqui
@Pattern(regexp = "([a-zA-Z]{2}\\d{4})?")
public @interface SKU {

    @OverridesAttribute(constraint = Pattern.class, name = "message")
    String message() default "SKU deve ser informado no formato correto (exemplo: XX9999)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
