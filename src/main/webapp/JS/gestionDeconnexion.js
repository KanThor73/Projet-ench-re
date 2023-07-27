var inactiveTime = 300000;
var timer = null;

function startTimer() {
	timer = setTimeout(disconnect, inactiveTime);
}

function resetTimer() {
	clearTimeout(timer);
	startTimer();
}

document.addEventListener("keydown", resetTimer);// remettre le timer a zero a chaque evenements
document.addEventListener("mousemove", resetTimer);

window.addEventListener("load", () => {
	startTimer();
	console.log("Session expiree");
});// lancer le timer a l'ouverture de la page

function disconnect() {
	var request = new XMLHttpRequest();

	request.open("GET", "/ProjetEnchere/Deconnexion", true);

	request.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 200) {
			if (request.status == 200) {
				console.log("Session expiree");
				window.location.href = "/ProjetEnchere/Connexion";
			} else {
				console.error("Erreur lors de la deconnexion automatique");
			}
		}
	};
	request.send();// envoyer la requete
}