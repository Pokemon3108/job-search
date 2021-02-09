hideElement('emailError');
hideElement('passwordError');
hideElement('differentPassword');

function validate(form) {
    let email = form["email"].value;
    let password = form["password"].value;

    let flag = true;

    if (!validateEmail(email)) {
        document.getElementById('emailError').innerHTML = 'Illegal email format.';
        flag = false;
    } else {
        document.getElementById('emailError').innerHTML='';
    }
    hideElement('emailError');

    if (!validatePassword(password)) {
        document.getElementById('passwordError').innerHTML = 'Invalid password format. It should contains 1 number, 1 capital, 1 lowercase letter, 1 special symbol !@#$%^&*' +
            ' and at least 8 symbols.';
        flag = false;
    } else {
        document.getElementById('passwordError').innerHTML='';
    }
    hideElement('passwordError');

    if (!comparePasswords()) {
        document.getElementById('differentPassword').innerHTML = 'The values entered for password and confirm password do not match. Please, enter the same password for both fields.';
        flag = false;
    } else {
        document.getElementById('differentPassword').innerHTML='';
    }
    hideElement('differentPassword');

    return flag;
}

function validateEmail(email) {
    if (email.length>255) {
        return false;
    }
    const re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    return re.test(String(email).toLowerCase());
}

function validatePassword(password) {
     const re = /(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,}/;
     return re.test(String(password));
  //  return password.length > 8;
}

function hideElement(id) {
    if (document.getElementById(id).innerHTML === '') {
        document.getElementById(id).hidden = true;
    } else {
        document.getElementById(id).hidden = false;
    }
}

function checkRadioButton(role) {
    let counter=0;
    let roles = document.getElementsByName("role");
    for (let i=0; i<roles.length; ++i) {
        if (document.getElementsByName("role")[i]===role) {
            ++counter;
            document.getElementsByName("role")[i].checked=true;
        }
    }

    if (counter===0) {
        document.getElementsByName("role")[0].checked=true;
    }

}

function show_hide_password(target, id){
    let input = document.getElementById(id);
    if (input.getAttribute('type') == 'password') {
        target.classList.add('view');
        input.setAttribute('type', 'text');
    } else {
        target.classList.remove('view');
        input.setAttribute('type', 'password');
    }
    return false;
}

function comparePasswords() {
    let areEquals=true;
    let password=document.getElementById("password").value;
    let confirmedPassword=document.getElementById("confirm-password").value;

    if (password!=confirmedPassword) areEquals=false;
    return areEquals;
}



