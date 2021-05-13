import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable} from 'rxjs';
import { Site } from './domain/sites'

@Injectable({
  providedIn: 'root'
})
export class SiteService {
  siteUrl = "http://localhost/lab8/";
  httpOptions = {
    headers: new HttpHeaders({ 
        'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  addRecord(site: Site) :void{
    this.http.post<any>(this.siteUrl+"addButton.php",site)
    .subscribe(data=>alert(data))
  }

  removeRecord(site: Site) :void{
    this.http.put<any>(this.siteUrl+"deleteButton.php",site)
    .subscribe(data=>alert(data))
  }

  updateRecord(site: Site): void{
    this.http.put<any>(this.siteUrl+"updateButton.php",site)
    .subscribe(data=>alert(data))
  }

  getSites(category:string, pageNumber:number) : Observable<Site[]>{
  	return this.http.get<Site[]>(this.siteUrl+'browseButton.php/?category='+ category +'&page='+pageNumber);
  }
}
