package com.gitRepository.repository

import com.gitRepository.model.GitCommit
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional


interface GitJPARepository : JpaRepository<GitCommit, Long> {
    fun findByCommitId(commitId:String):Optional<GitCommit>
}