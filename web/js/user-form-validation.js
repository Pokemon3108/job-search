function validate(form) {
    let email = form["email"].value;
    let password = form["password"].value;

    let flag = true;

    if (!validateEmail(email)) {
        document.getElementById('emailError').innerHTML = 'Illegal email format. js';
        hideElement('emailError');
        flag = false;
    }

    if (!validatePassword(password)) {
        document.getElementById('passwordError').innerHTML = 'Invalid password format. It should contains 1 number, 1 capital, 1 lowercase letter and at least 8 symbols. js';
        hideElement('passwordError');
        flag = false;
    }

    return flag;
}

function validateEmail(email) {
    const re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    return re.test(String(email).toLowerCase());
}

function validatePassword(password) {
    // const re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^\w\s]).{8,}/;
    // return re.test(String(password));
     return password.length>8;
}

function hideElement(id) {
    if (document.getElementById(id).innerHTML === '') {
        document.getElementById(id).hidden = true;
    } else {
        document.getElementById(id).hidden = false;
    }
}

hideElement('emailError');
hideElement('passwordError')