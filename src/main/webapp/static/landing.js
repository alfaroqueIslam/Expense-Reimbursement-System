const baseUrl = "http://localhost:8080/project1-ers";

const requestsUrl = baseUrl + "/requests";
const createUrl = baseUrl + "/create";
const modifyUrl = baseUrl + "/modify";
const idRequests = requestsUrl + "?userId=";
const usersUrl = baseUrl + "/users";

function performAjaxGetRequest(url, callback) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            callback(xhr.response); // this is going to be the response body of our http response  (JSON)
        }
    }
    xhr.send();
}

function getAllRequests(callback) {
    performAjaxGetRequest(requestsUrl, callback);
}

function performAjaxPostRequest(url, payload, successCallback, failureCallback) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status > 199 && xhr.status < 300) {
                successCallback(xhr.response); // this is going to be the response body of our http response  (JSON)
            } else {
                if (failureCallback) {
                    failureCallback()
                } else {
                    console.error("An error occurred while attempting to create a new record")
                }
            }
        }
    }
    xhr.send(payload);
}

function getUserById(id, callback) {
    performAjaxGetRequest((usersUrl + "/" + id), callback);
}

function approveRequest() {
    let status = "approved"
    let id = document.getElementById("rId").value;
    let details = { status, id }
    performAjaxPostRequest(modifyUrl, JSON.stringify(details), handleSuccessfulApprove, handleUnsuccessfulApprove);
}

function handleSuccessfulApprove() {
    window.location.href = "landing.html";
    displayLoggedInUser();
}

function handleUnsuccessfulApprove() {
    console.log("Request unsuccessful");
    document.getElementById("error-msg-ad").hidden = false;
}

function denyRequest() {
    let status = "denied"
    let id = document.getElementById("rId").value;
    let details = { status, id }
    performAjaxPostRequest(modifyUrl, JSON.stringify(details), handleSuccessfulDeny, handleUnsuccessfulDeny);
}

function handleSuccessfulDeny() {
    window.location.href = "landing.html";
    displayLoggedInUser();
}

function handleUnsuccessfulDeny() {
    console.log("Request unsuccessful");
    document.getElementById("error-msg-ad").hidden = false;
}

function getResponse(r) {
    myObj = JSON.parse(r);
    var i = 0;
    displayLoggedInUser();
    var table = document.getElementById("table").getElementsByTagName('tbody')[0];
    const token = sessionStorage.getItem("token");
    // ex. 1:ADMIN
    const parsedToken = token.split(":");
    if (parsedToken[1] == "EMPLOYEE") {
        document.getElementById("request-form").hidden = false;
        for (let obj of myObj) {
            if (obj.userId == parsedToken[0]) {
                var row = table.insertRow(i);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                var cell7 = row.insertCell(6);
                cell1.innerHTML = obj.userId;
                cell2.innerHTML = obj.email;
                cell3.innerHTML = obj.requestId;
                cell4.innerHTML = "$" + obj.amount;
                cell5.innerHTML = obj.reason;
                cell6.innerHTML = obj.date;
                cell7.innerHTML = obj.status;
                i = i + 1;
            }
        }
    } else if (parsedToken[1] == "MANAGER") {
        document.getElementById("approve-deny").hidden = false;
        for (let obj of myObj) {
            if (obj.status == "pending") {
                var row = table.insertRow(i);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                var cell7 = row.insertCell(6);
                cell1.innerHTML = obj.userId;
                cell2.innerHTML = obj.email;
                cell3.innerHTML = obj.requestId;
                cell4.innerHTML = "$" + obj.amount;
                cell5.innerHTML = obj.reason;
                cell6.innerHTML = obj.date;
                cell7.innerHTML = obj.status;
                i = i + 1;
            }
        }
    }
}
