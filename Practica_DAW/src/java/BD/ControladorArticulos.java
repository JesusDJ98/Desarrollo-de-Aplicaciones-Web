package BD;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "controladorArt", urlPatterns = {"/home", "/articulos",
    "/publicar",
    "/altaArt",
    "/detalles",
    "/filtro",
    "/favoritos",
    "/delFav",
    "/addFav",
    
    "/idValido",
    "/invalidar"
})
@MultipartConfig
public class ControladorArticulos extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica_DAWPU");
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EntityManager em = emf.createEntityManager();
        
        HttpSession session = request.getSession();

        TypedQuery<Articulos> query;
        List<Articulos> lr;
        ArrayList<Articulos> ar = new ArrayList<>();
        int cont;
        String msg;
        String idf;
        List<Articulos> lid = new ArrayList<Articulos>();
        Articulos art;
        String vista ="";
        String accion = request.getServletPath();
        
        switch(accion){
            case "/home":
                query = em.createQuery("SELECT r FROM Articulos r ORDER BY r.id DESC", Articulos.class);
                lr = query.getResultList();
                
                
                cont = 0;
                while(cont < lr.size() && cont < 4){
                    art = new Articulos();
                    art.setId(lr.get(cont).getId());
                    art.setNombre(lr.get(cont).getNombre());
                    art.setCategoria(lr.get(cont).getCategoria());
                    art.setEstado(lr.get(cont).getEstado());
                    art.setPrecio(lr.get(cont).getPrecio());
                    art.setAnio(lr.get(cont).getAnio());
                    art.setDescripcion(lr.get(cont).getDescripcion());
                    art.setCreador(lr.get(cont).getCreador());
                    art.setComent(lr.get(cont).getComent());
                    //enviar += " -- Nuevo "+cont+": "+art.getId();
                    ar.add(art);
                    cont++;
                }
                //request.setAttribute("msg", enviar);
                request.setAttribute("articulos", ar);
                session.setAttribute("ref", "3");
                
                vista="index.jsp";
                break;

            case "/articulos":
                System.out.println("Entro en articulos");
                System.out.println("Tamaño de lid: "+lid.size());
                query = em.createNamedQuery("Articulos.findAll", Articulos.class);
                lr = query.getResultList();
                
                ar = new ArrayList<>();
                cont = 0;
                while(cont<lr.size()){
                    art = new Articulos();
                    art.setId(lr.get(cont).getId());
                    art.setNombre(lr.get(cont).getNombre());
                    art.setCategoria(lr.get(cont).getCategoria());
                    art.setEstado(lr.get(cont).getEstado());
                    art.setPrecio(lr.get(cont).getPrecio());
                    art.setAnio(lr.get(cont).getAnio());
                    art.setDescripcion(lr.get(cont).getDescripcion());
                    art.setCreador(lr.get(cont).getCreador());
                    art.setComent(lr.get(cont).getComent());
                    
                    ar.add(art);
                    cont++;
                }
                System.out.println("Cantidad: "+lr.size());
                
                request.setAttribute("articulos", ar);
                session.setAttribute("ref", "2");

                vista = "/Articulos.jsp";
                break;
            
            case "/publicar":
                vista = "/Publicar.jsp";
                break;
                
            case "/altaArt":

                System.out.println("Entro en alta");
                String name = request.getParameter("nombre");
                String cat = request.getParameter("categoria");
                String pvp = request.getParameter("precio");
                String desc = request.getParameter("descripcion");
                String estado = request.getParameter("estado");
                
                String state = "";
                int stdo = Integer.parseInt(estado);
                if(stdo <10){
                    state = "Antiguo";
                }else if(stdo <20){
                    state = "Deteriorado";
                }else if(stdo <30){
                    state = "Semi-nuevo";
                }else{
                    state = "Nuevo";
                }
                String anio = request.getParameter("fecha");
                
                System.out.println("cat:" + cat + " - nombre:" + name + " - estado:" + state +
                        " - descripcion: "+desc + " - fecha: "+anio);
                if (cat != null && name != null && pvp != null) {
                    String categoriaReal = "";
                    switch(cat.toUpperCase()){
                        case "HERRAMIENTA":
                        case "HERRAMIENTAS":
                            categoriaReal = "Herramientas";
                            break;

                        case "DEPORTE":
                        case "DEPORTES":
                            categoriaReal = "Deportes";
                            break;

                        case "ELECTRONICA":
                        case "ELECTRONICO":
                            categoriaReal = "Electronica";
                            break;

                        default: categoriaReal = "Otros";
                    }
                    System.out.println("Paso este try");
                    
                    try {
                        //Hago la consulta a Usuarios
                        System.out.println("Entro en otro try: "+session.getAttribute("id")+",  "+session.getAttribute("correo"));
                        
                        TypedQuery<Usuarios> queryU = em.createNamedQuery("Usuarios.findById", Usuarios.class);
                        queryU.setParameter("id", session.getAttribute("id"));
                        List<Usuarios> lu = queryU.getResultList();
                        System.out.println("Paso la consulta");
                        
                        if(lu == null){
                            throw new Exception("El id del usuario no existe");
                        }
                        System.out.println("El lu esta: ");
                        art = new Articulos();
                        Long id = art.getId();  //Aqui es nulo
                        Long idImagen = new Long(0);
                        art.setNombre(name);
                        art.setCategoria(categoriaReal);
                        art.setPrecio(Float.parseFloat(pvp));
                        art.setDescripcion(desc);
                        art.setEstado(state);
                        art.setAnio(anio);
                        Usuarios u = lu.get(0);
                        art.setCreador(u);
                        
                        //a.setImagen(img);
                        persist(art);
                        System.out.println("Artículo: " + name + " creado con id: "+id);
                        msg = "<p class='ok'>Artículo: " + name + " publicado adecuadamente</p>";
                        
                        /*
                            Miro mi id real
                        Cojo todo el listado, empezando por el ultimo, y cojo este
                        */
                        query = em.createQuery("SELECT r FROM Articulos r ORDER BY r.id DESC", Articulos.class);
                        lr = query.getResultList();
                        if(lr.size() > 0){  //Hay algo
                            Articulos ArtAux = lr.get(0);
                            idImagen = ArtAux.getId();
                            System.out.println("El id del nuevo es: "+idImagen);
                        }

                        
                        final Part filePart = request.getPart("file");
                        if (filePart != null) {

                            String nombre = filePart.getName();
                            Long tamano = filePart.getSize();
                            String file = filePart.getSubmittedFileName();
                            
                            String relativePathFolder = "imagenes";
                            String absolutePathFolder = getServletContext().getRealPath(relativePathFolder);
                            System.out.println("Pongo el filefolder");

                            File folder = new File(absolutePathFolder);
                            if (folder.exists()) {
                            } else {
                                folder.mkdir();
                            }                            
                            
                            
                            System.out.println(absolutePathFolder+ File.separator +idImagen+".jpg");
                            File f = new File(absolutePathFolder+ File.separator + idImagen+".jpg");
                            
                            //String nfp = f.getAbsolutePath();

                            OutputStream p = new FileOutputStream(f);
                            InputStream filecontent;
                            filecontent = filePart.getInputStream();
                            System.out.println("Tam: " +  filePart.getSize());

                            
                            int read = 0;
                            final byte[] bytes = new byte[1024];
                            while ((read = filecontent.read(bytes)) != -1) {
                                p.write(bytes, 0, read);
                            }

                            p.close();
                            filecontent.close();
                            
                        }

                    } catch (Exception ex) {
                        System.out.println(ex);
                        System.out.println("Error: Imposible persistir  articulo: " + name);
                        msg = "<p class='error'>Error: Artículo " + name + " no publicado</p>";
                    }
                } else {
                    System.out.println("Error: datos incorrectos");
                    msg = "<p class=\"error\">Error: Faltan datos</p>";
                }

                request.setAttribute("msg", msg);

                vista = "/Publicar.jsp";
                break;
                
            case "/detalles":
                System.out.println("Entro en detalles");
                idf = request.getParameter("id");
                System.out.println("El id es: "+idf);
                query = em.createNamedQuery("Articulos.findById", Articulos.class);
                query.setParameter("id", Integer.parseInt(idf));
                lr = query.getResultList();
                art = new Articulos();
                boolean continuar = false;
                if(lr != null){
                    if(lr.size() > 0){
                        art = lr.get(0);
                        System.out.println("La consultta devuelve: "+lr.size());
                        continuar = true;
                    }else{
                        System.out.println("La consultta devuelve:  VACIO");
                    }
                }
                
                
                List<Comentarios> lc = new Vector<Comentarios>();
                if(continuar){
                    System.out.println("Voy a coger los comentarios publicos en detalles ");
                    String s = "Publico";
                    String consulta = "SELECT r FROM Comentarios r where r.idArticulo.id = "+ art.getId() + " AND r.privacidad = '"+s+"'";
                    System.out.println("La consulta que hago es: "+consulta);
                    TypedQuery<Comentarios> queryC = em.createQuery(consulta, Comentarios.class);
                    lc = queryC.getResultList();
                    if(lc == null){
                        System.out.println("Es nulo");
                    }else{
                        
                        System.out.println("No es nulo");
                        if(lc.size() > 0){
                            System.out.println("Tiene comentarios");
                        }else{
                            System.out.println("Esta vacio");
                        }
                    }
                    
                    
                    System.out.println("Ahora cogere los comentarios que he hecho yo");
                    String s1 = "Personal";
                    String consulta2 = "SELECT r FROM Comentarios r where r.idArticulo.id = "+art.getId() + " AND r.privacidad = '"+s1+"' AND "+
                            "r.idUser.id = "+session.getAttribute("id");
                    queryC = em.createQuery(consulta2, Comentarios.class);
                    List<Comentarios> lc2 = queryC.getResultList();
                    //Los añado a lc
                    if(lc2 != null){
                        for (int i = 0; i < lc2.size(); i++) {
                            if( lc.contains(lc2.get(i))){
                                
                            }else{
                                lc.add(lc2.get(i));
                            }
                            
                        }
                    }else{
                        System.out.println("lc2 Es nulo");
                    }
                    
                    System.out.println("Por ultimo cogere los comentarios que van para mi");
                    String s2 = "Vendedor";
                    String consulta3 = "SELECT r FROM Comentarios r where r.idArticulo.id = "+art.getId() + " AND r.privacidad = '"+s2+"' AND "+
                            "r.idArticulo.Creador.id = "+session.getAttribute("id");
                    queryC = em.createQuery(consulta3, Comentarios.class);
                    List<Comentarios> lc3 = queryC.getResultList();
                    //Los añado a lc
                    if(lc3 != null){
                        for (int i = 0; i < lc3.size(); i++) {
                            if(lc != null && lc.contains(lc3.get(i))){
                                
                            }else{
                                lc.add(lc3.get(i));
                            }
                        }
                    }else{
                        System.out.println("lc3 Es nulo");
                    }
                    
                    System.out.println("La consulta que hago es: "+consulta+ " || "+consulta2 + " || "+consulta3);
                   
                }
                System.out.println("envio los comentarios");
                
                request.setAttribute("articulos", art);
                request.setAttribute("comentarios", lc);
                vista = "/MiniInfo.jsp";
                break;

                
            case "/filtro":
                String categoria = request.getParameter("Categoria");
                String CodP = request.getParameter("CodP");
                String precio = request.getParameter("precio");
                
                String consulta = "";
                
                boolean catVacia = false;
                boolean nameVacia = false;
                boolean pvpVacia = false;
                
                if(categoria == null || categoria.equals("") ||  categoria.equals("Categoria") ){
                    catVacia = true;
                }else{
                    consulta = "WHERE a.categoria = '"+categoria+"' ";
                }
                if(CodP == null || CodP.equals("")){
                    nameVacia = true;
                }else{
                    if(catVacia){
                        consulta = "WHERE a.Creador.postal = "+CodP+" ";
                    }else{
                        consulta += " AND a.Creador.postal  = "+CodP+" ";
                    }
                }
                if(precio == null || precio.equals("")){
                    pvpVacia = true;
                }else{
                    if(catVacia && nameVacia){
                        consulta = "WHERE a.precio < "+precio+" ";
                    }else{
                        consulta += " AND a.precio < "+precio+" ";
                    }
                }
                System.out.println("Mi consulta: SELECT a FROM Articulos a "+consulta);
                if(catVacia && nameVacia && pvpVacia){  //Todas vacias
                    query = em.createNamedQuery("Articulos.findAll", Articulos.class);
                }else{
                    query = em.createQuery("SELECT a FROM Articulos a " + consulta, Articulos.class);
                }
                
                lr = query.getResultList();
                System.out.println("Obtenemos: "+lr.size());
                
                ar = new ArrayList<>();
                cont = 0;
                while(cont<lr.size()){
                    art = new Articulos();
                    art.setId(lr.get(cont).getId());
                    art.setNombre(lr.get(cont).getNombre());
                    art.setCategoria(lr.get(cont).getCategoria());
                    art.setEstado(lr.get(cont).getEstado());
                    art.setPrecio(lr.get(cont).getPrecio());
                    art.setAnio(lr.get(cont).getAnio());
                    art.setDescripcion(lr.get(cont).getDescripcion());
                    art.setCreador(lr.get(cont).getCreador());
                    art.setComent(lr.get(cont).getComent());
                    
                    ar.add(art);
                    cont++;
                }
                
                System.out.println("Que pasa aqui");
                //request.setAttribute("ref", "2");
                request.setAttribute("articulos", ar);
                vista = "Articulos.jsp";
                break;
            
                
            case "/favoritos":
                //Funciona
                if(session.getAttribute("lart") != null){
                    request.setAttribute("articulos", session.getAttribute("lart"));
                }else{
                    request.setAttribute("articulos", lid);
                }
                System.out.println("Paso el setAtt -->"+session.getAttribute("lart"));
                System.out.println("Paso el lid -->"+lid.size());
                
                session.setAttribute("ref", "1");
                
                vista = "/Favoritos.jsp";
                break;     
     
     
                
            case "/addFav":
                System.out.println("Entro en addFav");
                idf = request.getParameter("id");
                System.out.println("El id: "+idf);
                if (idf != null) {
                    query = em.createNamedQuery("Articulos.findById", Articulos.class);
                    query.setParameter("id", Integer.parseInt(idf));
                    lr = query.getResultList();
                    System.out.println("Lo que devuelve: "+lr.size());
                    if (lr != null) { // Esta el artículo
                        if(session.getAttribute("lart") == null){
                            System.out.println("Mi lart es nulo");
                        }else{
                            System.out.println("No es nulo");
                            lid = (List<Articulos>) session.getAttribute("lart");
                        }
                        
                        System.out.println("Cuando cojo el lid hay: "+lid.size());
                        if(lid.contains(lr.get(0))){    //Sino no esta ya introducido
                            msg = "<p class=\"ok\">Error: Artículo ya está registrado en favoritos.<p>";
                        }else{
                            art = lr.get(0);
                            lid.add(art);
                            msg = "<p class=\"ok\">Artículo registrado en favoritos.<p>";
                            
                        }
                        System.out.println("En lid tenemos: "+lid.size());
                        session.setAttribute("lart", lid);
                    } else { //No está el art
                        msg = "<p class=\"ok\">ERROR: Artículo registrado en favoritos. No existía.<p>";
                    }
                } else { // Id no enviado
                    msg = "<p class=\"error\">Artículo No registrado en favoritos. Falta Id.<p>";
                }

                request.setAttribute("msg", msg);
                vista = "/articulos";
                break; 
                
            case "/delFav":
                System.out.println("Entro en delFav");
                idf = request.getParameter("id");
                int i = Integer.parseInt(idf);
                if(session.getAttribute("lart") == null){
                    System.out.println("Mi lart es nulo");
                }else{
                    System.out.println("No es nulo --> "+session.getAttribute("lart"));
                    lid = (List<Articulos>) session.getAttribute("lart");
                }
                System.out.println("Lid tiene: "+lid.size());
                
                //Bucamos el indice pasado por parametroç
                int pos = 0;
                int contador = 0;
                while(contador < lid.size()){
                    Articulos next = lid.get(contador);
                    if(next.getId() == i ){
                        System.out.println("Encontrado "+contador);
                        pos = contador;
                        contador = lid.size();
                    }
                    contador++;
                }
                
                lid.remove(pos);
                session.setAttribute("lart", lid);
                vista = "/favoritos";
                break;
                
                
                
            case "/idValido":
                //No funcional...
                System.out.println("Entro aqui en validar");
                idf = request.getParameter("correo");
                System.out.println("El parametro de correo que recibo es: "+idf);
                TypedQuery<Usuarios> queryU = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
                queryU.setParameter("correo", idf);
                List<Usuarios> lu = queryU.getResultList();
                System.out.println("Listado: "+lu.size());
                if (lu.size() > 0) {
                    request.setAttribute("miLabel", "No");
                } else {
                    request.setAttribute("miLabel", "Ok");
                }
                System.out.println("Hago el setAtribute");

                vista = "idvalido.jsp";
                break;      
      
      
                
            case "/invalidar":
                session.invalidate();
                vista = "/favoritos.jsp";
                break;    
                
            default: vista = "/home";
        }
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }


}
