import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Commit } from '../model/commit';

@Injectable({
  providedIn: 'root',
})
export class RepositoryService {
  private apiUrl = 'http://localhost:8087/api/git/repository';

  public commitsObserver: BehaviorSubject<Commit[]> = new BehaviorSubject<
    Commit[]
  >([]);

  constructor(private httpClient: HttpClient) {}

  getCommitsPage(page: number, size: number): Observable<Commit[]> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.httpClient
      .get<Commit[]>(this.apiUrl, { params })
      .pipe(tap((res) => this.commitsObserver.next(res)));
  }

  getTotalCommitsCount(): Observable<number> {
    const url = `${this.apiUrl}/count`;
    return this.httpClient.get<number>(url);
  }

  addCommit(commit: Commit): Observable<Commit> {
    return this.httpClient.post<Commit>(this.apiUrl, commit);
  }
}
