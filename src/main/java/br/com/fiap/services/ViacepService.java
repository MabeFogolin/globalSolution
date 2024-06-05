package br.com.fiap.services;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import br.com.fiap.beans.EnderecoViaCep;



public class ViacepService {

	//método
	public EnderecoViaCep getEnderecoViaCep(String cep) throws ClientProtocolException, IOException {
		
		EnderecoViaCep enderecoViaCep = null;
		HttpGet request = new HttpGet("https://viacep.com.br/ws/"+cep+"/json/");
		CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
		
		CloseableHttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		if(entity != null) {	
			String result = EntityUtils.toString(entity);
			Gson gson = new Gson();
			enderecoViaCep = gson.fromJson(result, EnderecoViaCep.class);	
		}
		return enderecoViaCep;
	}

	
}
