package br.com.isato.applojalivros.service;

import br.com.isato.applojalivros.model.Item;
import br.com.isato.applojalivros.repository.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Deve ser passado um id válido (Long id)!", null);
        }
        return itemRepository.findById(id);
    }

    public Optional<Item> create(@Valid Item item){
        return Optional.of(itemRepository.save(item));
    }

    public Optional<Item> update(@Valid Item item){
        return Optional.of(itemRepository.save(item));
    }

    public void deleteById(Long id){
        itemRepository.deleteById(id);
    }
}
