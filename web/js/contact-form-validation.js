function validate(form) {
    let email = form["email"].value;
    let number = form["number"].value;

    let flag = true;

    if (!validateEmail(email)) {
        document.getElementById('emailError').innerHTML = 'Illegal email format. js';
        hideElement('emailError');
        flag = false;
    }

    if (!validatePhone(number)) {
        document.getElementById('contactError').innerHTML = 'Invalid telephone format. Example: +375291234567';
        hideElement('contactError');
        flag = false;
    }

    return flag;
}

function validateEmail(email) {
    const re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    return re.test(String(email).toLowerCase());
}

function validatePhone(phone) {
    const re=/\+\d{12}/;
    return re.test(String(phone));
}

function hideElement(id) {
    if (document.getElementById(id).innerHTML === '') {
        document.getElementById(id).hidden = true;
    } else {
        document.getElementById(id).hidden = false;
    }
}

hideElement('emailError');
hideElement('contactError');

