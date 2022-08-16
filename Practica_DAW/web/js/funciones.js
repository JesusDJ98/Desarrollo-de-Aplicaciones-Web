function FormValido(){
    return (PwdIguales());// && EmailDisp());
}

function PwdIguales(){
    var p1 = document.getElementById("Ipwd1");
    var p2 = document.getElementById("Ipwd2");
    
    if (p1.value !== p2.value) {
        document.getElementById("Imsg").innerHTML = "Las passwords deben de coincidir";
        cambio(p2);
        return false;
    }
    cambio2(p2);
    return true; 
    
}

function cambio(elemento){
  $(elemento).css("border", "1px solid red");
}
function cambio2(elemento){
  $(elemento).css("border", "1px solid #ccccff");
}

function EmailDisp(){
    /*
     * 
     * @type {Element}Lo he intentado cambiando el valor de un span
     * y de un input, ninguno de los dos lo consigue.
     * Me cambian el valor, pero al cogerlo despues de onreadystatechange,
     * no coge nada, es decir, lo coge antes de que se modifique....
     * He probado pero no logro sacarlo
     */
    
    var p1 = document.getElementById("micorreo");
    
    alert("cojo: "+p1.value)
    init_ajax();
    
    var url = "idValido?correo="+p1.value;
    xhr.open("GET", url, true);
    xhr.onreadystatechange = idValido;
    xhr.send(null);
    //return validarCorreoDB(p1);
    
    //Hata que no se cumple el valido, no llega aqui
    var p2 = document.getElementById("miLabel").value;
    alert("envio datos y cojo p2: "+p2)
    
    
    
    //alert("Valido: "+p2.object);    //indefinido
    alert(p2);
    if(p2 == "correo permitido"){
        alert("Igual1");
    }
    if("correo permitido".equals(p2)){
        alert("Igual2");
    }
    //alert("Ya es valido: "+p2.value);//Indefinido
    /*if(p2 === 'JSP PageOK'){
        alert("Es ok");
        cambio2(p1);
        return true;
    }else if(p2 === 'OK'){
        alert("Comparacion1")
    }else if(p2 === 'JSP Page OK'){
        alert("Comparacion2")
    }else if('JSP Page OK'.equals (p2)){
        alert("Comparacion3")
    }else if('JSP Page OK'.equals(p2) ){
        alert("Comparacion4")
    }else if('correo permitido'.equals(p2) ){
        alert("Comparacion5")
    }else if('JSP Page correo permitido'.equals(p2) ){
        alert("Comparacion6")
    }else if('JSP Pagecorreo permitido'.equals(p2) ){
        alert("Comparacion7")
    }else if(p2 === 'JSP Page correo permitido'){
        alert("Comparacion8")
    }else if(p2 === 'JSP Pagecorreo permitido'){
        alert("Comparacion9")
    }else if(p2 === 'correo permitido'){
        alert("Comparacion10")
    }else {
        alert("No es, JSP PageOK: "+p2)
        cambio(p1);
        return false;
    }*/
       
    
    
    
}

var xhr;

function init_ajax(){
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function idValido() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            //Aqui cambio el valor
            document.getElementById("miLabel").value = xhr.responseText;
            alert("Loq cambio: "+document.getElementById("miLabel").value );
        }
    }
}
