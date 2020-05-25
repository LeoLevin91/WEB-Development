<%--
  Created by IntelliJ IDEA.
  User: Leonard
  Date: 24.05.2020
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private String userIsFound(ServletContext application) {
        String topLabel = (String)application.getAttribute("top_label");
        System.out.println("index.jsp "+topLabel);
        if (topLabel.equals("USER NOT FOUND"))
            return "background: #ffacac;";
        else
            return "background: #c2c2c2;";
    }

%>

<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" href="styles/index.css">
</head>
<body>
<div class="container">
    <section class="logo">
        <div class="logo_title">
            <span>${top_label}</span>
        </div>
    </section>
    <section class="form_container">
        <form action="index" method="post">
            <div class="inputs_wrapper">
                <div class="input_field clearfix">
                    <div class="a">
                        <input type="text" name="login" placeholder="Enter username" required style="<%=userIsFound(application)%>">
                        <span></span>
                    </div>
                </div>
                <div class="input_field clearfix">
                    <div class="a">
                        <input type="password" name="password" placeholder="Enter password" required style="<%=userIsFound(application)%>">
                    </div>
                </div>
            </div>
            <button>LOGIN</button>
        </form>
    </section>
</div>

</body>
</html>
