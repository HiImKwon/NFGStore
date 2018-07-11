<%-- 
    Document   : gameList
    Created on : Jul 7, 2018, 11:36:35 AM
    Author     : Bui Quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>NFG Shop</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
              crossorigin="anonymous">
        <link rel="stylesheet" href="style.css" type="text/css">
        <link rel="stylesheet" href="list.css" type="text/css">
    </head>
    <body>
        <div class="div-body">
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
                    <!--                    <button class="dropbtn">Nhà phát hành
                                            <i class="fa fa-caret-down"></i>
                                        </button>
                                        <div class="dropdown-content">
                                            <a href="#">Origin</a>
                                            <a href="#">Uplay</a>
                                            <a href="#">Steam</a>
                                            <a href="#">Battle.net</a>
                                        </div>-->
                </div>
                <div class="child">
                    <!--                    <button class="dropbtn">Wallet code
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
                                        </div>-->
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
                            update hoài
                        </div>
                        <div class="small-title">
                            cập nhật suốt
                        </div>
                    </div>
                </div>
                <div>
                    <div class="logo">
                        <img class="image" src="logo/sale.svg" alt="Sales">
                    </div>
                    <div>
                        <div class="big-title">
                             sale mãi
                        </div>
                        <div class="small-title">
                            lên đến 90%
                        </div>
                    </div>
                </div>
            </div>

            <!-- search bar -->
            <form action="searchServlet">
                <div class="search">
                    <div class="drop-down">
                        <button class="btn">
                            Bộ lọc
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <select name="txtFilter" class="dropdown-content">
                            <c:set var="categoriesXmlString" value="${sessionScope.CATEGORIES}"/>
                            <c:import charEncoding="UTF-8" url="filter-list.xsl" var="categoryDoc"/>
                            <x:transform doc="${categoriesXmlString}" xslt="${categoryDoc}"/>
                        </select>
                    </div>
                    <input id="inputFilter" name="txtSearch" type="text" placeholder="Tìm game bạn muốn xem giá ....">
                    <button type="submit" id="btnFilter">Tìm kiếm</button>
                </div>
            </form>

            <!-- category title -->
            <div class="sort-bar">
                <div class="title-icon">
                    <div class="sort-bar-title">
                        <b><c:out value="${requestScope.CATENAME}"/></b>
                    </div>
                    <div class="icon">
                        <img src="logo/light-bulb.png" alt="">
                    </div>
                </div>
                <!--                <button class="more-info">
                                    Xem thêm
                                </button>-->
            </div>

            <!-- list of products -->
            <div class="update-items">
                <div class="update-row-one">
                    <c:set var="firstProductXMLString" value="${requestScope.PRODUCTS}"/>
                    <c:import charEncoding="UTF-8" url="products-top-list.xsl" var="proDoc"/>
                    <x:transform doc="${firstProductXMLString}" xslt="${proDoc}"/>
                </div>
            </div>
            <form action="gameListServlet">
                <button class="more-info" type="submit" name="btAction" value="More info">
                    Xem thêm
                </button>
                <input type="hidden" name="txtPagination" value="${requestScope.PAGINATION}" />         
                <input type="hidden" name="category" value="${requestScope.category}" />
            </form>

        </div>
        <!-- end -->
        <div class="ending-div">
            <div class="information">
                <b>Thông tin</b>
                <br/> Đây không phải là phiên bản thương mại.
                <br/> Trang web này dành cho thầy KhanhKT.
                <br/> Bài tập môn PRX301.
            </div>
            <div class="contact">
                <b>Liên hệ</b>
                <br/> QuanBKMSE62170
                <br/> Email: quanbkmse62170@fpt.edu.vn
                <br/> Địa chỉ: Đại học FPT HCM
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
    </body>
</html>
