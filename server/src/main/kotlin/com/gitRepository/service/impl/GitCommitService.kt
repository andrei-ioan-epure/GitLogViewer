package com.gitRepository.service.impl

import com.gitRepository.dto.GitCommitDTO
import com.gitRepository.dto.toEntity
import com.gitRepository.exception.InvalidDataException
import com.gitRepository.model.GitCommit
import com.gitRepository.model.toDto
import com.gitRepository.repository.GitRepository
import com.gitRepository.service.GitCommitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GitCommitService @Autowired constructor( private var gitRepository: GitRepository): GitCommitService {

    override fun findAll( page:Int,  size:Int): List<GitCommitDTO> {
        validatePagination(page, size)
        val pageable: Pageable = PageRequest.of(page - 1, size)
        val commitsPerPage: Page<GitCommit> = gitRepository.findAll(pageable)
        return commitsPerPage.content
            .map { it.toDto() }
    }

    override fun getTotalCommits()=this.gitRepository.getTotalCommits()

    private fun validatePagination(page: Int?, itemsPerPage: Int?) {
        if (page == null || page < 1) {
            throw InvalidDataException("Page must be positive")
        }
        if (itemsPerPage == null || itemsPerPage < 1) {
            throw InvalidDataException("ItemsPerPage must be positive")
        }
        if (itemsPerPage > 25) {
            throw InvalidDataException("ItemsPerPage exceeds maximum limit of 25")
        }
        if (page > 1000) {
            throw InvalidDataException("Page exceeds maximum limit of 1000")
        }
    }


    override fun save(commit: GitCommitDTO): GitCommitDTO {
        return ( this.gitRepository.save(commit.toEntity())).toDto()
    }

}