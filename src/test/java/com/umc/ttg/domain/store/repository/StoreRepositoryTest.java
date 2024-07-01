package com.umc.ttg.domain.store.repository;

import com.umc.ttg.domain.store.dto.StoreRequestDto;
import com.umc.ttg.domain.store.entity.Menu;
import com.umc.ttg.domain.store.entity.School;
import com.umc.ttg.domain.store.entity.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = true)
@Sql("/sql/store-repository-test-data.sql")
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private SchoolRepository schoolRepository;

//    @Test
//    void StoreRepository_가_제대로_연결되었다() {
//
//        // given
//
//        MockMultipartFile mockMultipartFile
//                = new MockMultipartFile("image", "test-image.png", "image/png", "imageBytes".getBytes());
//
//        StoreRequestDto storeRequestDto =
//                StoreRequestDto.builder()
//                        .title("title")
//                        .useInfo("userInfo")
//                        .subTitle("subTitle")
//                        .saleInfo("saleInfo")
//                        .serviceInfo("serviceInfo")
//                        .placeInfo("placeInfo")
//                        .name("name")
//                        .sponInfo("sponInfo")
//                        .address("address")
//                        .school(1L)
//                        .menu(1L)
//                        .storeImage(mockMultipartFile).build();
//
//        // Menu, School 데이터 생성 및 저장
//        Menu menu = new Menu("중식");
//        menuRepository.save(menu);
//        School school = new School("서울여대");
//        schoolRepository.save(school);
//
//        Store store = Store.builder()
//                .storeRequestDto(storeRequestDto)
//                .storeImage("imagePath")
//                .school(school)
//                .menu(menu)
//                .build();
//
//        // when
//        Store result = storeRepository.save(store);
//
//        // then
//        assertThat(result.getId()).isNotNull();
//    }

    @Test
    void findBySchool_학교_객체로_해당하는_상점들을_찾을_수_있다() {
        // given

        // when
        School school = schoolRepository.findById(1L).get();
        List<Store> result = storeRepository.findBySchool(school);

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void findBySchool_학교_객체로_해당하는_상점이_없으면_빈_리스트를_반환한다() {
        // given

        // when
        School fakeSchool = schoolRepository.findById(2L).get();
        List<Store> result = storeRepository.findBySchool(fakeSchool);

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    void findByTitleContainingOrNameContaining_제목과_이름으로_상점을_조회할_수_있다() {
        // given

        // when
        List<Store> result = storeRepository.findByTitleContainingOrNameContaining("강남", "둘쓰닭");

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void findByTitleContainingOrNameContaining_검색어에_해당하는_상점이_없으면_빈_리스트를_반환한다() {
        // given

        // when
        List<Store> result = storeRepository.findByTitleContainingOrNameContaining("일식", "네코스시");

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(0);
    }

    /**
     * 이후에 띄어쓰기가 있어도 해당 키워드에 걸리도록 리팩터링 예정
     */
//    @Test
//    void findByTitleContainingOrNameContaining_중간에_띄어쓰기가_있어도_해당_상점을_잘_반환한다() {
//        // given
//        MockMultipartFile mockMultipartFile
//                = new MockMultipartFile("image", "test-image.png", "image/png", "imageBytes".getBytes());
//
//        StoreRequestDto storeRequestDto =
//                StoreRequestDto.builder()
//                        .title("맛있는 중식 먹으러 오세요 ~")
//                        .useInfo("userInfo")
//                        .subTitle("subTitle")
//                        .saleInfo("saleInfo")
//                        .serviceInfo("serviceInfo")
//                        .placeInfo("placeInfo")
//                        .name("홍콩반점")
//                        .sponInfo("sponInfo")
//                        .address("address")
//                        .school(1L)
//                        .menu(1L)
//                        .storeImage(mockMultipartFile).build();
//
//        // Menu, School 데이터 생성 및 저장
//        Menu menu = new Menu("중식");
//        menuRepository.save(menu);
//        School school = new School("서울여대");
//        schoolRepository.save(school);
//
//        Store store = Store.builder()
//                .storeRequestDto(storeRequestDto)
//                .storeImage("imagePath")
//                .school(school)
//                .menu(menu)
//                .build();
//        // when
//        storeRepository.save(store);
//        List<Store> result = storeRepository.findByTitleContainingOrNameContaining("중 식", "홍콩 반점");
//
//        // then
//        assertThat(result.size()).isNotNull();
//        assertThat(result.size()).isEqualTo(1);
//    }


    @Test
    void findByMenu_메뉴_객체로_상점들을_조회할_수_있다() {
        // given

        // when
        Menu menu = menuRepository.findById(1L).get();
        List<Store> result = storeRepository.findByMenu(menu);

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void findByMenu_메뉴_객체로_해당하는_상점이_없으면_빈_리스트를_반환한다() {
        // given

        // when
        Menu fakeMenu = menuRepository.findById(2L).get();
        List<Store> result = storeRepository.findByMenu(fakeMenu);

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(0);
    }
}