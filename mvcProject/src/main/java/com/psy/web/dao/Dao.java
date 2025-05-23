package com.psy.web.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.psy.web.dto.Notice;
import com.psy.web.dto.view.notice.NoticeView;

public interface Dao {
	// 검색 필드와 검색어 입력된 상태에서의 페이지 네이션 클릭시의 조회를 위한 메서드.
	ArrayList<NoticeView> getNotices(int pageNum, String searchField, String searchWord, boolean pub)
			throws SQLException;

	// 공지사항에서 검색된 레코드수 조회를 위한 메서드.
	int getNoticeCnt(String searchField, String searchWord, boolean pub) throws SQLException;

	// list.jsp 페이지에서 목록을 클릭했을 때, 해당 목록에 대응되는 ID 를 통해 검색된 데이터를 detail/page.jsp 페이지에
	// 로드하기 위한 메서드.
	Notice getCurrentNotice(int id) throws SQLException;

	// 공지사항 상세 페이지 조회시의 이전 페이지 조회를 위한 메서드(전체 목록 및 검색 목록 조회시)
	Notice getPrevNotice(int id, String searchField, String searchWord, boolean pub) throws SQLException;

	// 공지사항 상세 페이지 조회시의 다음 페이지 조회를 위한 메서드(검색 목록 조회시).
	Notice getNextNotice(int id, String searchField, String searchWord, boolean pub) throws SQLException;

	// 관리자 메서드 - 공개 및 비공개 여부 설정. 파라미터 인수 목록 변경.
	int setPub(int[] pubTrueId_, int[] pubFalseId_) throws SQLException;

	int delNotice(int[] delId) throws SQLException;
	
	int regNotice(Notice notice) throws SQLException;
}