package com.protosstechnology.train.bootexamine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class TestUtils {

    public static <T> T fromJsonResult(MvcResult result, ObjectMapper objectMapper, Class<T> tClass) throws UnsupportedEncodingException, JsonProcessingException {
        return objectMapper.readValue(result.getResponse().getContentAsString(), tClass);
    }
}
