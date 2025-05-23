//package com.psy.web.controller.notice;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class DetailController {
//   @GetMapping("/view/customer/notice/detail")
//   public String handleRootRequest(Model mv) {
//
//      mv
//         .addAttribute("title", "공지사항목록별 Detail 페이지")
//         .addAttribute("isList", false)
//         .addAttribute("fragment", "customer/include/customer_common_module");
//
//      return "customer/notice/list_detail";
//   }
//}