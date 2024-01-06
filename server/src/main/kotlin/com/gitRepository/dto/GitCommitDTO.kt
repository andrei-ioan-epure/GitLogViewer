package com.gitRepository.dto

import com.gitRepository.model.GitCommit
import java.time.LocalDateTime

data class GitCommitDTO(
    val commitId: String,
    val author: String,
    val commitDate: LocalDateTime,
    val message: String
)
fun GitCommitDTO.toEntity(): GitCommit {
    return GitCommit(
        commitId = this.commitId,
        author = this.author,
        commitDate = this.commitDate,
        message = this.message
    )
}