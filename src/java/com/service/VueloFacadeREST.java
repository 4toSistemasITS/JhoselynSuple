/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Avion;
import com.model.Pasajero;
import com.model.Vuelo;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path("com.model.vuelo")
public class VueloFacadeREST extends AbstractFacade<Vuelo> {

    @PersistenceContext(unitName = "AeropuertoPU")
    private EntityManager em;
    @EJB 
    AvionFacadeREST avionFacadeREST;
    PasajeroFacadeREST pasajeroFacadeREST;

    public VueloFacadeREST() {
        super(Vuelo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Vuelo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Vuelo entity) {
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
    public Vuelo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Vuelo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Vuelo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    public String crear(@FormParam("num_vuelo") int numVuelo, @FormParam("origen") String origen, @FormParam("destino") String destino, @FormParam("hora_salida") Date horaSalida,@FormParam("hora_llegada") Date horaLlegada,
            @FormParam("fecha_salida") Date fechaSalida,@FormParam("fecha_llegada") Date fechaLlegada,@FormParam("idAvion") int idAvion,@FormParam("idPasajero") int idPasajero) {
        String mensaje = "{\"exitoso\":false}";
        Avion avi=avionFacadeREST.find(idAvion);
        Pasajero pa=pasajeroFacadeREST.find(idPasajero);
        try {
            create(new Vuelo(numVuelo, origen, destino,horaSalida,horaLlegada,fechaSalida,fechaLlegada,false,avi,pa));
            mensaje = "{\"exitoso\":true}";
        } catch (Exception e) {
            System.out.println(e);
        }

        return mensaje;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
