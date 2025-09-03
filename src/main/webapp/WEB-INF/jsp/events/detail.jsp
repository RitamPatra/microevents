<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="card card-event">
  <div class="card-body">
    <h3>${event.title}</h3>
    <div class="text-muted mb-2">${event.eventTime} — ${event.location}</div>
    <div class="mb-3">${event.description}</div>

    <div class="mb-2">
      <c:forEach items="${event.tags}" var="t">
        <span class="badge badge-tag">${t.name}</span>
      </c:forEach>
    </div>

    <div class="mb-3">
      Going: <strong>${goingCount}</strong> | Maybe: <strong>${maybeCount}</strong>
    </div>

    <c:if test="${not empty username}">
      <form method="post" action="${pageContext.request.contextPath}/events/${event.id}/rsvp">
        <div class="input-group">
          <select class="form-select" name="status">
            <option value="YES">Yes</option>
            <option value="MAYBE">Maybe</option>
            <option value="NO">No</option>
          </select>
          <button class="btn btn-primary" type="submit">RSVP</button>
        </div>
      </form>
    </c:if>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/fragments/footer.jsp" %>
