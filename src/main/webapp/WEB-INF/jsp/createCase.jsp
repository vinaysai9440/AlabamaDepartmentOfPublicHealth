<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Create SNAP Case</title>
  <script type="text/javascript">
    function validateForm() {
      var county = document.forms["caseForm"]["countyCd"].value;
      var office = document.forms["caseForm"]["officeCd"].value;

      if (!county || !office) {
        alert("County and Office are required.");
        return false;
      }
      return true;
    }
  </script>
</head>
<body>

<h2>Create SNAP Case</h2>

<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
<% if (errorMsg != null) { %>
  <div style="color:red;"><%= errorMsg %></div>
<% } %>

<form name="caseForm" method="post" action="<%=request.getContextPath()%>/case/create"
      onsubmit="return validateForm();">

  County Code: <input type="text" name="countyCd" maxlength="10"/><br/><br/>
  Office Code: <input type="text" name="officeCd" maxlength="10"/><br/><br/>

  <input type="submit" value="Create Case"/>
</form>

</body>
</html>