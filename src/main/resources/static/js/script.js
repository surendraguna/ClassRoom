setInterval(timeNow, 1000);
function timeNow() {
    document.getElementById('date').innerHTML = new Date().toLocaleDateString('en-GB', {
        day: 'numeric',
        month: 'long',
        year: 'numeric'
    });
    document.getElementById('time').innerHTML = new Date().toLocaleTimeString();
}

document.getElementById("show-register").addEventListener("click", function (event) {
    event.preventDefault();
    document.getElementById("login-card").style.display = "none";
    document.getElementById("register-card").style.display = "grid";
    document.getElementById("reg-email").value = '';
    document.getElementById("reg-pass").value = '';
});

document.getElementById("show-login").addEventListener("click", function (event) {
    event.preventDefault();
    document.getElementById("register-card").style.display = "none";
    document.getElementById("login-card").style.display = "grid";
    document.getElementById("username").value = '';
    document.getElementById("password").value = '';
});

function submitRegistrationForm() {
    
    const username = document.getElementById("reg-email").value;
    const password = document.getElementById("reg-pass").value;
    const successMessageDiv = document.getElementById("success-message");
    const errorMessageDiv = document.getElementById("error-message");

    successMessageDiv.style.display = "none";
    errorMessageDiv.style.display = "none";

    if (!username || !password) {
        errorMessageDiv.innerText = "All fields are required.";
        errorMessageDiv.style.display = "block";
        return; 
    }

    fetch("/reg", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username, password })
    })
    .then(response => {
        
        document.getElementById("reg-email").value = '';
        document.getElementById("reg-pass").value = '';
        
        if (response.ok) {

            return response.json().then(data => {
                successMessageDiv.style.display = "block";
                successMessageDiv.textContent = data.message;
            });

        } else {
            return response.json().then(data => {
                errorMessageDiv.style.display = "block";
                errorMessageDiv.textContent = data.message;
            });
        }
    })
    .catch(error => {
        console.error("Error:", error);
        errorMessageDiv.style.display = "block";
        errorMessageDiv.textContent = "An unexpected error occurred. Please try again.";
    });
}
