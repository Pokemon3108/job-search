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

