<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todo List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css" type="text/css" />
</head>
<body>
	<h1>Todo List</h1>
	<div id="todoForm">
		<%-- エラーメッセージ --%>
		<t:messagesPanel />
		<%--入力エリア --%>
		<form:form action="${pageContext.request.contextPath}/todo/create" method="post" modelAttribute="todoForm">
			<form:input path="todoTitle" />
			<form:errors path="todoTitle" cssClass="text-error" />
			<form:button>Create Todo</form:button>
		</form:form>
	</div>
	<hr />
	<div id="todoList">
		<ul>
			<%-- 件数分繰り返し --%>
			<c:forEach items="${todos}" var="todo">
				<li>
					<c:choose>
						<%-- 完了の場合 --%>
						<c:when test="${todo.finished}">
							<span class="strike">${f:h(todo.todoTitle)}</span>
						</c:when>
						<%-- 未完了の場合 --%>
						<c:otherwise>
							${f:h(todo.todoTitle)}
							<form:form action="${pageContext.request.contextPath}/todo/finish" method="post" modelAttribute="todoForm" cssStyle="display: inline-block;">
								<form:hidden path="todoId" value="${f:h(todo.todoId)}" />
								<form:button>Finish</form:button>
							</form:form>
						</c:otherwise>
					</c:choose>
					<form:form action="${pageContext.request.contextPath}/todo/delete" method="post" modelAttribute="todoForm" cssStyle="display: inline-block;">
						<form:hidden path="todoId" value="${f:h(todo.todoId)}" />
						<form:button>Delete</form:button>
					</form:form>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>