package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.RefreshTokenDto;
import com.openclassrooms.mddapi.model.RefreshToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = InstantMapper.class)
public interface RefreshTokenMapper {
    RefreshTokenDto toRefreshTokenDto(RefreshToken refreshToken);
    RefreshToken toRefreshToken(RefreshTokenDto refreshTokenDto);
}
