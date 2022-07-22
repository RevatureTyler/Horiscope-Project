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


    async function updateUsersMood(mood){
        const raw_response = await fetch(`http://localhost:8080/`)
    }
});

let userInStorage = localStorage.getItem('currentUser');

let currentUser = JSON.parse(userInStorage);
console.log(currentUser);

let welcomeLabel = document.getElementById("welcome-label")
welcomeLabel.innerText = `Welcome ${currentUser.first_name}, Ready to find out your horoscope?`

let button = document.getElementById("button")

button.addEventListener("click", async() =>{


    try{
        const raw_response = await fetch (`http://sandipbgt.com/theastrologer/api/horoscope/${currentUser.horoscope}/today`)

        //check for a successful response
        if(!raw_response.ok){
            throw new Error(raw_response.status);
        }
        const json_data = await raw_response.json();
        console.log(json_data);

        var input = document.getElementById("input");
        var horoscope = document.getElementById("h2");

        horoscope.innerHTML = `Your mood for the day is: ${json}`;
        input.append(horoscope);
        
        var b = document.createElement('br');
        input.append(b);

        var mood = document.getElementById("mood");
        mood.innerText = `${json_data.meta.mood}`;

        var newButton = document.createElement("BUTTON")
        newButton.setAttribute("id","moodButton");
        newButton.setAttribute("width", "250px");
        newButton.setAttribute("height","250px");
        newButton.innerText = "Click Here to Update Mood";
        buttons.append(newButton);

        // let horoscope = document.getElementById("horoscope")

        // horoscope.innerText = json_data.horoscope;

        // let mood = document.getElementById("mood");
        // mood.innerText = `Today's Mood: ${json_data.meta.mood}`;

        // //update database
        // updateUsersMood(json_data.meta.mood)

    }catch(error){
        alert("Error: " + error)
    }
})


async function updateUsersMood(mood){

    
}