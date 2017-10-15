import { Component, OnInit, Input } from '@angular/core';
import { I18nValue } from '../domain/i18n.model';

@Component({
  selector: 'i18n',
  templateUrl: './i18n.component.html',
  styleUrls: ['./i18n.component.css']
})
export class I18nComponent implements OnInit {

  @Input("model")
  private model: I18nValue[];

  constructor() { }

  ngOnInit() {
  }

}
