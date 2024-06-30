package com.umc.ttg.domain.store.repository;

import com.umc.ttg.domain.store.dto.StoreRequestDto;
import com.umc.ttg.domain.store.entity.Menu;
import com.umc.ttg.domain.store.entity.School;
import com.umc.ttg.domain.store.entity.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = true)
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    void StoreRepository_가_제대로_연결되었다() {

        // given

        MockMultipartFile mockMultipartFile
                = new MockMultipartFile("image", "test-image.png", "image/png", "imageBytes".getBytes());

        StoreRequestDto storeRequestDto =
                StoreRequestDto.builder()
                        .title("title")
                        .useInfo("userInfo")
                        .subTitle("subTitle")
                        .saleInfo("saleInfo")
                        .serviceInfo("serviceInfo")
                        .placeInfo("placeInfo")
                        .name("name")
//                        .reviewSpan(1)
                        .sponInfo("sponInfo")
                        .address("address")
                        .school(1L)
                        .menu(1L)
                        .storeImage(mockMultipartFile).build();

        // Menu, School 데이터 생성 및 저장
        Menu menu = new Menu("중식");
        menuRepository.save(menu);
        School school = new School("서울여대");
        schoolRepository.save(school);

        Store store = Store.builder()
                .storeRequestDto(storeRequestDto)
                .storeImage("imagePath")
                .school(school)
                .menu(menu)
                .build();

        // when
        Store result = storeRepository.save(store);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    void findBySchool_학교이름으로_상점을_찾을_수_있다() {

    }

    @Test
    void findByTitleContainingOrNameContaining() {
    }

    @Test
    void findByMenu() {
    }
}