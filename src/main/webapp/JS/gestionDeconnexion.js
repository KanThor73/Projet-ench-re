var inactiveTime = 300000;
var timer = null;

function startTimer() {// minuteur qui execute disconnect a la fin du temps imparti
	timer = setTimeout(disconnect, inactiveTime);
}

function resetTimer() {// reset le chrono des que l'on detect une activite utilisateur
	clearTimeout(timer);
	startTimer();
}

document.addEventListener("keydown", resetTimer);// remettre le timer a zero a chaque evenements
document.addEventListener("mousemove", resetTimer);

window.addEventListener("load", () => {// lancer le timer a l'ouverture de la page
	startTimer();
	console.log("Session expiree");
});

function disconnect() {
	var request = new XMLHttpRequest();

	request.open("GET", "/ProjetEnchere/Deconnexion", true);// init la requete

	request.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 200) {
			if (request.status == 200) {
				console.log("Session expiree");
				window.location.href = "/ProjetEnchere/Connexion";//redirection quand le timer arrive a zero
			} else {
				console.error("Erreur lors de la deconnexion automatique");
			}
		}
	};
	request.send();// envoyer la requete
}