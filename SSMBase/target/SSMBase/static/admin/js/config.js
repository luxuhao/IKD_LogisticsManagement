//用来获取根路径下的项目名  2020-06-10 lua
var pathName = window.document.location.pathname;
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);

var config = {

    //调试模式 (开启后左侧菜单不缓存,菜单收展失效,打开渲染速度会变慢)
    debug: true,

    //网站名称 (左上角显示的文字LOGO)
    webname: '物流管理',

    //菜单列表路径 (可以是本地json,也可以是api接口)
    menuUrl: projectName+'/getmenu',

    //layer全局提示层
    layerMsg: {
        offset: 't', //坐标 (详细说明 https://www.layui.com/doc/modules/layer.html#offset)
        shade: [0.4, '#000'] //遮罩 (详细说明 https://www.layui.com/doc/modules/layer.html#shade)
    }
}

