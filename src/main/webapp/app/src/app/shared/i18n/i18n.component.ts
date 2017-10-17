import { Component, OnInit, Input } from '@angular/core';
import { I18nValue } from '../domain/i18n.model';

@Component({
  selector: 'i18n',
  templateUrl: './i18n.component.html',
  styleUrls: ['./i18n.component.css']
})
export class I18nComponent implements OnInit {

  private langs = ["en_EN", "fr_FR"];

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
    let ret: I18nValue[];
    if (this.key != null && this.valuesByKey != null){
      if (this.valuesByKey[this.key] == null){
        this.valuesByKey[this.key] = [];
      }

      ret = this.valuesByKey[this.key];
    }
    else{
      ret = this.values;
    }
  

    let orderedRet: I18nValue[];
    this.langs.forEach(lang => {
      let exists = false;
      ret.forEach(v=>{
        if (v.langKey == lang){
          exists = true;
        }
      })
      if (!exists){
        ret.push(new I18nValue(lang));
      }
    });

    return ret.sort((v1,v2)=>{return v1.langKey.localeCompare(v2.langKey);});
  }

}
