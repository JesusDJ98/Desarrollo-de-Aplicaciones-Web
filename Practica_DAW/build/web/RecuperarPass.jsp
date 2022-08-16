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

            <section class="row" id="cuerpo">
                <div class="col-5">
                    <form class="Formulario" action="Inicio/RecPassword" method="post">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Correo: </label>
                            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                        
                        <div class="back">
                            <small> <a href="Formulario.jsp" >Volver al registro</a> </small>
                        </div>
                        
                    </form>
                </div>
                
                <div class="col-1">
                </div>
                <div class="col-6">
                    <img id="ImgForm" src="./imagenes/Formulario.jpg">
                </div>
                
            </section>


            <%@include file="WEB-INF/jspf/Pie.jspf" %>
        </div>

        <!-- Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>