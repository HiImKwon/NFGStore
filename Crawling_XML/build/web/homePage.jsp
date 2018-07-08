<%-- 
    Document   : homePage
    Created on : Jul 6, 2018, 9:08:56 AM
    Author     : Bui Quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>NFG Shop</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
              crossorigin="anonymous">
        <link rel="stylesheet" href="style.css" type="text/css">
    </head>

    <body>
        <div class="div-body">



            <%--
            <c:out value="${categoriesXmlString}"/>
            --%>

            <!-- drop down bar -->
            <div class="top-nav container">
                <div class="child">
                    <img src="logo/logo.png" alt="NFG Shop">
                </div>
                <div class="child">
                    <button class="dropbtn">Thể loại
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div id="categoryList" class="dropdown-content">
                        <c:set var="categoriesXmlString" value="${sessionScope.CATEGORIES}"/>
                        <c:import charEncoding="UTF-8" url="category-list.xsl" var="categoryDoc"/>
                        <x:transform doc="${categoriesXmlString}" xslt="${categoryDoc}"/>
                    </div>
                </div>
                <div class="child">
                    <button class="dropbtn">Nhà phát hành
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content">
                        <a href="#">Origin</a>
                        <a href="#">Uplay</a>
                        <a href="#">Steam</a>
                        <a href="#">Battle.net</a>
                    </div>
                </div>
                <div class="child">
                    <button class="dropbtn">Wallet code
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content">
                        <a href="#">Steam code wallet 5$</a>
                        <a href="#">Steam code wallet 10$</a>
                        <a href="#">Steam code wallet 20$</a>
                        <a href="#">Steam code wallet 50$</a>
                        <a href="#">Steam code wallet 15$</a>
                        <a href="#">Steam code wallet 100$</a>
                        <a href="#">Steam Code Random Game</a>
                    </div>
                </div>
                <div class="child">
                    <div>
                        Crawl:
                    </div>
                    <div>
                        <form action="thread.jsp">
                            <button>
                                Click here
                            </button>
                        </form>
                    </div>
                    <!-- <input type="text" placeholder="Tìm kiếm">
                        <button type="submit"><i class="fas fa-search"></i></button> -->
                </div>
            </div>

            <!-- bar -->
            <div class="trending">
                <div>
                    <div class="logo">
                        <img class="image" src="logo/heart.svg" alt="Trustworthy">
                    </div>
                    <div>
                        <div class="big-title">
                            bán chạy
                        </div>
                        <div class="small-title">
                            trong một giờ
                        </div>
                    </div>
                </div>
                <div>
                    <div class="logo">
                        <img class="image" src="logo/coins.svg" alt="CheapGames">
                    </div>
                    <div>
                        <div class="big-title">
                            Game giá rẻ
                        </div>
                        <div class="small-title">
                            game tốt giá tốt
                        </div>
                    </div>
                </div>
                <div>
                    <div class="logo">
                        <img class="image" src="logo/new.svg" alt="News">
                    </div>
                    <div>
                        <div class="big-title">
                            mới update
                        </div>
                        <div class="small-title">
                            cập nhật
                        </div>
                    </div>
                </div>
                <div>
                    <div class="logo">
                        <img class="image" src="logo/sale.svg" alt="Sales">
                    </div>
                    <div>
                        <div class="big-title">
                            đang sale
                        </div>
                        <div class="small-title">
                            lên đến 90%
                        </div>
                    </div>
                </div>
            </div>

            <!-- search bar -->
            <div class="search">
                <div class="drop-down">
                    <button class="btn">
                        Bộ lọc
                        <i class="fa fa-caret-down"></i>
                    </button>
                </div>
                <input id="inputFilter" type="text" placeholder="Tìm game bạn muốn xem giá ....">
                <button id="btnFilter">Tìm kiếm</button>
            </div>

            <!-- slide show -->
            <div class="slide-show">
                <img class="mySlideImg" src="img/0eVoz_ok1.jpg" alt="" />
                <img class="mySlideImg" src="img/2gmab_XRZ1Y_coop.png" alt="" />
                <img class="mySlideImg" src="img/RlzLt_Counter-Strike_Global_Offensive.png" alt="" />
                <img class="mySlideImg" src="img/uqCUo_tf_logo.png" alt="" />
                <img class="mySlideImg" src="img/garena-banner-824x470.png" alt="">
                <img class="mySlideImg" src="img/mFqK5_2151.png" alt="">
            </div>

            <!-- sort bar -->
            <div class="sort-bar">
                <div class="title-icon">
                    <div class="sort-bar-title">
                        <b>GAME MỚI UP</b>
                    </div>
                    <div class="icon">
                        <img src="logo/light-bulb.png" alt="">
                    </div>
                </div>
