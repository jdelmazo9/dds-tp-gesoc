//$('#medioDePagoDiv').ddslick({
//    data: ddData,
//    width: 300,
//    imagePosition: "left",
//    selectText: "Seleccione medio de pago",
//    onSelected: function (data) {
//        console.log(data);
//    }
//});

$(document).ready(function(){
  $("#medioDePago").select2({
   templateResult: formatState
  });
 });

 function formatState (state) {
  if (!state.id) { return state.text; }
  if (state.text === 'Seleccionar') {
      var $state = $(
         '<span>' + state.text + ' </span>'
      );
  }
  else{
      var $state = $(
       '<span>' + state.text + '<img class="option-img" sytle="display: inline-block; width: 10px; height: 10px; " src="'+state.element.value+'" />  </span>'
      );
  }
  return $state;
 }

//
//function agregarItem() {
//    var descripcion = document.getElementById("inputDescripcion");
//    var valor = document.getElementById("inputValor");
//    var tipo = document.getElementById("inputTipo");
//            console.log(descripcion.value);
//            console.log(valor.value);
//            console.log(tipo.value);
////   // if(criterio.selectedIndex == 0 || categoria.selectedIndex == 0){
////        return;
////    }
////
//    // Agrego items a la lista de items cargados
//  //  var listaItems = document.getElementById("listaItemsCargados");
//    var elem = document.createElement("li");
//    elem.appendChild(document.createTextNode(descripcion.value+" $"+valor.value+" tipo:"+tipo.value));
//    listaItemsCargados.appendChild(elem);
//    }
//Handlebars.registerHelper('ifEquals', function(arg1, arg2, options) {
//    return (arg1 == arg2) ? options.fn(this) : options.inverse(this);
//});
