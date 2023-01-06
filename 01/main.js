let telleknapp = document.getElementById("telleknapp");
let teller = 0;

telleknapp.addEventListener('click', () => {
    teller++;
    telleknapp.innerHTML = "Du har trykket her " + teller + " ganger";
});

let hamsterknapp = document.getElementById("hamsterknapp");
let hamsterbilde = document.getElementById("hamsterbilde");
let hamstervisning = false;

hamsterbilde.style.display = 'none';

hamsterknapp.addEventListener('click', () => {
    hamstervisning = !hamstervisning;

    if (hamstervisning) {
        hamsterbilde.style.display = 'block';
        hamsterknapp.innerHTML = hamsterknapp.innerHTML.toUpperCase();
    } else {
        hamsterbilde.style.display = 'none';
        hamsterknapp.innerHTML = hamsterknapp.innerHTML.toLowerCase();
    }
});

let listeknapp = document.getElementById("listeknapp");
let stikkord = "dette er en liste med ord som jeg fant på".split(" ");
let listelagd = false;

listeknapp.addEventListener("click", () => {
    if (!listelagd) {
        let liste = document.createElement("ul");
        stikkord.forEach((ord, _) => {
            let listeelement = document.createElement("li");
            listeelement.innerHTML = ord;
            liste.appendChild(listeelement);
        });

        listeknapp.insertAdjacentElement("afterend", liste);

        listelagd = true;
    }
});
