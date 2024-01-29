package com.fiap.techchallenge4.fiaptechchallenge4.bdd;

import com.fiap.techchallenge4.fiaptechchallenge4.application.dto.request.SetProductionStatusDTO;
import com.fiap.techchallenge4.fiaptechchallenge4.application.dto.response.ProductionResponse;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;
import com.fiap.techchallenge4.fiaptechchallenge4.utils.ProductionHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class StepDefinition {
    private Response response;

    private ProductionResponse productionResponse;

    private final String BASEURL = "http://localhost:8080/";

    @Quando("eu cadastrar uma nova produção")
    public ProductionResponse eu_cadastrar_uma_nova_producao() {
        var addProductionDTO = ProductionHelper.buildAddProductionDTO();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(addProductionDTO)
                .when().post(BASEURL + "production");

        return response.then().extract().as(ProductionResponse.class);
    }


    @Então("a produção deve ser cadastrada com sucesso")
    public void aProducaoDeveSerCadastradaComSucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ProductionResponseSchema.json"));
    }

    @Dado("que uma produção já foi cadastrada")
    public void queUmaProducaoJaFoiCadastrada() {
        productionResponse = eu_cadastrar_uma_nova_producao();
    }

    @Quando("requisitar a lista de produções")
    public void requisitarAListaDeProducoes() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get(BASEURL + "production/");
    }

    @Então("as produções devem ser exibidas com sucesso")
    public void asProducoesDevemSerExibidasComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ProductionListResponseSchema.json"));
    }

    @Quando("requisitar a atualização do status da produção")
    public void requisitarAAtualizacaoDoStatusDaProducao() {
        response = given().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new SetProductionStatusDTO(ProductionStatus.IN_PREPARATION))
                .when().put(BASEURL + "production/{orderId}", productionResponse.getOrderId());
    }

    @Então("a produção deve ser atualizada com sucesso")
    public void aProducaoDeveSerAtualizadaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ProductionResponseSchema.json"));
    }

    @Quando("eu cadastrar uma nova produção com o mesmo pedido")
    public void euCadastrarUmaNovaProducaoComOMesmoPedido() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(ProductionHelper.buildAddProductionDTO(productionResponse.getOrderId()))
                .when().post(BASEURL + "production");
    }

    @Então("a produção não deve ser cadastrada com sucesso")
    public void aProducaoNaoDeveSerCadastradaComSucesso() {
        response.then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
