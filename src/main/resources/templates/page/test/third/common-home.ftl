<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>首页</title>
    <#include "/commonCss.ftl">

    <link rel="stylesheet" href="/static/third/private.css">
    <link rel="stylesheet" href="/static/third/list.css">
    <link rel="stylesheet" href="/static/third/common-home.css">
    <link rel="stylesheet" href="/static/third/footer.css">
</head>

<body>
<div class="common-home">
   <#include "/page/test/third/header_ad.ftl">

    <div class="layout1200">
       <#include "/page/test/third/common_search.ftl">
       <#include "/page/test/third/common_nav.ftl">
    </div>
    <!-- m2 -->
    <div class="banner-box-bg">

        <div class="layout1200 banner-bg">
            <!-- 翅膀 -->
            <#include "/page/test/third/common_wings.ftl">
            <!-- banner图 -->
            <#include "/page/test/third/common_banner.ftl">
            <#include "/page/test/third/common_left_bar.ftl">
            <#include  "/page/test/third/common_login.ftl">
        </div>
        <#include "/page/test/third/common_info.ftl">
    </div>
    <!-- 秒杀入口 -->
    <#include "/page/test/third/common_seackill.ftl">
    <#include  "/page/test/third/common_ad.ftl">
    <#include  "/page/test/third/common_img_floor.ftl">
    <#include  "/page/test/third/module_floor1.ftl">
    <#include  "/page/test/third/common_ad.ftl">
    <#include  "/page/test/third/module_floor2.ftl">



    <div class="layout1200">
        <#include "/page/test/third/common_hot_area.ftl">
        <div class="layout1200"><img src="/static/images/home/show-at2.jpg" alt=""></div>
        <#include "/page/test/third/common_new_recommend.ftl">
       <#--  <#include "/page/test/third/common_hot_category.ftl">-->
    </div>

    <div class="layout1200">
        <#include "/page/test/third/common_you_like.ftl">
    </div>
</div>
<div class="common-footer mt35" style="background:#fff;">
    <div class="layout1200">
        <ul class="clearfix footer-top">
            <li>
                <p><img src="/static/images/home/f1.png" alt=""><span class="section1">品质保障</span></p>
                <p class="section2">品质护航 购物无忧</p>
            </li>
            <li>
                <p><img src="/static/images/home/f2.png" alt=""><span class="section1">专业服务</span></p>
                <p class="section2">专业物流 准时送达</p>
            </li>
            <li>
                <p><img src="/static/images/home/f3.png" alt=""><span class="section1">轻松采购</span></p>
                <p class="section2">一件采购 便捷无忧</p>
            </li>
            <li>
                <p><img src="/static/images/home/f4.png" alt=""><span class="section1">品类齐全</span></p>
                <p class="section2">百万好药 等你来选</p>
            </li>
        </ul>
        <ul class="footer-bt clearfix">
            <li class="mr200">
                <dl>
                    <dt>帮助</dt>
                    <dd><a href="">买家注册</a></dd>
                    <dd><a href="">下单流程</a></dd>
                    <dd><a href="">商家入驻</a></dd>
                </dl>
            </li>
            <li class="mr115">
                <dl>
                    <dt>售后服务</dt>
                    <dd><a href="">退换货说明</a></dd>
                    <dd><a href="">投诉建议</a></dd>
                    <dd><a href="">客服电话：400-100-1023</a></dd>
                </dl>
            </li>
            <li>
                <dl>
                    <dt>关于我们</dt>
                    <dd><a href="">公司简介</a></dd>
                </dl>
            </li>
            <li class="logo-style">
                <img src="/static/images/home/logo2.png" alt="">
            </li>
        </ul>
    </div>
</div>


