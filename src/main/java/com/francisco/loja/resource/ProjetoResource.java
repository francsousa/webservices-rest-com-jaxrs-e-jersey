package com.francisco.loja.resource;

import com.francisco.loja.dao.ProjetoDAO;
import com.francisco.loja.modelo.Projeto;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("projetos")
public class ProjetoResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") long id) {
		Projeto projeto = new ProjetoDAO().busca(id);
		return projeto.toXML();
	}
}
