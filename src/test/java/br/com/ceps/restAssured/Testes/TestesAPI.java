package br.com.ceps.restAssured.Testes;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;
import com.jayway.restassured.path.json.JsonPath; 


public class TestesAPI {

	public TestesAPI() {
		baseURI = "https://api.postmon.com.br/v1/cep";
	}

	@Test
	public void testeCepValido() {
		System.out.println("Hello");
		JsonPath path = given().contentType("application/json;charset=UTF-8").expect().statusCode(200).when().get("/13326050")
				.andReturn().jsonPath();

		String bairro =   path.getString("bairro");
		String cidade  =   path.getString("cidade");
		String logradouro  =   path.getString("logradouro");
		String cep  =   path.getString("cep");
		String estado  =   path.getString("estado");

		assertEquals(bairro, "Jardim Elizabeth");
		assertEquals(cidade, "Salto");
		assertEquals(logradouro, "Rua Bruxelas");
		assertEquals(cep, "1326050");
		assertEquals(estado, "SP");

		System.out.println("bairro "+bairro);
		System.out.println("cidade "+ cidade);
	}

	@Test
	public void testeCepInvalido() {
		System.out.println("Hello");
		given().contentType("application/json;charset=UTF-8").expect().statusCode(404).when().get("/a").andReturn()
				.jsonPath();

	}

	@Test
	public void testeMetodoInvalido() {
		System.out.println("Hello");
		given().contentType("application/json;charset=UTF-8").expect().statusCode(405).when().post("/13326050")
				.andReturn().jsonPath();

	}

}
