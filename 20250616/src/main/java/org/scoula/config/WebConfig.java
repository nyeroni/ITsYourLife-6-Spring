package org.scoula.config;


import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Log4j2
@Configuration
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 📍 파일 업로드 설정 상수
    final String LOCATION = "/Users/yerong/documents/board";
    final long MAX_FILE_SIZE = 1024 * 1024 * 10L;      // 10MB
    final long MAX_REQUEST_SIZE = 1024 * 1024 * 20L;   // 20MB
    final int FILE_SIZE_THRESHOLD = 1024 * 1024 * 5;   // 5MB

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { ServletConfig.class };
    }

    // 스프링의 FrontController인 DispatcherServlet이 담당할 Url 매핑 패턴, / : 모든 요청에 대해 매핑
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    // POST body 문자 인코딩 필터 설정 - UTF-8 설정
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 📍 404 에러를 Exception으로 변환
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");

        // 📍 Multipart 파일 업로드 설정
        MultipartConfigElement multipartConfig = new MultipartConfigElement(
                LOCATION,           // 업로드 처리 디렉토리 경로
                MAX_FILE_SIZE,      // 업로드 가능한 파일 하나의 최대 크기
                MAX_REQUEST_SIZE,   // 업로드 가능한 전체 최대 크기(여러 파일 업로드)
                FILE_SIZE_THRESHOLD // 메모리 파일의 최대 크기(임계값)
        );
        registration.setMultipartConfig(multipartConfig);
    }
}