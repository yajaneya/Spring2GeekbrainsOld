package ru.yajaneya.SpringFM1GeekbrainsDz7.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.yajaneya.SpringFM1GeekbrainsDz7.services.ProductsService;
import ru.yajaneya.SpringFM1GeekbrainsDz7.soap.products.GetAllProductsRequest;
import ru.yajaneya.SpringFM1GeekbrainsDz7.soap.products.GetAllProductsResponse;
import ru.yajaneya.SpringFM1GeekbrainsDz7.soap.products.GetProductByIdRequest;
import ru.yajaneya.SpringFM1GeekbrainsDz7.soap.products.GetProductByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.yajaneya.ru/SpringFM1GeekbrainsDz7/products";
    private final ProductsService productsService;


    /*
        Пример запроса: POST http://localhost:8189/app/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:f="http://www.yajaneya.ru/SpringFM1GeekbrainsDz7/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByIdRequest>
                    <f:id>1</f:id>
                </f:getProductByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productsService.getById(request.getId().longValue()));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8189/app/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:f="http://www.yajaneya.ru/SpringFM1GeekbrainsDz7/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllStudents(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productsService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}
