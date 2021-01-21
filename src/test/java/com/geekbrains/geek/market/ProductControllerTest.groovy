package com.geekbrains.geek.market

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import org.springframework.security.test.context.support.WithMockUser

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@AutoConfigureMockMvc
@WebMvcTest
class ProductControllerTest extends Specification {

    @Autowired
    private MockMvc mvc

    @WithMockUser(username = "user", roles = "USER")
    def "when get is performed then the response has status 200 and content is a json array"() {
        expect: "Status is 200 and the response is '[data]'"
        mvc.perform(get("/api/v1/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.content').isArray());
    }
}
