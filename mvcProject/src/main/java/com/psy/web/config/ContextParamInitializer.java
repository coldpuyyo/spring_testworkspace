package com.psy.web.config;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

//import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.psy.web.context.ServletContextHolder;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextParamInitializer implements ServletContextListener {
   @Override
   public void contextInitialized(ServletContextEvent sce) {
      ServletContext context = sce.getServletContext();
      
      ServletContextHolder.setServletContext(context);
      
      /*
       * Elastic Beanstalk 에 설정한 환경 변수에서 DB 연결 정보 수신.
       * 
       * ===================================================================================
       * 
       *       < getenv() >
       * 
       * -  운영체제나 컨테이너, 혹은 AWS Elastic Beanstalk 과 같은 환경 설정에서 지정한 운영체제 수준에서
       *    설정된 환경 변수(Environment Variable)를 가져오는 메서드.
       */
//       String dbHost = System.getenv("DB_HOST");
//       String dbPort = System.getenv("DB_PORT");
//       String dbName = System.getenv("DB_NAME");
//       String dbUser = System.getenv("DB_USER");
//       String dbPass = System.getenv("DB_PASSWORD");
//      
//      context.setInitParameter("maria_url", String.format("jdbc:mariadb://%s:%s/%s", dbHost, dbPort, dbName));
//      context.setInitParameter("maria_userName", dbUser);
//      context.setInitParameter("maria_psw", dbPass);
      
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

      context.setInitParameter("mysql_driver", "com.mysql.cj.jdbc.Driver");
      context.setInitParameter("maria_driver", "org.mariadb.jdbc.Driver");
      context.setInitParameter("mysql_url", "jdbc:mysql://localhost:3306/test_db");
//      context.setInitParameter("maria_url", "jdbc:mariadb://localhost:3307/maria_db");
//      context.setInitParameter("maria_url", "jdbc:mariadb://database-1.cdgccwwi0gu7.ap-northeast-2.rds.amazonaws.com:3307/maria_db");
      context.setInitParameter("mysql_userName", "kkm");
//      context.setInitParameter("maria_userName", "kkm");
//      context.setInitParameter("maria_userName", "admin");
      context.setInitParameter("mysql_psw", "qntleh378@");
//      context.setInitParameter("maria_psw", "qntleh378@");
//      context.setInitParameter("maria_psw", "qntleh378");
      
      context.setInitParameter("react_env", "production");
//      context.setInitParameter("react_env", "development");
      
      // 공지사항 페이지에서 기본적으로 페이징할 레코드 갯수(10)를 초기 파라미터값으로 설정.
      context.setInitParameter("pagingSizeValue", "10");
      
      // 공지사항 페이지에서 페이지 네이션들을 그룹화시킬 페이지 네이션 세트값(5)을 초기 파라미터값으로 설정.
      context.setInitParameter("pagenationSet", "5");
   }
   
   @Override
   public void contextDestroyed(ServletContextEvent sce) {
      HikariDataSource dataSource = (HikariDataSource)sce.getServletContext().getAttribute("dataSource");
      
      /*
       * DB 를 사용하는 경우에만, CommonModule 에서 HikariDataSource 생성 및 JDBC 드라이버 로드가
       * 이루어지므로, DB 를 사용하지 않는 서블릿에서는 해당 리소스에 대한 null 포인터 할당에 따른 리소스
       * 해제시 예외 발생.
       * 따라서 아래와같이 HikariDataSource 에 대한 null 포인터 체크로 리소스 해제시의 방어 코드 설정.
       * 단, JDBC 드라이버는 HikariDataSource 를 생성하는 경우에만 유효하므로 별도의 null 포인터 유효성
       * 체크는 HikariDataSource 에 대한 null 포인터 체크로 통합 체크.
       */
      if( dataSource != null ) {
         dataSource.close();
//         AbandonedConnectionCleanupThread.checkedShutdown();
         
         try {
            Driver driver = DriverManager.getDriver((String)(sce.getServletContext().getAttribute("closedJdbcUrl")));
            DriverManager.deregisterDriver(driver);
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
}