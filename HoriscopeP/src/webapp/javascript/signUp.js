let submitButton = document.getElementById("submitButton");
console.log(submitButton);
submitButton.addEventListener("click", (event) => {
    event.preventDefault();
    let xhttp = new XMLHttpRequest();
    let first_name = document.getElementById("firstName-input").value;
    let last_name = document.getElementById("lastName-input").value;
    let email = document.getElementById("email-input").value;
    let username = document.getElementById("username-input").value;
    let passw = document.getElementById("password-input").value;
    let horoscope = document.getElementById("zodiac-input").value;
    let signUpInfo = {
        first_name:first_name,
        last_name:last_name,
        email:email,
        username:username,
        passw:passw,
        horoscope:horoscope,
    };
    console.log(signUpInfo);
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && xhttp.status === 200){
            console.log(xhttp.responseText);
            let data = JSON.parse(xhttp.responseText);
            console.log(data);
            //global caching
            // localStorage.setItem('currentHoroscopeUser',JSON.stringify(data));
            // console.log(localStorage.getItem('currentHoroscopeUser'))
            window.location.replace("index.html");
        }  else if(this.readyState == 4 && xhttp.status == 204){
            console.log(xhttp.responseText);
            alert("Failed to Login: Status Code - "+xhttp.status)
        }
    };
    //OPEN THE REQUEST
    xhttp.open("POST",`http://localhost:8080/HoriscopeP/signUp`);
    xhttp.setRequestHeader("Access-Control-Allow-Origin","*");
    xhttp.setRequestHeader("Content-Type","application/json");
    //SEND STATUS
    xhttp.send(JSON.stringify(signUpInfo));
});