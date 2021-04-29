package com.hqjang.springboot.config.auth;

import com.hqjang.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //CustomOAuth2UserService는 구글 로그인 이후 가져온 사용자의 정보들을 기반으로 가입,정보수정, 세션저장 등의 기능을 지원.
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                //h2-console 사용을 위해
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    //antMatchers는 권한관리 대상을 지정하는 옵션임
                    //해당 URL들은 전체열람권한
                    .antMatchers("/","/css/**","/images/**",
                        "/js/**","/h2-console/**").permitAll()
                    //해당 URL은 USER권한을 가진 사람만 가능하도록
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        //로그인성공 이후 사용자정보 가져올 때의 설정들 담당
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);

    }
}
