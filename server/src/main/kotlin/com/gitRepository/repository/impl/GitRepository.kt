package com.gitRepository.repository.impl

import com.gitRepository.model.GitCommit
import com.gitRepository.repository.GitJPARepository
import com.gitRepository.repository.GitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class GitRepository @Autowired constructor( private  var  gitJPARepository: GitJPARepository) : GitRepository {

    override fun findAll( pageable: Pageable): Page<GitCommit> {
        return this.gitJPARepository.findAll(pageable)
    }

    override fun getTotalCommits(): Long {
        return this.gitJPARepository.count()
    }

    override fun save(commit: GitCommit): GitCommit {
        return this.gitJPARepository.save(commit)
    }
}