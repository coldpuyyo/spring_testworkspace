//package com.psy.web.controller.notice;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class ListController {
//   @GetMapping("/view/customer/notice/list")
//   public String handleRootRequest(Model mv) {
//      
//      mv.addAttribute("title", "공지사항목록") ;
//      mv.addAttribute("isList", true);
//      mv.addAttribute("fragment", "customer/include/customer_common_module");
//
//      return "customer/notice/list_detail";
//   }
//}
//
//package com.psy.web.controller.notice;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class ListController {
//   @GetMapping("/view/customer/notice/list")
//   public String handleRootRequest(Model mv) {
//      
//      /*
//       * Model 인터페이스는 아래와같이 addAttribute() 를 통한 체이닝 가능.
//       * ModelAndView 도 addObject 메서드를 통한 체이닝 가능. 
//       */
//      mv
//         .addAttribute("title", "공지사항목록")
//         .addAttribute("isList", true)
//         .addAttribute("fragment", "customer/include/customer_common_module");
//      
//      return "customer/notice/list_detail";
//   }
//}