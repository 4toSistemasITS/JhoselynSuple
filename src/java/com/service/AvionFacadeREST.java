/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Aeropuerto;
import com.model.Avion;
import static com.model.Avion_.idAvion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jhoselin Trujillo
 */
@Stateless
@Path("com.model.avion")
public class AvionFacadeREST extends AbstractFacade<Avion> {

    @PersistenceContext(unitName = "AeropuertoPU")
    private EntityManager em;
    @EJB
    AeropuertoFacadeREST aeropuertoFacadeREST;

    public AvionFacadeREST() {
        super(Avion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Avion entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Avion entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Avion find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Avion> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Avion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @POST
    @Path("crear")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String crear(@FormParam("compañia") String compañia, @FormParam("capacidad") int capacidad, @FormParam("modelo") String modelo, @FormParam("id_aeropuerto") int aeropuerto) {
        String mensaje = "{\"exitoso\":false}";
        Aeropuerto aero=aeropuertoFacadeREST.find(aeropuerto);
        try {
            create(new Avion(compañia, capacidad, modelo, aero));
            mensaje = "{\"exitoso\":true}";
        } catch (Exception e) {
            System.out.println(e);
        }

        return mensaje;
    }
    
    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
     public String editarA(@FormParam("compañia") String compañia, @FormParam("capacidad") int capacidad, @FormParam("modelo") String modelo, @FormParam("id_aeropuerto") int aeropuerto) {
         String mensaje="{\"exitoso\":false,\"motivo\":";
         Avion a= editarA(idAeropuerto); 
         
         if (a != null){
             a.setCompañia(compañia);
             a.setCapacidad(capacidad);
             a.setModelo(modelo);
             a.setIdAeropuerto(idAeropuerto);
         try{
           edit(a);
           mensaje="{\"exitoso\":true";
         }catch(Exception e){
             mensaje+="\"Excepcion en base\"";
         }
         }else{
             mensaje+="\"datos no correctos\"";
         }
         mensaje+="}";
         return mensaje;
     }
       @POST
    @Path("eliminar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String eliminar(@FormParam ("idavion")int idavion){
        String mensaje="{\"exitoso\":false,\"motivo\":";
        Avion e=BuscarPorId(idavion);
        if (e!=null){
            e.setEliminado(true);
            edit(e);
            mensaje="{\"exitoso\":true";
        }else{
            mensaje+="\"Datos no correctos\"";
        }
        
        mensaje+="}";
        return mensaje;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
     public Avion editarA(int idUsuario){
      
        TypedQuery<Avion> qry;
        qry = getEntityManager().createQuery("SELECT a FROM Avion a WHERE a.idAvion = :idAvion AND a.eliminado = :eliminado",Avion.class);
        qry.setParameter("idavion",idAvion);
        qry.setParameter("eliminado", false);
        try{
            return qry.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
     }  
      public Avion BuscarPorId(int idavion){
        TypedQuery<Avion>qry;
        qry=getEntityManager().createQuery("SELECT a FROM Avion a WHERE a.idAvion = :idAvion and a.eliminado = :eliminado", Avion.class);
        qry.setParameter("idavion", idavion);
        qry.setParameter("eliminado", false);
        try {
            return qry.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
