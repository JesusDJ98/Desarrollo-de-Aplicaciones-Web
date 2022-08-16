<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Proyecto DAW</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="/Practica_DAW/css/estilos.css" />
        <!-- Boostrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="WEB-INF/jspf/Cabecera.jspf" %>
            
            <%@include file="WEB-INF/jspf/Menu.jspf" %>
            
            ${msg}

            <section class="row" >
                <div class="col-12 GlobalForm2">
                    <% Object id = request.getAttribute("idArtC"); %>
                    <form class="Formulario2" action="altaComent?id=<%= id %>" method="post" >
                        
                        <div class="form-group">
                            <label for="comentario">Comentatio: </label>
                            <textarea class="form-control" name="comentario" rows="10" cols="40" ></textarea>
                        </div>
                        <div class="form-group">
                            <label for="Privacidad">Privacidad: </label>
                            <select name="Privacidad" >
                                <option selected>Publico</option>
                                <option>Vendedor</option>
                                <option>Personal</option>
                            </select>
                        </div>
                        <br>
                        
                        <button type="submit" class="btn btn-primary">Publicar</button>
                        
                    </form>
                    
                </div>
                
            </section>


            <%@include file="WEB-INF/jspf/Pie.jspf" %>
        </div>

        <!-- Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>
