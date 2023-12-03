document.body.onload = addProfileMenu();

function addProfileMenu() {

    const newMenu = document.createElement("div");
    newMenu.id = "user-menu";
    const error = document.createElement("div");
    error.id = "error";



    let header = document.createElement("header");
    header.appendChild(newMenu);
    header.appendChild(error);
    document.body.prepend(header);

    loadUser();
}
function loadUser() {
    $("#user-menu").load(location.origin+ "/users/menu/1 #user",
        function (response, status, xhr) {
            if (status === "error") {
                $("#error").html("Error: " + xhr.status + " " + xhr.statusText);
            }
        });
}