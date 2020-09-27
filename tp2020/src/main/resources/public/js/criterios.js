$(function() {
    $('#inputCriterio').change( function(){
        var id = $(this).val();
//        console.log('id criterio: '+id);
        $.ajax({
            url: '/criterios/' + id,
            success: function(data) {
                var catSelect = document.getElementById("inputCategoria");
                catSelect.options.length = 0;
                catSelect.options[catSelect.options.length] = new Option("Seleccionar", 0, true, false);
                for(index in data.categorias){
//                    console.log('index: '+ index);
//                    console.log('cat: '+ data.categorias[index].nombre);
                    catSelect.options[catSelect.options.length] = new Option(data.categorias[index].nombre, data.categorias[index].nombre);
                }
            }
        });
    });
});

function aplicarFiltro() {
    var criterio = document.getElementById("inputCriterio");
    var categoria = document.getElementById("inputCategoria");

    if(criterio.selectedIndex == 0 || categoria.selectedIndex == 0){
        return;
    }

    // Agrego el filtro a la lista
    var listaFiltros = document.getElementById("listaFiltros");
    var elem = document.createElement("li");
    //  elem.appendChild(document.createTextNode(criterio.value+": "+categoria.value));
    elem.appendChild(document.createTextNode("criterio="+criterio.value+"&categoria="+categoria.value));
    listaFiltros.appendChild(elem);

    // Obtengo la lista completa de filtros aplicados
    var todosLosFiltros = "";
    for (const li of listaFiltros.getElementsByTagName("li")) {
    //  console.log(li.textContent);
        todosLosFiltros += li.textContent + "&";
    }
    todosLosFiltros = todosLosFiltros.slice(0, -1).replace(' ','%20');
    //  console.log(todosLosFiltros);

    $("#tablaOperaciones tr").not('thead tr').remove();
    var tablaOperaciones = document.getElementById("tablaOperaciones").getElementsByTagName('tbody')[0];
    $.ajax({
    //  url: '/api/egresos?criterio='+criterio.value+'&categoria='+categoria.value,
        url: '/api/egresos?'+todosLosFiltros,
        success: function(data) {
            for(index in data){
                var newRow = tablaOperaciones.insertRow();
                newRow.insertCell(0).appendChild(document.createTextNode(data[index].id));
                newRow.insertCell(1).appendChild(document.createTextNode(data[index].fechaStr));
                newRow.insertCell(2).appendChild(document.createTextNode(data[index].proveedor.nombre));
            }
        }
    });

    criterio.options[0].selected = "selected";
    categoria.options[0].selected = "selected";
    categoria.options.length = 0;
    categoria.options[categoria.options.length] = new Option("Seleccionar", 0, true, false);
}

function borrarFiltros() {
    var listaFiltros = document.getElementById("listaFiltros");
    listaFiltros.innerHTML = "";

    $("#tablaOperaciones tr").not('thead tr').remove();
    var tablaOperaciones = document.getElementById("tablaOperaciones").getElementsByTagName('tbody')[0];
    $.ajax({
        url: '/api/egresos',
        success: function(data) {
            for(index in data){
                var newRow = tablaOperaciones.insertRow();
                newRow.insertCell(0).appendChild(document.createTextNode(data[index].id));
                newRow.insertCell(1).appendChild(document.createTextNode(data[index].fechaStr));
                newRow.insertCell(2).appendChild(document.createTextNode(data[index].proveedor.nombre));
            }
        }
    });
}