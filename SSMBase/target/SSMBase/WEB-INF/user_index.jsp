<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String appContext= request.getContextPath();// 获得当前应用的根路径
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + appContext ;
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户列表</title>
    <link rel="stylesheet" href="<%=basePath%>/static/common/layui/css/layui.css">
    <link rel="stylesheet" href="<%=basePath%>/static/admin/css/style.css">
    <script src="<%=basePath%>/static/common/layui/layui.js"></script>
    <script src="<%=basePath%>/static/common/jquery-3.3.1.min.js"></script>
    <script src="<%=basePath%>/static/common/vue.min.js"></script>

</head>
<body>
<div id="app">
    <!--顶栏-->
    <div class="layui-layout layui-layout-admin">

        <div class="layui-header">
            <%@include file="index_head.jsp"%>
        </div>

    </div>>


    <div class="main" id="app">
        <!--左栏-->
        <div class="left">
            <ul class="cl" >
                <!--顶级分类-->
                <li v-for="vo,index in menu" :class="{hidden:vo.hidden}">
                    <a href="javascript:;"  :class="{active:vo.active}"  @click="onActive(index)">
                        <i class="layui-icon" v-html="vo.icon"></i>
                        <span v-text="vo.name"></span>
                        <i class="layui-icon arrow" v-show="vo.url.length==0">&#xe61a;</i> <i v-show="vo.active" class="layui-icon active">&#xe623;</i>
                    </a>
                    <!--子级分类-->
                    <div v-for="vo2,index2 in vo.list">
                        <a href="javascript:;" :class="{active:vo2.active}" @click="onActive(index,index2)" v-text="vo2.name"></a>
                        <i v-show="vo2.active" class="layui-icon active">&#xe623;</i>
                    </div>
                </li>
            </ul>
        </div>
        <!--右侧-->
        <div class="right">


            <div class="layui-row">
                <div class="layui-col-lg8">
                    <a href="?/admin/user/index/connect/qq.html" class="layui-btn">QQ</a>

                    <a href="?/admin/user/index/connect/weibo.html" class="layui-btn">微博</a>
                    <a href="?/admin/user/index/connect/weixin.html" class="layui-btn">微信</a>
                    <a href="?/admin/user/index/connect/alipay.html" class="layui-btn">支付宝</a>
                    <a href="?/admin/user/index/connect/xcx.html" class="layui-btn">小程序</a>
                </div>

                <div  class="layui-col-lg4" style="text-align: right">

                    <div class="layui-input-inline" style="width: 300px">
                        <input type="text" name="k" value="" placeholder="手机/用户名/邮箱/昵称" class="layui-input key">
                    </div>
                    <button type="button" class="layui-btn sou">搜索</button>
                </div>
            </div>

            <table class="layui-table layui-form"  id="operation_user">

            </table>

            <div class="page"><ul class="pagination"><li class="disabled"><span>&laquo;</span></li> <li class="active"><span>1</span></li><li><a href="?/admin/user/index.html?page=2">2</a></li><li><a href="?/admin/user/index.html?page=3">3</a></li><li><a href="?/admin/user/index.html?page=4">4</a></li><li><a href="?/admin/user/index.html?page=5">5</a></li><li><a href="?/admin/user/index.html?page=6">6</a></li><li><a href="?/admin/user/index.html?page=7">7</a></li><li><a href="?/admin/user/index.html?page=8">8</a></li><li class="disabled"><span>...</span></li><li><a href="?/admin/user/index.html?page=109">109</a></li><li><a href="?/admin/user/index.html?page=110">110</a></li> <li><a href="?/admin/user/index.html?page=2">&raquo;</a></li></ul></div>


        </div>
    </div>
</div>
<script src="<%=basePath%>/static/admin/js/config.js"></script>
<script src="<%=basePath%>/static/admin/js/script.js"></script>
<script>

    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#operation_user'
            ,height: 312
            ,url: '<%=basePath%>/user/select' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'user_id', title: '用户ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'username', title: '用户名', width:80}
                ,{field: 'password', title: '密码', width:80}
                ,{field: 'email', title: '邮箱', width:80}
                ,{field: 'phone', title: '手机号', width:80}
                ,{field: 'sex', title: '性别', width: 177}
                ,{field: 'age', title: '年龄', width: 80, sort: true}
                ,{field: 'status', title: '状态', width: 80, sort: true}
                ,{field: 'create_time', title: '注册时间', width: 80, sort: true}
                ,{field: 'update_time', title: '更改时间', width: 135}
                ,{field: 'last_login_time', title: '最后一次登陆时间', width: 135}
            ]]
        });

    });


</script>
</body>
</html>
