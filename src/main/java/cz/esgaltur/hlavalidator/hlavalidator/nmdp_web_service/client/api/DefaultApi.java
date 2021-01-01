package cz.esgaltur.hlavalidator.hlavalidator.nmdp_web_service.client.api;

import cz.esgaltur.hlavalidator.hlavalidator.nmdp_web_service.client.invoker.ApiClient;
import cz.esgaltur.hlavalidator.hlavalidator.nmdp_web_service.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-12-26T01:59:51.225722100+01:00[Europe/Prague]")
@Component("cz.esgaltur.nmdp_web_service.client.api.DefaultApi")
public class DefaultApi {
    private ApiClient apiClient;

    public DefaultApi() {
        this(new ApiClient());
    }

    @Autowired
    public DefaultApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param after  Only return code definitions with a code greater. If \&quot;AAA\&quot; is specified,  will not include itself or shorter codes like \&quot;ZZ\&quot;.  (required)
     * @param before Only return code definitions with a code smaller. (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String gETCodes(String after, String before) throws RestClientException {
        return gETCodesWithHttpInfo(after, before).getBody();
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param after  Only return code definitions with a code greater. If \&quot;AAA\&quot; is specified,  will not include itself or shorter codes like \&quot;ZZ\&quot;.  (required)
     * @param before Only return code definitions with a code smaller. (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> gETCodesWithHttpInfo(String after, String before) throws RestClientException {
        Object postBody = null;

        // verify the required parameter 'after' is set
        if (after == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'after' when calling gETCodes");
        }

        // verify the required parameter 'before' is set
        if (before == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'before' when calling gETCodes");
        }

        String path = apiClient.expandPath("/codes", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "after", after));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "before", before));

        final String[] accepts = {
                "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[]{};

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * Retrieve the multiple allele code definition for the specified code
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param code The specific multiple allele code (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String gETCodesCode(String code) throws RestClientException {
        return gETCodesCodeWithHttpInfo(code).getBody();
    }

    /**
     * Retrieve the multiple allele code definition for the specified code
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param code The specific multiple allele code (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> gETCodesCodeWithHttpInfo(String code) throws RestClientException {
        Object postBody = null;

        // verify the required parameter 'code' is set
        if (code == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'code' when calling gETCodesCode");
        }

        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("code", code);
        String path = apiClient.expandPath("/codes/{code}", uriVariables);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = {
                "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[]{};

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (required)
     * @param typing         An allele code designation in the format locus*firstField:alleleCode (required)
     * @param expand         flag indicating whether to expand short allele names into their sets of full IMGT/HLA names. (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String gETDecode(String imgtHlaRelease, String typing, Boolean expand) throws RestClientException {
        return gETDecodeWithHttpInfo(imgtHlaRelease, typing, expand).getBody();
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (required)
     * @param typing         An allele code designation in the format locus*firstField:alleleCode (required)
     * @param expand         flag indicating whether to expand short allele names into their sets of full IMGT/HLA names. (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> gETDecodeWithHttpInfo(String imgtHlaRelease, String typing, Boolean expand) throws RestClientException {
        Object postBody = null;

        // verify the required parameter 'imgtHlaRelease' is set
        if (imgtHlaRelease == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'imgtHlaRelease' when calling gETDecode");
        }

        // verify the required parameter 'typing' is set
        if (typing == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'typing' when calling gETDecode");
        }

        // verify the required parameter 'expand' is set
        if (expand == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'expand' when calling gETDecode");
        }

        String path = apiClient.expandPath("/decode", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "imgtHlaRelease", imgtHlaRelease));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "typing", typing));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "expand", expand));

        final String[] accepts = {
                "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[]{};

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (required)
     * @param typing         An allele code designation in the format locus*firstField:alleleCode (required)
     * @return List&lt;AlleleResponseItem&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<AlleleResponseItem> gETExpand(String imgtHlaRelease, String typing) throws RestClientException {
        return gETExpandWithHttpInfo(imgtHlaRelease, typing).getBody();
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (required)
     * @param typing         An allele code designation in the format locus*firstField:alleleCode (required)
     * @return ResponseEntity&lt;List&lt;AlleleResponseItem&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<AlleleResponseItem>> gETExpandWithHttpInfo(String imgtHlaRelease, String typing) throws RestClientException {
        Object postBody = null;

        // verify the required parameter 'imgtHlaRelease' is set
        if (imgtHlaRelease == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'imgtHlaRelease' when calling gETExpand");
        }

        // verify the required parameter 'typing' is set
        if (typing == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'typing' when calling gETExpand");
        }

        String path = apiClient.expandPath("/expand", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "imgtHlaRelease", imgtHlaRelease));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "typing", typing));

        final String[] accepts = {
                "application/json", "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[]{};

        ParameterizedTypeReference<List<AlleleResponseItem>> returnType = new ParameterizedTypeReference<List<AlleleResponseItem>>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String gETImgtHlaReleases() throws RestClientException {
        return gETImgtHlaReleasesWithHttpInfo().getBody();
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> gETImgtHlaReleasesWithHttpInfo() throws RestClientException {
        Object postBody = null;

        String path = apiClient.expandPath("/imgtHlaReleases", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        final String[] accepts = {
                "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[]{};

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (required)
     * @param typing         An allele code designation in the format locus*firstField:alleleCode (required)
     * @return TransformedMacd
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public TransformedMacd gETTransform(String imgtHlaRelease, String typing) throws RestClientException {
        return gETTransformWithHttpInfo(imgtHlaRelease, typing).getBody();
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (required)
     * @param typing         An allele code designation in the format locus*firstField:alleleCode (required)
     * @return ResponseEntity&lt;TransformedMacd&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<TransformedMacd> gETTransformWithHttpInfo(String imgtHlaRelease, String typing) throws RestClientException {
        Object postBody = null;

        // verify the required parameter 'imgtHlaRelease' is set
        if (imgtHlaRelease == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'imgtHlaRelease' when calling gETTransform");
        }

        // verify the required parameter 'typing' is set
        if (typing == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'typing' when calling gETTransform");
        }

        String path = apiClient.expandPath("/transform", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "imgtHlaRelease", imgtHlaRelease));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "typing", typing));

        final String[] accepts = {
                "application/json", "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[]{};

        ParameterizedTypeReference<TransformedMacd> returnType = new ParameterizedTypeReference<TransformedMacd>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param imgtHlaRelease Optionall specify the IMGT/HLA Release version. The default is the latest release. (required)
     * @param typing         An allele code designation in the format locus*family:alleleCode (required)
     * @return ValidationResult
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ValidationResult gETValidate(String imgtHlaRelease, String typing) throws RestClientException {
        return gETValidateWithHttpInfo(imgtHlaRelease, typing).getBody();
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param imgtHlaRelease Optionall specify the IMGT/HLA Release version. The default is the latest release. (required)
     * @param typing         An allele code designation in the format locus*family:alleleCode (required)
     * @return ResponseEntity&lt;ValidationResult&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ValidationResult> gETValidateWithHttpInfo(String imgtHlaRelease, String typing) throws RestClientException {
        Object postBody = null;

        // verify the required parameter 'imgtHlaRelease' is set
        if (imgtHlaRelease == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'imgtHlaRelease' when calling gETValidate");
        }

        // verify the required parameter 'typing' is set
        if (typing == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'typing' when calling gETValidate");
        }

        String path = apiClient.expandPath("/validate", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "imgtHlaRelease", imgtHlaRelease));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "typing", typing));

        final String[] accepts = {
                "application/json", "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {};
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[]{};

        ParameterizedTypeReference<ValidationResult> returnType = new ParameterizedTypeReference<ValidationResult>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (required)
     * @param requestBody    (required)
     * @return List&lt;DecodedTyping&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<DecodedTyping> pOSTDecodeList(String imgtHlaRelease, List<String> requestBody) throws RestClientException {
        return pOSTDecodeListWithHttpInfo(imgtHlaRelease, requestBody).getBody();
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (required)
     * @param requestBody    (required)
     * @return ResponseEntity&lt;List&lt;DecodedTyping&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<DecodedTyping>> pOSTDecodeListWithHttpInfo(String imgtHlaRelease, List<String> requestBody) throws RestClientException {
        Object postBody = requestBody;

        // verify the required parameter 'imgtHlaRelease' is set
        if (imgtHlaRelease == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'imgtHlaRelease' when calling pOSTDecodeList");
        }

        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestBody' when calling pOSTDecodeList");
        }

        String path = apiClient.expandPath("/decodeList", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "imgtHlaRelease", imgtHlaRelease));

        final String[] accepts = {
                "application/json", "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {
                "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[]{};

        ParameterizedTypeReference<List<DecodedTyping>> returnType = new ParameterizedTypeReference<List<DecodedTyping>>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param trialRun       Simulate an encode without actually persisting. (required)
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (required)
     * @param email          Specify an email identifying the requester.   Without this no new allele code will be created  (required)
     * @param body           (required)
     * @return String
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public String pOSTEncode(Boolean trialRun, String imgtHlaRelease, String email, String body) throws RestClientException {
        return pOSTEncodeWithHttpInfo(trialRun, imgtHlaRelease, email, body).getBody();
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param trialRun       Simulate an encode without actually persisting. (required)
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (required)
     * @param email          Specify an email identifying the requester.   Without this no new allele code will be created  (required)
     * @param body           (required)
     * @return ResponseEntity&lt;String&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<String> pOSTEncodeWithHttpInfo(Boolean trialRun, String imgtHlaRelease, String email, String body) throws RestClientException {
        Object postBody = body;

        // verify the required parameter 'trialRun' is set
        if (trialRun == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'trialRun' when calling pOSTEncode");
        }

        // verify the required parameter 'imgtHlaRelease' is set
        if (imgtHlaRelease == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'imgtHlaRelease' when calling pOSTEncode");
        }

        // verify the required parameter 'email' is set
        if (email == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'email' when calling pOSTEncode");
        }

        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling pOSTEncode");
        }

        String path = apiClient.expandPath("/encode", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "trialRun", trialRun));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "imgtHlaRelease", imgtHlaRelease));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "email", email));

        final String[] accepts = {
                "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {
                "text/plain"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[]{};

        ParameterizedTypeReference<String> returnType = new ParameterizedTypeReference<String>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param requestBody    (required)
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (optional)
     * @return List&lt;ValidatedTyping&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<ValidatedTyping> pOSTValidateList(List<String> requestBody, String imgtHlaRelease) throws RestClientException {
        return pOSTValidateListWithHttpInfo(requestBody, imgtHlaRelease).getBody();
    }

    /**
     * <p><b>200</b>
     * <p><b>400</b>
     * <p><b>500</b>
     *
     * @param requestBody    (required)
     * @param imgtHlaRelease Optionally specify the IMGT/HLA Release like \&quot;3.19.0\&quot; (default is the latest release)  (optional)
     * @return ResponseEntity&lt;List&lt;ValidatedTyping&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<ValidatedTyping>> pOSTValidateListWithHttpInfo(List<String> requestBody, String imgtHlaRelease) throws RestClientException {
        Object postBody = requestBody;

        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'requestBody' when calling pOSTValidateList");
        }

        String path = apiClient.expandPath("/validateList", Collections.<String, Object>emptyMap());

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap formParams = new LinkedMultiValueMap();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "imgtHlaRelease", imgtHlaRelease));

        final String[] accepts = {
                "application/json", "text/plain"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = {
                "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[]{};

        ParameterizedTypeReference<List<ValidatedTyping>> returnType = new ParameterizedTypeReference<List<ValidatedTyping>>() {
        };
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType);
    }
}
