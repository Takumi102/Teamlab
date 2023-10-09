
$(".menu-bar").on("click", function(){
    $(".sidebar").addClass("active");
});

$(".btn-close").on("click", function(){
    $(".sidebar").removeClass("active");
});

const body = document.querySelector('body');
const box = document.querySelector('.box');
const sun = document.querySelector('.sun');
const moon = document.querySelector('.moon');

box.addEventListener('click', () =>{
    body.classList.toggle('dark-mode');
    store(body.classList.contains('dark-mode'));
});

function store(value){
    localStorage.setItem('dark-mode', value);
}

function load(){
    const darkmode = localStorage.getItem('dark-mode');

    if(!darkmode){
        store(flase);
    } else if(darkmode == 'true'){
        body.classList.add('dark-mode');
        
    } 
}
load();