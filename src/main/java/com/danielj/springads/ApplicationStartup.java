package com.danielj.springads;

import com.danielj.springads.domain.*;

import com.danielj.springads.service.AdService;
import com.danielj.springads.service.CategoryService;
import com.danielj.springads.service.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class for startup events (populating db)
 *
 * @author Daniel Johansson
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    private AdService adService;
    private CategoryService categoryService;
    private UserService userService;

    public ApplicationStartup(AdService adService, CategoryService categoryService, UserService userService) {
        this.adService = adService;
        this.categoryService=categoryService;
        this.userService=userService;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        insertExampleData();
    }

    /**
     * Method for inserting example data in db
     */
    public void insertExampleData() {
        User user1=new User("Daniel", "Johansson", "daniel@johansson.se", "070-123456789", "106989013950030170308", new Address("Gata", "Stockholm", "12345", "Stockholm"));
        userService.saveUser(user1);
        User user2=new User("Nils", "Persson", "nils@persson.se", "070-12345678", "2", new Address("Gata", "Stockholm", "12345", "Stockholm"));
        userService.saveUser(user2);

        //For images
        List<byte[]> list=new ArrayList<>();
        for (int i=1; i<=5; i++) {

            String prefix="exampledata/image";
            String suffix=".jpg";
            String fileName=prefix + i + suffix;
            try {
                File f = new File(fileName);
                list.add(Files.readAllBytes(f.toPath()));
            } catch (IOException e) {e.printStackTrace();}
        }

        Category books=new Category("Books");
        Category clothes=new Category("Clothes");
        Category electronics=new Category("Electronics");
        Category furniture=new Category("Furniture");
        Category music=new Category("Music");
        Category tools=new Category("Tools");
        Category vehicles=new Category("Vehicles");

        categoryService.saveCategory(books);
        categoryService.saveCategory(clothes);
        categoryService.saveCategory(electronics);
        categoryService.saveCategory(furniture);
        categoryService.saveCategory(music);
        categoryService.saveCategory(tools);
        categoryService.saveCategory(vehicles);

        Ad ad1=new Ad("Sony A6000 in used condition", "", 5000, AdType.SELL,new Date(),new Date(),electronics, user1, list.get(0), true);
        ad1.setDescription("Sony A6000 mirrorless camera, barely used. The A6000 is a very compact camera" +
                " with a fast auto-focus and a big aps-c sensor.");
        Ad ad2=new Ad("Canon EOS 6D - full format camera almost as new", "", 9000, AdType.SELL, new Date(),new Date(),electronics, user1, list.get(1), true);
        ad2.setDescription("Canon EOS 6D in great condition. The 6D is a compact full-format DSLR camera" +
                " that produces awesome pictures.");
        Ad ad3=new Ad("Canon EOS 7D - APS-C format camera in mint condition", "", 5000, AdType.SELL,new Date(),new Date(),electronics, user1,  list.get(2), true);
        ad3.setDescription("Canon EOS 7D in great condition. The 7D is a high quality DSLR camera with" +
                " a APS-C sensor. It´s fast and produces pictures with great quality.");

        Ad ad4=new Ad("Asus Zenbook 15 inch laptop", "", 7000, AdType.SELL,new Date(),new Date(),electronics, user1,  list.get(3), true);
        ad4.setDescription("Asus Zenbook laptop in great condition. Fast CPU with lots of RAM.");

        Ad ad5=new Ad("Asus Zenbook 3 Deluxe laptop", "", 6000, AdType.SELL,new Date(),new Date(),electronics, user1,  list.get(4), true);
        ad5.setDescription("Asus Zenbook 3 Deluxe laptop. Almost new laptop in awesome condition. Very light and fast computer.");

        user1.addAd(ad1);
        user1.addAd(ad2);
        user1.addAd(ad3);
        user1.addAd(ad4);
        user1.addAd(ad5);

        adService.saveAd(ad1);
        adService.saveAd(ad2);
        adService.saveAd(ad3);
        adService.saveAd(ad4);
        adService.saveAd(ad5);
    }
}

