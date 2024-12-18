const otpInputs = document.querySelectorAll('.otp-input');

otpInputs.forEach((input, index) => {
    input.addEventListener('input', (e) => {
        const value = e.target.value;

        if (value && index < otpInputs.length - 1) {
            otpInputs[index + 1].focus(); 
        }
    });

    input.addEventListener('keydown', (e) => {
        if (e.key === 'Backspace' && index > 0 && !e.target.value) {
            otpInputs[index - 1].focus(); 
        }
    });
});

document.getElementById('verify').addEventListener('submit', (e) => {
    e.preventDefault();
    const otp = Array.from(otpInputs).map(input => input.value).join('');
    fetch('http://localhost:8080/verify-otp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({otp : otp}),
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            window.location.href = 'http://localhost:8080/';
        } else {
            alert(data.error);
        }
    })
    .catch(error => console.error(error));
});