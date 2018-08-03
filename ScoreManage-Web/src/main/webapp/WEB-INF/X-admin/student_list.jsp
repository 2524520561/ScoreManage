<%--
  Created by IntelliJ IDEA.
  User: zsq
  Date: 2018/7/10
  Time: 19:30
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
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" action="/scoreManage/onStudent_list" method="post">
            <input type="text" name="name"   placeholder="请输入姓名" value="${name}" autocomplete="off" class="layui-input">
            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加学生','/scoreManage/addStudent')"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：${student.total} 条</span>
    </xblock>
    <div>
        <table class="layui-table">
            <thead>
            <tr>
                <th>
                    <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
                </th>
                <th>学号</th>
                <th>姓名</th>
                <th>学院</th>
                <th>电话</th>
                <th>性别</th>
                <th>状态</th>
                <th>操作</th>
            </thead>
            <tbody>
            <c:forEach items="${student.rows}" var="stu">
                <tr>
                    <td>
                        <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${stu.stuId}'><i class="layui-icon">&#xe605;</i></div>
                    </td>
                    <td>${stu.stuId }</td>
                    <td>${stu.name }</td>
                    <td>${stu.depart }</td>
                    <td>${stu.phone }</td>
                    <td>${stu.sex eq 1 ? '男' : '女' }</td>
                    <td>${stu.status  eq 1 ? '离校' : '在校'}</td>
                    <td class="td-manage">
                        <a onclick="member_stop(this,'10001')" href="javascript:;"  title="启用">
                            <i class="layui-icon">&#xe601;</i>
                        </a>
                        <a title="编辑"  onclick="x_admin_show('编辑','/scoreManage/stu_edit?id=${stu.stuId}')" href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>
                        <a title="删除" onclick="member_del(this,'${stu.stuId}')" href="javascript:;">
                            <i class="layui-icon">&#xe640;</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%--<form id="pageForm" action="">
        <input type="hidden" name="name"  placeholder="请输入用户名" value="${name}">
        <input type="hidden" id="pageNum" name="pageNum">
        <div class="page">
            当前第<font color="red">${student.currentPage} </font>页/共<font color="green">${student.paages} </font>页
            <div>
                <a class="num" href="javascript:void(0)" onclick="jumpPage(1)">首页</a>
                <a class="num" href="javascript:void(0)" onclick="jumpPage(${(student.currentPage-1>0)?(student.currentPage-1):1})">上一页</a>
                <a class="num" href="javascript:void(0)" onclick="jumpPage(${(student.currentPage+1)>student.paages?student.paages:(student.currentPage+1)})">下一页</a>
                <a class="num" href="javascript:void(0)" onclick="jumpPage(${student.paages})">尾页</a>
            </div>
        </div>
    </form>--%>
    <div class="page">
        <div>
            <a class="num" href="/scoreManage/onStudent_list?pageNum=1&&name=${name}">首页</a>
            <a class="prev" href="/scoreManage/onStudent_list?pageNum=${(student.currentPage-1>0)?(student.currentPage-1):1}&& name=${name}">&lt;&lt;</a>
            <c:forEach begin="1" end="${student.paages}" var="i">
                <a class="num" href="/scoreManage/onStudent_list?pageNum=${i}&&name=${name}">
                    <c:choose>
                        <c:when test="${student.currentPage==i}">
                            <span class="current">${i}</span>
                        </c:when>
                        <c:otherwise>
                            ${i}
                        </c:otherwise>
                    </c:choose>
                </a>
            </c:forEach>
            <a class="next" href="/scoreManage/onStudent_list?pageNum=${(student.currentPage+1)>student.paages?student.paages:(student.currentPage+1)}&& name=${name}">&gt;&gt;</a>
            <a class="num" href="/scoreManage/onStudent_list?pageNum=${student.paages}&&name=${name}">尾页</a>
        </div>
    </div>

</div>

<%--<script type="text/javascript">
    function jumpPage(pages) {
        $("#pageNum").val(pages);
        $("#pageForm").attr({"action":"/scoreManage/onStudent_list","method":"post"});
        $("#pageForm").submit();

    }

</script>--%>
<script>

    /*用户-停用*/
    function member_stop(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {

            if ($(obj).attr('title') == '启用') {

                //发异步把用户状态进行更改
                $(obj).attr('title', '停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!', {icon: 5, time: 1000});

            } else {
                $(obj).attr('title', '启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!', {icon: 5, time: 1000});
            }

        });
    }

    /*用户-删除*/
    function member_del(obj, id) {
        var ids = [];
        ids.push(id);
        layer.confirm('确认要删除吗？', function (index) {
            if (index){

                var params = {"adds":ids};
                $.post("/scoreManage/deleteStuAll",params, function(data){
                    if(data.code == 200) {
                        layer.msg('删除成功', {icon: 1});
                        window.location.href="/scoreManage/onStudent_list";
                    }else{
                        layer.msg('删除失败', {icon: 1});
                    }
                });
            }
        });
    }


    function delAll(argument) {

        var data = tableCheck.getData();
        var ids = [];
        for(var i in data){

            ids.push(data[i]);
        }
        ids = ids.join(",");

        layer.confirm('确认要删除吗？' + ids, function (index) {

            //捉到所有被选中的，发异步进行删除
            if (index){

                var params = {"adds":data};
                $.post("/scoreManage/deleteStuAll",params, function(data){
                    if(data.code == 200) {
                        layer.msg('删除成功', {icon: 1});
                        window.location.href="/scoreManage/onStudent_list";
                    }else {
                        layer.msg('删除失败', {icon: 1});
                    }
                });
            }

        });
    }

</script>
<script>var _hmt = _hmt || []; (function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</body>

