var filter;

function getFilter(filterId, filterName) {
    filter = filterId;
    document.getElementById("filter").innerHTML = filterName;
}

function filterSearch() {
    var txtSearch = document.getElementById("inputFilter");
    var xhr = new XMLHttpRequest();
    if (filter != null) {
        xhr.open("GET", "searchServlet?txtFilter=" + filter + "&txtSearch=" + txtSearch.value, true);
        xhr.send();
        xhr.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.body.innerHTML = this.responseText;
            }
        };
    } else {
        xhr.open("GET", "searchServlet?" + "&txtSearch=" + txtSearch.value, true);
        xhr.send();
        xhr.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.body.innerHTML = this.responseText;
            }
        };
    }

}



