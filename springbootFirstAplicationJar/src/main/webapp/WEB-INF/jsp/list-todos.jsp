<%@ include file= "common/header.jspf" %>
<%@ include file= "common/navigation.jspf" %>
<div class="container">
	<h1>Todos de ${name}</h1>
	<table class="table table-striped">
		<caption>Your Todos are:</caption>
		<thead> 
			<tr> 
				<th>Description</th>
				<th>Date</th>
				<th>Is it done?</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${todos}" var="todo">
			<tr> 
				<td>${todo.desc}</td>
				<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
				<td>${todo.done}</td>
				<td><a type="button" class="btn btn-warning" href="/delete-todo?id=${todo.id}">delete</a></td>
				<td><a type="button" class="btn btn-success" href="/update-todo?id=${todo.id}">update</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br>
	<div><a class="button" href="add-todo">add Todo</a></div>
</div>
<%@ include file= "common/footer.jspf" %>