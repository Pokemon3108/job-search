function selectList(name, value) {
    let elements = document.getElementsByName(name);

    for (let i = 0; i < elements.length; ++i) {
        if (document.getElementsByName(name)[i].value === value) {
            document.getElementsByName(name)[i].selected = true;
        }
    }
}


function selectCountry(country) {
    selectList("country", country);
}

function selectGender(gender) {
    selectList("gender", gender);
}

function validate(form) {
    let name = form["name"].value;
    let surname = form["surname"].value;
    let birthday = form["birthday"].value;
    let city = form["city"].value;

    let flag = true;

    if (!validateName(name)) {
        document.getElementById('nameError').innerHTML = 'Name should contains only letters or -.';
        hideElement('nameError');
        flag = false;
    }

    if (name.length>30) {
        document.getElementById('nameLengthError').innerHTML = 'Name cannot be longer than 30 symbols.';
        hideElement('nameLengthError');
        flag = false;
    }

    if (!validateName(surname)) {
        document.getElementById('surnameError').innerHTML = 'Surname should contains only letters or -.';
        hideElement('surnameError');
        flag = false;
    }

    if (surname.length>50) {
        document.getElementById('nameLengthError').innerHTML = 'Surname cannot be longer than 50 symbols.';
        hideElement('surnameLengthError');
        flag = false;
    }


    if (!validateName(city)) {
        document.getElementById('cityError').innerHTML = 'City should contains only letters or -.';
        hideElement('cityError');
        flag = false;
    }

    if (city.length>100) {
        document.getElementById('cityLengthError').innerHTML = 'City cannot be longer than 100 symbols.';
        hideElement('cityLengthError');
        flag = false;
    }

    if (!validateBirthday(birthday)) {
        document.getElementById('birthdayError').innerHTML = 'Your birthday cannot be later, than today date.';
        hideElement('birthdayError');
        flag = false;
    }

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



