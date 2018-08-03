<%--
  Created by IntelliJ IDEA.
  User: zsq
  Date: 2018/7/11
  Time: 14:15
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
    <form class="layui-form" action="${pageContext.request.contextPath}/scoreManage/addScoreInfo" method="post">
        <div class="layui-form-item">
            <label for="name" class="layui-form-label">
                <span class="x-red">*</span>姓名
            </label>
            <div class="layui-input-inline" id="namediv">
                <input type="text" id="name" name="name" required="" lay-verify="required"
                       autocomplete="off"  class="layui-input" >
            </div>
            <div class="layui-form-mid layui-word-aux" id="no">
                <span class="x-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="stuId" class="layui-form-label">
                <span class="x-red">*</span>学号
            </label>
            <div class="layui-input-inline">
                <select name="stuId" id = "stuId">

                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="couId" class="layui-form-label">
                <span class="x-red">*</span>课程名称
            </label>
            <div class="layui-input-inline">
                <select name="couId">
                    <c:forEach items="${cou}" var="course">
                    <option  value="${course.couId}">${course.couName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="layui-form-item">
            <label for="score" class="layui-form-label">
                <span class="x-red">*</span>分数
            </label>
            <div class="layui-input-inline">
                <input type="text" id="score" name="score" required="" lay-verify="scoree"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <button  class="layui-btn" lay-filter="add" lay-submit="">
                增加
            </button>
        </div>
    </form>
</div>
</body>
<script>
    var form;
    $("#name").blur(function(){
        var name = $("#name").val();
        var params = {"name":name};
        $.post("/scoreManage/querySid",params, function(data){
            if(data.code == 200) {
                var span = document.getElementById("span1");
                if(span != null){
                $(span).remove();
                }
                var options = '';
               var root = document.getElementById("stuId");
                  root.options.length=0;

                for(var i=0;i<data.sid.length;i++){
                   var o= document.createElement("option");
                        o.setAttribute("value",data.sid[i]);
                        o.innerText = data.sid[i];
                    root.appendChild(o);
                    form.render('select')
                }
            }else {
                $('#no').html(' <span class="x-red" id="span1">学生不存在</span>');
                document.getElementById("stuId").options.length=0;
                form.render();
            }
        })
    });
    layui.use(['upload','form'], function() {
        $ = layui.jquery;
        form = layui.form
            , layer = layui.layer;

        //自定义验证规则
        form.verify({
            pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,scoree:function (value) {
                if($('#score').val()<0){
                    return '分数不能为负数';
                }
                if($('#score').val()>100)           {
                    return '分数不能超过100';
                }
            }
            , repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });
        var $ = layui.jquery
            , upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            , url: '/pic/upload'
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.error > 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
                $('#teaImg').val(res.url);
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });


        //监听提交
        form.on('submit(add)', function(data){
            $.ajax({
                url: data.form.action,
                type: data.form.method,
                data: $(data.form).serialize(),
                success: function (info) {
                    if (info.code === 200) {
                        layer.alert("增加成功", {icon: 6},function () {
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
