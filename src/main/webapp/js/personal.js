const rightNav = document.querySelectorAll(".right-nav")[0];
document.addEventListener("scroll", function () {
    if (window.pageYOffset > 200) {
        rightNav.style.top = "0";
    } else {
        rightNav.style.top = "12%";
    }
});
