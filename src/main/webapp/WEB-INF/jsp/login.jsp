<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="row justify-content-center">
  <div class="col-md-6">
    <div class="card card-event">
      <div class="card-body">
        <h4 class="card-title">Login (demo)</h4>
        <form method="post" action="${pageContext.request.contextPath}/login">
          <div class="mb-3">
            <label class="form-label">Username</label>
            <input name="username" class="form-control"/>
          </div>
          <div class="mb-3">
            <label class="form-label">Password</label>
            <input name="password" type="password" class="form-control"/>
          </div>
          <button class="btn btn-primary">Login / Register</button>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/fragments/footer.jsp" %>
