<%--
  Created by IntelliJ IDEA.
  User: stone
  Date: 2017/6/12
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>new</title>
</head>
<body>
<table>
    <th>id</th>
    <th>name</th>
    <th>age</th>
    <th>school</th>
    <c:forEach items="${u}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.age}</td>
            <td>${item.school}</td>
            <td><input type="button" value="编辑" onclick="edit(${item.id},'${item.name}',${item.age},${item.school})"/>
                <input type="button" value="删除" onclick="del(${item.id})"/></td>
        </tr>
    </c:forEach>
    <a href="?page=${prePage}">上一页</a>
    <a href="?page=${nextPage}">下一页</a>
    <form:form action="save" method="post">
        <table>
            <tr>
                <td><form:label path="name">Name</form:label></td>
                <td><form:input path="name"/><form:hidden path="id"/></td>
            </tr>
            <tr>
                <td><form:label path="age">Age</form:label></td>
                <td><form:input path="age"/></td>
            </tr>
            <tr>
                <td><form:label path="school">School</form:label></td>
                <td><form:select path="school">
                    <c:forEach items="${school}" var="item">
                        <form:option value="${item.id}">${item.school}</form:option>
                    </c:forEach>
                </form:select></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form:form>
</table>
<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
<script>
    function edit(id, name, age, school) {
        console.info("edit" + id);
        $("#id").val(id);
        $("#name").val(name);
        $("#age").val(age);
        $("#school").val(school);
    }
    function del(id) {
        console.info("del" + id);
        $.ajax({
            url: "delete",
            type: "POST",
            dataType: "text",
            data: {"id": id},
            success: function (result) {
                window.location.reload();
            },
            error: function (result) {
                console.info("error");
                alert(result);
            }
        })
    }
</script>
</body>
</html>
