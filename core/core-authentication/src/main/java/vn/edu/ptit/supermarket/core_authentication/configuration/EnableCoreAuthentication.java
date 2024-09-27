package vn.edu.ptit.supermarket.core_authentication.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import vn.edu.ptit.supermarket.core_authentication.controller.AuthAccountController;

@Import({
  CoreAuthenticationConfiguration.class,
  WebSecurityConfiguration.class,
  AuthAccountController.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface EnableCoreAuthentication {

}
