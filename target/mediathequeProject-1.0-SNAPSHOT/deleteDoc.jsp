<%--
  Created by IntelliJ IDEA.
  User: Aman
  Date: 20/03/2021
  Time: 22:26
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
                    Delete a documents by there ID.
				</span>
        </div>
        <section>
            <form method=post id="add-doc-form" class="login100-form validate-form p-b-33 p-t-5">
                <div class="field">
                    <label class="text-secondary mb-1">Document ID:</label>
                    <div class="control">
                        <input class="input100" type="text" name="id" placeholder="Enter a id number">
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

