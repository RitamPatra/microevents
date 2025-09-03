<%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="row justify-content-center">
  <div class="col-md-8">
    <div class="card card-event">
      <div class="card-body">
        <h4>Create Event</h4>
        <form method="post" action="${pageContext.request.contextPath}/events/create">
          <div class="mb-3">
            <label class="form-label">Title</label>
            <input class="form-control" name="title"/>
          </div>
          <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea class="form-control" name="description"></textarea>
          </div>
          <div class="mb-3">
            <label class="form-label">Event time</label>
            <input class="form-control" type="datetime-local" name="eventTime"/>
          </div>
          <div class="mb-3">
            <label class="form-label">Location</label>
            <input class="form-control" name="location"/>
          </div>
          <div class="mb-3">
            <label class="form-label">Tags (comma separated)</label>
            <input class="form-control" name="tags"/>
          </div>
          <button class="btn btn-primary">Create</button>
        </form>
      </div>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/fragments/footer.jsp" %>
