<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Case Created</title></head>
<body>
  <h2><%= request.getAttribute("successMsg") == null ? "Case Created Successfully" : request.getAttribute("successMsg") %></h2>
  Case ID: <b><%= request.getAttribute("caseId") %></b><br/><br/>
  <a href="<%=request.getContextPath()%>/">Go to Home</a>
</body>
</html>
