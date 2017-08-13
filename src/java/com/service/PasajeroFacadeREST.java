/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Pasajero;
import java.util.List;
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
@Path("com.model.pasajero")
public class PasajeroFacadeREST extends AbstractFacade<Pasajero> {

    @PersistenceContext(unitName = "AeropuertoPU")
    private EntityManager em;

    public PasajeroFacadeREST() {
        super(Pasajero.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Pasajero entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Pasajero entity) {
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
    public Pasajero find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pasajero> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pasajero> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    public String crear(@FormParam("nombre") String nombre, @FormParam("direccion") String direccion, @FormParam("pasaporte") String pasaporte, @FormParam("num_pasajero")int num_pasajero) {
        String mensaje = "{\"exitoso\":false}";
        try {
            create(new Pasajero(nombre, direccion, pasaporte, num_pasajero,false));
            mensaje = "{\"exitoso\":true}";
        } catch (Exception e) {
            System.out.println(e);
        }

        return mensaje;
    }
    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
     public String editarA(@FormParam("nombre") String nombre, @FormParam("direccion") String direccion, @FormParam("pasaporte") String pasaporte, @FormParam("num_pasajero") int num_pasajero) {
         String mensaje="{\"exitoso\":false,\"motivo\":";
         Pasajero a= editarA(idPasajero); 
         
         if (a != null){
             a.setNombre(nombre);
             a.setDireccion(direccion);
             a.setPasaporte(pasaporte);
             a.setNumPasajero(num_pasajero);
             a.setIdPasajero(idPasajero);
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
    public String eliminar(@FormParam ("idpasajero")int idpasajero){
        String mensaje="{\"exitoso\":false,\"motivo\":";
        Pasajero e=BuscarPorId(idpasajero);
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
    public Pasajero BuscarPorId(int idpasajero){
        TypedQuery<Pasajero>qry;
        qry=getEntityManager().createQuery("SSELECT p FROM Pasajero p WHERE p.idPasajero = :idPasajero and p.eliminado = :eliminado", Pasajero.class);
        qry.setParameter("idpasajero", idpasajero);
        qry.setParameter("eliminado", false);
        try {
            return qry.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
