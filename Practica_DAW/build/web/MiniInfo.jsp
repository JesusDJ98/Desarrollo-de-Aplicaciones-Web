<%@page import="java.util.Vector"%>
<%@page import="BD.Comentarios"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BD.Articulos"%>
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
            
            <% Articulos product = (Articulos)request.getAttribute("articulos"); %>

            <h3 class="text-center">Información especifica de <%= product.getNombre() %></h3>
            <br>
            <div class="GlobalD">
                <div class="elementoD">
                    <div class="col-12 SeparacionMI">
                        <div class="arribaD">
                            <% String url = "/Practica_DAW/imagenes/"+ product.getId() +".jpg"; %>
                            <img id="ImgFormInf" src="<%= url %>" alt="Imagen">
                        </div>
                    </div>
                    <div class="parteAbajoD" >
                        <div class="dentroizqMI">
                            <label for="InfoV" class="DatosP"> <b>Vendedor: </b></label> <br>
                            <label for="InfoCodP" class="DatosP"> <b>Postal: </b></label> <br>
                            <label for="InfoContacto" class="DatosP"> <b>Contacto: </b></label> <br>
                            <label for="InfoCat" class="DatosP"><b>Categoria: </b></label> <br>
                            <label for="InfoNombre" class="DatosP"><b>Nombre: </b></label> <br> 
                            <label for="InfoDescripcion" class="DatosP2"><b>Descripción: </b></label> <br>
                            <label for="InfoEstado" class="DatosP"><b>Estado: </b></label> <br>
                            <label for="InfoAño" class="DatosP"><b>Adquisición: </b></label> <br>
                            <label for="InfoPrecio" class="DatosP"> <b>Precio: </b></label> <br>
                            
                        </div>
                        <div class="dentroderMI">
                            <label for="RellenarV" class="DatosP"><%= product.getCreador().getNombre() %></label> <br>
                            <label for="RellenarCodP" class="DatosP"><%= product.getCreador().getPostal() %></label> <br>
                            <label for="RellenarContacto" class="DatosP"><%= product.getCreador().getTelefono() %></label> <br>
                            <label for="RellenarCat" class="DatosP"><%= product.getCategoria()%></label> <br>
                            <label for="RellenarNombre" class="DatosP"><%= product.getNombre() %></label> <br> 
                            <label for="RellenarDescripcion" class="DatosP2_1"><%= product.getDescripcion()%></label> <br>
                            <label for="RellenarEstado" class="DatosP"><%= product.getEstado()%></label> <br>
                            <label for="RellenarAño" class="DatosP"><%= product.getAnio()%></label> <br>
                            <label for="RellenarPrecio" class="DatosP"><%= product.getPrecio()%></label> <br>
                        </div>
                    </div>
                </div>
                        
                <hr> 
                <h3 class="text-center"> Comentarios </h3>
                <hr>
                
                
                <% Object obj = request.getAttribute("comentarios"); %>
                <% if( obj == null ){ %>
                    <h5 class="text-center">No hay comentarios</h5>
                <% }else { %>
                    <% Vector<Comentarios> coment = (Vector) obj; %>
                    <% if(coment.size() > 0){ %>
                        <div class="MisComent">
                            <% for (int i = 0; i < coment.size(); i++) { %>
                                <div class="CadaComent">
                                    <% Comentarios c = coment.get(i); %>
                                    <small> <b>Usuario: <%= c.getIdUser().getNombre() %></b>, <%= c.getPrivacidad() %> </small>
                                    <label class="DatosP2_1"> <%= c.getTexto() %> </label>
                                </div>
                            <% } %>
                        </div>    
                    <% }else{ %>
                        <h5 class="text-center">No hay comentarios</h5>
                    <% } %>
                <% } %>
                
            </div>
             
            <div class="CamposPver">
                <% if (session.getAttribute("log") == "true") {%> 
                    <% String dir = "Solicitud?id="+product.getId(); %>
                    <td><a href="<%= dir %>">+Comentarios</a></td>
                <% } %>
                <% String miref = (String)session.getAttribute("ref"); %>
                <% if (miref.equals("1")) {%> 
                    <td><a href="favoritos">+Atras</a></td>
                <% }else if(miref.equals("2")){ %>
                    <td><a href="articulos">+Atras</a></td>
                <% }else if(miref.equals("3")){ %>
                    <td><a href="home">+Atras</a></td>
                <% } %>
            </div>


            <%@include file="WEB-INF/jspf/Pie.jspf" %>
        </div>


        <!-- Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    </body>
</html>