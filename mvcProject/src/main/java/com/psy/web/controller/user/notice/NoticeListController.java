package com.psy.web.controller.user.notice;

import java.io.IOException;
import java.util.List;

import com.psy.web.dto.view.notice.NoticeView;
import com.psy.web.service.Service;
import com.psy.web.service.imp.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/notice/list")
public class NoticeListController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   private Service service;
   
   // 세터 DI 주입용
   public void setService(Service service) {
      this.service = service;
   }
   
   public NoticeListController() {
      // 기본 생성자에서는 service 초기화하지 않음, 세터 주입을 기대
   }
   
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if(service == null) {
         service = new UserService();  // 혹은 예외 처리
      }
      
      int defaultPageNum = 1;

      String pageNum = request.getParameter("pageNum");
      String searchField = request.getParameter("searchField");
      String searchWord = request.getParameter("searchWord");
      
      List<NoticeView> notices = null;
      int noticeCnt = 0;
      
      if( searchWord == null ) {
         notices = service.getNotices(defaultPageNum);
         noticeCnt = service.getNoticeCnt();
         
         pageNum = String.valueOf(defaultPageNum);
      } else if ( searchWord.equals("") ) {
         notices = service.getNotices(Integer.parseInt(pageNum));
         noticeCnt = service.getNoticeCnt();
      } else {
         notices = service.getNotices(Integer.parseInt(pageNum), searchField, searchWord);
         noticeCnt = service.getNoticeCnt(searchField, searchWord);
      }
      
      request.setAttribute("pagingSizeValue", getServletContext().getInitParameter("pagingSizeValue"));
      request.setAttribute("pagenationSet", getServletContext().getInitParameter("pagenationSet"));
      request.setAttribute("noticeViews", notices);
      request.setAttribute("noticeCnt", noticeCnt);
      request.setAttribute("pageNum", pageNum);
      request.setAttribute("searchField", searchField);
      request.setAttribute("searchWord", searchWord);
      
      request.getRequestDispatcher("/WEB-INF/view/user/notice/list.jsp").forward(request, response);
   }
}
