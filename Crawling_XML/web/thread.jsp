<%-- 
    Document   : thread
    Created on : Jul 8, 2018, 4:21:59 AM
    Author     : Bui Quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>NFG Shop</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
              crossorigin="anonymous">
        <link rel="stylesheet" href="thread.css" type="text/css">
        <link rel="stylesheet" href="style.css" type="text/css">
    </head>
    <body>
        <div class="logo-on-top">
            <img src="../logo/logo2.png" />
        </div>
        <div class="text-field">
            PIN CODE:
            <input type="password" name="txtPin" />
        </div>
        <button class="button">
            Check!
        </button>
        <div class=toggle-button>
            <label class="switch">
                <input type="checkbox">
                <span class="slider"></span>
            </label>
        </div>
        <div class="description">
            <p>
                1. Crawling categories, then put those categories to DB.
            </p>
            <p>
                2. Crawling games based on categories, then put them to DB.
            </p>
            <p>
                3. Put game's price in first page to DB.
            </p>
            <p>
                4. Put game's categories in first page to DB.
            </p>
            <p>
                5. User game's name to search for game's price in second page. Then add it to DB.
            </p>
        </div>
        <button class="button">
            Home
        </button>
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
                <img src="../logo/logo.png" alt="">
            </div>
        </div>
        <!-- copyright -->
        <div class="copyright">
            <p>© 2018 NFG's Store. Bản quyền nội dung thuộc quyền sở hửu của NFG's Store.</p>
        </div>
        <script src="slideshow.js"></script>
    </body>
</html>
