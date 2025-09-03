<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
  <div class="col-md-8">
    <h3>Upcoming Events</h3>

    <c:forEach items="${events}" var="e">
      <div class="mb-3">
        <div class="card card-event">
          <div class="row g-0">
            <div class="col-md-9 p-3">
              <h5><a href="${pageContext.request.contextPath}/events/${e.id}">${e.title}</a></h5>
              <div class="text-muted">${e.eventTime} — ${e.location}</div>
              <p class="mt-2">${e.description}</p>
            </div>
            <div class="col-md-3 p-3">
              <div>
                <c:forEach items="${e.tags}" var="t">
                  <span class="badge badge-tag">${t.name}</span>
                </c:forEach>
              </div>
              <div class="mt-3">
                <a class="btn btn-outline-primary btn-sm" href="${pageContext.request.contextPath}/events/${e.id}">Details</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>

  <div class="col-md-4">
    <div class="card card-event sidebar-suggestions">
      <div class="card-body">
        <h6>Suggested for you</h6>
        <c:if test="${not empty suggestions}">
          <ul class="list-group list-group-flush">
            <c:forEach items="${suggestions}" var="s">
              <li class="list-group-item"><a href="${pageContext.request.contextPath}/events/${s.id}">${s.title}</a></li>
            </c:forEach>
          </ul>
        </c:if>
        <c:if test="${empty suggestions}">
          <div class="text-muted">No suggestions yet — RSVP to events to get recommendations.</div>
        </c:if>
      </div>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/fragments/footer.jsp" %>
