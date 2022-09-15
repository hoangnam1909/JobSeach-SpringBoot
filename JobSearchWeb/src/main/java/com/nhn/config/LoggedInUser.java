package com.nhn.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.swing.text.html.parser.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal
public @interface LoggedInUser {
}
