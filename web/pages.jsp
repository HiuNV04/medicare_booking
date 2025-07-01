<%-- 
    Document   : pages
    Created on : Jun 30, 2025, 9:57:33 AM
    Author     : ADDMIN
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<c:if test="${totalPages > 1}">
    <div class="d-flex justify-content-center mt-4">
        <nav aria-label="Pagination">
            <ul class="pagination pagination-sm">

                <!-- Nút "Trước" -->
                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                    <a class="page-link rounded-pill px-3"
                       href="${baseUrl}?page=${currentPage - 1}${queryString}">
                        &laquo; Trước
                    </a>
                </li>

                <!-- Các trang -->
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link rounded-circle mx-1 text-center"
                           style="width: 38px;"
                           href="${baseUrl}?page=${i}${queryString}">
                            ${i}
                        </a>
                    </li>
                </c:forEach>

                <!-- Nút "Sau" -->
                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                    <a class="page-link rounded-pill px-3"
                       href="${baseUrl}?page=${currentPage + 1}${queryString}">
                        Sau &raquo;
                    </a>
                </li>

            </ul>
        </nav>
    </div>
</c:if>

