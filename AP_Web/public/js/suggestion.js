
function getSuggest() {
    console.log("i am Suggest");
    const items = document.getElementById("suggest");
    while (items.firstChild) {
        items.removeChild(items.firstChild);
    }
    let sug = new XMLHttpRequest();
    let url = "/gets/" + document.getElementById("query").value;
    console.log(url);
    sug.open("POST", url, [true]);
    sug.send(document.getElementById("query").value);
    sug.onload = function() {
        if (sug.status != 200) { // analyze HTTP status of the response
            //  alert(`Error ${sug.status}: ${sug.statusText}`); // e.g. 404: Not Found
        } else { // show the result
            // alert(`Done, got ${xhr.response.length} bytes`); // response is the server response
        }
    };


    sug.onprogress = function(event) {

        console.log(event["srcElement"]["responseText"])
        if (event.lengthComputable && event["srcElement"]["responseText"].length != 0) {
            var my_obj = JSON.parse(event["srcElement"]["responseText"]);
          
            for (var i = 0; i < my_obj.length && i < 5; i++) {
                var n = document.createElement("option");
                var nodetext = document.createTextNode(my_obj[i]["word"]);
                n.textContent = my_obj[i]["word"];
                n.value = my_obj[i]["word"];
                document.getElementById("suggest").appendChild(n);
            }
        } else {
           
        }
    }

    sug.onerror = () => {
        alert("failed");
    }
}