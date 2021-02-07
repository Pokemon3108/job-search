function validate(form) {
    let name = form["name"].value;
    let surname = form["surname"].value;
    let birthday = form["birthday"].value;
    let city = form["city"].value;

    let flag = true;

    if (!validateName(name)) {
        document.getElementById('nameError').innerHTML = 'Name should contains only letters or -.';
        flag = false;
    }
    hideElement('nameError');

    if (name.length>30) {
        document.getElementById('nameLengthError').innerHTML = 'Name cannot be longer than 30 symbols.';
        flag = false;
    }
    hideElement('nameLengthError');

    if (!validateName(surname)) {
        document.getElementById('surnameError').innerHTML = 'Surname should contains only letters or -.';
        flag = false;
    }
    hideElement('surnameError');

    if (surname.length>50) {
        document.getElementById('nameLengthError').innerHTML = 'Surname cannot be longer than 50 symbols.';
        flag = false;
    }
    hideElement('surnameLengthError');


    if (!validateName(city)) {
        document.getElementById('cityError').innerHTML = 'City should contains only letters or -.';
        flag = false;
    }
    hideElement('cityError');

    if (city.length>100) {
        document.getElementById('cityLengthError').innerHTML = 'City cannot be longer than 100 symbols.';
        flag = false;
    }
    hideElement('cityLengthError');

    if (!validateBirthday(birthday)) {
        document.getElementById('birthdayError').innerHTML = 'Your birthday cannot be later, than today date.';
        flag = false;
    }
    hideElement('birthdayError');

    return flag;
}


function validateName(name) {
    if (name==='') {
        return true;
    }
    const re = /^[a-zа-я\s\-]+$/;
    return re.test(String(name).toLowerCase());
}

function validateBirthday(date) {
    let today = Date.now();
    return (new Date(date) < today);
}

function hideElement(id) {
    if (document.getElementById(id).innerHTML === '') {
        document.getElementById(id).hidden = true;
    } else {
        document.getElementById(id).hidden = false;
    }
}

hideElement('nameError');
hideElement('surnameError');
hideElement('cityError');
hideElement('birthdayError');
hideElement('nameLengthError');
hideElement('surnameLengthError');
hideElement('cityLengthError');



