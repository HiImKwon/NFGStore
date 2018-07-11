function filterSearch(var filterId) {
    var txtSearch = document.getElementById("inputFilter");
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "searchServlet", true);
    xhr.send("txtFilter=" + filterId + "&txtSearch=" + txtSearch);
}


