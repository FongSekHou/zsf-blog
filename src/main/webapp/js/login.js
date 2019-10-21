const login = document.querySelectorAll(".page-login")[0];
const register = document.querySelectorAll(".page-register")[0];
const registerBtn = document.querySelectorAll(".register>a")[0];
const returnBtn = document.querySelectorAll(".return>a")[0];

registerBtn.addEventListener("click",function() {
    login.classList.remove("login-ruturn");
    login.classList.add("login-reverse");
    register.classList.remove("register-reverse");
    register.classList.add("register-return");
})
returnBtn.addEventListener("click",function() {
    login.classList.remove("login-reverse");
    login.classList.add("login-ruturn");
    register.classList.remove("register-return");
    register.classList.add("register-reverse");
})
