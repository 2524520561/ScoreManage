<%--
  Created by IntelliJ IDEA.
  User: zsq
  Date: 2018/7/10
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎界面</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/scoreManage/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/xadmin.css">
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script src="/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/xadmin.js"></script>

</head>
<body>
<div class="x-body">
    <form class="layui-form" action="${pageContext.request.contextPath}/scoreManageeditCourseInfo" method="post">
        <div class="layui-form-item">
            <label for="couName" class="layui-form-label">
                <span class="x-red">*</span>课程名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="couName" name="couName" value="${cou.couName}" required="" lay-verify="required"
                       autocomplete="off"  class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="couId" class="layui-form-label">
                <span class="x-red">*</span>课程号
            </label>
            <div class="layui-input-inline">
                <input type="text"  id="couId" name="couId" value="${cou.couId}" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>
            </div>
        </div>

        <div class="layui-form-item">
            <button  class="layui-btn" lay-filter="add" lay-submit="">
                修改
            </button>
        </div>
    </form>
</div>
</body>
<script>
    layui.use('form', function() {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;
        //监听提交
        form.on('submit(add)', function(data){
            $.ajax({
                url: data.form.action,
                type: data.form.method,
                data: $(data.form).serialize(),
                success: function (info) {
                    if (info.code === 200) {
                        layer.alert("修改成功", {icon: 6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                            parent.location.reload();
                        });
                    }
                    else {
                        layer.alert("修改失败，课程号冲突", {icon: 6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                            parent.location.reload();
                        });
                    }
                }
            });
            //发异步，把数据提交给php
            return false;
        });

    });
</script>
</html>
