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
                    <form class="Formulario2" action="altaArt" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="Categoria">Categoria: </label>
                            <input type="text" class="form-control" name="categoria" required>
                        </div>
                        <div class="form-group">
                            <label for="Nombre">Nombre: </label>
                            <input type="text" class="form-control" name="nombre" required>
                        </div>
                        <div class="form-group">
                            <label for="comentarios">Descripción: </label>
                            <textarea class="form-control" name="descripcion" rows="10" cols="40" ></textarea>
                            
                        </div>
                        <div class="form-group">
                            <label for="estado">Estado: </label>
                            <input type="range" class="form-control" id="estado" name="estado" min="0" max="30" list="tickmarks"  />
                            
                            <!-- Aun no soportado por los buscadores -->
                            <datalist id="tickmarks">
                                <option value="0" label="Antiguo">
                                <option value="10" label="Deteriorado">
                                <option value="20" label="Semi-Nuevo">
                                <option value="30" label="Nuevo">
                            </datalist>
                            <small class="form-text text-muted ">
                                Antiguo || Deteriorado || Semi-Nuevo || Nuevo
                            </small>
                            
                        </div>
                        <div class="form-group">
                            <label for="año">Año de adquisición: </label>
                            <input type="year" class="form-control" id="año" name="fecha" min="0" />
                        </div>
                        <div class="form-group">
                            <label for="precio">Precio: </label>
                            <input type="number" class="form-control" id="precio" name="precio" min="0" step="any" required/>
                        </div>
                        
                        <div class="form-group">
                            <label for="ImagenPublicar">Imagen: </label> <br>
                            <input type="file" id="ImagenPublicar" name="file" value="" />
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