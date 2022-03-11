package br.com.VozAtiva.VozAtiva.Configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.responses.ApiResponse;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI SpringVozAtivaOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Projeto VozAtiva")
						.description("Projeto Voz Ativa - Generation Brasil")
						.version("v0.0.1").license(new License()
						.name("Voz Ativa").url("https://VozAtiva.com.br"))
						.contact(new Contact().name("Equipe ASJPPI")
						.url("https://github.com/agathacamargo23/Projeto_VozAtiva_Bloco2")
						.email("VozAtiva@gmail.com")))
				.externalDocs(new ExternalDocumentation().description("Github")
						.url("https://github.com/agathacamargo23/Projeto_VozAtiva_Bloco2"));

	}

	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);

	}

}