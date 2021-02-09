package com.francisco.loja.resource;

import com.francisco.loja.dao.ProjetoDAO;
import com.francisco.loja.modelo.Projeto;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("projetos")
public class ProjetoResource {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca() {
		Projeto projeto = new ProjetoDAO().busca(1L);
		return projeto.toXML();
	}
}
