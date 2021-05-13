import { Component, OnInit } from '@angular/core';
import { Site } from '../domain/sites';
import { SiteService } from '../site.service';

@Component({
  selector: 'app-site',
  templateUrl: './site.component.html',
  styleUrls: ['./site.component.css']
})
export class SiteComponent implements OnInit {

  sites : Site[]=[];
  site = new Site();
  addPanel = false;
  removePanel = false;
  updatePanel = false;
  browsePanel = false;
  pageNumber = 1;
  categoryToSort="";
  constructor(private service: SiteService) { }

  ngOnInit(): void {
  }

  getSites(): void{
    this.service.getSites(this.categoryToSort, this.pageNumber).subscribe(sites=>this.sites = sites);
  }

  addSite(): void{
    this.service.addRecord(this.site)
  }

  removeSite(): void{
    this.service.removeRecord(this.site)
  }

  updateSite(): void{
    this.service.updateRecord(this.site)
  }

  setPanels(add:boolean, remove:boolean, update:boolean, browse:boolean): void{
    this.addPanel = add;
    this.removePanel = remove;
    this.updatePanel = update;
    this.browsePanel = browse;
  }

  setAddPanel(): void{
    this.setPanels(true, false, false, false);
  }

  setRemovePanel(): void{
    this.setPanels(false, true, false, false);
  }

  setUpdatePanel(): void{
    this.setPanels(false, false, true, false);
  }

  setBrowsePanel(): void{
    this.getSites();
    this.setPanels(false, false, false, true);
  }

  previousPage():void{
  	if(this.pageNumber>1)
  		this.pageNumber--;
  	this.getSites();
  }
  nextPage():void{
  	this.pageNumber++;
  	this.getSites();
  }
}
