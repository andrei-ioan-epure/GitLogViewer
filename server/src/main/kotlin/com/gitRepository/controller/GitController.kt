package com.gitRepository.controller

import com.gitRepository.dto.GitCommitDTO
import com.gitRepository.exception.InvalidDataException
import com.gitRepository.service.GitCommitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/git/repository")
class GitController @Autowired constructor(private  var gitCommitService: GitCommitService) {

    @GetMapping()
    fun getCommits(@RequestParam(name = "page", required = false, defaultValue = "1")  page:Int,
                   @RequestParam(name = "size", required = false, defaultValue = "10")  size:Int):ResponseEntity<Any>
    {
        return try {
            ResponseEntity<Any>(
                this.gitCommitService.findAll(
                    page = page,
                    size = size
                ), HttpStatus.OK
            )
        } catch (e:InvalidDataException) {
            ResponseEntity<Any>(e.message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch(e:Exception)
        {
            ResponseEntity<Any>(e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/count")
    fun getTotalCommits(): ResponseEntity<Long> {
        val totalCount = gitCommitService.getTotalCommits()
        return ResponseEntity.ok(totalCount)
    }

    @PostMapping()
    fun postCommit(@RequestBody gitCommitDTO: GitCommitDTO):ResponseEntity<GitCommitDTO>
    {
        return  ResponseEntity<GitCommitDTO>(this.gitCommitService.save(commit = gitCommitDTO),HttpStatus.CREATED)
    }


}