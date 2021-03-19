<%@ page import="aman.fr.persistantdata.modele.user.AUser" %>
<%--
Created by IntelliJ IDEA.
  User: Aman KUMAR
  Date: 28/02/2021
  Time: 19:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<style type="text/css">
    body { background: rgba(0,0,0,0.65) }
</style>
<body>

<div class="p-5 text-center bg-image"
     style="
      background-image: url('../images/bg-01.jpg');
      height: 400px; ">
    <div class="container-login100" >
        <div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
                    Welcome to Librarian section.
				</span>
        </div>
        <div class="col-md-4 mb-3 d-flex">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex flex-column align-items-center text-center">
                        <img src="https://img.icons8.com/ios/50/000000/test-account.png" alt="Subscriber" class="rounded-circle" width="50">
                        <div class="mt-3">
                            <h4><% AUser user = (AUser) session.getAttribute("utilisateur");
                                out.print(user.getLogin()); %>.</h4>
                            <p class="text-secondary mb-1">librarian</p>
                            <p class="text-muted font-size-sm">As a librarian you have access to following services :</p>
                            <a class="btn btn-outline-primary"  href="/Catalogue">View all docuemnts</a>
                            <a class="btn btn-outline-primary"  href="/AddDoc">Add documents</a>
                            <a class="btn btn-outline-primary"  href="/Logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>

