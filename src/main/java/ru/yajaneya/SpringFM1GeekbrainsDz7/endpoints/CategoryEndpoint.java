package ru.yajaneya.SpringFM1GeekbrainsDz7.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.yajaneya.SpringFM1GeekbrainsDz7.services.CategoriesService;
import ru.yajaneya.SpringFM1GeekbrainsDz7.soap.categories.*;

@Endpoint
@RequiredArgsConstructor
public class CategoryEndpoint {
    private static final String NAMESPACE_URI = "http://www.yajaneya.ru/SpringFM1GeekbrainsDz7/categories";
    private final CategoriesService categoriesService;

    /*
        Пример запроса: POST http://localhost:8189/app/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.yajaneya.ru/SpringFM1GeekbrainsDz7/categories">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getCategoryByCategoryNameRequest>
                    <f:categoryName>Milk</f:categoryName>
                </f:getCategoryByCategoryNameRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByCategoryNameRequest")
    @ResponsePayload
    @Transactional
    public GetCategoryByCategoryNameResponse getCategoryByCategoryName(@RequestPayload GetCategoryByCategoryNameRequest request) {
        GetCategoryByCategoryNameResponse response = new GetCategoryByCategoryNameResponse();
        response.setCategory(categoriesService.getByCategoryName(request.getCategoryName()));
        return response;
    }
}
