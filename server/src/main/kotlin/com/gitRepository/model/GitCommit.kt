package com.gitRepository.model

import com.gitRepository.dto.GitCommitDTO
import javax.persistence.*
import java.time.LocalDateTime

@Entity
 data class GitCommit(
    @Id
    val commitId: String = "",

    val author: String = "",

    val commitDate: LocalDateTime = LocalDateTime.now(),

    val message: String = ""
)
fun GitCommit.toDto(): GitCommitDTO {
   return GitCommitDTO(
      commitId = this.commitId,
      author = this.author,
      commitDate = this.commitDate,
      message = this.message
   )
}