<#include "/commonJS.ftl">
<script src="/static/third/jquery.slide.js"></script>
<script>
    layui.use(['laypage', 'form'], function () {
        var laypage = layui.laypage;
        var form = layui.form;
        //执行一个laypage实例
        laypage.render({
            elem: 'productsPage' //注意，这里的 test1 是 ID，不用加 # 号
            ,
            count: 100 //数据总数，从服务端得到
            ,
            layout: ['prev', 'page', 'next', 'count', 'skip'],
            jump: function (obj) {
                // console.log(obj)
            }
        });
        form.on('checkbox(yes)', function (data) {
            // console.log(data.elem); //得到checkbox原始DOM对象
            console.log(data.elem.checked); //是否被选中，true或者false
            // console.log(data.value); //复选框value值，也可以通过data.elem.value得到
            // console.log(data.othis); //得到美化后的DOM对象
        });
    });
    $(function () {
        // 首页地区选择
        $('.map-index').click(function () {
            $('.cover').show(0);
            $('.site-box').show(0)
        })
        $('.close-btn').click(function () {
            $(this).parents('.site-box').hide();
            $('.cover').hide();
        })
        var num;
        $('.site-choose-cont').click(function () {
            $('.site-choose-cont').removeClass('current');
            $(this).addClass('current')
            num = $(this).index('.site-choose-cont');
            $('.scoll-box').find('ul li').removeClass('current')
            $('.scoll-box').find('ul li').eq(num).addClass('current')
            var top = $('.scoll-box').find('ul li').eq(num).position().top + $('.scoll-box ul')
                    .scrollTop()
            $('.scoll-box ul').stop().animate({
                scrollTop: top
            })
        })
        $('.jt').hover(function () {
            $(this).addClass('current')
        }, function () {
            $(this).removeClass('current')
        })

        $('.classify > li').hover(function () {
            $(this).find('em').addClass('current')
        }, function () {
            $(this).find('em').removeClass('current')
        })

        // 首页banner轮播
        layui.use('carousel', function () {
            var carousel = layui.carousel;
            //建造实例
            carousel.render({
                elem: '#banner',
                width: '100%',
                height: '360px',
                arrow: 'none',
            });
        });


        // 首页秒杀滚动
        $(".m_activ_goods").imgSlide({
            body: ".m_ss_list",
            prev: ".u_arrow_lf",
            next: ".u_arrow_rt",
            minCount: 5,
            stepCount: 5,
            auto: false
        });

        // 首页banner滚动
        $('.m_banner_slider').imgSlide({
            body: ".m_banner_list",
            prev: ".u_slider_lf",
            next: ".u_slider_rt",
            minCount: 1,
            stepCount: 1,
        });

        // 秒杀进度条计算
        $('.m_ss_list').children('li').each(function (index, element) {
            var oPercent = $(element).find('.u_progress_bar').data('percent');
            $(element).find('.u_progress_bar_color').css("width", oPercent);
        });

        // 首页广告关闭
        $('#close_gg').click(function (e) {
            e.preventDefault();
            var oh = $('.m_indextop_gg').height();
            if (oh > 0) {
                $(this).parents('.m_indextop_gg').animate({
                    height: '0'
                });
            } else {
                $(this).parents('.m_indextop_gg').animate({
                    height: '90px'
                });
            }

        });

        // 首页新banner
        var aa = 0;
        var size = $(".banner_select>ul li").size(); //5
        var img_size = $(".m_banner_slider>ul li").size(); //5
        for(var i = 0; i <= size - 1; i++) {
            var banSelect = $(".banner_select>ul li")[i];
            $(banSelect).attr('data-id',i);
            var banImg = $(".m_banner_slider>ul li")[i];
            $(banImg).attr('data-id',i);
        }

        $(".banner_select>ul li").on('click', function () {
            aa = parseInt($(this).attr("data-id"));
            if (aa<2) {
                $(".banner_select>ul").animate({'left':'0'});
            }else{
                if ((img_size-aa)>2) {
                    $(".banner_select>ul").animate({'left':-114*(aa-2)});
                }
            }
            $(".banner_select>ul li").eq(aa).addClass("on").siblings().removeClass("on");
            $(".m_banner_slider>ul li").eq(aa).fadeIn(200).siblings().fadeOut(200);
        });

        function move() {
            $(".banner_select>ul li").eq(aa).addClass("on").siblings().removeClass("on");
            if (aa<2) {
                $(".banner_select>ul").animate({'left':'0'});
            }else{
                if ((img_size-aa)>2) {
                    $(".banner_select>ul").animate({'left':-114*(aa-2)});
                }
            }
            $(".m_banner_slider>ul li").eq(aa).fadeIn(200).siblings().fadeOut(200);
        }

        var t = setInterval(lunbo, 2000);

        function lunbo() {
            if(aa == img_size) {
                aa = 0;
            }
            move();
            aa++
        }
        $(".m_banner_slider").hover(function() {
            clearInterval(t);
        }, function() {
            t = setInterval(lunbo, 2000);
        });
        $(".u_ban_pre").click(function() {
            if(aa <= 0) {
                aa = img_size;
                $(".banner_select>ul").animate({'left':-114*(img_size-5)});

            }
            aa = aa - 1;
            move();

        })
        $(".u_ban_next").click(function() {
            if(aa == (img_size-1)) {
                aa = -1;
                $(".banner_select>ul").animate({'left':'0'});
            }
            console.log(aa);
            aa = aa + 1;
            move();
        })



    })
</script>
</body>

</html>