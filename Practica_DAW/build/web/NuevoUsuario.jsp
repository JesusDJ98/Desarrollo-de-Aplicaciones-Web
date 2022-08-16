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
            
            
            <section class="row">
                
                <div class="col-12 GlobalForm2">
                    <h3>Cree su cuenta de usuario</h3>
                    <form class="Formulario2" id="Registro" onsubmit="return FormValido()"  action="persistUser" >
                        <div class="form-group">
                            <label for="exampleInputEmail1">Correo de usuario:</label>
                            <input type="email" class="form-control" name="correo" id="micorreo" aria-describedby="emailHelp" required  > <!-- onblur="return EmailDisp()" -->
                            <!--
                            <span id="miLabel"><small id="emailHelp" class="form-text text-muted"></small></span>
                            <input type="text" id="miLabel" class="Esconder">
                            -->
                        </div>
                        
                        <div class="form-group">
                            <label for="Ipwd">Contraseña:</label>
                            <input type="password" class="form-control" id="Ipwd1" name="password" required>
                        </div>
                        <div class="form-group">
                            <label for="Ipwd">Repita la Contraseña:</label>
                            <input type="password" class="form-control" id="Ipwd2" name="password2" required onblur="return PwdIguales()">
                            <small id="Imsg"></small> <!-- Aqui quiero que se muestre el mensaje -->
                        </div>
                        <div class="form-group">
                            <label for="nameUser">Nombre:</label>
                            <input type="text" class="form-control" id="nameUser" name="nombre" required />
                        </div>
                        <div class="form-group">
                            <label for="Address1">Direccion: </label>
                            <input type="text" class="form-control" id="Address1" name="direccion" />
                        </div>
                        <div class="form-group">
                            <label for="Address2">Codigo Postal: </label>
                            <input type="text" class="form-control" id="Address2" name="postal" required pattern="[0-9]{5}" />
                        </div>
                        <div class="form-group">
                            <label for="Socila1">Facebook: </label>
                            <input type="text" class="form-control" name="facebook" id="Socila1">
                        </div>
                        <div class="form-group">
                            <label for="Social2">Twitter: </label>
                            <input type="text" class="form-control" name="twitter" id="Social2">
                        </div>
                        <div class="form-group">
                            <label for="telefono">Telefono: </label>
                            <input type="tel" class="form-control" id="telefono" name="telefono" required pattern="[0-9]{9}"/>
                            
                        </div>
                        
                        <button type="submit" class="btn btn-primary" >Crear Cuenta</button>
                    </form>
                    
                </div>
                
            </section>

            <%@include file="WEB-INF/jspf/Pie.jspf" %>
        </div>
        
        <!-- Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

        <!-- Mis scrip -->
        <script src="/Practica_DAW/js/funciones.js" type="text/javascript"></script>
        
    </body>
</html>
