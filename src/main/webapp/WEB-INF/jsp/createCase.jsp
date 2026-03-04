<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Create SNAP Case</title>
  <script type="text/javascript">
    function validateForm() {
      var county = document.forms["caseForm"]["countyCd"].value;
      var office = document.forms["caseForm"]["officeCd"].value;
      var applicationDt = document.forms["caseForm"]["applicationDt"].value;
      var createdBy = document.forms["caseForm"]["createdBy"].value;

      if (!county || !office || !applicationDt || !createdBy) {
        alert("County, Office, Application Date, and Created By are required.");
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

  County Code: <input type="text" name="countyCd" maxlength="10" value="<%= request.getAttribute("countyCd") == null ? "" : request.getAttribute("countyCd") %>"/><br/><br/>
  Office Code: <input type="text" name="officeCd" maxlength="10" value="<%= request.getAttribute("officeCd") == null ? "" : request.getAttribute("officeCd") %>"/><br/><br/>
  Application Date: <input type="date" name="applicationDt" value="<%= request.getAttribute("applicationDt") == null ? "" : request.getAttribute("applicationDt") %>"/><br/><br/>
  Created By: <input type="text" name="createdBy" maxlength="50" value="<%= request.getAttribute("createdBy") == null ? "" : request.getAttribute("createdBy") %>"/><br/><br/>

  <input type="submit" value="Create Case"/>
</form>

</body>
</html>
