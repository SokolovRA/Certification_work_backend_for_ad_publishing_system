package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDTO;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdsService {
    private final AdsRepository adsRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    private final AdsMapper adsMapper;
    private final CommentMapper commentMapper;

    public Collection<Ads> getAllAds() {
        log.info("Used method is - getAllAds");
        return adsRepository.findAll();

    }

    public Ads addAds(CreateAdsDTO createAdsDto, MultipartFile adsImage, String Email) throws Exception {
        log.info("Used method is - addAds");
        User user = userRepository.findByEmail(Email).orElseThrow(() -> new Exception("User no found"));
        Ads ads = adsMapper.toEntity(createAdsDto);
        ads.setAuthor(user);
        ads.setImage(imageService.uploadImage(adsImage));
        return adsRepository.save(ads);
    }

    public FullAdsDTO getFullAds(long id) throws Exception {
        log.info("Used method is - getFullAd");
        return adsMapper.toFullAdsDto(adsRepository.findById(id).orElseThrow(() -> new Exception("Ad not found")));

    }
}
