package com.example.be_study.service.hurdles.service;

import com.example.be_study.service.hurdles.domain.Bag;
import com.example.be_study.service.hurdles.domain.BagRepository;
import com.example.be_study.service.hurdles.domain.Item;
import com.example.be_study.service.hurdles.domain.ItemRepository;
import com.example.be_study.service.hurdles.dto.BagDto;
import com.example.be_study.service.hurdles.dto.ItemDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TestService {

    private BagRepository bagRepository;
    private ItemRepository itemRepository;

    public TestService(BagRepository bagRepository, ItemRepository itemRepository) {
        this.bagRepository = bagRepository;
        this.itemRepository = itemRepository;
    }

    public String letsGoStackOverFlowDatoVer() throws JsonProcessingException {
        BagDto bag1 = new BagDto();
        BagDto bag2 = new BagDto();

        ItemDto item1 = new ItemDto();
        ItemDto item2 = new ItemDto();

        // 양방향 참조 설정
        bag1.setItemList(Arrays.asList(item1));
        bag2.setItemList(Arrays.asList(item2));

        item1.setBag(bag1);
        item2.setBag(bag2);

        List<BagDto> bags = Arrays.asList(bag1, bag2);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String json = objectMapper.writeValueAsString(bags);
        System.out.println(json);
        return "OK";
    }


    public String letsGoStackOverFlow() throws JsonProcessingException {
        Bag bag1 = new Bag();
        Bag bag2 = new Bag();

        Item item1 = new Item();
        Item item2 = new Item();

        // 양방향 참조 설정
        bag1.setItems(Arrays.asList(item1));
        bag2.setItems(Arrays.asList(item2));

        item1.setBag(bag1);
        item2.setBag(bag2);

        List<Bag> bags = Arrays.asList(bag1, bag2);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String json = objectMapper.writeValueAsString(bags);
        System.out.println(json);
        return "OK";
    }
}
