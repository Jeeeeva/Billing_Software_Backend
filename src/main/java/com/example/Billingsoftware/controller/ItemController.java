package com.example.Billingsoftware.controller;

import com.example.Billingsoftware.Service.ItemService;
import com.example.Billingsoftware.io.ItemReponse;
import com.example.Billingsoftware.io.ItemRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/items")
    public ItemReponse addItem(@RequestPart("item")String itemString,
                               @RequestPart("file")MultipartFile file){
        ObjectMapper objectMapper = new ObjectMapper();
        ItemRequest itemRequest = null;
        try{
            itemRequest = objectMapper.readValue(itemString,ItemRequest.class);
            return itemService.add(itemRequest,file);

        }
        catch (JsonProcessingException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error while add the items"+ex.getMessage());
        }
    }

    @GetMapping("/items")
    public List<ItemReponse> readItems(){
        return itemService.fetch();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("admin/items/{itemId}")
    public void DeleteItems(@PathVariable("itemId") String id){
        try{
            itemService.deleteItem(id);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item not found");
        }

    }


}
