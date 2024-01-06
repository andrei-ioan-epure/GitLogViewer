package com.gitRepository.service.mapper

import com.gitRepository.dto.GitCommitDTO
import com.gitRepository.model.GitCommit
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Mapper(componentModel = "spring")
interface GitCommitMapper {

    @Mapping(target = "id", ignore = true)
    fun toDto(entity: GitCommit): GitCommitDTO


    fun toEntity(dto: GitCommitDTO): GitCommit
}