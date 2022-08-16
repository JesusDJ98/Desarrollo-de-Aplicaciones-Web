<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="BD.Articulos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Proyecto DAW</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" href="./css/estilos.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="WEB-INF/jspf/Cabecera.jspf" %>
            
            <%@include file="WEB-INF/jspf/Menu.jspf" %>
            
            ${msg}
            <div class="row">
                <form class="col-12 Art_2Menu" action="filtro" method="post">
                    <select name="Categoria">
                        <option selected>Categoria</option>
                        <option>Herramientas</option>
                        <option>Deportes</option>
                        <option>Electronica</option>
                        <option>Otros</option>
                    </select>
                    Codigo Postal: <input type="text" id="CodP" name="CodP" class="btn2"/>
                    Precio: <input type="number" id="precio" name="precio" class="btn2" min="0" step="any"  />
                    <button type="submit" class="btn2 btn-primary">Search</button>
                </form>
            </div>
            
            <h3 class="text-center">Articulos disponibles</h3>
            <%@include file="WEB-INF/jspf/producto.jspf" %>
            <%@include file="WEB-INF/jspf/Pie.jspf" %>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
        
    </body>
</html>