<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./layout/header.jsp"%>

<div class="container-fluid mt-3">

	<!-- 페이지네이션 -->
	<!-- 시작 페이지의 정수형 연산을 위해 fmt 사용 -->
	<fmt:parseNumber integerOnly="true" var="startPage" value="${ (postList.number / 5) }"></fmt:parseNumber>
	<fmt:parseNumber integerOnly="true" var="startPageNumber" value="${ startPage * 5 + 1 }"></fmt:parseNumber>
	<ul class="pagination justify-content-center">
		<!-- 이전 페이지로 이동 -->
		<c:if test="${ postList.number > 4 }">
			<!-- 처음으로 -->
			<li class="page-item"><a class="page-link" href="?page=0"><i class="bi bi-chevron-double-left"></i></a></li>
			<!-- 이전 startPageNumber의 마지막 번호로 이동 -->
		  	<li class="page-item"><a class="page-link" href="?page=${ startPageNumber - 2 }"><i class="bi bi-chevron-left"></i></a></li>
		</c:if>
		<!-- 페이지넘버 -->
		<c:if test="${ postList.totalPages <= 5 }">
			<!-- 5페이지 이하 -->
			<c:forEach var="page" begin="1" end="${ postList.totalPages }">
				<c:if test="${ postList.number == page - 1 }">
					<li class="page-item active"><a class="page-link" href="?page=${ page - 1 }">${ page }</a></li>
				</c:if>
				<c:if test="${ postList.number != page - 1 }">
					<li class="page-item"><a class="page-link" href="?page=${ page - 1 }">${ page }</a></li>
				</c:if>
			</c:forEach>
		</c:if>
		<!-- 5페이지 이상 -->
		<c:if test="${ postList.totalPages > 5 }">
			<!-- 남은 페이지가 5페이지 이하 -->
			<c:if test="${ postList.totalPages - startPageNumber <= 4 }">
				<c:forEach var="page" begin="${ startPageNumber }" end="${ postList.totalPages }">
					<c:if test="${ postList.number == page - 1 }">
						<li class="page-item active"><a class="page-link" href="?page=${ page - 1 }">${ page }</a></li>
					</c:if>
					<c:if test="${ postList.number != page - 1 }">
						<li class="page-item"><a class="page-link" href="?page=${ page - 1 }">${ page }</a></li>
					</c:if>
				</c:forEach>
			</c:if>
			<!-- 남은 페이지가 5페이지 초과 -->
			<c:if test="${ postList.totalPages - startPageNumber > 4 }">
				<c:forEach var="page" begin="${ startPageNumber }" end="${ startPageNumber + 4 }">
					<c:if test="${ postList.number == page - 1 }">
						<li class="page-item active"><a class="page-link" href="?page=${ page - 1 }">${ page }</a></li>
					</c:if>
					<c:if test="${ postList.number != page - 1 }">
						<li class="page-item"><a class="page-link" href="?page=${ page - 1 }">${ page }</a></li>
					</c:if>
				</c:forEach>
			</c:if>
		</c:if>
		<!-- 다음 페이지로 이동 -->
		<c:if test="${ postList.totalPages > 5 }">
			<c:if test="${ postList.totalPages - startPageNumber > 4 }">
				<!-- 다음 startPageNumber로 이동 -->
				<li class="page-item"><a class="page-link" href="?page=${ startPageNumber + 4 }"><i class="bi bi-chevron-right"></i></a></li>
				<!-- 마지막 페이지로 이동 -->
		  		<li class="page-item"><a class="page-link" href="?page=${ postList.totalPages - 1 }"><i class="bi bi-chevron-double-right"></i></a></li>
			</c:if>
		</c:if>
	</ul>
	
</div>

<%@ include file="./layout/footer.jsp" %>