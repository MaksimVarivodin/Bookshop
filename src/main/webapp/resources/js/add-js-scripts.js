const contextPath = location.origin;
const jQueryPath = "https://code.jquery.com/jquery-3.7.0.js";
const headPaths = [
    contextPath + "/resources/js/add-css-dependencies.js",
    contextPath + "/resources/js/add-profile-menu.js",

    // "script.js",
];
document.head.onload = addHeadJsScripts();
// document.body.onload = addBodyJsScripts();
function addHeadJsScripts() {
    let script = document.createElement("script");
    script.src = jQueryPath;
    script.type = "text/javascript";
    document.head.appendChild(script);
    script.addEventListener('load', () => {
        for (const argument of headPaths) {
            script = document.createElement("script");
            script.src = argument;
            script.type = "text/javascript";
            document.head.appendChild(script);
        }
    });
}
