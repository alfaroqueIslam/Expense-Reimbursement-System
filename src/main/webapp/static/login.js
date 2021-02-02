
document.getElementById("sbmt").addEventListener("click", attemptLogin);

function attemptLogin() {
    let userName = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let credentials = { userName, password };
    const loginUrl = "http://localhost:8080/project1-ers/login";
    performAjaxPostRequest(loginUrl, JSON.stringify(credentials), handleSuccessfulLogin, handleUnsuccessfulLogin);
}

function newRequest() {
    let amount = document.getElementById("amount").value;
    let reason = document.getElementById("reason").value;
    let s = sessionStorage.getItem("token");
    let sp = s.split(":");
    let email = sp[2];
    let userId = sp[0];
    let details = { email, userId, amount, reason };
    // console.log(credentials);
    performAjaxPostRequest(createUrl, JSON.stringify(details), handleSuccessfulRequest, handleUnsuccessfulRequest);
}

function handleSuccessfulLogin(responseText) {
    console.log("Success! You're logged in");
    document.getElementById("error-msg").hidden = true;
    let token = responseText;
    sessionStorage.setItem("token", token);
    window.location.href = "landing.html";
    displayLoggedInUser();
}

function handleSuccessfulRequest() {
    window.location.href = "landing.html";
    displayLoggedInUser();
}

function handleUnsuccessfulRequest() {
    console.log("Request unsuccessful");
    document.getElementById("error-msg").hidden = false;
}

function handleUnsuccessfulLogin() {
    console.log("Login unsuccessful");
    document.getElementById("error-msg").hidden = false;
}

function logout() {
    sessionStorage.removeItem("token");
    window.location.href = "login.html";
}



function displayLoggedInUser() {
    const token = sessionStorage.getItem("token");
    const parsedToken = token.split(":");
    const loggedInID = parsedToken[0];
    getUserById(loggedInID, function (usersJSON) {
        const user = JSON.parse(usersJSON);
        document.getElementById("greeting-header").innerText = `Welcome to the ERS Portal, ${user.firstName} ${user.lastName}`;
    });
}

