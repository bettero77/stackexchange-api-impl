<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>StackExchange API</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>
<div>
    <div class="row pb-20 mt-3">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form:form id="config" action="/search" method="POST" class="row lead" modelAttribute="searchParams">
                <div class="col-md-8">
                    <div class="form-group">Site:
                        <select id="site" class="form-control" name="site">
                            <c:forEach var="option" items="${sites}">
                                <c:choose>
                                    <c:when test="${option eq site}">
                                        <option selected="selected">${option}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>${option}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">Intitle:
                        <form:input path="title" maxlength="20" minlength="1"/>
                    </div>
                </div>
                <div class="form-group col-md-1">
                    <input id="submitButton" class="btn btn-primary btn-lg" type="submit" value="Submit">
                </div>
            </form:form>
            <div id="error">
                <c:forEach var="error" items="${errors}">
                    <div>${error}</div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-1"></div>
    <div id="searchResult" class="col-md-10">
        <div class="row">
            <c:choose>
            <c:when test="${questions.isEmpty()}">
                <div class="lead">No results</div>
            </c:when>
            <c:otherwise>
            <c:forEach var="question" items="${questions}">
            <c:choose>
            <c:when test="${question.getAnswered()}">
            <div id="question_${question.getQuestionId()}" class="col-md-4 card bg-success mb-3 text-white">
                </c:when>
                <c:otherwise>
                <div id="question_${question.getQuestionId()}" class="col-md-4 card bg-light mb-3">
                    </c:otherwise>
                    </c:choose>
                    <div class="card-header">
                        <div>
                            <div class="row">
                                <div class="col-md-5"><img src="${question.getAuthor().getProfileImage()}"
                                                           alt="Can't load image" height="90px"/></div>
                                <div class="col-md-7 font-weight-light">
                                    <c:choose>
                                        <c:when test="${question.getAuthor().getLink().isEmpty()}">
                                            <div>User: ${question.getAuthor().getName()}</div>
                                        </c:when>
                                        <c:otherwise>
                                            <div>User: <a
                                                    href="${question.getAuthor().getLink()}">${question.getAuthor().getName()}</a>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="mt-2 mb-2 card-body" style="text-align:center">
                        <div class="card-title lead h3 font-weight-normal">Title: <a
                                href="${question.getLink()}">${question.getTitle()}</a></div>
                        <div class="font-weight-light">
                            Created: <fmt:formatDate value="${question.getCreationDate()}"
                                                     pattern="dd.MM.yyyy HH:mm:ss"/>
                        </div>
                    </div>
                </div>
                </c:forEach>
                </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
</html>