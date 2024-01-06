import { Component, OnInit, ViewChild } from '@angular/core';
import { RepositoryService } from '../services/repository.service';
import { Commit } from '../model/commit';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-git',
  templateUrl: './git.component.html',
  styleUrl: './git.component.scss',
})
export class GitComponent implements OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  commits!: Commit[];
  pageSizeOptions: number[] = [1, 5, 10];
  pageSize = 10;
  pageIndex = 1;
  totalCommits!: number;
  private commitsSubscription!: Subscription;
  constructor(private repositoryService: RepositoryService) {}

  ngOnInit(): void {
    this.getCommits();
    this.commitsSubscription = this.repositoryService.commitsObserver.subscribe(
      (res) => {
        this.commits = res;
      }
    );
  }

  ngOnDestroy(): void {
    if (this.commitsSubscription) {
      this.commitsSubscription.unsubscribe();
    }
  }

  getTotalCommitsCount(): void {
    this.repositoryService.getTotalCommitsCount().subscribe(
      (count: number) => {
        this.totalCommits = count;
      },
      (error: any) => {
        console.error('Get total commits count failed', error);
      }
    );
  }

  getCommits(pageIndex: number = 1, pageSize: number = 10): void {
    this.getTotalCommitsCount();

    this.repositoryService.getCommitsPage(pageIndex, pageSize).subscribe(
      (commits: Commit[]) => {
        this.commits = commits;
      },
      (error: any) => {
        console.error('Get commits failed', error);
      }
    );
  }

  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex + 1;
    this.pageSize = event.pageSize;
    this.getCommits(this.pageIndex, this.pageSize);
  }

  addCommit(formData: any) {
    const newCommit: Commit = {
      commitId: this.generateRandomString(),
      author: formData.commitAuthor,
      commitDate: new Date(),
      message: formData.commitMessage,
    };

    this.repositoryService.addCommit(newCommit).subscribe(
      (response) => {
        this.getCommits(this.pageIndex, this.pageSize);
        console.log('Successful', response);
      },
      (error) => {
        console.error('Failed', error);
      }
    );
  }
  generateRandomString(): string {
    const characters =
      'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const length = 40;

    let randomString = '';

    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * characters.length);
      randomString += characters.charAt(randomIndex);
    }

    return randomString;
  }
}
