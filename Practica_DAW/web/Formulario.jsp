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
            <section class="row">
                <div class="col-5">
                    <form class="Formulario" action="login" method="post">
                        <div class="form-group">
                            <label for="correouser">Usuario:</label>
                            <input type="email" class="form-control" name="correouser" aria-describedby="emailHelp" required>
                            
                        </div>
                        <div class="form-group">
                            <label for="pwduser">Contraseña:</label>
                            <input type="password" class="form-control" name="pwduser" required>
                        </div>
                        <button type="submit" class="btn btn-primary" value="SaveLogin">Log In</button>
                        
                    </form>
                    <div class="Formulario">
                        <!-- Separacion -->
                        <div id="Separacion">
                            <div class="dentroizq">
                                <hr>
                            </div>
                            <div class="dentroc">
                                OR 
                            </div>
                            <div class="dentroder">
                                 <hr>
                            </div>
                        </div> <br>
                        
                        <!-- Recuperar contraseña -->
                        <div class="Forgot">
                            <small> <a href="recuperarPwd" >Recuperar Contraseña</a> </small>
                        </div>
                        <br>
                        <br>
                        <!-- Registrarse -->
                        <div class="SignUp">
                            <small>No tienes cuenta? <a href="alta">Sign Up</a> </small>
                        </div>
                    </div>
                    
                    
                </div>
                
                <div class="col-1">
                </div>
                
                <div class="col-6">
                    <img id="ImgForm" src="/Practica_DAW/imagenes/Formulario.jpg">
                </div>
                
            </section>
            <%@include file="WEB-INF/jspf/Pie.jspf" %>
        </div>

        <!-- Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>