package com.protosstechnology.train.bootexamine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protosstechnology.train.bootexamine.TestUtils;
import com.protosstechnology.train.bootexamine.model.Document;
import com.protosstechnology.train.bootexamine.repository.DocumentRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void insertDocument() throws Exception {
        //given
        Document document = Document.builder().id(1).documentNumber("1").description("this is doc 1").build();

        //when
        ResultActions resultActions = mockMvc.perform(post("/document")
                .contentType("application/json")
                .content(objectMapper.writeValueAsBytes(document)));

        //then
        assertEquals(document, TestUtils.fromJsonResult(resultActions.andExpect(status().isOk())
                .andReturn(),objectMapper, Document.class));
    }

    @Test
    @Order(2)
    void whenGetDocumentById_ReturnDocument() throws Exception {
        //given
        Document retrievedDocument = Document.builder().id(1).documentNumber("1").description("this is doc 1").build();

        //when
        ResultActions resultActions = mockMvc.perform(get("/document/1").content("application/json"));

        //then
        assertEquals(retrievedDocument, TestUtils.fromJsonResult(resultActions.andExpect(status().isOk())
                .andReturn(),objectMapper, Document.class));
    }

    @Test
    @Order(3)
    void updateDocumentById() throws Exception {
        //given
        Document updatedDocument = Document.builder().id(1).documentNumber("1").description("this is doc 1 updated").build();

        //when
        ResultActions resultActions = mockMvc.perform(put("/document/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsBytes(updatedDocument)));

        //then
        assertEquals(updatedDocument, TestUtils.fromJsonResult(resultActions.andExpect(status().isOk())
                .andReturn(),objectMapper, Document.class));
        assertEquals(updatedDocument, documentRepository.findById(1).orElseThrow(Exception::new));
    }

    @Test
    @Order(4)
    void deleteDocumentById() throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(delete("/document/1")
                .content("application/json"));

        //then
        assertFalse(documentRepository.findById(1).isPresent());
    }




}