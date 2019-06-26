package ai.metaheuristic.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Serge
 * Date: 6/23/2019
 * Time: 10:13 PM
 */
@SpringBootApplication
@Controller
public class JelasticSimpleApp {

    @Configuration
    public static class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

        private final Globals globals;

        public SpringSecurityConfig(Globals globals) {
            this.globals = globals;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .authorizeRequests()
                    .antMatchers("/**/**").permitAll()
            ;
            if (globals.isSslRequired) {
                http.requiresChannel().antMatchers("/**").requiresSecure();
            }

        }
    }
    @RequestMapping("/")
    public @ResponseBody String index1() {
        return "Hello, world!";
    }

    public static void main(String[] args) {
        SpringApplication.run(JelasticSimpleApp.class, args);
    }

}