<%-- 
    Document   : detail
    Created on : Jul 6, 2018, 9:03:07 AM
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
        <link rel="stylesheet" href="detail.css" type="text/css">
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

            <!-- from this, using detail.css -->

            <!-- game title -->
            <div class="game-des">
                <h1>
                    <c:out value="${requestScope.NAME}" />
                </h1>
            </div>

            <!-- game content -->
            <c:set var="product" value="${requestScope.PRODUCT}" />
            <c:if test="${not empty product}">
                <div class="game-detail">
                    <div id="gamePicture" class="game-picture">
                        <!-- <div>
                            <img src="img/game/csgo.jpg" alt="">
                        </div>
                        <div class="game-price">
                            <h1>140,000 VNĐ</h1>
                        </div>
                        <div class="link-price-container">
                            <div class="link-price">
                                153.450 VNĐ
                            </div>
                            <div class="link-page">
                                <img src="img/wongstore.png" alt="">
                                <a href="">Wongstore.com</a>
                            </div>
                        </div>
                        <div class="link-price-container">
                            <div class="link-price underdog">
                                140.000 VNĐ
                            </div>
                            <div class="link-page underdog">
                                <img src="img/divineshop.png" alt="">
                                <a href="">Divineshop.vn</a>
                            </div>
                        </div> -->
                    </div>
                </div>
                <script>
                    var product = '${product}';
                    var parser = new DOMParser();
                    var xmlDoc = parser.parseFromString(product, "text/xml");
                    var productXMLString = xmlDoc.getElementsByTagName("productList")[0];
                    var avaUrl = productXMLString.getElementsByTagName("avaUrl")[0].childNodes[0].textContent;
                    var cheapestPrice = productXMLString.getElementsByTagName("cheapestPriceString")[0].childNodes[0].textContent;
                    var prices = productXMLString.getElementsByTagName("prices");

                    var gamePicture = document.createElement("div");
                    gamePicture.className = "game-picture";

                    var imgDisplay = document.createElement("div");
                    var imgDisplayInside = document.createElement("img");
                    imgDisplayInside.src = avaUrl;
                    imgDisplay.appendChild(imgDisplayInside);

                    gamePicture.appendChild(imgDisplay);

                    var gamePrice = document.createElement("div");
                    gamePrice.className = "game-price";
                    var boldPrice = document.createElement("h1");
                    boldPrice.textContent = cheapestPrice;
                    gamePrice.appendChild(boldPrice);
                    gamePicture.appendChild(gamePrice);

                    for (var i = 0; i < prices.length; i++) {

                        var creditName = prices[i].getElementsByTagName("creditName")[0].childNodes[0].textContent;
                        var price = prices[i].getElementsByTagName("priceString")[0].childNodes[0].textContent;
                        var href = prices[i].getElementsByTagName("href")[0].childNodes[0].textContent;

                        var linkPriceContainer = document.createElement("div");
                        linkPriceContainer.className = "link-price-container";

                        var linkPrice = document.createElement("div");
                        linkPrice.className = "link-price";
                        linkPrice.textContent = price;
                        linkPriceContainer.appendChild(linkPrice);

                        var linkPage = document.createElement("div");
                        linkPage.className = "link-page";
                        var aCreditName = document.createElement("a");
                        aCreditName.href = href;
                        aCreditName.textContent = creditName;
                        linkPage.appendChild(aCreditName);
                        linkPriceContainer.appendChild(linkPage);

                        gamePicture.appendChild(linkPriceContainer);

                    }

                    var gameDetail = document.getElementById("gamePicture");
                    gameDetail.appendChild(gamePicture);
                    console.log(product);
                </script>
            </c:if>
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
    </body>

</html>