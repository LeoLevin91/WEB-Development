<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/index.css">
    <title>Index</title>
</head>
<body>
    <div class="container">
        <section class="logo">
            <div class="logo_title">
                <span>top_label</span>
            </div>
        </section>
        <section class="form_container">
            <form action="index" method="post">
                <div class="inputs_wrapper">
                   <div class="input_field clearfix">
                       <div class="a">
                           <!-- <input type="text" name="login" placeholder="Enter username" required style="<%=userIsFound(application)%>">                           -->
                           <input type="text" name="login" placeholder="Enter username" required style="">
                           <span></span>
                       </div>
                   </div>
                    <div class="input_field clearfix">
                        <div class="a">
<!--                        <input type="password" name="password" placeholder="Enter password" required style="<%=userIsFound(application)%>">-->
                            <input type="password" name="password" placeholder="Enter password" required style="">
                        </div>
                    </div>
                </div>
                <button>LOGIN</button>
            </form>
        </section>







    </div>

</body>
</html>
