package BD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ControladorComentarios", urlPatterns = {"/Solicitud", "/altaComent"})
public class ControladorComentarios extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        EntityManager em = emf.createEntityManager();
        
        HttpSession session = request.getSession();

        TypedQuery<Comentarios> query;
        List<Comentarios> lc;
        ArrayList<Comentarios> ac = new ArrayList<>();
        String vista ="";
        String accion = request.getServletPath();
        
        switch(accion){
            case "/Solicitud":
                System.out.println("Entro en solicitud");
                /*
                        Comprobar que el id del producto es viable
                */
                System.out.println("Aqui con: "+request.getParameter("id"));
                String idArtC = (String)request.getParameter("id");
                System.out.println("El id es: "+idArtC);
                TypedQuery<Articulos> queryA = em.createNamedQuery("Articulos.findById", Articulos.class);
                queryA.setParameter("id", Integer.parseInt(idArtC));
                System.out.println("Fallo en el set id");
                List<Articulos> la = queryA.getResultList();
                
                if(la != null && la.size() > 0){
                     System.out.println("Cojo los articulos: "+la.size());
                    /*
                            Enviarle el id al formulario
                    */

                    int idNec = Integer.parseInt(idArtC);

                    request.setAttribute("idArtC", idNec);
                    vista = "/NuevoComent.jsp";
                    
                }else{
                   System.out.println("Es nulo o pasa algo");
                    session.setAttribute("msg", "Error en el id del Articulo");
                    vista = "/home";
                }
                System.out.println("Salgo de solicitud");
                
                break;


            case "/altaComent":
                System.out.println("Entro en alta");
                String idA = (String)request.getParameter("id");
                System.out.println("Mi idA es: "+idA);
                /*
                        Obtener su lista de Comentarios
                */
                    //Cojo la info de mi articulo
                queryA = em.createNamedQuery("Articulos.findById", Articulos.class);
                queryA.setParameter("id", Integer.parseInt(idA));
                la = queryA.getResultList();
                System.out.println("Realizo la consulta");
                
                if(la != null && la.size() > 0){
                    System.out.println("El articulo existe");
                    Articulos a = la.get(0);
                    
                    //Aqui debo coger la info del usuario, con un atributo de Articulos
                    Object idU = session.getAttribute("id");
                    System.out.println("El que esta en sesion ahora mismo es: "+idU);
                    TypedQuery<Usuarios> queryU = em.createNamedQuery("Usuarios.findById", Usuarios.class);  
                    queryU.setParameter("id", idU);
                    List<Usuarios> lu = queryU.getResultList();
                    if(lu != null & lu.size() > 0){
                        System.out.println("El usuario existe");
                        Usuarios u = lu.get(0);
                        
                        //Añado ya el comentario
                        try{
                            Comentarios c = new Comentarios();
                            c.setIdArticulo(a);
                            c.setIdUser(u);
                            c.setTexto((String)request.getParameter("comentario"));
                            c.setPrivacidad((String)request.getParameter("Privacidad"));
                            System.out.println("Voy a persist c");
                            persist(c);
                        }catch(Exception ex){
                            System.out.println(ex);
                            System.out.println("Error: Imposible persistir  comentario: ");
                        }
                        //Volvemos a detalles del articulo comentado
                        vista = "/detalles?id="+idA;
                            
                    }else{
                        session.setAttribute("msg", "Error en el id del Uusario");
                        vista = "/home";
                    }
                    
                }
                
                break;

            default :
                
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
