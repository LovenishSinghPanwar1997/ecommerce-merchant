package com.team2.merchant.Client;

import com.team2.merchant.dto.searchClientDTO.ProductSearchClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "searchclient",url = "http://localhost:8090")
public interface SearchClient {

    @PostMapping("/search-ms/product")
    public String addProductInSearchDb(@RequestBody ProductSearchClientDTO product);

}
