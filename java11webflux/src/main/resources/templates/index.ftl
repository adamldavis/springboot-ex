<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${applicationName}</title>

    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
            crossorigin="anonymous"></script>

    <script src="/js/main.js" type="application/javascript"></script>

    <style>.courses { font-size: 2em; }</style>

</head>
<body onload="">
 <div class="container">

     <#if name == ""><a href="#" onclick="signUp(); return false">Sign up today!</a></#if>

     <div class="navbar">Hello ${name}!</div>

    <div class="page-header">
        <h1>Welcome to ${applicationName}!</h1>
    </div>

    <article id="content" class="jumbotron center"></article>

    <script type="application/javascript">
        jQuery(document).ready(HC.loadCourses);
    </script>

     <form method="post" action="/api/course">
         Name: <input id="name" name="name" type="text">
         Price: <input id="price" name="price" type="number">
         <button onclick="HC.postCourse()">Save Course</button>
     </form>

 </div>
</body>
</html>