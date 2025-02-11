<%@ include file= "common/header.jspf" %>
<%@ include file= "common/navigation.jspf" %>
<div class="container">
	<form:form method="post" modelAttribute="todo">
	<form:hidden path="id"/>
	<fieldset class="form-group">
		<form:label path="desc">Description</form:label>
		<form:input path="desc" type="text" class="form-control" required="required"/>
		<form:errors path="desc" cssClass="text-warning"></form:errors>
	</fieldset>
	<fieldset class="form-group">
		<form:label path="targetDate">fecha: </form:label>
		<form:input type="text" path="targetDate" class="form-control"/>
		<form:errors path="targetDate" cssClass="text-warning"></form:errors>
	</fieldset>
<!-- 	<fieldset class="form-group"> -->
<%-- 		<form:label path="done">esta terminada?: </form:label> --%>
<%-- 		<form:input type="text" path="done" class="form-control"/> --%>
<!-- 	</fieldset> -->
	<button type="submit" class="btn btn-success">add</button>
	</form:form>
</div>
<%@ include file= "common/footer.jspf" %>