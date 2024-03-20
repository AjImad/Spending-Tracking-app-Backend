package com.apptracker.spendingtracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.model.Transaction;
import com.apptracker.spendingtracker.model.User;
import com.apptracker.spendingtracker.repository.CategoryRepository;
import com.apptracker.spendingtracker.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private CategoryRepository categoryRepository;

    String exampleTransactionJson = "{\"user\": {\"userID\": \"1\"}, \"amount\": \"250\", \"note\": \"Pay my internet invoice for this March\", \"date\": \"2024-03-01\",\"category\": { \"categoryID\": \"2\"}}";

    @Test
    void addTransaction() throws Exception{
        // Create a mock User object
        User mockUser = Mockito.mock(User.class);
        // Create a mock Category object
        Category mockCategory = Mockito.mock(Category.class);
        Transaction mockTransaction = Transaction.builder()
                .transactionID(1)
                .user(mockUser)
                .category(mockCategory)
                .note("Test Transaction")
                .amount(100L)
                .date(LocalDate.now())
                .build();

        // mocking the addTransaction method behavior of transactionService object.
        Mockito.when(
                transactionService.addTransaction(Mockito.any(Transaction.class))
        ).thenReturn(mockTransaction);

        // Prepare the Post request
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/api/transactions/add-transaction"
        ).accept(MediaType.APPLICATION_JSON).content(exampleTransactionJson).contentType(MediaType.APPLICATION_JSON);

        // Perform Post request
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        // For User and Category fields we include empty json since they're mock object and their content are not relevant to this test.
        String expected = "{\"transactionID\":1,"
                + "\"user\":{},"
                + "\"category\":{},"
                + "\"note\":\"Test Transaction\","
                + "\"amount\":100,"
                + "\"date\":\"" + LocalDate.now() + "\"}";

        // Assertion for http status
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        JSONAssert.assertEquals(expected, response.getContentAsString(), false);

    }
}