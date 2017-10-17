import { File } from '../../shared/domain/file.model'
import {I18nValue} from '../../shared/domain/i18n.model';

export class WebSiteConfig{

    private name:string;

    backgroundImageFile: File;
    logo500ImageFile: File;
    logo300ImageFile: File;
    
    formContactKey:string;
    email:string;

    fbUrl:string;
    twitterUrl:string;
    instaUrl:string;

    i18nFields: Map<String, I18nValue[]>;

    constructor(){
    }
}