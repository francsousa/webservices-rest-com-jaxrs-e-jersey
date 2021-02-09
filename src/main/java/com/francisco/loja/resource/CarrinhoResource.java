package com.francisco.loja.resource;

import com.francisco.loja.dao.CarrinhoDAO;
import com.francisco.loja.modelo.Carrinho;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("carrinhos")
public class CarrinhoResource {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca() {
		Carrinho carrinho = new CarrinhoDAO().busca(1L);
		return carrinho.toXML();
	}

}
