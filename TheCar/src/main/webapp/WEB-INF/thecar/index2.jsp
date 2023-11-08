<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./layout/header.jsp"%>

<div class="container-fluid mt-3">

	<!-- 페이지네이션 -->
	<!-- 시작 페이지의 정수형 연산을 위해 fmt 사용 -->
	<fmt:parseNumber integerOnly="true" var="startPage" value="${ (postList.number / 5) }"></fmt:parseNumber>
	<fmt:parseNumber integerOnly="true" var="startPageNumber" value="${ startPage * 5 + 1 }"></fmt:parseNumber>
	<ul class="pagination justify-content-center">
		
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
		
	</ul>
	
</div>

<%@ include file="./layout/footer.jsp" %>