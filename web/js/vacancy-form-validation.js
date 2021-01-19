function validateForm(form) {
    let city = form["city"].value;

    let flag=true;
    if (!validateCity(city)) {
        document.getElementById('cityError').innerHTML = 'City can contain only letters, spaces and -.';
        hideElement('cityError');
        flag = false;
    }

    return flag;
}

function validateCity(city) {
    const re = /[\w\-\s]/;
    return re.test(String(city));
}

function hideElement(id) {
    if (document.getElementById(id).innerHTML === '') {
        document.getElementById(id).hidden = true;
    } else {
        document.getElementById(id).hidden = false;
    }
}

hideElement("cityError");