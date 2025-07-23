package com.example.Billingsoftware.Service.impl;

import com.example.Billingsoftware.Respository.CategoryRepository;
import com.example.Billingsoftware.Respository.ItemRepository;
import com.example.Billingsoftware.Service.FileUploadService;
import com.example.Billingsoftware.Service.ItemService;
import com.example.Billingsoftware.entity.CategoryEntity;
import com.example.Billingsoftware.entity.ItemEntity;
import com.example.Billingsoftware.io.ItemReponse;
import com.example.Billingsoftware.io.ItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    private final FileUploadService fileUploadService;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ItemReponse add(ItemRequest request, MultipartFile file) {
        String imgUrl = fileUploadService.uploadFile(file);
        ItemEntity newitementity = ConvertToEntity(request);
        CategoryEntity extistingcatgory = categoryRepository.findByCategoryId(request.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category not Found"+request.getCategoryId()));
        newitementity.setCategory(extistingcatgory);
        newitementity.setImgUrl(imgUrl);
        itemRepository.save(newitementity);
        return ConvertToResponse(newitementity);



    }

    private ItemReponse ConvertToResponse(ItemEntity newitementity) {
        return ItemReponse.builder()
                .itemId(newitementity.getItemId())
                .name(newitementity.getName())
                .description(newitementity.getDescription())
                .price(newitementity.getPrice())
                .imgUrl(newitementity.getImgUrl())
                .categoryName(newitementity.getCategory().getName())
                .categoryId(newitementity.getCategory().getCategoryId())
                .createdAt(newitementity.getCreatedAt())
                .updatedAt(newitementity.getUpdatedAt())
                .build();

    }

    private ItemEntity ConvertToEntity(ItemRequest request) {
        return ItemEntity.builder()
                .itemId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    @Override
    public List<ItemReponse> fetch() {
        return itemRepository.findAll()
                .stream()
                .map(itemEntity -> ConvertToResponse(itemEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItem(String itemId) {
        ItemEntity existingItem = itemRepository.findByItemId(itemId)
                .orElseThrow(()-> new RuntimeException("Item not found"+itemId));
        boolean isfiledeleted = fileUploadService.deleteFile(existingItem.getImgUrl());
        if(isfiledeleted){
            itemRepository.delete(existingItem);
        }
        else{
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to delete the image");
        }


    }
}
