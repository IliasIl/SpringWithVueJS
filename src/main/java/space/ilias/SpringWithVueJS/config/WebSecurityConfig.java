package space.ilias.SpringWithVueJS.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.repo.UserDetailsRepo;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
        return map ->
        {
            String id = (String) map.get("sub");
            User newUser = userDetailsRepo.findById(id).orElseGet(() -> {
                User userNew = new User();
                userNew.setId(id);
                userNew.setName((String) map.get("name"));
                userNew.setEmail((String) map.get("email"));
                userNew.setGender((String) map.get("gender"));
                userNew.setUserpic((String) map.get("picture"));
                userNew.setLocale((String) map.get("locale"));
                return userNew;
            });
            newUser.setLastVisit(LocalDateTime.now());
            return newUser;
        };
    }


}
