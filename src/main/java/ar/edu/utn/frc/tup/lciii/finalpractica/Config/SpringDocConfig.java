package ar.edu.utn.frc.tup.lciii.finalpractica.Config;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SpringDocConfig {//sirve para documentar la aplicacion

    @Value("${app.url}") private String url;
    @Value("${app.dev-name}")private String devName;
    @Value("${app.dev-email}")private String devEmail;
    @Bean
    public OpenAPI openApi (
        @Value("@project.name@") String appName,
        @Value("@project.description@") String appDescription,
        @Value("@project.version@") String appVersion){

        Info info = new Info()
                .title(appName)
                .version(appVersion)
                .description(appDescription)
                .contact(
                        new Contact()
                                .name(devName)
                                .email(devEmail));

        Server server = new Server()
                .url(url)
                .description(appDescription);

        return new OpenAPI()
                .components(new Components())
                .info(info)
                .addServersItem(server);
    }
    @Bean//es el bean que necesita openAPi para generar la documentacion
    public ModelResolver modelResolver(ObjectMapper objectMapper){
        return new ModelResolver(objectMapper);
    }

}
