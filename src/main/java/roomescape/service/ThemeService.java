package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.dto.ThemeRequestDto;
import roomescape.dto.ThemeResponseDto;
import roomescape.model.Theme;
import roomescape.repository.ThemeJdbcRepository;
import roomescape.repository.ThemeRepository;

import java.util.NoSuchElementException;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeService(ThemeJdbcRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public Long createTheme(ThemeRequestDto themeRequest) {
        Theme theme = themeRequest.toEntity();
        return themeRepository.save(theme);
    }

    public ThemeResponseDto findTheme(Long themeId) {
        Theme theme = themeRepository
                .findOneById(themeId)
                .orElseThrow(() -> {
                    throw new NoSuchElementException("No Theme by that Id");});
        return new ThemeResponseDto(theme);
    }

    public void deleteTheme(Long themeId) {
        themeRepository.delete(themeId);
    }
}