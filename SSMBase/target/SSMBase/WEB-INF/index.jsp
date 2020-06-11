<!--主界面，登陆后获取相对应权限-->
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <title>后台首页</title>
    <link rel="stylesheet" href="<%=basePath%>/static/common/layui/css/layui.css">
    <link rel="stylesheet" href="<%=basePath%>/static/admin/css/style.css">
    <script src="<%=basePath%>/static/common/layui/layui.js"></script>
    <script src="<%=basePath%>/static/common/jquery-3.3.1.min.js"></script>
    <script src="<%=basePath%>/static/common/vue.min.js"></script>
    <script src="<%=basePath%>/static/common/echarts.min.js"></script>
    <style>
        .right h2{
            margin: 10px 0;
        }
        .right li{
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<div id="app">
    <!--顶栏-->
    <div class="layui-layout layui-layout-admin">

        <div class="layui-header">
            <div class="layui-logo"> <h1 v-text="webname"></h1></div>
<%--            <i class="layui-icon">&#xe715;</i>--%>

<%--                <li v-for="vo in address">--%>
<%--                    <a  v-text="vo.name" :href="vo.url" ></a> <span>/</span>--%>
<%--                </li>--%>
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="">控制台</a></li>
                <li class="layui-nav-item"><a href="">商品管理</a></li>
                <li class="layui-nav-item"><a href="">用户</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">其它系统</a>
                    <dl class="layui-nav-child">
                        <dd><a href="">邮件管理</a></dd>
                        <dd><a href="">消息管理</a></dd>
                        <dd><a href="">授权管理</a></dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="<%=basePath%>/static/admin/img/人员管理.png" class="layui-nav-img">
                        卢旭浩
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="">基本资料</a></dd>
                        <dd><a href="">安全设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">退了</a></li>
            </ul>





        </div>

    </div>>

    <div class="main" id="app">
        <!--左栏-->
        <div class="left">
            <ul class="cl" >
                <!--顶级分类-->
                <li v-for="vo,index in menu" :class="{hidden:vo.hidden}">
                    <a href="javascript:;" :class="{active:vo.active}" @click="onActive(index)">
                        <i class="layui-icon" v-html="vo.icon" ></i>
                        <span v-text="vo.name"></span>
                        <i class="layui-icon arrow" v-show="vo.url.length==0">&#xe61a;</i> <i v-show="vo.active" class="layui-icon active">&#xe623;</i>
                    </a>
                    <!--子级分类  target 指向iframe-->
                    <div v-for="vo2,index2 in vo.list">
                        <a href="javascript:;" :class="{active:vo2.active}" @click="onActive(index,index2)" v-text="vo2.name"  ></a>
                        <i v-show="vo2.active" class="layui-icon active">&#xe623;</i>
                    </div>
                </li>
            </ul>
        </div>

        <!--右侧iframe-->
        <div class="right">

        <div class="layui-body">

        </div>

        </div>


    </div>
</div>
<script src="<%=basePath%>/static/admin/js/config.js"></script>
<script src="<%=basePath%>/static/admin/js/script.js"></script>
<script type="text/javascript">




// $(document).ready(function(){
    //     $("a").click(function (e) {
    //         e.preventDefault();
    //         $("#iframeMain").attr("src",$(this).attr("href"));
    //     });
    // });

    $(function () {
        // $.ajax({
        //     url:"/manage/user/countloginer.do",
        //     type:"get",
        //     // dataType:"json",
        //     // async:false,
        //     success:function (e) {
        //         var date = []
        //         var count = []
        //         $(e).each(function (index,element) {
        //             date[index] = element.createDate
        //             count[index] = element.countuser
        //         })
        //         // 基于准备好的dom，初始化echarts实例
        //         var myChart = echarts.init(document.getElementById('main'));
        //         // 指定图表的配置项和数据
        //         var option = {
        //             title: {
        //                 text: '注册人数'
        //             },
        //             tooltip: {},
        //             legend: {
        //                 data:['人数']
        //             },
        //             xAxis: {
        //                 data: date
        //             },
        //             yAxis: {},
        //             series: [{
        //                 name: '人数',
        //                 type: 'bar',
        //                 data: count
        //             }]
        //         };
        //         // 使用刚指定的配置项和数据显示图表。
        //         myChart.setOption(option);
        //     }
        // })
        //


        // 基于准备好的dom，初始化echarts实例
       //  var myChart = echarts.init(document.getElementById('main2'));
       // var option = {
       //      legend: {},
       //      tooltip: {},
       //      dataset: {
       //          dimensions: ['product', '2015', '2016', '2017'],
       //          source: [
       //              {product: '手机', '2015': 43.3, '2016': 85.8, '2017': 93.7},
       //              {product: '服装', '2015': 83.1, '2016': 73.4, '2017': 55.1},
       //              {product: '家电', '2015': 86.4, '2016': 65.2, '2017': 82.5},
       //              {product: '动漫', '2015': 72.4, '2016': 53.9, '2017': 39.1}
       //          ]
       //      },
       //      xAxis: {type: 'category'},
       //      yAxis: {},
       //      // Declare several bar series, each will be mapped
       //      // to a column of dataset.source by default.
       //      series: [
       //          {type: 'bar'},
       //          {type: 'bar'},
       //          {type: 'bar'}
       //      ]
       //  };
       //  // 使用刚指定的配置项和数据显示图表。
       //  myChart.setOption(option);

        // $.get(
        //     "/manage/product/toprolist.do",
        //     function (e){
        //         var proName = []
        //         var count = []
        //         $(e).each(function (index,element) {
        //             proName[index] = element.name
        //             count[index] = element.stock
        //             console.log(proName[index])
        //             console.log(count[index])
        //         })
        //         var proN = []
        //         for (var i = 0; i <5 ; i++) {
        //             proN[i]=proName[i]
        //         }
        //         // 基于准备好的dom，初始化echarts实例
        //         var myChart = echarts.init(document.getElementById('main3'));
        //         option = {
        //             title : {
        //                 text: '各商品库存',
        //                 subtext: '纯属虚构',
        //                 x:'center'
        //             },
        //             tooltip : {
        //                 trigger: 'item',
        //                 formatter: "{a} <br/>{b} : {c} ({d}%)"
        //             },
        //             legend: {
        //                 orient: 'vertical',
        //                 left: 'left',
        //                 data:proName
        //                     // ['手机','家电','服装','动漫','其他']
        //             },
        //             series : [
        //                 {
        //                     name: '访问来源',
        //                     type: 'pie',
        //                     radius : '55%',
        //                     center: ['50%', '60%'],
        //                     data:[
        //                         {value:335, name:'其他'},
        //                         {value:310, name:'动漫'},
        //                         {value:234, name:'服装'},
        //                         {value:135, name:'家电'},
        //                         {value:1548, name:'手机'}
        //                     ],
        //                     itemStyle: {
        //                         emphasis: {
        //                             shadowBlur: 10,
        //                             shadowOffsetX: 0,
        //                             shadowColor: 'rgba(0, 0, 0, 0.5)'
        //                         }
        //                     }
        //                 }
        //             ]
        //         };
        //         // 使用刚指定的配置项和数据显示图表。
        //         myChart.setOption(option);
        //     }
        // )

    })

</script>
</body>
</html>

