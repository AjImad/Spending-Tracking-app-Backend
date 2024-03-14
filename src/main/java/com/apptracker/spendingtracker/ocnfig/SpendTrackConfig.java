package com.apptracker.spendingtracker.ocnfig;

import com.apptracker.spendingtracker.model.Category;
import com.apptracker.spendingtracker.model.CategoryType;
import com.apptracker.spendingtracker.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SpendTrackConfig implements WebMvcConfigurer {

    @Bean
    CommandLineRunner commandLineRunner(CategoryRepository categoryRepository){
        return args -> {
            Category ctgry1 = Category.builder()
                    .categoryType(CategoryType.EXPENSE)
                    .categoryName("House rend")
                    .build();
            Category ctgry2 = Category.builder()
                    .categoryType(CategoryType.EXPENSE)
                    .categoryName("Internet invoice")
                    .build();
            Category ctgry3 = Category.builder()
                    .categoryType(CategoryType.EXPENSE)
                    .categoryName("Fitness")
                    .build();
            Category ctgry4 = Category.builder()
                    .categoryType(CategoryType.INCOME)
                    .categoryName("Salary")
                    .build();

            categoryRepository.saveAll(List.of(ctgry1, ctgry2, ctgry3, ctgry4));
        };
    }
}
