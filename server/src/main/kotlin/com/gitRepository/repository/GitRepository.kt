package com.gitRepository.repository

import com.gitRepository.model.GitCommit
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GitRepository {
    fun findAll(pageable:Pageable): Page<GitCommit>
    fun getTotalCommits():Long
    fun save(commit: GitCommit): GitCommit

}