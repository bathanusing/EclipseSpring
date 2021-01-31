<%@ include file= "common/header.jspf" %>
<%@ include file= "common/navigation.jspf" %>
<div class="container">
<form method="post">
<span>Name: </span>
<input type="text" name="name">
<span>password: </span>
<input type="password" name="password">
<input type="submit">
</form>
${error}
</div>
<%@ include file= "common/footer.jspf" %>