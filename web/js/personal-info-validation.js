function selectList(name, value) {
    let counter = 0;
    let elements = document.getElementsByName(name);

    for (let i = 0; i < elements.length; ++i) {
        if (document.getElementsByName(name)[i].value === value) {
            console.log('here');
            ++counter;
            document.getElementsByName(name)[i].selected = true;
        }
    }

    if (counter === 0) {
        document.getElementsByName(name)[0].selected = true;
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
        console.log(1);
        document.getElementById('nameError').innerHTML = 'Name should contains only letters or -.';
        hideElement('nameError');
        flag = false;
    }


    if (!validateName(surname)) {
        console.log(2);
        document.getElementById('surnameError').innerHTML = 'Surname should contains only letters or -.';
        hideElement('surnameError');
        flag = false;
    }


    if (!validateName(city)) {
        console.log(3);
        document.getElementById('cityError').innerHTML = 'City should contains only letters or -.';
        hideElement('cityError');
        flag = false;
    }

    if (!validateBirthday(birthday)) {
        console.log(4);
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
    console.log(date);
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



