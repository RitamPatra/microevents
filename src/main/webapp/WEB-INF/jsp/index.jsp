<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="text-center py-5">
  <h1>Welcome to Microevents</h1>
  <p class="lead">Small local meetups — create, RSVP, and discover events.</p>
  <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/events">Browse events</a></p>
</div>

<%@ include file="/WEB-INF/jsp/fragments/footer.jsp" %>
