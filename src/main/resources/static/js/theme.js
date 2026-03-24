document.addEventListener("DOMContentLoaded", () => {
    const themeToggle = document.getElementById("checkbox-theme");
    const logoImg = document.querySelector(".logo-img"); 

    const lightLogoPath = "images/InventorySystem.Logo_white.png";
    const darkLogoPath = "images/InventorySystem.Logo-dark.png";

    const currentTheme = localStorage.getItem("theme");

    const updateLogo = (theme) => {
        if (logoImg) {
            logoImg.src = theme === "dark" ? darkLogoPath : lightLogoPath;
        }
    };

    if (currentTheme === "dark") {
        document.body.classList.add("dark-theme");
        if (themeToggle) {
            themeToggle.checked = true;
        }
        updateLogo("dark");
    } else {
        updateLogo("light");
    }

    if (themeToggle) {
        themeToggle.addEventListener("change", () => {
            if (themeToggle.checked) {
                document.body.classList.add("dark-theme");
                localStorage.setItem("theme", "dark");
                updateLogo("dark");
            } else {
                document.body.classList.remove("dark-theme");
                localStorage.setItem("theme", "light");
                updateLogo("light");
            }
        });
    }
});