
const array = [
    location.origin + "/resources/css/side-menu.css",
    location.origin + "/resources/css/style.css",
];
document.head.onload = addCssDependencies();

function addCssDependencies() {
    for (const argument of array) {
        const style = document.createElement("link");
        style.rel = "stylesheet";
        style.type = "text/css";
        style.href = argument;
        document.head.appendChild(style);
    }
}