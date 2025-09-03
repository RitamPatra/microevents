<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>Microevents</title>

  <!-- Bootstrap 5 CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="" crossorigin="anonymous"/>

  <!-- custom CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Microevents</a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navMenu">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/events">Events</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/events/create">Create</a></li>
      </ul>

      <ul class="navbar-nav ms-auto">
        <c:choose>
          <c:when test="${not empty username}">
            <li class="nav-item d-flex align-items-center pe-2">
              <span class="nav-link p-0">Signed in as <strong>${username}</strong></span>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
            </li>
          </c:when>
          <c:otherwise>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>

<div class="container container-main">
  <!-- flash message (success/info) -->
  <c:if test="${not empty message}">
    <div class="alert alert-success" role="alert">${message}</div>
  </c:if>
  <c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert">${error}</div>
  </c:if>
