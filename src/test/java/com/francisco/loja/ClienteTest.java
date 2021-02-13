package com.francisco.loja;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.francisco.loja.modelo.Carrinho;
import com.francisco.loja.modelo.Produto;
import com.francisco.loja.modelo.Projeto;
import com.thoughtworks.xstream.XStream;

public class ClienteTest {
	
	private HttpServer server;

	@Before
	public void startaServidor() {
		server = Servidor.inicializaServidor();
	}
	
	@After
	public void mataServidor() {
		server.shutdownNow();
	}
	
	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://www.mocky.io");
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
	}
	
	@Test
	public void testaQueBuscarUmProjetoTrazOProjetoEsperado() {
				
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        String conteudo = target.path("/projetos/1").request().get(String.class);
        Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
        assertEquals("Minha loja", projeto.getNome());
	}
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/carrinhos/1").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
		
		server.shutdownNow();
	}
	
	@Test
	public void testaQueSuportaNovosCarrinhos() {
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        
        Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        String xml = carrinho.toXML();
        String response = target.path("/carrinhos").request().post(Entity.xml(xml), String.class);
        Assert.assertEquals("<status>sucesso</status>", response);
	}
}
