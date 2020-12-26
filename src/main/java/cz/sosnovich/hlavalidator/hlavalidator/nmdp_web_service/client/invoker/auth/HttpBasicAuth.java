package cz.sosnovich.hlavalidator.hlavalidator.nmdp_web_service.client.invoker.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-12-26T01:59:51.225722100+01:00[Europe/Prague]")
public class HttpBasicAuth implements Authentication {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void applyToParams(MultiValueMap<String, String> queryParams, HttpHeaders headerParams, MultiValueMap<String, String> cookieParams) {
        if (username == null && password == null) {
            return;
        }
        String str = (username == null ? "" : username) + ":" + (password == null ? "" : password);
        headerParams.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString(str.getBytes(StandardCharsets.UTF_8)));
    }
}
