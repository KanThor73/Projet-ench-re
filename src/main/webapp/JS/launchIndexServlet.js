function executeOnce() {
  window.location.href = "/ProjetEnchere/IndexServlet";

  window.removeEventListener("load", executeOnce);
}

window.addEventListener("load", executeOnce);