package br.com.fiap.resources;

import java.net.URI;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.beans.Login;
import br.com.fiap.bo.UsuarioBO;

@Path("/oceanis")
public class LoginResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @Path("/login/inserir")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserir(Login login, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        Login logins = usuarioBO.verificarLogin(login.getEmail(), login.getSenha());
        boolean loginValido = (logins != null);
        
        if (loginValido) {
            URI createdUri = uriInfo.getAbsolutePathBuilder().path(login.getEmail()).build();
            return Response.created(createdUri).entity("Login realizado com sucesso").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciais inválidas").build();
        }
    }
}
	