<!--                <div class="more-info">
                    Xem thêm
                </div>-->
            </div>

            <!-- update-items -->
            <div class="update-items">
                <div class="update-row-one">
                    <c:set var="firstProductXMLString" value="${requestScope.FIRSTPRODUCTS}"/>
                    <c:import charEncoding="UTF-8" url="products-top-list.xsl" var="proDoc"/>
                    <x:transform doc="${firstProductXMLString}" xslt="${proDoc}"/>
                </div>
                <%--
                <div class="update-row-two">
                    <div>
                        <div class="img">
                            <img src="img/game/l4d.jpg" alt="">
                        </div>
                        <div class="price">
                            180.000 VNĐ
                        </div>
                    </div>
                    <div>
                        <div class="img">
                            <img src="img/game/dst.jpg" alt="">
                        </div>
                        <div class="price">
                            158.000 VNĐ
                        </div>
                    </div>
                    <div>
                        <div class="img">
                            <img src="img/game/rl.jpg" alt="">
                        </div>
                        <div class="price">
                            180.000 VNĐ
                        </div>
                    </div>
                    <div>
                        <div class="img">
                            <img src="img/game/ros.jpg" alt="">
                        </div>
                        <div class="price">
                            43.000 VNĐ
                        </div>
                    </div>
                </div>
                --%>
            </div>

            <!-- sort bar -->
            <div class="sort-bar">
                <div class="title-icon">
                    <div class="sort-bar-title">
                        <b>GAME HOT</b>
                    </div>
                    <div class="icon">
                        <img src="logo/fire.png" alt="">
                    </div>
                </div>
<!--                <div class="more-info">
                    Xem thêm
                </div>-->
            </div>

            <!-- update-items -->
            <div class="update-items">
                <div class="update-row-one">
                    <c:set var="secondProductXMLString" value="${requestScope.SECONDPRODUCTS}"/>
                    <c:import charEncoding="UTF-8" url="products-top-list.xsl" var="proDoc"/>
                    <x:transform doc="${secondProductXMLString}" xslt="${proDoc}"/>
                </div>
                <%--
                <div class="update-row-two">
                    <div>
                        <div class="img">
                            <img src="img/game/raft.jpg" alt="">
                        </div>
                        <div class="price">
                            180.000 VNĐ
                        </div>
                    </div>
                    <div>
                        <div class="img">
                            <img src="img/game/w3.jpg" alt="">
                        </div>
                        <div class="price">
                            362.700 VNĐ
                        </div>
                    </div>
                    <div>
                        <div class="img">
                            <img src="img/game/tf2.jpg" alt="">
                        </div>
                        <div class="price">
                            220.000 VNĐ
                        </div>
                    </div>
                    <div>
                        <div class="img">
                            <img src="img/game/forest.jpg" alt="">
                        </div>
                        <div class="price">
                            180.000 VNĐ
                        </div>
                    </div>
                </div>
                --%>
            </div>
        </div>
        <!-- end -->
        <div class="ending-div">
            <div class="information">
                <b>Thông tin</b>
                <br/> This is not a commercial version.
                <br/> This project is dedicated to Dr. KhanhKT.
                <br/> PRX301's Assignment.
            </div>
            <div class="contact">
                <b>Liên hệ</b>
                <br/> QuanBKMSE62170
                <br/> Email: quanbkmse62170@fpt.edu.vn
                <br/> Address: FPT University HCM
            </div>
            <div class="end-logo">
                <img src="logo/logo2.png" alt="">
            </div>
        </div>
        <!-- copyright -->
        <div class="copyright">
            <p>© 2018 NFG's Store. Bản quyền nội dung thuộc quyền sở hửu của NFG's Store.</p>
        </div>
        <script src="slideshow.js"></script>
        <script src="js.js"></script>
    </body>

</html>
