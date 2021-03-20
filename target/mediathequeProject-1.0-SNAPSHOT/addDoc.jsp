<%--
  Created by IntelliJ IDEA.
  User: Aman KUMAR
  Date: 04/03/2021
  Time: 22:50
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
                    Document Add Section, You can add 3 types of documents : Books, CD and DVD.
				</span>
        </div>
        <section>
            <form method=post id="add-doc-form" class="login100-form validate-form p-b-33 p-t-5">
                <div class="field">
                    <label class="text-secondary mb-1">Title</label>
                    <div class="control">
                        <input class="input100" type="text" name="titre" placeholder="Enter a title">
                    </div>
                </div>

                <% String type = request.getParameter("type");
                if(type.equals("1")){
                    %>
                    <div class="field">
                        <label class="text-secondary mb-1">Book Autor :</label>
                        <div class="control">
                            <div class="select">
                                <input class="input100" type="text" name="auteur" placeholder="Enter a autor name">
                            </div>
                        </div>
                    </div>
                <%}else if(type.equals("2")){%>
                    <div class="field">
                        <label class="text-secondary mb-1">DVD/Film Director :</label>
                        <div class="control">
                            <div class="select">
                                <input class="input100" type="text" name="realisateur" placeholder="Enter director name">
                            </div>
                        </div>
                    </div>
                <%} else {%>
                    <div class="field">
                        <label class="text-secondary mb-1">Music Artist :</label>
                        <div class="control">
                            <div class="select">
                                <input class="input100" type="text" name="artiste" placeholder="Enter artist name">
                            </div>
                        </div>
                    </div>
                <%}%>

                <div class="field">
                    <label class="text-secondary mb-1">Description</label>
                    <div class="control">
                        <textarea class="input100" name="desc" placeholder="Enter a description"></textarea>
                    </div>
                </div>

                <div class="container-login100-form-btn m-t-32">
                    <button class="login100-form-btn" type="submit" name="submit">
                        Add
                    </button>
                </div>
            </form>
        </section>
    </div>

</div>

</body>
