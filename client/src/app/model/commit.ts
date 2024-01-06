export class Commit {
  commitId: string;
  author: string;
  commitDate: Date;
  message: string;

  constructor(
    commitId: string,
    author: string,
    commitDate: Date,
    message: string
  ) {
    this.commitId = commitId;
    this.author = author;
    this.commitDate = commitDate;
    this.message = message;
  }
}
