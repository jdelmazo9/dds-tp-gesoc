function includeHTML() {
  var z, i, elmnt, file, xhttp;
  /* Loop through a collection of all HTML elements: */
  z = document.getElementsByTagName("*");
  for (i = 0; i < z.length; i++) {
    elmnt = z[i];
    /*search for elements with a certain atrribute:*/
    file = elmnt.getAttribute("w3-include-html");
    if (file) {
      /* Make an HTTP request using the attribute value as the file name: */
      xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
          if (this.status == 200) {elmnt.innerHTML = this.responseText;}
          if (this.status == 404) {elmnt.innerHTML = "Page not found.";}
          /* Remove the attribute, and call this function once more: */
          elmnt.removeAttribute("w3-include-html");
          includeHTML();
        }
      }
      xhttp.open("GET", file, true);
      xhttp.send();
      /* Exit the function: */
      return;
    }
  }
}

function eliminarItem(idEgreso, idItem){

  console.log(idEgreso);
  console.log(idItem);
  $.ajax({
      type: "DELETE",
      url: "/egresos/" + idEgreso + "/items/" + idItem,
      success: function(result){
          location.reload(true);
      }
  });
}

function eliminarPresupuesto(idEgreso, idPresupuesto){

  console.log(idEgreso);
  console.log(idPresupuesto);
  $.ajax({
      type: "DELETE",
      url: "/egresos/" + idEgreso + "/presupuestos/" + idPresupuesto,
      success: function(result){
          location.reload(true);
      }
  });
}

function eliminarCategoria(idEgreso, idCategoria){

  console.log(idEgreso);
  console.log(idCategoria);
  $.ajax({
      type: "DELETE",
      url: "/egresos/" + idEgreso + "/categorias/" + idCategoria,
      success: function(result){
          location.reload(true);
      }
  });
}

function backEgresos(){
  console.log(document.getElementById('items-list').getElementsByTagName('li').length);
  if(document.getElementById('items-list').getElementsByTagName('li').length > 0){
    location.replace('/egresos');
  }
  
}

/*
const sendBtn = document.getElementsByClassName("btn-item");
const dropdownMenu = document.getElementById('items-list').length;
if(dropdownMenu >= 1){
  ()=>{sendBtn.removeAttribute("disabled")};
};
*/
function DoNav(url) {
 document.location.href = url;
}

/*
$(document).ready(function(){
  $("#inputUsuario").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#tabla tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});



function confirmarEliminacion(id){
  document.getElementById("userId").value = id;
  document.getElementById("modalEliminar").style.display = 'block';
}

function eliminarUsuario(){
    var id = document.getElementById("userId").value;
    $.ajax({
        type: "DELETE",
        url: "usuario/"+id,
        success: function(result){
            location.reload(true);
        }
    });
}

function cerrarModal(){
  document.getElementsByClassName("modal")[0].style.display = 'none';
}
*/
