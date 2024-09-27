package vn.edu.ptit.supermarket.core_email.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Import({
    CoreEmailConfiguration.class,
    AsyncConfiguration.class,
    ThymleafTemplateConfiguration.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableCoreEmail {

}
