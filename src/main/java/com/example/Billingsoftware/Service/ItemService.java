package com.example.Billingsoftware.Service;

import com.example.Billingsoftware.io.ItemReponse;
import com.example.Billingsoftware.io.ItemRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    ItemReponse add(ItemRequest request, MultipartFile file);
    List<ItemReponse> fetch();

    void deleteItem(String itemId);
}
