import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Principal } from '../shared/auth/principal.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private principal: Principal) { }

  private displayName;

  ngOnInit() {
    this.principal.identity().subscribe(res=>{
      this.displayName = res != null ? res.firstName : "";
    });
  }


  isAuthenticated(){
    return this.principal.isAuthenticated();
  }

  isInRole(roleName: string): boolean{
    return this.principal.hasAnyAuthority([roleName]);
  }


}
