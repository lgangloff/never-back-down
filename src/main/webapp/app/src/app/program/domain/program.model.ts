import {I18nValue} from '../../shared/domain/i18n.model';

export class Program{

    id:number;
    private name:string;
    private link:string;

    i18nFields: Map<String, I18nValue[]>;

    constructor(){
        this.i18nFields = new Map<String, I18nValue[]>();
    }
}