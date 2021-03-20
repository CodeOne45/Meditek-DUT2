<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j" %>
<%@ page import="aman.fr.persistantdata.modele.docs.ADocument" %>
<%@ page import="mediatek2021.Document" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: Aman
  Date: 19/03/2021
  Time: 00:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<style type="text/css">
    body { background: rgba(0,0,0,0.65) }
</style>
<body>

<div class="p-5 text-center bg-image"
     style="
      height: 400px; ">
    <div class="container-login100" >
        <div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
                    All Documents
				</span>
        </div>
        <div>
            <table>
                <thead> Documents</thead>
            </table>
            <tbody>
            <j:forEach items="${ documents }" var="document">
                <tr><td><j:out value="${ document }"></j:out><br></td></tr>
            </j:forEach>
            </tbody>
        </div>
    </div>

</div>

</body>
