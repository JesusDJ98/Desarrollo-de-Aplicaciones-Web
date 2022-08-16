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


@WebServlet(name = "user", urlPatterns = {"/acceso",
"/login",
"/logout",
"/alta",
"/persistUser",
"/recuperarPwd"})

public class ControladorUsuario extends HttpServlet {

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
        
        TypedQuery<Usuarios> query;
        HttpSession session = request.getSession();
        List<Usuarios> lu= new ArrayList<>();
        Usuarios u;
        String accion = request.getServletPath();
        String vista;
        String msg;
        String email;
        String pwd;
        String name;
        
        switch (accion) {
            case "/alta":    
                vista="/NuevoUsuario.jsp";
                break;
                
            case "/persistUser":
                name = request.getParameter("nombre");
                email = request.getParameter("correo");
                pwd= request.getParameter("password");
                String codP = request.getParameter("postal");
                String number = request.getParameter("telefono");
                String dir = request.getParameter("direccion");
                String tw = request.getParameter("twitter");
                String fb = request.getParameter("facebook");
                
                
                if (name!=null && email!=null && pwd!=null && number!=null && codP!=null) {
                    u = new Usuarios();
                    System.out.println("Mi id: "+u.getId());
                    u.setCorreo(email);
                    u.setContraseña(pwd);
                    u.setNombre(name);
                    u.setDireccion(dir);
                    u.setPostal(Integer.parseInt(codP));
                    u.setTwitter(tw);
                    u.setFacebook(fb);
                    u.setTelefono(Integer.parseInt(number));
                    
                    //u.setComent(new ArrayList<>());
                    try {
                        persist(u);
                        msg="<p style='background:green;color:white'>Usuario: " + name + " insertado con éxito</p>";
                    }catch (Exception ex) {
                         msg="<p style='background:red;color:white'>Error al insertar el usuario: " + name + " en la BD</p>";
                    } 
                } else {
                    msg="<p style='background:red;color:white'>Error al insertar el usuario: " + name + " en la BD</p>";
                }                
                request.setAttribute("msg", msg);
                vista="/Formulario.jsp";
                break;  
                
            case "/login":
                email = request.getParameter("correouser");
                pwd = request.getParameter("pwduser");
                query = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
                query.setParameter("correo",email );
                
                lu = query.getResultList();
                if(lu.size() <= 0){
                    msg ="<p style='background:red;color:white'>Usuario: " + email + " no existe</p>";
                    vista="/Formulario.jsp"; 
                }else
                {
                    if(lu.get(0).getContraseña().equals(pwd)){
                        System.out.println("El id antiguo: "+session.getAttribute("id"));
                        session.setAttribute("id", lu.get(0).getId());
                        System.out.println("El id nuevo: "+session.getAttribute("id"));
                        session.setAttribute("correo", lu.get(0).getCorreo());
                        session.setAttribute("nombre", lu.get(0).getNombre());
                        session.setAttribute("log", "true");
                        msg ="<p style='background:green;color:white'>Usuario: " + email + " sesion iniciada</p>";
                        vista="/home";
                    }
                    else{
                        msg ="<p style='background:blue;color:white'>Usuario: " + email + "contraseña incorrecta </p>";
                        vista="/Formulario.jsp";
                    }
                }
                request.setAttribute("msg", msg);
                request.setAttribute("usuarios", lu);
                break;
                
            case "/logout":
                session.invalidate();
                msg ="<p style='background:green;color:white'>Sesion cerrada correctamente</p>";
                request.setAttribute("msg", msg);
                vista="/home";
                break;
            
            case "/acceso":
                vista="Formulario.jsp";
                break;    
                
            case "/recuperarPwd":
                vista="RecuperarPass.jsp";
                break;
                
            default: vista="/home";
                
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
