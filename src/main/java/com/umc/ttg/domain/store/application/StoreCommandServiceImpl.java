package com.umc.ttg.domain.store.application;

import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.repository.HeartStoreRepository;
import com.umc.ttg.domain.member.repository.MemberRepository;
import com.umc.ttg.domain.review.entity.Review;
import com.umc.ttg.domain.review.repository.ReviewRepository;
import com.umc.ttg.domain.store.dto.*;
import com.umc.ttg.domain.store.exception.handler.StoreHandler;
import com.umc.ttg.domain.store.dto.converter.StoreConverter;
import com.umc.ttg.domain.store.entity.Menu;
import com.umc.ttg.domain.store.entity.Region;
import com.umc.ttg.domain.store.entity.Store;
import com.umc.ttg.domain.store.repository.MenuRepository;
import com.umc.ttg.domain.store.repository.RegionRepository;
import com.umc.ttg.domain.store.repository.StoreRepository;
import com.umc.ttg.global.common.AwsS3;
import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import com.umc.ttg.global.util.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final AwsS3Service awsS3Service;
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional // 저장은 모든 과정이 완료되어야 하므로
    public BaseResponseDto<StoreCreateResponseDto> saveStore(StoreCreateRequestDto storeCreateRequestDto) throws IOException {

        Menu menu = menuRepository.findById(storeCreateRequestDto.getMenu())
                .orElseThrow(() -> new StoreHandler(ResponseCode._BAD_REQUEST));

        Region region = regionRepository.findById(storeCreateRequestDto.getRegion())
                .orElseThrow(() -> new StoreHandler(ResponseCode._BAD_REQUEST));

        Store store = Store.builder()
                .storeCreateRequestDto(storeCreateRequestDto)
                .menu(menu)
                .region(region)
                .storeImage(getS3ImageLink(storeCreateRequestDto.getStoreImage())).build();

        Store savedStore = storeRepository.save(store);

        return BaseResponseDto.onSuccess(StoreConverter.convertToCreateStoreResponse(savedStore.getId()), ResponseCode.OK);

    }

    private String getS3ImageLink(MultipartFile multipartFile) throws IOException {

        AwsS3 storeImage = awsS3Service.upload(multipartFile, "storeImage");

        return storeImage.getPath();

    }



}
