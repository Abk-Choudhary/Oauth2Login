package com.abk.OAuth2Login.configuration;

import com.abk.OAuth2Login.model.User;
import com.abk.OAuth2Login.repositories.UserRepositories;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    UserRepositories userRepositories;

    @Autowired
    public SecurityConfiguration(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request->
                    request.requestMatchers("/private/**").authenticated().
                         anyRequest().permitAll());
        //httpSecurity.formLogin(Customizer.withDefaults());

        httpSecurity.oauth2Login(oauth2-> {
            oauth2.loginPage("http://localhost:5173");


//            oauth2.successHandler(new AuthenticationSuccessHandler() {
//                @Override
//                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                    System.out.println("----Inside handeler");
//                }
//            });

                    oauth2.successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
                            //System.out.println(defaultOAuth2User.getName());
                            System.out.println(defaultOAuth2User.getAttributes());
                            //System.out.println(defaultOAuth2User.getAuthorities());
                            String name = defaultOAuth2User.getAttribute("name");
                            String email = defaultOAuth2User.getAttribute("email");
                            //System.out.println(email+"   "+name);
                            User user = new User();
                            user.setName(name);
                            user.setUserEmail(email);
                            user.setProfilePicture(defaultOAuth2User.getAttribute("avatar_url"));

                            //System.out.println(user.getProfilePicture());

                            User user1 = userRepositories.findByUserEmail(email);
                            //System.out.println(user1);
                            if (user1==null)
                            {
                                userRepositories.save(user);
                            }

                            response.sendRedirect("http://localhost:5173");
                        }
                    });
            //oauth2.defaultSuccessUrl("/private/user/profile2",true);
        });
        return httpSecurity.build();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Arrays.asList("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
