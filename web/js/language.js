let button = document.getElementById("change_lan");
button.addEventListener("click", getLanguage);

function getLanguage() {
    let radios = document.getElementsByName('lang');
    let locale;

    for (let radio of radios) {
        if (radio.checked) {
            locale=radio.value;
        }
    }

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        location.reload();
    }
    xhr.open('GET', "/main/job/changeLocale?lang=" + locale);
    // xhr.open('GET', "/bb")
    xhr.send();
}


