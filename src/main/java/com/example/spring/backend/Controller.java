package com.example.spring.backend;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/items")
//@CrossOrigin(origins = "http://localhost:4200")
//jus to test jenkins
public class Controller {

    List<Item> items = new ArrayList<>(Arrays.asList(
            new Item(1, "Laptop", 1200.0, 2, "Electronics"),
            new Item(2,"Book", 20.5, 5, "Books"),
            new Item(3,"Shoes", 89.99, 1, "Fashion"),
            new Item(4, "Headphones", 150.0, 3, "Electronics"),
            new Item(5, "Watch", 299.99, 1, "Fashion")));


    @GetMapping
    public List<Item> getAll() throws Exception {
        return items;
    }

    @GetMapping("/{id}")
    public Item getItembyId(@PathVariable int id){
        return items.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    @GetMapping("/filter")
    public List<Item> filterItems(@RequestParam(required = false) Double price, @RequestParam(required = false) String category){
        System.out.println(price + "--------------" + category);
        return items.stream().filter(x -> price == null || x.getPrice() <= price)
                .filter(x -> category == null || x.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @PostMapping
    public List<Item> add(@RequestBody Item item){
        System.out.println(item);
        item.setId(items.size() + 1);
        items.add(item);
        return items;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id){
        System.out.println("in the deleteById");
        items.remove(id-1);
        return "success";
    }

    
    @PutMapping
   public List<Item> updateItembyId(@RequestBody Item item) {
        Item oldItem = items.stream().filter((x) -> x.getId()==item.getId()).findAny().orElse(null);
        if(oldItem != null){
            items.remove(oldItem);
            items.add(item);
        }
        return items;
    }
}
