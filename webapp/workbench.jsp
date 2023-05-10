<%@ page import="com.wf.login.servlet.Upload" %>
<%@ page import="com.wf.login.dao.UploadDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>控制台</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/app.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/subpage.css">
</head>
<body >
    <h1 class="callout">工作台</h1>
    <div>
        <h2 style="margin: 100px 0;text-align: center;">登录成功！</h2>
    </div>

<!--文件模块 自己写
    <form action="UploadServlet" method="post" enctype="multipart/form-data">
        <table width="600">
            <tr>
                <td>上传者</td>
                <td><input type="text" name="name"/></td>
                <span>默认上传者：${sessionScope.user.username }</span>
            </tr>
            <tr>
                <td>上传文件</td>
                <td><input type="file" name="myfile"/></td>
            </tr>
            <tr>
               设置单元格可横跨的列数。
                <td colspan="2"><input type="submit" value="上传"/></td>
            </tr>
        </table>

    </form>
    -->
    <!--
    <form method="POST" action="FileUpload" enctype="multipart/form-data" >
        <table>
            <tr><td>Id</td>
                <td><input type="text" name="id" /></td>
                <span>默认上传者：${sessionScope.user.username }</span>
            <tr>
                <td>File</td>
                <td><input type="file" name="file" id="file" /> </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Upload" name="upload" id="upload" /> </td>
            </tr>
        </table>
        </form>

    -->
    <form action="${pageContext.request.contextPath}/" enctype="multipart/form-data" method="post">
        用户名：<input type="text" name="username">
        上&nbsp;&nbsp;传：<input type="file" name="file1">
        <input type="submit" value="上传">

    </form>
    <form action="/doAdd" method="post" enctype="multipart/form-data"><!---->
        <p>论文题目 <input name="title" required type="text"/>&nbsp;&nbsp;<span id="titleDesc"></span></p>
        <p>论文类型
            <select name="typeCode">
                <option >请选择论文类型</option>
                <!--                <option>应用性</option>-->
                <!--                <option>理论性</option>-->
            </select>
        </p>
        <p>论文摘要 <textarea id="paperSummary"  name="paperSummary" rows="10" cols="30"></textarea></p>

        <p>论文内容 文件：<input type="file" id="file" name="file" accept=".doc,.docx"  /></p>
        <p>*. 上传大小不能超过5M *上传文件类型
            必须为: doc docx</p>
        <input type="submit" value="保存">&nbsp;&nbsp;<button onclick="back()">返回</button><br>

    </form>




</body>
</html>
