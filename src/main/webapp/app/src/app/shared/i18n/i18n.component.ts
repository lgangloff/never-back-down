import { Component, OnInit, Input } from '@angular/core';
import { I18nValue } from '../domain/i18n.model';

@Component({
  selector: 'i18n',
  templateUrl: './i18n.component.html',
  styleUrls: ['./i18n.component.css']
})
export class I18nComponent implements OnInit {

  @Input("valuesByKey")
  private valuesByKey: Map<string, I18nValue[]>;

  @Input("key")
  private key: string;

  @Input("values")
  private values: I18nValue[];

  @Input("type")
  private type: string;

  constructor() { }

  ngOnInit() {
  }

  public getValues(){

    if (this.key != null && this.valuesByKey != null){
      return this.valuesByKey[this.key];
    }
    return this.values;

  }

}
