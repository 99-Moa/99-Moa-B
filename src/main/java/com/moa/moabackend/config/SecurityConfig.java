package com.moa.moabackend.config;

import com.moa.moabackend.jwt.JwtAuthFilter;
import com.moa.moabackend.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer(){
        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {

        http.csrf().disable();

        // 세션 사용 안함
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                // UserController
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/signin").permitAll()
                .antMatchers(HttpMethod.GET, "/issue/token").permitAll()
                .antMatchers(HttpMethod.GET, "/users/{userName}").permitAll()

                // AuthController
                .antMatchers(HttpMethod.POST, "/userIdCheck").permitAll()
                .antMatchers(HttpMethod.POST, "/userNameCheck").permitAll()

                // MypageController
                .antMatchers(HttpMethod.PUT, "/mypage").authenticated()
                .antMatchers(HttpMethod.GET, "/mypage").authenticated()

                // FriendController
                .antMatchers(HttpMethod.POST, "/friends").authenticated()
                .antMatchers(HttpMethod.GET, "/friends").authenticated()
                .antMatchers(HttpMethod.DELETE, "/friends/{friendId}").authenticated()

                // ScheduleController
                .antMatchers(HttpMethod.GET, "/calendar").authenticated()
                .antMatchers(HttpMethod.POST, "/schedules").authenticated()
                .antMatchers(HttpMethod.GET, "/schedules").authenticated()
                .antMatchers(HttpMethod.GET, "/schedules/{scheduleId}").authenticated()
                .antMatchers(HttpMethod.PUT, "/schedules/{scheduleId}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/schedules/{scheduleId}").authenticated()

                // 파일 업로드 권한 설정?
//                .anyRequest().permitAll()
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}