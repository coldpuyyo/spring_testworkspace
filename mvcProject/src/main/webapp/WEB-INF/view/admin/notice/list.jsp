<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="/static/css/notice_list.css">
<link rel="stylesheet" href="/static/css/notice_list_admin.css">

<script type="text/javascript">
    function search() {
        const optionField = document.querySelector('.optionField')
        const searchInput = document.querySelector('.searchInput');

        if (searchInput.value) {
            location.href = '?pageNum=1&' + 'searchField=' + optionField.value + '&searchWord=' + searchInput.value;
        } else {
            location.href = location.pathname;
        }
    }

    function changePageScope(page) {
        const optionField = document.querySelector('.optionField')
        const searchInput = document.querySelector('.searchInput');

        location.href = "?pageNum=" + page + '&searchField=' + optionField.value + '&searchWord=' + searchInput.value;
    }

    function transPub(self) {
        const pubId = document.getElementsByClassName('pubId');
        const pubTrueId_ = [], pubFalseId_ = [];

        for (const ele of pubId) {
            if (ele.checked) {
                pubTrueId_.push(ele.value);
            } else {
                pubFalseId_.push(ele.value);
            }
        }
        
        const pubDelData = {
            pubTrueId_,
            pubFalseId_,
            pudDelBtn : self.value,
        };

        try {
            axios.post('/admin/notice/list', pubDelData);
        } catch (error) {
            console.log(error);
        }
    }
    
    async function removeNotice(self) {
        const delId = document.getElementsByClassName('delId');
        const delNotice = [];

        for (const ele of delId) {
            if (ele.checked) {
               delNotice.push(ele.value);
            }
        }
        
        const pubDelData = {
              delNotice,
              pudDelBtn : self.value,
        };

        try {
            const response = await axios.post('/admin/notice/list', pubDelData);
            
            location.href = location.href;
        } catch (error) {
            console.log(error);
        }
    }
    
    function regNotice() {
       // pageNum, searchField, searchWord 변수를 JSP에서 자바스크립트 변수로 할당해 사용하도록 하면 편리합니다.
       const pageNum = ${pageNum};
       const searchField = '${searchField}';
       const searchWord = '${searchWord}';

       location.href = `board/reg?pageNum=${pageNum}&searchField=${searchField}&searchWord=${searchWord}`;
    }
</script>
</head>

<body>
	<h1 id="logo">
		<a href="/">PSYLAB</a>
	</h1>

	<div id="body">
		<main class="main">
			<h2>공지사항</h2>

			<div>
				<label>검색분류 
					<select class="optionField">
						<option value="title" ${searchField=='title' ? 'selected' : ''}>제목</option>
						<option value="writer_Id" ${searchField=='writer_Id' ? 'selected' : ''}>작성자</option>
					</select>
				</label> 
				<label>검색어 
					<input type="text" class="searchInput" value="${searchWord}" />
				</label>
				<button type="button" onclick="search()">검색</button>
			</div>
			<hr>

			<div class="noticeTable">
				<h3 class="hidden">공지사항 목록</h3>
				<div class="table">
					<div class="thead">번호</div>
					<div class="thead">제목</div>
					<div class="thead">작성자</div>
					<div class="thead">작성일</div>
					<div class="thead">조회수</div>
					<div class="thead">공개</div>
					<div class="thead">삭제</div>

					<c:forEach var="noticeView" items="${noticeViews}">
						<div>${noticeView.id}</div>
						<div>
							<a href="detail/page?id=${noticeView.id}&pageNum=${pageNum}&searchField=${searchField}&searchWord=${searchWord}">
								${noticeView.title} <span class="colorRed">(${noticeView.cmt_cnt})</span>
							</a>
						</div>
						<div>${noticeView.writer_id}</div>
						<div><fmt:formatDate value="${noticeView.regDate}" pattern="yyyy-MM-dd" /></div>
						<div class="colorRed">${noticeView.hit}</div>

						<div>
							<input type="checkbox" class="pubId" value="${noticeView.id}" ${noticeView.pub ? 'checked' : ''} />
						</div>
						<div>
							<input type="checkbox" class="delId" value="${noticeView.id}" />
						</div>
					</c:forEach>
				</div>

				<div class="dataControlBtn">
					<button value="batchPubBtn" onclick="transPub(this)">일괄공개</button>
					<button value="batchDelBtn" onclick="removeNotice(this)">일괄삭제</button>
					<button class="writeBtn" onclick="regNotice()">글쓰기</button>
				</div>
			</div>
			<hr>

			<div class="pageNationPart">
				<div>
					<h3 class="hidden">현재 페이지</h3>
					<div>
						<span>${pageNum}</span> / <span class="colorRed">${wholePage}</span> pages
					</div>
				</div>

				<div>
					<ul>
						<c:forEach var="printPageNum" begin="${pageNationStartNum}" end="${pageNationLastNum}">
							<li ${ pageNum == printPageNum ? 'class="sellected"' : '' }>
								<a href="?pageNum=${printPageNum}&searchField=${searchField}&searchWord=${searchWord}">${printPageNum}</a>
							</li>
						</c:forEach>
					</ul>

					<div class="preNextBtn">
						<c:choose>
							<c:when test="${pageNum > 1}">
								<button class="btn-prev" onclick="changePageScope(${pageNum - 1})">이전</button>
							</c:when>
							<c:otherwise>
								<button class="btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</button>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${pageNum < wholePage}">
								<button class="btn-next" onclick="changePageScope(${pageNum + 1})">다음</button>
							</c:when>
							<c:otherwise>
								<button class="btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>

</html>
