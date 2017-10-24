import { File } from '../../shared/domain/file.model'
import {I18nValue} from '../../shared/domain/i18n.model';

export class Coach{

    id:number;
    private name:string;
    private displayName:string;

    photo: File  = new File();
    

    i18nFields: Map<String, I18nValue[]>;
    competenceI18nFields: Map<String, I18nValue[]>;

    constructor(){
        this.i18nFields = new Map<String, I18nValue[]>();
        this.competenceI18nFields = new Map<String, I18nValue[]>();
    }
}