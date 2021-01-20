function selectList(name, value) {
    let elements = document.getElementsByName(name);
    for (let i = 0; i < elements.length; ++i) {
        if (document.getElementsByName(name)[i].value === value) {
            document.getElementsByName(name)[i].selected = true;
        }
    }
}


// function selectSpecialization(specialization) {
//     selectList("specialization", specialization);
// }

function selectCurrency(currency) {
    selectList("currency", currency);
}

function selectSchedule(schedule) {
    selectList("schedule", schedule);
}


function validate(form) {
    let position = form["position"].value;
    let flag = true;

    if (position.length>255) {
        document.getElementById(id).innerHTML = 'Position cannot be longer than 255 symbols.';
        hideElement('positionError');
        flag = false;
    }


    return flag;
}

function hideElement(id) {
    if (document.getElementById(id).innerHTML === '') {
        document.getElementById(id).hidden = true;
    } else {
        document.getElementById(id).hidden = false;
    }
}

hideElement('positionError');
