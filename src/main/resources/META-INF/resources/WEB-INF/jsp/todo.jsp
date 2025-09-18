<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet"> 
    <title>List Todos</title>
</head>
<body>
    <%@ include file="common/navigation.jspf" %>
    <div class="container">
        <h2>Todos Details:</h2>
        <form:form method="post" modelAttribute="todo">
            
            <fieldset class="mb-3">
                <form:label path = "description">Description</form:label>
                <form:input type="text" path = "description" required="required"/>
                <form:errors path = "description" cssClass = "text-warning"/>
            </fieldset>

            <fieldset class="mb-3">
                <form:label path = "targetDate">TargetDate</form:label>
                <form:input type="text" path = "targetDate" required="required"/>
                <form:errors path = "targetDate" cssClass = "text-warning"/>
            </fieldset>


            <form:input type="hidden" path = "id"/>
            <form:input type="hidden" path = "done"/>
            <input type="submit" class="btn btn-success"/>
        </form:form>
    </div>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>