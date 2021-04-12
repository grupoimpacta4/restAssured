package br.com.ceps.stepDefs;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.hamcrest.Matchers;

import com.google.gson.Gson;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
 
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class StepDefinitions {

	private Response response;

	private String cep = "";
	private JsonPath path = null;

	public StepDefinitions() {
		baseURI = "https://api.postmon.com.br/v1/cep";
	}

	@Dado("^que eu utilizo o cep  \"([^\"]*)\" na minha busca$")
	public void que_eu_utilizo_o_cep_na_minha_busca(String cep) throws Throwable {
		this.cep = cep;
	}

	@Quando("^faco a requisicao para buscar informacoes de um cep$")
	public void faco_a_requisicao_para_buscar_informacoes_de_um_cep() throws Throwable {
		response = given().contentType("application/json;charset=UTF-8").when().get("/" + cep);
	}

	@Entao("^verifico que o body da reposta da api retornou \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void verifico_que_o_body_da_reposta_da_api_retornou(String cepV, String logradouroV, String cidadeV, String estadoV,
			String bairroV) throws Throwable {
		path = response.andReturn().jsonPath();

		String bairro = path.getString("bairro");
		String cidade = path.getString("cidade");
		String logradouro = path.getString("logradouro");
		String cep = path.getString("cep");
		String estado = path.getString("estado");

		assertEquals(bairro, bairroV);
		assertEquals(cidade, cidadeV);
		assertEquals(logradouro, logradouroV);
		assertEquals(cep, cepV);
		assertEquals(estado, estadoV);
	}

	@Entao("^o codigo de retorno deve ser (\\d+)$")
	public void o_codigo_de_retorno_deve_ser(int codigo) throws Throwable {
		response.then().statusCode(codigo);
	}
}
