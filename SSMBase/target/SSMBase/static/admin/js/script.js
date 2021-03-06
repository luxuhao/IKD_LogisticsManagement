
// if(!/^http(s*):\/\//.test(location.href)){
//     alert('请先部署到 localhost 下再访问');
//     window.location.href = 'login.html';
// }
//用来获取根路径下的项目名  2020-06-10 lua
var pathName = window.document.location.pathname;
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);


layui.use('form', function () {
    var form = layui.form,
        layer = layui.layer;
});

var vue = new Vue({
    el:'#app',
    data:{
        webname:config.webname,
        menu:[],
        address:[]
    },
    created:function(){

        //加载左侧菜单
        if(config.debug){
            $.ajax({
                url: config.menuUrl,
                dataType: 'text',
                success:  (menu) => {
                    menu = eval('(' + menu + ')');
                    sessionStorage.menu = JSON.stringify(menu);
                    this.menu = menu;
                    this.thisActive();
                    this.thisAttr();
                }
            })
        }else {
            let data = sessionStorage.menu;
            if (!data) {
                $.ajax({
                    url: config.menuUrl,
                    dataType: 'text',
                    success: (menu) => {
                        menu = eval('(' + menu + ')');
                        sessionStorage.menu = JSON.stringify(menu);
                        this.menu = menu;
                        this.thisActive();
                        this.thisAttr();
                    }
                })
            } else {
                this.menu = JSON.parse(data);
                this.thisActive();
                this.thisAttr();
            }
        }
    },
    methods:{
        //记住收展
        onActive:function (pid,id=false) {
            let data;
            if(id===false){

                data = this.menu[pid];

                if(data.url.length>0){
                    this.menu.forEach((v,k)=>{
                        v.active = false;
                        v.list.forEach((v2,k2)=>{
                            v2.active = false;
                        })
                    })

                    data.active = true;

                }

                data.hidden = !data.hidden;

            }else{

                this.menu.forEach((v,k)=>{
                    v.active = false;
                    v.list.forEach((v2,k2)=>{
                        v2.active = false;
                    })
                })

                data = this.menu[pid].list[id];
            }
            this.updateStorage();
            if(data.url.length>0){
                if(data.target){
                    if(data.target=='_blank'){

                        window.open(projectName+data.url);


                    }else{
                        window.location.href = projectName+data.url;
                        //$("#iframeMain").attr("src",projectName+data.url);

                    }

                }else{
                    window.location.href = projectName+data.url;
                    //$("#iframeMain").attr("src",projectName+data.url);
                }

            }
        },

        //更新菜单缓存
        updateStorage(){
            sessionStorage.menu = JSON.stringify(this.menu);
        },
        //菜单高亮
        thisActive:function(){
            let pathname = window.location.pathname;

            let host = window.location.host;
            let pid = false;
            let id = false;
            this.menu.forEach((v,k)=>{
                let url = v.url;
                if(url.length>0){
                    if(url[0]!='/' && url.substr(0,4)!='http'){
                       url = '/'+url;
                    }


                }
                if(pathname==url){
                    pid = k;
                }
                v.list.forEach((v2,k2)=>{
                    let url = v2.url;

                    if(url.length>0){
                        if(url[0]!='/' && url.substr(0,4)!='http'){
                           url =pathname.substring(0, pathname.substr(1).indexOf('/')+1)+ '/'+url;
                        }
                       // url = projectName+url;
                       // $("#iframeMain").attr("src",url);
                    }
                    if(pathname==url){
                        pid = k;
                        id = k2;
                    }

                })
            })


            if(id!==false){
                this.menu[pid].list[id].active = true;
            }else{
                if(pid!==false){
                    this.menu[pid].active = true;
                }
            }

            this.updateStorage();

        },
        //当前位置
        thisAttr:function () {
            //当前位置
            let address = [{
                name:'首页',
                url:'/manage/user/list.do'
            }];
            this.menu.forEach((v,k)=>{
                    v.list.forEach((v2,k2)=>{
                        if(v2.active){
                        address.push({
                            name:v.name,
                            url:'javascript:;'
                        })
                        address.push({
                            name:v2.name,
                            url:v2.url,
                        })
                        this.address = address;
                    }
                })
            })
        }
    }
})



//导航 依赖 element 模块，否则无法进行功能性操作 2020-06-11 lua add
layui.use('element', function(){
    var element = layui.element;

    //…
});



$(document).ready(function() {


    //删除
    $(".del").click(function () {
        var url = $(this).attr("href");
        var id = $(this).attr("data-id");

        layer.confirm('你确定要删除么?', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            $.get(url, function (data) {
                if (data.code == 1) {
                    $(id).fadeOut();
                    layer.msg(data.msg, {icon: 1});
                } else {
                    layer.msg(data.msg, {icon: 2});
                }
            });
        }, function () {
            layer.msg("您取消了删除!");
        });
        return false;
    });
})

function delCache(){
    sessionStorage.clear();
    localStorage.clear();
}

function msg(code=1,msg='',url='',s=3) {
    if(typeof code == 'object') {
        msg = code.msg;
        url = code.url || '';
        s = code.s || 3;
        code = code.code;
    }
    code = code==1 ? 1 : 2;
    layer.msg(msg, {icon: code,offset: config.layerMsg.offset || 't',shade: config.layerMsg.shade || [0.4, '#000']});
    if(url){
        setTimeout(function () {
            window.location.href = url;
       },s*1000);
    }
}








