function getProduct(productName) {
//    var xhttp = new XMLHttpRequest();
    console.log(productName);
//    xhttp.open("GET", "http://localhost:8084/Crawling_XML/gameDetailServlet?productName=" + productName, false);
//    xhttp.send();
    window.location.replace("http://localhost:8084/Crawling_XML/gameDetailServlet?productName=" + productName);
}


