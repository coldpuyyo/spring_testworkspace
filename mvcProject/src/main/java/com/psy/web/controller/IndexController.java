package com.psy.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexController implements Controller {
   @Override
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
      /*
       * Thymeleaf 라이브러리를 이용하는 경우에는 각 컨트롤에서 반환하는 뷰 이름은 Tiles 와는 다르지만,
       * 기존 뷰리졸버와 동일하게 타임리프 뷰리졸버에서 설정한 prefix 와 suffix 와 조합되는 파일명 또는
       * 파일명을 포함한 경로명이 됨에 주의.
       */
      ModelAndView mv = new ModelAndView("index");
      
      /*
       * ModelAndView 에 addObject 메서드로 전달된 속성 및 값은 해당 HTML 에서 타임리프 전용 속성
       * (text)을 통해 value 부분에 EL 형식으로 속성명을 지정하여 해당하는 값을 추출 가능.
       */
      mv.addObject("title", "메인페이지");
      
      return mv;
   }
}