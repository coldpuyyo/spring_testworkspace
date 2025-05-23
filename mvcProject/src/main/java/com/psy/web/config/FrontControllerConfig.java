//package com.psy.web.config;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring6.view.ThymeleafViewResolver;
//
//@Configuration
//@EnableWebMvc
//@ComponentScan("com.psy.web.controller")
//public class FrontControllerConfig implements WebMvcConfigurer {
//   private final ApplicationContext applicationContext;
//
//   public FrontControllerConfig(ApplicationContext applicationContext) {
//      this.applicationContext = applicationContext;
//   }
//
//   @Bean
//   public ThymeleafViewResolver viewResolver() {
//
//      /*
//       * 익명객체와 초기화 블럭을 이용한 코드 단축.
//       * 익명객체 내 초기화 블럭을 지정함으로써, 상속받은 익명 객체의 초기화 블럭내에서
//       * 상속 메서드들을 바로 호출 가능한점을 활용.
//       */
//      return new ThymeleafViewResolver() {{
//         setTemplateEngine(new SpringTemplateEngine() {{
//            setTemplateResolver(new SpringResourceTemplateResolver() {{
//               setApplicationContext(applicationContext);
//               setPrefix("/WEB-INF/view/");
//               setSuffix(".html");
//               setTemplateMode("HTML");
//               setCharacterEncoding("UTF-8");
//            }});
//         }});
//         setCharacterEncoding("UTF-8");
//      }};
//   }
//
//   @Override
//   public void addResourceHandlers(ResourceHandlerRegistry registry) {
//      registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//   }
//}