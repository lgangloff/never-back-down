import { File } from '../../shared/domain/file.model'
import {I18nValue} from '../../shared/domain/i18n.model';

export class Coach{

    private id:number;
    private name:string;
    private displayName:string;

    photo: File;
    

    i18nFields: Map<String, I18nValue[]>;

    constructor(){
    }
}