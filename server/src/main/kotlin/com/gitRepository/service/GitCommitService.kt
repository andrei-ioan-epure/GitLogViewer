package com.gitRepository.service

import com.gitRepository.dto.GitCommitDTO

interface GitCommitService {
    fun findAll( page:Int,size:Int): List<GitCommitDTO>
    fun getTotalCommits():Long
    fun save(commit: GitCommitDTO): GitCommitDTO
}