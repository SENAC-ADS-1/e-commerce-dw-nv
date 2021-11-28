package br.senac.devweb.api.ecommerce.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final String[] AUTH_WHITELIST = {
            "/administrador/*",
            "/categoria/*",
            "/cliente/*",
            "/endereco/*",
            "/contato/*",
            "/produto/*",
            "/venda/*",
            "/h2-console",
            "/h2-console/*"
    };

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }
}

