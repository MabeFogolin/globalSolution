package br.com.fiap.resources;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.beans.Usuario;
import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.exceptions.DadoRepetido;

@Path("/oceanis")
public class UsuarioResource {	
	
	private UsuarioBO usuarioBO = new UsuarioBO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Usuario> listar() throws ClassNotFoundException, SQLException{
		System.out.println(Response.ok().build());
		return (ArrayList<Usuario>) usuarioBO.listarUsuarios();
	}
	
	@POST //localhost:8080/projetoTeste/rest/oceanis/inserir
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastroRs(Usuario usuario, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, DadoRepetido {
		usuarioBO.inserirUsuario(usuario);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder(); //recebe a informação do front (página)
		return Response.created(builder.build()).build(); //composição -> caminho (carrregar o que foi carregado) - http -> 200
	}
	
	 @DELETE //http://localhost:8080/projetoTeste/rest/oceanis/email
	    @Path("/{email}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response deletarTrail(@PathParam("email") String email) throws ClassNotFoundException, SQLException {
	    	usuarioBO.deletarUsuario(email);
	        return Response.ok().build();
	 }
	 
	 @PUT //http://localhost:8080/projetoTeste/rest/oceanis/atualizar/email
	    @Path("/atualizar/{email}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response atualizarTrail(Usuario usuario) throws ClassNotFoundException, SQLException {
	    	usuarioBO.atualizarUsuario(usuario);
	        return Response.ok().build();
	    }

}
